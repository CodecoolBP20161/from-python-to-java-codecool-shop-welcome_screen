import com.codecool.shop.controller.ProductController;
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

    }


}
