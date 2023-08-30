package com.example.core.common.error

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Error(
    @SerializedName("error_code") var errorCode: Int = 0,
    @SerializedName("error_message") var errorMessage: String = ""
) : Serializable
