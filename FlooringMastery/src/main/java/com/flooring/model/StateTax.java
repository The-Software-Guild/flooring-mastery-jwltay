package com.flooring.model;

import java.math.BigDecimal;
import java.util.Objects;

public class StateTax {
	private String state;
	private String stateName;
	private BigDecimal taxRate;
	
	public StateTax() {}

	public StateTax(String state, String stateName, BigDecimal taxRate) {
		super();
		this.state = state;
		this.stateName = stateName;
		this.taxRate = taxRate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	@Override
	public String toString() {
		return "StateTax [state=" + state + ", stateName=" + stateName + ", taxRate=" + taxRate + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(state, stateName, taxRate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StateTax other = (StateTax) obj;
		return Objects.equals(state, other.state) && Objects.equals(stateName, other.stateName)
				&& Objects.equals(taxRate, other.taxRate);
	}
	
}
