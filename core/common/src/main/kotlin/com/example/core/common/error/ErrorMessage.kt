package com.example.core.common.error

data class ErrorMessage private constructor(
    val code: String,
    val message: String,
    val data: Any? = null
) {
    constructor(errorType: ErrorType, data: Any? = null) : this(
        code = errorType.code.name,
        message = errorType.message,
        data = data
    )
}
