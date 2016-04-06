package com.richardmurphy.finalyearproject.simulation;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Date;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.validation.constraints.Max;

import org.joda.time.LocalDate;

import com.richardmurphy.finalyearproject.dao.DailyReport;
import com.richardmurphy.finalyearproject.dao.DailyReportDAO;
import com.richardmurphy.finalyearproject.dao.Employee;
import com.richardmurphy.finalyearproject.dao.SimEmployee;
import com.richardmurphy.finalyearproject.dao.SummaryReport;
import com.richardmurphy.finalyearproject.dao.SummaryReportDAO;

public class ReportGenerator {

	private List<DailyReport> reports;

	private List<SimEmployee> simEmployees;

	private boolean skipWeekends;
	private SummaryReport summaryReport;

	private String simName;
	private Date startDate;

	@Max(value = 1000)
	private int duration;

	public ReportGenerator() {
	}

	public List<DailyReport> getReports() {
		return reports;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public List<SimEmployee> getSimEmployees() {
		return simEmployees;
	}

	public void setSimEmployees(List<SimEmployee> simEmployees) {
		this.simEmployees = simEmployees;
	}

	/*
	 * This method is the main loop that creates daily reports based on
	 * employees' traits
	 * 
	 */
	public List<DailyReport> generateReports() {

		ArrayList<DailyReport> listOfReports = new ArrayList<DailyReport>();

		int numDays = duration;
		Date date = startDate;

		// setting the start date for each SimEmployee
		for (SimEmployee e : simEmployees)
			e.setStartDate(date);

		for (int i = 0; i <= numDays; i++) {

			// for each employee, if motivation is low, chance of quitting
			// set leaveDate to current and isCurrentEmployee = false
			for (SimEmployee e : simEmployees) {

				if (e.getMotivation() < 5 && Math.random() > 0.975) {
					e.setLeaveDate(date);
					e.setIsCurrentEmployee(false);
				} else if (e.getMotivation() < 10 && Math.random() > 0.99) {
					e.setLeaveDate(date);
					e.setIsCurrentEmployee(false);
				}

				// if currently employed i.e. hasn't quit, then 'do day's work'
				// - generate report and add to list
				if (e.getIsCurrentEmployee()) {
					listOfReports.add(generateSingleReport(e, date));
				}
			}

			// converting from Java Date to Joda LocalDate
			LocalDate localDate = new LocalDate(date);

			// incrementing day (skipping weekends)
			localDate = addDaySkippingWeekend(localDate, skipWeekends);

			// converting back
			date = localDate.toDateTimeAtStartOfDay().toDate();
		}

		return listOfReports;

	}

	public SummaryReport generateSummaryReport(List<DailyReport> dailyReports) {
		// constructor for summary report with list of daily reports calculates
		// aggregate vals from within constructor
		summaryReport = new SummaryReport(dailyReports);

		return summaryReport;
	}

	private DailyReport generateSingleReport(SimEmployee e, Date date) {

		DailyReport dr = new DailyReport();

		dr = simulateDailyReport(dr, e, date);

		return dr;
	}

	private DailyReport simulateDailyReport(DailyReport dr, SimEmployee e, Date date) {

		// setting employeeId on DailyReport
		dr.setEmployeeId(e.getEmployeeId());

		// adding 'current' date to DailyReport object
		// SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		dr.setDate(date);

		int ahtTarget, acwTarget, numCallsTarget;
		double fcrTarget, custSatTarget, callQualityTarget;

		int ahtStd = 30, acwStd = 5, numCallsStd = 5, fcrStd = 15, custSatStd = 1, callQualityStd = 10;

		Random r = new Random();

		// getting fixed employee traits
		int intelligence = e.getIntelligence();
		int empathy = e.getEmpathy();
		int patience = e.getPatience();
		int initiative = e.getInitiative();
		int communication = e.getCommunication();

		// dynamic traits
		int experience = e.getExperience();
		int motivation = e.getMotivation();
		int skillLevel = 0;

		// agents improve based on intelligence
		if (intelligence > 80) {
			e.setExperience(e.getExperience() + 1);
		} else if (intelligence > 60 && Math.random() > 0.2) {
			e.setExperience(e.getExperience() + 1);
		} else if (intelligence > 40 && Math.random() > 0.4) {
			e.setExperience(e.getExperience() + 1);
		} else if (intelligence > 20 && Math.random() > 0.6) {
			e.setExperience(e.getExperience() + 1);
		}

		// ~ randomly demotivate agents
		if (intelligence > 80 && patience < 20 && Math.random() > 0.5) {
			e.setMotivation(e.getMotivation() - 1);
		} else if (intelligence > 70 && patience < 30 && Math.random() > 0.6) {
			e.setMotivation(e.getMotivation() - 1);
		} else if (intelligence > 60 && patience < 40 && Math.random() > 0.7) {
			e.setMotivation(e.getMotivation() - 1);
		} else if (intelligence > 50 && patience < 20 && Math.random() > 0.6) {
			e.setMotivation(e.getMotivation() - 1);
		} else if (intelligence > 50 && patience < 30 && Math.random() > 0.7) {
			e.setMotivation(e.getMotivation() - 1);
		} else if (intelligence > 50 && patience < 40 && Math.random() > 0.8) {
			e.setMotivation(e.getMotivation() - 1);
		} else if (intelligence > 40 && patience < 50 && Math.random() > 0.85) {
			e.setMotivation(e.getMotivation() - 1);
		} else if (intelligence > 30 && patience < 70 && Math.random() > 0.9) {
			e.setMotivation(e.getMotivation() - 1);
		}

		// Classifying agents initial performance indicator averages
		// these values are based off of an interview with Operations Manager of
		// Eir's Tech Support Department
		if (experience > 80) {
			ahtTarget = 560;
			acwTarget = 45;
			fcrTarget = 80;
			numCallsTarget = 34;
			custSatTarget = 9;
			callQualityTarget = 90;

			skillLevel = 5;
		} else if (experience > 60) {
			ahtTarget = 620;
			acwTarget = 55;
			fcrTarget = 75;
			numCallsTarget = 30;
			custSatTarget = 8;
			callQualityTarget = 80;

			skillLevel = 4;
		} else if (experience > 40) {
			ahtTarget = 680;
			acwTarget = 60;
			fcrTarget = 70;
			numCallsTarget = 25;
			custSatTarget = 7;
			callQualityTarget = 70;

			skillLevel = 3;
		} else if (experience > 20) {
			ahtTarget = 720;
			acwTarget = 75;
			fcrTarget = 60;
			numCallsTarget = 20;
			custSatTarget = 6;
			callQualityTarget = 60;

			skillLevel = 2;
		} else {
			ahtTarget = 800;
			acwTarget = 90;
			fcrTarget = 50;
			numCallsTarget = 18;
			custSatTarget = 5;
			callQualityTarget = 50;

			skillLevel = 1;
		}

		/*
		 * Survey Monkey Domain Research Description: In order to determine
		 * roughly how much of an impact each skill had on performance, I
		 * created a survey and sent it around to a number of technical support
		 * agents and recruiting agents. The aggregate of their opinions on the
		 * importance of each skill will be used to simulate daily performance
		 * reports.
		 * 
		 * 
		 * 1. Skills = Weighted Importance (0..1) - Patience = 0.83 - Empathy =
		 * 0.83 - Intelligence = 0.75 - Motivation = 0.585 - Initiative = 0.165
		 * - Communication = 0.83
		 */

		double patienceWeight = 0.83, empathyWeight = 0.83, intelligenceWeight = 0.75, motivationWeight = 0.585,
				initiativeWeight = 0.165, communicationWeight = 0.83;

		/*
		 * Formula to implement trait impact on performance
		 * 
		 * impactedTarget = target +/- (skillWeight * target/10 * impact * skillLevel)
		 * 
		 * * aht, acw, fcr use negative impact as lower is better for these
		 * indicators whereas with numCalls, callQuality and custSat, higher is
		 * better
		 * 
		 * skillLevel = ( e.getSkill() - 50 ) / 100 - makes a value +/- .5 max
		 * 
		 *
		 *
		 * 2. Impact of skill on performance metrics = %
		 * 
		 * A. Patience - Average Handle Time = 66.67 - After Call Work Time =
		 * 16.67 - Customer Satisfaction = 66.67 - Call Quality Rating = 33.33 -
		 * First Call Resolution = 66.67 - Number of Calls = 16.67
		 */

		double patienceImpactAht = 0.6667, patienceImpactAcw = 0.1667, patienceImpactCustSat = 0.6667,
				patienceImpactCallQuality = 0.3333, patienceImpactFcr = 0.3333, patienceImpactNumCalls = 0.1667;

		ahtTarget = (int) (ahtTarget - (patienceWeight * ahtTarget / 10 * patienceImpactAht * (patience - 50) / 100));

		acwTarget = (int) (acwTarget - (patienceWeight * acwTarget / 10 * patienceImpactAcw * (patience - 50) / 100));

		custSatTarget = (int) (custSatTarget
				+ (patienceWeight * custSatTarget / 10 * patienceImpactCustSat * (patience - 50) / 100));

		callQualityTarget = (int) (callQualityTarget
				+ (patienceWeight * callQualityTarget / 10 * patienceImpactCallQuality * (patience - 50) / 100));

		fcrTarget = (int) (fcrTarget - (patienceWeight * fcrTarget / 10 * patienceImpactFcr * (patience - 50) / 100));

		numCallsTarget = (int) (numCallsTarget
				+ (patienceWeight * numCallsTarget / 10 * patienceImpactNumCalls * (patience - 50) / 100));

		/*
		 * B. Empathy ** no impact on AHT, ACW ** - Customer Satisfaction = 100
		 * - Call Quality = 66.67 - First Call Resolution = 33.33 - Number of
		 * Calls = 16.67
		 */

		double empathyImpactCustSat = 1.00, empathyImpactCallQuality = 0.6667, empathyImpactFcr = 0.3333,
				empathyImpactNumCalls = 0.1667;

		custSatTarget = (int) (custSatTarget
				+ (empathyWeight * custSatTarget / 10 * empathyImpactCustSat * (empathy - 50) / 100));

		callQualityTarget = (int) (callQualityTarget
				+ (empathyWeight * callQualityTarget / 10 * empathyImpactCallQuality * (empathy - 50) / 100));

		fcrTarget = (int) (fcrTarget - (empathyWeight * fcrTarget / 10 * empathyImpactFcr * (empathy - 50) / 100));

		numCallsTarget = (int) (numCallsTarget
				+ (empathyWeight * numCallsTarget / 10 * empathyImpactNumCalls * (empathy - 50) / 100));

		/*
		 * C. Intelligence - Average Handle Time = 83.33 - After Call Work =
		 * 33.33 - Customer Satisfaction = 50 - Call Quality = 66.67 - First
		 * Call Resolution = 83.33 - Number of Calls = 33.33
		 */

		double intelligenceImpactAht = 0.8333, intelligenceImpactAcw = 0.3333, intelligenceImpactCustSat = 0.50,
				intelligenceImpactCallQuality = 0.6667, intelligenceImpactFcr = 0.8333,
				intelligenceImpactNumCalls = 0.3333;

		ahtTarget = (int) (ahtTarget
				- (intelligenceWeight * ahtTarget / 10 * intelligenceImpactAht * (intelligence - 50) / 100));

		acwTarget = (int) (acwTarget
				- (intelligenceWeight * acwTarget / 10 * intelligenceImpactAcw * (intelligence - 50) / 100));

		custSatTarget = (int) (custSatTarget
				+ (intelligenceWeight * custSatTarget / 10 * intelligenceImpactCustSat * (intelligence - 50) / 100));

		callQualityTarget = (int) (callQualityTarget + (intelligenceWeight * callQualityTarget / 10
				* intelligenceImpactCallQuality * (intelligence - 50) / 100));

		fcrTarget = (int) (fcrTarget
				- (intelligenceWeight * fcrTarget / 10 * intelligenceImpactFcr * (intelligence - 50) / 100));

		numCallsTarget = (int) (numCallsTarget
				+ (intelligenceWeight * numCallsTarget / 10 * intelligenceImpactNumCalls * (intelligence - 50) / 100));

		/*
		 * D. Motivation - Average Handle Time = 50 - After Call Work = 83.33 -
		 * Customer Satisfaction = 66.67 - Call Quality = 66.67 - First Call
		 * Resolution = 16.67 - Number of Calls = 83.33
		 */

		double motivationImpactAht = 0.50, motivationImpactAcw = 0.8333, motivationImpactCustSat = 0.6667,
				motivationImpactCallQuality = 0.6667, motivationImpactFcr = 0.1667, motivationImpactNumCalls = 0.8333;

		ahtTarget = (int) (ahtTarget
				- (motivationWeight * ahtTarget / 10 * motivationImpactAht * (motivation - 50) / 100));

		acwTarget = (int) (acwTarget
				- (motivationWeight * acwTarget / 10 * motivationImpactAcw * (motivation - 50) / 100));

		custSatTarget = (int) (custSatTarget
				+ (motivationWeight * custSatTarget / 10 * motivationImpactCustSat * (motivation - 50) / 100));

		callQualityTarget = (int) (callQualityTarget
				+ (motivationWeight * callQualityTarget / 10 * motivationImpactCallQuality * (motivation - 50) / 100));

		fcrTarget = (int) (fcrTarget
				- (motivationWeight * fcrTarget / 10 * motivationImpactFcr * (motivation - 50) / 100));

		numCallsTarget = (int) (numCallsTarget
				+ (motivationWeight * numCallsTarget / 10 * motivationImpactNumCalls * (motivation - 50) / 100));

		/*
		 * E. Initiative - Average Handle Time = 50 - After Call Work = 50 -
		 * Customer Satisfaction = 16.67 - Call Quality = 0 - First Call
		 * Resolution = 100 - Number of Calls = 50
		 */

		double initiativeImpactAht = 0.5, initiativeImpactAcw = 0.5, initiativeImpactCustSat = 0.1667,
				initiativeImpactCallQuality = 0, initiativeImpactFcr = 0.1, initiativeImpactNumCalls = 0.5;

		ahtTarget = (int) (ahtTarget
				- (initiativeWeight * ahtTarget / 10 * initiativeImpactAht * (initiative - 50) / 100));

		acwTarget = (int) (acwTarget
				- (initiativeWeight * acwTarget / 10 * initiativeImpactAcw * (initiative - 50) / 100));

		custSatTarget = (int) (custSatTarget
				+ (initiativeWeight * custSatTarget / 10 * initiativeImpactCustSat * (initiative - 50) / 100));

		callQualityTarget = (int) (callQualityTarget
				+ (initiativeWeight * callQualityTarget / 10 * initiativeImpactCallQuality * (initiative - 50) / 100));

		fcrTarget = (int) (fcrTarget
				- (initiativeWeight * fcrTarget / 10 * initiativeImpactFcr * (initiative - 50) / 100));

		numCallsTarget = (int) (numCallsTarget
				+ (initiativeWeight * numCallsTarget / 10 * initiativeImpactNumCalls * (initiative - 50) / 100));

		/*
		 * F. Communication - Average Handle Time = 83.33 - After Call Work = 0
		 * - Customer Satisfaction = 100 - Call Quality = 83.33 - First Call
		 * Resolution = 33.33 - Number of Calls = 50
		 * 
		 * 
		 */

		double communicationImpactAht = 0.8333, communicationImpactAcw = 0, communicationImpactCustSat = 0.1,
				communicationImpactCallQuality = 0.8333, communicationImpactFcr = 0.3333,
				communicationImpactNumCalls = 0.5;

		ahtTarget = (int) (ahtTarget
				- (communicationWeight * ahtTarget / 10 * communicationImpactAht * (communication - 50) / 100));

		acwTarget = (int) (acwTarget
				- (communicationWeight * acwTarget / 10 * communicationImpactAcw * (communication - 50) / 100));

		custSatTarget = (int) (custSatTarget
				+ (communicationWeight * custSatTarget / 10 * communicationImpactCustSat * (communication - 50) / 100));

		callQualityTarget = (int) (callQualityTarget + (communicationWeight * callQualityTarget / 10
				* communicationImpactCallQuality * (communication - 50) / 100));

		fcrTarget = (int) (fcrTarget
				- (communicationWeight * fcrTarget / 10 * communicationImpactFcr * (communication - 50) / 100));

		numCallsTarget = (int) (numCallsTarget + (communicationWeight * numCallsTarget / 10
				* communicationImpactNumCalls * (communication - 50) / 100));

		/*
		 * Implement capping of values Call Quality range (0-100) Customer
		 * Satisfaction range (0-10)
		 * 
		 */
		int finalAht, finalAcw, finalFcr, finalCustSat, finalCallQuality, finalNumCalls;
		boolean resultsValid = false;

		do {
			finalAht = (int) Math.round((r.nextGaussian() * ahtStd + ahtTarget));
			finalAcw = (int) Math.round((r.nextGaussian() * acwStd + acwTarget));
			finalFcr = (int) Math.round((r.nextGaussian() * fcrStd + fcrTarget));
			finalCustSat = (int) Math.round((r.nextGaussian() * custSatStd + custSatTarget));
			finalCallQuality = (int) Math.round((r.nextGaussian() * callQualityStd + callQualityTarget));
			finalNumCalls = (int) Math.round((r.nextGaussian() * numCallsStd + numCallsTarget));

			if (finalAht > 0 && finalAcw > 0 && finalFcr > 0 && finalCustSat > 0 && finalCustSat <= 10
					&& finalCallQuality < 100 && finalCallQuality > 0 && finalNumCalls > 0)
				resultsValid = true;
		} while (!resultsValid);

		
		dr.setAht(finalAht);
		dr.setAcw(finalAcw);
		dr.setFcr(finalFcr);
		dr.setCustSat(finalCustSat);
		dr.setCallQuality(finalCallQuality);
		dr.setNumCalls(finalNumCalls);
		dr.setSkillLevel(skillLevel);

		return dr;
	}

	// Method increments days, skipping weekends
	private LocalDate addDaySkippingWeekend(LocalDate date, boolean skipWeekends) {
		date = date.plusDays(1);

		System.out.println(date.getDayOfWeek());

		if (skipWeekends) {
			// while next day is Sat OR Sun, skip forward another day
			while (date.getDayOfWeek() == DayOfWeek.SATURDAY.getValue()
					|| date.getDayOfWeek() == DayOfWeek.SUNDAY.getValue()) {
				date = date.plusDays(1);
			}
		}

		return date;
	}

	public int getDuration() {
		return duration;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getSimName() {
		return simName;
	}

	public void setSimName(String name) {
		this.simName = name;
	}

	public boolean isSkipWeekends() {
		return skipWeekends;
	}

	public void setSkipWeekends(boolean skipWeekends) {
		this.skipWeekends = skipWeekends;
	}

}

