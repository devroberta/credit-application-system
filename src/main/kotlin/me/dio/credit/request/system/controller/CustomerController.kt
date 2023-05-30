package me.dio.credit.request.system.controller

import me.dio.credit.request.system.dto.CustomerDto
import me.dio.credit.request.system.dto.CustomerUpdateDto
import me.dio.credit.request.system.dto.CustomerView
import me.dio.credit.request.system.entity.Customer
import me.dio.credit.request.system.service.impl.CustomerService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/aip/customers")
class CustomerController(
    private val customerService: CustomerService
) {

    @PostMapping
    fun saveCustomer(@RequestBody customerDto: CustomerDto): String {
        val savedCustomer = this.customerService.save(customerDto.toEntity())
        return "Customer ${savedCustomer.email} saved!"
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): CustomerView {
        val customer: Customer = this.customerService.findById(id)
        return CustomerView(customer)
    }

    @DeleteMapping("/{id}")
    fun deleteCustomer(@PathVariable id: Long) =
        this.customerService.delete(id)

    @PatchMapping
    fun updateCustomer(@RequestParam id: Long,
                       @RequestBody customerUpdateDto: CustomerUpdateDto): CustomerView {
        val customer: Customer = this.customerService.findById(id)
        val customertoUpdate = customerUpdateDto.toEntity(customer)
        val customerUpdated = this.customerService.save(customertoUpdate)
        return CustomerView(customerUpdated)
    }
}