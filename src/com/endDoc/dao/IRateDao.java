package com.endDoc.dao;

import java.util.List;

import com.endDoc.po.RateInfoPo;


public interface IRateDao {
	String addRate(RateInfoPo rateInfoPo);
	List<RateInfoPo> queryScoreByDid(String did);
	
}
