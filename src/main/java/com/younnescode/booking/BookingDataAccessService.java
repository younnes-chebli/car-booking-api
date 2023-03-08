package com.younnescode.booking;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookingDataAccessService implements BookingDAO {
    private static final List<Booking> bookings;

    static {
        bookings = new ArrayList<>();
    }

    private final BookingDAO bookingDataAccessService = new BookingDataAccessService();

    @Override
    public List<Booking> getAll() {
        return bookings;
    }

    @Override
    public void add(Booking booking) {
        bookings.add(booking);
    }

    @Override
    public Optional<Booking> getById(Integer id) {
        return bookings.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst();
    }
}
