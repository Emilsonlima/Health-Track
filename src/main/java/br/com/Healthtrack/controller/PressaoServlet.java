package br.com.Healthtrack.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

/**
 * Servlet implementation class PressaoServlet
 */
@WebServlet("/press")
public class PressaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Press_artDAO dao;

	@Override
	public void init() throws ServletException {
		super.init();
		dao = DAOfactory.getPress_artDAO();

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
		case "excluir":
			excluir(request, response);
			break;
		}

	}
	
	private void excluir(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id_press = Integer.parseInt(request.getParameter("id_press"));
		try {
			dao.remover(id_press);
			request.setAttribute("msg", "Produto removido!");
		} catch (DBException e) {
			e.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar");
		}
		listar(request,response);
	}

	private void cadastrar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			int vl_press_sis = Integer.parseInt(request.getParameter("vl_press_sis"));
			int vl_press_dis = Integer.parseInt(request.getParameter("vl_press_dis"));
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar dt_press = Calendar.getInstance();
			dt_press.setTime(format.parse(request.getParameter("dt_press")));

			Perfil perfil = new Perfil();
			perfil.setId_perfil(0);

			Press_art press = new Press_art(0, vl_press_sis, vl_press_dis, dt_press, perfil);
			dao.cadastrar(press);

			request.setAttribute("msg", "Pressão Arterial cadastrada!");
		} catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao cadastrar");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados");
		}
		request.getRequestDispatcher("cadastrarPressao.jsp").forward(request, response);
	}

	private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id_press = Integer.parseInt(request.getParameter("id_press"));
			int vl_press_sis = Integer.parseInt(request.getParameter("vl_press_sis"));
			int vl_press_dis = Integer.parseInt(request.getParameter("vl_press_dis"));

			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar dt_press = Calendar.getInstance();
			dt_press.setTime(format.parse(request.getParameter("dt_press")));

			Perfil perfil = new Perfil();
			perfil.setId_perfil(0);

			Press_art press = new Press_art(0, vl_press_sis, vl_press_dis, dt_press, perfil);
			dao.atualizar(press);

			request.setAttribute("msg", "Alimento atualizado!");
		} catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados");
		}
		listar(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");
		switch (acao) {
		case "listar":
			listar(request, response);
			break;
		case "abrir-form-edicao":
			int id = Integer.parseInt(request.getParameter("id_press"));
			Press_art press = dao.buscar(id);
			request.setAttribute("press", press);
			request.getRequestDispatcher("edicaoPress.jsp").forward(request, response);
		}
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Press_art> lista = dao.listar();
		request.setAttribute("press", lista);
		request.getRequestDispatcher("listarPressao.jsp").forward(request, response);
	}

}