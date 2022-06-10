package br.com.Healthtrack.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.Healthtrack.bean.Perfil;
import br.com.Healthtrack.dao.PerfilDAO;
import br.com.Healthtrack.exception.DBException;
import br.com.Healthtrack.singleton.ConnectionManager;

public class OraclePerfilDAO implements PerfilDAO{
	
	private Connection conexao;

	@Override
	public void cadastrar(Perfil perfil) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO T_PERFIL (ID_PERFIL,NM_PERFIL,NR_IDADE,NM_SEXO,VL_ALTURA) VALUES (SQ_T_PERFIL.NEXTVAL, ?, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, perfil.getNm_perfil());
			stmt.setInt(2, perfil.getNr_idade());
			stmt.setString(3, perfil.getNm_sexo());
			stmt.setDouble(4, perfil.getVl_altura());
			

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
	public void atualizar(Perfil perfil) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_PERFIL SET NM_PERFIL = ?, NR_IDADE = ?, NM_SEXO = ?, VL_ALTURA = ? WHERE ID_PERFIL = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, perfil.getNm_perfil());
			stmt.setInt(2, perfil.getNr_idade());
			stmt.setString(3, perfil.getNm_sexo());
			stmt.setDouble(4, perfil.getVl_altura());
			stmt.setInt(5, perfil.getId_perfil());

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
	public void remover(int id_perfil) throws DBException {
			PreparedStatement stmt = null;

			try {
				conexao = ConnectionManager.getInstance().getConnection();
				String sql = "DELETE FROM T_PERFIL WHERE ID_PERFIL= ?";
				stmt = conexao.prepareStatement(sql);
				stmt.setInt(1, id_perfil);
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
	public Perfil buscar(int id) {
		Perfil perfil = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_PERFIL WHERE ID_PERFIL = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()){
				int id_perfil = rs.getInt("ID_PERFIL");
				String nm_perfil = rs.getString("NM_PERFIL");
				int nr_idade = rs.getInt("NR_IDADE");
				String nm_sexo = rs.getString("NM_SEXO");
				double vl_altura = rs.getInt("VL_ALTURA");
				
				
				perfil = new Perfil (id_perfil,nm_perfil,nr_idade,nm_sexo,vl_altura);
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
		return perfil;
	}

	@Override
	public List<Perfil> listar() {
		List<Perfil> lista = new ArrayList<Perfil>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_PERFIL");
			rs = stmt.executeQuery();

			
			while (rs.next()) {
				int id_perfil = rs.getInt("ID_PERFIL");
				String nm_perfil = rs.getString("NM_PERFIL");
				int nr_idade = rs.getInt("NR_IDADE");
				String nm_sexo = rs.getString("NM_SEXO");
				double vl_altura = rs.getInt("VL_ALTURA");
				
				Perfil perfil = new Perfil(id_perfil,nm_perfil,nr_idade,nm_sexo,vl_altura);
				lista.add(perfil);
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

}
