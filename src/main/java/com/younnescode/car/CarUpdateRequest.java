package com.younnescode.car;

public record CarUpdateRequest(
        Integer regNumber, Double rentalPricePerDay, String brand, boolean isElectric, boolean isBooked
) {
}
