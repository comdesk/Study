package org.zerock.myapp.domain;

import java.util.List;

import lombok.Data;

@Data
public class PageTO {	//=DTO
	
	List<BoardDTO> list;
	int curPage;
	int perPage = 10;
	int totalCount;
} //end class
