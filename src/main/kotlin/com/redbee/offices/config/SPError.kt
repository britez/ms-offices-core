package com.redbee.offices.config

enum class SPError(val errorCode: Int, val defaultMessage: String) {

  INTERNAL_ERROR(100, "Error interno del servidor"),
  RESOURCE_NOT_FOUND(101, "No se encontro recurso solicitado"),
  BAD_REQUEST(102, "Error del cliente")

}

