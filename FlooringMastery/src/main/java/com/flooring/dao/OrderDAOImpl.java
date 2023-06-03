package com.flooring.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.flooring.model.Order;

@Repository
public class OrderDAOImpl implements OrderDAO {
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
	
	public List<Order> loadAllOrders(String date) {
		List<Order> orders = new ArrayList<>();
		String filePath = "Orders/Orders_" + date + ".txt"; // throw exception if file doesnt exist
	    	String delimiter = ","; 

	        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
	            String line;
	            boolean firstLine = true;
	            
	            while ((line = reader.readLine()) != null) {
	                if (firstLine) {
	                    firstLine = false;
	                    continue;
	                }
	            	
	                String[] data = line.split(delimiter);
	                
	                List<String> list = Arrays.asList(data);
	                
	                Order order = new Order(
	                		Integer.valueOf(data[0]),
	                		data[1], 
	                		LocalDate.parse(date, formatter), 
	                		data[2], 
	                		new BigDecimal(data[3]),
	            			data[4], 
	            			new BigDecimal(data[5]), 
	            			new BigDecimal(data[6]), 
	            			new BigDecimal(data[7]),
	            			new BigDecimal(data[8]), 
	            			new BigDecimal(data[9]), 
	            			new BigDecimal(data[10]), 
	            			new BigDecimal(data[11])
	            			);
	                
	               orders.add(order);
	            }
	            
	            System.out.println("Orders retrieved.");
	        } catch (IOException e) {
	            System.err.println("Error reading file: " + e.getMessage());
	        }
	        return orders;
	}
	
	public Order loadOrder(String date, String orderNumber) {
			String filePath = "Orders/Orders_" + date + ".txt"; // throw exception if file doesnt exist
		    	String delimiter = ","; 
		    	Order order = null;
		    	File file = new File(filePath);
		        
		    	if (file.exists()) {
			        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			            String line;
			            
			            while ((line = reader.readLine()) != null) {
			                String[] data = line.split(delimiter);
			                
			                if (data[0].equals(orderNumber)) {
				                order = new Order(
				                		Integer.valueOf(data[0]),
				                		data[1], 
				                		LocalDate.parse(date, formatter), 
				                		data[2], 
				                		new BigDecimal(data[3]),
				            			data[4], 
				            			new BigDecimal(data[5]), 
				            			new BigDecimal(data[6]), 
				            			new BigDecimal(data[7]),
				            			new BigDecimal(data[8]), 
				            			new BigDecimal(data[9]), 
				            			new BigDecimal(data[10]), 
				            			new BigDecimal(data[11])
				            			);
			                }
			            }
			            System.out.println("Order retrieved.");
			        } catch (IOException e) {
			            System.err.println("Error reading file: " + e.getMessage());
			        }
		    	}
		    	else
		    		System.out.println("No orders on that date.");
		        return order; // check if null
	}
	
	public void saveOrder(Order order) {
		String filePath = "Orders/Orders_" + order.getOrderDate().format(formatter) + ".txt";
        String delimiter = ",";
        File file = new File(filePath);
        boolean fileExists = file.exists();
        

	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
	        if (!fileExists) {
	        	writer.write("OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total");
	        	writer.newLine();
	        }
            writer.write(
            		order.getOrderNumber() + delimiter + 
            		order.getCustomerName() + delimiter + 
            		order.getState() + delimiter + 
            		order.getTaxRate() + delimiter + 
            		order.getProductType() + delimiter + 
            		order.getArea() + delimiter + 
            		order.getCostPerSquareFoot() + delimiter + 
            		order.getLaborCostPerSquareFoot() + delimiter + 
            		order.getMaterialCost() + delimiter + 
            		order.getLaborCost() + delimiter + 
            		order.getTax() + delimiter + 
            		order.getTotal()
            		);
            writer.newLine();
            
            System.out.println("Orders saved.");
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
	} 
	
	public void deleteOrder(String date, String orderNumber) {
		String filePath = "Orders/Orders_" + date + ".txt";
        String tempFilePath = "Orders/temp.txt";
        String delimiter = ",";
        File file = new File(filePath);
        
        if (file.exists()) {
	        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
	             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFilePath))) {
	            
	            String line;
	            while ((line = reader.readLine()) != null) {
	                String[] data = line.split(delimiter);
	                String order = data[0];
	                
	                if (!order.equals(orderNumber)) {
	                    writer.write(line);
	                    writer.newLine();
	                }
	            }
	            // delete original file
	            Files.delete(Paths.get(filePath));
	            
	            // rename temp file to the original file name
	            Files.move(Paths.get(tempFilePath), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
	            
	            System.out.println("Deleted order " + orderNumber + ".");
	        } catch (IOException e) {
	            System.err.println("Error reading or writing the file: " + e.getMessage());
	        }
        }
        else
        	System.out.println("No orders for the entered date.");
    }
	
	public void updateOrder(String date, String orderNumber, Order order) {
		String filePath = "Orders/Orders_" + date + ".txt";
        String tempFilePath = "Orders/temp.txt";
        String delimiter = ",";
        
        String newOrder = order.getOrderNumber() + delimiter + 
        		order.getCustomerName() + delimiter + 
        		order.getState() + delimiter + 
        		order.getTaxRate() + delimiter + 
        		order.getProductType() + delimiter + 
        		order.getArea() + delimiter + 
        		order.getCostPerSquareFoot() + delimiter + 
        		order.getLaborCostPerSquareFoot() + delimiter + 
        		order.getMaterialCost() + delimiter + 
        		order.getLaborCost() + delimiter + 
        		order.getTax() + delimiter + 
        		order.getTotal();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFilePath))) {
            
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(delimiter);
                String orderId = data[0];
                
                if (!orderId.equals(orderNumber)) {
                    writer.write(line);
                    writer.newLine();
                }
                else if (orderId.equals(orderNumber)){
                	writer.write(newOrder);
                	writer.newLine();
                }
                else {
    	        	writer.write("OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total");
    	        	writer.newLine();
                }
            }
            // delete original file
            Files.delete(Paths.get(filePath));
            
            // rename temp file to the original file name
            Files.move(Paths.get(tempFilePath), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
            
            System.out.println("Deleted order " + orderNumber + ".");
        } catch (IOException e) {
            System.err.println("Error reading or writing the file: " + e.getMessage());
        }
	}

	public int incrementOrderNumber() {
		int orderNumber = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("Data/OrderNumber.txt"));) 
        {
        	orderNumber = Integer.valueOf(reader.readLine());
            orderNumber++;
            
            BufferedWriter writer = new BufferedWriter(new FileWriter("Data/OrderNumber.txt", false));
            writer.write(String.valueOf(orderNumber));
            writer.close();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return orderNumber;
	}
	
}

