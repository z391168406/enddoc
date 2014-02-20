package com.endDoc.service.impl;

import java.util.List;

import com.endDoc.dao.IAttachmentDao;
import com.endDoc.po.AttachmentPo;
import com.endDoc.service.IAttachmentService;

public class AttachmentService implements IAttachmentService {
	IAttachmentDao attachmentDao;

	public String addAttachment(List<AttachmentPo> las) {
		// TODO Auto-generated method stub
		return attachmentDao.addAttachment(las);
	}
	
	public List<AttachmentPo> queryByDid(String id) {
		int did = Integer.parseInt(id);
		return attachmentDao.queryByDid(did);
	}

	public IAttachmentDao getAttachmentDao() {
		return attachmentDao;
	}

	public void setAttachmentDao(IAttachmentDao attachmentDao) {
		this.attachmentDao = attachmentDao;
	}
}
