package com.endDoc.service.impl;

import java.util.List;

import com.endDoc.dao.IDetailCommentDao;
import com.endDoc.po.DetailCommentAttriPo;
import com.endDoc.po.DetailCommentPo;
import com.endDoc.service.IDetailCommentService;

public class DetailCommentService implements IDetailCommentService {
	IDetailCommentDao detailCommentDao;
	public String addDetailComment(List<DetailCommentPo> ldcp) {
		// TODO Auto-generated method stub
		return detailCommentDao.addDetailComment(ldcp);
	}
	
	public List<String> queryByDid(String did) {
		// TODO Auto-generated method stub
		return detailCommentDao.queryByDid(did);
	}

	public List<String> showDraft(String did, String username) {
		// TODO Auto-generated method stub
		return detailCommentDao.showDraft(did, username);
	}
	
	public List<DetailCommentAttriPo> findAll() {
		List<DetailCommentAttriPo> ljs = detailCommentDao.findAll();
		return ljs;
	}

	public boolean updateDetailCommentAttri(DetailCommentAttriPo cpo) {
		return detailCommentDao.updateDetailCommentAttri(cpo);
	}

	public long addDetailCommentAttri(DetailCommentAttriPo cpo) {
		return detailCommentDao.addDetailCommentAttri(cpo);
	}

	public boolean deleteDetailCommentAttri(String id) {
		int rid = Integer.parseInt(id);
		return detailCommentDao.deleteDetailCommentAttri(rid);
	}

	public IDetailCommentDao getDetailCommentDao() {
		return detailCommentDao;
	}

	public void setDetailCommentDao(IDetailCommentDao detailCommentDao) {
		this.detailCommentDao = detailCommentDao;
	}
}
