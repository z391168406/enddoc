package com.endDoc.service;

import java.util.List;

import com.endDoc.po.DetailCommentAttriPo;
import com.endDoc.po.DetailCommentPo;

public interface IDetailCommentService {
	List<DetailCommentAttriPo> findAll();

	boolean updateDetailCommentAttri(DetailCommentAttriPo cpo);

	long addDetailCommentAttri(DetailCommentAttriPo cpo);

	boolean deleteDetailCommentAttri(String id);
	
	String addDetailComment(List<DetailCommentPo> ldcp);
	
	List<String> queryByDid(String did);
	
	List<String> showDraft(String did,String username);
}
