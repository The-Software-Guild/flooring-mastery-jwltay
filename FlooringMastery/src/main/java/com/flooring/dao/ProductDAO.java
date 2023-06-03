package com.flooring.dao;

import java.util.List;

import com.flooring.model.Product;

public interface ProductDAO {
	public List<Product> loadAllProductTypes();
}
