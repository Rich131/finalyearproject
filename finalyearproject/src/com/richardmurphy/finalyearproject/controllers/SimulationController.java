package com.richardmurphy.finalyearproject.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.richardmurphy.finalyearproject.dao.DailyReport;
import com.richardmurphy.finalyearproject.dao.Employee;
import com.richardmurphy.finalyearproject.dao.SimEmployee;
import com.richardmurphy.finalyearproject.dao.SummaryReport;
import com.richardmurphy.finalyearproject.services.DailyReportService;
import com.richardmurphy.finalyearproject.services.EmployeeService;
import com.richardmurphy.finalyearproject.services.SimEmployeeService;
import com.richardmurphy.finalyearproject.services.SummaryReportService;
import com.richardmurphy.finalyearproject.simulation.ReportGenerator;

@Controller
public class SimulationController {

	private DailyReportService dailyReportService;
	private EmployeeService employeeService;
	private SummaryReportService summaryReportService;
	private SimEmployeeService simEmployeeService;

	@Autowired
	public void setSimEmployeeService(SimEmployeeService simEmployeeService) {
		this.simEmployeeService = simEmployeeService;
	}

	@Autowired
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@Autowired
	public void setDailyReportService(DailyReportService dailyReportService) {
		this.dailyReportService = dailyReportService;
	}

	@Autowired
	public void setSummaryReportService(SummaryReportService summaryReportService) {
		this.summaryReportService = summaryReportService;
	}

	@RequestMapping(value = "/simulator", method = RequestMethod.GET)
	public String showSimulator(Model model, @Valid SummaryReport summaryReport, @Valid Employee employee) {
		List<SummaryReport> pastReports = summaryReportService.getSummaryReports();

		model.addAttribute("employee", employee);
		model.addAttribute("summaryReport", summaryReport);
		model.addAttribute("pastReports", pastReports);

		return "simulator";
	}

	// Main Simulator get request, contains all attributes required to display
	// simulation environment correctly
	@RequestMapping(value = "/simulator/{id}", method = RequestMethod.GET)
	public String showSimulation(Model model, @PathVariable("id") int simId) {
		
		
		SimEmployee simEmpToBeCreated = new SimEmployee();
		ReportGenerator reportGenerator = new ReportGenerator();
		
		// gathering lists of employees and reports
		SummaryReport summaryReport = summaryReportService.getSummaryReport(simId);
		List<DailyReport> dailyReports = dailyReportService.getDailyReportsBySimId(simId);
		List<SummaryReport> pastReports = summaryReportService.getSummaryReports();
		List<Employee> listedEmployees = employeeService.getEmployeesBySimId(simId);
		List<SimEmployee> simEmployees = simEmployeeService.getSimEmployees(simId);
		
		
		
		// add Report Generator to model
		model.addAttribute("reportGenerator", reportGenerator);
		
		// list of all employees in database
		model.addAttribute("listedEmployees", listedEmployees);

		// list of all simulations
		model.addAttribute("pastReports", pastReports);

		// employees added to sim environment
		model.addAttribute("simEmployees", simEmployees);

		// summary report of sim environment
		model.addAttribute("summaryReport", summaryReport);

		// raw data - daily reports
		model.addAttribute("dailyReports", dailyReports);
		
		// adding empty simEmp for adding individually through spring form
		model.addAttribute("simEmployee", simEmpToBeCreated);

		return "simulator";
	}

	// Conversion from String to Date
	// InitBinder to initialize WebDataBinders on controller
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	/*
	 * Post to simulator/{id}/employees - create a list of simEmployees
	 * 
	 * @RequestMapping(value = "/simulator/{id}/employees", method =
	 * RequestMethod.POST) public String runSimulator(Model
	 * model, @PathVariable("id") int simId, @Valid Employee employee,
	 * BindingResult result) {
	 * 
	 * employeeService.create(employee);
	 * 
	 * return "simulator/" + simId + "/employees"; }
	 */

	/*
	 * Request to POST collection of agents to simulation environment Ranges
	 * specified are parsed from String to ints, Then agents are created with
	 * values within range randomly
	 * 
	 */

	// TODO: Split functionality into 2 methods, specifying params like:
	// https://stackoverflow.com/questions/22373696/requestparam-in-spring-mvc-handling-optional-parameters

	@RequestMapping(value = "/simulator/{id}/employees", method = RequestMethod.POST)
	public String addSingleEmployeeToSim(Model model, @PathVariable("id") int simId,
			@Valid SimEmployee simEmp, BindingResult br) {
		
		
		simEmp.setSimId(simId);
		System.out.println(simEmp);
		
		simEmployeeService.create(simEmp);
		
		
		
		return "redirect:/simulator/" + simId;
	}

