package com.redbee.offices.adapter.controller.domain

data class DetailOfficeScheduleResponse(
  val id:Long,
  val officeId:Long,
  val availability: Long,
  val employees: List<EmployeeResponse>
) {
  data class EmployeeResponse(
    val mail: String,
    val name: String,
    val givenName: String,
    val familyName: String,
    val picture: String)
}
