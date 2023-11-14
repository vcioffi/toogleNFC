package com.cioffi.nfctoogle

import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver

class ToogleWidgetReciver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = ToogleNFCWidget()
}