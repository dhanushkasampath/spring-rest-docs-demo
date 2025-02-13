package com.learn.spring_rest_docs_demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.spring_rest_docs_demo.model.Product;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({ RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
@AutoConfigureRestDocs(outputDir = "target/generated-snippets") // this directory will be autogenerated
@RequiredArgsConstructor
class ProductControllerTest {

    private final WebApplicationContext context;

    private MockMvc mockMvc;

    List<Product> products=null;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {

        this.mockMvc = MockMvcBuilders
            .webAppContextSetup(webApplicationContext)
            .apply(documentationConfiguration(restDocumentation))
            .build();

        products= Stream.of(
            new Product(101L, "Mobile", 15000.00)
            ,new Product(102L, "laptop", 75000.00)
            ,new Product(102L, "laptop", 75000.00)
            ,new Product(102L, "laptop", 75000.00))
                .collect(Collectors.toList());
    }

    @Test
    void testGetProducts() throws Exception {
        mockMvc.perform(get("/products/all")
              .contentType("application/json")).andDo(print())
              .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(products)));
              .andDo(document("{methodName}",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint())));
    }

    @Test
    void testAddProduct() throws Exception {
        String productJson = new ObjectMapper().writeValueAsString(new Product(101L, "Mobile", 15000.00));
        mockMvc.perform(post("/products/add")
            .content(productJson)
            .contentType("application/json")).andDo(print())
            .andExpect(status().isCreated())
            .andExpect(MockMvcResultMatchers.content().json(productJson))
            .andDo(document("{methodName}",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())));
    }
}