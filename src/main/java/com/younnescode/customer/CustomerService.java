package com.younnescode.customer;

import com.younnescode.exception.DuplicateResourceException;
import com.younnescode.exception.NotValidResourceException;
import com.younnescode.exception.ResourceNotFoundException;
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
        if(customerRegistrationRequest.firstname() == null || customerRegistrationRequest.lastname() == null || customerRegistrationRequest.email() == null) {
            throw new NotValidResourceException("Missing data");
        }

        var firstname = customerRegistrationRequest.firstname();
        var lastname =  customerRegistrationRequest.lastname();
        var email = customerRegistrationRequest.email();
        checkEmailExists(email);

        var customer = new Customer(
                firstname,
                lastname,
                email
        );
        customerDAO.addCustomer(customer);
    }

    public void deleteCustomer(Integer id) {
        var customer = customerDAO.getCustomerById(id)
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
        var customer = customerDAO.getCustomerById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Customer with id [%s] not found".formatted(id))
                );

        if(customerUpdateRequest.firstname() != null) {
            var firstname = customerUpdateRequest.firstname();
            customer.setFirsname(firstname);
        }

        if(customerUpdateRequest.lastname() != null) {
            var lastname = customerUpdateRequest.lastname();
            customer.setLastname(lastname);
        }

        if(customerUpdateRequest.email() != null == !customer.getEmail().equals(customerUpdateRequest.email())) {
            var email = customerUpdateRequest.email();
            checkEmailExists(email);
            if(!checkEmailValid(email, "^(.+)@(\\S+)$")) {
                throw new NotValidResourceException("Email not valid");
            }
            customer.setEmail(email);
        }

        customerDAO.updateCustomer(customer);
    }
}
