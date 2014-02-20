package com.endDoc.po;

/**
 * @author zhangqi
 * @version 1.0
 * @created 10-����-2013 13:43:26
 */
public class ReferencePo {

	private int id;
	private int src_did;
	private int des_did;
	private String ref_type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSrc_did() {
		return src_did;
	}

	public void setSrc_did(int src_did) {
		this.src_did = src_did;
	}

	public int getDes_did() {
		return des_did;
	}

	public void setDes_did(int des_did) {
		this.des_did = des_did;
	}

	public String getRef_type() {
		return ref_type;
	}

	public void setRef_type(String ref_type) {
		this.ref_type = ref_type;
	}

	
}