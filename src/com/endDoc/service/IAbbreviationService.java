package com.endDoc.service;

import java.util.List;

import com.endDoc.po.AbbreviationPo;

public interface IAbbreviationService {
	List<AbbreviationPo> findAll();

	boolean addAbbreviation(AbbreviationPo cpo);

	boolean deleteAbbreviation(String abbrword);

	boolean updateAbbreviation(AbbreviationPo cpo);
}
