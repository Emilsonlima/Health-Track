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
 * Servlet implementation class AlimentoServlet
 */
@WebServlet("/alimento")
public class AlimentoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AlimentoDAO dao;

	@Override
	public void init() throws ServletException {
		super.init();
		dao = DAOfactory.getAlimentoDAO();

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
		int id_ref = Integer.parseInt(request.getParameter("id_ref"));
		try {
			dao.remover(id_ref);
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
			String tp_ref = request.getParameter("tp_ref");
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar dt_ref = Calendar.getInstance();
			dt_ref.setTime(format.parse(request.getParameter("dt_ref")));
			double nr_cal = Double.parseDouble(request.getParameter("nr_cal"));

			Perfil perfil = new Perfil();
			perfil.setId_perfil(0);

			Alimento alimento = new Alimento(0, tp_ref, dt_ref, nr_cal, perfil);
			dao.cadastrar(alimento);

			request.setAttribute("msg", "Refeição cadastrada!");
		} catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao cadastrar");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados");
		}
		request.getRequestDispatcher("cadastrarAlimentos.jsp").forward(request, response);
	}

	private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id_ref = Integer.parseInt(request.getParameter("id_ref"));
			String tp_ref = request.getParameter("tp_ref");
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar dt_ref = Calendar.getInstance();
			dt_ref.setTime(format.parse(request.getParameter("dt_ref")));
			double nr_cal = Double.parseDouble(request.getParameter("nr_cal"));

			Perfil perfil = new Perfil();
			perfil.setId_perfil(0);

			Alimento alimento = new Alimento(0, tp_ref, dt_ref, nr_cal, perfil);
			dao.atualizar(alimento);

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
			int id = Integer.parseInt(request.getParameter("id_ref"));
			Alimento alimento = dao.buscar(id);
			request.setAttribute("alimento", alimento);
			request.getRequestDispatcher("edicaoAlimento.jsp").forward(request, response);
		}
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Alimento> lista = dao.listar();
		request.setAttribute("alimento", lista);
		request.getRequestDispatcher("listarAlimento.jsp").forward(request, response);
	}

}