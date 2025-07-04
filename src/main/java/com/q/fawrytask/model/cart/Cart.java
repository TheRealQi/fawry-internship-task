package com.q.fawrytask.model.cart;

import com.q.fawrytask.model.product.Product;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void add(Product product, int quantity) {
        CartItem cartItem = new CartItem(product, quantity);
        items.add(cartItem);
    }

    public void remove(CartItem item) {
        items.remove(item);
    }

    public void clear() {
        items.clear();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
