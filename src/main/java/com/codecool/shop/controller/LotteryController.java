package com.codecool.shop.controller;

import com.codecool.shop.dao.Implementation.JdbcImpl.UserDaoJdbc;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONObject;
import spark.Request;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.apache.http.entity.ContentType.APPLICATION_JSON;

/**
 * Created by seradam on 2017.01.17..
 */
public class LotteryController {
    private static final String SERVICE_URL = "http://localhost:60001/api/random";

    public String createJSON() throws IOException, URISyntaxException {
        URIBuilder builder = new URIBuilder(SERVICE_URL);
        UserDaoJdbc datahandler = new UserDaoJdbc();
        JSONObject json =  new JSONObject(datahandler.getAllUserData());
        String toSend = json.toString();
        return org.apache.http.client.fluent.Request.Post(builder.build())
                .bodyString(toSend, APPLICATION_JSON)
                .execute()
                .returnContent()
                .asString();
    }
    public static String sendRequest(Request req, spark.Response res){
        LotteryController ez = new LotteryController();
        try {
            return ez.createJSON();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return "service not available";
    }
}
