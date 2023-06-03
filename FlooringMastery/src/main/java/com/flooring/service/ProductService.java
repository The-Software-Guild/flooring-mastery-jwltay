package com.flooring.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flooring.dao.ProductDAO;
import com.flooring.dao.ProductDAOImpl;
import com.flooring.model.Product;

@Service
public class ProductService {
	
	@Autowired
	private ProductDAOImpl productDAOImpl;
	private List<Product> products;
	
	
	public void setProductDAOImpl(ProductDAOImpl productDAOImpl) {
		this.productDAOImpl = productDAOImpl;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void loadProducts() {
		products = productDAOImpl.loadAllProductTypes();
	}
	
	public BigDecimal getCostPerSquareFoot(String product) {
		Map<String, BigDecimal> costByProduct = new HashMap<>();
		
		products.forEach(p -> costByProduct.put(p.getProductType(), p.getCostPerSquareFoot()));
		
		return costByProduct.get(product);
	}
	
	public BigDecimal getLaborCostPerSquareFoot(String product) {
		Map<String, BigDecimal> laborCostByProduct = new HashMap<>();
		
		products.forEach(p -> laborCostByProduct.put(p.getProductType(), p.getLaborCostPerSquareFoot()));
		
		return laborCostByProduct.get(product);
	}
	
}
