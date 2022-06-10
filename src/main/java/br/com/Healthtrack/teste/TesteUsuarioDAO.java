package br.com.Healthtrack.teste;

import java.util.List;

import br.com.Healthtrack.bean.Perfil;
import br.com.Healthtrack.bean.Usuario;
import br.com.Healthtrack.dao.UsuarioDAO;
import br.com.Healthtrack.exception.DBException;
import br.com.Healthtrack.factory.*;

public class TesteUsuarioDAO {

	public static void main(String[] args) {
		UsuarioDAO dao = DAOfactory.getUsuarioDAO();
		
		//Cadastrar l
		
		Perfil perfil = new Perfil();
		perfil.setId_perfil(0);
		
		Usuario usuario = new Usuario ();
		usuario.setUsuario_id(0);
		usuario.setNm_email("Teste email2");
		usuario.setNr_senha("091092");
		usuario.setPerfil(perfil);
		
		try {
			dao.cadastrar(usuario);
			System.out.println("Usuario cadastrado.");
		} catch (DBException e) {
			e.printStackTrace();
		}

		//Buscar um Usuario pelo código e atualizar
		usuario = dao.buscar(1);
		usuario.setNm_email("teste3@fiap.com");
		usuario.setNr_senha("091092");
		
		try {
			dao.atualizar(usuario);
			System.out.println("Perfil atualizado.");
		} catch (DBException e) {
			e.printStackTrace();
		}
		
		//Listar os produtos
		List<Usuario> lista = dao.listar();
		for (Usuario item : lista) {
			System.out.println(item.getNm_email() + " " + item.getNr_senha());
		}
		
		//Remover um produto
		try {
			dao.remover(2);
			System.out.println("Usuario removido.");
		} catch (DBException e) {
			e.printStackTrace();
		}	
	}	


	}


