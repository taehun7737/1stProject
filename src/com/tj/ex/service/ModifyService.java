package com.tj.ex.service;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.ex.dao.MemberDao;
import com.tj.ex.dto.MemberDto;
public class ModifyService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
			String mId = request.getParameter("mId");
			String mPw = request.getParameter("mPw");
			String mName = request.getParameter("mName");
			String mEmail = request.getParameter("mEmail");
			String mBirthStr = request.getParameter("mBirth");
			Date mBirth = null;
			if(!mBirthStr.equals("")) {
				mBirth = Date.valueOf(mBirthStr);
			}
			String mAddress = request.getParameter("mAddress");
			String mPhone = request.getParameter("mPhone");
			MemberDao mDao = MemberDao.getInstance();
			MemberDto member = new MemberDto(mId, mPw, mName, mEmail, mBirth, mAddress, mPhone, null);
			int result = mDao.modifyMember(member);
			if(result == MemberDao.SUCCESS) { // 수정 성공시 세션도 수정
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("member", member);
				request.setAttribute("modifyResult", "회원정보 수정 성공");
			}else {
				// 수정 실패시 
				request.setAttribute("modifyResult", "회원정보 수정 실패");
		}
	}
}