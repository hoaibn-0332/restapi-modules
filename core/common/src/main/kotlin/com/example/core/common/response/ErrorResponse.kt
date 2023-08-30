package com.example.core.common.response

import com.example.core.common.error.Error
import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.http.HttpStatus
import java.io.Serializable

data class ErrorResponse(
    @JsonIgnore
    var status: HttpStatus,
    var errors: List<Error>
) : Serializable {
    constructor(status: HttpStatus, error: Error) : this(status, listOf(error))
}
