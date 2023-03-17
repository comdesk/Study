package org.zerock.myapp.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.myapp.domain.BoardDTO;
import org.zerock.myapp.exception.BusinessException;
import org.zerock.myapp.persistence.BoardDAO;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor
public class BoardListCommand implements BoardCommand {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) 
			throws BusinessException {
		
		log.trace("execute({}, {}) invoked.", req, res);
		
		try {
			BoardDAO dao = new BoardDAO();
			
			List<BoardDTO> list = dao.list();
			
			req.setAttribute("list", list);
		} catch(Exception e) {
			throw new BusinessException(e);
		}		
		
	} //end class
	
	
} //end class
