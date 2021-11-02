package com.redbee.offices.application.exceptions

class FullOfficeException (
    errorCode: Int,
    message: String,
    cause: Throwable? = null
) : GenericException(
    errorCode,
    message,
    cause
)