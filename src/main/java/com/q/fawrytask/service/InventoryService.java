package com.q.fawrytask.service;
import com.q.fawrytask.model.product.Product;

public class InventoryService {
    protected static boolean hasStockQuantity(Product product, int quantity) {
        return product.getQuantity() >= quantity;
    }

    protected static void reduceStockQuantity(Product product, int quantity) {
        if (hasStockQuantity(product, quantity)) {
            product.setQuantity(product.getQuantity() - quantity);
        }
    }

    protected static void increaseStockQuantity(Product product, int quantity) {
        product.setQuantity(product.getQuantity() + quantity);
    }
}
