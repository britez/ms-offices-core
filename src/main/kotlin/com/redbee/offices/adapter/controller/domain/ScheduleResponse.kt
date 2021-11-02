package com.redbee.offices.adapter.controller.domain

data class ScheduleResponse(
  val id:Long,
  val day:String,
  val state:String,
  val offices:List<OfficeScheduleResponse>,
)
