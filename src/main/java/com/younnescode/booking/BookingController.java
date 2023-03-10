package com.younnescode.booking;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/bookings")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public List<Booking> getBookings() {
        return bookingService.getAll();
    }

    @GetMapping("{id}")
    public Booking getBooking(@PathVariable("id") Integer id) {
        return bookingService.getById(id);
    }

    @PostMapping
    public void addBooking(@RequestBody BookingRegistrationRequest request) {
        bookingService.add(request);
    }
}
