// Package
package com.richardmurphy.finalyearproject.dao;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

public class Employee {

	// Attributes
	private int employeeId;

	@Size(min = 2, max = 100, message = "Name must be between 2-100 characters")
	private String firstName;

	@Size(min = 2, max = 100, message = "Surname must be between 2-100 characters")
	private String surname;
	// private String startDate;

	@Email(message = "You must enter a valid email address.")
	String email;

	@Min(value = 0, message = "You must select a department.")
	private int departmentId;

	// Simulation input metrics
	@Max(value = 100, message = "Value must be between 0-100")
	@Min(value = 0, message = "Value must be between 0-100")
	private int intelligence, patience, experience, motivation, empathy, initiative, communication;

	// Constructors
	public Employee() {
	}

	public Employee(String firstName, String surname, String startDate, int departmentId) {
		this.firstName = firstName;
		this.surname = surname;
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

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getInitiative() {
		return initiative;
	}

	public void setInitiative(int initiative) {
		this.initiative = initiative;
	}

	public int getCommunication() {
		return communication;
	}

	public void setCommunication(int communication) {
		this.communication = communication;
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
		if (experience > 100)
			this.experience = 100;
		else
			this.experience = experience;
	}

	public int getMotivation() {
		return motivation;
	}

	public void setMotivation(int motivation) {
		if (motivation > 0)
			this.motivation = motivation;
		else
			this.motivation = 0;
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
				+ ", departmentId=" + departmentId + ", intelligence=" + intelligence + ", patience=" + patience
				+ ", experience=" + experience + ", motivation=" + motivation + ", empathy=" + empathy + "]";
	}
}
