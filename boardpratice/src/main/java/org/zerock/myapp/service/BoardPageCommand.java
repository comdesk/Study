package org.zerock.myapp.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.myapp.domain.PageTO;
import org.zerock.myapp.exception.BusinessException;
import org.zerock.myapp.persistence.BoardDAO;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2
public class BoardPageCommand implements BoardCommand {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) 
			throws BusinessException {
		
		log.trace("execute({}, {}) invoked.", req, res);

		try {
			int curPage = 1; //기본 페이지
			
			if(req.getParameter("curPage") != null) {
				curPage = Integer.parseInt(req.getParameter("curPage"));
			} //if			
			
			BoardDAO dao = new BoardDAO();
			
			PageTO list = dao.page(curPage);
			
			// listPage.jsp에서 목록 리스트 데이터 저장
			req.setAttribute("list", list.getList());

			// page.jsp에서 페이징 처리 데이터 저장
			req.setAttribute("page", list);
		} catch(Exception e) {
			throw new BusinessException(e);
		} //try-catch
		
	} //execute

} //end class
