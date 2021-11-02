package com.redbee.offices.adapter.postgres

import com.redbee.offices.domain.Schedule
import org.springframework.data.repository.CrudRepository

interface ScheduleJPARepository:CrudRepository<Schedule, Long>
