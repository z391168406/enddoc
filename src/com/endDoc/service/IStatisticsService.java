package com.endDoc.service;

import java.util.List;

import com.endDoc.vo.StatisticsVo;

public interface IStatisticsService {

	List<StatisticsVo> statistics(String username, String time);

}
