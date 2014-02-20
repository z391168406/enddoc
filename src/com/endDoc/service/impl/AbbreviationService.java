package com.endDoc.service.impl;

import java.util.List;

import com.endDoc.dao.IAbbreviationDao;
import com.endDoc.po.AbbreviationPo;
import com.endDoc.service.IAbbreviationService;

public class AbbreviationService implements IAbbreviationService {
	IAbbreviationDao abbreviationDao;

	public List<AbbreviationPo> findAll() {
		List<AbbreviationPo> ljs = abbreviationDao.findAll();
		return ljs;
	}

	public boolean updateAbbreviation(AbbreviationPo cvo) {
		AbbreviationPo cpo = new AbbreviationPo();
		return abbreviationDao.updateAbbreviation(cpo);
	}

	public boolean addAbbreviation(AbbreviationPo cpo) {
		return abbreviationDao.addAbbreviation(cpo);
	}

	public boolean deleteAbbreviation(String abbrword) {
		return abbreviationDao.deleteAbbreviation(abbrword);
	}

	public IAbbreviationDao getAbbreviationDao() {
		return abbreviationDao;
	}

	public void setAbbreviationDao(IAbbreviationDao abbreviationDao) {
		this.abbreviationDao = abbreviationDao;
	}
}
