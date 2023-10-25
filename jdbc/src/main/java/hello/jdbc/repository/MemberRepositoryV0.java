package hello.jdbc.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.NoSuchElementException;

import hello.jdbc.connection.DBConnectionUtil;
import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;


/**
 * JDBC - DriverManager 사용
 */
@Slf4j
public class MemberRepositoryV0 {

	public Member save(Member member) throws SQLException {
		String sql = "insert into member(member_id, money) value (? , ?)";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		try {
			//커넥션을 먼저 획득한다.
			con = DBConnectionUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId());
			pstmt.setInt(2, member.getMoney());
			pstmt.executeUpdate();
			return member;
			
		} catch (SQLException e) {
			log.info("db error");
			throw e;
		} finally {
			close(con, pstmt, null);
			
		}
		
	}
	
	
	public Member findById(String memberId) throws Exception {
		
		String sql = "select * from member where member_id = ?";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DBConnectionUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberId);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				Member member = new Member();
				member.setMemberId(rs.getString("member_id"));
				member.setMoney(rs.getInt("money"));
				return member;
			} else {
				throw new NoSuchElementException("memebr not found memberId = " + memberId);
			}
			
		} catch (Exception e) {
			log.info("db error", e);
			throw e;
		} finally {
			close(con, pstmt, rs);
		}
		
		
		
	}
	
	
	public void update (String memberId, int money) throws SQLException {
		
		String sql = "update member set money = ? where member_id = ?";
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			//커넥션을 먼저 획득한다.
			con = DBConnectionUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, money);
			pstmt.setString(2, memberId);
			int resultSize = pstmt.executeUpdate();
			log.info("resultSize = {}", resultSize);
			
		} catch (SQLException e) {
			log.info("db error");
			throw e;
		} finally {
			close(con, pstmt, null);
			
		}
		
	}
	
	
	public void delete (String memberId) throws SQLException {
		
		String sql = "delete from member where member_id = ?";
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			//커넥션을 먼저 획득한다.
			con = DBConnectionUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			log.info("db error = {}", e);
			throw e;
		} finally {
			close(con, pstmt, null);
			
		}
		
	}
	
	
	
	private void close(Connection con, Statement stmt, ResultSet rs) {
		// 메모리 close는 사용한 역순으로 닫아주면 된다.
		if ( rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				log.info("error", e);
			}
			
		}
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				log.info("error", e);
			}
		}
		
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				log.info("error", e);
			}
		}
	}
	
	
}
