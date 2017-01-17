package com.codecool.shop.dao;

import com.codecool.shop.model.User;

import java.util.HashMap;

/**
 * Created by kalman on 2017.01.17..
 */
public interface UserDao {

    void add(User user);
    void remove(int id);

    HashMap<String, String> getAll();
}
