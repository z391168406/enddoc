package com.endDoc.dao;

import java.util.List;

import com.endDoc.po.AttachmentPo;

public interface IAttachmentDao {
	List<AttachmentPo> queryByDid(int did);
	String addAttachment(List<AttachmentPo> las);
}
