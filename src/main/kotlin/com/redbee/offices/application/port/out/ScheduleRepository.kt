package com.redbee.offices.application.port.out

import com.redbee.offices.domain.Schedule

interface ScheduleRepository {
  fun create(schedule: Schedule): Schedule
  fun findAll(): List<Schedule>
  fun findById(id:Long): Schedule
  fun update(it: Schedule): Schedule

  companion object {

  }
}
