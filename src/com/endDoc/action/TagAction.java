package com.endDoc.action;

import java.util.List;

import com.endDoc.po.TagPo;
import com.endDoc.service.ITagService;
import com.endDoc.vo.DefaultTagVo;
import com.opensymphony.xwork2.ActionSupport;

public class TagAction extends ActionSupport {
	ITagService tagService;
	List<DefaultTagVo> ldvos;
	List<TagPo> tagList;
	private String title;
	private String id;
	private String did;

	public String findall() throws Exception {
		ldvos = tagService.findAll();
		return super.execute();
	}

	public String saveDefaultTag() throws Exception {
		DefaultTagVo cvo = new DefaultTagVo();
		cvo.setId(id);
		cvo.setTitle(title);
		tagService.updateDefaultTag(cvo);
		return super.execute();
	}

	public String addDefaultTag() throws Exception {
		DefaultTagVo cvo = new DefaultTagVo();
		cvo.setTitle(title);
		id = tagService.addDefaultTag(cvo) + "";
		return super.execute();
	}

	public String delDefaultTag() throws Exception {
		tagService.deleteDefaultTag(id);
		return super.execute();
	}

	public String findTag() throws Exception {
		tagList = tagService.queryByDid(did);
		return super.execute();
	}

	public String addTag() throws Exception {
		String split[] = title.split(",");
		for (int i = 0; i < split.length; i++) {
			TagPo cpo = new TagPo();
			cpo.setTitle((split[i]));
			cpo.setDid(Integer.parseInt(did));
			tagService.addTag(cpo);
		}
		return super.execute();
	}

	public String delTag() throws Exception {
		tagService.deleteTag(id);
		return super.execute();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public List<DefaultTagVo> getLdvos() {
		return ldvos;
	}

	public void setLdvos(List<DefaultTagVo> ldvos) {
		this.ldvos = ldvos;
	}

	public List<TagPo> getTagList() {
		return tagList;
	}

	public void setTagList(List<TagPo> tagList) {
		this.tagList = tagList;
	}

	public void setTagService(ITagService tagService) {
		this.tagService = tagService;
	}

}
