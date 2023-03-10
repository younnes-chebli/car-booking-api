package com.younnescode.car;

import java.util.List;
import java.util.Optional;

public interface CarDAO {
    List<Car> getAllCars();

    Optional<Car> getCarById(Integer id);

    Optional<Car> getCarByRegNumber(Integer regNumber);

    void addCar(Car car);

    boolean existsCarWithRegNumber(Integer regNumber);

    void saveCar(Car car);

    List<Car> getAvailableCars();

    List<Car> getAvailableElectricCars();
    void deleteCar(Car car);
}
