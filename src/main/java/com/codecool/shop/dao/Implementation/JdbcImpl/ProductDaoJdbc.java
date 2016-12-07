package com.codecool.shop.dao.Implementation.JdbcImpl;

import com.codecool.shop.controller.ConfigController;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kalman on 2016.11.22..
 */
public class ProductDaoJdbc implements ProductDao {

    ConfigController controller = new ConfigController();

    private String DATABASE = controller.getPropValues("database");
    private String DB_USER = controller.getPropValues("user");
    private String DB_PASSWORD = controller.getPropValues("password");

    ProductCategoryDaoJdbc prodcat = new ProductCategoryDaoJdbc();
    SupplierDaoJdbc supp = new SupplierDaoJdbc();



    @Override
    public void add(Product product) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(
                    "INSERT INTO products (name, description, defprice, currency, supplier, prodcat) VALUES (?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setFloat(3, product.getDefaultPrice());
            preparedStatement.setString(4, String.valueOf(product.getDefaultCurrency()));
            preparedStatement.setInt(5, product.getSupplier().getId());
            preparedStatement.setInt(6, product.getProductCategory().getId());
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public Product find(int id) {


        String query = "SELECT products.product_id, products.name, products.description, products.defprice, products.currency," +
                "supplier.supplier_id AS supplier, productcategory.productcategory_id AS prodcat" +
                " FROM products " +
                " FULL OUTER JOIN supplier ON products.supplier = supplier.supplier_id" +
                " FULL OUTER JOIN productcategory ON products.prodcat = productcategory.productcategory_id " +
                " WHERE product_id ='" + id + "';";



        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)
        ) {
            if (resultSet.next()) {
                Product result = new Product(
                        resultSet.getString("name"),
                        resultSet.getFloat("defprice"),
                        resultSet.getString("currency"),
                        resultSet.getString("description"),
                        prodcat.find(resultSet.getInt("prodcat")),
                        supp.find(resultSet.getInt("supplier")));
                result.setId(resultSet.getInt("product_id"));
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
                        resultSet.getString("currency"),
                        resultSet.getString("description"),
                        prodcat.find(resultSet.getInt("prodcat")),
                        supp.find(resultSet.getInt("supplier")));
                product.setId(resultSet.getInt("product_id"));
                resultList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return resultList;
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
                        resultSet.getString("currency"),
                        resultSet.getString("description"),
                        prodcat.find(resultSet.getInt("prodcat")),
                        supp.find(resultSet.getInt("supplier")));
                product.setId(resultSet.getInt("product_id"));
                resultList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }


        @Override
        public List<Product> getBy(ProductCategory productCategory) {
        String query = "SELECT * FROM products WHERE prodcat ='" + productCategory.getId() + "';";
        List<Product> resultList = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ) {
            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getString("name"),
                        resultSet.getFloat("defprice"),
                        resultSet.getString("currency"),
                        resultSet.getString("description"),
                        prodcat.find(resultSet.getInt("prodcat")),
                        supp.find(resultSet.getInt("supplier")));
                        product.setId(resultSet.getInt("product_id"));
                        resultList.add(product);
            }
            return resultList;
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
