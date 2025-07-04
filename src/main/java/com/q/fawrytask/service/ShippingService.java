package com.q.fawrytask.service;

import com.q.fawrytask.model.cart.CartItem;
import com.q.fawrytask.model.interfaces.Shippable;

import java.util.List;

public class ShippingService {
    protected static void processShippableItems(List<CartItem> shippableItems) {
        System.out.println("** Shipment Notice **");
        double totalWeight = 0.0;
        if (shippableItems.isEmpty()) {
            System.out.println("No items to ship.");
            return;
        }
        for (CartItem item : shippableItems) {
            printShippableItem(item);
            totalWeight += ((Shippable) item.getProduct()).getWeight() * item.getQuantity();
        }
        System.out.println("Total package weight " + gramToKg(totalWeight) + "kg");
    }

    private static double gramToKg(double weight) {
        return weight / 1000.0;
    }

    private static void printShippableItem(CartItem shippableItem) {
        String cartItemName = shippableItem.getProduct().getName();
        double cartItemWeight = ((Shippable) shippableItem.getProduct()).getWeight();
        System.out.println(shippableItem.getQuantity() + "x " + cartItemName + "\t" + cartItemWeight + "g");
    }

    protected static double calculateShippingCost(List<CartItem> shippableItems) {
        if (shippableItems.isEmpty()) {
            return 0.0;
        }
        double totalWeight = 0.0;
        for (CartItem item : shippableItems) {
            totalWeight += ((Shippable) item.getProduct()).getWeight() * item.getQuantity();
        }
        return totalWeight * 0.03;
    }
}
