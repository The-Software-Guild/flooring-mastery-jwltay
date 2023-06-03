package com.flooring.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.flooring.dao.OrderDAOImpl;
import com.flooring.model.Order;

class OrderDAOImplTest {
    private OrderDAOImpl orderDAO;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");

    
    @BeforeEach
    void setup() {
        orderDAO = new OrderDAOImpl();
        
    }
    
    @Test
    void testLoadAllOrders() throws IOException {
    	Order order = new Order(
    			6,
    			"Bomb Voyage",
    			LocalDate.parse("02242020", formatter),
    			"WA",
    			new BigDecimal("9.25"),
    			"Wood",
    			new BigDecimal("456"),
    			new BigDecimal("5.15"),
    			new BigDecimal("4.75"),
    			new BigDecimal("2348.40"),
    			new BigDecimal("2166.00"),
    			new BigDecimal("417.582000"),
    			new BigDecimal("4931.982000")
    			);
    	
        List<Order> orders = orderDAO.loadAllOrders("02242020");
        
        assertTrue(orders.contains(order));
    }
    
    @Test
    void testLoadOrder() throws IOException {
    	Order order = new Order(
    			6,
    			"Bomb Voyage",
    			LocalDate.parse("02242020", formatter),
    			"WA",
    			new BigDecimal("9.25"),
    			"Wood",
    			new BigDecimal("456"),
    			new BigDecimal("5.15"),
    			new BigDecimal("4.75"),
    			new BigDecimal("2348.40"),
    			new BigDecimal("2166.00"),
    			new BigDecimal("417.582000"),
    			new BigDecimal("4931.982000")
    			);
        
        assertTrue(orderDAO.loadOrder("02242020", "6").equals(order));
    }
    
    @Test
    void testSaveOrder() throws IOException {
    	Order order = new Order(
    			6,
    			"Bomb Voyage",
    			LocalDate.parse("03242020", formatter),
    			"WA",
    			new BigDecimal("9.25"),
    			"Wood",
    			new BigDecimal("456"),
    			new BigDecimal("5.15"),
    			new BigDecimal("4.75"),
    			new BigDecimal("2348.40"),
    			new BigDecimal("2166.00"),
    			new BigDecimal("417.582"),
    			new BigDecimal("4931.982")
    			);
    	
        orderDAO.saveOrder(order);
        
        assertTrue(orderDAO.loadAllOrders("03242020").contains(order));
        
    }
  

    @Test
    void testDeleteOrder() throws IOException {
    	Order order = new Order(
    			10,
    			"Bone Voyage",
    			LocalDate.parse("03242020", formatter),
    			"WA",
    			new BigDecimal("9.25"),
    			"Wood",
    			new BigDecimal("456"),
    			new BigDecimal("5.15"),
    			new BigDecimal("4.75"),
    			new BigDecimal("2348.40"),
    			new BigDecimal("2166.00"),
    			new BigDecimal("417.582"),
    			new BigDecimal("4931.982")
    			);
    	
    	orderDAO.saveOrder(order);
    	orderDAO.deleteOrder("03242020", "10");
    	
    	assertFalse(orderDAO.loadAllOrders("03242020").contains(order));
    }
    
    @Test
    void testUpdateOrder() throws IOException {
    	Order order = new Order(
    			11,
    			"Bone Voyage",
    			LocalDate.parse("06242020", formatter),
    			"WA",
    			new BigDecimal("9.25"),
    			"Wood",
    			new BigDecimal("456"),
    			new BigDecimal("5.15"),
    			new BigDecimal("4.75"),
    			new BigDecimal("2348.40"),
    			new BigDecimal("2166.00"),
    			new BigDecimal("417.582"),
    			new BigDecimal("4931.982")
    			);
    	orderDAO.saveOrder(order);
    	
    	Order newOrder = new Order(
    			11,
    			"Glasspipe Voyage",
    			LocalDate.parse("06242020", formatter),
    			"WA",
    			new BigDecimal("9.25"),
    			"Wood",
    			new BigDecimal("456"),
    			new BigDecimal("5.15"),
    			new BigDecimal("4.75"),
    			new BigDecimal("2348.40"),
    			new BigDecimal("2166.00"),
    			new BigDecimal("417.582"),
    			new BigDecimal("4931.982")
    			);
    	
    	orderDAO.updateOrder("06242020", "11", newOrder);
    	
    	assertEquals(orderDAO.loadOrder("06242020", "11"), newOrder);
    	
    }
    
}
