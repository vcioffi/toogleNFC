package com.cioffi.nfctoogle.layout;


import NFCToogleRefreshCallback
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp

import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import com.cioffi.nfctoogle.R
import androidx.glance.action.clickable
import androidx.glance.appwidget.Switch
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.text.Text
import com.cioffi.nfctoogle.uiState.NFCWidgetUiState

@Composable
fun NFCWidget(nfcWidgetState: NFCWidgetUiState, context: Context) {
    Column(
        modifier = GlanceModifier
            .fillMaxSize()
            .background(
            ImageProvider(R.drawable.background_widget_negative_rate)),
            verticalAlignment = Alignment.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "NFC", modifier = GlanceModifier.padding(12.dp))
            Row(horizontalAlignment = Alignment.CenterHorizontally) {
                var checked by remember { mutableStateOf(nfcWidgetState.nfcStatus) }
                Switch(
                    checked = checked,
                    onCheckedChange = {
                        checked = true
                    }
                )
            }
            Image(
                provider = ImageProvider(R.drawable.ic_refresh),
                contentDescription = "Refresh",
                modifier = GlanceModifier.clickable(actionRunCallback<NFCToogleRefreshCallback>())
            )
        }
}
