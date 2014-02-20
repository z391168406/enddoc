package com.endDoc.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.endDoc.dao.IDocumentDao;
import com.endDoc.po.Doc_typePo;
import com.endDoc.po.DocumentAttributePo;
import com.endDoc.po.DocumentPo;
import com.endDoc.po.RateInfoPo;
import com.endDoc.po.TagPo;

public class DocumentDao extends HibernateDaoSupport implements IDocumentDao {
	public List<DocumentPo> findAll() {
		String hql = "from DocumentPo order by id";
		List<DocumentPo> ljs = super.getHibernateTemplate().find(hql);
		return ljs;
	}

	public List<Doc_typePo> findAllType() {
		String hql = "from Doc_typePo order by id";
		List<Doc_typePo> ljs = super.getHibernateTemplate().find(hql);
		return ljs;
	}

	public String addDocument(DocumentPo documentPo) {
		// TODO Auto-generated method stub

		Serializable serializable = this.getHibernateTemplate()
				.save(documentPo);

		for (int i = 1; i < 6; i++) {
			RateInfoPo rateInfoPo = new RateInfoPo();
			rateInfoPo.setDid(Integer.parseInt(serializable.toString()));
			rateInfoPo.setAmount(0);
			rateInfoPo.setScore(i);
			this.getHibernateTemplate().save(rateInfoPo);
		}
		return serializable.toString();

	}

	public long addDocType(Doc_typePo cpo) {
		Serializable serializable = super.getHibernateTemplate().save(cpo);
		return serializable == null ? -1 : Long.parseLong(serializable
				.toString());
	}

	public long addAttribute(DocumentAttributePo cpo) {
		Serializable serializable = super.getHibernateTemplate().save(cpo);
		return serializable == null ? -1 : Long.parseLong(serializable
				.toString());
	}

	public List<DocumentPo> getDocsByUser(String username) {
		String hql = "from DocumentPo where username = ? ";
		List<DocumentPo> ljs = super.getHibernateTemplate().find(hql, username);
		return ljs;
	}

	public List<DocumentPo> queryById(int id) {
		String hql = "from DocumentPo where id = ? ";
		List<DocumentPo> ljs = super.getHibernateTemplate().find(hql, id);
		return ljs;
	}

