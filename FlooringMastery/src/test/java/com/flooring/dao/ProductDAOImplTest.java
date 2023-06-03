package com.flooring.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.flooring.model.Product;


class ProductDAOImplTest {
    private ProductDAOImpl productDAO;
    
    @BeforeEach
    void setup() {
        productDAO = new ProductDAOImpl();
    }
    
    @Test
    void testLoadAllProductTypes() throws IOException {
        Product product = new Product("Carpet",new BigDecimal("2.25"), new BigDecimal("2.10"));
        
        List<Product> products = productDAO.loadAllProductTypes();
        assertTrue(products.contains(product));
    }
}
