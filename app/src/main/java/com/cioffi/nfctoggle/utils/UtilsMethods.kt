package com.cioffi.nfctoggle.utils

import androidx.compose.ui.graphics.Color
import androidx.glance.unit.ColorProvider

class UtilsMethods {


    companion object {
        fun getColorforNFCStatus(nfcWidgetState: Boolean): ColorProvider {
            return if (nfcWidgetState) ColorProvider(Color(0xFF5BB45E))
            else ColorProvider(Color(0xFFFF5050))
        }
    }

}