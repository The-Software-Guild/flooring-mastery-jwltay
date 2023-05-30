package com.flooring.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.flooring.model.StateTax;

@Repository
public class StateTaxDAOImpl {
	public List<StateTax> loadAllStateTaxes() {
		List<StateTax> stateTaxes = new ArrayList<>();
		String filePath = "Data/Taxes.txt"; 
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
	                
	                StateTax tax = new StateTax(
	                		data[0],
	                		data[1],
	                		new BigDecimal(data[2])
	            			);
	                
	               stateTaxes.add(tax);
	            }
	            System.out.println("Taxes retrieved.");
	        } catch (IOException e) {
	            System.err.println("Error reading file: " + e.getMessage());
	        }
	        return stateTaxes;
	}
}
