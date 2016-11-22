package com.codecool.shop.dao.Implementation.JdbcImpl;


import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.List;

/**
 * Created by kalman on 2016.11.22..
 */
public class SupplierDaoJdbc implements SupplierDao {

    private static final String DATABASE = "jdbc:postgresql://localhost:5432/codecoolshop";
    private static final String DB_USER = "Kalman";
    private static final String DB_PASSWORD = "jelszo";

    @Override
    public void add(Supplier category) {

    }

    @Override
    public Supplier find(int id) {
        String query = "SELECT * FROM supplier WHERE id ='" + id + "';";
        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ){
            if (resultSet.next()){
                Supplier result = new Supplier(resultSet.getString("name"),
                        resultSet.getString("desciption"));
                return result;
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public void remove(int id) {
        String query = "DELETE FROM supplier WHERE id ='" + id + "';";
        executeQuery(query);

    }

    @Override
    public List<Supplier> getAll() {
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
        }
    }
}
