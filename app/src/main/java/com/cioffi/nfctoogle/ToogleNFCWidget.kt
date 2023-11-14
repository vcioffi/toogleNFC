package com.cioffi.nfctoogle

import android.content.Context
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.text.Text


class ToogleNFCWidget : GlanceAppWidget() {

    override suspend fun provideGlance(context: Context, id: GlanceId) {

        provideContent {
                MyContent()
        }
    }

    //@Preview(showBackground = true, showSystemUi = true)
    @Composable
     fun MyContent() {
        //var checked by remember { mutableStateOf(true) }
                Column(
                    modifier = GlanceModifier.fillMaxSize(),
                    verticalAlignment = Alignment.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "NFC", modifier = GlanceModifier.padding(12.dp))
                    Row(horizontalAlignment = Alignment.CenterHorizontally) {

                        var checked by remember { mutableStateOf(true) }

/*                        Switch(
                            checked = checked,
                            onCheckedChange = {
                                checked = it
                            }
                        )*/


                    }
                }
    }
}
