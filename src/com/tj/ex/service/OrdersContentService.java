package com.tj.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.OrderDao;
public class OrdersContentService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mId = request.getParameter("mId");
		int pNo = Integer.parseInt(request.getParameter("pNo"));
		String oAddress = request.getParameter("oAddress");
		int oQty = Integer.parseInt(request.getParameter("oQty"));
		String oPhone = request.getParameter("oPhone");
		OrderDao oDao = OrderDao.getInstance();
		int result = oDao.OrdersRegister(mId, pNo, oAddress, oQty, oPhone);
		if(result == OrderDao.SUCCESS) {
			request.setAttribute("resultMsg", "주문 성공");
		}else {
			request.setAttribute("resultMsg", "주문 실패");
		}
	}
}
