package com.example.core.common

import java.util.*

class LocalizedException(
    private val exceptionKey: String,
    private val keyCode: String = "default"
) {
    fun getMessage(): String {
        return getMessageForLocale("api_error.$exceptionKey.$keyCode.message")
    }

    fun getCode(): Int {
        return getMessageForLocale("api_error.$exceptionKey.$keyCode.code").toIntOrNull() ?: 0
    }

    private fun getMessageForLocale(messageKey: String): String {
        return ResourceBundle.getBundle("messages", Locale.ENGLISH).getString(messageKey)
    }
}
