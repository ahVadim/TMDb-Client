package com.example.core.util

object NetworkUtil {
    fun isResponseCodeSuccessful(code: Int): Boolean {
        return code in (200 until 300)
    }
}
