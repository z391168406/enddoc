package com.endDoc.vo;

/**
 * @author zhangqi
 * @version 1.0
 * @created 10-����-2013 13:43:26
 */
public class StatisticsVo {
	private String username;
	private int countDoc;
	private int countBrief;
	private int countDetail;
	private int countAttachment;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getCountDoc() {
		return countDoc;
	}

	public void setCountDoc(int countDoc) {
		this.countDoc = countDoc;
	}

	public int getCountBrief() {
		return countBrief;
	}

	public void setCountBrief(int countBrief) {
		this.countBrief = countBrief;
	}

	public int getCountDetail() {
		return countDetail;
	}

	public void setCountDetail(int countDetail) {
		this.countDetail = countDetail;
	}

	public int getCountAttachment() {
		return countAttachment;
	}

	public void setCountAttachment(int countAttachment) {
		this.countAttachment = countAttachment;
	}

}