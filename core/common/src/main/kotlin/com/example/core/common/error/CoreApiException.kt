package com.example.core.common.error

class CoreApiException(
    val errorType: ErrorType,
    val data: Any? = null
) : RuntimeException(errorType.message)
