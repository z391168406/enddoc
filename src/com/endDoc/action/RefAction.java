package com.endDoc.action;

import java.util.List;

import com.endDoc.po.RefTypePo;
import com.endDoc.po.ReferencePo;
import com.endDoc.service.IRefService;
import com.opensymphony.xwork2.ActionSupport;

public class RefAction extends ActionSupport {
	IRefService refService;
	List<RefTypePo> ldvos;
	List<ReferencePo> referenceList;
	private String type;
	private String src_did;
	private String des_did;
	private String ref_type;
	private String id;

	public String findall() throws Exception {
		ldvos = refService.findAll();
		return super.execute();
	}

	public String saveRefType() throws Exception {
		RefTypePo cpo = new RefTypePo();
		cpo.setId(Integer.parseInt(id));
		cpo.setType(type);
		refService.updateRefType(cpo);
		return super.execute();
	}

	public String addRefType() throws Exception {
		RefTypePo cpo = new RefTypePo();
		cpo.setType(type);
		id = refService.addRefType(cpo) + "";
		return super.execute();
	}

	public String delRefType() throws Exception {
		refService.deleteRefType(id);
		return super.execute();
	}

	public String addReference() {
		ReferencePo referencePo = new ReferencePo();
		if (src_did.length() != 0) {
			referencePo.setSrc_did(Integer.parseInt(src_did));
		}
		if (des_did.length() != 0) {
			referencePo.setDes_did(Integer.parseInt(des_did));
		}
		referencePo.setRef_type(ref_type);
		return refService.addReference(referencePo);
	}

	public String delReference() throws Exception {
		refService.deleteReference(id);
		return super.execute();
	}

	public String findReference() throws Exception {
		referenceList = refService.queryByDid(id);
		return super.execute();
	}

	public String findReferenced() throws Exception {
		referenceList = refService.queryReferenced(id);
		return super.execute();
	}

	public List<RefTypePo> getLdvos() {
		return ldvos;
	}

	public void setLdvos(List<RefTypePo> ldvos) {
		this.ldvos = ldvos;
	}

	public List<ReferencePo> getReferenceList() {
		return referenceList;
	}

	public void setReferenceList(List<ReferencePo> referenceList) {
		this.referenceList = referenceList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public String getSrc_did() {
		return src_did;
	}

	public void setSrc_did(String src_did) {
		this.src_did = src_did;
	}

	public String getDes_did() {
		return des_did;
	}

	public void setDes_did(String des_did) {
		this.des_did = des_did;
	}

	public String getRef_type() {
		return ref_type;
	}

	public void setRef_type(String ref_type) {
		this.ref_type = ref_type;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setRefService(IRefService refService) {
		this.refService = refService;
	}
}
