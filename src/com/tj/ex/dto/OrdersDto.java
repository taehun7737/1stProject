package com.tj.ex.dto;

import java.sql.Date;

public class OrdersDto {
	private int oNo;
	private String mId;
	private int pNo;
	private String oAddress;
	private Date oRdate;
	private int oQty;
	private String oPhone;
	private String pName;
	private int pPrice;
	public OrdersDto() {}
	public OrdersDto(int oNo, String mId, int pNo, String oAddress, Date oRdate, int oQty, String oPhone, String pName,
			int pPrice) {
		this.oNo = oNo;
		this.mId = mId;
		this.pNo = pNo;
		this.oAddress = oAddress;
		this.oRdate = oRdate;
		this.oQty = oQty;
		this.oPhone = oPhone;
		this.pName = pName;
		this.pPrice = pPrice;
	}
	public int getoNo() {
		return oNo;
	}
	public void setoNo(int oNo) {
		this.oNo = oNo;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public int getpNo() {
		return pNo;
	}
	public void setpNo(int pNo) {
		this.pNo = pNo;
	}
	public String getoAddress() {
		return oAddress;
	}
	public void setoAddress(String oAddress) {
		this.oAddress = oAddress;
	}
	public Date getoRdate() {
		return oRdate;
	}
	public void setoRdate(Date oRdate) {
		this.oRdate = oRdate;
	}
	public int getoQty() {
		return oQty;
	}
	public void setoQty(int oQty) {
		this.oQty = oQty;
	}
	public String getoPhone() {
		return oPhone;
	}
	public void setoPhone(String oPhone) {
		this.oPhone = oPhone;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public int getpPrice() {
		return pPrice;
	}
	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}
	@Override
	public String toString() {
		return "OrdersDto [oNo=" + oNo + ", mId=" + mId + ", pNo=" + pNo + ", oAddress=" + oAddress + ", oRdate="
				+ oRdate + ", oQty=" + oQty + ", oPhone=" + oPhone + ", pName=" + pName + ", pPrice=" + pPrice + "]";
	}
	
}
