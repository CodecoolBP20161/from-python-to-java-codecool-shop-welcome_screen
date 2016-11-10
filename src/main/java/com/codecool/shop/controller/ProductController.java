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

        Map params = new HashMap<>();
        params.put("category", productCategoryDataStore.find(1));
        params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));
        return new ModelAndView(params, "product/index");
    }
    public static String addToCart(Request req, Response res) {
        int productId = Integer.parseInt(req.params(":id"));
        if(req.session().attribute("cart") == null){
            ShoppingCart Cart = new ShoppingCart();
            req.session().attribute("cart", Cart);
        }

        req.session().attribute("cart");
        ShoppingCart sessionCart = req.session().attribute("cart");
        ProductDao productDataStore = ProductDaoMem.getInstance();
        Product product = productDataStore.find(productId);
        sessionCart.add(product);
        System.out.println(sessionCart.getLineItems());
        //System.out.println( req.session() );
        //System.out.println((req.session().attribute("cart")).getClass().getName());


        res.redirect("/");
        return null;

}}
