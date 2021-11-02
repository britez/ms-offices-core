package com.redbee.offices.application.port.out

import com.redbee.offices.domain.Office

interface OfficeRepository {
  fun create(office: Office): Office
  fun findAll(): List<Office>
  fun findById(id: Long): Office

  companion object {

  }
}
