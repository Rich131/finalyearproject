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

				return employee;
			}

		});
	}

	// Update single record
	public boolean update(Employee employee) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(employee);

		return jdbc.update("update employees set firstName = :firstName, surname = :surname, "
				+ "departmentId = :departmentId where employeeId = :employeeId", params) == 1;
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
				+ "departmentId = :departmentId where employeeId = :employeeId", batchParams);
	}

	public boolean create(Employee employee) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(employee);

		try {
			return jdbc.update("insert into employees (firstName, surname, departmentId) "
					+ "values (:firstName, :surname, :departmentId)", params) == 1;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}

	@Transactional
	public int[] create(List<Employee> employees) {
		SqlParameterSource[] batchParams = SqlParameterSourceUtils.createBatch(employees.toArray());

		return jdbc.batchUpdate("insert into employees (firstName, surname, departmentId) "
				+ "values (:firstName, :surname, :departmentId)", batchParams);
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

				return employee;
			}

		});
	}

	public List<Employee> getUnregisteredEmployees() {
		// query to select all employee object with no associated user account
		// TODO: Test this
		return jdbc.query("select * from employees e where e.agentId not in (select agentId from users)",
				new RowMapper<Employee>() {

					@Override
					public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
						Employee employee = new Employee();

						employee.setDepartmentId(rs.getInt("departmentId"));
						employee.setEmployeeId(rs.getInt("employeeId"));
						employee.setFirstName(rs.getString("firstName"));
						employee.setSurname(rs.getString("surname"));

						return employee;
					}

				});
	}

	public List<Integer> getEmployeesNotInSim(int simId, int limit) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("simId", simId);
		params.addValue("limit", limit);

		String sql = "SELECT employeeId FROM employees WHERE employeeId NOT IN "
				+ "(SELECT employeeId FROM simemployees WHERE simId = :simId) LIMIT :limit";

		return jdbc.query(sql, params, new RowMapper<Integer>() {

			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getInt("employeeId");
			}
		});

	}

	public List<Employee> getEmployeesNotInSim(int simId) {
		// TODO Auto-generated method stub
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("simId", simId);

		String sql = "SELECT * FROM employees WHERE employeeId NOT IN "
				+ "(SELECT employeeId FROM simemployees WHERE simId = :simId)";
		return jdbc.query(sql, params, new RowMapper<Employee>() {

			@Override
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				Employee employee = new Employee();

				employee.setDepartmentId(rs.getInt("departmentId"));
				employee.setEmployeeId(rs.getInt("employeeId"));
				employee.setFirstName(rs.getString("firstName"));
				employee.setSurname(rs.getString("surname"));

				return employee;
			}

		});
	}
}
