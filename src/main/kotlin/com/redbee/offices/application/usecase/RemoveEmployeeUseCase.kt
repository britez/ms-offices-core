package com.redbee.offices.application.usecase

import com.redbee.offices.application.port.`in`.RemoveEmployeeCommand
import com.redbee.offices.application.port.out.ScheduleOfficeRepository
import com.redbee.offices.application.service.EmployeeService
import com.redbee.offices.domain.OfficeSchedule
import com.redbee.offices.domain.ScheduleState
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class RemoveEmployeeUseCase(
  var scheduleOfficeRepository: ScheduleOfficeRepository,
  private val employeeService: EmployeeService
): RemoveEmployeeCommand {

  private val log = LoggerFactory.getLogger(RemoveEmployeeUseCase::class.java)

  override fun execute(
    scheduleId: Long,
    scheduleOfficeId: Long
  ): OfficeSchedule =
    scheduleOfficeRepository
      .also { log.info("Attempt to find schedule office with office id $scheduleOfficeId and schedule $scheduleId") }
      .findByOfficeIdAndScheduleIdAndScheduleState(scheduleOfficeId, scheduleId, ScheduleState.OPEN)
      .let { it.copy(
        availability = it.availability + 1,
        employees = it.employees.filter { em -> em.id != employeeService.getAuthentication().id }.toMutableSet()) }
      .let { scheduleOfficeRepository.update(it) }

}
