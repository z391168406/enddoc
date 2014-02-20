package com.endDoc.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.endDoc.dao.IUserDao;
import com.endDoc.po.UserPo;

public class UserDao extends HibernateDaoSupport implements IUserDao {

	public String login(String username, String password) {
		String hql = "from UserPo where username = ? and password = ?";
		List<UserPo> list = this.getHibernateTemplate().find(hql,new String[] { username, password });
		UserPo user = list.size() == 0 ? null : list.get(0);
		if (user == null)
			return null;
		else
			return user.getUsername();
	};

	public boolean addUser(UserPo user) {
		Serializable serializable = super.getHibernateTemplate().save(user);
		return serializable == null ? false : true;
	};

	public boolean updateUser(UserPo user) {
		UserPo cpo = super.getHibernateTemplate().get(UserPo.class,user.getUsername());
		cpo.setName(user.getName());
		cpo.setGender(user.getGender());
		cpo.setPhone(user.getPhone());
		cpo.setEmail(user.getEmail());
		cpo.setIdentity(user.getIdentity());
		super.getHibernateTemplate().update(cpo);
		return true;
	}

	public List<UserPo> findAll() {
		String hql = "from UserPo order by username";
		List<UserPo> ljs = super.getHibernateTemplate().find(hql);
		return ljs;
	}

	public List<UserPo> find(String username) {
		String hql = "from UserPo where username = ? ";
		List<UserPo> ljs = super.getHibernateTemplate().find(hql, username);
		return ljs;
	}

	public List<UserPo> search(String username) {
		if (username.length() != 0) {
			return find(username);
		} else
			return findAll();
	}

	public boolean updatePassword(UserPo cpo) {
		UserPo user = super.getHibernateTemplate().get(UserPo.class,
				cpo.getUsername());
		user.setPassword(cpo.getPassword());
		super.getHibernateTemplate().update(user);
		return true;
	}

	public boolean deleteUser(String username) {
		UserPo cpo = super.getHibernateTemplate().get(UserPo.class, username);
		if (cpo == null) {
			return false;
		}
		super.getHibernateTemplate().delete(cpo);
		return true;
	}
}
