package com.cioffi.nfctoogle.glance

import NFCToogleRefreshCallback
import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.nfc.NfcAdapter
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.appwidget.updateAll
import androidx.glance.state.PreferencesGlanceStateDefinition
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.cioffi.nfctoogle.CheckNFCWorker
import com.cioffi.nfctoogle.ToogleNFCWidget
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit


class ToogleWidgetReciver : GlanceAppWidgetReceiver() {

    override val glanceAppWidget: GlanceAppWidget = ToogleNFCWidget()
    private val coroutineScope = MainScope()



    private fun observeData(context: Context) {
        coroutineScope.launch {

            val nfcAdpt = NfcAdapter.getDefaultAdapter(context)
            var isNFCOn by mutableStateOf(nfcAdpt.isEnabled())

            scheduleNFCWorker(context)
            val glanceId =
                GlanceAppWidgetManager(context).getGlanceIds(ToogleNFCWidget::class.java).firstOrNull()

            glanceId?.let {
                updateAppWidgetState(context, PreferencesGlanceStateDefinition, it) { pref ->
                    pref.toMutablePreferences().apply {
                        this[nfcStatus] = isNFCOn
                    }
                }
                glanceAppWidget.update(context, it)
            }
        }
    }

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        observeData(context)
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        if (intent.action == NFCToogleRefreshCallback.UPDATE_ACTION) {
            observeData(context)
        }
    }



    companion object {
        val nfcStatus = booleanPreferencesKey("nfc_status")
    }

    fun scheduleNFCWorker(context: Context) {

        val workManager = WorkManager.getInstance(context)


        val request: PeriodicWorkRequest = PeriodicWorkRequest.Builder(
            CheckNFCWorker::class.java, 15, TimeUnit.MINUTES, 15, TimeUnit.MINUTES)
            .build()

        workManager.enqueueUniquePeriodicWork(
            "NFC_CONTROLLER",
            ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE,
            request
        )
    }

}