package com.endDoc.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.endDoc.dao.IAbbreviationDao;
import com.endDoc.po.AbbreviationPo;

public class AbbreviationDao extends HibernateDaoSupport implements
		IAbbreviationDao {
	public List<AbbreviationPo> findAll() {
		String hql = "from AbbreviationPo order by abbrword";
		List<AbbreviationPo> ljs = super.getHibernateTemplate().find(hql);
		return ljs;
	}

	public boolean updateAbbreviation(AbbreviationPo cpo) {
		AbbreviationPo abbr = super.getHibernateTemplate().get(
				AbbreviationPo.class, cpo.getAbbrword());
		abbr.setFullword(cpo.getFullword());
		super.getHibernateTemplate().update(abbr);
		return true;
	}

	public boolean addAbbreviation(AbbreviationPo cpo) {
		Serializable serializable = super.getHibernateTemplate().save(cpo);
		return serializable == null ? false : true;
	}

	public boolean deleteAbbreviation(String abbrword) {
		AbbreviationPo cpo = super.getHibernateTemplate().get(
				AbbreviationPo.class, abbrword);
		if (cpo == null) {
			return false;
		}
		super.getHibernateTemplate().delete(cpo);
		return true;
	}
}
