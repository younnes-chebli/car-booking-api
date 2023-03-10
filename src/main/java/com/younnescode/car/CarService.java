package com.younnescode.car;

import com.younnescode.exception.DuplicateResourceException;
import com.younnescode.exception.NotValidResourceException;
import com.younnescode.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {
    private final CarDAO carDAO;

    public CarService(@Qualifier("car-jpa") CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    public List<Car> getAll() {
        return carDAO.getAll();
    }

    public Car getById(Integer id) {
        return carDAO.getById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Car with id [%s] not found".formatted(id))
                );
    }

    public Car getByRegNumber(Integer regNumber) {
        return carDAO.getByRegNumber(regNumber)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Car with Reg Number [%s] not found".formatted(regNumber))
                );
    }

    public void add(CarRegistrationRequest carRegistrationRequest){
        if(carDAO.existsWithRegNumber(carRegistrationRequest.regNumber())) {
            throw new DuplicateResourceException("Reg Number already taken");
        }

        try {
            Brand.valueOf(carRegistrationRequest.brand());
        } catch (IllegalArgumentException e) {
            throw new NotValidResourceException("Not Valid Brand");
        }

        Car car = new Car(
                carRegistrationRequest.regNumber(),
                new BigDecimal(carRegistrationRequest.rentalPricePerDay()).setScale(2, BigDecimal.ROUND_HALF_EVEN),
                Brand.valueOf(carRegistrationRequest.brand()),
                carRegistrationRequest.isElectric()
        );

        carDAO.addCar(car);
    }

    public List<Car> getAvailableCars() {
        return carDAO.getAvailableCars();
    }

    public List<Car> getAvailableElectricCars() {
        return carDAO.getAvailableElectricCars();
    }
}
