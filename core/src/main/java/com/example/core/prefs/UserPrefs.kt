package com.example.core.prefs

import android.content.Context
import android.content.SharedPreferences
import com.example.core.di.AppScope
import com.example.core.prefs.delegates.IntPreference
import com.example.core.prefs.delegates.StringPreference
import com.google.crypto.tink.Aead
import com.google.crypto.tink.DeterministicAead
import com.ironz.binaryprefs.BinaryPreferencesBuilder

@AppScope
class UserPrefs constructor(
    context: Context,
    aead: Aead,
    daead: DeterministicAead
) : SharedPreferences by BinaryPreferencesBuilder(context)
    .keyEncryption(TinkKeyEncryption(daead))
    .valueEncryption(TinkValueEncryption(aead))
    .name("user_prefs")
    .build() {

    var sessionId by StringPreference(
        key = "sessionId",
        defValue = null
    )
    var userLogin by StringPreference(
        key = "userLogin",
        defValue = null
    )
    var userPassword by StringPreference(
        key = "userPassword",
        defValue = null
    )
    var userName by StringPreference(
        key = "userName",
        defValue = null
    )
    var userId by IntPreference(
        key = "userId",
        defValue = -1
    )
    var userPincode by StringPreference(
        key = "userPincode",
        defValue = null
    )

    fun deleteAllPrefs() = this.edit().clear().apply()
}
