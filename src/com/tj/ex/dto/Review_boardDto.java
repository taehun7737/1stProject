package com.tj.ex.dto;

import java.sql.Date;

public class Review_boardDto {
	private int eNo;
	private String mId;
	private String mName;
	private String eHead;
	private String eContent;
	private Date eRdate;
	private String eIp;
	public Review_boardDto() {}
	public Review_boardDto(int eNo, String mId, String mName, String eHead, String eContent, Date eRdate, String eIp) {
		this.eNo = eNo;
		this.mId = mId;
		this.mName = mName;
		this.eHead = eHead;
		this.eContent = eContent;
		this.eRdate = eRdate;
		this.eIp = eIp;
	}
	public int geteNo() {
		return eNo;
	}
	public void seteNo(int eNo) {
		this.eNo = eNo;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String geteHead() {
		return eHead;
	}
	public void seteHead(String eHead) {
		this.eHead = eHead;
	}
	public String geteContent() {
		return eContent;
	}
	public void seteContent(String eContent) {
		this.eContent = eContent;
	}
	public Date geteRdate() {
		return eRdate;
	}
	public void seteRdate(Date eRdate) {
		this.eRdate = eRdate;
	}
	public String geteIp() {
		return eIp;
	}
	public void seteIp(String eIp) {
		this.eIp = eIp;
	}
	@Override
	public String toString() {
		return "Review_boardDto [eNo=" + eNo + ", mId=" + mId + ", mName=" + mName + ", eHead=" + eHead + ", eContent="
				+ eContent + ", eRdate=" + eRdate + ", eIp=" + eIp + "]";
	}
	
}
