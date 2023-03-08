package com.younnescode.car;

import java.util.List;
import java.util.Optional;

public interface CarDAO {
    List<Car> getAll();

    Optional<Car> getById(Integer id);

    Optional<Car> getByRegNumber(Integer regNumber);
}