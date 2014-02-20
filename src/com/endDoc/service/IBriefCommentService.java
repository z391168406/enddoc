package com.endDoc.service;

import java.util.List;
import com.endDoc.po.BriefCommentPo;


public interface IBriefCommentService {
	String addBriefComment(BriefCommentPo briefCommentPo);
	List<BriefCommentPo> queryByDid(String did);
	List<BriefCommentPo> showDraft(String did, String username);
}