	public String saveForUpdate(DocumentPo documentPo) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(documentPo);
		return "update_success";
	}

	public boolean updateInfo(DocumentPo po) {
		DocumentPo cpo = super.getHibernateTemplate().get(DocumentPo.class,
				po.getId());
		cpo.setAbstracts(po.getAbstracts());
		cpo.setAuthor(po.getAuthor());
		cpo.setKeywords(po.getKeywords());
		cpo.setPages(po.getPages());
		cpo.setPublisher(po.getPublisher());
		cpo.setTitle(po.getTitle());
		cpo.setYear(po.getYear());
		super.getHibernateTemplate().update(cpo);
		return true;
	}

	public List<DocumentAttributePo> getDocType(String type) {
		// TODO Auto-generated method stub
		String hql = "from DocumentAttributePo where type = ?";
		List<DocumentAttributePo> ljs = super.getHibernateTemplate().find(hql,
				type);
		return ljs;

	}

	public List<DocumentPo> simpleSearch(String title) {
		List<DocumentPo> ljs = findbyTitle(title);
		add(ljs, findbyType(title));
		add(ljs, findbyAuthor(title));
		add(ljs, findbyType(title));
		add(ljs, findbyTag(title));
		add(ljs, findbyAbstract(title));
		return ljs;
	}

	public List<DocumentPo> add(List<DocumentPo> temp1, List<DocumentPo> temp2) {
		for (int i = 0; i < temp2.size(); i++) {
			if (!temp1.contains(temp2.get(i)))
				temp1.add(temp2.get(i));
		}
		return temp1;
	}

	public List<DocumentPo> search(String type, String year, String endyear,
			String title, String author, String tag, String abstracts) {
		List<DocumentPo> list = findbyType(type);
		list.retainAll(findbyYear(year, endyear));
		list.retainAll(findbyTitle(title));
		list.retainAll(findbyAuthor(author));
		list.retainAll(findbyTag(tag));
		list.retainAll(findbyAbstract(abstracts));
		return list;
	}

	private List<DocumentPo> findbyType(String type) {
		if ((type != null) && (type.length() != 0)) {
			String hql = "from DocumentPo where type = ?";
			List<DocumentPo> ljs = super.getHibernateTemplate().find(hql, type);
			return ljs;
		} else
			return findAll();
	}

	private List<DocumentPo> findbyYear(String year, String endyear) {
		if ((year != null) && (year.length() != 0) && (year != endyear)
				&& (endyear.length() != 0)) {
			String hql = "from DocumentPo where year >= :startyear and year<=:endyear";
			String[] params = { "startyear", "endyear" };
			Object[] args = { Integer.parseInt(year), Integer.parseInt(endyear) };
			List<DocumentPo> ljs = super.getHibernateTemplate()
					.findByNamedParam(hql, params, args);
			return ljs;
		} else
			return findAll();
	}

	private List<DocumentPo> findbyTitle(String title) {
		if ((title != null) && (title.length() != 0)) {
			String hql = "from DocumentPo where lower(title) like ?";
			List<DocumentPo> ljs = super.getHibernateTemplate().find(hql,
					'%' + title.toLowerCase() + '%');
			return ljs;
		} else
			return findAll();
	}

	private List<DocumentPo> findbyAuthor(String author) {
		if ((author != null) && (author.length() != 0)) {
			String hql = "from DocumentPo where lower(author) like ?";
			List<DocumentPo> ljs = super.getHibernateTemplate().find(hql,
					'%' + author.toLowerCase() + '%');
			return ljs;
		} else
			return findAll();
	}

	private List<DocumentPo> findbyAbstract(String abstracts) {
		if ((abstracts != null) && (abstracts.length() != 0)) {
			String hql = "from DocumentPo where lower(abstracts) like ?";
			List<DocumentPo> ljs = super.getHibernateTemplate().find(hql,
					'%' + abstracts.toLowerCase() + '%');
			return ljs;
		} else
			return findAll();
	}

	private List<DocumentPo> findbyTag(String tag) {
		List<DocumentPo> docList = new ArrayList<DocumentPo>();
		if ((tag != null) && (tag.length() != 0)) {
			String hql = "from TagPo where lower(title) like ?";
			List<TagPo> ljs = super.getHibernateTemplate().find(hql,
					'%' + tag.toLowerCase() + '%');
			if (ljs.isEmpty())
				return new ArrayList<DocumentPo>();
			for (int i = 0; i < ljs.size(); i++) {
				docList.addAll(queryById(ljs.get(i).getDid()));
			}
			return docList;
		} else
			return findAll();
	}

	public boolean deleteAttribute(int tid) {
		DocumentAttributePo cpo = super.getHibernateTemplate().get(
				DocumentAttributePo.class, tid);
		if (cpo == null) {
			return false;
		}
		super.getHibernateTemplate().delete(cpo);
		return true;
	}

	public boolean deleteType(int tid) {
		Doc_typePo cpo = super.getHibernateTemplate()
				.get(Doc_typePo.class, tid);
		if (cpo == null) {
			return false;
		}
		super.getHibernateTemplate().delete(cpo);
		List<DocumentAttributePo> po = getDocType(cpo.getType());
		for (int i = 0; i < po.size(); i++) {
			super.getHibernateTemplate().delete(po.get(i));
		}
		return true;
	}

	public boolean deleteDoc(int tid) {
		DocumentPo cpo = super.getHibernateTemplate()
				.get(DocumentPo.class, tid);
		if (cpo == null) {
			return false;
		}
		super.getHibernateTemplate().delete(cpo);
		// 未完待续
		return true;
	}
}
