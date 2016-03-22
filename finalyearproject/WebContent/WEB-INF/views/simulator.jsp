<%@page import="java.net.URL"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FYP | Simulator</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<!-- Chart.js CDN link -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/1.0.2/Chart.min.js"></script>


<link rel="stylesheet" href="<c:url value='/resources/css/main.css'/>"
	type="text/css">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<div class="row">
					<div class="col-12-md">
						<h4>Settings</h4>
						<sf:form class="form" method="post" commandName="summaryReport"
							action="${ pageContext.request.contextPath }/simulator">

							<div class="input-group">
								<div class="input-group">
									<sf:input path="simName" name="simName" type="text"
										class="form-control" placeholder="Enter name.."></sf:input>
									<span class="input-group-btn"> <input
										class="control btn btn-primary" type="submit" value="New" />
									</span>
								</div>
							</div>

						</sf:form>
					</div>
				</div>
				<div class="row">
					<div class="col-12-md">
						<ul class="nav nav-pills nav-stacked">

							<c:if test="${not empty summaryReport}">
								<c:set var="summarySimId" scope="session"
									value="${ summaryReport.simId }" />
							</c:if>
							<c:if test="${ empty summaryReport }">
								<c:set var="summarySimId" scope="session" value="0" />
							</c:if>
							<c:forEach items="${ pastReports }" var="summary">
								<c:choose>
									<c:when test="${(summarySimId == summary.simId)}">
										<li class="active">
									</c:when>
									<c:otherwise>
										<li>
									</c:otherwise>
								</c:choose>
								<a
									href="${ pageContext.request.contextPath }/simulator/${ summary.simId }">
									<c:out value="${ summary.simName } ${ summary.simId }"></c:out>
								</a>
								</li>
							</c:forEach>


						</ul>
					</div>
				</div>

			</div>
			<div class="col-md-9">

				<ul class="nav nav-tabs">
					<li class="active"><a data-toggle="tab" href="#employees">Employees</a></li>
					<li><a data-toggle="tab" href="#simulation-settings">Launch
							Control</a></li>
					<li class="disabled"><a data-toggle="tab" href="#overview">Results</a></li>
					<li class="disabled"><a data-toggle="tab" href="#data">Raw
							Data</a></li>
				</ul>
				<!-- Dashboard -->
				<div class="tab-content">

					<!-- Employees Tab -->
					<div id="employees" class="tab-pane fade in ">
						<div class="row">
							<div class="col-md-3">
								<ul class="nav nav-stacked">
									<li class="active"><a data-toggle="tab"
										href="#employees-add">Add Employees</a></li>
									<li><a data-toggle="tab" href="#employees-add-bulk">Bulk
											Add Employees</a></li>
									<li><a data-toggle="tab" href="#employees-view">View
											Employees</a></li>
								</ul>

							</div>
							<div class="col-md-9">
								<div class="tab-content">
									<div class="tab-pane active employees-page" id="employees-add">

										<!-- Form to create new employee -->
										<sf:form class="form-inline" method="post"
											commandName="employee"
											action="${ pageContext.request.contextPath }/simulator/${ summarySimId }/employees">
											<div class="form-group">
												<sf:input class="form-control" path="firstName"
													name="firstName" placeholder="First name.." />
												<sf:input class="form-control" path="surname" name="surname"
													placeholder="Surname.." />
												<sf:select class="form-control" path="departmentId"
													name="departmentId">
													<sf:option value="-1">- Select a department -</sf:option>
													<sf:option value="0">Tech Support</sf:option>
													<sf:option value="1">Sales</sf:option>
													<sf:option value="2">Customer Service</sf:option>
												</sf:select>
												<button type="submit" class="btn btn-default">Add</button>
											</div>
										</sf:form>
										
										<h4>All employees:</h4>
										<div class="table-responsive report-table">
											<table class="table table-striped">
												<thead>
													<tr>
														<th>ID</th>
														<th>Name</th>
													</tr>
												</thead>
												<tbody>
												<c:forEach var="listedEmployee" items="${ listedEmployees }">
													<tr>
														<td><c:out value="${ listedEmployee.employeeId }" /></td>
														<td><c:out value="${ listedEmployee.firstName } ${ listedEmployee.surname }" /></td>
													</tr>
												</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
									<div class="tab-pane employees-page" id="employees-add-bulk">
										<p>More test text</p>
									</div>
									<div class="tab-pane employees-page" id="employees-view">
										<p>and once again</p>
									</div>
								</div>
							</div>
						</div>
					</div>

					<!-- Simulation Control Tab -->
					<div id="simulation-settings" class="tab-pane">
						<div class="row">
							<div class="col-md-12"></div>
						</div>
					</div>


					<!-- Overview Tab -->
					<div id="overview" class="tab-pane active">
						<div class="row dashboard-header">
							<div class="col-md-9">
								<h2>Simulation Dashboard</h2>
								<div class="dashboard-settings">
									<p>Define targets</p>
									<br /> <label>AHT</label> <input id="ahtTarg" value="600"
										max="1000" min="0" type="number" /> <label>ACW</label> <input
										id="acwTarg" value="60" max="200" min="0" type="number" /> <label>FCR</label>
									<input id="fcrTarg" value="70" max="100" min="0" type="number" />

									<label>Call Quality</label> <input id="callQTarg" value="80"
										max="100" min="0" type="number" /> <label>Review</label> <input
										id="custSatTarg" value="8" max="10" min="0" type="number" />
									<label># Calls</label> <input id="numCallsTarg" value="25"
										max="100" min="0" type="number" />
								</div>
							</div>

						</div>
						<div class="row dashboard">
							<div class="col-md-6">
								<canvas id="ahtBarChart" width="100" height="100"></canvas>
								<canvas id="acwBarChart" width="100" height="100"></canvas>
								<canvas id="callQBarChart" width="100" height="100"></canvas>
								<canvas id="fcrBarChart" width="100" height="100"></canvas>
								<canvas id="custSatBarChart" width="100" height="100"></canvas>
								<canvas id="numCallsBarChart" width="100" height="100"></canvas>
							</div>
							<div class="col-md-3">
								<canvas id="employeePieChart" width="400" height="400"></canvas>
							</div>
						</div>
					</div>

					<!-- Data tab -->
					<div id="data" class="tab-pane">
						<div class="row">
							<div class="col-md-12">
								<div class="panel panel-default">
									<div class="panel-heading">
										<div class="panel-title">Daily Reports</div>
									</div>
									<div class="panel-body">
										<p>Log of daily reports for each employee in simulation
											environment.</p>
										<div class="table-responsive">
											<table class="table table-striped report-table">
												<thead>
													<tr>
														<th>Date Logged</th>
														<th>Employee ID</th>
														<th>AHT</th>
														<th>ACW</th>
														<th>FCR</th>
														<th>Call Quality</th>
														<th>Customer Satisfaction</th>
														<th>Number of Calls</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${ dailyReports }" var="report">
														<tr>
															<td class="date"><c:out value="${ report.date }"></c:out></td>
															<td><c:out value="${ report.employeeId }"></c:out></td>
															<td id="aht"><c:out value="${ report.aht }"></c:out></td>
															<td id="acw"><c:out value="${ report.acw }"></c:out></td>
															<td id="fcr"><c:out value="${ report.fcr }"></c:out></td>
															<td id="callQ"><c:out
																	value="${ report.callQuality }"></c:out></td>
															<td id="custSat"><c:out value="${ report.custSat }"></c:out></td>
															<td id="numCalls"><c:out
																	value="${ report.numCalls }"></c:out></td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>




				<div class="row">
					<table class="table" hidden="true">
						<tr>
							<th>Sim ID</th>
							<th>AHT</th>
							<th>ACW</th>
							<th>FCR</th>
							<th>Call Quality</th>
							<th>Customer Satisfaction</th>
							<th>Number of Calls</th>

							<th>Count VG</th>
							<th>Count G</th>
							<th>Count Avg</th>
							<th>Count B</th>
							<th>Count VB</th>
						</tr>

						<tr>
							<td id="simId"><c:out value="${ summaryReport.simId }"></c:out></td>
							<td id="aht"><c:out value="${ summaryReport.avgAht }"></c:out></td>
							<td id="acw"><c:out value="${ summaryReport.avgAcw }"></c:out></td>
							<td id="fcr"><c:out value="${ summaryReport.avgFcr }"></c:out></td>
							<td id="callQ"><c:out
									value="${ summaryReport.avgCallQuality }"></c:out></td>
							<td id="custSat"><c:out
									value="${ summaryReport.avgCustSat }"></c:out></td>
							<td id="numCalls"><c:out
									value="${ summaryReport.avgNumCalls }"></c:out></td>

							<td id="countVG"><c:out value="${ summaryReport.countVG }"></c:out></td>
							<td id="countG"><c:out value="${ summaryReport.countG }"></c:out></td>
							<td id="countAvg"><c:out value="${ summaryReport.countAvg }"></c:out></td>
							<td id="countB"><c:out value="${ summaryReport.countB }"></c:out></td>
							<td id="countVB"><c:out value="${ summaryReport.countVB }"></c:out></td>
						</tr>

					</table>
				</div>

			</div>
		</div>
	</div>

	<script src="<c:url value='/resources/js/simulator.js'/>"></script>

</body>
</html>