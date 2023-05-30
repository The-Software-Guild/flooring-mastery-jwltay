package com.flooring.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.flooring.dao.StateTaxDAOImpl;
import com.flooring.model.StateTax;

class StateTaxServiceTest {
	
	private StateTaxDAOImpl taxDAO;
	private StateTaxService service;

	@BeforeEach
	void setUp() {
		taxDAO = new StateTaxDAOImpl();
		service = new StateTaxService();
		service.setStateTaxDAOImpl(taxDAO);
	}
	
	
	@Test
	void testLoadTaxes() {
		StateTax tax = new StateTax("TX", "Texas", new BigDecimal("4.45"));
		service.loadTaxes();
		System.out.println(service.getTaxes());
		
		assertTrue(service.getTaxes().contains(tax));
	}
	
	@Test
	void testGetStateTax() {
		service.loadTaxes();
		
		assertEquals(service.getStateTax("TX"), new BigDecimal("4.45"));
	}

}
