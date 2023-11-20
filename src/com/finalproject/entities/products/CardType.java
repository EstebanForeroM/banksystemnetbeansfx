package com.finalproject.entities.products;

public enum CardType {
    VISA("Visa"),
    MASTERCARD("Mastercard");

    private String name;

    CardType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
