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

import com.tj.ex.dto.Qna_boardDto;

public class Qna_boardDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private static Qna_boardDao instance = new Qna_boardDao();

	public static Qna_boardDao getInstance() {
		return instance;
	}

	private Qna_boardDao() {
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

	// 글목록(startRow~endRow)
	public ArrayList<Qna_boardDto> qnaList(int startRow, int endRow) {
		ArrayList<Qna_boardDto> dtos = new ArrayList<Qna_boardDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM(SELECT BNO, (SELECT M.MID FROM QNA_BOARD Q, MEMBER M WHERE Q.MID=M.MID AND QNA_BOARD.BNO=BNO) MID," + 
				"				        (SELECT A.AID FROM QNA_BOARD Q, ADMIN A WHERE Q.AID=A.AID AND QNA_BOARD.BNO=BNO) AID," + 
				"				           BHEAD, BCONTENT, BRDATE, BHIT, BGROUP, BSTEP, BINDENT, BIP FROM QNA_BOARD ORDER BY BGROUP DESC, BSTEP) A)" + 
				"				    WHERE RN BETWEEN ? AND ?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int bNo = rs.getInt("bNo");
				String bHead = rs.getString("bHead");
				String mId = rs.getString("mId");
				String aId = rs.getString("aId");
				String bContent = rs.getString("bContent");
				Date bRdate = rs.getDate("bRdate");
				int bHit = rs.getInt("bHit");
				int bGroup = rs.getInt("bGroup");
				int bStep = rs.getInt("bStep");
				int bIndent = rs.getInt("bIndent");
				String bIp = rs.getString("bIp");
				dtos.add(new Qna_boardDto(bNo, mId, aId, null, bHead, bContent, bRdate, bHit, bGroup, bStep, bIndent, bIp));
			}
		} catch (Exception e) {
			System.out.println("리스트 예외 : "+e.getMessage());
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

	// 글개수
	public int getQnaTotCnt() {
		int totCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM QNA_BOARD";
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

	// 글쓰기(원글쓰기)
	public int qnaWrite(String mId, String bHead, String bContent, String bIp) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO QNA_BOARD(BNO, MID, BHEAD, BCONTENT, BGROUP, BSTEP, BINDENT, BIP)"
				+ "				VALUES (QNA_BOARD_SEQ.NEXTVAL, ?, ?, ?, QNA_BOARD_SEQ.CURRVAL, 0, 0, ?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setString(2, bHead);
			pstmt.setString(3, bContent);
			pstmt.setString(4, bIp);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "원글쓰기성공" : "원글쓰기실패");
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

	// hit수 올리기
	public void qnaHitUp(int bNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE QNA_BOARD SET bHIT = bHIT+1 WHERE bNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bNo);
			pstmt.executeUpdate();
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
	}

	// 글상세보기(hit수 올리기 호출)
	public Qna_boardDto qnaContentView(int bNo) {
		qnaHitUp(bNo); // 글 상세보기시 hitUp
		Qna_boardDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT Q.*, MNAME FROM QNA_BOARD Q, MEMBER M WHERE M.MID=Q.MID AND BNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String mId = rs.getString("mId");
				String aId = rs.getString("aId");
				String bHead = rs.getString("bHead");
				String bContent = rs.getString("bContent");
				Date bRdate = rs.getDate("bRdate");
				int bHit = rs.getInt("bHit");
				int bGroup = rs.getInt("bGroup");
				int bStep = rs.getInt("bStep");
				int bIndent = rs.getInt("bIndent");
				String bIp = rs.getString("bIp");
				dto = new Qna_boardDto(bNo, mId, aId, null, bHead, bContent, bRdate, bHit, bGroup, bStep, bIndent, bIp);
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

	// 글 수정하기
	public int QnaModify(int bNo, String bHead, String bContent, String bIp) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE QNA_BOARD SET BHEAD = ?," + 
				"                     	   BCONTENT = ?," + 
				"                     	   BIP = ?" + 
				"               	 WHERE BNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bHead);
			pstmt.setString(2, bContent);
			pstmt.setString(3, bIp);
			pstmt.setInt(4, bNo);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "글수정성공" : "글수정실패");
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
	public int qnaDelete(int bNo) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM QNA_BOARD WHERE BNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bNo);
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

	// bNo로 dto 가져오기
	public Qna_boardDto QnaModifyView_replyView(int bNo) {
		Qna_boardDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT Q.*, MNAME FROM QNA_BOARD Q, MEMBER M WHERE M.MID=Q.MID AND BNO=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String mId = rs.getString("mId");
				String bHead = rs.getString("bHead");
				String bContent = rs.getString("bContent");
				Date bRdate = rs.getDate("bRdate");
				int bHit = rs.getInt("bHit");
				int bGroup = rs.getInt("bGroup");
				int bStep = rs.getInt("bStep");
				int bIndent = rs.getInt("bIndent");
				String bIp = rs.getString("bIp");
				dto = new Qna_boardDto(bNo, mId, null, null, bHead, bContent, bRdate, bHit, bGroup, bStep, bIndent, bIp);
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

	// 답변글 추가전 STEP a 수행
	public void preReplyStepA(int bGroup, int bStep) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE QNA_BOARD SET bSTEP = bSTEP+1 WHERE bGROUP=? AND bSTEP>?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bGroup);
			pstmt.setInt(2, bStep);
			pstmt.executeUpdate();
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
	}

	// 답변글 쓰기
	public int qnaReply(String mId, String aId, String bHead, String bContent, int bGroup, int bStep, int bIndent, String bIp) {
		preReplyStepA(bGroup, bStep);
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO QNA_BOARD (bNO, mID, aID, bHEAD, bCONTENT, bGROUP, bSTEP, bINDENT, bIP)" + 
				"    VALUES (QNA_BOARD_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?,?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setString(2, aId);
			pstmt.setString(3, bHead);
			pstmt.setString(4, bContent);
			pstmt.setInt(5, bGroup);
			pstmt.setInt(6, bStep + 1);
			pstmt.setInt(7, bIndent + 1);
			pstmt.setString(8, bIp);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "답변쓰기성공" : "답변쓰기실패");
		} catch (SQLException e) {
			System.out.println("qnaReply"+e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
}