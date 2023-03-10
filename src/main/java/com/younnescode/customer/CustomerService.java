package com.younnescode.customer;

import com.younnescode.exception.ResourceNotFound;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerDAO customerDAO;

    public CustomerService(@Qualifier("customer-jpa") CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public List<Customer> getAll() {
        return customerDAO.getAll();
    }

    public Customer getById(Integer id) {
        return customerDAO.getById(id)
                .orElseThrow(() ->
                        new ResourceNotFound("Customer with id [%s] not found".formatted(id))
                );
    }
}
