package com.endDoc.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.endDoc.dao.ITagDao;
import com.endDoc.po.DefaultTagPo;
import com.endDoc.po.TagPo;

public class TagDao extends HibernateDaoSupport implements ITagDao {
	public List<DefaultTagPo> findAll() {
		String hql = "from DefaultTagPo order by id";
		List<DefaultTagPo> ljs = super.getHibernateTemplate().find(hql);
		return ljs;
	}

	public List<TagPo> queryByDid(int id) {
		String hql = "from TagPo where did = ? ";
		List<TagPo> ljs = super.getHibernateTemplate().find(hql, id);
		return ljs;
	}

	public boolean updateDefaultTag(DefaultTagPo cpo) {
		DefaultTagPo tag = super.getHibernateTemplate().get(DefaultTagPo.class,
				cpo.getId());
		tag.setTitle(cpo.getTitle());
		super.getHibernateTemplate().update(tag);
		return true;
	}

	public long addDefaultTag(DefaultTagPo cpo) {
		Serializable serializable = super.getHibernateTemplate().save(cpo);
		return serializable == null ? -1 : Long.parseLong(serializable
				.toString());
	}

	public boolean deleteDefaultTag(int tid) {
		DefaultTagPo cpo = super.getHibernateTemplate().get(DefaultTagPo.class,
				tid);
		if (cpo == null) {
			return false;
		}
		super.getHibernateTemplate().delete(cpo);
		return true;
	}

	public long addTag(TagPo cpo) {
		Serializable serializable = super.getHibernateTemplate().save(cpo);
		return serializable == null ? -1 : Long.parseLong(serializable
				.toString());
	}

	public boolean deleteTag(int tid) {
		TagPo cpo = super.getHibernateTemplate().get(TagPo.class, tid);
		if (cpo == null) {
			return false;
		}
		super.getHibernateTemplate().delete(cpo);
		return true;
	}
}
