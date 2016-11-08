package com.codecool.shop.model;

import java.util.HashMap;

/**
 * Created by kalman on 2016.11.08..
 */
public class ShoppingCart {
    HashMap<Product, Integer> lineItems;

    public ShoppingCart() {
    }

    public  void add(Product product) {
        Integer value = lineItems.get(product);
        if (value != null) {
            lineItems.put(product, lineItems.get(product) + 1);
        } else {
            lineItems.put(product, 1);
        }
    }
}
