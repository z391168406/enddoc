package com.endDoc.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.endDoc.dao.IBriefCommentDao;
import com.endDoc.po.BriefCommentPo;



public class BriefCommentDao extends HibernateDaoSupport implements IBriefCommentDao {
	
	public String addBriefComment(BriefCommentPo briefCommentPo) {
		// TODO Auto-generated method stub
		Serializable serializable = this.getHibernateTemplate().save(briefCommentPo);
		return serializable == null ? "error" : "success";
	}
	
	public List<BriefCommentPo> queryByDid(String did) {
		// TODO Auto-generated method stub
		String hql = "from BriefCommentPo where did = ? and isDraft = ?";
		Object[] objects = {Integer.parseInt(did), false};
		List<BriefCommentPo> lbs = super.getHibernateTemplate().find(hql, objects);
		return lbs;
	}
	
	public List<BriefCommentPo> showDraft(String did, String username) {
		// TODO Auto-generated method stub
		String hql = "from BriefCommentPo where did = ? and username = ? and isDraft = ?";
		Object[] objects = {Integer.parseInt(did), username, true};
		List<BriefCommentPo> lbs = super.getHibernateTemplate().find(hql, objects);
		return lbs;
	}
}
