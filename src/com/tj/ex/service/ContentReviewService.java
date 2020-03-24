package com.tj.ex.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.Review_boardDao;
import com.tj.ex.dto.Review_boardDto;
public class ContentReviewService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int eNo = Integer.parseInt(request.getParameter("eNo"));
		Review_boardDao boardDao = Review_boardDao.getInstance();
		Review_boardDto dto = boardDao.reviewContent(eNo);
		request.setAttribute("contentReview", dto);
	}

}