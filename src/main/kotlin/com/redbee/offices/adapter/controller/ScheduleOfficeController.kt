package com.redbee.offices.adapter.controller

import com.redbee.offices.adapter.controller.domain.DetailOfficeScheduleResponse
import com.redbee.offices.application.port.`in`.AddEmployeeCommand
import com.redbee.offices.application.port.`in`.RemoveEmployeeCommand
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RequestMapping("/schedules/{scheduleId}")
@RestController
class ScheduleOfficeController(
    val addEmployeeCommand: AddEmployeeCommand,
    val removeEmployeeCommand: RemoveEmployeeCommand
) {

    @PutMapping("/offices/{officeId}/employees")
    fun addEmployee(
        @PathVariable("scheduleId") scheduleId:Long,
        @PathVariable("officeId") officeId:Long,
        @Validated @RequestBody command: AddEmployeeCommand.Command): DetailOfficeScheduleResponse =
        addEmployeeCommand
            .execute(scheduleId, officeId, command)
            .let { DetailOfficeScheduleResponse(
                it.id!!,
                it.office.id!!,
                it.availability,
                employees = it.employees.map {
                        em -> DetailOfficeScheduleResponse.
                EmployeeResponse(mail = em.mail) }) }

    @DeleteMapping("/offices/{officeId}/employees")
    fun removeEmployee(
        @PathVariable("scheduleId") scheduleId:Long,
        @PathVariable("officeId") officeId:Long,
        @Validated @RequestBody command: RemoveEmployeeCommand.Command):DetailOfficeScheduleResponse =
        removeEmployeeCommand
            .execute(scheduleId, officeId, command)
            .let { DetailOfficeScheduleResponse(
                it.id!!,
                it.office.id!!,
                it.availability,
                employees = it.employees.map {
                        em -> DetailOfficeScheduleResponse.
                EmployeeResponse(mail = em.mail) }) }
}