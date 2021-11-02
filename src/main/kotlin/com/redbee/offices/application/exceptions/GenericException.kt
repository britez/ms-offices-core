package com.redbee.offices.application.exceptions

abstract class GenericException(
    val errorCode: Int,
    message: String,
    cause: Throwable? = null
) : RuntimeException(
    message,
    cause
)