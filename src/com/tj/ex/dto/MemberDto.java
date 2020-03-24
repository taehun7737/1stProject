package com.tj.ex.dto;

import java.sql.Date;

public class MemberDto {
	private String mId;
	private String mPw;
	private String mName;
	private String mEmail;
	private Date mBirth;
	private String mAddress;
	private String mPhone;
	private Date mRdate;
	public MemberDto() {}
	public MemberDto(String mId, String mPw, String mName, String mEmail, Date mBirth, String mAddress, String mPhone,
			Date mRdate) {
		this.mId = mId;
		this.mPw = mPw;
		this.mName = mName;
		this.mEmail = mEmail;
		this.mBirth = mBirth;
		this.mAddress = mAddress;
		this.mPhone = mPhone;
		this.mRdate = mRdate;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getmPw() {
		return mPw;
	}
	public void setmPw(String mPw) {
		this.mPw = mPw;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getmEmail() {
		return mEmail;
	}
	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}
	public Date getmBirth() {
		return mBirth;
	}
	public void setmBirth(Date mBirth) {
		this.mBirth = mBirth;
	}
	public String getmAddress() {
		return mAddress;
	}
	public void setmAddress(String mAddress) {
		this.mAddress = mAddress;
	}
	public String getmPhone() {
		return mPhone;
	}
	public void setmPhone(String mPhone) {
		this.mPhone = mPhone;
	}
	public Date getmRdate() {
		return mRdate;
	}
	public void setmRdate(Date mRdate) {
		this.mRdate = mRdate;
	}
	@Override
	public String toString() {
		return "MemberDto [mId=" + mId + ", mPw=" + mPw + ", mName=" + mName + ", mEmail=" + mEmail + ", mBirth="
				+ mBirth + ", mAddress=" + mAddress + ", mPhone=" + mPhone + ", mRdate=" + mRdate + "]";
	}
	
}
