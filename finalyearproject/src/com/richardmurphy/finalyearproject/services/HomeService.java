package com.richardmurphy.finalyearproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.richardmurphy.finalyearproject.dao.Employee;
import com.richardmurphy.finalyearproject.dao.EmployeesDAO;

@Service("homeService")
public class HomeService {

	private EmployeesDAO employeesDao;
	
	@Autowired
	public void setEmployeesDao(EmployeesDAO employeesDao) {
		this.employeesDao = employeesDao;
	}

	public List<Employee> getEmployees() {
		return employeesDao.getEmployees();
	}
}
