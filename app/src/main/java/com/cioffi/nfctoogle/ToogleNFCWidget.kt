package com.cioffi.nfctoogle


import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.glance.*
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.glance.state.GlanceStateDefinition
import androidx.glance.state.PreferencesGlanceStateDefinition
import com.cioffi.nfctoogle.glance.ToogleWidgetReciver
import com.cioffi.nfctoogle.layout.NFCWidget
import com.cioffi.nfctoogle.ui.theme.NFCToogleTheme
import com.mertceyhan.bitcoinmarket.utils.extensions.darkModeEnabled


class ToogleNFCWidget : GlanceAppWidget() {


    override val stateDefinition: GlanceStateDefinition<*>
        get() = PreferencesGlanceStateDefinition
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            val prefs = currentState<Preferences>()
            val nfcStatus = prefs[ToogleWidgetReciver.nfcStatus]

            NFCToogleTheme(darkTheme = context.darkModeEnabled()) {
                NFCWidget(nfcStatus!!,context)
            }
        }
    }


}