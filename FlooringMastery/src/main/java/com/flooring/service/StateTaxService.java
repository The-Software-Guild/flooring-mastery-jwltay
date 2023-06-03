package com.flooring.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flooring.dao.StateTaxDAO;
import com.flooring.dao.StateTaxDAOImpl;
import com.flooring.model.StateTax;

@Service
public class StateTaxService {
	@Autowired
	private StateTaxDAOImpl stateTaxDAOImpl;
	private List<StateTax> taxes;

	public List<StateTax> getTaxes() {
		return taxes;
	}

	public void setStateTaxDAOImpl(StateTaxDAOImpl stateTaxDAOImpl) {
		this.stateTaxDAOImpl = stateTaxDAOImpl;
	}
	
	public void loadTaxes() {
		taxes = stateTaxDAOImpl.loadAllStateTaxes();
	}
	
	public BigDecimal getStateTax(String state) {
		Map<String, BigDecimal> taxesByState = new HashMap<>();
		
		taxes.forEach(tax -> taxesByState.put(tax.getState(), tax.getTaxRate()));
		
		return taxesByState.get(state);
	}
}
