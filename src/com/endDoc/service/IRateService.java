package com.endDoc.service;

import java.util.List;

import com.endDoc.po.RateInfoPo;

public interface IRateService {
	String addRate(RateInfoPo rateInfoPo);
	List<RateInfoPo> queryScoreByDid(String did);
}
