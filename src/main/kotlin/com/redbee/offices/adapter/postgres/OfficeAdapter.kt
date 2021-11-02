package com.redbee.offices.adapter.postgres

import com.redbee.offices.adapter.postgres.exceptions.EntityNotFoundException
import com.redbee.offices.application.port.out.OfficeRepository
import com.redbee.offices.config.SPError
import com.redbee.offices.domain.Office
import org.springframework.stereotype.Repository

@Repository
class OfficeAdapter(
  private val officeJPARepository: OfficeJPARepository
): OfficeRepository {

  override fun create(office: Office): Office =
    officeJPARepository.save(office)

  override fun findAll():List<Office> =
    officeJPARepository.findAll().toList()

  override fun findById(id: Long): Office =
    officeJPARepository
      .findById(id)
      .orElseThrow { EntityNotFoundException(SPError.RESOURCE_NOT_FOUND.errorCode, MESSAGE.format(id)) }

  companion object {
    private const val MESSAGE:String = "office with id %s not found";
  }

}
