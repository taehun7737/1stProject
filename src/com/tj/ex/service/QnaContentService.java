package com.tj.ex.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.Qna_boardDao;
import com.tj.ex.dto.Qna_boardDto;

public class QnaContentService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int bNo = Integer.parseInt(request.getParameter("bNo"));
		Qna_boardDao boardDao = Qna_boardDao.getInstance();
		Qna_boardDto dto = boardDao.qnaContentView(bNo);
		request.setAttribute("qnaContent", dto);
	}

}