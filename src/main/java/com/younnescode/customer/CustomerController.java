package com.younnescode.customer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    private final CustomerDAO customerDataAccessService = new CustomerDataAccessService();
    private final CustomerService customerService = new CustomerService(customerDataAccessService);

    @GetMapping("/api/v1/customers")
    public List<Customer> getCustomers() {
        return customerService.getAll();
    }

    @GetMapping("/api/v1/customer/{id}")
    public Customer getCustomer(@PathVariable("id") Integer id) {
        return customerService.getById(id);
    }
}
