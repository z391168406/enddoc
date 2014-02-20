package com.endDoc.action;

import java.util.Date;
import java.util.List;

import com.endDoc.po.BriefCommentPo;
import com.endDoc.service.IBriefCommentService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BriefCommentAction extends ActionSupport {
	private IBriefCommentService briefCommentService;
	List<BriefCommentPo> list;
	private String did;
	private String username;
	private String content;
	private String rate;
	private String create_time;
	private String update_time;
	private boolean isDraft;

	public String addBriefComment() {
		BriefCommentPo briefCommentPo = new BriefCommentPo();
		briefCommentPo.setDid(Integer.parseInt(did));
		briefCommentPo.setUsername((String) ActionContext.getContext()
				.getSession().get("username"));
		briefCommentPo.setContent(content);
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		briefCommentPo.setCreate_time(curDate);
		briefCommentPo.setIsDraft(isDraft);
		return briefCommentService.addBriefComment(briefCommentPo);
	}

	public String queryByDid() throws Exception {
		list = briefCommentService.queryByDid(did);
		return super.execute();
	}

	public String showDraft() throws Exception {
		BriefCommentPo briefCommentPo = new BriefCommentPo();
		briefCommentPo.setUsername((String) ActionContext.getContext()
				.getSession().get("username"));
		list = briefCommentService.showDraft(did, briefCommentPo.getUsername());
		return super.execute();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDid() {
		return did;
	}

	public List<BriefCommentPo> getList() {
		return list;
	}

	public void setList(List<BriefCommentPo> list) {
		this.list = list;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	public boolean getIsDraft() {
		return isDraft;
	}

	public void setIsDraft(String isDraft) {
		if ("true".equals(isDraft))
			this.isDraft = true;
		else
			this.isDraft = false;
	}

	public void setBriefCommentService(IBriefCommentService briefCommentService) {
		this.briefCommentService = briefCommentService;
	}

}
