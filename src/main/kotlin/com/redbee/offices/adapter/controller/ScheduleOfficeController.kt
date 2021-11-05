package com.redbee.offices.adapter.controller

import com.redbee.offices.adapter.controller.domain.DetailOfficeScheduleResponse
import com.redbee.offices.application.port.`in`.AddEmployeeCommand
import com.redbee.offices.application.port.`in`.GetScheduleOfficePortIn
import com.redbee.offices.application.port.`in`.RemoveEmployeeCommand
import com.redbee.offices.domain.OfficeSchedule
import org.springframework.web.bind.annotation.*

@RequestMapping("/schedules/{scheduleId}")
@RestController
class ScheduleOfficeController(
    private val getScheduleOfficePortIn: GetScheduleOfficePortIn,
    val addEmployeeCommand: AddEmployeeCommand,
    val removeEmployeeCommand: RemoveEmployeeCommand
) {

    @PutMapping("/offices/{officeId}/employees")
    fun addEmployee(
        @PathVariable("scheduleId") scheduleId:Long,
        @PathVariable("officeId") officeId:Long): DetailOfficeScheduleResponse =
        addEmployeeCommand
            .execute(scheduleId, officeId)
            .let { buildResponse(it) }

    @DeleteMapping("/offices/{officeId}/employees")
    fun removeEmployee(
        @PathVariable("scheduleId") scheduleId:Long,
        @PathVariable("officeId") officeId:Long):DetailOfficeScheduleResponse =
        removeEmployeeCommand
            .execute(scheduleId, officeId)
            .let { buildResponse(it) }

    @GetMapping("/offices/{officeId}")
    fun get(
        @PathVariable("scheduleId") scheduleId:Long,
        @PathVariable("officeId") officeId:Long):DetailOfficeScheduleResponse =
        getScheduleOfficePortIn
            .execute(scheduleId, officeId)
            .let { buildResponse(it) }


    private fun buildResponse(officeSchedule:OfficeSchedule):DetailOfficeScheduleResponse =
        DetailOfficeScheduleResponse(
            officeSchedule.id!!,
            officeSchedule.office.id!!,
            officeSchedule.availability,
            employees = officeSchedule.employees.map {
                    em -> DetailOfficeScheduleResponse.
            EmployeeResponse(
                mail = em.mail,
                givenName = em.givenName,
                name = em.name,
                familyName = em.familyName,
                picture = em.picture) })
}