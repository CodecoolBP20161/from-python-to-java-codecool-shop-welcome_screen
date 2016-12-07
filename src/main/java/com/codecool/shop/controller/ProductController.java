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

    static ProductDao productDataStore = new ProductDaoJdbc();
    static ProductCategoryDao productCategoryDataStore = new ProductCategoryDaoJdbc();
    static SupplierDao supplierDataStore = new SupplierDaoJdbc();


    public static ModelAndView renderAll(Request req, Response res) {

        ShoppingCart cart = req.session().attribute("cart");
        int cartItemSum = 0;
        try {
            cartItemSum = cart.lineItemsum();
        } catch (NullPointerException e) {}

        if (req.session().attribute("cart") == null) {
            ShoppingCart Cart = new ShoppingCart();
            req.session().attribute("cart", Cart);
        }


        Map params = new HashMap<>();
        params.put("category", productCategoryDataStore.find(1));
        params.put("categories", productCategoryDataStore.getAll());
        params.put("suppliers", supplierDataStore.getAll());
        params.put("products", productDataStore.getAll());
        params.put("cart", cartItemSum);

        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView renderProducts(Request req, Response res) {

        ShoppingCart cart = req.session().attribute("cart");
        int cartItemSum = 0;
        try {
            cartItemSum = cart.lineItemsum();
        } catch (NullPointerException e) {}

        Map params = new HashMap<>();
        params.put("category", productCategoryDataStore.find(Integer.parseInt(req.params(":id"))));
        params.put("categories", productCategoryDataStore.getAll());
        params.put("suppliers", supplierDataStore.getAll());
        params.put("products", productDataStore.getBy(productCategoryDataStore.find(Integer.parseInt(req.params(":id")))));
        params.put("cart", cartItemSum);


        return new ModelAndView(params, "product/index");
    }
    public static String addToCart(Request req, Response res) {
        int productId = Integer.parseInt(req.params(":id"));

        ShoppingCart sessionCart = req.session().attribute("cart");
        ProductDao productDataStore = new ProductDaoJdbc();
        Product product = productDataStore.find(productId);
        sessionCart.add(product);

        res.redirect("/");
        return null;
    }


    public static ModelAndView renderSupplier(Request req, Response res) {

            ShoppingCart cart = req.session().attribute("cart");
            int cartItemSum = 0;
            try {
                cartItemSum = cart.lineItemsum();
            } catch (NullPointerException e) {}


            Map params = new HashMap<>();

        params.put("category", supplierDataStore.find(Integer.parseInt(req.params(":id"))));
        params.put("categories", productCategoryDataStore.getAll());
        params.put("suppliers", supplierDataStore.getAll());
        params.put("products", productDataStore.getBy(supplierDataStore.find(Integer.parseInt(req.params(":id")))));
        params.put("cart", cartItemSum);

            return new ModelAndView(params, "product/index");
    }

    public static ModelAndView renderList(Request req, Response res) {
        ShoppingCart cart = req.session().attribute("cart");

        Map params = new HashMap<>();
        params.put("cart", cart);
//        params.put("quantity", );
//        params.put("totalprice", cart.getTotalPrice() );

        return new ModelAndView(params, "product/list");
    }


}


