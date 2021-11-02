package com.redbee.offices.application.exceptions

class EmployeeAlreadyExistsException (
    errorCode: Int,
    message: String,
    cause: Throwable? = null
) : GenericException(
    errorCode,
    message,
    cause
)