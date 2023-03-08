package com.younnescode.booking;

import java.util.List;
import java.util.Optional;

public interface BookingDAO {
    List<Booking> getAll();

    void add(Booking booking);

    Optional<Booking> getById(Integer id);
}
