package com.endDoc.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.endDoc.dao.IUserDao;
import com.endDoc.po.UserPo;
import com.endDoc.service.IUserService;
import com.endDoc.vo.UserVo;

public class UserService implements IUserService {
	IUserDao userDao;

	public String login(String username, String password) {
		return userDao.login(username, password);
	}

	public List<UserVo> search(String username) {
		List<UserPo> ljs = userDao.search(username);
		List<UserVo> ljvos = p2v(ljs);
		return ljvos;
	}

	public List<UserVo> findAll() {
		List<UserPo> ljs = userDao.findAll();
		List<UserVo> ljvos = p2v(ljs);
		return ljvos;
	}

	public List<UserVo> find(String username) {
		return p2v(userDao.find(username));
	}

	public boolean addUser(UserVo cvo) {
		return userDao.addUser(v2p(cvo));
	}

	public boolean updateUser(UserVo cvo) {
		UserPo cpo = v2p(cvo);
		return userDao.updateUser(cpo);
	}

	public boolean updatePassword(UserVo cvo) {
		UserPo cpo = v2p(cvo);
		return userDao.updatePassword(cpo);
	}

	public boolean deleteUser(String username) {
		return userDao.deleteUser(username);
	}

	private List<UserVo> p2v(List<UserPo> ljs) {
		List<UserVo> ljvos = new ArrayList<UserVo>();
		for (UserPo cm : ljs) {
			ljvos.add(p2v(cm));
		}
		return ljvos;
	}

	private UserPo v2p(UserVo cm) {
		UserPo cpo = new UserPo();
		cpo.setUsername(cm.getUsername());
		cpo.setPassword(cm.getPassword());
		cpo.setName(cm.getName());
		cpo.setGender(cm.getGender());
		cpo.setEmail(cm.getEmail());
		cpo.setPhone(cm.getPhone());
		cpo.setIdentity(cm.getIdentity());
		return cpo;
	}

	private UserVo p2v(UserPo cm) {
		UserVo cvo = new UserVo();
		cvo.setUsername(cm.getUsername());
		cvo.setPassword(cm.getPassword());
		cvo.setName(cm.getName());
		cvo.setGender(cm.getGender());
		cvo.setEmail(cm.getEmail());
		cvo.setPhone(cm.getPhone());
		cvo.setIdentity(cm.getIdentity());
		return cvo;
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

}
