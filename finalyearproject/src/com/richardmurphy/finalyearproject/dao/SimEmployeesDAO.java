package com.richardmurphy.finalyearproject.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("simEmployeesDao")
public class SimEmployeesDAO {
	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	/*
	 * TODO: Queries required - get List<SimEmployee> by simId - get SimEmployee
	 * by employeeId - create List<SimEmployee> <- will be called by
	 * SimulationController (using service)
	 * 
	 */

	public List<SimEmployee> getSimEmployees(int simId) {

		MapSqlParameterSource params = new MapSqlParameterSource("simId", simId);

		return jdbc.query("SELECT * FROM simEmployees WHERE simId = :simId ORDER BY employeeId", params,
				new RowMapper<SimEmployee>() {
					@Override
					public SimEmployee mapRow(ResultSet rs, int rowNum) throws SQLException {
						SimEmployee simEmp = new SimEmployee();

						simEmp.setEmployeeId(rs.getInt("employeeId"));
						simEmp.setLeaveDate(rs.getDate("leaveDate"));
						simEmp.setSimId(rs.getInt("simId"));
						simEmp.setStartDate(rs.getDate("startDate"));

						simEmp.setPatience(rs.getInt("patience"));
						simEmp.setIntelligence(rs.getInt("intelligence"));
						simEmp.setEmpathy(rs.getInt("empathy"));
						simEmp.setExperience(rs.getInt("experience"));
						simEmp.setMotivation(rs.getInt("motivation"));
						simEmp.setInitiative(rs.getInt("initiative"));
						simEmp.setCommunication(rs.getInt("communication"));
						simEmp.setIsCurrentEmployee(rs.getBoolean("isCurrentEmployee"));

						return simEmp;
					}
				});
	}

	public SimEmployee getEmployee(SimEmployee simEmployee) {

		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(simEmployee);

		return jdbc.queryForObject("select * from simemployees where employeeId = :employeeid and simId = :simId",
				params, new RowMapper<SimEmployee>() {

					@Override
					public SimEmployee mapRow(ResultSet rs, int rowNum) throws SQLException {
						SimEmployee simEmp = new SimEmployee();

						simEmp.setEmployeeId(rs.getInt("employeeId"));
						simEmp.setLeaveDate(rs.getDate("leaveDate"));
						simEmp.setSimId(rs.getInt("simId"));
						simEmp.setStartDate(rs.getDate("startDate"));

						simEmp.setPatience(rs.getInt("patience"));
						simEmp.setIntelligence(rs.getInt("intelligence"));
						simEmp.setEmpathy(rs.getInt("empathy"));
						simEmp.setExperience(rs.getInt("experience"));
						simEmp.setMotivation(rs.getInt("motivation"));
						simEmp.setInitiative(rs.getInt("initiative"));
						simEmp.setCommunication(rs.getInt("communication"));
						simEmp.setIsCurrentEmployee(rs.getBoolean("isCurrentEmployee"));

						return simEmp;
					}

				});
	}

	@Transactional
	public int[] create(List<SimEmployee> employees) {
		SqlParameterSource[] batchParams = SqlParameterSourceUtils.createBatch(employees.toArray());

		return jdbc.batchUpdate("insert into simEmployees (employeeId, simId, intelligence, initiative, "
				+ "patience, motivation, empathy, experience, communication, isCurrentEmployee) "
				+ "values (:employeeId, :simId, :intelligence, :initiative,  :patience, "
				+ ":motivation, :empathy, :experience, :communication, :isCurrentEmployee)", batchParams);
	}

	public boolean create(SimEmployee emp) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(emp);

		try {
			return jdbc.update("insert into simemployees (employeeId, simId, communication, empathy, experience, "
					+ "initiative, intelligence, motivation, patience, isCurrentEmployee) "
					+ "values (:employeeId, :simId, :communication, :empathy, :experience, "
					+ ":initiative, :intelligence, :motivation, :patience, :isCurrentEmployee)", params) == 1;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}

	public int[] update(List<SimEmployee> simEmployees) {
		SqlParameterSource[] batchParams = SqlParameterSourceUtils.createBatch(simEmployees.toArray());

		return jdbc.batchUpdate(
				"update simemployees set experience = :experience, "
						+ "motivation = :motivation, startDate = :startDate, "
						+ "leaveDate = :leaveDate, isCurrentEmployee = :isCurrentEmployee where employeeId = :employeeId",
				batchParams);
	}

	public SimEmployee getEmployeeBySimIdAndEmpId(int simId, int empId) {
		// TODO Auto-generated method stub

		MapSqlParameterSource params = new MapSqlParameterSource("simId", simId);
		params.addValue("empId", empId);

		String sql = "SELECT * FROM simEmployees WHERE simId = :simId AND employeeId = :empId";

		return jdbc.queryForObject(sql, params, new RowMapper<SimEmployee>() {

			@Override
			public SimEmployee mapRow(ResultSet rs, int rowNum) throws SQLException {
				SimEmployee simEmp = new SimEmployee();

				simEmp.setEmployeeId(rs.getInt("employeeId"));
				simEmp.setLeaveDate(rs.getDate("leaveDate"));
				simEmp.setSimId(rs.getInt("simId"));
				simEmp.setStartDate(rs.getDate("startDate"));

				simEmp.setPatience(rs.getInt("patience"));
				simEmp.setIntelligence(rs.getInt("intelligence"));
				simEmp.setEmpathy(rs.getInt("empathy"));
				simEmp.setExperience(rs.getInt("experience"));
				simEmp.setMotivation(rs.getInt("motivation"));
				simEmp.setInitiative(rs.getInt("initiative"));
				simEmp.setCommunication(rs.getInt("communication"));
				simEmp.setIsCurrentEmployee(rs.getBoolean("isCurrentEmployee"));

				return simEmp;
			}

		});
	}

}
