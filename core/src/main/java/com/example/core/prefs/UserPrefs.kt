package com.example.core.prefs

import android.content.Context
import com.example.core.di.AppScope
import javax.inject.Inject

@AppScope
class UserPrefs @Inject constructor(
    context: Context
) : BasePrefs(context, "user_prefs") {

    var sessionId by StringPreference(key = "sessionId", defValue = null)
    var userLogin by StringPreference(key = "userLogin", defValue = null)
    var userPassword by StringPreference(key = "userPassword", defValue = null)

    fun deleteAllPrefs() = this.edit().clear().apply()
}
