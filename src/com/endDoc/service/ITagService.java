package com.endDoc.service;

import java.util.List;

import com.endDoc.po.TagPo;
import com.endDoc.vo.DefaultTagVo;

public interface ITagService {
	List<DefaultTagVo> findAll();

	long addDefaultTag(DefaultTagVo cvo);

	boolean deleteDefaultTag(String id);

	boolean updateDefaultTag(DefaultTagVo cvo);

	List<TagPo> queryByDid(String id);

	long addTag(TagPo cpo);

	boolean deleteTag(String id);
}
