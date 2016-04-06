package com.richardmurphy.finalyearproject.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DailyReport {

	private Date date;
	private int employeeId, simId, skillLevel;
	private int aht, acw, custSat, callQuality, fcr, numCalls;
	private String dateDayMonth;

	// constructor
	public DailyReport() {
		
	}
	
	public void setDateDayMonth() {
		this.dateDayMonth = new SimpleDateFormat("DD/MM").format(date);
	}
	
	public String getDateDayMonth() {
		return dateDayMonth;
	}

	public void setDateDayMonth(String dateDayMonth) {
		this.dateDayMonth = dateDayMonth;
	}

	public int getSkillLevel() {
		return skillLevel;
	}

	public void setSkillLevel(int skillLevel) {
		this.skillLevel = skillLevel;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
		setDateDayMonth();
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

	@Override
	public String toString() {
		return "DailyReport [date=" + date + ", employeeId=" + employeeId + ", simId=" + simId + ", aht=" + aht
				+ ", acw=" + acw + ", custSat=" + custSat + ", callQuality=" + callQuality + ", fcr=" + fcr
				+ ", numCalls=" + numCalls + "]\n";
	}
	
	
}
