package br.com.Healthtrack.dao;

import java.util.List;

import br.com.Healthtrack.bean.Alimento;
import br.com.Healthtrack.exception.DBException;

public interface AlimentoDAO {
	void cadastrar(Alimento alimento) throws DBException;
	void atualizar(Alimento alimento) throws DBException;
	void remover(int id_ref) throws DBException;
	 Alimento buscar(int id);
	List<Alimento> listar();

}
