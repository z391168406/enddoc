package com.endDoc.dao;

import java.util.List;

import com.endDoc.po.DefaultTagPo;
import com.endDoc.po.TagPo;

public interface ITagDao {

	List<DefaultTagPo> findAll();

	long addDefaultTag(DefaultTagPo cpo);

	boolean deleteDefaultTag(int tid);

	boolean updateDefaultTag(DefaultTagPo cpo);

	List<TagPo> queryByDid(int did);

	long addTag(TagPo cpo);

	boolean deleteTag(int tid);
}
