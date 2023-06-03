package com.flooring.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Product{
	private String productType;
	private BigDecimal costPerSquareFoot;
	private BigDecimal laborCostPerSquareFoot;
	
	public Product () {}

	public Product(String productType, BigDecimal costPerSquareFoot, BigDecimal laborCostPerSquareFoot) {
		super();
		this.productType = productType;
		this.costPerSquareFoot = costPerSquareFoot;
		this.laborCostPerSquareFoot = laborCostPerSquareFoot;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public BigDecimal getCostPerSquareFoot() {
		return costPerSquareFoot;
	}

	public void setCostPerSquareFoot(BigDecimal costPerSquareFoot) {
		this.costPerSquareFoot = costPerSquareFoot;
	}

	public BigDecimal getLaborCostPerSquareFoot() {
		return laborCostPerSquareFoot;
	}

	public void setLaborCostPerSquareFoot(BigDecimal laborCostPerSquareFoot) {
		this.laborCostPerSquareFoot = laborCostPerSquareFoot;
	}

	@Override
	public String toString() {
		return "Product [productType=" + productType + ", costPerSquareFoot=" + costPerSquareFoot
				+ ", laborCostPerSquareFoot=" + laborCostPerSquareFoot + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(costPerSquareFoot, laborCostPerSquareFoot, productType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(costPerSquareFoot, other.costPerSquareFoot)
				&& Objects.equals(laborCostPerSquareFoot, other.laborCostPerSquareFoot)
				&& Objects.equals(productType, other.productType);
	}
	
}
