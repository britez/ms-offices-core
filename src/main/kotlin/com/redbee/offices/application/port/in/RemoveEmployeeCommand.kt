package com.redbee.offices.application.port.`in`

import com.redbee.offices.domain.OfficeSchedule
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

interface RemoveEmployeeCommand {

    fun execute(scheduleId:Long, scheduleOfficeId:Long): OfficeSchedule

}
