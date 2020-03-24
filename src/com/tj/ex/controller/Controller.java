package com.tj.ex.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.service.*;

@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		actionDo(request, response);
	}
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String command = uri.substring(conPath.length());
		Service service = null;
		String viewPage = null;
		if (command.equals("/main.do")) {
			viewPage = "main/main.jsp";
		}
			///////////////////////////////////////////////////
			//						Member					 //
			///////////////////////////////////////////////////
		if (command.equals("/loginView.do")) {
			viewPage = "member/login.jsp";
		} else if (command.equals("/login.do")) {
			// service의 execute() 호출(LoginService.java) - 로그인 여부에 따라 세션
			service = new LoginService();
			service.execute(request, response);
			viewPage = "main/main.jsp";
		} else if (command.equals("/joinView.do")) {
			viewPage = "member/join.jsp";
		} else if (command.equals("/join.do")) { // id중복체크 후 회원가입
			service = new JoinService();
			service.execute(request, response);
			viewPage = "member/login.jsp";
		} else if (command.equals("/modifyView.do")) {
			viewPage = "member/modify.jsp";
		} else if (command.equals("/modify.do")) {
			//service의 execute()호출 (ModifyService.java) - DB에 수정
			service = new ModifyService();
			service.execute(request, response);
			viewPage = "main/main.jsp";
		}else if(command.equals("/logout.do")) {
			// service의 execute()호출 (LogoutService.java) - 세션 날리기
			service = new LogoutService();
			service.execute(request, response);
			viewPage = "main/main.jsp";
		}else if(command.equals("/idConfirm.do")) {
			service = new IdConfirmService();
			service.execute(request, response);
			viewPage = "member/idConfirm.jsp";
		}else if(command.equals("/allView.do")) {
			// service의 execute()호출 (AllViewService.java) - 회원목록가져오기
			service = new AllViewService();
			service.execute(request, response);
			viewPage = "member/AllView.jsp";
		}
		///////////////////////////////////////////////////
		//						Admin					 //
		///////////////////////////////////////////////////
		else if(command.equals("/adminloginView.do")) {
			viewPage = "admin/adminLogin.jsp";
		}else if(command.equals("/adminLogin.do")) {
			service = new ALoginService();
			service.execute(request, response);
			viewPage = "allView.do";
		}
		///////////////////////////////////////////////////
		//					REVIEW_BOARD				 //
		///////////////////////////////////////////////////
		if(command.equals("/reviewList.do")) { // 후기 글목록
			service = new ReviewListService();
			service.execute(request, response);
			viewPage = "review/reviewList.jsp";
		}else if(command.equals("/reviewWriteView.do")) { // 후기 글쓰기 view
			viewPage = "review/reviewWrite.jsp";
		}else if(command.equals("/reviewWrite.do")) { // 후기 글쓰기 DB 저장
			service = new ReviewWriteService();
			service.execute(request, response);
			viewPage = "reviewList.do";
		}else if(command.equals("/reviewContent.do")) { // 후기글 상세보기
			service = new ReviewContentService();
			service.execute(request, response);
			viewPage = "review/reviewContent.jsp";
		}else if(command.equals("/reviewModifyView.do")){ // 후기 수정 view 페이지
			service = new ReviewModifyViewService();
			service.execute(request, response);
			viewPage = "review/reviewModify.jsp";
		}else if(command.equals("/reviewModify.do")) { // 후기글 DB에 수정
			service = new ReviewModifyService();
			service.execute(request, response);
			viewPage = "reviewList.do";
		}else if(command.equals("/reviewDelete.do")) {// 후기글 DB에 삭제
			service = new ReviewDeleteService();
			service.execute(request, response);
			viewPage = "reviewList.do";
		}
		///////////////////////////////////////////////////
		//						product					 //
		///////////////////////////////////////////////////
		if(command.equals("/productList.do")) { // 상품 리스트
			service = new ProductListService();
			service.execute(request, response);
			viewPage = "product/productList.jsp";
		}else if(command.equals("/productWriteView.do")) { // 상품 등록 view
			viewPage = "product/productWrite.jsp";
		}else if(command.equals("/productWrite.do")) { // 상품 등록 DB 저장
			service = new ProductWriteService();
			service.execute(request, response);
			viewPage = "productList.do";
		}else if(command.equals("/productContent.do")) { // 상품 상세보기
			service = new ProductContentService();
			service.execute(request, response);
			viewPage = "product/productContent.jsp";
		}else if(command.equals("/productModifyView.do")){ // 상품 수정 view 페이지
			service = new ProductModifyViewService();
			service.execute(request, response);
			viewPage = "product/productModify.jsp";
		}else if(command.equals("/productModify.do")) { // 상품글 DB에 수정
			service = new ProductModifyService();
			service.execute(request, response);
			viewPage = "productList.do";
		}else if(command.equals("/productDelete.do")) {// 상품글 DB에 삭제
			// service의 execute()호출 - BDeleteService.java
			service = new ProductDeleteService();
			service.execute(request, response);
			viewPage = "productList.do";
		}
		///////////////////////////////////////////////////
		//					QNA_BOARD					 //
		///////////////////////////////////////////////////
		if(command.equals("/qnaList.do")) { // 질문글목록
			service = new QnaListService();
			service.execute(request, response);
			viewPage = "qna/qnaList.jsp";
		}else if(command.equals("/qnaWriteView.do")) { // 질문글쓰기 view
			viewPage = "qna/qnaWrite.jsp";
		}else if(command.equals("/qnaWrite.do")) { // 질문글쓰기 DB 저장
			service = new QnaWriteService();
			service.execute(request, response);
			viewPage = "qnaList.do";
		}else if(command.equals("/qnaContent.do")) { // 질문글 상세보기
			service = new QnaContentService();
			service.execute(request, response);
			viewPage = "qna/qnaContent.jsp";
		}else if(command.equals("/qnaModifyView.do")){ // 질문글 수정 view 페이지
			service = new QnaModifyViewService();
			service.execute(request, response);
			viewPage = "qna/qnaModify.jsp";
		}else if(command.equals("/qnaModify.do")) { // 질문글 DB에 수정
			service = new QnaModifyService();
			service.execute(request, response);
			viewPage = "qnaList.do";
		}else if(command.equals("/qnaDelete.do")) {// 질문글 DB에 삭제
			service = new QnaDeleteService();
			service.execute(request, response);
			viewPage = "qnaList.do";
		}else if(command.equals("/qnaReplyView.do")) {// 질문글 답변쓰기 view
			service = new QnaReplyViewService();
			service.execute(request, response);
			viewPage = "qna/qnaReplyView.jsp";
		}else if(command.equals("/qnaReply.do")) {// 질문글 답변 DB에 저장
			service = new QnaReplyService();
			service.execute(request, response);
			viewPage = "qnaList.do";
		}
		///////////////////////////////////////////////////
		//						ORDERS					 //
		///////////////////////////////////////////////////
		if(command.equals("/ordersList.do")) { // 주문 목록
			service = new OrdersListService();
			service.execute(request, response);
			viewPage = "orders/ordersList.jsp";
		}else if(command.equals("/ordersView.do")) { // 주문 버튼 클릭시 product에서 상품명, 설명, 가격 받아오고 배송지, 수량, 전화번호 입력
			service = new OrdersViewService();
			service.execute(request, response);
			viewPage = "orders/ordersView.jsp";
		}else if(command.equals("/ordersDo.do")) { // 주문 입력
			service = new OrdersDoService();
			service.execute(request, response);
			viewPage = "ordersList.do";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
