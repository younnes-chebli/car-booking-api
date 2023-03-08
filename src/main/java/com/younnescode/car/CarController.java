package com.younnescode.car;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CarController {
    private final CarDAO carDataAccessService  = new CarDataAccessService();
    private final CarService carService = new CarService(carDataAccessService);

    @GetMapping("/api/v1/cars")
    public List<Car> getCars() {
        return carService.getAll();
    }

    @GetMapping("/api/v1/car/{id}")
    public Car getCar(@PathVariable("id") Integer id) {
        return carService.getById(id);
    }

    @GetMapping("/api/v1/car/{regNumber}")
    public Car getCarByRegNumber(@PathVariable("regNumber") Integer regNumber) {
        return carService.getByRegNumber(regNumber);
    }
}
