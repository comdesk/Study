package org.zerock.myapp.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.myapp.exception.BusinessException;
import org.zerock.myapp.persistence.BoardDAO;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2
public class BoardDeleteCommand implements BoardCommand {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) 
			throws BusinessException {

		log.trace("execute({}, {}) invoked.", req, res);
		
		try {
			String num = req.getParameter("num");
			
			BoardDAO dao = new BoardDAO();
			dao.delete(num);
		} catch(Exception e) {
			throw new BusinessException(e);
		} //try-catch
	} //execute

} //end class
