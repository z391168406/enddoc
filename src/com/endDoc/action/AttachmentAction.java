package com.endDoc.action;

import com.endDoc.service.IAttachmentService;
import com.opensymphony.xwork2.ActionSupport;

public class AttachmentAction extends ActionSupport {
	IAttachmentService attachmentService;
	public void setAttachmentService(IAttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}
}
