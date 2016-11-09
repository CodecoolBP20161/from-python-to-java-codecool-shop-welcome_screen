package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.BaseModel;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import spark.Request;
import spark.Response;
import spark.ModelAndView;

import java.util.HashMap;
import java.util.Map;

public class ProductController {

    public static ModelAndView renderAll(Request req, Response res) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();

        Map params = new HashMap<>();
        params.put("category", productCategoryDataStore.find(1));
        params.put("categories", productCategoryDataStore.getAll());
        params.put("products", productDataStore.getAll());
        return new ModelAndView(params, "product/index");
    }
    public static ModelAndView renderProducts(Request req, Response res) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();

            Map params = new HashMap<>();
            params.put("category", productCategoryDataStore.find(Integer.parseInt(req.params(":id"))));
            params.put("categories", productCategoryDataStore.getAll());
            params.put("products", productDataStore.getBy(productCategoryDataStore.find(Integer.parseInt(req.params(":id")))));
            return new ModelAndView(params, "product/index");
    }


    public static ModelAndView renderSupplier(Request req, Response res) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        Map params = new HashMap<>();
        params.put("category", supplierDataStore.find(Integer.parseInt(req.params(":id"))));
        params.put("suppliers", supplierDataStore.getAll());
        params.put("products", productDataStore.getBy(supplierDataStore.find(Integer.parseInt(req.params(":id")))));
        return new ModelAndView(params, "product/index");
    }




}
