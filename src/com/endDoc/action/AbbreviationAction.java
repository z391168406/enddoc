package com.endDoc.action;

import java.util.List;

import com.endDoc.po.AbbreviationPo;
import com.endDoc.service.IAbbreviationService;
import com.opensymphony.xwork2.ActionSupport;

public class AbbreviationAction extends ActionSupport {
	IAbbreviationService abbreviationService;
	List<AbbreviationPo> ldvos;
	private String abbrword;
	private String fullword;

	public String findall() throws Exception {
		ldvos = abbreviationService.findAll();
		return super.execute();
	}

	public String saveAbbreviation() throws Exception {
		AbbreviationPo cpo = new AbbreviationPo();
		cpo.setAbbrword(abbrword);
		cpo.setFullword(fullword);
		abbreviationService.updateAbbreviation(cpo);
		return super.execute();
	}

	public String addAbbreviation() throws Exception {
		AbbreviationPo cpo = new AbbreviationPo();
		cpo.setAbbrword(abbrword);
		cpo.setFullword(fullword);
		abbreviationService.addAbbreviation(cpo);
		return super.execute();
	}

	public String delAbbreviation() throws Exception {
		abbreviationService.deleteAbbreviation(abbrword);
		return super.execute();

	}

	public List<AbbreviationPo> getLdvos() {
		return ldvos;
	}

	public void setLdvos(List<AbbreviationPo> ldvos) {
		this.ldvos = ldvos;
	}

	public String getAbbrword() {
		return abbrword;
	}

	public void setAbbrword(String abbrword) {
		this.abbrword = abbrword;
	}

	public String getFullword() {
		return fullword;
	}

	public void setFullword(String fullword) {
		this.fullword = fullword;
	}

	public void setAbbreviationService(IAbbreviationService abbreviationService) {
		this.abbreviationService = abbreviationService;
	}
}
