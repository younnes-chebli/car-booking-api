package com.younnescode.car;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Integer> {
    Optional<Car> findByRegNumber(Integer regNumber);

    boolean existsCarByRegNumber(Integer regNumber);
}
