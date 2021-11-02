package com.redbee.offices.adapter.controller

import com.redbee.offices.adapter.controller.domain.OfficeScheduleResponse
import com.redbee.offices.adapter.controller.domain.ScheduleResponse
import com.redbee.offices.application.port.`in`.CreateScheduleCommand
import com.redbee.offices.application.port.`in`.GetSchedulePortIn
import com.redbee.offices.application.port.`in`.ListSchedulePortIn
import com.redbee.offices.application.port.`in`.UpdateScheduleCommand
import com.redbee.offices.domain.OfficeSchedule
import com.redbee.offices.domain.Schedule
import com.redbee.offices.extensions.toIsoString
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RequestMapping("/schedules")
@RestController
class ScheduleController(
    val createScheduleCommand: CreateScheduleCommand,
    val listSchedulePortIn: ListSchedulePortIn,
    val updateScheduleCommand: UpdateScheduleCommand,
    val getSchedulePortIn: GetSchedulePortIn
) {

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    fun create(@Validated @RequestBody command: CreateScheduleCommand.Command) =
        createScheduleCommand
            .execute(command)
            .let { buildSchedule(it) }

    @GetMapping
    fun list():List<ScheduleResponse> =
        listSchedulePortIn
            .execute()
            .map { buildSchedule(it) }

    @GetMapping("/{id}")
    fun get(@PathVariable id:Long):ScheduleResponse =
        getSchedulePortIn
            .execute(id)
            .let { buildSchedule(it) }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id:Long,
        @Validated @RequestBody command: UpdateScheduleCommand.Command):ScheduleResponse =
        updateScheduleCommand
            .execute(id, command)
            .let { buildSchedule(it) }

    private fun createOfficeSchedule(officeSchedule: OfficeSchedule): OfficeScheduleResponse =
        OfficeScheduleResponse(
            id = officeSchedule.id!!,
            officeId = officeSchedule.office.id!!,
            availability = officeSchedule.availability)

    private fun buildOptions(options: List<OfficeSchedule>?):List<OfficeScheduleResponse> =
        options?.map { op ->  createOfficeSchedule(op)} ?: emptyList()

    private fun buildSchedule(schedule: Schedule):ScheduleResponse =
        ScheduleResponse(
            id = schedule.id!!,
            day = schedule.day.toIsoString(),
            state = schedule.state.toString(),
            offices = buildOptions(schedule.officeSchedule))
}