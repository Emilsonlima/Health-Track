package br.com.Healthtrack.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.Healthtrack.bean.Perfil;
import br.com.Healthtrack.bean.Usuario;
import br.com.Healthtrack.dao.UsuarioDAO;
import br.com.Healthtrack.exception.DBException;
import br.com.Healthtrack.singleton.ConnectionManager;

public class OracleUsuarioDAO implements UsuarioDAO{
	
	private Connection conexao;

	@Override
	public void cadastrar(Usuario usuario) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO T_USUARIO (USUARIO_ID,NM_EMAIL,NR_SENHA,T_PERFIL_ID_PERFIL) VALUES (SQ_T_USUARIO.NEXTVAL, ?, ?, (SELECT MAX(ID_PERFIL)FROM T_PERFIL))";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, usuario.getNm_email());
			stmt.setString(2, usuario.getNr_senha());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao cadastradar.");
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void atualizar(Usuario usuario) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_USUARIO SET NM_EMAIL= ?, NR_SENHA = ?, T_PERFIL_ID_PERFIL = ? WHERE USUARIO_ID = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, usuario.getNm_email());
			stmt.setString(2, usuario.getNr_senha());
			stmt.setInt(3, usuario.getPerfil().getId_perfil());
			stmt.setInt(4, usuario.getUsuario_id());

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao atualizar.");
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void remover(int usuario_id) throws DBException {
			PreparedStatement stmt = null;

			try {
				conexao = ConnectionManager.getInstance().getConnection();
				String sql = "DELETE FROM T_USUARIO WHERE USUARIO_ID = ?";
				stmt = conexao.prepareStatement(sql);
				stmt.setInt(1, usuario_id);
				stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DBException("Erro ao remover.");
			} finally {
				try {
					stmt.close();
					conexao.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}

	@Override
	public Usuario buscar(int id) {
		Usuario usuario = new Usuario();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_USUARIO WHERE USUARIO_ID = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()){
				
				int usuario_id = rs.getInt("USUARIO_ID");
				String nm_email = rs.getString("NM_EMAIL");
				String nr_senha = rs.getString("NR_SENHA");
			
				
				Perfil perfil = new Perfil();
				perfil.setId_perfil(rs.getInt("T_PERFIL_ID_PERFIL"));
				usuario.setPerfil(perfil);
				
				
				
				
				usuario = new Usuario (usuario_id,nm_email, nr_senha, perfil);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				rs.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return usuario;
	}

	@Override
	public List<Usuario> listar() {
		List<Usuario> lista = new ArrayList<Usuario>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_USUARIO");
			rs = stmt.executeQuery();

			//Percorre todos os registros encontrados
			while (rs.next()) {
				Usuario usuario = new Usuario();
				int usuario_id = rs.getInt("USUARIO_ID");
				String nm_email = rs.getString("NM_EMAIL");
				String nr_senha = rs.getString("NR_SENHA");
				
				Perfil perfil = new Perfil();
				perfil.setId_perfil(rs.getInt("T_PERFIL_ID_PERFIL"));
				usuario.setPerfil(perfil);
				
				Usuario usuario1 = new Usuario (usuario_id,nm_email, nr_senha, perfil);
				lista.add(usuario1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				rs.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lista;
	}

	@Override
	public boolean validarUsuario(Usuario usuario) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM TB_USUARIO WHERE nm_email = ? AND nr_senha = ?");
			stmt.setString(1, usuario.getNm_email());
			stmt.setString(2, usuario.getNr_senha());
			stmt.setInt(3, usuario.getPerfil().getId_perfil());
			stmt.setInt(4, usuario.getUsuario_id());
			
			rs = stmt.executeQuery();

			if (rs.next()){
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				rs.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

}


