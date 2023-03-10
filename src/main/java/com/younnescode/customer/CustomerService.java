package com.younnescode.customer;

import com.younnescode.exception.DuplicateResourceException;
import com.younnescode.exception.ResourceNotFoundException;
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
                        new ResourceNotFoundException("Customer with id [%s] not found".formatted(id))
                );
    }

    public void add(CustomerRegistrationRequest customerRegistrationRequest) {
        if(customerDAO.existsWithEmail(customerRegistrationRequest.email())) {
            throw new DuplicateResourceException("Email already taken");
        }

        Customer customer = new Customer(
                customerRegistrationRequest.firstname(),
                customerRegistrationRequest.lastname(),
                customerRegistrationRequest.email()
        );

        customerDAO.add(customer);
    }
}
