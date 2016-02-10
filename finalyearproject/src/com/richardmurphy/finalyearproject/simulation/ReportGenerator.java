package com.richardmurphy.finalyearproject.simulation;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.joda.time.LocalDate;

import com.richardmurphy.finalyearproject.dao.DailyReport;
import com.richardmurphy.finalyearproject.dao.DailyReportDAO;
import com.richardmurphy.finalyearproject.dao.Employee;

public class ReportGenerator {

	private List<List<DailyReport>> reports;
	private DailyReportDAO reportDao;
	private LocalDate startDate;
	private int duration;

	public ReportGenerator() {
		reportDao = new DailyReportDAO();
	}

	public ReportGenerator(String startDate, int duration) {
		reportDao = new DailyReportDAO();
		this.duration = duration;
		this.startDate = LocalDate.parse(startDate);
	}

	public List<List<DailyReport>> getReports() {
		return reports;
	}

	public void setStartDate(String startDate) {
		this.startDate = LocalDate.parse(startDate);
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	/*
	 * This method will be the main loop that creates daily reports based on
	 * employees' traits
	 * 
	 */
	public List<DailyReport> generateReports(List<Employee> employees) {

		ArrayList<DailyReport> listOfReports = new ArrayList<DailyReport>();

		int numDays = duration;
		LocalDate date = startDate;

		for (int i = 0; i <= numDays; i++) {

			for (Employee e : employees) {

				listOfReports.add(generateSingleReport(e, date));

			}

			// incrementing day (skipping weekends)
			date = addDaySkippingWeekend(date);
		}

		// testing purposes only
		reportDao.create(listOfReports);

		return listOfReports;

	}

	private DailyReport generateSingleReport(Employee e, LocalDate date) {

		DailyReport dr = new DailyReport();

		dr = simulateDailyReport(dr, e, date);

		return dr;
	}

	private DailyReport simulateDailyReport(DailyReport dr, Employee e, LocalDate date) {

		// setting employeeId on DailyReport
		dr.setEmployeeId(e.getEmployeeId());

		// adding 'current' date to DailyReport object
		dr.setDate(date.toString("YYYY-MM-DD"));

		int ahtTarget = 600, acwTarget = 60, numCallsTarget = 35;
		float fcrTarget = 70, custSatTarget = 7, callQualityTarget = 80;

		int ahtStd = 20, acwStd = 5, numCallsStd = 5, fcrStd = 15, custSatStd = 2, callQualityStd = 15;

		Random r = new Random();

		// generating avgHandleTime
		dr.setAht((int) Math.round((r.nextGaussian() + ahtTarget * ahtStd)));
		dr.setAcw((int) Math.round((r.nextGaussian() + acwTarget * acwStd)));
		dr.setFcr((int) Math.round((r.nextGaussian() + fcrTarget * fcrStd)));
		dr.setCustSat((int) Math.round((r.nextGaussian() + custSatTarget * custSatStd)));
		dr.setCallQuality((int) Math.round((r.nextGaussian() + callQualityTarget * callQualityStd)));
		dr.setNumCalls((int) Math.round((r.nextGaussian() + numCallsTarget * numCallsStd)));

		return dr;
	}

	// Method increments days, skipping weekends
	private LocalDate addDaySkippingWeekend(LocalDate date) {
		date = date.plusDays(1);

		System.out.println(date.getDayOfWeek());

		// while next day is Sat OR Sun, skip forward another day
		while (date.getDayOfWeek() == DayOfWeek.SATURDAY.getValue()
				|| date.getDayOfWeek() == DayOfWeek.SUNDAY.getValue()) {
			date = date.plusDays(1);
		}

		return date;
	}
}
