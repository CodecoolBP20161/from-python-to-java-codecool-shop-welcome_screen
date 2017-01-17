package com.codecool.shop.dao.Implementation.JdbcImpl;

import com.codecool.shop.controller.ConfigController;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

/**
 * Created by kalman on 2017.01.17..
 */
public class UserDaoJdbc implements UserDao {

    ConfigController controller = new ConfigController();

    private String DATABASE = controller.getPropValues("database");
    private String DB_USER = controller.getPropValues("user");
    private String DB_PASSWORD = controller.getPropValues("password");

    @Override
    public void add(User user) {

    }

    @Override
    public void remove(int id) {

    }

    @Override
    public HashMap<String, String> getAll() {
        return null;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
    }

    private void executeQuery(String query) {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()
        ) {
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
