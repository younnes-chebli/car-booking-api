package com.younnescode.customer;

public record CustomerRegistrationRequest(
        String firstname, String lastname, String email
) {
}
