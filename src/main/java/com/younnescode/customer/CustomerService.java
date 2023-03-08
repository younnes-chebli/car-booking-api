package com.younnescode.customer;

import java.util.List;

public class CustomerService {
    private final CustomerDAO customerDataAccessService;

    public CustomerService(CustomerDAO customerDataAccessService) {
        this.customerDataAccessService = customerDataAccessService;
    }

    public List<Customer> getAll() {
        return customerDataAccessService.getAll();
    }

    public Customer getById(Integer id) {
        return customerDataAccessService.getById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Customer with id [%s] not found".formatted(id))
                );
    }
}
