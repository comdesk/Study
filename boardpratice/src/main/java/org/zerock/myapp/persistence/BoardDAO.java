package org.zerock.myapp.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.zerock.myapp.domain.BoardDTO;
import org.zerock.myapp.domain.PageTO;
import org.zerock.myapp.mapper.BoardMapper;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class BoardDAO {
	
	private SqlSessionFactory factory;
	
	public BoardDAO() throws NamingException, IOException {
		
		log.trace("Default Constructor invoked.");
		
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		
		InputStream is = Resources.getResourceAsStream("mybatis-context.xml");
		
		this.factory = builder.build(is);		
	} //default constructor
	
	//목록 보기
	public List<BoardDTO> list() throws SQLException {
		
		log.trace("list() invoked.");
		
		@Cleanup
		SqlSession sqlSession = factory.openSession();
		
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		List<BoardDTO> list = mapper.selectAll();
		
		return list;
	} //list
	
	//글쓰기
	public void write(String _title, String _author, String _content) throws SQLException {
		
		log.trace("write(_title, _author, _content) invoked.");
		
		@Cleanup
		SqlSession sqlSession = factory.openSession();
		
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		int n = mapper.insertBoard(_title, _author, _content);
	} //write
	
	//조회수 1 증가
	public void readCount(String _num) throws SQLException {
		
		log.trace("readCount(_num) invoked.");
		
		@Cleanup
		SqlSession sqlSession = factory.openSession();
		
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		int n = mapper.updateReadCount(_num);
	} //readCount
	
	//글 자세히 보기
	public BoardDTO retrieve(String _num) throws SQLException {
		
		log.trace("retrieve(_num) invoked.");
		
		readCount(_num);
		
		@Cleanup
		SqlSession sqlSession = factory.openSession();
		
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		BoardDTO data = mapper.selectOneDTO(_num);
		
		return data;
	} //retrieve
	
	//글 수정하기
	public void update(String _num, String _author, String _title, String _content) throws SQLException {
		
		log.trace("update(_num, _author, _title, _content) invoked.");
		
		@Cleanup
		SqlSession sqlSession = factory.openSession();
		
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		int n = mapper.updateBoard(_num, _author, _title, _content);
	} //update
	
	//글 삭제하기
	public void delete(String _num) throws SQLException {
		
		log.trace("delete(_num) invoked.");
		
		@Cleanup
		SqlSession sqlSession = factory.openSession();
		
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		int n = mapper.deleteBoard(_num);
	} //delete
	
	//글 검색하기
	public List<BoardDTO> search(String _searchName, String _searchValue) throws SQLException {
		
		log.trace("search(_searchName, _searchValue) invoked.");
		
		@Cleanup
		SqlSession sqlSession = factory.openSession();
		
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		List<BoardDTO> list = mapper.searchBoard(_searchName, _searchValue);
		
		return list;
	} //search
	
	//답변글 입력폼 보기
	public BoardDTO replyui(String _num) throws SQLException {
		
		log.trace("replyui(_num) invoked.");
		
		@Cleanup
		SqlSession sqlSession = factory.openSession();
		
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		BoardDTO data = mapper.replySelect(_num);
		
		String sql = "SELECT * FROM board WHERE num = ?";
		
		return data;
	} //replyui
	
	//답변글의 기존 repStep 1 증가
	public void makeReply(String _root, String _step) throws SQLException {
		
		log.info("makeReply(_root, _step) invoked.");
		
		@Cleanup
		SqlSession sqlSession = factory.openSession();
		
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		int n = mapper.updateRepStep(_root, _step);
	} //makeReply
	
	//답변달기
	public void reply(String _num, String _title, String _author, String _content, String _repRoot, String _repStep, String _repIndent) throws SQLException {
		
		log.trace("reply(_num, _title, _author,_content, _repRoot, _repStep, _repIndent) invoked.");
		
		//repStep + 1
		this.makeReply(_repRoot, _repStep);
		
		@Cleanup
		SqlSession sqlSession = factory.openSession();
		
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		int n = mapper.insertReply(_num, _title, _author, _content, _repRoot, _repStep, _repIndent);	
	} //reply
	
	//페이징 처리: 전체 레코드 개수 구하기
	public int totalCount() throws SQLException {
		
		log.trace("totalCount() invoked.");
		
		int count = 0;
		
		@Cleanup
		SqlSession sqlSession = factory.openSession();
		
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		count = mapper.totalCountPage();

		return count;
	} //totalCount
	
	//페이지 구현 (한 페이지 구현인듯..)
	public PageTO page(int curPage) throws SQLException {
		
		log.trace("page(curPage) invoked.");
		
		PageTO to = new PageTO();
		int totalCount = totalCount();
				
		int perPage = to.getPerPage();
		
		@Cleanup
		SqlSession sqlSession = factory.openSession();
		
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		List<BoardDTO> list = mapper.selectPage(curPage, perPage);
		
		to.setList(list);
		to.setCurPage(curPage);
		to.setTotalCount(totalCount);
		
		return to;
	} //page
} //end class


 
















