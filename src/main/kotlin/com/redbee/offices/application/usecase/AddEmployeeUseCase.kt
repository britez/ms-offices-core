package com.redbee.offices.application.usecase

import com.redbee.offices.application.exceptions.EmployeeAlreadyExistsException
import com.redbee.offices.application.exceptions.FullOfficeException
import com.redbee.offices.application.port.`in`.AddEmployeeCommand
import com.redbee.offices.application.port.out.ScheduleOfficeRepository
import com.redbee.offices.config.SPError
import com.redbee.offices.domain.Employee
import com.redbee.offices.domain.OfficeSchedule
import com.redbee.offices.domain.ScheduleState
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class AddEmployeeUseCase(
  var scheduleOfficeRepository: ScheduleOfficeRepository
): AddEmployeeCommand {

  private val log = LoggerFactory.getLogger(AddEmployeeUseCase::class.java)

  override fun execute(scheduleId: Long, scheduleOfficeId: Long, command: AddEmployeeCommand.Command): OfficeSchedule =
    log
      .info("Attempt to find schedule office with id $scheduleOfficeId and schedule id $scheduleId")
      .let { scheduleOfficeRepository.findByOfficeIdAndScheduleIdAndScheduleState(
        scheduleOfficeId, scheduleId, ScheduleState.OPEN) }
      .also { if (it.employees.find { em -> em.mail == command.mail } != null)
        throw EmployeeAlreadyExistsException(SPError.BAD_REQUEST.errorCode, ALREADY_MESSAGE.format(command.mail)) }
      .also { if (it.availability <= 0) throw FullOfficeException(SPError.BAD_REQUEST.errorCode, FULL_MESSAGE) }
      .addEmployee(Employee(mail = command.mail))
      .let { scheduleOfficeRepository.update(it) }
      .also { log.info("Schedule office updated successfully") }

  companion object {
    private const val FULL_MESSAGE:String = "The office is full"
    private const val ALREADY_MESSAGE:String = "The mail %s is already added"
  }

}
