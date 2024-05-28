package com.ps;

public abstract class Product {
    protected String name;
    protected double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public abstract void productSelection();

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("%s - Price: $%.2f", name, price);
    }
}
