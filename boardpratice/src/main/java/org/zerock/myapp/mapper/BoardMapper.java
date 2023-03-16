package org.zerock.myapp.mapper;

import java.util.List;

import org.zerock.myapp.domain.BoardDTO;
import org.zerock.myapp.domain.PageTO;

public interface BoardMapper {

	//전체 글 조회
	public abstract List<BoardDTO> selectAll();
	
	//글쓰기
	public abstract int insertBoard(String _title, String _author, String _content);
	
	//조회수 1 증가
	public abstract int updateReadCount(String _num);
	
	//글 자세히 보기
	public abstract BoardDTO selectOneDTO(String _num);
	
	//글 수정하기
	public abstract int updateBoard(String _num, String _author, String _title, String _content);
	
	//글 삭제하기
	public abstract int deleteBoard(String _num);
	
	//글 검색하기
	public abstract List<BoardDTO> searchBoard(String _searchName, String _searchValue);
	
	//답변글 입력폼 보기
	public abstract BoardDTO replySelect(String _num);
	
	//답변글의 기존 repStep 1 증가
	public abstract int updateRepStep(String _root, String _step);
	
	//답변달기
	public abstract int insertReply(String _num, String _title, String _author, String _content, String _repRoot, String _repStep, String _repIndent);
	
	//페이징 처리: 전체 레코드 개수 구하기
	public abstract int totalCountPage();
	
	public abstract List<BoardDTO> selectPage(int curPage, int perPage);
} //end interface






