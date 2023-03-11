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
        return carService.getAllCars();
    }

    @GetMapping("{id}")
    public Car getCar(@PathVariable("id") Integer id) {
        return carService.getCarById(id);
    }

    @GetMapping("rn/{regNumber}")
    public Car getCarByRegNumber(@PathVariable("regNumber") Integer regNumber) {
        return carService.getCarByRegNumber(regNumber);
    }

    @GetMapping("available")
    public List<Car> getAvailableCars() {
        return carService.getAvailableCars();
    }

    @GetMapping("available-electric")
    public List<Car> getAvailableElectricCars() {
        return carService.getAvailableElectricCars();
    }

    @PostMapping
    public void addCar(@RequestBody CarRegistrationRequest request) {
        carService.addCar(request);
    }

    @DeleteMapping("{id}")
    public void deleteCar(@PathVariable("id") Integer id) {
        carService.deleteCar(id);
    }

    @PutMapping("{id}")
    public void updateCar(
            @PathVariable("id") Integer id,
            @RequestBody CarUpdateRequest request
            ) {
        carService.updateCar(id, request);
    }
}
