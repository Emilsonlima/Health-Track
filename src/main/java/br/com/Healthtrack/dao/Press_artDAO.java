package br.com.Healthtrack.dao;

import java.util.List;

import br.com.Healthtrack.bean.Press_art;
import br.com.Healthtrack.exception.DBException;

public interface Press_artDAO {
	void cadastrar(Press_art press) throws DBException;
	void atualizar(Press_art press) throws DBException;
	void remover(int id_press) throws DBException;
	 Press_art buscar(int id);
	List<Press_art> listar();

}
