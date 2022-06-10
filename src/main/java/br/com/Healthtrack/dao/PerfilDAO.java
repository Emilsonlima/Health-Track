package br.com.Healthtrack.dao;

import java.util.List;
import br.com.Healthtrack.bean.Perfil;
import br.com.Healthtrack.exception.DBException;

public interface PerfilDAO {
	
	void cadastrar(Perfil perfil) throws DBException;
	void atualizar(Perfil perfil) throws DBException;
	void remover(int id_perfil) throws DBException;
	Perfil buscar(int id);
	List<Perfil> listar();

}
