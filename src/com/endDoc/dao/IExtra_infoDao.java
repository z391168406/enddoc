package com.endDoc.dao;

import java.util.List;

import com.endDoc.po.Extra_infoPo;

public interface IExtra_infoDao {
	List<Extra_infoPo> queryByDid(int did);
}
