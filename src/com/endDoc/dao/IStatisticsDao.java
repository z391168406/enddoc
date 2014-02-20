package com.endDoc.dao;

import java.util.List;

import com.endDoc.vo.StatisticsVo;

public interface IStatisticsDao {

	List<StatisticsVo> statistics(String username, String time);

}
