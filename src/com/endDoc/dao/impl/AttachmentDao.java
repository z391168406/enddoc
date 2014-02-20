package com.endDoc.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.endDoc.dao.IAttachmentDao;
import com.endDoc.po.AttachmentPo;

public class AttachmentDao extends HibernateDaoSupport implements
		IAttachmentDao {
	public String addAttachment(List<AttachmentPo> las) {
		// TODO Auto-generated method stub
		Serializable serializable=null;
		for(int i=0;i<las.size();i++){
			serializable = this.getHibernateTemplate().save(las.get(i));
		}
		return serializable == null ? "error":"success";
	}
	public List<AttachmentPo> queryByDid(int id) {
		String hql = "from AttachmentPo where did = ? ";
		List<AttachmentPo> ljs = super.getHibernateTemplate().find(hql, id);
		return ljs;
	}
}
