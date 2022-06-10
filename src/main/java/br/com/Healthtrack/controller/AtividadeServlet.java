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

@WebServlet("/atividade")
public class AtividadeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AtividadeDAO dao;

	@Override
	public void init() throws ServletException {
		super.init();
		dao = DAOfactory.getAtividadeDAO();

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
		int id_atividade = Integer.parseInt(request.getParameter("id_atividade"));
		try {
			dao.remover(id_atividade);
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
			String nm_atividade = request.getParameter("nm_atividade");
			double nr_calorias = Double.parseDouble(request.getParameter("nr_calorias"));
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar dt_atividade = Calendar.getInstance();
			dt_atividade.setTime(format.parse(request.getParameter("dt_atividade")));

			Perfil perfil = new Perfil();
			perfil.setId_perfil(0);

			Atividade atividade = new Atividade(0, nm_atividade, nr_calorias, dt_atividade, perfil);
			dao.cadastrar(atividade);

			request.setAttribute("msg", "Atividade cadastrada!");
		} catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao cadastrar");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados");
		}
		request.getRequestDispatcher("cadastrarAtividades.jsp").forward(request, response);
	}

	private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id_atividade = Integer.parseInt(request.getParameter("id_atividade"));
			String nm_atividade = request.getParameter("nm_atividade");
			double nr_cal = Double.parseDouble(request.getParameter("nr_cal"));
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar dt_atividade = Calendar.getInstance();
			dt_atividade.setTime(format.parse(request.getParameter("dt_atividade")));

			Perfil perfil = new Perfil();
			perfil.setId_perfil(0);

			Atividade atividade = new Atividade(0, nm_atividade, nr_cal, dt_atividade, perfil);
			dao.atualizar(atividade);

			request.setAttribute("msg", "Atividade atualizada!");
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
			int id = Integer.parseInt(request.getParameter("id_atividade"));
			Atividade atividade = dao.buscar(id);
			request.setAttribute("atividade", atividade);
			request.getRequestDispatcher("edicaoAtividade.jsp").forward(request, response);
		}

	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Atividade> lista = dao.listar();
		request.setAttribute("atividade", lista);
		request.getRequestDispatcher("listarAtividades.jsp").forward(request, response);
	}

}