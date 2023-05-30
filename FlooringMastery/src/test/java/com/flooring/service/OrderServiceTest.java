package com.flooring.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.flooring.dao.OrderDAOImpl;
import com.flooring.model.Order;

class OrderServiceTest {
	
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
	private OrderDAOImpl orderDAO;
	private OrderService service;

	@BeforeEach
	void setp() {
		orderDAO = new OrderDAOImpl();
		service = new OrderService();
		service.setOrderDAOImpl(orderDAO);
	}
	
	@Test
	void testGetOrders() {
		assertEquals(service.getOrders("06012013"), orderDAO.loadAllOrders("06012013"));
	}
	
	@Test
	void testCreateOrder() {
		
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
		
		Order newOrder = service.createOrder(
				"04062010", 
				"New Order",
				"WA",
				"Wood", 
				new BigDecimal("456"), 
				new BigDecimal("9.25"),
				new BigDecimal("5.15"), 
				new BigDecimal("4.75"), 
				new BigDecimal("2348.40"), 
				new BigDecimal("2166.00"), 
				new BigDecimal("417.582"), 
				new BigDecimal("4931.982")
				);
		
		assertEquals(newOrder.getTotal(), order.getTotal());
	}
	
	@Test
	void testRemoveOrder() {
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
    	service.removeOrder("03242020", "10");
    	
    	assertFalse(service.getOrders("03242020").contains(order));
	}
	
	@Test
	void testGetOrder() {

		Order order = new Order(
    			6,
    			"Bomb Voyage",
    			LocalDate.parse("04062010", formatter),
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
		
		assertEquals(order.getTotal(), service.getOrder("04062010", "10").getTotal());
	}
	
	@Test
	void testEditOrder() {
		Order order = new Order(
    			20,
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
    			20,
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
    	
    	service.editOrder("06242020", "20", newOrder);
    	
    	assertEquals(orderDAO.loadOrder("06242020", "20"), newOrder);
	}
	
	@Test
	void testGetMaterialCost() {
		BigDecimal area = new BigDecimal("100");
		BigDecimal costPerSquareFoot = new BigDecimal("4.25");
		BigDecimal actual = new BigDecimal("425.00");
		
		assertEquals(actual, service.getMaterialCost(area, costPerSquareFoot));
	}
	
	@Test
	void testGetLaborCost() {
		BigDecimal area = new BigDecimal("100");
		BigDecimal laborCostPerSquareFoot = new BigDecimal("5.00");
		BigDecimal actual = new BigDecimal("500.00");
		
		assertEquals(actual, service.getLaborCost(area, laborCostPerSquareFoot));
	}
	
	@Test
	void testGetTax() {
		BigDecimal materialCost = new BigDecimal("425");
		BigDecimal laborCost = new BigDecimal("500");
		BigDecimal taxRate = new BigDecimal("10");
		BigDecimal actual = new BigDecimal("92.5");
		
		assertEquals(actual, service.getTax(materialCost, laborCost, taxRate));
	}
	
	@Test
	void testGetTotal() {
		BigDecimal materialCost = new BigDecimal("425");
		BigDecimal laborCost = new BigDecimal("500");
		BigDecimal tax = new BigDecimal("92.5");
		BigDecimal actual = new BigDecimal("1017.5");
		
		assertEquals(actual, service.getTotal(materialCost, laborCost, tax));
	}

}
