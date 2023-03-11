package com.younnescode.car;

import com.younnescode.exception.DuplicateResourceException;
import com.younnescode.exception.NotValidResourceException;
import com.younnescode.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CarService {
    private final CarDAO carDAO;

    public CarService(@Qualifier("car-jpa") CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    public List<Car> getAllCars() {
        return carDAO.getAllCars();
    }

    public Car getCarById(Integer id) {
        return carDAO.getCarById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Car with id [%s] not found".formatted(id))
                );
    }

    public Car getCarByRegNumber(Integer regNumber) {
        return carDAO.getCarByRegNumber(regNumber)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Car with Reg Number [%s] not found".formatted(regNumber))
                );
    }

    private void checkValidBrand(String brand) {
        try {
            Brand.valueOf(brand);
        } catch (IllegalArgumentException e) {
            throw new NotValidResourceException("Brand not Valid");
        }
    }

    private void checkRegNumberExists(Integer regNumber) {
        if(carDAO.existsCarWithRegNumber(regNumber)) {
            throw new DuplicateResourceException("Reg Number already taken");
        }
    }

    private void checkRentalPricePerDay(Double rentalPricePerDay) {
        if(rentalPricePerDay <= 0) {
            throw new NotValidResourceException("Price not valid");
        }
    }

    public void addCar(CarRegistrationRequest carRegistrationRequest){
        if(carRegistrationRequest.regNumber() == null || carRegistrationRequest.rentalPricePerDay() == null || carRegistrationRequest.brand() == null || Boolean.valueOf(carRegistrationRequest.isElectric()) == null) {
            throw new NotValidResourceException("Missing data");
        }

        checkRegNumberExists(carRegistrationRequest.regNumber());
        checkRentalPricePerDay(carRegistrationRequest.rentalPricePerDay());
        checkValidBrand(carRegistrationRequest.brand());

        var regNumber = carRegistrationRequest.regNumber();
        var rentalPricePerDay = new BigDecimal(carRegistrationRequest.rentalPricePerDay()).setScale(2, BigDecimal.ROUND_HALF_EVEN);
        var brand = Brand.valueOf(carRegistrationRequest.brand());
        var isElectric = carRegistrationRequest.isElectric();
        Car car = new Car(
                regNumber,
                rentalPricePerDay,
                brand,
                isElectric
        );
        carDAO.addCar(car);
    }

    public List<Car> getAvailableCars() {
        return carDAO.getAvailableCars();
    }

    public List<Car> getAvailableElectricCars() {
        return carDAO.getAvailableElectricCars();
    }

    public void deleteCar(Integer id) {
        Car car = carDAO.getCarById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Car with id [%s] not found".formatted(id))
                );

        carDAO.deleteCar(car);
    }

    public void updateCar(Integer id, CarUpdateRequest carUpdateRequest) {
        var car = carDAO.getCarById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Car with id [%s] not found".formatted(id))
                );

        if(carUpdateRequest.regNumber() != null && !car.getRegNumber().equals(carUpdateRequest.regNumber())) {
            var regNumber = carUpdateRequest.regNumber();
            if(regNumber <= 0) {
                throw new NotValidResourceException("Reg Number not valid");
            }
            checkRegNumberExists(regNumber);
            car.setRegNumber(regNumber);
        }

        if(carUpdateRequest.rentalPricePerDay() != null) {
            checkRentalPricePerDay(carUpdateRequest.rentalPricePerDay());
            var rentalPricePerDay = new BigDecimal(carUpdateRequest.rentalPricePerDay()).setScale(2, BigDecimal.ROUND_HALF_EVEN);
            car.setRentalPricePerDay(rentalPricePerDay);
        }

        if(carUpdateRequest.brand() != null) {
            var stringBrand = carUpdateRequest.brand();
            checkValidBrand(stringBrand);
            var brand = Brand.valueOf(carUpdateRequest.brand());
            car.setBrand(brand);
        }

        if(Boolean.valueOf(carUpdateRequest.isElectric()) != null) {
            var isElectric = carUpdateRequest.isElectric();
            car.setBooked(isElectric);
        }

        if(Boolean.valueOf(carUpdateRequest.isBooked()) != null) {
            var isBooked = carUpdateRequest.isBooked();
            car.setBooked(isBooked);
        }

        carDAO.updateCar(car);
    }
}
