package com.q.fawrytask;

import com.q.fawrytask.model.Customer;
import com.q.fawrytask.model.cart.Cart;
import com.q.fawrytask.model.product.Product;
import com.q.fawrytask.model.product.RegularProduct;
import com.q.fawrytask.model.product.ShippableExpirableProduct;
import com.q.fawrytask.model.product.ShippableProduct;

import java.time.LocalDate;

import static com.q.fawrytask.service.CheckoutService.checkout;

public class FawyTaskApp {
    public static void main(String[] args) {
        testValidCheckOut();
        System.out.println();
        testEmptyCart();
        System.out.println();
        testExpiredProduct();
        System.out.println();
        testNoStock();
        System.out.println();
        testInsufficientBalance();
    }

    public static void testValidCheckOut() {
        System.out.println("--- Test Case 1: Valid Checkout ---");
        Product cheese = new ShippableExpirableProduct("Cheese", 100.0, 2, 400, LocalDate.now().plusDays(30));
        Product biscuits = new ShippableExpirableProduct("Biscuits", 150.0, 1, 700, LocalDate.now().plusDays(60));
        Product scratchCard = new RegularProduct("Scratch Card", 200.0, 100);
        Customer customer = new Customer("Mohamed", 10000.0);
        Cart cart = new Cart();
        cart.add(cheese, 2);
        cart.add(biscuits, 1);
        cart.add(scratchCard, 5);
        checkout(customer, cart);
    }

    public static void testEmptyCart() {
        System.out.println("--- Test Case 2: Empty Cart ---");
        Customer customer = new Customer("Mohamed", 1000.0);
        Cart cart = new Cart();
        checkout(customer, cart);
    }

    public static void testExpiredProduct() {
        System.out.println("--- Test Case 3: Expired Product ---");
        Product cheese = new ShippableExpirableProduct("Cheese", 100.0, 2, 0.1, LocalDate.of(2025, 1, 1));
        Product biscuits = new ShippableExpirableProduct("Biscuits", 150.0, 1, 0.7, LocalDate.now().plusDays(60));
        Product scratchCard = new RegularProduct("Scratch Card", 200.0, 100);
        Customer customer = new Customer("Mohamed", 1000.0);
        Cart cart = new Cart();
        cart.add(cheese, 2);
        cart.add(biscuits, 1);
        cart.add(scratchCard, 200);
        checkout(customer, cart);
    }

    public static void testNoStock() {
        System.out.println("--- Test Case 4: No Stock ---");
        Product cheese = new ShippableExpirableProduct("Cheese", 100.0, 2, 0.1, LocalDate.now().plusDays(30));
        Product biscuits = new ShippableExpirableProduct("Biscuits", 150.0, 1, 0.7, LocalDate.now().plusDays(60));
        Product scratchCard = new RegularProduct("Scratch Card", 200.0, 0);
        Customer customer = new Customer("Mohamed", 1000.0);
        Cart cart = new Cart();
        cart.add(cheese, 2);
        cart.add(biscuits, 1);
        cart.add(scratchCard, 5);
        checkout(customer, cart);
    }

    public static void testInsufficientBalance() {
        System.out.println("--- Test Case 5: Insufficient Balance ---");
        Product biscuits = new ShippableProduct("Biscuits", 5000.0, 10, 0.2);
        Customer poorCustomer = new Customer("Mohamed", 500.0);
        Cart cart = new Cart();
        cart.add(biscuits, 1);
        checkout(poorCustomer, cart);
    }
}
