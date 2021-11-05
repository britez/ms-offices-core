package com.redbee.offices.application.usecase

import com.redbee.offices.application.port.`in`.GetScheduleOfficePortIn
import com.redbee.offices.application.port.out.ScheduleOfficeRepository
import com.redbee.offices.domain.OfficeSchedule
import org.springframework.stereotype.Component

@Component
class GetScheduleOfficeUseCase(
    private val scheduleOfficeRepository: ScheduleOfficeRepository
): GetScheduleOfficePortIn {

    override fun execute(scheduleId: Long, officeId: Long): OfficeSchedule =
        scheduleOfficeRepository.findByOfficeIdAndScheduleId(officeId, scheduleId)

}