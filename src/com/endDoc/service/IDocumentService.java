package com.endDoc.service;

import java.util.List;

import com.endDoc.po.Doc_typePo;
import com.endDoc.po.DocumentAttributePo;
import com.endDoc.po.DocumentPo;

public interface IDocumentService {
	String addDocument(DocumentPo documentPo);

	List<DocumentPo> queryById(String id);

	String saveForUpdate(DocumentPo documentPo);

	boolean updateInfo(DocumentPo po);

	List<DocumentAttributePo> getDocType(String type);

	List<DocumentPo> findAll();

	List<DocumentPo> search(String type, String year, String endyear,
			String title, String author, String tag, String abstracts);

	List<DocumentPo> simpleSearch(String title);

	List<DocumentPo> getDocsByUser(String username);

	List<Doc_typePo> findAllType();

	boolean deleteType(String id);

	boolean deleteAttribute(String id);

	long addDocType(Doc_typePo cpo);

	long addAttribute(DocumentAttributePo cpo);

	boolean deleteDoc(String id);
}
