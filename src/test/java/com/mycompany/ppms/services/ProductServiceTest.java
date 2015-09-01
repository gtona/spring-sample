package com.mycompany.ppms.services;

import com.mycompany.ppms.repositories.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductServiceTest {

    @Autowired private ProductRepository productRepository;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void find_whenValidId_ReturnsProduct() {

    }
}