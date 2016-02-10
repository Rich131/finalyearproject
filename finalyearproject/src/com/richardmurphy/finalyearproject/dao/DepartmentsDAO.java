package com.richardmurphy.finalyearproject.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component("departmentsDao")
public class DepartmentsDAO {

	private NamedParameterJdbcTemplate jdbc;

	/*
	 * Annotation autowiring to wire fully configured data source to
	 * JdbcTemplate
	 * 
	 */
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	/*
	 * Returns a list of department objects
	 * 
	 */
	public List<Department> getDepartments() {
		return jdbc.query("select * from departments", new RowMapper<Department>() {

			@Override
			public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
				Department department = new Department();

				// getInt.. must match column name in DB
				department.setDepartmentId(rs.getInt("departmentId"));
				department.setName(rs.getString("name"));

				return department;
			}

		});
	}

	public Department getDepartment(int id) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		return jdbc.queryForObject("select * from departments where departmentId = :id", params, new RowMapper<Department>() {

			@Override
			public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
				Department department = new Department();
				department.setDepartmentId(rs.getInt("departmentId"));
				department.setName(rs.getString("name"));

				return department;
			}

		});
	}

}
