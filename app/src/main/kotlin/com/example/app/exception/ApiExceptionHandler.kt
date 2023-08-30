package com.example.app.exception

import com.example.core.common.LocalizedException
import com.example.core.common.error.CoreExceptionHandler
import com.example.core.common.error.Error
import com.example.core.common.response.ErrorResponse
import org.springframework.beans.ConversionNotSupportedException
import org.springframework.beans.TypeMismatchException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.http.converter.HttpMessageNotWritableException
import org.springframework.web.HttpMediaTypeNotAcceptableException
import org.springframework.web.HttpMediaTypeNotSupportedException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.*
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.context.request.async.AsyncRequestTimeoutException
import org.springframework.web.multipart.support.MissingServletRequestPartException
import org.springframework.web.servlet.NoHandlerFoundException
import java.text.MessageFormat

@RestControllerAdvice
class ApiExceptionHandler : CoreExceptionHandler() {
    override fun handleHttpRequestMethodNotSupported(
        ex: HttpRequestMethodNotSupportedException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        pageNotFoundLogger.warn(ex.message)
        ex.supportedHttpMethods?.let { headers.allow = it }

        val localizedException = LocalizedException("http_request_method_not_supported")

        val error = ErrorResponse(
            HttpStatus.METHOD_NOT_ALLOWED,
            Error(localizedException.getCode(), localizedException.getMessage())
        )
        return handleExceptionInternal(ex, error, headers, error.status, request)
    }

    override fun handleHttpMediaTypeNotSupported(
        ex: HttpMediaTypeNotSupportedException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        ex.supportedMediaTypes.let { headers.accept = it }

        val localizedException = LocalizedException("http_media_type_not_supported")

        val error = ErrorResponse(
            HttpStatus.UNSUPPORTED_MEDIA_TYPE,
            Error(localizedException.getCode(), localizedException.getMessage())
        )
        return handleExceptionInternal(ex, error, headers, error.status, request)
    }

    override fun handleHttpMediaTypeNotAcceptable(
        ex: HttpMediaTypeNotAcceptableException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        ex.supportedMediaTypes.let { headers.accept = it }

        val localizedException = LocalizedException("http_media_type_not_supported")

        val error = ErrorResponse(
            HttpStatus.BAD_REQUEST,
            Error(localizedException.getCode(), localizedException.getMessage())
        )
        return handleExceptionInternal(ex, error, headers, error.status, request)
    }

