package com.richardmurphy.finalyearproject.dao;

import javax.sql.DataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("employeesDao")
public class EmployeesDAO {

	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	/*
	 * uses anonymous RowMapper class to create a list of Employees from the
	 * ResultSet object returned from the SQL query
	 */
	public List<Employee> getEmployees() {

		return jdbc.query("select * from employees", new RowMapper<Employee>() {

			@Override
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				Employee employee = new Employee();

				employee.setDepartmentId(rs.getInt("departmentId"));
				employee.setEmployeeId(rs.getInt("employeeId"));
				employee.setFirstName(rs.getString("firstName"));
				employee.setSurname(rs.getString("surname"));
				employee.setStartDate(rs.getString("startDate"));
				employee.setPatience(rs.getInt("patience"));
				employee.setIntelligence(rs.getInt("intelligence"));
				employee.setEmpathy(rs.getInt("empathy"));
				employee.setExperience(rs.getInt("experience"));
				employee.setMotivation(rs.getInt("motivation"));

				return employee;
			}

		});
	}

	// Update single record
	public boolean update(Employee employee) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(employee);

		return jdbc.update("update employees set firstName = :firstName, surname = :surname, "
				+ "startDate = :startDate, departmentId = :departmentId, intelligence = :intelligence, "
				+ "patience = :patience, empathy = :empathy, motivation = :motivation, experience = :experience "
				+ "where employeeId = :employeeId", params) == 1;
	}

	// Update batch of records
	@Transactional
	public int[] update(List<Employee> employees) {

		/*
		 * batchUpdate takes an array of SqlParameterSource objects
		 * SqlParameterSourceUtils.createBatch makes an array of
		 * SqlParameterSource objects from an array of beans/model objects
		 */
		SqlParameterSource[] batchParams = SqlParameterSourceUtils.createBatch(employees.toArray());

		return jdbc.batchUpdate("update employees set firstName = :firstName, surname = :surname, "
				+ "startDate = :startDate, departmentId = :departmentId, intelligence = :intelligence, "
				+ "patience = :patience, empathy = :empathy, motivation = :motivation, experience = :experience "
				+ "where employeeId = :employeeId", batchParams);
	}

	public boolean create(Employee employee) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(employee);
		
		try {
			return jdbc.update(
					"insert into employees (firstName, surname, startDate, departmentId, intelligence, "
					+ "patience, motivation, empathy, experience) "
					+ "values (:firstName, :surname, :startDate, :departmentId, :intelligence, "
					+ ":patience, :motivation, :empathy, :experience)",params) == 1;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}

	@Transactional
	public int[] create(List<Employee> employees) {
		SqlParameterSource[] batchParams = SqlParameterSourceUtils.createBatch(employees.toArray());

		return jdbc.batchUpdate(
				"insert into employees (firstName, surname, startDate, departmentId, intelligence, "
				+ "patience, motivation, empathy, experience) "
				+ "values (:firstName, :surname, :startDate, :departmentId, :intelligence, :patience, "
				+ ":motivation, :empathy, :experience)",batchParams);
	}

	public boolean delete(int id) {
		MapSqlParameterSource params = new MapSqlParameterSource("id", id);

		return jdbc.update("delete from employees where employeeid = :id", params) == 1;
	}

	public Employee getEmployee(int id) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		return jdbc.queryForObject("select * from employees where employeeId = :id", params, new RowMapper<Employee>() {

			@Override
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				Employee employee = new Employee();

				employee.setDepartmentId(rs.getInt("departmentId"));
				employee.setEmployeeId(rs.getInt("employeeId"));
				employee.setFirstName(rs.getString("firstName"));
				employee.setSurname(rs.getString("surname"));
				employee.setStartDate(rs.getString("startDate"));
				employee.setPatience(rs.getInt("patience"));
				employee.setIntelligence(rs.getInt("intelligence"));

				return employee;
			}

		});
	}

	public List<Employee> getUnregisteredEmployees() {
		// query to select all employee object with no associated user account
		// TODO: Test this 
		return jdbc.query("select * from employees e where e.agentId not in (select agentId from users)", new RowMapper<Employee>() {

			@Override
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				Employee employee = new Employee();

				employee.setDepartmentId(rs.getInt("departmentId"));
				employee.setEmployeeId(rs.getInt("employeeId"));
				employee.setFirstName(rs.getString("firstName"));
				employee.setSurname(rs.getString("surname"));
				employee.setStartDate(rs.getString("startDate"));
				employee.setPatience(rs.getInt("patience"));
				employee.setIntelligence(rs.getInt("intelligence"));
				employee.setEmpathy(rs.getInt("empathy"));
				employee.setExperience(rs.getInt("experience"));
				employee.setMotivation(rs.getInt("motivation"));

				return employee;
			}

		});
	}
}
