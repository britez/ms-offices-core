package com.redbee.offices.application.port.`in`

import com.redbee.offices.domain.OfficeSchedule

interface AddEmployeeCommand {

    fun execute(scheduleId:Long, scheduleOfficeId:Long): OfficeSchedule

}
