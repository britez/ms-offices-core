package com.redbee.offices.application.usecase

import com.redbee.offices.application.port.`in`.RemoveEmployeeCommand
import com.redbee.offices.application.port.out.ScheduleOfficeRepository
import com.redbee.offices.domain.OfficeSchedule
import com.redbee.offices.domain.ScheduleState
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class RemoveEmployeeUseCase(
  var scheduleOfficeRepository: ScheduleOfficeRepository
): RemoveEmployeeCommand {

  private val log = LoggerFactory.getLogger(RemoveEmployeeUseCase::class.java)

  override fun execute(
    scheduleId: Long,
    scheduleOfficeId: Long,
    command: RemoveEmployeeCommand.Command
  ): OfficeSchedule =
    scheduleOfficeRepository
      .also { log.info("Attempt to find schedule office with office id $scheduleOfficeId and schedule $scheduleId") }
      .findByOfficeIdAndScheduleIdAndScheduleState(scheduleOfficeId, scheduleId, ScheduleState.OPEN)
      .let { it.copy(
        availability = it.availability + 1,
        employees = it.employees.filter { em -> em.mail === command.mail }.toMutableSet()) }
      .let { scheduleOfficeRepository.update(it) }

}
