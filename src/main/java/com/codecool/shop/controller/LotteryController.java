package com.codecool.shop.controller;

import com.codecool.shop.dao.Implementation.JdbcImpl.UserDaoJdbc;
import org.json.JSONObject;
import spark.Request;

import java.io.IOException;
import org.apache.http.client.utils.URIBuilder;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

/**
 * Created by seradam on 2017.01.17..
 */
public class LotteryController {
    private static final String SERVICE_URL = "http://localhost:60000";

    public JSONObject getUsersFromDatabase(){
        UserDaoJdbc datahandler = new UserDaoJdbc();
        return new JSONObject(datahandler.getAllUserData());
    }

    private String execute(String url) throws IOException, URISyntaxException {
        URI uri = new URIBuilder(SERVICE_URL).build();
        return org.apache.http.client.fluent.Request.Get(uri).execute().returnContent().asString();
    }
}
