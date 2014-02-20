package com.endDoc.action;

import java.util.List;

import com.endDoc.po.RateInfoPo;
import com.endDoc.service.IRateService;
import com.opensymphony.xwork2.ActionSupport;


public class RateAction extends ActionSupport {
	IRateService rateService;
	private String did;
	private String score;
	
	public String addRate() throws Exception{
		RateInfoPo rateInfoPo = new RateInfoPo();
		rateInfoPo.setDid(Integer.parseInt(did));
		rateInfoPo.setScore(Integer.parseInt(score));
		rateService.addRate(rateInfoPo);
		return super.execute();
	}
	
	public String getDid() {
		return did;
	}
	public void setDid(String did) {
		this.did = did;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public void setRateService(IRateService rateService) {
		this.rateService = rateService;
	}
	
}
