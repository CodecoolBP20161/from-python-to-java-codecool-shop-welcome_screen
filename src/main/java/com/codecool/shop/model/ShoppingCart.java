package com.codecool.shop.model;

import javax.print.attribute.HashAttributeSet;
import java.util.*;

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
//    ArrayList<Object> listOfKeys = new ArrayList();
//    ArrayList<Integer> listOfValues = new ArrayList();
//
//    public ArrayList getKeys(){
//
//        for (Object key : lineItems.keySet()){
//            listOfKeys.add(key);
//
//        }
//        return listOfKeys;
//
//    }
//
//
//    public ArrayList getValues(){
//
//        for (Integer value : lineItems.values()){
//            listOfValues.add(value);
//
//        }
//        return listOfValues;
//
//    }
//
//    public ArrayList getTotalPrice(){
//        ArrayList summtotal = new ArrayList();
//        int totalprice = 0;
//
//        for ( Integer i : listOfValues) {
//            int key = listOfKeys.get(i).get
//            int price = listOfValues.get(i) * listOfKeys.get(i);
//            totalprice+=price;
//
//        }
//
//            summtotal.add(totalprice);
//
//            return summtotal;
//
//
//
//        }




}
