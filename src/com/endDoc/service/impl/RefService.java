package com.endDoc.service.impl;

import java.util.List;

import com.endDoc.dao.IRefDao;
import com.endDoc.po.RefTypePo;
import com.endDoc.po.ReferencePo;
import com.endDoc.service.IRefService;

public class RefService implements IRefService {
	IRefDao refDao;

	public List<RefTypePo> findAll() {
		List<RefTypePo> ljs = refDao.findAll();
		return ljs;
	}

	public List<ReferencePo> queryByDid(String id) {
		int did = Integer.parseInt(id);
		return refDao.queryByDid(did);
	}

	public List<ReferencePo> queryReferenced(String id) {
		int did = Integer.parseInt(id);
		return refDao.queryReferenced(did);
	}

	public boolean updateRefType(RefTypePo cpo) {
		return refDao.updateRefType(cpo);
	}

	public long addRefType(RefTypePo cpo) {
		return refDao.addRefType(cpo);
	}

	public boolean deleteRefType(String id) {
		int rid = Integer.parseInt(id);
		return refDao.deleteRefType(rid);
	}

	public boolean deleteReference(String id) {
		int rid = Integer.parseInt(id);
		return refDao.deleteReference(rid);
	}

	public String addReference(ReferencePo referencePo) {
		return refDao.addReference(referencePo);
	}

	public IRefDao getRefDao() {
		return refDao;
	}

	public void setRefDao(IRefDao refDao) {
		this.refDao = refDao;
	}
}
