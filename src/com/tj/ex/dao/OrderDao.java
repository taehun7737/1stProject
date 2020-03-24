package com.tj.ex.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tj.ex.dto.OrdersDto;

public class OrderDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private static OrderDao instance = new OrderDao();
	public static OrderDao getInstance() {
		return instance;
	}
	private OrderDao() {}
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
			conn = ds.getConnection();
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	// 주문 목록(startRow~endRow)
	public ArrayList<OrdersDto> OrdersList(int startRow, int endRow) {
		ArrayList<OrdersDto> dtos = new ArrayList<OrdersDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM(SELECT O.*, pPrice,pName FROM ORDERS O, PRODUCT P WHERE P.PNO=O.PNO ORDER BY ONO DESC) A) " + 
				"    WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int oNo = rs.getInt("oNo");
				String mId = rs.getString("mId");
				String pName = rs.getString("pName");
				int pNo = rs.getInt("pNo");
				int pPrice = rs.getInt("pPrice");
				String oAddress = rs.getString("oAddress");
				Date oRdate = rs.getDate("oRdate");
				int oQty = rs.getInt("oQty");
				String oPhone = rs.getString("oPhone");
				dtos.add(new OrdersDto(oNo, mId, pNo, oAddress, oRdate, oQty, oPhone, pName, pPrice));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return dtos;
	}

	// 주문 상품 개수
	public int getOrderTotCnt(String mId) {
		int totCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM ORDERS WHERE mID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				totCnt = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return totCnt;
	}
	// 총 주문 개수
	public int getTotCnt() {
		int totCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM ORDERS";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				totCnt = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return totCnt;
	}
	// oNo로 dto 가져오기
	public OrdersDto OrdersView(int oNo) {
		OrdersDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT O.*, pPrice FROM ORDERS O, PRODUCT P WHERE P.PNO=O.PNO AND ONO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, oNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String mId = rs.getString("mId");
				int pNo = rs.getInt("pNo");
				String oAddress = rs.getString("oAddress");
				Date oRdate = rs.getDate("oRdate");
				int oQty = rs.getInt("oQty");
				String oPhone = rs.getString("oPhone");
				int pPrice = rs.getInt("pPrice");
				dto = new OrdersDto(oNo, mId, pNo, oAddress, oRdate, oQty, oPhone, null, pPrice);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return dto;
	}
	// 주문 등록
	public int OrdersRegister(String mId, int pNo, String oAddress, int oQty, String oPhone) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO ORDERS(ONO, MID, PNO, OADDRESS, OQTY, OPHONE)" + 
				"				VALUES (ORDERS_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setInt(2, pNo);
			pstmt.setString(3, oAddress);
			pstmt.setInt(4, oQty);
			pstmt.setString(5, oPhone);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "주문 성공" : "주문 실패");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	// 주문 상품 상세보기
	public OrdersDto ordersContent(int oNo) {
		OrdersDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM ORDERS WHERE ONO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, oNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String mId = rs.getString("mId");
				int pNo = rs.getInt("pNo");
				String oAddress = rs.getString("oAddress");
				Date oRdate = rs.getDate("oRdate");
				int oQty = rs.getInt("oQty");
				String oPhone = rs.getString("oPhone");
				dto = new OrdersDto(oNo, mId, pNo, oAddress, oRdate, oQty, oPhone, null, 0);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return dto;
	}
}
