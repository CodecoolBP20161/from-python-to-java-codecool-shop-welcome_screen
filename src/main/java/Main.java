import com.codecool.shop.controller.LotteryController;
import com.codecool.shop.controller.ProductController;
import com.codecool.shop.model.ShoppingCart;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import static spark.Spark.*;

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
        get("/removefromcart/:id", ProductController::removeFromCart);

        get("/toto", LotteryController::sendRequest);


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