	/*
	 * Add Bulk Employees To Simulation Environment Desc: This method parses the
	 * range of allowed values for employee traits then randomly creates
	 * SimEmployee objects within the range specified.
	 * 
	 */
	@RequestMapping(value = "/simulator/{id}/employees", method = RequestMethod.POST, params = { "intelligence",
			"motivation", "empathy", "communication", "initiative", "patience", "experience", "submit", "_csrf" })
	public String addBulkEmployeesToSim(Model model, @PathVariable("id") int simId,
			@RequestParam("intelligence") String intelligenceRangeStr,
			@RequestParam("initiative") String initiativeRangeStr, @RequestParam("empathy") String empathyRangeStr,
			@RequestParam("patience") String patienceRangeStr, @RequestParam("experience") String experienceRangeStr,
			@RequestParam("communication") String communicationRangeStr,
			@RequestParam("motivation") String motivationRangeStr, @RequestParam("submit") String submit) {

		int limit = 0;

		int minCommunication, minEmpathy, minExperience, minInitiative, minIntelligence, minMotivation, minPatience;
		int maxCommunication, maxEmpathy, maxExperience, maxInitiative, maxIntelligence, maxMotivation, maxPatience;

		// parsing ints from range strings [ "x - y" ] -> int x, int y
		minCommunication = Integer.parseInt((communicationRangeStr.split("-"))[0].trim());
		maxCommunication = Integer.parseInt((communicationRangeStr.split("-"))[1].trim());

		minEmpathy = Integer.parseInt((empathyRangeStr.split("-"))[0].trim());
		maxEmpathy = Integer.parseInt((empathyRangeStr.split("-"))[1].trim());

		minExperience = Integer.parseInt((experienceRangeStr.split("-"))[0].trim());
		maxExperience = Integer.parseInt((experienceRangeStr.split("-"))[1].trim());

		minInitiative = Integer.parseInt((initiativeRangeStr.split("-"))[0].trim());
		maxInitiative = Integer.parseInt((initiativeRangeStr.split("-"))[1].trim());

		minIntelligence = Integer.parseInt((intelligenceRangeStr.split("-"))[0].trim());
		maxIntelligence = Integer.parseInt((intelligenceRangeStr.split("-"))[1].trim());

		minMotivation = Integer.parseInt((motivationRangeStr.split("-"))[0].trim());
		maxMotivation = Integer.parseInt((motivationRangeStr.split("-"))[1].trim());

		minPatience = Integer.parseInt((patienceRangeStr.split("-"))[0].trim());
		maxPatience = Integer.parseInt((patienceRangeStr.split("-"))[1].trim());

		limit = Integer.parseInt(submit);

		Random r = new Random();

		// attempts to add up to limit
		List<Integer> availableEmployeeIds = employeeService.getEmployeesNotInSim(simId, limit);

		// sets limit to size of resultSet (could be same OR max available
		// employees if max < limit)
		limit = availableEmployeeIds.size();

		System.out.println("Size of available employees list: " + limit);

		List<SimEmployee> simEmployees = new ArrayList<SimEmployee>();

		if (limit > 0) {
			for (int i = 0; i < limit; i++) {
				simEmployees.add(new SimEmployee(availableEmployeeIds.get(i), simId,
						(r.nextInt(maxIntelligence - minIntelligence) + minIntelligence),
						(r.nextInt(maxPatience - minPatience) + minPatience),
						(r.nextInt(maxExperience - minExperience) + minExperience),
						(r.nextInt(maxMotivation - minMotivation) + minMotivation),
						(r.nextInt(maxEmpathy - minEmpathy) + minEmpathy),
						(r.nextInt(maxInitiative - minInitiative) + minInitiative),
						(r.nextInt(maxCommunication - minCommunication) + minCommunication)));
			}

			simEmployeeService.create(simEmployees);
		}

		return "redirect:/simulator/" + simId + "/";
	}

	/*
	 * runSimulator method
	 * 
	 * desc: POST from launch control view creates simulation and runs with
	 * specified settings
	 * 
	 */
	@RequestMapping(value = "/simulator/{id}/run", method = RequestMethod.POST)
	public String runSimulator(Model model, @PathVariable("id") int simId, @Valid ReportGenerator reportGenerator,
			BindingResult result) {

		// get list of employees in simulation environment
		List<SimEmployee> simEmployees = simEmployeeService.getSimEmployees(simId);
		SummaryReport summaryReport;

		// assign list of employees in ReportGenerator class
		reportGenerator.setSimEmployees(simEmployees);
		
		// run simulation, generating daily reports for each employee
		List<DailyReport> reports = reportGenerator.generateReports();

		// create summary report from list of daily reports
		summaryReport = reportGenerator.generateSummaryReport(reports);
		

		

		// getting list of all sim environments
		List<SummaryReport> pastReports = summaryReportService.getSummaryReports();

		// create blank summary, get auto assigned simId

		summaryReport.setSimId(simId);
		summaryReport.setSimName(reportGenerator.getSimName());

		// associating each report with current simId
		for (DailyReport report : reports) {
			report.setSimId(simId);
		}
		
		// after simulation completes, get updated list of simEmployees
		simEmployees = reportGenerator.getSimEmployees();
		
		// write daily reports to DB
		dailyReportService.create(reports);
		
		// update summary report with aggregate data & SimEmployee records 
		summaryReportService.update(summaryReport);
		simEmployeeService.update(simEmployees);
		
		return "redirect:/simulator/" + simId;
	}

	@RequestMapping(value = "/simulator", method = RequestMethod.POST)
	public String createSimulation(Model model, @Valid SummaryReport summaryReport, BindingResult result) {

		if (result.hasErrors()) {
			System.out.println("Not valid.");

			List<ObjectError> errors = result.getAllErrors();

			for (ObjectError error : errors) {
				System.out.println(error.getDefaultMessage());
			}

			return "simulator";
		} else {

			// create a blank summary report
			int simId = summaryReportService.createEmpty(summaryReport);

			// redirect to newly created simulation environment
			return "redirect:/simulator/" + simId;
		}
	}
}
