package com.redbee.offices.application.port.`in`

import com.redbee.offices.domain.Schedule
import java.time.LocalDateTime
import javax.validation.constraints.Future
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty

interface CreateScheduleCommand {

    fun execute(command: Command): Schedule

    data class Command(
        @get:NotEmpty(message = "options should not be empty")
        val options: List<OfficeCommand>,
        @get:Future()
        val day: LocalDateTime
    )

    data class OfficeCommand(
        @get:NotBlank(message = "office id should not be empty")
        val officeId: Long,
        @get:NotBlank(message = "availability id should not be empty")
        val availability: Long
    )

}
