package com.richardmurphy.finalyearproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.richardmurphy.finalyearproject.dao.Employee;
import com.richardmurphy.finalyearproject.dao.EmployeesDAO;
import com.richardmurphy.finalyearproject.dao.SimEmployee;
import com.richardmurphy.finalyearproject.dao.SimEmployeesDAO;

@Service("simEmployeeService")
public class SimEmployeeService {
	private SimEmployeesDAO simEmployeesDao;
	
	@Autowired
	public void setSimEmployeesDao(SimEmployeesDAO simEmployeesDao) {
		this.simEmployeesDao = simEmployeesDao;
	}

	public List<SimEmployee> getSimEmployees(int simId) {
		return simEmployeesDao.getSimEmployees(simId);
	}
	
	public void create(List<SimEmployee> simEmployees) {
		simEmployeesDao.create(simEmployees);
	}

	public void create(SimEmployee simEmp) {
		simEmployeesDao.create(simEmp);
	}

	public int[] update(List<SimEmployee> simEmployees) {
		return simEmployeesDao.update(simEmployees);
	}

}
