package com.endDoc.action;

import com.endDoc.service.IUserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	IUserService userService;
	private String username;
	private String password;
	private String message;

	public String login() throws Exception {
		message = userService.login(username, password);
		if ((message != null)) {
			ActionContext.getContext().getSession()
					.put("username", getUsername());
			message = userService.find(username).get(0).getIdentity();
			ActionContext.getContext().getSession().put("identity", message);
			return SUCCESS;
		} else
			return ERROR;
	}

	public String logout() throws Exception {
		ActionContext.getContext().getSession().clear();
		return SUCCESS;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
