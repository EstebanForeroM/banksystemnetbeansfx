package com.finalproject.entities;

import java.util.Date;

public abstract class Product implements Identifiable {

    private String id;

    private String ownerId;

    protected double balance;

    private Date openingDate;

    public Product(String id, String ownerId, Date openingDate) {
        comproveOnlyNubers(id);
        comproveOnlyNubers(ownerId);

        if (openingDate == null)
            throw new NullPointerException("Opening date can't be null");

        if (id.isEmpty())
            throw new IllegalArgumentException("Product ID can't be empty");

        if (ownerId.isEmpty())
            throw new IllegalArgumentException("Owner ID can't be empty");

        this.openingDate = openingDate;
        this.ownerId = ownerId;
        this.id = id;
        balance = 0;
    }

    public abstract String getProductName();

    public double setBalance(double balance) {
        return this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public String getId() {
        return id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    private void comproveOnlyNubers(String id) {
        for (int i = 0; i < id.length(); i++) {
            if (!Character.isDigit(id.charAt(i))) {
                throw new IllegalArgumentException("Product ID must contain only numbers");
            }
        }
    }

    public Date getOpeningDate() {
        if (openingDate == null) {
            throw new NullPointerException("Product not initialized");
        }

        return openingDate;
    }

    public void setOpeningDate(Date openingDate) {
        if (openingDate == null)
            throw new NullPointerException("Opening date can't be null");

        this.openingDate = openingDate;

    }

    public Object getProductId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getClientId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void deleteClient() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
