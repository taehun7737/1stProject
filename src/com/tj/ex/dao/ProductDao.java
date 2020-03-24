package com.tj.ex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tj.ex.dto.ProductDto;

public class ProductDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private static ProductDao instance = new ProductDao();

	public static ProductDao getInstance() {
		return instance;
	}

	private ProductDao() {
	}

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

	// 물품 목록(startRow~endRow)
	public ArrayList<ProductDto> ProductList(int startRow, int endRow) {
		ArrayList<ProductDto> dtos = new ArrayList<ProductDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM(SELECT * FROM PRODUCT ORDER BY PNO DESC) A)"
				+ "    WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int pNo = rs.getInt("pNo");
				String pName = rs.getString("pName");
				String pFilename = rs.getString("pFilename");
				String pDetail = rs.getString("pDetail");
				int pPrice = rs.getInt("pPrice");
				dtos.add(new ProductDto(pNo, pName, pFilename, pDetail, pPrice));
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

	// 상품 개수
	public int getProductTotCnt() {
		int totCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM PRODUCT";
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

	// 상품 등록
	public int productRegister(String pName, String pFileName, int pPrice, String pDetail) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO PRODUCT (PNO, PNAME, PFILENAME, PPRICE, PDETAIL) VALUES(PRODUCT_SEQ.NEXTVAL, ?, ?, ?, ?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pName);
			pstmt.setString(2, pFileName);
			pstmt.setInt(3, pPrice);
			pstmt.setString(4, pDetail);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "상품 등록 성공" : "상품 등록 실패");
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

	// 글상세보기
	public ProductDto productContent(int pNo) {
		ProductDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM PRODUCT WHERE PNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String pName = rs.getString("pName");
				String pFileName = rs.getString("pFileName");
				String pDetail = rs.getString("pDetail");
				int pPrice = rs.getInt("pPrice");
				dto = new ProductDto(pNo, pName, pFileName, pDetail, pPrice);
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

	// 상품 수정하기
	public int productModify(String pName, String pFileName, int pPrice, String pDetail, int pNo) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE PRODUCT SET PNAME = ?," + 
				"                   PFILENAME = ?," + 
				"                   PPRICE = ?," + 
				"                   PDETAIL = ?" + 
				"             WHERE PNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pName);
			pstmt.setString(2, pFileName);
			pstmt.setInt(3, pPrice);
			pstmt.setString(4, pDetail);
			pstmt.setInt(5, pNo);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "상품 수정 성공" : "상품 수정 실패");
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

	// 글 삭제하기
	public int productDelete(int pNo) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM PRODUCT WHERE PNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pNo);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	// 수정페이지 가기
	public ProductDto productModifyView(int pNo) {
		ProductDto dto = null;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT * FROM PRODUCT WHERE PNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String pName = rs.getString("pName");
				String pFileName = rs.getString("pFileName");
				String pDetail = rs.getString("pDetail");
				int pPrice = rs.getInt("pPrice");
				dto = new ProductDto(pNo, pName, pFileName, pDetail, pPrice);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs   !=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (SQLException e) {System.out.println(e.getMessage());}
		}
		return dto;
	}
}
