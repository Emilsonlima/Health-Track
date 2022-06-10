package br.com.Healthtrack.controller;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.Healthtrack.bean.*;
import br.com.Healthtrack.dao.*;
import br.com.Healthtrack.exception.DBException;
import br.com.Healthtrack.factory.DAOfactory;

@WebServlet("/perfil")
public class PerfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PerfilDAO dao;
	private UsuarioDAO dao1;

	@Override
	public void init() throws ServletException {
		super.init();
		dao = DAOfactory.getPerfilDAO();
		dao1 = DAOfactory.getUsuarioDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

String acao = request.getParameter("acao");
		
		switch (acao) {
		case "cadastrar":
			cadastrar(request, response);
			break;
		case "editar":
			editar(request, response);
			break;
		}
		}

	private void cadastrar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String nm_perfil = request.getParameter("nm_perfil");
			int nr_idade = Integer.parseInt(request.getParameter("nr_idade"));
			String nm_sexo = request.getParameter("nm_sexo");
			double vl_altura = Double.parseDouble(request.getParameter("vl_altura"));

			String nm_email = request.getParameter("nm_email");
			String nr_senha = request.getParameter("nr_senha");

			Perfil perfil = new Perfil(0, nm_perfil, nr_idade, nm_sexo, vl_altura);
			dao.cadastrar(perfil);

			Perfil perfil1 = new Perfil();
			perfil1.setId_perfil(0);

			Usuario usuario = new Usuario();
			usuario.setUsuario_id(0);
			usuario.setNm_email(nm_email);
			usuario.setNr_senha(nr_senha);

			usuario.setPerfil(perfil1);
			dao1.cadastrar(usuario);

			request.setAttribute("msg", "Perfil cadastrado!");
		} catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao cadastrar");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados");
		}
		request.getRequestDispatcher("perfil.jsp").forward(request, response);
	}
	
	private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String nm_perfil = request.getParameter("nm_perfil");
			int nr_idade = Integer.parseInt(request.getParameter("nr_idade"));
			String nm_sexo = request.getParameter("nm_sexo");
			double vl_altura = Double.parseDouble(request.getParameter("vl_altura"));
			
			String nm_email = request.getParameter("nm_email");
			String nr_senha = request.getParameter("nr_senha");
			
			Perfil perfil = new Perfil(0, nm_perfil, nr_idade, nm_sexo, vl_altura);
			dao.cadastrar(perfil);

			Perfil perfil1 = new Perfil();
			perfil1.setId_perfil(0);

			Usuario usuario = new Usuario();
			usuario.setUsuario_id(0);
			usuario.setNm_email(nm_email);
			usuario.setNr_senha(nr_senha);

			usuario.setPerfil(perfil1);
			dao1.cadastrar(usuario);

			request.setAttribute("msg", "Peso atualizado!");
		} catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados");
		}
		request.getRequestDispatcher("perfil.jsp").forward(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
		
		switch(acao) {
		
		case "listar":
			
		List<Perfil> lista = dao.listar();
		request.setAttribute("perfil", lista);
		request.getRequestDispatcher("perfil.jsp").forward(request, response);
		
		List<Usuario> lista1 = dao1.listar();
		request.setAttribute("usuario", lista1);
		request.getRequestDispatcher("perfil.jsp").forward(request, response);	
		break;
		
		case "abrir-form-edicao":

			int id = Integer.parseInt(request.getParameter("id_perfil"));
			Perfil perfil = dao.buscar(id);
			request.setAttribute("perfil",perfil);
			request.getRequestDispatcher("perfil.jsp").forward(request, response);
			
			int id2 = Integer.parseInt(request.getParameter("usuario_id"));
			Usuario usuario = dao1.buscar(id2);
			request.setAttribute("usuario",usuario);
			request.getRequestDispatcher("perfil.jsp").forward(request, response);
	}

	}
}
