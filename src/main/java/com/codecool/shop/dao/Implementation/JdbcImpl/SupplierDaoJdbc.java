package com.codecool.shop.dao.Implementation.JdbcImpl;


import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kalman on 2016.11.22..
 */
public class SupplierDaoJdbc implements SupplierDao {

    private static final String DATABASE = "jdbc:postgresql://localhost:5432/codecoolshop";
    private static final String DB_USER = "Kalman";
    private static final String DB_PASSWORD = "jelszo";

    public void clearDATA() {
        String query = "TRUNCATE table productcategory;";
        executeQuery(query);

    }


    @Override
    public void add(Supplier supplier) {

        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(
                    "INSERT INTO supplier (name, description) VALUES ( ?, ?)");
            preparedStatement.setString(1, supplier.getName());
            preparedStatement.setString(2, supplier.getDescription());
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    @Override
    public Supplier find(int id) {
        String query = "SELECT * FROM supplier WHERE supplier_id ='" + id + "';";
        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)

        ){
            if (resultSet.next()){
                Supplier result = new Supplier(resultSet.getInt("supplier_id"), resultSet.getString("name"),
                        resultSet.getString("description"));
                return result;
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }


    @Override
    public void remove(int id) {
        String query = "DELETE FROM supplier WHERE supplier_id ='" + id + "';";
        executeQuery(query);

    }

    @Override
    public List<Supplier> getAll() {
        String query = "SELECT * FROM supplier;";

        List<Supplier> resultList = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement =connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)
        ){
            while (resultSet.next()){
                Supplier result = new Supplier(
                        resultSet.getString("name"),
                        resultSet.getString("description"));
                resultList.add(result);
            }
            return resultList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return resultList;
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
