package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.Implementation.MemImpl.ProductCategoryDaoMem;
import com.codecool.shop.dao.Implementation.MemImpl.ProductDaoMem;
import com.codecool.shop.dao.Implementation.MemImpl.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ShoppingCart;

import spark.Request;
import spark.Response;
import spark.ModelAndView;

import java.util.HashMap;
import java.util.Map;

public class ProductController {

    public static ModelAndView renderAll(Request req, Response res) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        ShoppingCart cart = req.session().attribute("cart");
        int cartItemSum = 0;
        try {
            cartItemSum = cart.lineItemsum();
        } catch (NullPointerException e) {}


        Map params = new HashMap<>();
        params.put("category", productCategoryDataStore.find(1));
        params.put("categories", productCategoryDataStore.getAll());
        params.put("suppliers", supplierDataStore.getAll());
        params.put("products", productDataStore.getAll());
        params.put("cart", cartItemSum);

        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView renderProducts(Request req, Response res) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

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
        if (req.session().attribute("cart") == null) {
            ShoppingCart Cart = new ShoppingCart();
            req.session().attribute("cart", Cart);
        }

        ShoppingCart sessionCart = req.session().attribute("cart");
        ProductDao productDataStore = ProductDaoMem.getInstance();
        Product product = productDataStore.find(productId);
        sessionCart.add(product);

        res.redirect("/");
        return null;
    }


    public static ModelAndView renderSupplier(Request req, Response res) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();

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


