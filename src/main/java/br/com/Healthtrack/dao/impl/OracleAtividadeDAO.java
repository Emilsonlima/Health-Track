package br.com.Healthtrack.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.Healthtrack.bean.Atividade;
import br.com.Healthtrack.bean.Perfil;
import br.com.Healthtrack.dao.AtividadeDAO;
import br.com.Healthtrack.exception.DBException;
import br.com.Healthtrack.singleton.ConnectionManager;

public class OracleAtividadeDAO implements AtividadeDAO{
	
	private Connection conexao;

	@Override
	public void cadastrar(Atividade atividade) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO T_ATIVIDADE (ID_ATIVIDADE,NM_ATIVIDADE,NR_CAL,DT_ATIVIDADE,T_PERFIL_ID_PERFIL) VALUES (SQ_T_ATIVIDADE.NEXTVAL, ?, ?, ?, (SELECT MAX(ID_PERFIL)FROM T_PERFIL))";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, atividade.getNm_atividade());
			stmt.setDouble(2, atividade.getNr_cal());
			java.sql.Date data = new java.sql.Date(atividade.getDt_atividade().getTimeInMillis());
			stmt.setDate(3, data);

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
	public void atualizar(Atividade atividade) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_ATIVIDADE SET NM_ATIVIDADE = ?, NR_CAL = ?, DT_ATIVIDADE = ?, T_PERFIL_ID_PERFIL = ? WHERE ID_ATIVIDADE = ?";
			stmt = conexao.prepareStatement(sql);
			
			stmt.setString(1, atividade.getNm_atividade());
			stmt.setDouble(2, atividade.getNr_cal());
			java.sql.Date data = new java.sql.Date(atividade.getDt_atividade().getTimeInMillis());
			stmt.setDate(3, data);
			stmt.setInt(4, atividade.getPerfil().getId_perfil());
			stmt.setInt(5, atividade.getId_atividade());

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
	public void remover(int id_atividade) throws DBException {
			PreparedStatement stmt = null;

			try {
				conexao = ConnectionManager.getInstance().getConnection();
				String sql = "DELETE FROM T_ATIVIDADE WHERE  ID_ATIVIDADE = ?";
				stmt = conexao.prepareStatement(sql);
				stmt.setInt(1, id_atividade);
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
	public Atividade buscar(int id) {
		Atividade atividade = new Atividade();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_ATIVIDADE WHERE ID_ATIVIDADE = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()){
				
				int id_atividade = rs.getInt("ID_ATIVIDADE");
				String nm_atividade = rs.getString("NM_ATIVIDADE");
				double nr_cal = rs.getDouble("NR_CAL");
				java.sql.Date data = rs.getDate("DT_ATIVIDADE");
				Calendar dt_atividade = Calendar.getInstance();
				dt_atividade.setTimeInMillis(data.getTime());
				
				Perfil perfil = new Perfil();
				perfil.setId_perfil(rs.getInt("T_PERFIL_ID_PERFIL"));
				atividade.setPerfil(perfil);
				
				atividade = new Atividade (id_atividade, nm_atividade, nr_cal , dt_atividade, perfil);
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
		return atividade;
	}

	@Override
	public List<Atividade> listar() {
		List<Atividade> lista = new ArrayList<Atividade>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM TB_PRODUTO");
			rs = stmt.executeQuery();

			//Percorre todos os registros encontrados
			while (rs.next()) {
				Atividade a = new Atividade();
				
				int id_atividade = rs.getInt("ID_ATIVIDADE");
				String nm_atividade = rs.getString("NM_ATIVIDADE");
				double nr_cal = rs.getDouble("NR_CAL");
				java.sql.Date data = rs.getDate("DT_ATIVIDADE");
				Calendar dt_atividade = Calendar.getInstance();
				dt_atividade.setTimeInMillis(data.getTime());
				
				Perfil perfil = new Perfil();
				perfil.setId_perfil(rs.getInt("T_PERFIL_ID_PERFIL"));
				a.setPerfil(perfil);
				
				Atividade atividade = new Atividade (id_atividade, nm_atividade, nr_cal , dt_atividade, perfil);
				lista.add(atividade);
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
