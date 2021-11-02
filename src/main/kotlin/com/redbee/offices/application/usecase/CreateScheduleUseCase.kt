package com.redbee.offices.application.usecase

import com.redbee.offices.application.port.`in`.CreateScheduleCommand
import com.redbee.offices.application.port.out.OfficeRepository
import com.redbee.offices.application.port.out.ScheduleOfficeRepository
import com.redbee.offices.application.port.out.ScheduleRepository
import com.redbee.offices.domain.OfficeSchedule
import com.redbee.offices.domain.Schedule
import com.redbee.offices.domain.ScheduleState
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class CreateScheduleUseCase(
  var scheduleRepository: ScheduleRepository,
  var scheduleOfficeRepository: ScheduleOfficeRepository,
  var officeRepository: OfficeRepository
): CreateScheduleCommand {

  private val log = LoggerFactory.getLogger(CreateScheduleUseCase::class.java)

  override fun execute(command: CreateScheduleCommand.Command): Schedule =
    scheduleRepository
      .also { log.info("Attempt to create schedule {}", command) }
      .let { Schedule(
        day = command.day,
        state = ScheduleState.CRATED) }
      //.let { scheduleRepository.create(it) }
      .also {
          it.addOfficeSchedule(command.options.map { op ->
            scheduleOfficeRepository.create(
              OfficeSchedule(
              office = officeRepository.findById(op.officeId),
              availability = op.availability,
              schedule = it)
            ) }) }
//      .also { log.info("Office created {}", it) }

}
