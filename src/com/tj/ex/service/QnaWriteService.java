package com.tj.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.ex.dao.Qna_boardDao;
import com.tj.ex.dto.MemberDto;

public class QnaWriteService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession httpSession = request.getSession();
		String mId = ((MemberDto)httpSession.getAttribute("member")).getmId();
		String bHead = request.getParameter("bHead");
		String bContent = request.getParameter("bContent");
		String bIp = request.getRemoteAddr();
		Qna_boardDao boardDao = Qna_boardDao.getInstance();
		int result = boardDao.qnaWrite(mId, bHead, bContent, bIp);
		// joinMember결과에 따라 적절히 request.setAttribute
		if(result == Qna_boardDao.SUCCESS) { // 회원가입 진행
			request.setAttribute("resultMsg", "QnA 글쓰기 성공");
		}else {
			request.setAttribute("resultMsg", "QnA 글쓰기 실패");
		}
	}
}
