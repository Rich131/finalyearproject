<%@page import="java.net.URL"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<div class="col-md-3">


	<div class="panel panel-default">
		<div class="panel-heading">
			<h4>Choose Environment</h4>
		</div>
		<div class="panel-body">
			<div class="row">
				<div class="col-12-md">

					<sf:form class="form" method="post" commandName="summaryReport"
						action="${ pageContext.request.contextPath }/simulator">

						<div class="input-group">
							<div class="input-group">
								<sf:input path="simName" name="simName" type="text"
									class="form-control" placeholder="Name"></sf:input>
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
					<div class="scrollable">
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
			<!-- end row -->

		</div>
		<!--  end panel body -->
	</div>
	<!-- end panel -->



</div>

<!-- Main view port
	
	Desc: Houses main views for employee customisation, simulation control & results viewing
		
 -->
<div class="col-md-9">

	<div class="panel panel-default">


		<div class="panel-head">
			<ul class="nav nav-tabs">
				<li class="active"><a data-toggle="tab" href="#employees">Employees</a></li>
				<li><a data-toggle="tab" href="#simulation-settings">Launch
						Control</a></li>
				<li class="disabled"><a data-toggle="tab" id="overview-control" href="#overview">Results</a></li>
				<li class="disabled"><a data-toggle="tab" href="#data">Raw
						Data</a></li>
			</ul>
		</div>


		<div class="panel-body">


			<!-- Dashboard -->
			<div class="tab-content" id="main-tabs">

				<!-- Employees Tab -->
				<div id="employees" class="tab-pane fade in active">
					<div class="row">
						<div class="panel panel-default">
							<div class="panel-body">
								<ul class="nav nav-tabs">
									<li class="active"><a data-toggle="tab"
										href="#employees-add">Add Employees</a></li>
									<li><a data-toggle="tab" href="#employees-add-bulk">Bulk
											Add Employees</a></li>
									<li><a data-toggle="tab" href="#employees-view">View
											Employees</a></li>
								</ul>

								<div class="tab-content">

									<!-- Pane for adding employees to the simulation environment -->
									<div class="tab-pane active employees-page" id="employees-add">

										<div class="table-responsive report-table">
											<table class="table table-striped">
												<thead>
													<tr>
														<th>ID</th>
														<th>Name</th>
														<th><img class="trait-img" title="Communication"
															alt="communication"
															src="<c:url value='/resources/images/communication-icon.png'/>"></th>
														<th><img class="trait-img" title="Empathy"
															alt="empathy"
															src="<c:url value='/resources/images/empathy-icon.png'/>"></th>
														<th><img class="trait-img" title="Initiative"
															alt="initiative"
															src="<c:url value='/resources/images/initiative-icon.png'/>"></th>
														<th><img class="trait-img" title="Intelligence"
															alt="intelligence"
															src="<c:url value='/resources/images/intelligence-icon.png'/>"></th>
														<th><img class="trait-img" title="Motivation"
															alt="motivation"
															src="<c:url value='/resources/images/motivation-icon.png'/>"></th>
														<th><img class="trait-img" title="Patience"
															alt="patience"
															src="<c:url value='/resources/images/patience-icon.png'/>"></th>
														<th><img class="trait-img" title="Experience"
															alt="experience"
															src="<c:url value='/resources/images/experience-icon.jpg'/>"></th>
														<th>Submit</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="listedEmployee"
														items="${ listedEmployees }">

														<sf:form class="form" method="post"
															commandName="simEmployee"
															action="${ pageContext.request.contextPath }/simulator/${ summarySimId }/employees/batch">
															<tr>
																<td><c:out value="${ listedEmployee.employeeId }" />
																	<sf:input hidden="true" type="number" path="employeeId"
																		name="employeeId"
																		value="${ listedEmployee.employeeId }" /></td>
																<td><c:out
																		value="${ listedEmployee.firstName } ${ listedEmployee.surname }" />
																</td>
																<td><sf:input type="number" path="communication"
																		name="communication" min="1" max="100" value="50" /></td>
																<td><sf:input type="number" path="empathy"
																		name="empathy" min="1" max="100" value="50" /></td>
																<td><sf:input type="number" path="initiative"
																		name="initiative" min="1" max="100" value="50" /></td>
																<td><sf:input type="number" path="intelligence"
																		name="intelligence" min="1" max="100" value="50" /></td>
																<td><sf:input type="number" path="motivation"
																		name="motivation" min="1" max="100" value="50" /></td>
																<td><sf:input type="number" path="patience"
																		name="patience" min="1" max="100" value="50" /></td>
																<td><sf:input type="number" path="experience"
																		name="experience" min="1" max="100" value="50" /></td>

																<td><input class="btn btn-primary control"
																	type="submit" value="Add to Sim" /></td>
															</tr>
														</sf:form>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>


									<!-- Pane for bulk adding of employees with generated traits -->

									<div class="tab-pane employees-page" id="employees-add-bulk">

										<div class="col-md-12">

											<h4>Bulk Add Employees</h4>


											<form method="post"
												action="${ pageContext.request.contextPath }/simulator/${ summarySimId }/employees">

												<p>Select specification of employee to be generated:</p>

												<div class="row">
													<div class="col-md-4 col-sm-4">
														<div class="form-group">
															<label for="intelligence-range" class="slider-label">Intelligence:</label>
															<input type="text" name="intelligence-range"
																id="intelligence-range" readonly class="slider-input"
																style="border: 0; color: #f6931f; font-weight: bold;" />
														</div>
														<div class="slider-range" id="intelligence-slider-range"></div>
													</div>
													<div class="col-md-4 col-sm-4">
														<div class="form-group">
															<label for="motivation-range" class="slider-label">Motivation:</label>
															<input type="text" name="motivation-range"
																id="motivation-range" readonly class="slider-input"
																style="border: 0; color: #f6931f; font-weight: bold;" />
														</div>
														<div class="slider-range" id="motivation-slider-range"></div>
													</div>
													<div class="col-md-4 col-sm-4">
														<div class="form-group">
															<label for="empathy-range" class="slider-label">Empathy:</label>
															<input type="text" name="empathy-range"
																id="empathy-range" readonly class="slider-input"
																style="border: 0; color: #f6931f; font-weight: bold;" />
														</div>
														<div class="slider-range" id="empathy-slider-range"></div>
													</div>
												</div>
												<div class="row">
													<div class="col-md-4 col-sm-4">
														<div class="form-group">
															<label for="communication-range" class="slider-label">Communication:</label>
															<input type="text" name="communication-range"
																id="communication-range" readonly class="slider-input"
																style="border: 0; color: #f6931f; font-weight: bold;" />
														</div>
														<div class="slider-range" id="communication-slider-range"></div>
													</div>
													<div class="col-md-4 col-sm-4">
														<div class="form-group">
															<label for="initiative-range" class="slider-label">Initiative:</label>
															<input type="text" name="initiative-range"
																id="initiative-range" readonly class="slider-input"
																style="border: 0; color: #f6931f; font-weight: bold;" />
														</div>
														<div class="slider-range" id="initiative-slider-range"></div>
													</div>
													<div class="col-md-4 col-sm-4">
														<div class="form-group">
															<label for="patience-range" class="slider-label">Patience:</label>
															<input type="text" name="patience-range"
																id="patience-range" readonly class="slider-input"
																style="border: 0; color: #f6931f; font-weight: bold;" />
														</div>
														<div class="slider-range" id="patience-slider-range"></div>
													</div>
												</div>
												<!-- row -->

												<div class="row">
													<div class="col-md-8">
														<div class="form-group">
															<label for="experience-range">Experience:</label> <input
																type="text" name="experience-range"
																id="experience-range" readonly
																style="border: 0; color: #f6931f; font-weight: bold;" />
														</div>
														<div class="slider-range" id="experience-slider-range"></div>
													</div>

													<!-- submit buttons -->
													<div class="col-md-4">
														<div class="submit-btn">
															<button name="submit" value="1">+1</button>
															<button name="submit" value="5">+5</button>
															<button name="submit" value="10">+10</button>
														</div>
													</div>
													<input type="hidden" name="${_csrf.parameterName}"
														value="${_csrf.token}" />
												</div>

											</form>
										</div>

									</div>

									<!-- Pane with detailed list of employees in simulation environment -->
									<div class="tab-pane employees-page" id="employees-view">
										<div class="row">
											<div class="col-md-12">
												<p>Below is a list of all employees currently in the
													simulation environment.</p>
												<div class="table-responsive">
													<table class="table sim-emp-details header-fixed">
														<thead>
															<tr>
																<th>ID</th>
																<th><img class="trait-img" title="Communication"
																	alt="communication"
																	src="<c:url value='/resources/images/communication-icon.png'/>"></th>
																<th><img class="trait-img" title="Empathy"
																	alt="empathy"
																	src="<c:url value='/resources/images/empathy-icon.png'/>"></th>
																<th><img class="trait-img" title="Initiative"
																	alt="initiative"
																	src="<c:url value='/resources/images/initiative-icon.png'/>"></th>
																<th><img class="trait-img" title="Intelligence"
																	alt="intelligence"
																	src="<c:url value='/resources/images/intelligence-icon.png'/>"></th>
																<th><img class="trait-img" title="Motivation"
																	alt="motivation"
																	src="<c:url value='/resources/images/motivation-icon.png'/>"></th>
																<th><img class="trait-img" title="Patience"
																	alt="patience"
																	src="<c:url value='/resources/images/patience-icon.png'/>"></th>
																<th><img class="trait-img" title="Experience"
																	alt="experience"
																	src="<c:url value='/resources/images/experience-icon.jpg'/>"></th>
																<th></th>
																<th></th>
															</tr>
														</thead>

														<tbody>
															<c:forEach items="${ simEmployees }" var="simEmp">


																<tr>
																	<td>${ simEmp.employeeId }</td>
																	<td>${ simEmp.communication }</td>
																	<td>${ simEmp.empathy }</td>
																	<td>${ simEmp.initiative }</td>
																	<td>${ simEmp.intelligence }</td>
																	<td>${ simEmp.motivation }</td>
																	<td>${ simEmp.patience }</td>
																	<td>${ simEmp.experience }</td>
																	<td><span class="glyphicon glyphicon-cog"
																		aria-hidden="true"></span></td>
																	<td><span class="glyphicon glyphicon-remove"
																		aria-hidden="true"></span></td>

																</tr>


															</c:forEach>
														</tbody>

													</table>
												</div>
												<!-- end table responsive -->



											</div>
										</div>
									</div>
									<!-- end pane -->
								</div>
								<!-- end tab content -->

							</div>
							<!-- end panel body -->
						</div>
						<!-- end panel -->
					</div>
					<!-- end row -->
				</div>
				<!-- end tab pane -->


				<!-- Simulation Control Tab 
				
			Desc:	This tab provides settings related to running simulation (ReportGenerator)
					including duration, start date, enable quitting, include weekends(?)
		 -->

				<div id="simulation-settings" class="tab-pane">
					<div class="row">
						<div class="col-md-8 col-md-offset-2">
							<h5>Simulation settings and control</h5>
							<sf:form class="form" method="post" commandName="reportGenerator"
								action="${ pageContext.request.contextPath }/simulator/${ summarySimId }/run">
								<div class="form-group form-inline row">
									<div class="col-md-8">
										<label for="startDate" class="form-control">Start Date</label>
									</div>
									<div class="col-md-4">
										<sf:input class="form-control" path="startDate"
											name="startDate" type="date" />
									</div>
								</div>
								<div class="form-group form-inline row">
									<div class="col-md-8">
										<label for="duration" class="form-control">Duration
											(in days)</label>
									</div>
									<div class="col-md-4">
										<sf:input class="form-control" path="duration" name="duration"
											type="number" max="1000" min="1" />
									</div>
								</div>
							</sf:form>

						</div>
					</div>
				</div>


				<!-- Overview Tab 
		
			Desc: 	This tab provides an overview or summary of results of the simulation including averages for each indicator &
					a pie chart representing count of each 'class' of agent [very good, good, .. very bad]
		
		-->
				<div id="overview" class="tab-pane">
					<div class="panel panel-default">
						<div class="panel-heading">
							<div class="panel-title">Results Dashboard</div>
						</div>
						<div class="panel-body">
							<div class="row dashboard">
								<div class="col-md-6">
									<canvas class="chart" id="ahtBarChart" width="100" height="100"></canvas>
									<canvas class="chart" id="acwBarChart" width="100" height="100"></canvas>
									<canvas class="chart" id="callQBarChart" width="100" height="100"></canvas>
									<canvas class="chart" id="fcrBarChart" width="100" height="100"></canvas>
									<canvas class="chart" id="custSatBarChart" width="100" height="100"></canvas>
									<canvas class="chart" id="numCallsBarChart" width="100" height="100"></canvas>
								</div>
								<div class="col-md-3">
									<canvas class="chart" id="employeePieChart" width="400" height="400"></canvas>
								</div>
							</div>

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
														<td id="callQ"><c:out value="${ report.callQuality }"></c:out></td>
														<td id="custSat"><c:out value="${ report.custSat }"></c:out></td>
														<td id="numCalls"><c:out value="${ report.numCalls }"></c:out></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
									<!-- table responsive -->
								</div>
								<!-- panel body -->
							</div>
							<!-- panel -->
						</div>
						<!-- col -->
					</div>
					<!-- row -->
				</div>
				<!-- tab pane -->
			</div>
			<!-- tab content -->



			<!--  hidden table of values to be used by javascript library for graphing -->
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
						<td id="custSat"><c:out value="${ summaryReport.avgCustSat }"></c:out></td>
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
			<!-- end hidden row -->
		</div>
		<!--  end panel body -->


	</div>
	<!-- end panel -->



</div>
<!-- end col-md-9 -->