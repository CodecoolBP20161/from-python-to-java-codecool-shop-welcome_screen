package com.codecool.shop.model;

import javax.print.attribute.HashAttributeSet;
import java.util.*;

/**
 * Created by kalman on 2016.11.08..
 */
public class ShoppingCart {
    HashMap<Object, Integer> lineItems;
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

        for (Object objKey : lineItems.keySet()) {
            Product prodKey = (Product) objKey;
            if (prodKey.getId() == product.getId()) {
                lineItems.put(prodKey, lineItems.get(prodKey) + 1);
            }
            else {
                lineItems.put(product, 1);
            }
        }
        if (lineItems.keySet().size() == 0) {
            lineItems.put(product, 1);

        }



//        if (!lineItems.containsKey(product)) {
//            lineItems.put(product, 1);
//        } else {
//            lineItems.put(product, lineItems.get(product) + 1);
//        }
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

        for (Map.Entry<Object, Integer> entry : lineItems.entrySet()) {
            Product key = (Product) entry.getKey();
            Integer value = entry.getValue();
            totalprice += key.getDefaultPrice() * value;

        }

        return totalprice;


    }

    public void changeQuantity(String id, int dif) {
        int idToFind = Integer.parseInt(id);
        for (Map.Entry<Object, Integer> entry : lineItems.entrySet()) {
            Product key = (Product) entry.getKey();
            Integer value = entry.getValue();
            if (idToFind == key.getId()) {
                lineItems.put(key, value+ dif);
                if (value == 0) lineItems.remove(entry);
                break;
            }
        }
    }
}


