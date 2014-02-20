package com.endDoc.dao;

import java.util.List;

import com.endDoc.po.DetailCommentAttriPo;
import com.endDoc.po.DetailCommentPo;

public interface IDetailCommentDao {

	List<DetailCommentAttriPo> findAll();

	long addDetailCommentAttri(DetailCommentAttriPo cpo);

	boolean deleteDetailCommentAttri(int cid);

	boolean updateDetailCommentAttri(DetailCommentAttriPo cpo);
	
	String addDetailComment(List<DetailCommentPo> ldcp);
	
	List<String> queryByDid(String did);
	
	List<String> showDraft(String did,String username);
}
