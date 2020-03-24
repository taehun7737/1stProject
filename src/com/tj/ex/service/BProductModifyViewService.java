package com.tj.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.ProductDao;
import com.tj.ex.dto.ProductDto;

public class BProductModifyViewService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
			String pNoStr = request.getParameter("pNo");
			System.out.println("pNo : "+pNoStr);
			int pNo = Integer.parseInt(pNoStr);
			ProductDao pDao = ProductDao.getInstance();
			ProductDto dto = pDao.productModifyView(pNo);
			request.setAttribute("productModify", dto);
	}
}