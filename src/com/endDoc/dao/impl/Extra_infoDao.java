package com.endDoc.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.endDoc.dao.IExtra_infoDao;
import com.endDoc.po.Extra_infoPo;

public class Extra_infoDao extends HibernateDaoSupport implements
		IExtra_infoDao {

	public List<Extra_infoPo> queryByDid(int id) {
		String hql = "from Extra_infoPo where did = ? ";
		List<Extra_infoPo> ljs = super.getHibernateTemplate().find(hql, id);
		return ljs;
	}
}
