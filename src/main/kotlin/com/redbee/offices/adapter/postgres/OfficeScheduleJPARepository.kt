package com.redbee.offices.adapter.postgres

import com.redbee.offices.domain.OfficeSchedule
import com.redbee.offices.domain.ScheduleState
import org.springframework.data.repository.CrudRepository
import java.util.*

interface OfficeScheduleJPARepository:CrudRepository<OfficeSchedule, Long> {

    fun findByOfficeIdAndSchedule_IdAndSchedule_State(
        officeId:Long,
        scheduleId:Long,
        scheduleState: ScheduleState
    ): Optional<OfficeSchedule>
}


