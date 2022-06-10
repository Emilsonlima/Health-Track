package br.com.Healthtrack.dao;

import java.util.List;

import br.com.Healthtrack.bean.Usuario;
import br.com.Healthtrack.exception.DBException;

public interface UsuarioDAO {
	
	void cadastrar(Usuario usuario) throws DBException;
	void atualizar(Usuario usuario) throws DBException;
	void remover(int usuario_id) throws DBException;
	boolean validarUsuario(Usuario usuario);
	Usuario buscar(int id);
	List<Usuario> listar();

}
