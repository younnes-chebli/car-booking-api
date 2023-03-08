package com.younnescode.booking;

import com.younnescode.car.Car;
import com.younnescode.customer.Customer;

import java.time.LocalDateTime;
import java.util.Objects;

public class Booking {
    private Integer id;
    private Customer customer;
    private Car car;
    private LocalDateTime bookingTime;
    private boolean isCanceled;

    public Booking(Integer id, Customer customer, Car car, LocalDateTime bookingTime, boolean isCanceled) {
        this.id = id;
        this.customer = customer;
        this.car = car;
        this.bookingTime = bookingTime;
        this.isCanceled = isCanceled;
    }

    public Booking(Customer customer, Car car) {
//        this.id = null;
        this.customer = customer;
        this.car = car;
        this.bookingTime = LocalDateTime.now();
        this.isCanceled = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public void setCanceled(boolean canceled) {
        isCanceled = canceled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return isCanceled == booking.isCanceled && Objects.equals(id, booking.id) && Objects.equals(customer, booking.customer) && Objects.equals(car, booking.car) && Objects.equals(bookingTime, booking.bookingTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, car, bookingTime, isCanceled);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", customer=" + customer +
                ", car=" + car +
                ", bookingTime=" + bookingTime +
                ", isCanceled=" + isCanceled +
                '}';
    }
}
