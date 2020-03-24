package com.tj.ex.dto;


public class ProductDto {
	private int pNo;
	private String pName;
	private String pFileName;
	private String pDetail;
	private int pPrice;
	public ProductDto() {}
	public ProductDto(int pNo, String pName, String pFileName, String pDetail, int pPrice) {
		this.pNo = pNo;
		this.pName = pName;
		this.pFileName = pFileName;
		this.pDetail = pDetail;
		this.pPrice = pPrice;
	}
	public int getpNo() {
		return pNo;
	}
	public void setpNo(int pNo) {
		this.pNo = pNo;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getpFileName() {
		return pFileName;
	}
	public void setpFileName(String pFileName) {
		this.pFileName = pFileName;
	}
	public String getpDetail() {
		return pDetail;
	}
	public void setpDetail(String pDetail) {
		this.pDetail = pDetail;
	}
	public int getpPrice() {
		return pPrice;
	}
	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}
	@Override
	public String toString() {
		return "ProductDto [pNo=" + pNo + ", pName=" + pName + ", pFileName=" + pFileName + ", pDetail=" + pDetail
				+ ", pPrice=" + pPrice + "]";
	}
	
}
