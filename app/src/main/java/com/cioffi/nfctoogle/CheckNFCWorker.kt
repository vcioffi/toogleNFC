package com.cioffi.nfctoogle

import android.content.Context
import android.nfc.NfcAdapter
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.appwidget.updateAll
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.cioffi.nfctoogle.utils.makeStatusNotification


private const val  TAG = "Test Worker"

 class CheckNFCWorker  constructor(
     private val context: Context,
     workParams: WorkerParameters,
) : CoroutineWorker(context, workParams) {



    override suspend fun doWork(): Result {

        val nfcAdpt = NfcAdapter.getDefaultAdapter(context)
        var isNFCOn by mutableStateOf(nfcAdpt.isEnabled())

        if(isNFCOn) {
            makeStatusNotification(
                "NFC is On",
                context
            )
        }
        return try {
            // Update the widget's text content
            updateWidget(isNFCOn, context)
            Result.success()
        } catch(e: Exception) {
            Log.e(TAG, "Exception in Worker")
            Result.retry()
        }
    }



    suspend fun updateWidget(nfcStatus: Boolean, context: Context) {
        // Iterate through all the available glance id's.
        GlanceAppWidgetManager(context).getGlanceIds(ToogleNFCWidget::class.java).forEach { glanceId ->
            updateAppWidgetState(context, glanceId) { prefs ->
                prefs[booleanPreferencesKey("nfc_status")] = nfcStatus //new value
            }
        }
        ToogleNFCWidget().updateAll(context)
    }

}