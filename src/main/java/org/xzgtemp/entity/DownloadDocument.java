package org.xzgtemp.entity;

import java.sql.Date;

public class DownloadDocument {
	private Long id;
	private String uid;
	private Long did;
	private Date downloadtime;
	
	public DownloadDocument(){
		
	}

	public DownloadDocument(Long id,String uid,Long did,Date downloadtime) {
		this.id=id;
		this.did=did;
		this.uid=uid;
		this.downloadtime=downloadtime;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid){
		this.uid = uid;
	}

	public Long getDid() {
		return did;
	}

	public void setDid(Long did){
		this.did = did;
	}

	public Date getDownloadtime(){
		return downloadtime;
	}

	public void setDownloadtime(Date downloadtime){
		this.downloadtime = downloadtime;
	}
    
}
