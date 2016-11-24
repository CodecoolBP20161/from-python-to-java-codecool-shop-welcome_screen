package com.codecool.shop.dao;

import com.codecool.shop.dao.Implementation.MemImpl.SupplierDaoMem;
import com.codecool.shop.model.Supplier;
import org.junit.*;
import org.mockito.Mock;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by seradam on 2016.11.23..
 */
public class SupplierDaoTest {
    private SupplierDaoMem dao = SupplierDaoMem.getInstance();


    @Mock
    Supplier supplier = new Supplier("name", "desc");
    Supplier supplier2 = new Supplier("name2", "desc2");


    @Before
    public void setUp() throws Exception {
        supplier.setId(1);
        supplier2.setId(2);
        dao.add(supplier);
        dao.add(supplier2);
    }

    @After
    public void tearDown() throws Exception {
        dao.setDATA();


    }

    @Test
    public void add() throws Exception {
        assertEquals(2, dao.getAll().size());

    }

    @Test
    public void find() throws Exception {
        System.out.println(supplier);
        System.out.println(dao.find(1));
        assertEquals(supplier, dao.find(1));


    }

    @Test
    public void remove() throws Exception {
        dao.remove(2);
        assertEquals(1, dao.getAll().size());
    }

    @Test
    public void getAll() throws Exception {
        List<Supplier> exp = new ArrayList();
        exp.add(supplier);
        exp.add(supplier2);
        List<Supplier> actual = dao.getAll();
        assertEquals(exp, actual);
    }

}