package com.learn.spring_rest_docs_demo.controller;

import com.learn.spring_rest_docs_demo.model.Product;
import com.learn.spring_rest_docs_demo.util.DummyProducts;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getProducts(){
        return ResponseEntity.ok(DummyProducts.getAllProducts());
    }

    @PostMapping("add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        Product addedProduct = DummyProducts.addProduct(product);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(addedProduct.getId())
                .toUri();
        return ResponseEntity.created(location).body(addedProduct);
    }

}
