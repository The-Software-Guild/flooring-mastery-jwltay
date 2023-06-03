package com.flooring.dao;

import com.flooring.model.StateTax;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


class StateTaxDAOImplTest {
    private StateTaxDAOImpl stateTaxDAO;

    @BeforeEach
    void setUp() {
        stateTaxDAO = new StateTaxDAOImpl();
    }

    @Test
    void loadAllStateTaxes_shouldLoadStateTaxesFromFile() throws IOException {
        StateTax tax = new StateTax("WA", "Washington", new BigDecimal("9.25"));
        
        System.out.println(tax);
        
        List<StateTax> taxes = stateTaxDAO.loadAllStateTaxes();
        assertTrue(taxes.contains(tax));
    }
}

