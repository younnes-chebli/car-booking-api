package com.younnescode.car;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("car-jpa")
public class CarJPADataAccessService implements CarDAO {
    private final CarRepository carRepository;
    public CarJPADataAccessService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }
    @Override
    public List<Car> getAll() {
        return carRepository.findAll();
    }

    @Override
    public Optional<Car> getById(Integer id) {
        return carRepository.findById(id);
    }

    @Override
    public Optional<Car> getByRegNumber(Integer regNumber) {
        return carRepository.findByRegNumber(regNumber);
    }

    @Override
    public void addCar(Car car) {
        carRepository.save(car);
    }

    @Override
    public boolean existsWithRegNumber(Integer regNumber) {
        return carRepository.existsCarByRegNumber(regNumber);
    }

    @Override
    public void saveCar(Car car) {
        carRepository.save(car);
    }
}
