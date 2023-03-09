package com.younnescode.booking;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("api/v1/bookings")
    public List<Booking> getBookings() {
        return bookingService.getAll();
    }

    @GetMapping("api/v1/booking/{id}")
    public Booking getBooking(@PathVariable("id") Integer id) {
        return bookingService.getById(id);
    }
}
