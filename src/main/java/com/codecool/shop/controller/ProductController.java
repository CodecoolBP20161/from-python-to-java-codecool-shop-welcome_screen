package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.ShoppingCart;
import com.codecool.shop.model.Supplier;

import spark.Request;
import spark.Response;
import spark.ModelAndView;

import java.util.HashMap;
import java.util.Map;

public class ProductController {

    public static ModelAndView renderProducts(Request req, Response res) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        ShoppingCart cart = req.session().attribute("cart");
        int cartItemSum = 0;
        try {
            System.out.println(cart.getLineItems());
            cartItemSum = cart.lineItemsum();
        } catch (NullPointerException e) {}

        Map params = new HashMap<>();
        params.put("category", productCategoryDataStore.find(1));
        params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));

        params.put("cart", cartItemSum);


        return new ModelAndView(params, "product/index");
    }
    public static String addToCart(Request req, Response res) {
        int productId = Integer.parseInt(req.params(":id"));
        if(req.session().attribute("cart") == null){
            ShoppingCart Cart = new ShoppingCart();
            req.session().attribute("cart", Cart);
        }

        ShoppingCart sessionCart = req.session().attribute("cart");
        ProductDao productDataStore = ProductDaoMem.getInstance();
        Product product = productDataStore.find(productId);
        sessionCart.add(product);
        System.out.println(sessionCart.getLineItems());

        System.out.println(sessionCart.lineItemsum());

        //System.out.println((req.session().attribute("cart")).getClass().getName());


        res.redirect("/");
        return null;

}}
