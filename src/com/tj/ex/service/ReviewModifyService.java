package com.tj.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.Review_boardDao;
public class ReviewModifyService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int eNo = Integer.parseInt(request.getParameter("eNo"));
		String eHead = request.getParameter("eHead");
		String eContent = request.getParameter("eContent");
		Review_boardDao boardDao = Review_boardDao.getInstance();
		int result = boardDao.reviewModify(eNo, eHead, eContent);
		// joinMember결과에 따라 적절히 request.setAttribute
		if(result == Review_boardDao.SUCCESS) { // 회원가입 진행
			request.setAttribute("resultMsg", "글수정 성공");
		}else {
			request.setAttribute("resultMsg", "글수정 실패");
		}
		// pageNum이 request객체에서 온 것은 null값이라 request에 담아놓음
		String pageNum = request.getParameter("pageNum");
		request.setAttribute("pageNum", pageNum);
	}
}