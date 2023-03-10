package com.younnescode.car;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("car-list")
public class CarListDataAccessService implements CarDAO {
    private static final List<Car> cars;

    static {
        cars = new ArrayList<>();
        cars.add(new Car(1, 1001, new BigDecimal(89).setScale(2, BigDecimal.ROUND_HALF_EVEN), Brand.TESLA, true, false));
        cars.add(new Car(2, 1002, new BigDecimal(50).setScale(2, BigDecimal.ROUND_HALF_EVEN), Brand.AUDI, false, false));
        cars.add(new Car(3, 1003, new BigDecimal(77).setScale(2, BigDecimal.ROUND_HALF_EVEN), Brand.MERCEDES, false, false));
    }

    @Override
    public List<Car> getAll() {
        return cars;
    }

    @Override
    public Optional<Car> getById(Integer id) {
        return cars.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();

    }

    @Override
    public Optional<Car> getByRegNumber(Integer regNumber) {
        return cars.stream()
                .filter(c -> c.getRegNumber().equals(regNumber))
                .findFirst();
    }

    @Override
    public void addCar(Car car) {
        cars.add(car);
    }

    @Override
    public boolean existsWithRegNumber(Integer regNumber) {
        return cars.stream()
                .anyMatch(c -> c.getRegNumber().equals(regNumber));
    }
}
