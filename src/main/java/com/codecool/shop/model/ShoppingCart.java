package com.codecool.shop.model;

import javax.print.attribute.HashAttributeSet;
import java.util.*;

/**
 * Created by kalman on 2016.11.08..
 */
public class ShoppingCart {
    HashMap<Product, Integer> lineItems;
    private static ShoppingCart instance = null;


    public ShoppingCart() {
        this.lineItems = new HashMap<>();
    }

    public HashMap<Product, Integer> getLineItems() {
        return lineItems;
    }

    public static ShoppingCart getInstance() {
        if (instance == null) {
            instance = new ShoppingCart();
        }
        return instance;
    }

    public void add(Product product) {

        for (Product prodKey : lineItems.keySet()) {
            if (prodKey.getId() == product.getId()) {
                lineItems.put(prodKey, lineItems.get(prodKey) + 1);
                return;
            }
        }
        lineItems.put(product, 1);

    }

    public void remove(Product product) {
        for (Object objKey : lineItems.keySet()) {
            Product prodKey = (Product) objKey;
            if (prodKey.getId() == product.getId()) {
                lineItems.remove(objKey);
                break;
            }
        }
    }


    public Integer lineItemsum() {
        Integer sum = 0;
        for (Integer i : this.lineItems.values()) {
            sum += i;
        }
        return sum;
    }

    public float getTotalPrice() {

        float totalprice = 0;

        for (Map.Entry<Product, Integer> entry : lineItems.entrySet()) {
            Product key = entry.getKey();
            Integer value = entry.getValue();
            totalprice += key.getDefaultPrice() * value;

        }

        return totalprice;


    }

    public void changeQuantity(String id, int difference) {
        int idToFind = Integer.parseInt(id);
        for (Map.Entry<Product, Integer> entry : lineItems.entrySet()) {
            Product key = entry.getKey();
            Integer value = entry.getValue();
            if (value < 1) {
                lineItems.remove(key,value);
                break;
            }

            else if (idToFind == key.getId()) {
                lineItems.put(key, value + difference);
                if(entry.getValue()<1){
                    lineItems.remove(key,value);
                }
                break;
            }

        }
    }
}


