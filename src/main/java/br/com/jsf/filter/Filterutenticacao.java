package br.com.jsf.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.jsf.jpautil.JPAUtil;

@WebFilter(urlPatterns= {"/*"})
public class Filterutenticacao implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Invocando Filter");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		String usuarioLogado = (String) session.getAttribute("usuarioLogado"); //variavel de sessao que será atribuido ao login
		String url = req.getServletPath();
		if(!url.equalsIgnoreCase("login.xhtml")  && usuarioLogado == null 
				|| (usuarioLogado!= null && usuarioLogado.trim().isEmpty())) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.xhtml");
			dispatcher.forward(request, response);
			return;
		}else {
			chain.doFilter(request, response);			
		}
		
		
	}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException{
		JPAUtil.getEntityManager();
	}

	

}
