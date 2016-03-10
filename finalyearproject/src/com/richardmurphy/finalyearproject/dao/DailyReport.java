package com.richardmurphy.finalyearproject.dao;

public class DailyReport {

	private String date;
	private int employeeId, simId, skillLevel;
	private int aht, acw, custSat, callQuality, fcr, numCalls;

	// constructor
	public DailyReport() {

	}

	public int getSkillLevel() {
		return skillLevel;
	}

	public void setSkillLevel(int skillLevel) {
		this.skillLevel = skillLevel;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getSimId() {
		return simId;
	}

	public void setSimId(int simId) {
		this.simId = simId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getAht() {
		return aht;
	}

	public void setAht(int aht) {
		this.aht = aht;
	}

	public int getAcw() {
		return acw;
	}

	public void setAcw(int acw) {
		this.acw = acw;
	}

	public int getCustSat() {
		return custSat;
	}

	public void setCustSat(int custSat) {
		this.custSat = custSat;
	}

	public int getCallQuality() {
		return callQuality;
	}

	public void setCallQuality(int callQuality) {
		this.callQuality = callQuality;
	}

	public int getFcr() {
		return fcr;
	}

	public void setFcr(int fcr) {
		this.fcr = fcr;
	}

	public int getNumCalls() {
		return numCalls;
	}

	public void setNumCalls(int numCalls) {
		this.numCalls = numCalls;
	}
}
