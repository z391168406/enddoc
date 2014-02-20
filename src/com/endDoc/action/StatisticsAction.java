package com.endDoc.action;

import java.util.List;

import com.endDoc.service.IStatisticsService;
import com.endDoc.vo.StatisticsVo;
import com.opensymphony.xwork2.ActionSupport;

public class StatisticsAction extends ActionSupport {
	IStatisticsService statisticsService;
	List<StatisticsVo> statisticsList;
	private String username;
	private String time;

	public String statistics() throws Exception {
		statisticsList = statisticsService.statistics(username, time);
		return super.execute();
	}

	public List<StatisticsVo> getStatisticsList() {
		return statisticsList;
	}

	public void setStatisticsList(List<StatisticsVo> statisticsList) {
		this.statisticsList = statisticsList;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setStatisticsService(IStatisticsService statisticsService) {
		this.statisticsService = statisticsService;
	}
}
