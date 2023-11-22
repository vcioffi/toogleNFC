package com.cioffi.nfctoogle.layout;


import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.glance.Button
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.Switch
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.text.Text
import com.cioffi.nfctoogle.uiState.NFCWidgetUiState

@Composable
fun NFCWidget() {
    Column(
        modifier = GlanceModifier.fillMaxSize(),
        verticalAlignment = Alignment.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "NFC", modifier = GlanceModifier.padding(12.dp))
        Row(horizontalAlignment = Alignment.CenterHorizontally) {


        }
        //Button(text = "test", onClick = { Log.v("Test2", "Is NFC:  ${isNFCOn}")})
    }
}
