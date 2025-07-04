package com.q.fawrytask.service;

import com.q.fawrytask.model.Customer;
import com.q.fawrytask.model.cart.Cart;
import com.q.fawrytask.model.cart.CartItem;
import com.q.fawrytask.model.interfaces.Expirable;
import com.q.fawrytask.model.interfaces.Shippable;
import com.q.fawrytask.model.product.Product;

import java.util.ArrayList;
import java.util.List;

import static com.q.fawrytask.service.ShippingService.processShippableItems;

public class CheckoutService {
    public static void checkout(Customer customer, Cart cart) {
        if (cart.getItems().isEmpty()) {
            System.out.println("Cart is empty, please add items before checking out.");
            return;
        }
        if (!validCart(cart)) {
            return;
        }
        double shippingCost = 0.0;
        List<CartItem> shippableItems = extractShippableItems(cart);
        double subtotal = calculateSubtotal(cart);
        shippingCost = ShippingService.calculateShippingCost(shippableItems);
        if (!PaymentService.sufficientBalance(customer, subtotal + shippingCost)) {
            System.out.println("Insufficient balance.");
            return;
        }
        processShippableItems(shippableItems);
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            int quantity = item.getQuantity();
            InventoryService.reduceStockQuantity(product, quantity);
        }
        PaymentService.processPayment(customer, subtotal + shippingCost);
        printReceipt(cart, subtotal, shippingCost);
        cart.clear();
    }

    private static double calculateSubtotal(Cart cart) {
        double subtotal = 0.0;
        for (CartItem item : cart.getItems()) {
            subtotal += item.getTotalPrice();
        }
        return subtotal;
    }

    private static void printReceipt(Cart cart, double subtotal, double shippingCost) {
        System.out.println("** Checkout receipt **");
        for (CartItem item : cart.getItems()) {
            System.out.println(item.getQuantity() + "x " + item.getProduct().getName() + "\t" + item.getTotalPrice());
        }
        System.out.println("----------------------");
        System.out.println("Subtotal " + subtotal);
        System.out.println("Shipping " + shippingCost);
        System.out.println("Amount " + (subtotal + shippingCost));
    }

    private static boolean validCart(Cart cart) {
        for (CartItem item : cart.getItems()) {
            Product product = item.getProduct();
            int quantity = item.getQuantity();
            if (!InventoryService.hasStockQuantity(product, quantity)) {
                System.out.println("Product \"" + product.getName() + "\" is out of stock.");
                return false;
            }
            if (product instanceof Expirable) {
                if (((Expirable) product).isExpired()) {
                    System.out.println("Product \"" + product.getName() + "\" is expired.");
                    return false;
                }
            }
        }
        return true;
    }

    private static List<CartItem> extractShippableItems(Cart cart) {
        List<CartItem> shippableItems = new ArrayList<>();
        for (CartItem item : cart.getItems()) {
            if (item.getProduct() instanceof Shippable) {
                shippableItems.add(item);
            }
        }
        return shippableItems;
    }
}
