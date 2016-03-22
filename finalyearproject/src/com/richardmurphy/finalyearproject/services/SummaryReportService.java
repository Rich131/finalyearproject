package com.richardmurphy.finalyearproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.richardmurphy.finalyearproject.dao.SummaryReport;
import com.richardmurphy.finalyearproject.dao.SummaryReportDAO;

@Service("summaryReportService")
public class SummaryReportService {
	private SummaryReportDAO summaryReportDao;

	@Autowired
	public void setSummaryReportDao(SummaryReportDAO dailyReportDao) {
		this.summaryReportDao = dailyReportDao;
	}
	
	public SummaryReport getSummaryReport(int id) {
		return summaryReportDao.getSummaryReport(id);
	}
	
	public List<SummaryReport> getSummaryReports() {
		return summaryReportDao.getSummaryReports();
	}

	public int createEmpty(SummaryReport report) {
		return summaryReportDao.createEmpty(report);
	}

	public int update(SummaryReport report) {
		return summaryReportDao.update(report);
	}
}
