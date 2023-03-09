package com.younnescode.car;

import com.younnescode.exception.ResourceNotFound;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {
    private final CarDAO carDataAccessService;

    public CarService(CarDAO carDataAccessService) {
        this.carDataAccessService = carDataAccessService;
    }

    public List<Car> getAll() {
        return carDataAccessService.getAll();
    }

    public Car getById(Integer id) {
        return carDataAccessService.getById(id)
                .orElseThrow(() ->
                        new ResourceNotFound("Car with id [%s] not found".formatted(id))
                );
    }

    public Car getByRegNumber(Integer regNumber) {
        return carDataAccessService.getByRegNumber(regNumber)
                .orElseThrow(() ->
                        new ResourceNotFound("Car with Reg Number [%s] not found".formatted(regNumber))
                );
    }

    public List<Car> getAvailableCars() {
        return carDataAccessService.getAll().stream()
                .filter(c -> !c.isBooked())
                .collect(Collectors.toList());
    }

    public List<Car> getAvailableElectricCars() {
        return carDataAccessService.getAll().stream()
                .filter(c -> !c.isBooked() && c.isElectric())
                .collect(Collectors.toList());
    }
}
