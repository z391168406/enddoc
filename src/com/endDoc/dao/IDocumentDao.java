package com.endDoc.dao;

import java.util.List;

import com.endDoc.po.Doc_typePo;
import com.endDoc.po.DocumentAttributePo;
import com.endDoc.po.DocumentPo;

public interface IDocumentDao {
	String addDocument(DocumentPo documentPo);

	List<DocumentPo> queryById(int did);

	String saveForUpdate(DocumentPo documentPo);

	boolean updateInfo(DocumentPo po);

	List<DocumentAttributePo> getDocType(String type);

	List<DocumentPo> findAll();

	List<DocumentPo> search(String type, String year, String endyear,
			String title, String author, String tag, String abstracts);

	List<DocumentPo> simpleSearch(String title);

	List<DocumentPo> getDocsByUser(String username);

	List<Doc_typePo> findAllType();

	boolean deleteAttribute(int tid);

	boolean deleteType(int tid);

	long addDocType(Doc_typePo cpo);

	long addAttribute(DocumentAttributePo cpo);

	boolean deleteDoc(int tid);
}
