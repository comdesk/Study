package org.zerock.myapp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.myapp.service.BoardCommand;
import org.zerock.myapp.service.BoardDeleteCommand;
import org.zerock.myapp.service.BoardPageCommand;
import org.zerock.myapp.service.BoardReplyCommand;
import org.zerock.myapp.service.BoardReplyUICommand;
import org.zerock.myapp.service.BoardRetrieveCommand;
import org.zerock.myapp.service.BoardSearchCommand;
import org.zerock.myapp.service.BoardUpdateCommand;
import org.zerock.myapp.service.BoardWriteCommand;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2

@WebServlet(name = "BoardFrontController", urlPatterns = { "*.do" })
public class BoardFrontControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		log.trace("service(req, res) invoked.");
		
		try {
			String requestURI = req.getRequestURI();
			String comm = requestURI.substring(requestURI.lastIndexOf("/"));
			
			BoardCommand command = null;
			String target = null;
			
			/*if(comm.equals("/list.do")) {
				command = new BoardListCommand();
				command.execute(req, res);
				target = "/list.jsp";
			}//if */
			
			if(comm.equals("/list.do")) {
				command = new BoardPageCommand();
				command.execute(req, res);
				target = "listPage.jsp";
			} //if(페이징처리)
			
			if(comm.equals("/writeui.do")) {
				target = "/write.jsp";
			} //if
			
			if(comm.equals("/write.do")) {
				command = new BoardWriteCommand();
				command.execute(req, res);
				target = "/list.do";
			} //if			
			
			if(comm.equals("/retrieve.do")) {
				command = new BoardRetrieveCommand();
				command.execute(req, res);
				target = "/retrieve.jsp";
			} //if
			
			if(comm.equals("/update.do")) {
				command = new BoardUpdateCommand();
				command.execute(req, res);
				target = "/list.do";
			} //if
			
			if(comm.equals("/delete.do")) {
				command = new BoardDeleteCommand();
				command.execute(req, res);
				target = "/list.do";
			} //if
			
			if(comm.equals("/search.do")) {
				command = new BoardSearchCommand();
				command.execute(req, res);
				target = "/list.jsp";
			} //if
			
			if(comm.equals("/replyui.do")) {
				command = new BoardReplyUICommand();
				command.execute(req, res);
				target = ("/reply.jsp");
			} //if
			
			if(comm.equals("/reply.do")) {
				command = new BoardReplyCommand();
				command.execute(req, res);
				target = ("/list.do");
			} //if
			
			if(target.matches(".*\\.jsp")){
				log.trace("RequestDispatcher");
				RequestDispatcher dis = req.getRequestDispatcher(target);
				dis.forward(req, res);
			} else {
				log.trace("Send Redirection");
				res.sendRedirect("/board"+target);
			} //if-else			
		} catch(Exception e) {
			throw new ServletException(e);
		} //try-catch		
	} //service

} //end class






