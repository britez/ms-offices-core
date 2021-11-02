package com.redbee.offices.application.port.out

import com.redbee.offices.domain.OfficeSchedule
import com.redbee.offices.domain.ScheduleState

interface ScheduleOfficeRepository {
  fun findByOfficeIdAndScheduleIdAndScheduleState(id:Long, scheduleId:Long, state: ScheduleState): OfficeSchedule
  fun update(it: OfficeSchedule):OfficeSchedule
  fun create(it: OfficeSchedule):OfficeSchedule

  companion object {

  }
}
