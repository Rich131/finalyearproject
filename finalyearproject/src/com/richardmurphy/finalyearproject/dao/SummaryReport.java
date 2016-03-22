package com.richardmurphy.finalyearproject.dao;

import java.util.ArrayList;
import java.util.List;

public class SummaryReport {

	private int simId;
	private String simName;
	private int countVG, countG, countAvg, countB, countVB, numDays;
	private double avgAht, avgAcw, avgNumCalls, avgFcr, avgCustSat, avgCallQuality;

	private List<DailyReport> reports;

	public SummaryReport() {

	}

	public SummaryReport(List<DailyReport> reports) {
		setReports(reports);

		calculateAverages();
		calculateCount();
	}

	private void calculateAverages() {
		int sumAht = 0, sumAcw = 0, sumNumCalls = 0;
		double sumFcr = 0, sumCustSat = 0, sumCallQuality = 0;

		int count = 0;

		// iterate through reports
		for (DailyReport report : reports) {
			sumAht += report.getAht();
			sumAcw += report.getAcw();
			sumNumCalls += report.getNumCalls();
			sumFcr += report.getFcr();
			sumCustSat += report.getCustSat();
			sumCallQuality += report.getCallQuality();

			count++;
		}

		setAvgAht((double)Math.round((sumAht / count) * 100d) / 100d);
		setAvgAcw((double)Math.round((sumAcw / count) * 100d) / 100d);
		setAvgCallQuality((double)Math.round((sumCallQuality / count) * 100d) / 100d);
		setAvgCustSat((double)Math.round((sumCustSat / count) * 100d) / 100d);
		setAvgFcr((double)Math.round((sumFcr / count) * 100d) / 100d);
		setAvgNumCalls((double)Math.round((sumNumCalls / count) * 100d) / 100d);

	}

	private void calculateCount() {
		
		ArrayList<Integer> uniqueEmployees = new ArrayList<Integer>();
		
		for (DailyReport report : reports) {
			
			// If array list doesn't already contain employee Id,
			// add Id to list, increment counter for corresponding 'classification'
			if ( !uniqueEmployees.contains(report.getEmployeeId()) ) {
				
				uniqueEmployees.add(report.getEmployeeId());
			
				switch (report.getSkillLevel()) {
					case 5:
						setCountVG(getCountVG() + 1);
						break;
					case 4:
						setCountG(getCountG() + 1);
						break;
					case 3:
						setCountAvg(getCountAvg() + 1);
						break;
					case 2:
						setCountB(getCountB() + 1);
						break;
					case 1:
						setCountVB(getCountVB() + 1);
						break;
					default:
						break;
				}
			}
		}
	}

	public List<DailyReport> getReports() {
		return reports;
	}

	public void setReports(List<DailyReport> reports) {
		this.reports = reports;
	}

	public double getAvgAcw() {
		return avgAcw;
	}

	public void setAvgAcw(double avgAcw) {
		this.avgAcw = avgAcw;
	}

	public int getSimId() {
		return simId;
	}

	public void setSimId(int simId) {
		this.simId = simId;
	}

	public double getAvgAht() {
		return avgAht;
	}

	public void setAvgAht(double avgAht) {
		this.avgAht = avgAht;
	}

	public double getAvgNumCalls() {
		return avgNumCalls;
	}

	public void setAvgNumCalls(double avgNumCalls) {
		this.avgNumCalls = avgNumCalls;
	}

	public int getCountVG() {
		return countVG;
	}

	public void setCountVG(int countVG) {
		this.countVG = countVG;
	}

	public int getCountG() {
		return countG;
	}

	public void setCountG(int countG) {
		this.countG = countG;
	}

	public int getCountAvg() {
		return countAvg;
	}

	public void setCountAvg(int countAvg) {
		this.countAvg = countAvg;
	}

	public int getCountB() {
		return countB;
	}

	public void setCountB(int countB) {
		this.countB = countB;
	}

	public int getCountVB() {
		return countVB;
	}

	public void setCountVB(int countVB) {
		this.countVB = countVB;
	}

	public int getNumDays() {
		return numDays;
	}

	public void setNumDays(int numDays) {
		this.numDays = numDays;
	}

	public double getAvgFcr() {
		return avgFcr;
	}

	public void setAvgFcr(double avgFcr) {
		this.avgFcr = avgFcr;
	}

	public double getAvgCustSat() {
		return avgCustSat;
	}

	public void setAvgCustSat(double avgCustSat) {
		this.avgCustSat = avgCustSat;
	}

	public double getAvgCallQuality() {
		return avgCallQuality;
	}

	public void setAvgCallQuality(double avgCallQuality) {
		this.avgCallQuality = avgCallQuality;
	}

	public String getSimName() {
		return simName;
	}

	public void setSimName(String simName) {
		this.simName = simName;
	}
	

}
