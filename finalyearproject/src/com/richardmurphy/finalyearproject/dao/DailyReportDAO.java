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

@Component("dailyReportDao")
public class DailyReportDAO {

	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	/*
	 * uses anonymous RowMapper class to create a list of Employees from the
	 * ResultSet object returned from the SQL query
	 */
	public List<DailyReport> getEmployees() {

		return jdbc.query("select * from dailyReport", new RowMapper<DailyReport>() {

			@Override
			public DailyReport mapRow(ResultSet rs, int rowNum) throws SQLException {
				DailyReport report = new DailyReport();

				report.setEmployeeId(rs.getInt("employeeId"));
				report.setAht(rs.getInt("aht"));
				report.setAcw(rs.getInt("acw"));
				report.setFcr(rs.getInt("fcr"));
				report.setCustSat(rs.getInt("custSat"));
				report.setCallQuality(rs.getInt("callQuality"));
				report.setNumCalls(rs.getInt("numCalls"));
				report.setDate(rs.getString("dateLogged"));

				return report;
			}

		});
	}
	
	// Update single record
	public boolean update(DailyReport report) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(report);
		
		
		return jdbc.update("update dailyReport set employeeId = :employeeId, aht = :aht, "
				+ "acw = :acw, fcr = :fcr, custSat = :custSat, numCalls = :numCalls, "
				+ "callQuality = :callQuality where employeeId = :employeeId and dateLogged = :dateLogged", params) == 1;
	}
	
	// Update batch of records
	@Transactional
	public int[] update(List<DailyReport> reports) {
		
		/*	batchUpdate takes an array of SqlParameterSource objects
		 * 	SqlParameterSourceUtils.createBatch makes an array of SqlParameterSource objects from an array of beans/model objects
		 */
		SqlParameterSource[] batchParams = SqlParameterSourceUtils.createBatch(reports.toArray());
		
		return jdbc.batchUpdate("update dailyReport set employeeId = :employeeId, aht = :aht, "
				+ "acw = :acw, fcr = :fcr, custSat = :custSat, numCalls = :numCalls, "
				+ "callQuality = :callQuality where employeeId = :employeeId and dateLogged = :dateLogged", batchParams);		
	}
	
	
	public boolean create(DailyReport report) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(report);
		
		return jdbc.update("insert into dailyReport (employeeId, aht, acw, fcr, custSat, callQuality, numCalls, dateLogged) "
				+ "values (:employeeId, :aht, :acw, :fcr, :custSat, :callQuality, :numCalls, :dateLogged)", params) == 1;
	}
	
	@Transactional
	public int[] create(List<DailyReport> reports) {
		SqlParameterSource[] batchParams = SqlParameterSourceUtils.createBatch(reports.toArray());
		
		return jdbc.batchUpdate("insert into dailyReport (employeeId, aht, acw, fcr, custSat, callQuality, numCalls, dateLogged) "
				+ "values (:employeeId, :aht, :acw, :fcr, :custSat, :callQuality, :numCalls, :dateLogged)", batchParams);
	}
	
	public boolean delete(int id) {
		MapSqlParameterSource params = new MapSqlParameterSource("id", id);
		
		
		return jdbc.update("delete from dailyReport where employeeid = :id and dateLogged = :dateLogged", params) == 1;
	}
	
	public DailyReport getDailyReport(int id, String dateLogged) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		return jdbc.queryForObject("select * from dailyReport where employeeId = :id and dateLogged = :dateLogged", params, new RowMapper<DailyReport>() {

			@Override
			public DailyReport mapRow(ResultSet rs, int rowNum) throws SQLException {
				DailyReport report = new DailyReport();

				report.setEmployeeId(rs.getInt("employeeId"));
				report.setAht(rs.getInt("aht"));
				report.setAcw(rs.getInt("acw"));
				report.setFcr(rs.getInt("fcr"));
				report.setCustSat(rs.getInt("custSat"));
				report.setCallQuality(rs.getInt("callQuality"));
				report.setNumCalls(rs.getInt("numCalls"));
				report.setDate(rs.getString("dateLogged"));

				return report;
			}

		});
	}
}
