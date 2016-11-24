package com.codecool.shop.dao;

import com.codecool.shop.dao.Implementation.MemImpl.ProductCategoryDaoMem;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;
import org.junit.*;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by seradam on 2016.11.23..
 */
public class ProductCategoryDaoTest {
    private ProductCategoryDao dao = ProductCategoryDaoMem.getInstance();



     ProductCategory productCategory = new ProductCategory("name", "dep", "desc");
     ProductCategory productCategory2 = new ProductCategory("name2", "dep2", "desc2");
    ProductCategory productCategory3 = new ProductCategory("name3", "dep3", "desc3");


    @Before
    public void setUp() throws Exception {
        productCategory.setId(1);
        productCategory2.setId(2);
        dao.add(productCategory);
        dao.add(productCategory2);
    }

    @After
    public void tearDown() throws Exception {
        dao.clearDATA();


    }

    @Test
    public void add() throws Exception {
        productCategory3.setId(3);
        dao.add(productCategory3);
        assertEquals(3, dao.getAll().size());

    }

    @Test
    public void find() throws Exception {
        assertEquals(productCategory, dao.find(1));


    }

    @Test
    public void remove() throws Exception {
        dao.remove(2);
        assertEquals(1, dao.getAll().size());
    }

    @Test
    public void getAll() throws Exception {
        List<ProductCategory> exp = new ArrayList();
        exp.add(productCategory);
        exp.add(productCategory2);
        List<ProductCategory> actual = dao.getAll();
        assertEquals(exp, actual);
    }

}