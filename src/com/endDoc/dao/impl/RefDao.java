package com.endDoc.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.endDoc.dao.IRefDao;
import com.endDoc.po.RefTypePo;
import com.endDoc.po.ReferencePo;

public class RefDao extends HibernateDaoSupport implements IRefDao {
	public List<RefTypePo> findAll() {
		String hql = "from RefTypePo order by id";
		List<RefTypePo> ljs = super.getHibernateTemplate().find(hql);
		return ljs;
	}

	public List<ReferencePo> queryByDid(int id) {
		String hql = "from ReferencePo where src_did = ? ";
		List<ReferencePo> ljs = super.getHibernateTemplate().find(hql, id);
		return ljs;
	}

	public List<ReferencePo> queryReferenced(int id) {
		String hql = "from ReferencePo where des_did = ? ";
		List<ReferencePo> ljs = super.getHibernateTemplate().find(hql, id);
		return ljs;
	}

	public boolean updateRefType(RefTypePo cpo) {
		RefTypePo refType = super.getHibernateTemplate().get(RefTypePo.class,
				cpo.getId());
		refType.setType(cpo.getType());
		super.getHibernateTemplate().update(refType);
		return true;
	}

	public long addRefType(RefTypePo cpo) {
		Serializable serializable = super.getHibernateTemplate().save(cpo);
		return serializable == null ? -1 : Long.parseLong(serializable
				.toString());
	}

	public boolean deleteRefType(int rid) {
		RefTypePo cpo = super.getHibernateTemplate().get(RefTypePo.class, rid);
		if (cpo == null) {
			return false;
		}
		super.getHibernateTemplate().delete(cpo);
		return true;
	}

	public String addReference(ReferencePo referencePo) {
		Serializable serializable = this.getHibernateTemplate().save(
				referencePo);
		return serializable == null ? "error" : "success";
	}

	public boolean deleteReference(int rid) {
		ReferencePo cpo = super.getHibernateTemplate().get(ReferencePo.class,
				rid);
		if (cpo == null) {
			return false;
		}
		super.getHibernateTemplate().delete(cpo);
		return true;
	}
}
