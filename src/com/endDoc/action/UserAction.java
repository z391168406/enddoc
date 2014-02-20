package com.endDoc.action;

import java.util.List;

import com.endDoc.service.IUserService;
import com.endDoc.vo.UserVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {
	private String username;
	private String password;
	private String newPassword;
	private String name;
	private String identity;
	private String gender;
	private String phone;
	private String email;
	IUserService userService;
	List<UserVo> ldvos;

	public String findall() throws Exception {
		ldvos = userService.findAll();
		return super.execute();
	}

	public String find() throws Exception {
		ldvos = userService.find(username);
		return super.execute();
	}

	public String search() throws Exception {
		ldvos = userService.search(username);
		return super.execute();
	}

	public String saveInfo() throws Exception {
		UserVo cvo = new UserVo();
		username = (String) ActionContext.getContext().getSession()
				.get("username");
		cvo.setUsername(username);
		cvo.setName(name);
		cvo.setGender(gender);
		cvo.setIdentity(identity);
		cvo.setEmail(email);
		cvo.setPhone(phone);
		userService.updateUser(cvo);
		return super.execute();
	}

	public String savePassword() throws Exception {
		ldvos = userService.find(username);
		UserVo cvo = ldvos.get(0);
		if (!password.equals(newPassword))
			return ERROR;
		else
			cvo.setPassword(newPassword);
		userService.updatePassword(cvo);
		return super.execute();
	}

	public String delUser() throws Exception {
		userService.deleteUser(username);
		return super.execute();
	}

	public String addUser() throws Exception {
		UserVo cvo = new UserVo();
		cvo.setUsername(username);
		cvo.setPassword(password);
		userService.addUser(cvo);
		return super.execute();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<UserVo> getLdvos() {
		return ldvos;
	}

	public void setLdvos(List<UserVo> ldvos) {
		this.ldvos = ldvos;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

}
