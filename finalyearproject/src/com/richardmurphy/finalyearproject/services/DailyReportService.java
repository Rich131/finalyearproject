package com.richardmurphy.finalyearproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.richardmurphy.finalyearproject.dao.DailyReport;
import com.richardmurphy.finalyearproject.dao.DailyReportDAO;


@Service("dailyReportService")
public class DailyReportService {
	private DailyReportDAO dailyReportDao;
	
	@Autowired
	public void setDailyReportDao(DailyReportDAO dailyReportDao) {
		this.dailyReportDao = dailyReportDao;
	}

	public List<DailyReport> getDailyReports() {
		return dailyReportDao.getDailyReports();
	}
	
	public List<DailyReport> getDailyReportsBySimId(int simId){
		return dailyReportDao.getDailyReports(simId);
	}
	
	public boolean create(List<DailyReport> reports) {
		int[] result = dailyReportDao.create(reports);
		
		if (result != null)
			return true;
		else
			return false;
	}

	public List<DailyReport> getDailyReportsBySimIdAndEmpId(int simId, int empId) {
		return dailyReportDao.getDailyReportsBySimIdAndEmpId(simId, empId);
	}
}
