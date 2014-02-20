package com.endDoc.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.endDoc.po.AttachmentPo;
import com.endDoc.po.Doc_typePo;
import com.endDoc.po.DocumentAttributePo;
import com.endDoc.po.DocumentPo;
import com.endDoc.po.RateInfoPo;
import com.endDoc.po.ReferencePo;
import com.endDoc.po.TagPo;
import com.endDoc.service.IAttachmentService;
import com.endDoc.service.IDocumentService;
import com.endDoc.service.IRateService;
import com.endDoc.service.IRefService;
import com.endDoc.service.ITagService;
import com.endDoc.vo.ReferenceVo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DocumentAction extends ActionSupport {
	IRateService rateService;
	IDocumentService documentService;
	IAttachmentService attachmentService;
	IRefService refService;
	ITagService tagService;
	List<DocumentPo> docLists;
	List<Doc_typePo> docTypeLists;
	List<AttachmentPo> attachmentList;
	List<RateInfoPo> rateInfo;
	List<TagPo> tagList;
	List<DocumentAttributePo> ldas;
	List<ReferenceVo> refList;
	List<ReferenceVo> refedList;
	List<RateInfoPo> rateList;
	private DocumentPo documentPo;
	private String endyear;
	private String id;
	private String attribute;
	private String username;
	private String title;
	private String author;
	private String tag;
	private String year;
	private String pages;
	private String abstracts;
	private String keywords;
	private String publisher;
	private String url;
	private String type;
	private String publication;
	private String periodicalname;
	private String conferencename;
	private String city;
	private String volume;
	private String issue;
	private String startpage;
	private String endpage;
	private String editor;
	private String bookname;
	private String collegename;
	private String isbn;
	private File image; // 上传的文件
	private String imageFileName; // 文件名称
	private String imageContentType; // 文件类型

	public String test() throws IOException {
		for (int i = 0; i < refList.size(); i++) {
			System.out.println(refList.get(i).getRef_type()
					+ refList.get(i).getDes_did());
		}
		return abstracts;
	}

	public String addDocument() throws Exception {
		try {
			DocumentPo documentPo = new DocumentPo();
			username = (String) ActionContext.getContext().getSession()
					.get("username");
			documentPo.setUsername(username);
			documentPo.setTitle(title);
			documentPo.setAuthor(author);
			if (year != null && year.length() != 0) {
				documentPo.setYear(Integer.parseInt(year));
			}
			if (pages != null && pages.length() != 0) {
				documentPo.setPages(Integer.parseInt(pages));
			}
			documentPo.setAbstracts(abstracts);
			documentPo.setKeywords(keywords);
			documentPo.setPublisher(publisher);
			documentPo.setUrl(url);
			documentPo.setType(type);
			documentPo.setPublication(publication);
			documentPo.setPeriodicalname(periodicalname);
			documentPo.setConferencename(conferencename);
			documentPo.setCity(city);
			if (volume != null && volume.length() != 0) {
				documentPo.setVolume(Integer.parseInt(volume));
			}
			if (issue != null && issue.length() != 0) {
				documentPo.setVolume(Integer.parseInt(issue));
			}
			if (startpage != null && startpage.length() != 0) {
				documentPo.setVolume(Integer.parseInt(startpage));
			}
			if (endpage != null && endpage.length() != 0) {
				documentPo.setVolume(Integer.parseInt(endpage));
			}
			documentPo.setEditor(editor);
			documentPo.setBookname(bookname);
			documentPo.setCollegename(collegename);
			documentPo.setIsbn(isbn);
			documentPo.setIs_draft(false);
			Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
			documentPo.setCreate_time(curDate);
			// 上传文件到服务器的upload文件夹
			String realpath = ServletActionContext.getServletContext()
					.getRealPath("/upload");
			System.out.println("realpath: " + realpath);
			System.out.println(image);
			System.out.println(imageFileName);
			System.out.println(imageContentType);
			AttachmentPo attachmentPo = new AttachmentPo();

			if (image != null) {
				File savefile = new File(new File(realpath), imageFileName);
				if (!savefile.getParentFile().exists())
					savefile.getParentFile().mkdirs();
				FileUtils.copyFile(image, savefile);
				// ActionContext.getContext().put("message", "文件上传成功");
			}
			int did = Integer.parseInt(documentService.addDocument(documentPo));
			attachmentPo.setDid(did);
			attachmentPo.setUrl(realpath + "\\" + imageFileName);
			attachmentPo.setCreate_time(curDate);
			attachmentPo.setUsername(username);
			List<AttachmentPo> las = new ArrayList<AttachmentPo>();
			las.add(attachmentPo);
			attachmentService.addAttachment(las);

			if (refList != null && refList.size() > 0) {
				for (int i = 0; i < refList.size(); i++) {
					ReferencePo refPo = new ReferencePo();
					refPo.setSrc_did(did);
					refPo.setDes_did(refList.get(i).getDes_did());
					refPo.setRef_type(refList.get(i).getRef_type());
					refService.addReference(refPo);
				}
			}

			return super.execute();
		} catch (NumberFormatException e) {
			return ERROR;
		}
	}

	public String findDocInfo() throws Exception {
		docLists = documentService.queryById(id);
		attachmentList = attachmentService.queryByDid(id);
		tagList = tagService.queryByDid(id);
		refList = new ArrayList<ReferenceVo>();
		List<ReferencePo> tem = refService.queryByDid(id);
		for (int i = 0; i < tem.size(); i++) {
			ReferenceVo vo = new ReferenceVo();
			ReferencePo po = tem.get(i);
			vo.setId(po.getId());
			vo.setRef_type(po.getRef_type());
			vo.setDes_did(po.getDes_did());
			vo.setSrc_did(po.getSrc_did());
			List<DocumentPo> document = documentService.queryById(po
					.getSrc_did() + "");
			if (document != null)
				vo.setSrc(document.get(0).getTitle());
			List<DocumentPo> document2 = documentService.queryById(po
					.getDes_did() + "");
			if (document2 != null)
				vo.setDes(document.get(0).getTitle());
			refList.add(vo);
		}
		refedList = new ArrayList<ReferenceVo>();
		List<ReferencePo> tem2 = refService.queryReferenced(id);
		for (int i = 0; i < tem2.size(); i++) {
			ReferenceVo vo = new ReferenceVo();
			ReferencePo po = tem2.get(i);
			vo.setId(po.getId());
			vo.setRef_type(po.getRef_type());
			vo.setDes_did(po.getDes_did());
			vo.setSrc_did(po.getSrc_did());
			List<DocumentPo> document = documentService.queryById(po
					.getSrc_did() + "");
			if (document != null)
				vo.setSrc(document.get(0).getTitle());
			List<DocumentPo> document2 = documentService.queryById(po
					.getDes_did() + "");
			if (document2 != null)
				vo.setDes(document2.get(0).getTitle());
			refedList.add(vo);
		}
		rateList = rateService.queryScoreByDid(id);
		return super.execute();
	}

	public String queryById() throws Exception {
		docLists = documentService.queryById(id);
		return super.execute();
	}

	public List<RateInfoPo> getRateList() {
		return rateList;
	}

	public void setRateList(List<RateInfoPo> rateList) {
		this.rateList = rateList;
	}

	public void setRateService(IRateService rateService) {
		this.rateService = rateService;
	}

	public String findDocsByUser() throws Exception {
		docLists = documentService.getDocsByUser((String) ActionContext
				.getContext().getSession().get("username"));
		return super.execute();
	}

	public String findall() throws Exception {
		docLists = documentService.findAll();
		return super.execute();
	}

	public String search() throws Exception {
		docLists = documentService.search(type, year, endyear, title, author,
				tag, abstracts);
		return super.execute();
	}

	public String simpleSearch() throws Exception {
		docLists = documentService.simpleSearch(title);
		return super.execute();
	}

	public String saveInfo() throws Exception {
		DocumentPo po = new DocumentPo();
		po.setId(3);
		po.setAbstracts(abstracts);
		po.setAuthor(author);
		po.setKeywords(keywords);
		po.setPages(Integer.parseInt(pages));
		po.setPublisher(publisher);
		po.setTitle(title);
		po.setYear(Integer.parseInt(year));
		documentService.updateInfo(po);
		return super.execute();
	}

	public String saveForUpdate() {
		Date curDate = new Date(System.currentTimeMillis());
		documentPo.setUpdate_time(curDate);
		return documentService.saveForUpdate(documentPo);
	}

	public String findDocType() throws Exception {
		ldas = documentService.getDocType(type);
		return super.execute();
	}

	public String addDocType() throws Exception {
		Doc_typePo cpo = new Doc_typePo();
		cpo.setType(type);
		id = documentService.addDocType(cpo) + "";
		return super.execute();
	}

	public String addDocAttribute() throws Exception {
		DocumentAttributePo cpo = new DocumentAttributePo();
		cpo.setType(type);
		cpo.setAttribute(attribute);
		id = documentService.addAttribute(cpo) + "";
		return super.execute();
	}

	public String findAllType() throws Exception {
		docTypeLists = documentService.findAllType();
		return super.execute();
	}

	public String delType() throws Exception {
		documentService.deleteType(id);
		return super.execute();
	}

	public String delDoc() throws Exception {
		documentService.deleteDoc(id);
		return super.execute();
	}

	public String delAttribute() throws Exception {
		documentService.deleteAttribute(id);
		return super.execute();

	}

	public DocumentPo mentPo() {
		return documentPo;
	}

	public void setDocumentPo(DocumentPo documentPo) {
		this.documentPo = documentPo;
	}

	public void setDocumentService(IDocumentService documentService) {
		this.documentService = documentService;
	}

	public void setAttachmentService(IAttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getPages() {
		return pages;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}

	public String getAbstracts() {
		return abstracts;
	}

	public void setAbstracts(String abstracts) {
		this.abstracts = abstracts;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<DocumentPo> getDocLists() {
		return docLists;
	}

	public void setDocLists(List<DocumentPo> docLists) {
		this.docLists = docLists;
	}

	public List<AttachmentPo> getAttachmentList() {
		return attachmentList;
	}

	public void setAttachmentList(List<AttachmentPo> attachmentList) {
		this.attachmentList = attachmentList;
	}

	public void setTagService(ITagService tagService) {
		this.tagService = tagService;
	}

	public void setRefService(IRefService refService) {
		this.refService = refService;
	}

	public List<RateInfoPo> getRateInfo() {
		return rateInfo;
	}

	public void setRateInfo(List<RateInfoPo> rateInfo) {
		this.rateInfo = rateInfo;
	}

	public List<TagPo> getTagList() {
		return tagList;
	}

	public List<Doc_typePo> getDocTypeLists() {
		return docTypeLists;
	}

	public void setDocTypeLists(List<Doc_typePo> docTypeLists) {
		this.docTypeLists = docTypeLists;
	}

	public void setTagList(List<TagPo> tagList) {
		this.tagList = tagList;
	}

	public List<ReferenceVo> getRefList() {
		return refList;
	}

	public void setRefList(List<ReferenceVo> refList) {
		this.refList = refList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<DocumentAttributePo> getLdas() {
		return ldas;
	}

	public void setLdas(List<DocumentAttributePo> ldas) {
		this.ldas = ldas;
	}

	public String getPublication() {
		return publication;
	}

	public void setPublication(String publication) {
		this.publication = publication;
	}

	public String getPeriodicalname() {
		return periodicalname;
	}

	public void setPeriodicalname(String periodicalname) {
		this.periodicalname = periodicalname;
	}

	public String getConferencename() {
		return conferencename;
	}

	public void setConferencename(String conferencename) {
		this.conferencename = conferencename;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	public String getStartpage() {
		return startpage;
	}

	public void setStartpage(String startpage) {
		this.startpage = startpage;
	}

	public String getEndpage() {
		return endpage;
	}

	public void setEndpage(String endpage) {
		this.endpage = endpage;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getCollegename() {
		return collegename;
	}

	public void setCollegename(String collegename) {
		this.collegename = collegename;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getEndyear() {
		return endyear;
	}

	public void setEndyear(String endyear) {
		this.endyear = endyear;
	}

	public List<ReferenceVo> getRefedList() {
		return refedList;
	}

	public void setRefedList(List<ReferenceVo> refedList) {
		this.refedList = refedList;
	}
}
