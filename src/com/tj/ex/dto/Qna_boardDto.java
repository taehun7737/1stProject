package com.tj.ex.dto;

import java.sql.Date;

public class Qna_boardDto {
	private int bNo;
	private String mId;
	private String aId;
	private String writer; // join
	private String bHead;
	private String bContent;
	private Date bRdate;
	private int bHit;
	private int bGroup;
	private int bStep;
	private int bIndent;
	private String bIp;
	public Qna_boardDto() {	}
	public Qna_boardDto(int bNo, String mId, String aId, String writer, String bHead, String bContent, Date bRdate,
			int bHit, int bGroup, int bStep, int bIndent, String bIp) {
		this.bNo = bNo;
		this.mId = mId;
		this.aId = aId;
		this.writer = writer;
		this.bHead = bHead;
		this.bContent = bContent;
		this.bRdate = bRdate;
		this.bHit = bHit;
		this.bGroup = bGroup;
		this.bStep = bStep;
		this.bIndent = bIndent;
		this.bIp = bIp;
	}
	public int getbNo() {
		return bNo;
	}
	public void setbNo(int bNo) {
		this.bNo = bNo;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getaId() {
		return aId;
	}
	public void setaId(String aId) {
		this.aId = aId;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getbHead() {
		return bHead;
	}
	public void setbHead(String bHead) {
		this.bHead = bHead;
	}
	public String getbContent() {
		return bContent;
	}
	public void setbContent(String bContent) {
		this.bContent = bContent;
	}
	public Date getbRdate() {
		return bRdate;
	}
	public void setbRdate(Date bRdate) {
		this.bRdate = bRdate;
	}
	public int getbHit() {
		return bHit;
	}
	public void setbHit(int bHit) {
		this.bHit = bHit;
	}
	public int getbGroup() {
		return bGroup;
	}
	public void setbGroup(int bGroup) {
		this.bGroup = bGroup;
	}
	public int getbStep() {
		return bStep;
	}
	public void setbStep(int bStep) {
		this.bStep = bStep;
	}
	public int getbIndent() {
		return bIndent;
	}
	public void setbIndent(int bIndent) {
		this.bIndent = bIndent;
	}
	public String getbIp() {
		return bIp;
	}
	public void setbIp(String bIp) {
		this.bIp = bIp;
	}
	@Override
	public String toString() {
		return "Qna_boardDto [bNo=" + bNo + ", mId=" + mId + ", aId=" + aId + ", writer=" + writer + ", bHead=" + bHead
				+ ", bContent=" + bContent + ", bRdate=" + bRdate + ", bHit=" + bHit + ", bGroup=" + bGroup + ", bStep="
				+ bStep + ", bIndent=" + bIndent + ", bIp=" + bIp + "]";
	}	
}
