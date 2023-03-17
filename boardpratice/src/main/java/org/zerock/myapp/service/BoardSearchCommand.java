package org.zerock.myapp.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.myapp.domain.BoardDTO;
import org.zerock.myapp.exception.BusinessException;
import org.zerock.myapp.persistence.BoardDAO;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2
public class BoardSearchCommand implements BoardCommand {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) 
			throws BusinessException {
		
		log.trace("execute({}, {}) invoked.", req, res);
		
		try {
			String searchName = req.getParameter("searchName");
			String searchValue = req.getParameter("searchValue");
			
			BoardDAO dao = new BoardDAO();
			List<BoardDTO> list = dao.search(searchName, searchValue);
			
			req.setAttribute("list", list);
		} catch(Exception e) {
			throw new BusinessException(e);
		} //try-catch
	} //execute
} //end class
