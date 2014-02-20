package com.endDoc.service;

import java.util.List;

import com.endDoc.vo.UserVo;

public interface IUserService {
	String login(String username, String password);

	List<UserVo> find(String username);

	boolean updateUser(UserVo cvo);

	boolean updatePassword(UserVo cvo);

	List<UserVo> findAll();

	boolean deleteUser(String username);

	boolean addUser(UserVo cvo);

	List<UserVo> search(String username);
}
