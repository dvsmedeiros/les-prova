package com.dvsmedeiros.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dvsmedeiros.core.controller.PessoaDAO;
import com.dvsmedeiros.domain.Pessoa;

@SuppressWarnings("serial")
public class ServletController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Pessoa> result = new PessoaDAO().cosultar(null);
		
		req.setAttribute("pessoas", result);
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
;	
	}
}
