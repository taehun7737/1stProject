package com.tj.ex.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.Qna_boardDao;

public class QnaDeleteService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int bNo = Integer.parseInt(request.getParameter("bNo"));
		Qna_boardDao qDao = Qna_boardDao.getInstance();
		int result = qDao.qnaDelete(bNo);
		if(result == Qna_boardDao.SUCCESS) {
			request.setAttribute("resultMsg", "QnA 글삭제 성공");
		}else {
			request.setAttribute("resultMsg", "QnA 글삭제 실패");
		}
	}
}