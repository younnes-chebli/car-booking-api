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
    public List<Customer> getAllCustomers() {
        return customers;
    }

    @Override
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    @Override
    public Optional<Customer> getCustomerById(Integer id) {
        return customers.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }

    @Override
    public boolean existsCustomerWithEmail(String email) {
        return customers.stream()
                .anyMatch(c -> c.getEmail().equals(email));
    }

    @Override
    public void deleteCustomer(Customer customer) {
        customers.remove(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customers.add(customer);
    }
}
