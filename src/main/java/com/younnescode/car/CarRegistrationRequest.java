package com.younnescode.car;

public record CarRegistrationRequest (
        Integer regNumber, Double rentalPricePerDay, String brand, boolean isElectric
){
}
