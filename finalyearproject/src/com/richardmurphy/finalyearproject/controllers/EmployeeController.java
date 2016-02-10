package com.richardmurphy.finalyearproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.richardmurphy.finalyearproject.dao.Employee;
import com.richardmurphy.finalyearproject.services.HomeService;


@Controller
public class EmployeeController {
	
	private HomeService homeService;
	
	@Autowired
	public void setHomeService(HomeService homeService) {
		this.homeService = homeService;
	}
	
	@RequestMapping("/employees")
	public String showEmployees(Model model) {		
		List<Employee> employees = homeService.getEmployees();
		
		model.addAttribute("employees", employees);
		
		return "employee/employees";
	}
	
	@RequestMapping("/employee/create")
	public String createEmployee() {
		return "employee/create";
	}
	
	@RequestMapping(value="/employee/created", method=RequestMethod.POST)
	public String createdEmployee(Model model, Employee employee) {
		System.out.println(employee);
		
		return "employee/create";
	}
	
}
