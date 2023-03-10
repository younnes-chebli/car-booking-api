package com.younnescode.customer;

import com.younnescode.exception.DuplicateResourceException;
import com.younnescode.exception.NotValidResourceException;
import com.younnescode.exception.ResourceNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class CustomerService {
    private final CustomerDAO customerDAO;

    public CustomerService(@Qualifier("customer-jpa") CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public List<Customer> getAllCustomers() {
        return customerDAO.getAllCustomers();
    }

    public Customer getCustomerById(Integer id) {
        return customerDAO.getCustomerById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Customer with id [%s] not found".formatted(id))
                );
    }

    private void checkEmailExists(String email) {
        if(customerDAO.existsCustomerWithEmail(email)) {
            throw new DuplicateResourceException("Email already taken");
        }
    }

    public void addCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        checkEmailExists(customerRegistrationRequest.email());

        Customer customer = new Customer(
                customerRegistrationRequest.firstname(),
                customerRegistrationRequest.lastname(),
                customerRegistrationRequest.email()
        );

        customerDAO.addCustomer(customer);
    }

    public void deleteCustomer(Integer id) {
        Customer customer = customerDAO.getCustomerById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Customer with id [%s] not found".formatted(id))
                );

        customerDAO.deleteCustomer(customer);
    }

    private boolean checkEmailValid(String email, String emailRegex) {
        return Pattern.compile(emailRegex)
                .matcher(email)
                .matches();
    }

    public void updateCustomer(Integer id, CustomerUpdateRequest customerUpdateRequest) {
        Customer customer = customerDAO.getCustomerById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Customer with id [%s] not found".formatted(id))
                );

        String firstname = customerUpdateRequest.firstname();
        String lasname = customerUpdateRequest.lastname();
        String email = customerUpdateRequest.email();

        if(firstname != null) {
            customer.setFirsname(firstname);
        }

        if(lasname != null) {
            customer.setLastname(lasname);
        }

        if(email != null == !customer.getEmail().equals(email)) {
            checkEmailExists(email);

            if(!checkEmailValid(email, "^(.+)@(\\S+)$")) {
                throw new NotValidResourceException("Email not valid");
            }

            customer.setEmail(email);
        }

        customerDAO.updateCustomer(customer);
    }
}
