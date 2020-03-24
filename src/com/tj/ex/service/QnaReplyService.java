package com.tj.ex.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.Qna_boardDao;

public class QnaReplyService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mId = request.getParameter("mId");
		String aId = request.getParameter("aId");
		String bHead = request.getParameter("bHead");
		String bContent = request.getParameter("bContent");
		int bGroup = Integer.parseInt(request.getParameter("bGroup"));
		int bStep = Integer.parseInt(request.getParameter("bStep"));
		int bIndent = Integer.parseInt(request.getParameter("bIndent"));
		String bIp = request.getRemoteAddr();
		Qna_boardDao boardDao = Qna_boardDao.getInstance();
		int result = boardDao.qnaReply(mId, aId, bHead, bContent, bGroup, bStep, bIndent, bIp);
		if(result == Qna_boardDao.SUCCESS) {
			request.setAttribute("resultMsg", "답변 성공");
		}else {
			request.setAttribute("resultMsg", "답변 실패");
		}
		String pageNum = request.getParameter("pageNum");
		request.setAttribute("pageNum", pageNum);
	}
}