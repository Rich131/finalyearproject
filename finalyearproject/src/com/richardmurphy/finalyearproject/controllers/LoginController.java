package com.richardmurphy.finalyearproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.richardmurphy.finalyearproject.dao.Employee;
import com.richardmurphy.finalyearproject.services.EmployeeService;

@Controller
public class LoginController {
	
	private EmployeeService employeeService;
	
	@Autowired
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@RequestMapping("/login")
	public String showLogin() {
		return "login";
	}
	
	@RequestMapping("/create")
	public String showCreate(Model model) {
		
		List<Employee> unregisteredEmployees = employeeService.getUnregisteredEmployees();
		
		model.addAttribute("employees", unregisteredEmployees);
		
		return "create";
	}
	
}
