package com.younnescode.car;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository("car-jpa")
public class CarJPADataAccessService implements CarDAO {
    private final CarRepository carRepository;
    public CarJPADataAccessService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }
    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Optional<Car> getCarById(Integer id) {
        return carRepository.findById(id);
    }

    @Override
    public Optional<Car> getCarByRegNumber(Integer regNumber) {
        return carRepository.findByRegNumber(regNumber);
    }

    @Override
    public void addCar(Car car) {
        carRepository.save(car);
    }

    @Override
    public boolean existsCarWithRegNumber(Integer regNumber) {
        return carRepository.existsByRegNumber(regNumber);
    }

    @Override
    public void updateCar(Car car) {
        carRepository.save(car);
    }

    @Override
    public List<Car> getAvailableCars() {
        //try with jpa;

        return carRepository.findAll().stream()
                .filter(c -> !c.isBooked())
                .collect(Collectors.toList());
    }

    @Override
    public List<Car> getAvailableElectricCars() {
        //try with jpa;

        return carRepository.findAll().stream()
                .filter(c -> !c.isBooked() && c.isElectric())
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCar(Car car) {
        carRepository.delete(car);
    }
}
