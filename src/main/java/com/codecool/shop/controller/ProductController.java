package com.codecool.shop.controller;


import com.codecool.shop.dao.Implementation.JdbcImpl.ProductCategoryDaoJdbc;
import com.codecool.shop.dao.Implementation.JdbcImpl.ProductDaoJdbc;
import com.codecool.shop.dao.Implementation.JdbcImpl.SupplierDaoJdbc;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ShoppingCart;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class ProductController {

    private static ProductDao productDataStore = new ProductDaoJdbc();
    private static ProductCategoryDao productCategoryDataStore = new ProductCategoryDaoJdbc();
    private static SupplierDao supplierDataStore = new SupplierDaoJdbc();


    public static ModelAndView renderAll(Request req, Response res) {
        int cartItemSum = ProductController.getLineItemSum(req);

        Map params = new HashMap<>();
        params.put("category", productCategoryDataStore.find(1));
        params.put("categories", productCategoryDataStore.getAll());
        params.put("suppliers", supplierDataStore.getAll());
        params.put("products", productDataStore.getAll());
        params.put("cart", cartItemSum);

        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView renderProducts(Request req, Response res) {

        Map params = ProductController.createParams(req);

        return new ModelAndView(params, "product/index");
    }

    public static String addToCart(Request req, Response res) {
        int productId = Integer.parseInt(req.params(":id"));
        String referer = req.headers("referer");

        ShoppingCart sessionCart = ProductController.getCart(req);
        Product product = productDataStore.find(productId);
        sessionCart.add(product);

        res.redirect(referer);
        return null;
    }

    public static String removeFromCart(Request req, Response res) {
        Product product = productDataStore.find(Integer.parseInt(req.params(":id")));
        String referer = req.headers("referer");

        ShoppingCart sessionCart = ProductController.getCart(req);

        sessionCart.remove(product);

        res.redirect(referer);
        return null;
    }


    public static ModelAndView renderSupplier(Request req, Response res) {

            Map params = ProductController.createParams(req);

            return new ModelAndView(params, "product/index");
    }

    public static ModelAndView renderList(Request req, Response res) {
        ShoppingCart cart = ProductController.getCart(req);

        Map params = new HashMap<>();
        params.put("cart", cart);

        return new ModelAndView(params, "product/list");
    }

    public static Map createParams(Request req) {

        Map params = new HashMap<>();
        int cartItemSum = ProductController.getLineItemSum(req);

        params.put("category", productCategoryDataStore.find(Integer.parseInt(req.params(":id"))));
        params.put("categories", productCategoryDataStore.getAll());
        params.put("suppliers", supplierDataStore.getAll());
        params.put("products", productDataStore.getBy(productCategoryDataStore.find(Integer.parseInt(req.params(":id")))));
        params.put("cart", cartItemSum);

        return params;
    }

    public static ShoppingCart getCart(Request req) {
        if (req.session().attribute("cart") == null) {
            ShoppingCart cart = new ShoppingCart();
            req.session().attribute("cart", cart);
        }
        ShoppingCart cart = req.session().attribute("cart");

        return cart;
    }

    public static int getLineItemSum(Request req) {

        ShoppingCart cart = ProductController.getCart(req);
        int cartItemSum = 0;
        cartItemSum = cart.lineItemsum();

        return cartItemSum;
    }
}


