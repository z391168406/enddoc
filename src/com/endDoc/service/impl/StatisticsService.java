package com.endDoc.service.impl;

import java.util.List;

import com.endDoc.dao.IStatisticsDao;
import com.endDoc.service.IStatisticsService;
import com.endDoc.vo.StatisticsVo;

public class StatisticsService implements IStatisticsService {
	IStatisticsDao statisticsDao;

	public List<StatisticsVo> statistics(String username, String time) {
		List<StatisticsVo> ljs = statisticsDao.statistics(username, time);
		return ljs;
	}

	public IStatisticsDao getStatisticsDao() {
		return statisticsDao;
	}

	public void setStatisticsDao(IStatisticsDao statisticsDao) {
		this.statisticsDao = statisticsDao;
	}
}
