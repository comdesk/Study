package org.zerock.myapp.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.zerock.myapp.domain.BoardDTO;
import org.zerock.myapp.domain.PageTO;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class BoardDAOFirst {
	
	private DataSource dataSource;
	
	public BoardDAOFirst() throws NamingException {
		
		log.trace("Default Constructor invoked.");
		
			String prefix = "java:comp/env/";
			String name = "jdbc/OracleCloudATPWithDriverSpy";
			
			Context ctx = new InitialContext();
			Object obj = ctx.lookup(prefix + name);
			
			Objects.nonNull(obj);
			
			this.dataSource = (DataSource) obj;
	} //default constructor
	
	//목록 보기
	public List<BoardDTO> list() throws SQLException {
		
		log.trace("list() invoked.");
		
		List<BoardDTO> list = new ArrayList<>();
		
		final String sql = "SELECT num, author, title, content, to_char(writeday, 'YYYY/MM/DD') writeday, readcnt, repRoot, repStep, repIndent FROM board order by repRoot desc, repStep asc";
		
		@Cleanup Connection conn = this.dataSource.getConnection();
		
		@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
		
		@Cleanup ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			int num = rs.getInt("num");
			String author = rs.getString("author");
			String title = rs.getString("title");
			String content = rs.getString("content");
			String writeday = rs.getString("writeday");
			int readcnt = rs.getInt("readcnt");
			int repRoot = rs.getInt("repRoot");
			int repStep = rs.getInt("repStep");
			int repIndent = rs.getInt("repIndent");
			
			BoardDTO data = new BoardDTO(num, author, title, content, writeday, readcnt, repRoot, repStep, repIndent);
			
			list.add(data);
		}
		
		return list;
	} //list
	
	//글쓰기
	public void write(String _title, String _author, String _content) throws SQLException {
		
		log.trace("write(_title, _author, _content) invoked.");
		
		final String sql = "INSERT INTO board (num, title, author, content, repRoot, repStep, repIndent) values (board_seq.NEXTVAL, ?, ?, ?, board_seq.CURRVAL, 0, 0)";
		
		@Cleanup Connection conn = this.dataSource.getConnection();
		
		@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, _title);
		pstmt.setString(2, _author);
		pstmt.setString(3, _content);
		
		int n = pstmt.executeUpdate();
	} //write
	
	//조회수 1 증가
	public void readCount(String _num) throws SQLException {
		
		log.trace("readCount(_num) invoked.");
		
		final String sql = "UPDATE board SET readcnt = (readcnt + 1) WHERE num = ?";
		
		@Cleanup Connection conn = this.dataSource.getConnection();

		@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, Integer.parseInt(_num));
		int n = pstmt.executeUpdate();		
		
	} //readCount
	
	//글 자세히 보기
	public BoardDTO retrieve(String _num) throws SQLException {
		
		log.trace("retrieve(_num) invoked.");
		
		readCount(_num);
		
		String sql = "Select * FROM board WHERE num = ?";
		
		@Cleanup Connection conn = this.dataSource.getConnection();
		
		@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, Integer.parseInt(_num));
		
		@Cleanup ResultSet rs = pstmt.executeQuery();
		
		BoardDTO data = new BoardDTO();
		
		if(rs.next()) {
			int num = rs.getInt("num");
			String author = rs.getString("author");
			String title = rs.getString("title");
			String content = rs.getString("content");
			String writeday = rs.getString("writeday");
			int readcnt = rs.getInt("readcnt");
			
			data.setNum(num);
			data.setAuthor(author);
			data.setTitle(title);
			data.setContent(content);
			data.setWriteday(writeday);
			data.setReadcnt(readcnt);
		} //if
		
		return data;
	} //retrieve
	
	//글 수정하기
	public void update(String _num, String _author, String _title, String _content) throws SQLException {
		
		log.trace("update(_num, _author, _title, _content) invoked.");
		
		final String sql = "UPDATE board SET author = ?, title = ?, content = ? WHERE num = ?";
		
		@Cleanup Connection conn = this.dataSource.getConnection();
		
		@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, _author);
		pstmt.setString(2, _title);
		pstmt.setString(3, _content);
		pstmt.setInt(4, Integer.parseInt(_num));
		
		int n = pstmt.executeUpdate();		
	} //update
	
	//글 삭제하기
	public void delete(String _num) throws SQLException {
		
		log.trace("delete(_num) invoked.");
		
		final String sql = "DELETE FROM board WHERE num = ?";
		
		@Cleanup Connection conn = this.dataSource.getConnection();
		
		@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, Integer.parseInt(_num));
		int n = pstmt.executeUpdate();
	} //delete
	
	//글 검색하기
	public List<BoardDTO> search(String _searchName, String _searchValue) throws SQLException {
		
		log.trace("search(_searchName, _searchValue) invoked.");
		
		String sql = "SELECT num, author, title, content, to_char(writeday, 'YYYY/MM/DD') writeday, readcnt FROM board";
		
		if(_searchName.equals("title")) {
			sql += " WHERE title LIKE ?";
		} else {
			sql += " WHERE author LIKE ?";
		}
		
		@Cleanup Connection conn = this.dataSource.getConnection();
		
		@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, "%" + _searchValue + "%");
		
		@Cleanup ResultSet rs = pstmt.executeQuery();
		
		List<BoardDTO> list = new ArrayList<>();
		
		while(rs.next()) {
			int num = rs.getInt("num");
			String author = rs.getString("author");
			String title = rs.getString("title");
			String content = rs.getString("content");
			String writeday = rs.getString("writeday");
			int readcnt = rs.getInt("readcnt");
			
			BoardDTO data = new BoardDTO();
			
			data.setNum(num);
			data.setAuthor(author);
			data.setTitle(title);
			data.setContent(content);
			data.setWriteday(writeday);
			data.setReadcnt(readcnt);
			
			list.add(data);
		} //while
		
		return list;
	} //search
	
	//답변글 입력폼 보기
	public BoardDTO replyui(String _num) throws SQLException {
		
		log.trace("replyui(_num) invoked.");
		
		String sql = "SELECT * FROM board WHERE num = ?";
		
		@Cleanup Connection conn = this.dataSource.getConnection();
		
		@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, Integer.parseInt(_num));
		
		@Cleanup ResultSet rs = pstmt.executeQuery();
		
		BoardDTO data = new BoardDTO();
		
		if(rs.next()) {
			data.setNum(rs.getInt("num"));
			data.setAuthor(rs.getString("author"));
			data.setTitle(rs.getString("title"));
			data.setContent(rs.getString("content"));
			data.setWriteday(rs.getString("writeday"));
			data.setReadcnt(rs.getInt("readcnt"));
			data.setRepRoot(rs.getInt("repRoot"));
			data.setRepStep(rs.getInt("repStep"));
			data.setRepIndent(rs.getInt("repIndent"));			
		} //if
		
		return data;
	} //replyui
	
	//답변글의 기존 repStep 1 증가
	public void makeReply(String _root, String _step) throws SQLException {
		
		log.info("makeReply(_root, _step) invoked.");
		
		String sql = "UPDATE board SET repStep = repStep + 1 WHERE repRoot = ? AND repStep > ?";
		
		@Cleanup Connection conn = this.dataSource.getConnection();
		
		@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, Integer.parseInt(_root));
		pstmt.setInt(2, Integer.parseInt(_step));
		
		int n = pstmt.executeUpdate();
	} //makeReply
	
	//답변달기
	public void reply(String _num, String _title, String _author, String _content, String _repRoot, String _repStep, String _repIndent) throws SQLException {
		
		log.trace("reply(_num, _title, _author,_content, _repRoot, _repStep, _repIndent) invoked.");
		
		//repStep + 1
		this.makeReply(_repRoot, _repStep);
		
		String sql = "INSERT INTO board(num, title, author, content, repRoot, repStep, repIndent) values (board_seq.NEXTVAL, ?, ?, ?, ?, ?, ?)";
		
		@Cleanup Connection conn = this.dataSource.getConnection();
		
		@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, _title);
		pstmt.setString(2, _author);
		pstmt.setString(3, _content);
		pstmt.setInt(4, Integer.parseInt(_repRoot));
		pstmt.setInt(5, Integer.parseInt(_repStep) + 1);
		pstmt.setInt(6, Integer.parseInt(_repIndent) + 1);
		
		int n = pstmt.executeUpdate();		
	} //reply
	
	//페이징 처리: 전체 레코드 개수 구하기
	public int totalCount() throws SQLException {
		
		log.trace("totalCount() invoked.");
		
		int count = 0;
		
		String sql = "SELECT count(num) FROM board";
		
		@Cleanup Connection conn = this.dataSource.getConnection();
		
		@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
		
		@Cleanup ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()) {
			count = rs.getInt(1);
		} //if
		
		return count;
	} //totalCount
	
	//페이지 구현 (한 페이지 구현인듯..)
	public PageTO page(int curPage) throws SQLException {
		
		log.trace("page(curPage) invoked.");
		
		PageTO to = new PageTO();
		int totalCount = totalCount();
		
		List<BoardDTO> list = new ArrayList<>();
		
		String sql = "SELECT num, author, title, content, to_char(writeday, 'YYYY/MM/DD') writeday, readcnt, repRoot, repStep, repIndent FROM board order by repRoot desc, repStep asc";
		
		@Cleanup Connection conn = this.dataSource.getConnection();
		
		@Cleanup PreparedStatement pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		@Cleanup ResultSet rs = pstmt.executeQuery();
		
		int perPage = to.getPerPage();
		
		int skip = (curPage - 1) * perPage;
		if(skip > 0) {
			rs.absolute(skip);
			log.trace("rs 커서 위치: {}", rs.getRow());
		} //if
		
		for(int i = 0; i < perPage && rs.next(); i++) {
			log.trace("rs.next() 커서 위치: {}", rs.getRow());
			
			int num = rs.getInt("num");
			String author = rs.getString("author");
			String title = rs.getString("title");
			String content = rs.getString("content");
			String writeday = rs.getString("writeday");
			int readcnt = rs.getInt("readcnt");
			int repRoot = rs.getInt("repRoot");
			int repStep = rs.getInt("repStep");
			int repIndent = rs.getInt("repIndent");
			
			BoardDTO data = new BoardDTO(num, author, title, content, writeday, readcnt, repRoot, repStep, repIndent);
			list.add(data);
		} //for
		
		to.setList(list);
		to.setCurPage(curPage);
		to.setTotalCount(totalCount);
		
		return to;
	} //page
} //end class


 
















