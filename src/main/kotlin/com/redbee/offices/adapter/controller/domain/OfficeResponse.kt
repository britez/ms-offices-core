package com.redbee.offices.adapter.controller.domain

data class OfficeResponse(
  val id:Long,
  val name:String,
  val address:String,
  val location:LocationResponse
) {
  data class LocationResponse(
    val longitude: String,
    val latitude: String)
}
