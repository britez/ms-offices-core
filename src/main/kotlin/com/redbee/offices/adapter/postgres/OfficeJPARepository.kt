package com.redbee.offices.adapter.postgres

import com.redbee.offices.domain.Office
import org.springframework.data.repository.CrudRepository

interface OfficeJPARepository:CrudRepository<Office, Long>
