package br.com.Healthtrack.dao;

import java.util.List;

import br.com.Healthtrack.bean.Peso;
import br.com.Healthtrack.exception.DBException;

public interface PesoDAO {
	void cadastrar(Peso peso) throws DBException;
	void atualizar(Peso peso) throws DBException;
	void remover(int id_peso) throws DBException;
	Peso buscar(int id);
	List<Peso> listar();

}
