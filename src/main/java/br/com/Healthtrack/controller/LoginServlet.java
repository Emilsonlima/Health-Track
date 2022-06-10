package br.com.Healthtrack.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.Healthtrack.bean.*;
import br.com.Healthtrack.bo.*;
import br.com.Healthtrack.dao.*;
import br.com.Healthtrack.exception.EmailException;
import br.com.Healthtrack.factory.DAOfactory;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UsuarioDAO dao;
	private PerfilDAO dao1;
	private EmailBO bo;
  
	public LoginServlet() {
        dao = DAOfactory.getUsuarioDAO();
        dao1 = DAOfactory.getPerfilDAO();
        bo = new EmailBO();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nm_email = request.getParameter("nm_email");
		String nr_senha = request.getParameter("nr_senha");
		
		Perfil perfil = new Perfil();
		perfil.setId_perfil(0);
		
		Usuario usuario = new Usuario(0,nm_email, nr_senha,perfil);
		
		if (dao.validarUsuario(usuario)) {
			HttpSession session = request.getSession();
			session.setAttribute("user", nm_email);
			String mensagem = "Um Login Foi Realizado";
			try {
				bo.enviarEmail(nm_email, "Login Realizado", mensagem);
			} catch (EmailException e) {
				e.printStackTrace();
			}
		}else {
			request.setAttribute("erro", "Usuário e/ou senha inválidos");
		}
		request.getRequestDispatcher("perfil.jsp").forward(request, response);
	}

}