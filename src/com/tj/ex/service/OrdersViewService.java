package com.tj.ex.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.ProductDao;
import com.tj.ex.dto.ProductDto;

public class OrdersViewService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int pNo = Integer.parseInt(request.getParameter("pNo"));
		ProductDao oDao = ProductDao.getInstance();
		ProductDto dto = oDao.productContent(pNo);
		request.setAttribute("product", dto);
	}

}