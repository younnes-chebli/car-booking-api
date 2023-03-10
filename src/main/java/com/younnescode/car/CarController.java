package com.younnescode.car;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cars")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<Car> getCars() {
        return carService.getAll();
    }

    @GetMapping("{id}")
    public Car getCar(@PathVariable("id") Integer id) {
        return carService.getById(id);
    }

    @GetMapping("rn/{regNumber}")
    public Car getCarByRegNumber(@PathVariable("regNumber") Integer regNumber) {
        return carService.getByRegNumber(regNumber);
    }

    @PostMapping
    public void addCar(@RequestBody CarRegistrationRequest request) {
        carService.addCar(request);
    }
}
