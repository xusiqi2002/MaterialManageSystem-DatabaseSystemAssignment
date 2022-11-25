package org.xzgtemp.entity;

import java.sql.Date;

public class Document {

	private Long id;
	private String title;
	private String author;
	private String uploader;
	private Date uploadtime;
	private String filepath;
	private Integer downloadtimes = 0;
	private String doi;
	private String literature;//期刊或会议名

	public Document(){
	}

	
	public Document(Long id, String title, String author, String uploader, Date uploadtime, String filepath,
			Integer downloadtimes, String doi, String literature) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.uploader = uploader;
		this.uploadtime = uploadtime;
		this.filepath = filepath;
		this.downloadtimes = downloadtimes;
		this.doi = doi;
		this.literature = literature;
	}


	public Document(
		String title,
		String author,
		String uploader,
		Date uploadtime,
		String filepath
	) {
		setTitle(title);
		setAuthor(author);
		setUploader(uploader);
		setUploadtime(uploadtime);
		setFilepath(filepath);
	}

	public Document(
		String title,
		String author,
		String uploader,
		Date uploadtime,
		String filepath,
		Integer downloadtimes
	) {
		setTitle(title);
		setAuthor(author);
		setUploader(uploader);
		setUploadtime(uploadtime);
		setFilepath(filepath);
		setDownloadtimes(downloadtimes);
	}

	public Document(
		Long id,
		String title,
		String author,
		String uploader,
		Date uploadtime,
		String filepath
	) {
		setId(id);
		setTitle(title);
		setAuthor(author);
		setDownloadtimes(downloadtimes);
		setUploader(uploader);
		setUploadtime(uploadtime);
		setFilepath(filepath);
		setDownloadtimes(downloadtimes);
	}

	public Document(
		Long id,
		String title,
		String author,
		String uploader,
		Date uploadtime,
		String filepath,
		Integer downloadtimes
	) {
		setId(id);
		setTitle(title);
		setAuthor(author);
		setUploader(uploader);
		setUploadtime(uploadtime);
		setFilepath(filepath);
		setDownloadtimes(downloadtimes);
	}

	//论文id
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id=id;
	}
	
	//论文名
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		if(title == ""){
			throw new IllegalArgumentException("invalid title value");
		}
		this.title=title;
	}
	
	//作者
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author=author;
	}
	
	//上传信息
	public String getUploader() {
		return uploader;
	}
	public void setUploader(String uploader) {
		this.uploader=uploader;
	}
	public Date getUploadtime() {
		return uploadtime;
	}
	public void setUploadtime(Date uploadtime) {
		this.uploadtime=uploadtime;
	}
	
	//下载路径
	public String getFilepath(){
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath=filepath;
	}
    
	//下载次数
	public Integer getDownloadtimes() {
		return downloadtimes;
	}

	public void setDownloadtimes(Integer downloadtimes) {
		this.downloadtimes=downloadtimes;
	}

	public String getDoi() {
		return doi;
	}

	public void setDoi(String doi) {
		this.doi = doi;
	}

	public String getLiterature() {
		return literature;
	}

	public void setLiterature(String literature) {
		this.literature = literature;
	}

	
}
