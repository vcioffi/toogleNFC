package com.cioffi.nfctoogle.glance

import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import com.cioffi.nfctoogle.ToogleNFCWidget

class ToogleWidgetReciver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = ToogleNFCWidget()
}