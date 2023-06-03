package com.flooring.view;

import java.util.List;

import org.springframework.stereotype.Component;

import com.flooring.model.Order;

@Component
public class FlooringView {
	public void showMenu() {
		System.out.println("<<FLooring Program>>");
		System.out.println("1. Display Orders");
		System.out.println("2. Add an Order");
		System.out.println("3. Edit an Order");
		System.out.println("4. Remove an Order");
		System.out.println("5. Quit");
	}
	
	public void showOrder(Order order) {
		System.out.println("Order: " + order);
	}
	
	public void showOrders(List<Order> orders) {
		orders.forEach(order -> System.out.println(order));
	}
}
