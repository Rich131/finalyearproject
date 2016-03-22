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
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("summaryReportDao")
public class SummaryReportDAO {

	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	/*
	 * uses anonymous RowMapper class to create a list of SummaryReports from
	 * the ResultSet object returned from the SQL query
	 */
	public List<SummaryReport> getSummaryReports() {

		return jdbc.query("select * from summaryReport order by simId", new RowMapper<SummaryReport>() {

			@Override
			public SummaryReport mapRow(ResultSet rs, int rowNum) throws SQLException {
				SummaryReport report = new SummaryReport();

				report.setSimId(rs.getInt("simId"));

				report.setSimName(rs.getString("name"));
				report.setAvgAht(rs.getDouble("avgAht"));
				report.setAvgAcw(rs.getDouble("avgAcw"));
				report.setAvgCallQuality(rs.getDouble("avgCallQuality"));
				report.setAvgCustSat(rs.getDouble("avgCustSat"));
				report.setAvgFcr(rs.getDouble("avgFcr"));
				report.setAvgNumCalls(rs.getDouble("avgNumCalls"));

				report.setCountVG(rs.getInt("countVG"));
				report.setCountG(rs.getInt("countG"));
				report.setCountAvg(rs.getInt("countAvg"));
				report.setCountB(rs.getInt("countB"));
				report.setCountVB(rs.getInt("countVB"));

				return report;
			}

		});
	}

	public SummaryReport getSummaryReport(int simId) {

		MapSqlParameterSource params = new MapSqlParameterSource("simId", simId);

		return jdbc.queryForObject("select * from summaryReport where simId = :simId", params,
				new RowMapper<SummaryReport>() {

					@Override
					public SummaryReport mapRow(ResultSet rs, int rowNum) throws SQLException {
						SummaryReport report = new SummaryReport();

						report.setSimId(rs.getInt("simId"));
						report.setSimName(rs.getString("name"));

						report.setAvgAht(rs.getDouble("avgAht"));
						report.setAvgAcw(rs.getDouble("avgAcw"));
						report.setAvgCallQuality(rs.getDouble("avgCallQuality"));
						report.setAvgCustSat(rs.getDouble("avgCustSat"));
						report.setAvgFcr(rs.getDouble("avgFcr"));
						report.setAvgNumCalls(rs.getDouble("avgNumCalls"));

						report.setCountVG(rs.getInt("countVG"));
						report.setCountG(rs.getInt("countG"));
						report.setCountAvg(rs.getInt("countAvg"));
						report.setCountB(rs.getInt("countB"));
						report.setCountVB(rs.getInt("countVB"));

						return report;
					}

				});
	}

	public int update(SummaryReport report) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(report);

		String sql = "update summaryReport set name = :simName, avgAht = :avgAht, avgAcw = :avgAcw, "
				+ "avgCallQuality = :avgCallQuality, avgCustSat = :avgCustSat, avgFcr = :avgFcr,  "
				+ "avgNumCalls = :avgNumCalls, countVG = :countVG, countG = :countG, countAvg = :countAvg, "
				+ "countB = :countB, countVB = :countVB " + "where simId = :simId ";

		return jdbc.update(sql, params);
	}

	public boolean create(SummaryReport report) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(report);

		return jdbc.update(
				"insert into summaryReport (name, numDays, avgAht, avgAcw, avgCustSat, avgCallQuality, avgFcr, avgNumCalls, countVG, countG, countAvg, countB, countVB) "
						+ "values (:simName, :numDays, :avgAht, :avgAcw, :avgCustSat, :avgCallQuality, :avgFcr, :avgNumCalls, :countVG, :countG, :countAvg, :countB, :countVB)",
				params) == 1;
	}

	// create empty summary and return newly auto-assigned ID
	public int createEmpty(SummaryReport report) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(report);
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbc.update("insert into summaryReport (name) " + "values (:simName)", params, keyHolder,
				new String[] { "simId" });

		return keyHolder.getKey().intValue();
	}

	@Transactional
	public int[] create(List<SummaryReport> reports) {
		SqlParameterSource[] batchParams = SqlParameterSourceUtils.createBatch(reports.toArray());

		return jdbc.batchUpdate(
				"insert into summaryReport (name, avgAht, avgAcw, avgCustSat, avgCallQuality, avgFcr, avgNumCalls, countVG, countG, countAvg, countB, countVB) "
						+ "values (:simName, :avgAht, :avgAcw, :avgCustSat, :avgCallQuality, :avgFcr, :avgNumCalls, :countVG, :countG, :countAvg, :countB, :countVB)",
				batchParams);
	}

	public boolean delete(int id) {
		MapSqlParameterSource params = new MapSqlParameterSource("id", id);

		return jdbc.update("delete from summaryReport where simId = :id", params) == 1;
	}

}
