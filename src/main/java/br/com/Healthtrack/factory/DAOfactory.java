package br.com.Healthtrack.factory;

import br.com.Healthtrack.dao.*;
import br.com.Healthtrack.dao.impl.*;

public class DAOfactory {
	
	public static PerfilDAO getPerfilDAO() {
		return new OraclePerfilDAO();
	}
	
	public static UsuarioDAO getUsuarioDAO() {
		return new OracleUsuarioDAO();
	}
	
	public static PesoDAO getPesoDAO() {
		return new OraclePesoDAO();
	}
	
	public static AtividadeDAO getAtividadeDAO() {
		return new OracleAtividadeDAO();
	}
	
	public static AlimentoDAO getAlimentoDAO() {
		return new OracleAlimentoDAO();
	}
	
	public static Press_artDAO getPress_artDAO() {
		return new OraclePress_artDAO();
	}



}
