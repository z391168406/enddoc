package com.endDoc.service.impl;

import java.util.List;

import com.endDoc.dao.IRateDao;
import com.endDoc.po.RateInfoPo;
import com.endDoc.service.IRateService;


public class RateService implements IRateService {
	IRateDao rateDao;

	public String addRate(RateInfoPo rateInfoPo) {
		// TODO Auto-generated method stub
		return rateDao.addRate(rateInfoPo);
	}
	public List<RateInfoPo> queryScoreByDid(String did) {
		// TODO Auto-generated method stub
		return rateDao.queryScoreByDid(did);
	}
	
	public IRateDao getRateDao() {
		return rateDao;
	}

	public void setRateDao(IRateDao rateDao) {
		this.rateDao = rateDao;
	}
	
	
}
