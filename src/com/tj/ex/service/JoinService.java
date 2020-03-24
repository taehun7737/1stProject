package com.tj.ex.service;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.ex.dao.MemberDao;
import com.tj.ex.dto.MemberDto;

public class JoinService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mId = request.getParameter("mId");
		String mPw = request.getParameter("mPw");
		String mName = request.getParameter("mName");
		String mEmail = request.getParameter("mEmail");
		String mBirthStr = request.getParameter("mBirth");	
		Date mBirth = null;
		if (!mBirthStr.equals("")) {
			mBirth = Date.valueOf(mBirthStr);
		}
		String mAddress = request.getParameter("mAddress");
		String mPhone = request.getParameter("mPhone");
		MemberDao mDao = MemberDao.getInstance();
		// ID 중복체크
		int result = mDao.idConfirm(mId);
		if (result == MemberDao.NONEXISTENT) {// 회원가입 (dto로)
			MemberDto member = new MemberDto(mId, mPw, mName, mEmail, mBirth, mAddress, mPhone, null);
			result = mDao.joinMember(member); // 회원가입
			if (result == MemberDao.SUCCESS) {
				HttpSession session = request.getSession();
				session.setAttribute("mId", mId);
				request.setAttribute("joinResult", "회원가입이 완료되었습니다");
			} else {
				request.setAttribute("errorMsg", "길어서 가입이 실패되었습니다.");
			}
		} else {
			request.setAttribute("errorMsg", "중복된 ID라서 회원가입 불가합니다");
		}
	}
}
