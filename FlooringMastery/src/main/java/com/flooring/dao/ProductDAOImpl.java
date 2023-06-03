package com.flooring.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.flooring.model.Product;

@Repository
public class ProductDAOImpl {
	public List<Product> loadAllProductTypes() {
		List<Product> products = new ArrayList<>();
		String filePath = "Data/Products.txt"; // throw exception if file doesnt exist
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
	                
	                Product product = new Product(
	                		data[0],
	                		new BigDecimal(data[1]),
	                		new BigDecimal(data[2])
	            			);
	                
	               products.add(product);
	            }
	            System.out.println("Products retrieved.");
	        } catch (IOException e) {
	            System.err.println("Error reading file: " + e.getMessage());
	        }
	        return products;
	}
	
}
