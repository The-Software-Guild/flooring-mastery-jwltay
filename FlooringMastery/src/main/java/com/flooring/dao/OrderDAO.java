package com.flooring.dao;

import java.util.List;

import com.flooring.model.Order;

public interface OrderDAO {
	public List<Order> loadAllOrders(String date);
	public Order loadOrder(String date, String orderNumber);
	public void saveOrder(Order order);
	public void deleteOrder(String date, String orderNumber);
	public int incrementOrderNumber();
	public void updateOrder(String date, String orderNumber, Order order); 
}
