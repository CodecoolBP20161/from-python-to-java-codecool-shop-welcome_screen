import static spark.Spark.*;

import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.*;
import com.codecool.shop.dao.Implementation.JdbcImpl.ProductCategoryDaoJdbc;
import com.codecool.shop.dao.Implementation.JdbcImpl.ProductDaoJdbc;
import com.codecool.shop.dao.Implementation.MemImpl.ProductCategoryDaoMem;
import com.codecool.shop.dao.Implementation.MemImpl.ProductDaoMem;
import com.codecool.shop.dao.Implementation.MemImpl.SupplierDaoMem;
import com.codecool.shop.model.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // default server settings
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);



        get("/", ProductController::renderAll, new ThymeleafTemplateEngine());
        get("/category/:id", ProductController::renderProducts, new ThymeleafTemplateEngine());
        get("/supplier/:id", ProductController::renderSupplier, new ThymeleafTemplateEngine());
        get("/list", ProductController::renderList, new ThymeleafTemplateEngine());
        get("/addtocart/:id", ProductController::addToCart);

        post("/add_item", (req, res) -> {
            ((ShoppingCart) req.session().attribute("cart")).changeQuantity(req.queryParams("id"), 1);
            res.redirect("/list");
            return null;
        });

        post("/remove_item", (req, res) -> {
            ((ShoppingCart) req.session().attribute("cart")).changeQuantity(req.queryParams("id"), -1);
            res.redirect("/list");
            return null;
        });

    }


}
