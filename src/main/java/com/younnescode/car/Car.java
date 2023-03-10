package com.younnescode.car;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(
        name = "car",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "car_reg_number_unique",
                        columnNames = "reg_number"
                )
        }
)
public class Car {
    @Id
    @SequenceGenerator(
            name = "car_id_sequence",
            sequenceName = "car_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "car_id_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Integer id;

    @Column(
            name = "reg_number",
            nullable = false
    )
    private Integer regNumber;

    @Column(
            name = "rental_price_per_day",
            nullable = false
    )
    private BigDecimal rentalPricePerDay;

    @Column(
            name = "brand",
            nullable = false
    )
    private Brand brand;

    @Column(
            name = "is_electric",
            nullable = false
    )
    private boolean isElectric;

    @Column(
            name = "is_booked",
            nullable = false
    )
    private boolean isBooked;

    public Car() {
    }

    public Car(Integer regNumber, BigDecimal rentalPricePerDay, Brand brand, boolean isElectric) {
        this.regNumber = regNumber;
        this.rentalPricePerDay = rentalPricePerDay;
        this.brand = brand;
        this.isElectric = isElectric;
        this.isBooked = false;
    }

    public Car(Integer id, Integer regNumber, BigDecimal rentalPricePerDay, Brand brand, boolean isElectric, boolean isBooked) {
        this.id = id;
        this.regNumber = regNumber;
        this.rentalPricePerDay = rentalPricePerDay;
        this.brand = brand;
        this.isElectric = isElectric;
        this.isBooked = isBooked;
    }

    public Integer getId() {
        return id;
    }

    public Integer getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(Integer regNumber) {
        this.regNumber = regNumber;
    }

    public BigDecimal getRentalPricePerDay() {
        return rentalPricePerDay;
    }

    public void setRentalPricePerDay(BigDecimal rentalPricePerDay) {
        this.rentalPricePerDay = rentalPricePerDay;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public boolean isElectric() {
        return isElectric;
    }

    public void setElectric(boolean electric) {
        isElectric = electric;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return isElectric == car.isElectric && isBooked == car.isBooked && Objects.equals(regNumber, car.regNumber) && Objects.equals(rentalPricePerDay, car.rentalPricePerDay) && brand == car.brand;
    }

    @Override
    public int hashCode() {
        return Objects.hash(regNumber, rentalPricePerDay, brand, isElectric, isBooked);
    }

    @Override
    public String toString() {
        return "Car{" +
                "regNumber=" + regNumber +
                ", rentalPricePerDay=" + rentalPricePerDay +
                ", brand=" + brand +
                ", isElectric=" + isElectric +
                ", isBooked=" + isBooked +
                '}';
    }
}
