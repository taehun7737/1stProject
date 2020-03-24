package com.tj.ex.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tj.ex.dao.Qna_boardDao;
import com.tj.ex.dto.Qna_boardDto;
public class QnaReplyViewService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int bNo = Integer.parseInt(request.getParameter("bNo"));
		Qna_boardDao boardDao = Qna_boardDao.getInstance();
		Qna_boardDto dto      = boardDao.QnaModifyView_replyView(bNo);
		request.setAttribute("QnaReplyView", dto);
	}
}