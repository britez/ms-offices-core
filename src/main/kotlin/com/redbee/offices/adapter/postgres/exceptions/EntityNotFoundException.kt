package com.redbee.offices.adapter.postgres.exceptions

import com.redbee.offices.application.exceptions.GenericException

class EntityNotFoundException(
    errorCode: Int,
    message: String,
    cause: Throwable? = null
) : GenericException(
    errorCode,
    message,
    cause
)