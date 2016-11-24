package com.codecool.shop.dao.Implementation.JdbcImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.xml.crypto.KeySelectorException;
import java.util.List;

/**
 * Created by kalman on 2016.11.22..
 */
public class ProductDaoJdbc implements ProductDao {

    private static final String DATABASE = "jdbc:postgresql://localhost:5432/codecoolshop";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "mccartney42";

    ProductCategoryDaoJdbc prodcat = new ProductCategoryDaoJdbc();
    SupplierDaoJdbc supp = new SupplierDaoJdbc();



    @Override
    public void add(Product product) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(
                    "INSERT INTO products (name, description, defprice, currency, supplier, prodcat) VALUES (?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setString(3, String.valueOf(product.getDefaultPrice()));
            preparedStatement.setString(4, String.valueOf(product.getDefaultCurrency()));
            preparedStatement.setString(5, String.valueOf(product.getSupplier().getId()));
            preparedStatement.setString(6, String.valueOf(product.getProductCategory().getId()));
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public Product find(int id) {


        String query = "SELECT products.id, products.name, products.description, products.defprice, products.currency," +
                "supplier.id AS supplier, productcategory.id AS prodcat" +
                " FROM products " +
                " FULL OUTER JOIN supplier ON products.supplier = supplier.id" +
                " FULL OUTER JOIN productcategory ON products.prodcat = productcategory.id " +
                " WHERE id ='" + id + "';";



        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)
        ) {
            if (resultSet.next()) {
                Product result = new Product(
                        resultSet.getString("name"),
                        resultSet.getFloat("defprice"),
                        resultSet.getString("description"),
                        resultSet.getString("currency"),
                        prodcat.find(resultSet.getInt("prodcat")),
                        supp.find(resultSet.getInt("supplier")));
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
        String query = "DELETE * FROM product WHERE id ='" + id + "';";
        executeQuery(query);
    }

    @Override
    public List<Product> getAll() {
        String query = "SELECT * FROM products;";

        List<Product> resultList = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ) {
            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getString("name"),
                        resultSet.getFloat("defprice"),
                        resultSet.getString("description"),
                        resultSet.getString("currency"),
                        prodcat.find(resultSet.getInt("prodcat")),
                        supp.find(resultSet.getInt("supplier")));
                resultList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }


    @Override
    public List<Product> getBy(Supplier supplier) {
        String query = "SELECT * FROM products WHERE supplier ='" + supplier.getId() + "';";
        List<Product> resultList = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getString("name"),
                        resultSet.getFloat("defprice"),
                        resultSet.getString("description"),
                        resultSet.getString("currency"),
                        prodcat.find(resultSet.getInt("prodcat")),
                        supp.find(resultSet.getInt("supplier")));
                resultList.add(product);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


        @Override
        public List<Product> getBy(ProductCategory productCategory) {
        String query = "SELECT * FROM product WHERE productcategory ='" + productCategory.getId() + "';";
        List<Product> resultList = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ) {
            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getString("name"),
                        resultSet.getFloat("defprice"),
                        resultSet.getString("description"),
                        resultSet.getString("currency"),
                        prodcat.find(resultSet.getInt("prodcat")),
                        supp.find(resultSet.getInt("supplier")));
                        resultList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

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
