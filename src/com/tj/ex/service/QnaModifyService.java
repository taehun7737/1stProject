package com.tj.ex.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.Qna_boardDao;

public class QnaModifyService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int bNo = Integer.parseInt(request.getParameter("bNo"));
		String bHead = request.getParameter("bHead");
		String bContent = request.getParameter("bContent");
		String bIp = request.getRemoteAddr();
		Qna_boardDao boardDao = Qna_boardDao.getInstance();
		int result = boardDao.QnaModify(bNo, bHead, bContent, bIp);
		if(result == Qna_boardDao.SUCCESS) {
			request.setAttribute("resultMsg", "QnA 수정 성공");
		}else {
			request.setAttribute("resultMsg", "QnA 수정 실패");
		}
		String pageNum = request.getParameter("pageNum");
		request.setAttribute("pageNum", pageNum);
	}
}