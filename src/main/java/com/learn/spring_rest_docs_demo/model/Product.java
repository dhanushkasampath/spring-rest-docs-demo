package com.learn.spring_rest_docs_demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Product {

    private Long id;
    private String name;
    private Double price;
}
