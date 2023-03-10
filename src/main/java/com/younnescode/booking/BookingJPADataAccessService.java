package com.younnescode.booking;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("booking-jpa")
public class BookingJPADataAccessService implements BookingDAO {
    private final BookingRepository bookingRepository;

    public BookingJPADataAccessService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public List<Booking> getAll() {
        return bookingRepository.findAll();
    }

    @Override
    public void add(Booking booking) {

    }

    @Override
    public Optional<Booking> getById(Integer id) {
        return Optional.empty();
    }
}
