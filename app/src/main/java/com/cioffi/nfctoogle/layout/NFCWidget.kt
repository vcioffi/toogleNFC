package com.cioffi.nfctoogle.layout;


import NFCToogleRefreshCallback
import android.content.Context
import android.content.Intent
import android.provider.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.glance.ColorFilter
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import com.cioffi.nfctoogle.R
import androidx.glance.action.clickable
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.height
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.cioffi.nfctoogle.CheckNFCWorker
import com.cioffi.nfctoogle.utils.UtilsMethods
import java.util.concurrent.TimeUnit

@Composable
fun NFCWidget(nfcWidgetState: Boolean, context: Context) {
    Column(
        modifier = GlanceModifier
            .fillMaxSize()
            .background(
            ImageProvider(R.drawable.background_widget_negative_rate)),
            verticalAlignment = Alignment.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(verticalAlignment = Alignment.Top) {
                Image(
                    provider = ImageProvider(R.drawable.ic_launcher_foreground),
                    contentDescription = "Refresh",
                    modifier = GlanceModifier.clickable({
                        openNFC(context)
                    }),
                    colorFilter = ColorFilter.tint(UtilsMethods.getColorforNFCStatus(nfcWidgetState))
                )
            }
        Image(
            provider = ImageProvider(R.drawable.ic_refresh),
            contentDescription = "Refresh",
            modifier = GlanceModifier.clickable(actionRunCallback<NFCToogleRefreshCallback>())
        )

        }
}


fun openNFC(context: Context){

    val workManager = WorkManager.getInstance(context)

    val onTimeDailyWorker = OneTimeWorkRequest
        .Builder(CheckNFCWorker::class.java)
        .setInitialDelay(3, TimeUnit.SECONDS)
        .build()

    workManager.enqueueUniqueWork(
        "NFC_CONTROLLER_SINGLE",
        ExistingWorkPolicy.KEEP,
        onTimeDailyWorker
    )


    val intent = Intent(Settings.ACTION_NFC_SETTINGS)
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(context,intent,null)

}



