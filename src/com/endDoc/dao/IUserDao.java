package com.endDoc.dao;

import java.util.List;

import com.endDoc.po.UserPo;

public interface IUserDao {
	String login(String username, String password);

	List<UserPo> find(String username);

	boolean updateUser(UserPo cpo);

	boolean updatePassword(UserPo cpo);

	List<UserPo> findAll();

	boolean deleteUser(String username);

	boolean addUser(UserPo po);

	List<UserPo> search(String username);
}
