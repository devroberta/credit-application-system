package me.dio.credit.request.system.repository

import me.dio.credit.request.system.entity.Credit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface CreditRepository: JpaRepository<Credit, Long> {

    fun findByCreditCode(creditCode: UUID): Credit?

    @Query(value= "SELECT * FROM credit WHERE credit_id = ?1", nativeQuery = true)
    fun findAllByCustomerId(customerId: Long): List<Credit>
}