    override fun handleMissingPathVariable(
        ex: MissingPathVariableException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        val localizedException = LocalizedException("missing_path_variable")

        val error = ErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR,
            Error(localizedException.getCode(), localizedException.getMessage())
        )
        return handleExceptionInternal(ex, error, headers, error.status, request)
    }

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        val localizedException = LocalizedException("method_argument_not_valid")

        val error = ErrorResponse(
            HttpStatus.BAD_REQUEST,
            Error(localizedException.getCode(), localizedException.getMessage())
        )
        return handleExceptionInternal(ex, error, headers, error.status, request)
    }

    override fun handleMissingServletRequestParameter(
        ex: MissingServletRequestParameterException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        val localizedException = LocalizedException("missing_parameter")

        val error = ErrorResponse(
            HttpStatus.BAD_REQUEST,
            Error(localizedException.getCode(), localizedException.getMessage())
        )
        return handleExceptionInternal(ex, error, headers, error.status, request)
    }

    override fun handleServletRequestBindingException(
        ex: ServletRequestBindingException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        val cause = ex.cause
        var localizedException = LocalizedException("servlet_request_binding")
        var message = localizedException.getMessage()

        when (cause) {
            is MissingMatrixVariableException -> {
                localizedException = LocalizedException("missing_matrix_variable")
                message = MessageFormat.format(
                    localizedException.getMessage(),
                    cause.variableName,
                    cause.parameter.nestedParameterType.simpleName
                )
            }
            is MissingRequestCookieException -> {
                localizedException = LocalizedException("missing_request_cookie")
                message = MessageFormat.format(
                    localizedException.getMessage(),
                    cause.cookieName,
                    cause.parameter.nestedParameterType.simpleName
                )
            }
            is MissingRequestHeaderException -> {
                localizedException = LocalizedException("missing_request_header")
                message = MessageFormat.format(
                    localizedException.getMessage(),
                    cause.headerName,
                    cause.parameter.nestedParameterType.simpleName
                )
            }
        }

        val error = ErrorResponse(
            HttpStatus.BAD_REQUEST,
            Error(localizedException.getCode(), message)
        )
        return handleExceptionInternal(ex, error, headers, error.status, request)
    }

    override fun handleConversionNotSupported(
        ex: ConversionNotSupportedException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        val localizedException = LocalizedException("conversion_not_supported")
        val className = ex.value?.javaClass?.name ?: ex.javaClass.name
        val error = ErrorResponse(
            HttpStatus.BAD_REQUEST,
            Error(
                localizedException.getCode(),
                MessageFormat.format(localizedException.getMessage(), className, ex.requiredType)
            )
        )
        return handleExceptionInternal(ex, error, headers, error.status, request)
    }

    override fun handleTypeMismatch(
        ex: TypeMismatchException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        val localizedException = LocalizedException("type_mismatch")
        val className = ex.value?.javaClass?.name ?: ex.javaClass.name
        val error = ErrorResponse(
            HttpStatus.BAD_REQUEST,
            Error(
                localizedException.getCode(),
                MessageFormat.format(localizedException.getMessage(), className, ex.requiredType)
            )
        )
        return handleExceptionInternal(ex, error, headers, error.status, request)
    }

    override fun handleHttpMessageNotReadable(
        ex: HttpMessageNotReadableException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        val localizedException = LocalizedException("http_message_not_readable")
        val error = ErrorResponse(
            HttpStatus.UNPROCESSABLE_ENTITY,
            Error(localizedException.getCode(), localizedException.getMessage())
        )
        return handleExceptionInternal(ex, error, headers, error.status, request)
    }

    override fun handleHttpMessageNotWritable(
        ex: HttpMessageNotWritableException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        val localizedException = LocalizedException("http_message_not_writable")
        val error = ErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR,
            Error(
                localizedException.getCode(),
                MessageFormat.format(localizedException.getMessage(), ex.message).trim()
            )
        )
        return handleExceptionInternal(ex, error, headers, error.status, request)
    }

    override fun handleMissingServletRequestPart(
        ex: MissingServletRequestPartException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        val localizedException = LocalizedException("missing_servlet_request_part")
        val error = ErrorResponse(
            HttpStatus.BAD_REQUEST,
            Error(
                localizedException.getCode(),
                MessageFormat.format(localizedException.getMessage(), ex.requestPartName).trim()
            )
        )
        return handleExceptionInternal(ex, error, headers, error.status, request)
    }

    override fun handleNoHandlerFoundException(
        ex: NoHandlerFoundException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        val localizedException = LocalizedException("no_handler_found")
        val error = ErrorResponse(
            HttpStatus.BAD_REQUEST,
            Error(
                localizedException.getCode(),
                MessageFormat.format(
                    localizedException.getMessage(),
                    ex.httpMethod,
                    ex.requestURL
                ).trim()
            )
        )
        return handleExceptionInternal(ex, error, headers, error.status, request)
    }

    override fun handleAsyncRequestTimeoutException(
        ex: AsyncRequestTimeoutException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        val localizedException = LocalizedException("async_request_timeout")
        val error = ErrorResponse(
            HttpStatus.SERVICE_UNAVAILABLE,
            Error(localizedException.getCode(), MessageFormat.format(localizedException.getMessage()))
        )
        return handleExceptionInternal(ex, error, headers, error.status, request)
    }
}
