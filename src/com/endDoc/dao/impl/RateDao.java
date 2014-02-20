package com.endDoc.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.endDoc.dao.IRateDao;
import com.endDoc.po.RateInfoPo;



public class RateDao extends HibernateDaoSupport implements IRateDao {
	


	public String addRate(RateInfoPo rateInfoPo) {
		// TODO Auto-generated method stub
		String hql = "from RateInfoPo where did = ? and score = ? ";
		List<RateInfoPo> rate =  this.getHibernateTemplate().find(hql,rateInfoPo.getDid(),rateInfoPo.getScore());
		rateInfoPo=rate.get(0);
		rateInfoPo.setAmount(rateInfoPo.getAmount()+1);
		this.getHibernateTemplate().update(rateInfoPo);
		return "success";
	}
	
	public List<RateInfoPo> queryScoreByDid(String did) {
		// TODO Auto-generated method stub
		String hql = "from RateInfoPo where did = ?";
		List<RateInfoPo> lds = this.getHibernateTemplate().find(hql,Integer.parseInt(did));
//		float score=0;
//		int amount = 0;
//		for(int i=0;i<lds.size();i++){
//			score+=lds.get(i).getAmount()*lds.get(i).getScore();
//			amount+=lds.get(i).getAmount();
//		}		
		return lds;
	}
}
