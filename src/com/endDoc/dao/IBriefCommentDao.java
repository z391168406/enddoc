package com.endDoc.dao;

import java.util.List;

import com.endDoc.po.BriefCommentPo;


public interface IBriefCommentDao {
	String addBriefComment(BriefCommentPo briefCommentPo);
	List<BriefCommentPo> queryByDid(String did);
	List<BriefCommentPo> showDraft(String did, String username);
}
