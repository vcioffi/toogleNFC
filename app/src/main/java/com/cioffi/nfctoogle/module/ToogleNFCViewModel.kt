package com.cioffi.nfctoogle.module

import android.nfc.NfcAdapter
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ToogleNFCViewModel: ViewModel()  {

    private var nfcAdapter: NfcAdapter? = null
    private var isNFCOn by mutableStateOf(!nfcAdapter!!.isEnabled)


    private fun chekcNFCSts(): Boolean {
        return isNFCOn
    }

}