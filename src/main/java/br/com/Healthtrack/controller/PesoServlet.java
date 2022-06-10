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


@WebServlet("/peso")
public class PesoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private PesoDAO dao;
	

	@Override
	public void init() throws ServletException {
		super.init();
		dao = DAOfactory.getPesoDAO();
		
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
		int id_peso = Integer.parseInt(request.getParameter("id_peso"));
		try {
			dao.remover(id_peso);
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
			double vl_peso = Double.parseDouble(request.getParameter("vl_peso"));
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar dt_peso = Calendar.getInstance();
			dt_peso.setTime(format.parse(request.getParameter("dt_peso")));
			
			Perfil perfil = new Perfil();
			perfil.setId_perfil(0);

			Peso peso = new Peso(0, vl_peso, dt_peso, perfil);
			dao.cadastrar(peso);

			

			request.setAttribute("msg", "Peso cadastrado!");
		} catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao cadastrar");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados");
		}
		request.getRequestDispatcher("cadastrarpesos.jsp").forward(request, response);
	}
	
	private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id_peso = Integer.parseInt(request.getParameter("id_peso"));
			double vl_peso = Double.parseDouble(request.getParameter("vl_peso"));
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar dt_peso = Calendar.getInstance();
			dt_peso.setTime(format.parse(request.getParameter("dt_peso")));
			
			Perfil perfil = new Perfil();
			perfil.setId_perfil(0);

			Peso peso = new Peso(0, vl_peso, dt_peso, perfil);
			dao.atualizar(peso);

			request.setAttribute("msg", "Peso atualizado!");
		} catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide os dados");
		}
		listar(request,response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		
		switch (acao) {
		case "listar":
			listar(request, response);
			break;
		case "abrir-form-edicao":
			int id = Integer.parseInt(request.getParameter("codigo"));
			Peso peso = dao.buscar(id);
			request.setAttribute("peso", peso);
			request.getRequestDispatcher("edicaoPeso.jsp").forward(request, response);
		}	
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Peso> lista = dao.listar();
		request.setAttribute("peso", lista);
		request.getRequestDispatcher("listarPesos.jsp").forward(request, response);
	}
	
	


}