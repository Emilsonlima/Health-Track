package br.com.Healthtrack.teste;

import java.util.List;
import br.com.Healthtrack.bean.Perfil;
import br.com.Healthtrack.dao.PerfilDAO;
import br.com.Healthtrack.exception.DBException;
import br.com.Healthtrack.factory.DAOfactory;

public class TestePerfilDAO {

	public static void main(String[] args) {
		PerfilDAO dao = DAOfactory.getPerfilDAO();
		
		//Cadastrar um perfil
				Perfil perfil = new Perfil(0,"teste perfil 2",20,"Feminino",1.60);
				try {
					dao.cadastrar(perfil);
					System.out.println("Perfil cadastrado.");
				} catch (DBException e) {
					e.printStackTrace();
				}

				//Buscar um produto pelo código e atualizar
				perfil = dao.buscar(1);
				perfil.setNm_perfil("TESTE PERFIL3");
				perfil.setNm_sexo("MASCULINO");
				perfil.setNr_idade(29);
				perfil.setVl_altura(1.70);
				try {
					dao.atualizar(perfil);
					System.out.println("Perfil atualizado.");
				} catch (DBException e) {
					e.printStackTrace();
				}
				
				//Listar os produtos
				List<Perfil> lista = dao.listar();
				for (Perfil item : lista) {
					System.out.println(item.getNm_perfil() + " " + item.getNm_sexo() + " " + item.getNr_idade() + " " + item.getVl_altura());
				}
				
				//Remover um produto
				try {
					dao.remover(3);
					System.out.println("Perfil removido.");
				} catch (DBException e) {
					e.printStackTrace();
				}	
			}	

	

}
