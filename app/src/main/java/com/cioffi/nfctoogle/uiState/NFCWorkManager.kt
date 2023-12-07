

package com.example.android.appwidget.glance.weather

import NFCToogleRefreshCallback
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.appwidget.updateAll
import androidx.work.*
import com.cioffi.nfctoogle.ToogleNFCWidget
import com.cioffi.nfctoogle.glance.ToogleWidgetReciver
import com.cioffi.nfctoogle.uiState.NFCWidgetUiState
import java.time.Duration

class NFCWorkManager(
    private val context: Context,
    workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {

    companion object {

        private val uniqueWorkName = NFCWorkManager::class.java.simpleName

        /**
         * Enqueues a new worker to refresh weather data only if not enqueued already
         *
         * Note: if you would like to have different workers per widget instance you could provide
         * the unique name based on some criteria (e.g selected weather location).
         *
         * @param force set to true to replace any ongoing work and expedite the request
         */
        fun enqueue(context: Context, force: Boolean = false) {
            val manager = WorkManager.getInstance(context)
            val requestBuilder = PeriodicWorkRequestBuilder<NFCWorkManager>(
                Duration.ofMinutes(2)
            )
            var workPolicy = ExistingPeriodicWorkPolicy.KEEP

            // Replace any enqueued work and expedite the request
            if (force) {
                workPolicy = ExistingPeriodicWorkPolicy.REPLACE
            }

            manager.enqueueUniquePeriodicWork(
                uniqueWorkName,
                workPolicy,
                requestBuilder.build()
            )
        }

        /**
         * Cancel any ongoing worker
         */
        fun cancel(context: Context) {
            WorkManager.getInstance(context).cancelUniqueWork(uniqueWorkName)
        }
    }

    override suspend fun doWork(): Result {
        val manager = GlanceAppWidgetManager(context)
        val glanceIds = manager.getGlanceIds(ToogleNFCWidget::class.java)
        return try {
            // Update state to indicate loading
            updateWidgetState(glanceIds)
            Result.success()
        } catch (e: Exception) {
            updateWidgetState(glanceIds)
            if (runAttemptCount < 10) {
                // Exponential backoff strategy will avoid the request to repeat
                // too fast in case of failures.
                Result.retry()
            } else {
                Result.failure()
            }
        }
    }


    private fun updateWidgetState(glanceIds: List<GlanceId>) {
        Log.v("Test2", "execute call back ")
        val intent = Intent(context, ToogleWidgetReciver::class.java).apply {
            action = NFCToogleRefreshCallback.UPDATE_ACTION
        }
        context.sendBroadcast(intent)
    }
}