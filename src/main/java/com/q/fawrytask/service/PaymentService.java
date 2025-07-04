package com.q.fawrytask.service;

import com.q.fawrytask.model.Customer;

public class PaymentService {
    protected static void processPayment(Customer customer, double amount) {
        if (sufficientBalance(customer, amount)) {
            customer.setBalance(customer.getBalance() - amount);
        }
    }

    protected static boolean sufficientBalance(Customer customer, double amount) {
        return customer.getBalance() >= amount;
    }
}
