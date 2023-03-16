package org.zerock.myapp.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.zerock.myapp.domain.BoardDTO;
import org.zerock.myapp.domain.PageTO;
import org.zerock.myapp.mapper.BoardMapper;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@NoArgsConstructor
@Log4j2

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MybatisTests {
	
	private SqlSessionFactory factory;
	
	@BeforeAll
	void beforeAll() throws IOException {
		log.trace("beforeAll() invoked.");
		
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		log.info("1. builder: {}", builder);
		
		InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
		log.info("2. is: {}", is);
		
		this.factory = builder.build(is);
		log.info("3. factory: {}", factory);
	} //beforeAll
	
//	@Disabled
	@Test
	@Order(1)
	@DisplayName("테스트1: Mybatis 연결")
	@Timeout(value=1, unit=TimeUnit.SECONDS)
	void mybatisConnect() {
		log.trace("mybatisConnect() invoked.");
		
		@Cleanup
		SqlSession sqlSession = this.factory.openSession();
		
		Connection conn = sqlSession.getConnection();
		
		log.info("4. conn: {}", conn);
	} //mybatisConnect
	
//	@Disabled
	@Test
	@Order(2)
	@DisplayName("테스트2: selectAll 테스트")
	@Timeout(value=4, unit=TimeUnit.SECONDS)
	void selectAll() {
		log.trace("selectAll() invoked.");
		
		@Cleanup
		SqlSession sqlSession = this.factory.openSession();
		
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		
		log.info("mapper: {}", mapper);
		
		List<BoardDTO> list = mapper.selectAll();
		
		list.forEach(log::info);
	} //selectAll
	
//	@Disabled
	@Test
	@Order(3)
	@DisplayName("테스트3: insertBoard 테스트")
	@Timeout(value=4, unit=TimeUnit.SECONDS)
	void insertBoard() {
		log.trace("insertBoard() invoked.");

		@Cleanup
		SqlSession sqlSession = this.factory.openSession();
		
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		
		log.info("mapper: {}", mapper);
		
		int n = mapper.insertBoard("안녕", "hhh", "123456");
		
		log.info("n: {}", n);
		
	} //insertBoard
	
//	@Disabled
	@Test
	@Order(4)
	@DisplayName("테스트4: updateReadCount 테스트")
	@Timeout(value=4, unit=TimeUnit.SECONDS)
	void updateReadCount() {
		log.trace("updateReadCount() invoked.");

		@Cleanup
		SqlSession sqlSession = this.factory.openSession();
		
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		
		log.info("mapper: {}", mapper);
		
		int n = mapper.updateReadCount("15");
		
		log.info("n: {}", n);
		
	} //updateReadCount
	
//	@Disabled
	@Test
	@Order(5)
	@DisplayName("테스트5: selectOne 테스트")
	@Timeout(value=4, unit=TimeUnit.SECONDS)
	void selectOneDTO() {
		log.trace("selectOne() invoked.");

		@Cleanup
		SqlSession sqlSession = this.factory.openSession();
		
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		
		log.info("mapper: {}", mapper);
		
		BoardDTO data = mapper.selectOneDTO("15");
		
		log.info("data: {}", data);
		
	} //selectOne
	
//	@Disabled
	@Test
	@Order(6)
	@DisplayName("테스트6: update 테스트")
	@Timeout(value=4, unit=TimeUnit.SECONDS)
	void update() {
		log.trace("update() invoked.");

		@Cleanup
		SqlSession sqlSession = this.factory.openSession();
		
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		
		log.info("mapper: {}", mapper);
		
		int n = mapper.updateBoard("15", "저자", "hihi", "쓸말고갈..");
		
		log.info("n: {}", n);
		
	} //updateReadCount
	
//	@Disabled
	@Test
	@Order(7)
	@DisplayName("테스트7: delete 테스트")
	@Timeout(value=4, unit=TimeUnit.SECONDS)
	void delete() {
		log.trace("delete() invoked.");

		@Cleanup
		SqlSession sqlSession = this.factory.openSession();
		
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		
		log.info("mapper: {}", mapper);
		
		int n = mapper.deleteBoard("8");
		
		log.info("n: {}", n);
		
	} //delete
	
//	@Disabled
	@Test
	@Order(8)
	@DisplayName("테스트8: delete 테스트")
	@Timeout(value=4, unit=TimeUnit.SECONDS)
	void search() {
		log.trace("search() invoked.");

		@Cleanup
		SqlSession sqlSession = this.factory.openSession();
		
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		
		log.info("mapper: {}", mapper);
		
		List<BoardDTO> list = mapper.searchBoard("title", "완성");
		
		list.forEach(log::info);
		
	} //search
	
//	@Disabled
	@Test
	@Order(9)
	@DisplayName("테스트9: replySelect 테스트")
	@Timeout(value=4, unit=TimeUnit.SECONDS)
	void replySelect() {
		log.trace("replySelect() invoked.");

		@Cleanup
		SqlSession sqlSession = this.factory.openSession();
		
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		
		log.info("mapper: {}", mapper);
		
		BoardDTO data = mapper.replySelect("15");
		
		log.info("data: {}", data);
		
	} //replySelect
	
//	@Disabled
	@Test
	@Order(10)
	@DisplayName("테스트10: updateRepStep 테스트")
	@Timeout(value=4, unit=TimeUnit.SECONDS)
	void updateRepStep() {
		log.trace("updateRepStep() invoked.");

		@Cleanup
		SqlSession sqlSession = this.factory.openSession();
		
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		
		log.info("mapper: {}", mapper);
		
		int n = mapper.updateRepStep("9", "1");
		
		log.info("n: {}", n);
		
	} //updateRepStep
	
//	@Disabled
	@Test
	@Order(10)
	@DisplayName("테스트10: insertReply 테스트")
	@Timeout(value=4, unit=TimeUnit.SECONDS)
	void insertReply() {
		log.trace("insertReply() invoked.");

		@Cleanup
		SqlSession sqlSession = this.factory.openSession();
		
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		
		log.info("mapper: {}", mapper);
		
		int n = mapper.insertReply("9", "답글", "뚱랑이", "무직타이거", "9", "1", "1");
		
		log.info("n: {}", n);
		
	} //insertReply
	
//	@Disabled
	@Test
	@Order(11)
	@DisplayName("테스트11: totalCount 테스트")
	@Timeout(value=4, unit=TimeUnit.SECONDS)
	void totalCount() {
		log.trace("totalCount() invoked.");

		@Cleanup
		SqlSession sqlSession = this.factory.openSession();
		
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		
		log.info("mapper: {}", mapper);
		
		int total = mapper.totalCountPage();
		
		log.info("total", total);
		
	} //totalCount
} //end class 

















