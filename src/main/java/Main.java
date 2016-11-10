import static spark.Spark.*;

import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

public class Main {

    public static void main(String[] args) {

        // default server settings
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);

        populateData();

        get("/", ProductController::renderAll, new ThymeleafTemplateEngine());
        get("/category/:id", ProductController::renderProducts, new ThymeleafTemplateEngine());
        get("/supplier/:id", ProductController::renderSupplier, new ThymeleafTemplateEngine());
    }

    public static void populateData() {

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);
        Supplier apple = new Supplier("Apple", "Digital content and services");
        supplierDataStore.add(apple);
        Supplier codeshop = new Supplier("Codeshop", "Everything for a good workplace");
        supplierDataStore.add(codeshop);


        //setting up a new product category
        //ProductCategory all = new ProductCategory("All", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        //productCategoryDataStore.add(all);
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);
        ProductCategory phones = new ProductCategory("Phones", "Hardware", "Smartphone.");
        productCategoryDataStore.add(phones);
        ProductCategory watches = new ProductCategory("Watches", "Hardware", "Smartwatch.");
        productCategoryDataStore.add(watches);
        ProductCategory others = new ProductCategory("Others", "Hardware", "Smartwatch.");
        productCategoryDataStore.add(others);

        //setting up products and printing it
        productDataStore.add(new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        productDataStore.add(new Product("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));
        productDataStore.add(new Product("Iwatch", 389, "USD", "Instant get...or not?", watches, apple));
        productDataStore.add(new Product("HTC", 115, "USD", "best phone ever", phones, amazon));
        productDataStore.add(new Product("Sound Bowl", 59, "USD", "Ring-Ring", others, codeshop));
        productDataStore.add(new Product("White Board", 129, "USD", "for brainstorming", others, codeshop));
        productDataStore.add(new Product("White Board Maxx 290", 39, "USD", "for drawing tables and classes", others, codeshop));
        productDataStore.add(new Product("White Board Eraser", 115, "USD", "for 'drop' table", others, codeshop));
        productDataStore.add(new Product("Iphone", 999, "USD", "simple the best", phones, apple));

    }


}
