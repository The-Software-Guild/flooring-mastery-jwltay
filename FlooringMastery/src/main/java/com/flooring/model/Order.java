package com.flooring.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Order {
	private int orderNumber; //
	private String customerName; //
	private LocalDate orderDate; //
	private String state; //
	private BigDecimal taxRate;
	private String productType; //
	private BigDecimal area; //
	private BigDecimal costPerSquareFoot;
	private BigDecimal laborCostPerSquareFoot;
	private BigDecimal materialCost;
	private BigDecimal LaborCost;
	private BigDecimal tax;
	private BigDecimal total;
	
	public Order() {}

	public Order(
			int orderNumber,
			String customerName,
			LocalDate orderDate,
			String state,
			BigDecimal taxRate,
			String productType,
			BigDecimal area,
			BigDecimal costPerSquareFoot,
			BigDecimal laborCostPerSquareFoot,
			BigDecimal materialCost,
			BigDecimal laborCost,
			BigDecimal tax,
			BigDecimal total
			) {
		super();
		this.orderNumber = orderNumber;
		this.customerName = customerName;
		this.orderDate = orderDate;
		this.state = state;
		this.taxRate = taxRate;
		this.productType = productType;
		this.area = area;
		this.costPerSquareFoot = costPerSquareFoot;
		this.laborCostPerSquareFoot = laborCostPerSquareFoot;
		this.materialCost = materialCost;
		LaborCost = laborCost;
		this.tax = tax;
		this.total = total;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	public void setCostPerSquareFoot(BigDecimal costPerSquareFoot) {
		this.costPerSquareFoot = costPerSquareFoot;
	}

	public void setLaborCostPerSquareFoot(BigDecimal laborCostPerSquareFoot) {
		this.laborCostPerSquareFoot = laborCostPerSquareFoot;
	}

	public void setMaterialCost(BigDecimal materialCost) {
		this.materialCost = materialCost;
	}

	public void setLaborCost(BigDecimal laborCost) {
		LaborCost = laborCost;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public String getState() {
		return state;
	}

	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public String getProductType() {
		return productType;
	}

	public BigDecimal getArea() {
		return area;
	}

	public BigDecimal getCostPerSquareFoot() {
		return costPerSquareFoot;
	}

	public BigDecimal getLaborCostPerSquareFoot() {
		return laborCostPerSquareFoot;
	}

	public BigDecimal getMaterialCost() {
		return materialCost;
	}

	public BigDecimal getLaborCost() {
		return LaborCost;
	}

	public BigDecimal getTax() {
		return tax;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public void setArea(BigDecimal area) {
		this.area = area;
	}

	@Override
	public String toString() {
		return "Order [orderNumber=" + orderNumber + ", customerName=" + customerName + ", orderDate=" + orderDate
				+ ", state=" + state + ", taxRate=" + taxRate + ", productType=" + productType + ", area=" + area
				+ ", costPerSquareFoot=" + costPerSquareFoot + ", laborCostPerSquareFoot=" + laborCostPerSquareFoot
				+ ", materialCost=" + materialCost + ", LaborCost=" + LaborCost + ", tax=" + tax + ", total=" + total
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(LaborCost, area, costPerSquareFoot, customerName, laborCostPerSquareFoot, materialCost,
				orderDate, orderNumber, productType, state, tax, taxRate, total);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(LaborCost, other.LaborCost) && Objects.equals(area, other.area)
				&& Objects.equals(costPerSquareFoot, other.costPerSquareFoot)
				&& Objects.equals(customerName, other.customerName)
				&& Objects.equals(laborCostPerSquareFoot, other.laborCostPerSquareFoot)
				&& Objects.equals(materialCost, other.materialCost) && Objects.equals(orderDate, other.orderDate)
				&& orderNumber == other.orderNumber && Objects.equals(productType, other.productType)
				&& Objects.equals(state, other.state) && Objects.equals(tax, other.tax)
				&& Objects.equals(taxRate, other.taxRate) && Objects.equals(total, other.total);
	}
	
	
	
}
