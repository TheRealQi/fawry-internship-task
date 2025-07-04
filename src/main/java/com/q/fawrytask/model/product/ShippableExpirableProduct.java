package com.q.fawrytask.model.product;

import com.q.fawrytask.model.interfaces.Expirable;
import com.q.fawrytask.model.interfaces.Shippable;

import java.time.LocalDate;

public class ShippableExpirableProduct extends Product implements Shippable, Expirable {

    private double weight;
    private LocalDate expirationDate;

    public ShippableExpirableProduct(String name, double price, int quantity, double weight, LocalDate expirationDate) {
        super(name, price, quantity);
        this.weight = weight;
        this.expirationDate = expirationDate;
    }

    @Override
    public boolean isExpired() {
        return expirationDate.isBefore(LocalDate.now());
    }

    @Override
    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}
