package com.younnescode.booking;

import com.younnescode.car.Car;
import com.younnescode.customer.Customer;

public record BookingRegistrationRequest(
        Customer customer, Car car
) {
}
