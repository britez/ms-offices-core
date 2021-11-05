package com.redbee.offices.application.exceptions

class CannotExtractMailException (
    errorCode: Int,
    message: String,
    cause: Throwable? = null
) : GenericException(
    errorCode,
    message,
    cause
)