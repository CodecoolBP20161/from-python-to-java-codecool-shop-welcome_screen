package com.codecool.shop.dao.Implementation.JdbcImpl;

import com.codecool.shop.controller.ConfigController;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    public ArrayList<User> getAll() {
        String query = "SELECT * FROM users;";

        ArrayList<User> UserList = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)
        ){
            while (resultSet.next()){
                User result = new User(
                        resultSet.getString("email"),
                        resultSet.getString("name"));
                UserList.add(result);
            }
            return UserList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return UserList;
    }

    public HashMap<String, String> getAllUserData() {
        String query = "SELECT * FROM users;";

        HashMap<String, String> resultMap = new HashMap<>();

        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)
        ){
            while (resultSet.next()){
                String name = resultSet.getString("name");
                String emailAddress = resultSet.getString("email");

                resultMap.put(emailAddress, name);
            }
            return resultMap;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return resultMap;
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
