package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Created by kalman on 2016.11.08..
 */
public class ShoppingCart {
    HashMap <Object, Integer> lineItems;
    private static ShoppingCart instance = null;


    public ShoppingCart() {
        this.lineItems = new HashMap<>();
    }

    public HashMap<Object, Integer> getLineItems() {
        return lineItems;
    }
    public static ShoppingCart getInstance() {
        if (instance == null) {
            instance = new ShoppingCart();
        }
        return instance;
    }

    public void add(Product product) {
        if (!lineItems.containsKey(product)) {
            lineItems.put(product, 1);
        } else {
            lineItems.put(product, lineItems.get(product) + 1);
        }
    }

    public Integer lineItemsum() {
        Integer sum = 0;
        for (Integer i : this.lineItems.values()) {
            sum += i;
        }
        return sum;
    }



    }
