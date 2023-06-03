package com.flooring.dao;

import java.util.List;

import com.flooring.model.StateTax;

public interface StateTaxDAO {
	public List<StateTax> loadAllStateTaxes();
}
