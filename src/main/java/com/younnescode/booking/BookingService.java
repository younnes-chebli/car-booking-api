package com.younnescode.booking;

import com.younnescode.car.Car;
import com.younnescode.customer.Customer;
import com.younnescode.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
    private final BookingDAO bookingDAO;

    public BookingService(@Qualifier("booking-jpa") BookingDAO bookingDAO) {
        this.bookingDAO = bookingDAO;
    }

    public Booking getById(Integer id) {
        return bookingDAO.getById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Booking with id [%s] not found".formatted(id))
                );
    }

    public List<Booking> getAll() {
        return bookingDAO.getAll();
    }

    public void add(Customer customer, Car car) {
        bookingDAO.add(new Booking(customer, car));
    }
}
