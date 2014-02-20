package com.endDoc.service.impl;

import java.util.List;

import com.endDoc.dao.IDocumentDao;
import com.endDoc.po.Doc_typePo;
import com.endDoc.po.DocumentAttributePo;
import com.endDoc.po.DocumentPo;
import com.endDoc.service.IDocumentService;

public class DocumentService implements IDocumentService {
	IDocumentDao documentDao;

	public List<DocumentPo> findAll() {
		List<DocumentPo> ljs = documentDao.findAll();
		return ljs;
	}

	public List<Doc_typePo> findAllType() {
		List<Doc_typePo> ljs = documentDao.findAllType();
		return ljs;
	}

	public List<DocumentPo> search(String type, String year, String endyear,
			String title, String author, String tag, String abstracts) {
		List<DocumentPo> ljs = documentDao.search(type, year, endyear, title,
				author, tag, abstracts);
		return ljs;
	}

	public List<DocumentPo> simpleSearch(String title) {
		List<DocumentPo> ljs = documentDao.simpleSearch(title);
		return ljs;
	}

	public String addDocument(DocumentPo documentPo) {
		// TODO Auto-generated method stub
		return documentDao.addDocument(documentPo);
	}

	public long addDocType(Doc_typePo cpo) {
		return documentDao.addDocType(cpo);
	}

	public long addAttribute(DocumentAttributePo cpo) {
		return documentDao.addAttribute(cpo);
	}

	public List<DocumentPo> queryById(String id) {
		int did = Integer.parseInt(id);
		return documentDao.queryById(did);
	}

	public List<DocumentPo> getDocsByUser(String username) {
		return documentDao.getDocsByUser(username);
	}

	public String saveForUpdate(DocumentPo documentPo) {
		// TODO Auto-generated method stub
		return documentDao.saveForUpdate(documentPo);
	}

	public boolean deleteType(String id) {
		int tid = Integer.parseInt(id);
		return documentDao.deleteType(tid);
	}

	public boolean deleteAttribute(String id) {
		int tid = Integer.parseInt(id);
		return documentDao.deleteAttribute(tid);
	}

	public boolean deleteDoc(String id) {
		int tid = Integer.parseInt(id);
		return documentDao.deleteDoc(tid);
	}

	public IDocumentDao getDocumentDao() {
		return documentDao;
	};

	public void setDocumentDao(IDocumentDao documentDao) {
		this.documentDao = documentDao;
	}

	public boolean updateInfo(DocumentPo po) {
		return documentDao.updateInfo(po);
	}

	public List<DocumentAttributePo> getDocType(String type) {
		return documentDao.getDocType(type);
	}
}
