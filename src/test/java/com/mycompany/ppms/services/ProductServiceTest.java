package com.mycompany.ppms.services;

import com.mycompany.ppms.models.Product;
import com.mycompany.ppms.repositories.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class ProductServiceTest {

    @Autowired private ProductRepository productRepository;

    @Before
    public void setUp() throws Exception {
        productRepository.initWithSampleData();
    }

    @Test
    public void find_whenValidId_ReturnsProduct() {
        Product productToBeAdded = new Product("some id", "some product");
        productRepository.addProduct(productToBeAdded);
        Product addedProduct = productRepository.findById("some id");

        assertThat(productToBeAdded, equalTo(addedProduct));
    }
}