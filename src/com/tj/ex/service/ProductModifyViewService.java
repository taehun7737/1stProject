package com.tj.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.ProductDao;
import com.tj.ex.dto.ProductDto;

public class ProductModifyViewService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
			String pNoStr = request.getParameter("pNo");
			int pNo = Integer.parseInt(pNoStr);
			ProductDao pDao = ProductDao.getInstance();
			ProductDto dto = pDao.productModifyView(pNo);
			request.setAttribute("productModifyView", dto);
	}
}