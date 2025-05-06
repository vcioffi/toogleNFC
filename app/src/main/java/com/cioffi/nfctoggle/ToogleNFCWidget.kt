package com.cioffi.nfctoggle


import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.glance.*
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.glance.state.GlanceStateDefinition
import androidx.glance.state.PreferencesGlanceStateDefinition
import com.cioffi.nfctoggle.layout.NFCWidget
import com.cioffi.nfctoggle.ui.theme.NFCToggleTheme
import com.mertceyhan.bitcoinmarket.utils.extensions.darkModeEnabled


class ToggleNFCWidget : GlanceAppWidget() {


    override val stateDefinition: GlanceStateDefinition<*>
        get() = PreferencesGlanceStateDefinition
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            val state = currentState<Preferences>()
            val nfcWidgetState = state[booleanPreferencesKey("nfc_status")] ?: false

            NFCToggleTheme(darkTheme = context.darkModeEnabled()) {
                NFCWidget(nfcWidgetState,context)
            }
        }
    }

}