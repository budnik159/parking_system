package com.parking;

public class User {
    private final String name;
    private final String carPlateNumber;
    private final String phone;

    public User(String name, String carPlateNumber, String phone) {
        this.name = name;
        this.carPlateNumber = carPlateNumber;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getCarPlateNumber() {
        return carPlateNumber;
    }

    public String getPhone() {
        return phone;
    }
}
