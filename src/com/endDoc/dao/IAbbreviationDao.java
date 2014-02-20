package com.endDoc.dao;

import java.util.List;

import com.endDoc.po.AbbreviationPo;

public interface IAbbreviationDao {

	List<AbbreviationPo> findAll();

	boolean addAbbreviation(AbbreviationPo cpo);

	boolean deleteAbbreviation(String abbrword);

	boolean updateAbbreviation(AbbreviationPo cpo);
}
