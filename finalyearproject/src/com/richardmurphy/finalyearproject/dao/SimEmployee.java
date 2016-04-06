package com.richardmurphy.finalyearproject.dao;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class SimEmployee {

	private int employeeId, simId;
	private Date startDate, leaveDate;
	private boolean isCurrentEmployee;
	
	// Simulation input metrics
	@Max(value=100, message="Value must be between 0-100")
	@Min(value=0, message="Value must be between 0-100")
	private int intelligence, patience, experience, motivation, empathy, initiative, communication;
	
	public SimEmployee() {
		isCurrentEmployee = true;
	}

	public SimEmployee(int employeeId, int simId, int intelligence, int patience, int experience, int motivation,
			int empathy, int initiative, int communication) {
		this.employeeId = employeeId;
		this.simId = simId;
		this.intelligence = intelligence;
		this.patience = patience;
		this.experience = experience;
		this.motivation = motivation;
		this.empathy = empathy;
		this.initiative = initiative;
		this.communication = communication;
		isCurrentEmployee = true;
	}

	public boolean getIsCurrentEmployee() {
		return isCurrentEmployee;
	}

	public void setIsCurrentEmployee(boolean isCurrentEmployee) {
		this.isCurrentEmployee = isCurrentEmployee;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getSimId() {
		return simId;
	}

	public void setSimId(int simId) {
		this.simId = simId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
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
		// capping increment
		if (experience > 100) {
			this.experience = 100;
		} else {
			this.experience = experience;
		}
	}

	public int getMotivation() {
		return motivation;
	}

	public void setMotivation(int motivation) {
		// capping decrement
		if (motivation < 0) {
			this.motivation = 0;
		} else {
			this.motivation = motivation;
		}
	}

	public int getEmpathy() {
		return empathy;
	}

	public void setEmpathy(int empathy) {
		this.empathy = empathy;
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

	@Override
	public String toString() {
		return "SimEmployee [employeeId=" + employeeId + ", simId=" + simId + ", startDate=" + startDate
				+ ", leaveDate=" + leaveDate + ", intelligence=" + intelligence + ", patience=" + patience
				+ ", experience=" + experience + ", motivation=" + motivation + ", empathy=" + empathy + ", initiative="
				+ initiative + ", communication=" + communication + "]";
	}
	
	

}
