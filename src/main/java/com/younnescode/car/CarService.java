package com.younnescode.car;

import com.younnescode.exception.ResourceNotFound;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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
                        new ResourceNotFound("Car with id [%s] not found".formatted(id))
                );
    }

    public Car getByRegNumber(Integer regNumber) {
        return carDAO.getByRegNumber(regNumber)
                .orElseThrow(() ->
                        new ResourceNotFound("Car with Reg Number [%s] not found".formatted(regNumber))
                );
    }

    public List<Car> getAvailableCars() {
        return carDAO.getAll().stream()
                .filter(c -> !c.isBooked())
                .collect(Collectors.toList());
    }

    public List<Car> getAvailableElectricCars() {
        return carDAO.getAll().stream()
                .filter(c -> !c.isBooked() && c.isElectric())
                .collect(Collectors.toList());
    }
}
