package com.redbee.offices.application.port.`in`

import com.redbee.offices.domain.OfficeSchedule

interface GetScheduleOfficePortIn {

    fun execute(scheduleId:Long, officeId:Long): OfficeSchedule

}
