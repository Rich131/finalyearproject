package com.richardmurphy.finalyearproject.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.richardmurphy.finalyearproject.dao.Employee;
import com.richardmurphy.finalyearproject.services.EmployeeService;
import com.richardmurphy.finalyearproject.services.HomeService;


@Controller
public class EmployeeController {
	
	private EmployeeService employeeService;
	
	@Autowired
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@RequestMapping("/employees")
	public String showEmployees(Model model, @Valid Employee employee) {		
		List<Employee> employees = employeeService.getEmployees();
		
		model.addAttribute("employees", employees);
		
		return "employees";
	}
	
	@RequestMapping(value="/employees/create", method=RequestMethod.GET)
	public String createEmployee(Model model, @ModelAttribute Employee employee) {
		
		model.addAttribute("employee", new Employee());
		
		return "employees";
	}
	
	@RequestMapping(value="/employees/create", method=RequestMethod.POST)
	public String createdEmployee(Model model, @Valid Employee employee, BindingResult result) {
		System.out.println(employee);
		
		if (result.hasErrors()) {
			System.out.println("Not valid.");
			
			List<ObjectError> errors = result.getAllErrors();
			
			for (ObjectError error : errors) {
				System.out.println(error.getDefaultMessage());
			}
			
			return "redirect:/employees";
		} else {
			System.out.println("Validated.");
			
			employeeService.create(employee);
			
			return "redirect:/employees";
		}
	}
	
}
