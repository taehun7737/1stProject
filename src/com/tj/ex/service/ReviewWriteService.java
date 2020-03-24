package com.tj.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.ex.dao.Review_boardDao;
import com.tj.ex.dto.MemberDto;

public class ReviewWriteService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession httpSession = request.getSession();
		String mId = ((MemberDto)httpSession.getAttribute("member")).getmId();
		String eHead = request.getParameter("eHead");
		String eContent = request.getParameter("eContent");
		String eIp = request.getRemoteAddr();
		Review_boardDao boardDao = Review_boardDao.getInstance();
		int result = boardDao.reviewWrite(mId, eHead, eContent, eIp);
		// joinMember결과에 따라 적절히 request.setAttribute
		if(result == Review_boardDao.SUCCESS) { // 회원가입 진행
			request.setAttribute("resultMsg", "글쓰기 성공");
		}else {
			request.setAttribute("resultMsg", "글쓰기 실패");
		}
	}
}
