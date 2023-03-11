package com.younnescode.booking;

import com.younnescode.car.CarDAO;
import com.younnescode.customer.CustomerDAO;
import com.younnescode.exception.AlreadyBookedException;
import com.younnescode.exception.NotValidResourceException;
import com.younnescode.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
    private final BookingDAO bookingDAO;
    private final CarDAO carDAO;

    public BookingService(
            @Qualifier("booking-jpa") BookingDAO bookingDAO,
            @Qualifier("customer-jpa") CustomerDAO customerDAO,
            @Qualifier("car-jpa") CarDAO carDAO
    ) {
        this.bookingDAO = bookingDAO;
        this.carDAO = carDAO;
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

    public void add(BookingRegistrationRequest bookingRegistrationRequest) {
        if(bookingRegistrationRequest.customer() == null || bookingRegistrationRequest.car() == null) {
            throw new NotValidResourceException("Missing data");
        }

        var customer = bookingRegistrationRequest.customer();
        var car = bookingRegistrationRequest.car();
        if(car.isBooked()) {
            throw new AlreadyBookedException("Car already booked");
        }

        car.setBooked(true);
        carDAO.updateCar(car);

        Booking booking = new Booking(customer, car);
        bookingDAO.add(booking);
    }
}
