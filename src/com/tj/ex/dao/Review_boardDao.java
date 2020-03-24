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

import com.tj.ex.dto.Review_boardDto;

public class Review_boardDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private static Review_boardDao instance = new Review_boardDao();
	public static Review_boardDao getInstance() {
		return instance;
	}
	private Review_boardDao() {}
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
	// 글목록(startRow~endRow)
	public ArrayList<Review_boardDto> ReviewList(int startRow, int endRow){
		ArrayList<Review_boardDto> dtos = new ArrayList<Review_boardDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM(SELECT R.*, MNAME FROM REVIEW_BOARD R, MEMBER M WHERE M.MID=R.MID ORDER BY ENO DESC) A)" + 
				"    WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int eNo = rs.getInt("eNo");
				String mId = rs.getString("mId");
				String mName = rs.getString("mName"); //join해서 출력
				String eHead = rs.getString("eHead");
				String eContent = rs.getString("eContent");
				Date eRdate = rs.getDate("eRdate");
				String eIp = rs.getString("eIp");
				dtos.add(new Review_boardDto(eNo, mId, mName, eHead, eContent, eRdate, eIp));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs   != null) rs.close();
				if(pstmt!= null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return dtos;
	}
	// 글개수
	public int getReviewTotCnt() {
		int totCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM REVIEW_BOARD";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				totCnt = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs   != null) rs.close();
				if(pstmt!= null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return totCnt;
	}
	// 글쓰기(원글쓰기)
		public int reviewWrite(String mId, String eHead, String eContent, String eIp) {
			int result = FAIL;
			Connection        conn  = null;
			PreparedStatement pstmt = null;
			String sql = "INSERT INTO REVIEW_BOARD(ENO, MID, EHEAD, ECONTENT, EIP)" + 
					"				VALUES (REVIEW_BOARD_SEQ.NEXTVAL, ?, ?, ?, ?)";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, mId);
				pstmt.setString(2, eHead);
				pstmt.setString(3, eContent);
				pstmt.setString(4, eIp);
				result = pstmt.executeUpdate();
				System.out.println(result==SUCCESS? "원글쓰기성공":"원글쓰기실패");
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}finally {
				try {
					if(pstmt!=null) pstmt.close();
					if(conn !=null) conn.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
			return result;
		}
		// 글상세보기
		public Review_boardDto reviewContent(int eNo) {
			Review_boardDto dto = null;
			Connection        conn  = null;
			PreparedStatement pstmt = null;
			ResultSet         rs    = null;
			String sql = "SELECT F.*, MNAME FROM REVIEW_BOARD F, MEMBER M WHERE M.MID=F.MID AND ENO=?";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, eNo);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					String mId = rs.getString("mId");
					String mName = rs.getString("mName"); //join해서 출력
					String eHead = rs.getString("eHead");
					String eContent = rs.getString("eContent");
					Date eRdate = rs.getDate("eRdate");
					String eIp = rs.getString("eIp");
					dto = new Review_boardDto(eNo, mId, mName, eHead, eContent, eRdate, eIp);
			}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}finally {
				try {
					if(rs   != null) rs.close();
					if(pstmt!= null) pstmt.close();
					if(conn != null) conn.close();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			return dto;
		}
		// 글 수정하기
		public int reviewModify(int eNo, String eHead, String eContent) {
			int result = FAIL;
			Connection        conn  = null;
			PreparedStatement pstmt = null;
			String sql = "UPDATE REVIEW_BOARD SET EHEAD = ?," + 
					"                        	  ECONTENT = ?" + 
					"                  		WHERE ENO=?";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, eHead);
				pstmt.setString(2, eContent);
				pstmt.setInt(3, eNo);
				result = pstmt.executeUpdate();
				System.out.println(result==SUCCESS? "글수정성공":"글수정실패");
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}finally {
				try {
					if(pstmt!=null) pstmt.close();
					if(conn !=null) conn.close();
				} catch (SQLException e) {System.out.println(e.getMessage());}
			}
			return result;
		}
		// 글 삭제하기
		public int reviewDelete(int eNo) {
			int result = 0;
			Connection        conn  = null;
			PreparedStatement pstmt = null;
			String sql = "DELETE FROM REVIEW_BOARD WHERE ENO=?";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, eNo);
				result = pstmt.executeUpdate();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}finally {
				try {
					if(pstmt!= null) pstmt.close();
					if(conn != null) conn.close();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			return result;
		}
		// 수정페이지 가기
		public Review_boardDto reviewModifyView(int eNo) {
			Review_boardDto dto = null;
			Connection        conn  = null;
			PreparedStatement pstmt = null;
			ResultSet         rs    = null;
			String sql = "SELECT F.*, MNAME FROM REVIEW_BOARD F, MEMBER M WHERE M.MID=F.MID AND eNo=?";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, eNo);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					String mId = rs.getString("mId");
					String mName = rs.getString("mName");
					String eHead = rs.getString("eHead");
					String eContent = rs.getString("eContent");
					Date eRdate = rs.getDate("eRdate");
					String eIp = rs.getString("eIp");
					dto = new Review_boardDto(eNo, mId, mName, eHead, eContent, eRdate, eIp);
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







