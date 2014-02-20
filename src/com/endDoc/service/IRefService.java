package com.endDoc.service;

import java.util.List;

import com.endDoc.po.RefTypePo;
import com.endDoc.po.ReferencePo;

public interface IRefService {
	List<RefTypePo> findAll();

	long addRefType(RefTypePo cpo);

	boolean deleteRefType(String id);

	boolean updateRefType(RefTypePo cpo);

	List<ReferencePo> queryByDid(String did);

	String addReference(ReferencePo referencePo);

	boolean deleteReference(String id);

	List<ReferencePo> queryReferenced(String id);
}
