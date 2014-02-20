package com.endDoc.service.impl;

import java.util.List;

import com.endDoc.dao.IBriefCommentDao;
import com.endDoc.po.BriefCommentPo;
import com.endDoc.service.IBriefCommentService;



public class BriefCommentService implements IBriefCommentService {
	IBriefCommentDao briefCommentDao;

	public String addBriefComment(BriefCommentPo briefCommentPo) {
		// TODO Auto-generated method stub
		return briefCommentDao.addBriefComment(briefCommentPo);
	}
	
	public List<BriefCommentPo> queryByDid(String did) {
		// TODO Auto-generated method stub
		return briefCommentDao.queryByDid(did);
	}

	public List<BriefCommentPo> showDraft(String did, String username) {
		// TODO Auto-generated method stub
		return briefCommentDao.showDraft(did,username);
	}

	public IBriefCommentDao getBriefCommentDao() {
		return briefCommentDao;
	}
	
	public void setBriefCommentDao(IBriefCommentDao briefCommentDao) {
		this.briefCommentDao = briefCommentDao;
	}
	
}
