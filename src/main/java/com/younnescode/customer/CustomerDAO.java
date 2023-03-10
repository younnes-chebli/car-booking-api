package com.younnescode.customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDAO {
    List<Customer> getAllCustomers();

    void addCustomer(Customer user);

    Optional<Customer> getCustomerById(Integer id);

    boolean existsCustomerWithEmail(String email);

    void deleteCustomer(Customer customer);

    void updateCustomer(Customer customer);
}
