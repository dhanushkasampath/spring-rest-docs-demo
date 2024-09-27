package com.learn.spring_rest_docs_demo.util;



import com.learn.spring_rest_docs_demo.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * This class used as a resource which returns list of products. Ideally this should be replaced by a database
 */
public class DummyProducts {

    private static final List<Product> productsList = new ArrayList<>();

    static {
        productsList.addAll(List.of(
                Product.builder().id(1L).name("Gas Cooker").price(1000.00).build(),
                Product.builder().id(2L).name("Washing Machine").price(7000.00).build(),
                Product.builder().id(3L).name("Toaster").price(2000.00).build(),
                Product.builder().id(4L).name("Blender").price(4000.00).build()
        ));
    }

    public static List<Product> getAllProducts(){
        return productsList;
    }

    public static Product addProduct(Product product) {
        productsList.add(product);
        return product;
    }
}
