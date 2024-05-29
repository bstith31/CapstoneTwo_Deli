package com.ps;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private List<Product> products;
    private double totalPrice;
    private LocalDateTime scheduledDateTime;

    public Order(List<Product> products, double totalPrice, LocalDateTime scheduledDateTime) {
        this.products = products;
        this.totalPrice = totalPrice;
        this.scheduledDateTime = scheduledDateTime;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getScheduledDateTime() {
        return scheduledDateTime;
    }

    public void setScheduledDateTime(LocalDateTime scheduledDateTime) {
        this.scheduledDateTime = scheduledDateTime;
    }
}