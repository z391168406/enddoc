package com.endDoc.po;

import java.util.Date;

/**
 * 
 * @author liuyuan
 * 
 */
public class DetailCommentPo {
	private int id;
	private int did;
	private String username;
	private String attribute;
	private String value;
	private Date create_time;
	private Date update_time;

	private boolean isDraft;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getIsDraft() {
		return isDraft;
	}

	public void setIsDraft(boolean isDraft) {
		this.isDraft = isDraft;
	}

	public int getDid() {
		return did;
	}

	public void setDid(int did) {
		this.did = did;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public void setDraft(boolean isDraft) {
		this.isDraft = isDraft;
	}

	

}
