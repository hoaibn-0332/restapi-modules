package com.example.core.common.error

import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
abstract class CoreExceptionHandler : ResponseEntityExceptionHandler()
