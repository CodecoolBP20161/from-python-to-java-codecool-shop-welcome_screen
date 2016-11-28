package com.codecool.shop.dao.Implementation.JdbcImpl;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kalman on 2016.11.22..
 */
public class ProductCategoryDaoJdbc implements ProductCategoryDao {

    private static final String DATABASE = "jdbc:postgresql://localhost:5432/codecoolshop";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "patrik";

    public void clearDATA() {
        String query = "TRUNCATE table productcategory;";
        executeQuery(query);

    }

    @Override
    public void add(ProductCategory category) {

        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(
                    "INSERT INTO productcategory (name, description, department) VALUES ( ?, ?, ?)");
            preparedStatement.setString(1, category.getName());
            preparedStatement.setString(2, category.getDescription());
            preparedStatement.setString(3, category.getDepartment());
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public ProductCategory find(int id) {

        String query = "SELECT * FROM productcategory WHERE productcategory_id ='" + id + "';";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)
        ) {
            if (resultSet.next()) {
                ProductCategory result = new ProductCategory(
                        resultSet.getString("name"),
                        resultSet.getString("department"),
                        resultSet.getString("description"));
                result.setId(resultSet.getInt("productcategory_id"));
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

        String query = "DELETE FROM productcategory WHERE productcategory_id = '" + id + "'; ";
        executeQuery(query);

    }


    @Override
    public List<ProductCategory> getAll() {


        String query = "SELECT * FROM productcategory;";

        List<ProductCategory> resultList = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ) {
            while (resultSet.next()) {
                ProductCategory actProdCat = new ProductCategory(
                        resultSet.getString("name"),
                        resultSet.getString("department"),
                        resultSet.getString("description"));
                actProdCat.setId(resultSet.getInt("productcategory_id"));
                resultList.add(actProdCat);
            }
            return resultList;

        } catch (SQLException e) {
            e.printStackTrace();
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
             Statement statement =connection.createStatement();
        ){
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}