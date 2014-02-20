package com.endDoc.dao;

import java.util.List;

import com.endDoc.po.RefTypePo;
import com.endDoc.po.ReferencePo;

public interface IRefDao {

	List<RefTypePo> findAll();

	long addRefType(RefTypePo cpo);

	boolean deleteRefType(int rid);

	boolean updateRefType(RefTypePo cpo);

	List<ReferencePo> queryByDid(int did);

	String addReference(ReferencePo referencePo);

	boolean deleteReference(int rid);

	List<ReferencePo> queryReferenced(int did);
}
