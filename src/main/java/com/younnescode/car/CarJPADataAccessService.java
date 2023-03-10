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
        return Optional.empty();
    }

    @Override
    public Optional<Car> getByRegNumber(Integer regNumber) {
        return Optional.empty();
    }
}
