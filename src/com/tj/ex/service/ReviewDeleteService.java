package com.tj.ex.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tj.ex.dao.Review_boardDao;
public class ReviewDeleteService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int eNo = Integer.parseInt(request.getParameter("eNo"));
		Review_boardDao rBoardDao = Review_boardDao.getInstance();
		int result = rBoardDao.reviewDelete(eNo);
		if(result == Review_boardDao.SUCCESS) {
			request.setAttribute("resultMsg", "글삭제 성공");
		}else {
			request.setAttribute("resultMsg", "글삭제 실패");
		}
	}
}