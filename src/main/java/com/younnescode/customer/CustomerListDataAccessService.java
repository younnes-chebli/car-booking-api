package com.younnescode.customer;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("customer-list")
public class CustomerListDataAccessService implements CustomerDAO {
    private static final List<Customer> customers;

    static {
        customers = new ArrayList<>();
        for(int i = 0; i < 20; i++) {
            Faker faker = new Faker();
            customers.add(new Customer(i, faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress()));
        }
    }

    @Override
    public List<Customer> getAll() {
        return customers;
    }

    @Override
    public void add(Customer customer) {
        customers.add(customer);
    }

    @Override
    public Optional<Customer> getById(Integer id) {
        return customers.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }
}
