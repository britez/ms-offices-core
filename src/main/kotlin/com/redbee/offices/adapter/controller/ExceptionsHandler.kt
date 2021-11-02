package com.redbee.offices.adapter.controller

import brave.Tracer
import com.redbee.offices.adapter.postgres.exceptions.EntityNotFoundException
import com.redbee.offices.application.exceptions.EmployeeAlreadyExistsException
import com.redbee.offices.application.exceptions.FullOfficeException
import com.redbee.offices.config.SPError
import com.redbee.offices.config.TraceSleuthInterceptor
import com.redbee.offices.extensions.toIsoString
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ExceptionsHandler(
  private val httpServletRequest: HttpServletRequest,
  private val tracer: Tracer?
) {

  private val log = LoggerFactory.getLogger(ExceptionHandler::class.java)

  @ExceptionHandler(Throwable::class)
  fun handle(ex: Throwable): ResponseEntity<ApiErrorResponse> {
    log.error(HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase, ex)
    return buildResponseError(HttpStatus.INTERNAL_SERVER_ERROR, ex, SPError.INTERNAL_ERROR.errorCode)
  }

  @ExceptionHandler(EntityNotFoundException::class)
  fun handle(ex: EntityNotFoundException): ResponseEntity<ApiErrorResponse> {
    log.error(HttpStatus.NOT_FOUND.reasonPhrase, ex)
    return buildResponseError(HttpStatus.NOT_FOUND, ex, ex.errorCode)
  }

  @ExceptionHandler(FullOfficeException::class)
  fun handle(ex: FullOfficeException): ResponseEntity<ApiErrorResponse> {
    log.error(HttpStatus.BAD_REQUEST.reasonPhrase, ex)
    return buildResponseError(HttpStatus.BAD_REQUEST, ex, ex.errorCode)
  }

  @ExceptionHandler(EmployeeAlreadyExistsException::class)
  fun handle(ex: EmployeeAlreadyExistsException): ResponseEntity<ApiErrorResponse> {
    log.error(HttpStatus.BAD_REQUEST.reasonPhrase, ex)
    return buildResponseError(HttpStatus.BAD_REQUEST, ex, ex.errorCode)
  }

  @ExceptionHandler(MethodArgumentNotValidException::class)
  fun handle(ex: MethodArgumentNotValidException): ResponseEntity<ApiErrorResponse> {
    log.error(HttpStatus.BAD_REQUEST.reasonPhrase, ex)
    return buildResponseError(HttpStatus.BAD_REQUEST, ex, SPError.BAD_REQUEST.errorCode)
  }

  private fun buildResponseError(
    httpStatus: HttpStatus,
    ex: Throwable,
    errorCode: Int,
    errorMessage: String = ex.message ?: ""): ResponseEntity<ApiErrorResponse> {

    val traceId = tracer
      ?.currentSpan()
      ?.context()
      ?.traceIdString()
      ?: TraceSleuthInterceptor.TRACE_ID_NOT_EXISTS

    val spanId = tracer
      ?.currentSpan()
      ?.context()
      ?.spanIdString()
      ?: TraceSleuthInterceptor.SPAND_ID_NOT_EXISTS

    val apiErrorResponse = ApiErrorResponse(
      timestamp = LocalDateTime.now(ZoneOffset.UTC).toIsoString(),
      name = httpStatus.reasonPhrase,
      detail = errorMessage,
      status = httpStatus.value(),
      code = errorCode,
      resource = httpServletRequest.requestURI,
      metadata = Metadata(xB3TraceId = traceId, xB3SpanId = spanId)
    )

    return ResponseEntity(apiErrorResponse, httpStatus)
  }

  data class Metadata(
    val xB3TraceId: String,
    val xB3SpanId: String
  )

  data class ApiErrorResponse(
    val name: String,
    val status: Int,
    val timestamp: String,
    val code: Int,
    val resource: String,
    val detail: String,
    val metadata: Metadata
  )

}
