package com.cioffi.nfctoogle.layout;


import NFCToogleRefreshCallback
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.cioffi.nfctoogle.ui.theme.purple200
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
import androidx.glance.layout.fillMaxSize
import com.cioffi.nfctoogle.utils.UtilsMethods

@Composable
fun NFCWidget(nfcWidgetState: Boolean, context: Context) {
    Column(
        modifier = GlanceModifier
            .fillMaxSize()
            .background(
                ImageProvider(R.drawable.background_widget_negative_rate)
            ),
        verticalAlignment = Alignment.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(horizontalAlignment = Alignment.CenterHorizontally, verticalAlignment = Alignment.CenterVertically) {
            Image(
                provider = ImageProvider(R.drawable.outline_power_settings_new_15),
                contentDescription = "Open Setting",
                modifier = GlanceModifier.clickable(actionRunCallback<NFCToogleRefreshCallback>()),
            )
        }
        Row(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                provider = ImageProvider(R.drawable.nfc_icon),
                contentDescription = "Refresh",
                modifier = GlanceModifier.clickable(actionRunCallback<NFCToogleRefreshCallback>()),
                colorFilter = ColorFilter.tint(UtilsMethods.getColorforNFCStatus(nfcWidgetState))
            )
        }
    }
}


