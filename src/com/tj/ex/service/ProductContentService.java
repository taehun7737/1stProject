package com.tj.ex.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.ProductDao;
import com.tj.ex.dto.ProductDto;

public class ProductContentService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int pNo = Integer.parseInt(request.getParameter("pNo"));
		ProductDao productDao = ProductDao.getInstance();
		ProductDto dto = productDao.productContent(pNo);
		request.setAttribute("productContent", dto);
	}

}