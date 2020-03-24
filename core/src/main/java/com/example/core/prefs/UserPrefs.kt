package com.example.core.prefs

import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPrefs @Inject constructor(
    context: Context
) : BasePrefs(context, "user_prefs") {

    var sessionId by StringPreference(key = "sessionId", defValue = null)
    var userLogin by StringPreference(key = "userLogin", defValue = null)
    var userPassword by StringPreference(key = "userPassword", defValue = null)
}
