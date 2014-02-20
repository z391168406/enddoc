package com.endDoc.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.endDoc.dao.IDetailCommentDao;
import com.endDoc.po.DetailCommentAttriPo;
import com.endDoc.po.DetailCommentPo;
import com.endDoc.po.RateInfoPo;

public class DetailCommentDao extends HibernateDaoSupport implements
		IDetailCommentDao {
	public String addDetailComment(List<DetailCommentPo> ldcp) {
		// TODO Auto-generated method stub
		Serializable serializable=null;
		for(int i=0;i<ldcp.size();i++){
			serializable = this.getHibernateTemplate().save(ldcp.get(i));
		}
		return serializable == null ? "error":"success";
	}
	public List<String> queryByDid(String did) {
		// TODO Auto-generated method stub
		String hql = "from DetailCommentPo where did = ? and isDraft = ? order by username";
		Object[] objects={Integer.parseInt(did), false};
		List<DetailCommentPo> ljs = super.getHibernateTemplate().find(hql, objects);
		List<String> ls = new ArrayList<String>();
		int i=0,j=0;
		String str = ljs.get(0).getUsername();
		for(i=0;i<ljs.size()-1;i++){				
			if(!ljs.get(i).getUsername().equals(ljs.get(i+1).getUsername())){
				str +="&"+ljs.get(i+1).getUsername();
			}	
		}
		String split[] = str.split("&");
		Date time=new Date();
		for(int k=0;k<split.length;k++){
			String str2 = "did="+ljs.get(0).getDid()+"&username="+split[k];
			for(i=0;i<ljs.size();i++){
				if(split[k].equals(ljs.get(i).getUsername())){
					str2 += "&"+ljs.get(i).getAttribute()+"="+ljs.get(i).getValue();
					time = ljs.get(i).getCreate_time();
				}
			}
			str2 += "&create_time="+time;
			ls.add(str2);
		}
		return ls;
	}
	
	public List<String> showDraft(String did, String username) {
		// TODO Auto-generated method stub
		String hql = "from DetailCommentPo where did = ? and username = ? and isDraft = ?";
		Object[] objects={Integer.parseInt(did), username, true};
		List<DetailCommentPo> ljs = super.getHibernateTemplate().find(hql, objects);
		List<String> ls = new ArrayList<String>();
		String rs = "";
		
		rs = "did=" + ljs.get(0).getDid() + "&username=" + ljs.get(0).getUsername();
		
		for(int i=0; i<ljs.size(); i++)
			rs += "&" + ljs.get(i).getAttribute() + "="  + ljs.get(i).getValue(); 
		
		rs += "&create_time=" + ljs.get(0).getCreate_time();
		ls.add(rs);
		return ls;
	}
	
	public List<DetailCommentAttriPo> findAll() {
		String hql = "from DetailCommentAttriPo order by id";
		List<DetailCommentAttriPo> ljs = super.getHibernateTemplate().find(hql);
		return ljs;
	}

	public boolean updateDetailCommentAttri(DetailCommentAttriPo cpo) {
		DetailCommentAttriPo commentType = super.getHibernateTemplate().get(
				DetailCommentAttriPo.class, cpo.getId());
		commentType.setAttribute(cpo.getAttribute());
		super.getHibernateTemplate().update(commentType);
		return true;
	}

	public long addDetailCommentAttri(DetailCommentAttriPo cpo) {
		Serializable serializable = super.getHibernateTemplate().save(cpo);
		return serializable == null ? -1 : Long.parseLong(serializable
				.toString());
	}

	public boolean deleteDetailCommentAttri(int cid) {
		DetailCommentAttriPo cpo = super.getHibernateTemplate().get(
				DetailCommentAttriPo.class, cid);
		if (cpo == null) {
			return false;
		}
		super.getHibernateTemplate().delete(cpo);
		return true;
	}
}
