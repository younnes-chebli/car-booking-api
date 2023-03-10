package com.younnescode;

import com.github.javafaker.Faker;
import com.younnescode.car.Brand;
import com.younnescode.car.Car;
import com.younnescode.car.CarRepository;
import com.younnescode.customer.Customer;
import com.younnescode.customer.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Bean
	CommandLineRunner seedDataRunner(CustomerRepository customerRepository, CarRepository carRepository) {
		return args -> {
			List<Customer> customers = new ArrayList<>();
			List<Car> cars = new ArrayList<>();

			for(int i = 0; i < 20; i++) {
				Faker faker = new Faker();
				customers.add(new Customer(faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress()));
			}

			cars.add(new Car(1001, new BigDecimal(89).setScale(2, BigDecimal.ROUND_HALF_EVEN), Brand.TESLA, true));
			cars.add(new Car(1002, new BigDecimal(50).setScale(2, BigDecimal.ROUND_HALF_EVEN), Brand.AUDI, false));
			cars.add(new Car(1003, new BigDecimal(77).setScale(2, BigDecimal.ROUND_HALF_EVEN), Brand.MERCEDES, false));

			customerRepository.saveAll(customers);
			carRepository.saveAll(cars);
		};
	}
}
