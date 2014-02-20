package com.endDoc.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.endDoc.po.BriefCommentPo;
import com.endDoc.po.DetailCommentAttriPo;
import com.endDoc.po.DetailCommentPo;
import com.endDoc.service.IDetailCommentService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DetailCommentAction extends ActionSupport {
	IDetailCommentService detailCommentService;
	List<DetailCommentAttriPo> ldvos;
	List<String> list;
	private String did;
	private String attribute;
	private String id;
	private String form;
	private boolean isDraft;

	@SuppressWarnings("null")
	public String addDetailComment() {

		String split[] = form.substring(0, form.length() - 1).split("/");
		List<DetailCommentPo> ldcp = new ArrayList<DetailCommentPo>();
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		for (int i = 0; i < split.length; i++) {
			DetailCommentPo detailCommentPo = new DetailCommentPo();
			String split2[] = split[i].split("=");
			detailCommentPo.setDid(Integer.parseInt(did));
			detailCommentPo.setUsername((String) ActionContext.getContext()
					.getSession().get("username"));
			detailCommentPo.setAttribute(split2[0]);
			detailCommentPo.setValue(split2[1]);
			detailCommentPo.setCreate_time(curDate);
			detailCommentPo.setIsDraft(isDraft);
			ldcp.add(detailCommentPo);
		}
		return detailCommentService.addDetailComment(ldcp);
	}

	public String queryByDid() throws Exception {
		list = detailCommentService.queryByDid(did);
		return super.execute();
	}
	
	public String showDraft() throws Exception {
		DetailCommentPo detailCommentPo = new DetailCommentPo();
		detailCommentPo.setUsername((String)ActionContext.getContext().getSession().get("username"));
		list = detailCommentService.showDraft(did, detailCommentPo.getUsername());
		System.out.println(list.toString());
		return super.execute();
	}

	public String findallAttr() throws Exception {
		ldvos = detailCommentService.findAll();
		return super.execute();
	}

	public String saveDetailCommentAttri() throws Exception {
		DetailCommentAttriPo cpo = new DetailCommentAttriPo();
		cpo.setId(Integer.parseInt(id));
		cpo.setAttribute(attribute);
		detailCommentService.updateDetailCommentAttri(cpo);
		return super.execute();
	}

	public String addDetailCommentAttri() throws Exception {
		DetailCommentAttriPo cpo = new DetailCommentAttriPo();
		cpo.setAttribute(attribute);
		id = detailCommentService.addDetailCommentAttri(cpo) + "";
		return super.execute();
	}

	public String delDetailCommentAttri() throws Exception {
		detailCommentService.deleteDetailCommentAttri(id);
		return super.execute();

	}

	public List<DetailCommentAttriPo> getLdvos() {
		return ldvos;
	}

	public void setLdvos(List<DetailCommentAttriPo> ldvos) {
		this.ldvos = ldvos;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}
	
	public boolean getIsDraft() {
		return isDraft;
	}

	public void setIsDraft(String isDraft) {
		if("true".equals(isDraft))
			this.isDraft = true;
		else 
			this.isDraft = false;
	}

	public void setDetailCommentService(
			IDetailCommentService detailCommentService) {
		this.detailCommentService = detailCommentService;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

}
