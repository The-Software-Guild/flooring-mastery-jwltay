package com.flooring.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import com.flooring.model.Order;
import com.flooring.service.OrderService;
import com.flooring.service.ProductService;
import com.flooring.service.StateTaxService;
import com.flooring.view.FlooringView;
import com.flooring.view.UserIOImpl;

@Controller
public class FlooringController implements CommandLineRunner {
	@Autowired
	private FlooringView view;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ProductService productService;
	@Autowired
	private StateTaxService taxService;
	@Autowired
	private UserIOImpl io;
	
	public void runMenu() {
		view.showMenu();
		int choice = io.readInt("Select an option above:", 1, 5);
	
		switch (choice) {
		case 1:
			displayOrders();
			break;
		case 2:
			addOrder();
			break;
		case 3:
			editOrder();
			break;
		case 4:
			removeOrder();
			break;
		case 5:
			exitApp();
		}
	}
	
	public void displayOrders() {
		// get list of orders
		String date = io.readString("Enter the orders' date (MMDDYYYY):");
//		 send to view to display
		view.showOrders(orderService.getOrders(date));
		runMenu();
	}
	
	public void addOrder() {
		// get view to grab inputs
		String date = io.readString("Enter the order's date (MMDDYYYY):");
		String name = io.readString("Enter the customer's name:");
		String state = io.readString("Enter the 2-letter state abbreviation:");
		String product = io.readString("Enter the product to order:");
		BigDecimal area = new BigDecimal(io.readDouble("Enter the sq feet of flooring needed:", 100));
		
		// get confirmation
		io.print("Order date: " + date + ". Customer name: " + name + ". State: " + state + ". Product: " + product + ".");
		boolean save = io.getConfirmation("Save order? (y/n)");
		
		if (save) {
			// calculate data needed to create order
			BigDecimal taxRate = taxService.getStateTax(state);
			BigDecimal costPerSquareFoot = productService.getCostPerSquareFoot(product);
			BigDecimal laborCostPerSquareFoot = productService.getLaborCostPerSquareFoot(product);
			BigDecimal materialCost = orderService.getMaterialCost(area, costPerSquareFoot);
			BigDecimal laborCost = orderService.getLaborCost(area, laborCostPerSquareFoot);
			BigDecimal tax = orderService.getTax(materialCost, laborCost, taxRate);
			BigDecimal total = orderService.getTotal(materialCost, laborCost, tax);
			
			// send info to service to create order and return it
			Order order = orderService.createOrder(date, name, state, product, area, taxRate, costPerSquareFoot, laborCostPerSquareFoot, materialCost, laborCost, tax, total);
			
			// send order to view to display
			view.showOrder(order);
		}
		runMenu();
	}
	
	public void editOrder() {
		// get view to grab order date and order number if entered
		String date = io.readString("Enter the order's date (MMDDYYYY):");
		String orderNumber = io.readString("Enter the order number:");
		
		// get service to retrieve order
		Order order = orderService.getOrder(date, orderNumber);
		
		// get view to ask for inputs
		String name = io.readString("Enter the customer name (" + order.getCustomerName() + "):");
		name = name.isEmpty() ? order.getCustomerName() : name;
		String state = io.readString("Enter the order's state (" + order.getState() + "):");
		state = state.isEmpty() ? order.getState() : state;
		String product = io.readString("Enter the product to order (" + order.getProductType() + "):");
		product = product.isEmpty() ? order.getProductType() : product;
		String areaStr = io.readString("Enter the area of flooring needed (" + order.getArea() + "):");
		BigDecimal area = areaStr.isEmpty() ? order.getArea() : new BigDecimal(Double.valueOf(areaStr));
		
		// set new values
		order.setCustomerName(name);
		order.setState(state);
		order.setProductType(product);
		order.setArea(area);
		
		// show edited object
		view.showOrder(order);
		boolean save = io.getConfirmation("Save these changes? (y/n)");
		
		if (save) {
			// send info to service to edit said order and return it
			order = orderService.editOrder(date, orderNumber, order);
			// send order to view to display
			view.showOrder(order);
		}
		runMenu();
	}
	
	public void removeOrder() {
		// get view to grab order date and number
		String date = io.readString("Enter the order's date (MMDDYYYY):");
		String orderNumber = io.readString("Enter the order number:");
		
		// send info to service to delete order
		orderService.removeOrder(date, orderNumber);
		runMenu();
	}
	
	public void exitApp() {
		io.print("Goodbye!");
		System.exit(0);
	}
	
	@Override
	public void run(String... args) throws Exception {
		productService.loadProducts();
		taxService.loadTaxes();	
		runMenu();	
	}
	
}
