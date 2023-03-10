package com.younnescode.customer;

public record CustomerUpdateRequest(
        String firstname, String lastname, String email
) {
}
