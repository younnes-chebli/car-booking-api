package com.younnescode.customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDAO {
    List<Customer> getAll();

    void add(Customer user);

    Optional<Customer> getById(Integer id);
}
