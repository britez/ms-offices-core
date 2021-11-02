package com.redbee.offices.adapter.postgres

import com.redbee.offices.adapter.postgres.exceptions.EntityNotFoundException
import com.redbee.offices.application.port.out.ScheduleOfficeRepository
import com.redbee.offices.application.port.out.ScheduleRepository
import com.redbee.offices.config.SPError
import com.redbee.offices.domain.OfficeSchedule
import com.redbee.offices.domain.Schedule
import com.redbee.offices.domain.ScheduleState
import org.springframework.stereotype.Repository

@Repository
class ScheduleAdapter(
  private val scheduleJPARepository: ScheduleJPARepository,
  private val officeScheduleJPARepository: OfficeScheduleJPARepository
): ScheduleRepository, ScheduleOfficeRepository {

  override fun create(schedule: Schedule): Schedule =
    scheduleJPARepository.save(schedule)

  override fun findAll(): List<Schedule> =
    scheduleJPARepository.findAll().toList()

  override fun findById(id:Long): Schedule =
    scheduleJPARepository
      .findById(id)
      .orElseThrow { EntityNotFoundException(SPError.RESOURCE_NOT_FOUND.errorCode, MESSAGE.format(id)) }

  override fun update(it: Schedule): Schedule =
    scheduleJPARepository.save(it)

  companion object {
    private const val MESSAGE:String = "schedule with id %s not found";
    private const val MESSAGE_OFFICE_SCHEDULE:String = "office schedule with id %s and schedule id %s not found";
  }

  override fun findByOfficeIdAndScheduleIdAndScheduleState(id: Long, scheduleId: Long, state: ScheduleState): OfficeSchedule =
    officeScheduleJPARepository
      .findByOfficeIdAndSchedule_IdAndSchedule_State(id, scheduleId, state)
      .orElseThrow { EntityNotFoundException(
        SPError.RESOURCE_NOT_FOUND.errorCode,
        MESSAGE_OFFICE_SCHEDULE.format(id, scheduleId)) }

  override fun update(it: OfficeSchedule): OfficeSchedule =
    officeScheduleJPARepository.save(it)

  override fun create(it: OfficeSchedule): OfficeSchedule =
    officeScheduleJPARepository.save(it)

}
