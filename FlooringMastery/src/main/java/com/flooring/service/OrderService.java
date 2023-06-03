package com.flooring.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flooring.dao.OrderDAO;
import com.flooring.dao.OrderDAOImpl;
import com.flooring.model.Order;

@Service
public class OrderService {
	
	@Autowired
	private OrderDAOImpl orderDAOImpl;
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");

	public void setOrderDAOImpl(OrderDAOImpl orderDAOImpl) {
		this.orderDAOImpl = orderDAOImpl;
	}

	public List<Order> getOrders(String date) {
		return orderDAOImpl.loadAllOrders(date);
	}
	
	public Order createOrder(String date, String name, String state, String product, BigDecimal area, BigDecimal taxRate, BigDecimal costPerSquareFoot, BigDecimal laborCostPerSquareFoot, BigDecimal materialCost, BigDecimal laborCost, BigDecimal tax, BigDecimal total) {		
		// create order
		Order order = new Order(
				orderDAOImpl.incrementOrderNumber(),
				name,
				LocalDate.parse(date, formatter),
				state,
				taxRate,
				product,
				area,
				costPerSquareFoot, 
				laborCostPerSquareFoot,
				materialCost, 
				laborCost, 
				tax, 
				total);
		
		// save order
		orderDAOImpl.saveOrder(order);
		
		// return order so it can be displayed by view
		return order;
	}
	
	public void removeOrder(String date, String orderNumber) {
		orderDAOImpl.deleteOrder(date, orderNumber);
	}
	
	public Order getOrder(String date, String orderNumber) {
		return orderDAOImpl.loadOrder(date, orderNumber);
	}
	
	public Order editOrder(String date, String orderNumber, Order order) {
		orderDAOImpl.updateOrder(date, orderNumber, order);
		return orderDAOImpl.loadOrder(date, orderNumber);
	}
	
	public BigDecimal getMaterialCost(BigDecimal area, BigDecimal costPerSquareFoot) {
		return area.multiply(costPerSquareFoot);
	}
	
	public BigDecimal getLaborCost(BigDecimal area, BigDecimal laborCostPerSquareFoot) {
		return area.multiply(laborCostPerSquareFoot);
	}
	
	public BigDecimal getTax(BigDecimal materialCost, BigDecimal laborCost, BigDecimal taxRate) {
		return (materialCost.add(laborCost)).multiply(taxRate.divide(new BigDecimal(100)));
	}
	
	public BigDecimal getTotal(BigDecimal materialCost, BigDecimal laborCost, BigDecimal tax) {
		return materialCost.add(laborCost).add(tax);
	}

}
