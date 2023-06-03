package com.flooring.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.flooring.dao.ProductDAOImpl;
import com.flooring.model.Product;

class ProductServiceTest {
	
	private ProductDAOImpl productDAO;
	private ProductService service;
	
	@BeforeEach
	void setUp() {
		productDAO = new ProductDAOImpl();
		service = new ProductService();
		service.setProductDAOImpl(productDAO);
	}

	@Test
	void testLoadProducts() {
		Product product = new Product("Carpet", new BigDecimal("2.25"), new BigDecimal("2.10"));
		service.loadProducts();
		
		assertTrue(service.getProducts().contains(product));
	}
	
	@Test
	void testGetCostPerSquareFoot() {
		service.loadProducts();
		
		assertEquals(service.getCostPerSquareFoot("Carpet"), new BigDecimal("2.25"));
	}
	
	@Test
	void testGetLaborCostPerSquareFoot() {
		service.loadProducts();
		
		assertEquals(service.getLaborCostPerSquareFoot("Carpet"), new BigDecimal("2.10"));
	}

}
