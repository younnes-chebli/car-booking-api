package com.younnescode.customer;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("customer-jpa")
public class CustomerJPADataAccessService implements CustomerDAO {
    private final CustomerRepository customerRepository;

    public CustomerJPADataAccessService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public void addCustomer(Customer user) {
        customerRepository.save(user);
    }

    @Override
    public Optional<Customer> getCustomerById(Integer id) {
        return customerRepository.findById(id);
    }

    @Override
    public boolean existsCustomerWithEmail(String email) {
        return customerRepository.existsByEmail(email);
    }

    @Override
    public void deleteCustomer(Customer customer) {
        customerRepository.delete(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerRepository.save(customer);
    }
}
