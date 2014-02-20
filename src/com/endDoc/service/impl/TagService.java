package com.endDoc.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.endDoc.dao.ITagDao;
import com.endDoc.po.DefaultTagPo;
import com.endDoc.po.TagPo;
import com.endDoc.service.ITagService;
import com.endDoc.vo.DefaultTagVo;

public class TagService implements ITagService {
	ITagDao tagDao;

	public List<DefaultTagVo> findAll() {
		List<DefaultTagPo> ljs = tagDao.findAll();
		List<DefaultTagVo> ljvos = p2v(ljs);
		return ljvos;
	}

	public List<TagPo> queryByDid(String id) {
		int did = Integer.parseInt(id);
		return tagDao.queryByDid(did);
	}

	public boolean updateDefaultTag(DefaultTagVo cvo) {
		DefaultTagPo cpo = new DefaultTagPo();
		cpo.setTitle(cvo.getTitle());
		cpo.setId(Integer.parseInt(cvo.getId()));
		return tagDao.updateDefaultTag(cpo);
	}

	public long addDefaultTag(DefaultTagVo cvo) {
		DefaultTagPo cpo = new DefaultTagPo();
		cpo.setTitle(cvo.getTitle());
		return tagDao.addDefaultTag(cpo);
	}

	public boolean deleteDefaultTag(String id) {
		int tid = Integer.parseInt(id);
		return tagDao.deleteDefaultTag(tid);
	}

	public long addTag(TagPo cpo) {
		return tagDao.addTag(cpo);
	}

	public boolean deleteTag(String id) {
		int tid = Integer.parseInt(id);
		return tagDao.deleteTag(tid);
	}

	private List<DefaultTagVo> p2v(List<DefaultTagPo> ljs) {
		List<DefaultTagVo> ljvos = new ArrayList<DefaultTagVo>();
		for (DefaultTagPo cm : ljs) {
			ljvos.add(p2v(cm));
		}
		return ljvos;
	}

	private DefaultTagVo p2v(DefaultTagPo cm) {
		DefaultTagVo cvo = new DefaultTagVo();
		cvo.setId(cm.getId() + "");
		cvo.setTitle(cm.getTitle());
		return cvo;
	}

	public ITagDao getTagDao() {
		return tagDao;
	}

	public void setTagDao(ITagDao tagDao) {
		this.tagDao = tagDao;
	}
}
