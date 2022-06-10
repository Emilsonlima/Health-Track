package br.com.Healthtrack.dao;

import java.util.List;

import br.com.Healthtrack.bean.Atividade;
import br.com.Healthtrack.exception.DBException;

public interface AtividadeDAO {
	void cadastrar(Atividade atividade) throws DBException;
	void atualizar(Atividade atividade) throws DBException;
	void remover(int id_atividade) throws DBException;
	 Atividade buscar(int id);
	List<Atividade> listar();

}
