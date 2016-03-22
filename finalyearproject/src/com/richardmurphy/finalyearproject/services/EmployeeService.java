package com.richardmurphy.finalyearproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.richardmurphy.finalyearproject.dao.Employee;
import com.richardmurphy.finalyearproject.dao.EmployeesDAO;

@Service("employeeService")
public class EmployeeService {
	private EmployeesDAO employeesDao;
	
	@Autowired
	public void setEmployeesDao(EmployeesDAO employeesDao) {
		this.employeesDao = employeesDao;
	}

	public List<Employee> getEmployees() {
		return employeesDao.getEmployees();
	}
	
	public void create(Employee employee) {
		employeesDao.create(employee);
	}

	public List<Employee> getUnregisteredEmployees() {
		return employeesDao.getUnregisteredEmployees();
	}

	public List<Integer> getEmployeesNotInSim(int simId, int limit) {
		return employeesDao.getEmployeesNotInSim(simId, limit);
	}

	public List<Employee> getEmployeesBySimId(int simId) {
		return employeesDao.getEmployeesNotInSim(simId);
	}
}
