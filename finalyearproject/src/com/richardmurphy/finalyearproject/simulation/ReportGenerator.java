package com.richardmurphy.finalyearproject.simulation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.validation.constraints.Max;

import org.joda.time.LocalDate;

import com.richardmurphy.finalyearproject.dao.DailyReport;
import com.richardmurphy.finalyearproject.dao.DailyReportDAO;
import com.richardmurphy.finalyearproject.dao.Employee;

public class ReportGenerator {

	private List<DailyReport> reports;
	private DailyReportDAO reportDao;
	
	private Date startDate;
	
	@Max(value=101)
	private int duration;

	public ReportGenerator() {
		reportDao = new DailyReportDAO();
	}

	public List<DailyReport> getReports() {
		return reports;
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
		Date date = startDate;

		for (int i = 0; i <= numDays; i++) {

			for (Employee e : employees) {

				listOfReports.add(generateSingleReport(e, date));

			}
			
			// converting from Java Date to Joda LocalDate
			LocalDate localDate = new LocalDate(date);
			
			// incrementing day (skipping weekends)
			localDate = addDaySkippingWeekend(localDate);
			
			// converting back
			date = localDate.toDateTimeAtStartOfDay().toDate();
		}

		// testing purposes only
		// reportDao.create(listOfReports);

		return listOfReports;

	}

	public int getDuration() {
		return duration;
	}

	public DailyReportDAO getReportDao() {
		return reportDao;
	}

	public void setReportDao(DailyReportDAO reportDao) {
		this.reportDao = reportDao;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		System.out.println(startDate.toString());
		this.startDate = startDate;
	}

	private DailyReport generateSingleReport(Employee e, Date date) {

		DailyReport dr = new DailyReport();

		dr = simulateDailyReport(dr, e, date);

		return dr;
	}

	private DailyReport simulateDailyReport(DailyReport dr, Employee e, Date date) {

		// setting employeeId on DailyReport
		dr.setEmployeeId(e.getEmployeeId());

		// adding 'current' date to DailyReport object
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		dr.setDate(formatter.format(date));


		int ahtTarget = 600, acwTarget = 60, numCallsTarget = 35;
		float fcrTarget = 70, custSatTarget = 7, callQualityTarget = 80;

		int ahtStd = 30, acwStd = 5, numCallsStd = 5, fcrStd = 15, custSatStd = 2, callQualityStd = 15;

		Random r = new Random();
		
		// getting fixed employee traits
		int intelligence = e.getIntelligence();
		int empathy = e.getEmpathy();
		int patience = e.getPatience();
		
		// dynamic traits
		int experience = e.getExperience();
		int motivation = e.getMotivation();
		
		// determining baseline performance indicators based on traits
		if (experience > 75) {
			ahtTarget -= 20;
			acwTarget -= 20;
			fcrTarget -= 5;
			numCallsTarget += 10;
			custSatTarget += .5;
			callQualityTarget += 10;
		} else if (experience > 50) {
			ahtTarget -= 10;
			acwTarget -= 10;
			fcrTarget -= 2.5;
			numCallsTarget += 5;
			custSatTarget += .25;
			callQualityTarget += 5;
		} else if (experience > 25) {
			ahtTarget -= 10;
			acwTarget -= 10;
			fcrTarget -= 0;
			numCallsTarget += 0;
			custSatTarget += 0;
			callQualityTarget += 0;
		} else {
			ahtTarget += 10;
			acwTarget += 10;
			fcrTarget += 2.5;
			numCallsTarget -= 5;
			custSatTarget += .25;
			callQualityTarget -= 5;
		}
		
		/*  Adjusting targets and ranges based on traits
		 * 	Intelligence
		 * 	- targets: aht, fcr
		 * 	- ranges: ALL (smarter -> more consistent)
		 * 	
		 * 	Empathy 
		 * 	- targets: custSat, callQuality
		 * 	- ranges: custSat, callQuality
		 *  
		 *  Motivation
		 *  - targets: aht, acw, fcr, custSat, callQuality, numCalls
		 *  - ranges: none
		 *  
		 *  Patience
		 *  - targets: !aht, fcr, custSat, callQuality
		 *  - range: none
		 *  
		 *  Combined effects
		 *  High Intelligence & Low Patience = Reduced Motivation
		 */
		
		
		// Adjusting aht, fcr targets based on intelligence
		ahtTarget = (int)(ahtTarget + ( (intelligence - 50) * 0.2) );
		fcrTarget = (int)(fcrTarget + ( (intelligence - 50) * 0.2 ) );
		
		// adjusting std or range based on intelligence
		ahtStd = (int)(ahtStd + ( (intelligence - 50) * 0.2) );
		fcrStd = (int)(fcrStd + ( (intelligence - 50) * 0.2) );
		acwStd = (int)(acwStd + ( (intelligence - 50) * 0.2) );
		
		
		
		
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
