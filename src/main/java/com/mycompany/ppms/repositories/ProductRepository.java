package com.mycompany.ppms.repositories;

import com.mycompany.ppms.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ProductRepository {

    @Autowired static Map<String, Product> products = new HashMap<String, Product>();
    @Autowired static int numProducts = 0;

    @Autowired
    public static Product findById(String id) {
        return products.containsKey(id) ? products.get(id) : null;
    }

    @Autowired
    public static void addProduct(Product product) {
        products.put(product.getId(), product);
        numProducts ++;
    }

    @Autowired
    public static void initWithSampleData() {
        products.put("1", new Product("1", "very nice shoes"));
        products.put("2", new Product("2", "ok shoes"));
        products.put("999", new Product("999", "ugly shoes"));
        products.put("567", new Product("some random id", "very ugly shoes"));
        numProducts += 4;
    }
}
