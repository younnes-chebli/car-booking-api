package com.younnescode.booking;

import com.younnescode.car.Car;
import com.younnescode.customer.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
    private final BookingDAO bookingDataAccessService;

    public BookingService(BookingDAO bookingDataAccessService) {
        this.bookingDataAccessService = bookingDataAccessService;
    }

    public Booking getById(Integer id) {
        return bookingDataAccessService.getById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Booking with id [%s] not found".formatted(id))
                );
    }

    public List<Booking> getAll() {
        return bookingDataAccessService.getAll();
    }

    public void add(Customer customer, Car car) {
        bookingDataAccessService.add(new Booking(customer, car));
    }
}
