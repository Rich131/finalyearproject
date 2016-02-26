// Package
package com.richardmurphy.finalyearproject.dao;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

public class Employee {

	// Attributes
	private int employeeId;
	
	@Size(min=2, max=100, message="Name must be between 2-100 characters")
	private String firstName;
	
	@Size(min=2, max=100, message="Surname must be between 2-100 characters")
	private String surname;
	private String startDate;
	
	@Email(message="You must enter a valid email address.") 
	String email;
	
	@Min(value=0, message="You must select a department.")
	private int departmentId;

	// Simulation input metrics
	@Max(value=100, message="Value must be between 0-100")
	@Min(value=0, message="Value must be between 0-100")
	private int intelligence, patience, experience, motivation, empathy;

	// Constructors
	public Employee() {
	}

	public Employee(String firstName, String surname, String startDate, int departmentId) {
		this.firstName = firstName;
		this.surname = surname;
		this.startDate = startDate;
		this.departmentId = departmentId;
	}

	// Getters and Setters

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public int getPatience() {
		return patience;
	}

	public void setPatience(int patience) {
		this.patience = patience;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public int getMotivation() {
		return motivation;
	}

	public void setMotivation(int motivation) {
		this.motivation = motivation;
	}

	public int getEmpathy() {
		return empathy;
	}

	public void setEmpathy(int empathy) {
		this.empathy = empathy;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", firstName=" + firstName + ", surname=" + surname
				+ ", startDate=" + startDate + ", departmentId=" + departmentId + ", intelligence=" + intelligence
				+ ", patience=" + patience + ", experience=" + experience + ", motivation=" + motivation + ", empathy="
				+ empathy + "]";
	}
}
