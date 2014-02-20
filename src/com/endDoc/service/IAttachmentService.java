package com.endDoc.service;

import java.util.List;

import com.endDoc.po.AttachmentPo;

public interface IAttachmentService {
	List<AttachmentPo> queryByDid(String id);
	String addAttachment(List<AttachmentPo> las);
}