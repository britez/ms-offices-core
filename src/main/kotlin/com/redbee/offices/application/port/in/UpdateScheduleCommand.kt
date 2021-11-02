package com.redbee.offices.application.port.`in`

import com.redbee.offices.domain.Schedule
import com.redbee.offices.domain.ScheduleState
import java.time.LocalDateTime
import javax.validation.constraints.Future

interface UpdateScheduleCommand {

    fun execute(id:Long, command: Command): Schedule

    data class Command(
        @get:Future
        val day:LocalDateTime,
        val state: ScheduleState,
        val options: List<CreateScheduleCommand.OfficeCommand>?

    )

}
