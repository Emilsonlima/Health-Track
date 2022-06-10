package br.com.Healthtrack.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.Healthtrack.bean.Alimento;
import br.com.Healthtrack.bean.Perfil;
import br.com.Healthtrack.dao.AlimentoDAO;
import br.com.Healthtrack.exception.DBException;
import br.com.Healthtrack.singleton.ConnectionManager;

public class OracleAlimentoDAO implements AlimentoDAO {

	private Connection conexao;

	@Override
	public void cadastrar(Alimento alimento) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO T_ALIMENTO (ID_REF,TP_REF, DT_REF,NR_CAL,T_PERFIL_ID_PERFIL) VALUES (SQ_T_ALIMENTO.NEXTVAL, ?, ?, ?, (SELECT MAX(ID_PERFIL)FROM T_PERFIL))";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, alimento.getTp_ref());
			java.sql.Date data = new java.sql.Date(alimento.getDt_ref().getTimeInMillis());
			stmt.setDate(2, data);
			stmt.setDouble(3, alimento.getNr_cal());

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
	public void atualizar(Alimento alimento) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_ALIMENTO SET TP_REF = ?, DT_REF = ?, NR_CAL = ?, T_PERFIL_ID_PERFIL = ? WHERE ID_REF = ?";
			stmt = conexao.prepareStatement(sql);

			stmt.setString(1, alimento.getTp_ref());
			java.sql.Date data = new java.sql.Date(alimento.getDt_ref().getTimeInMillis());
			stmt.setDate(2, data);
			stmt.setDouble(3, alimento.getNr_cal());
			stmt.setInt(4, alimento.getPerfil().getId_perfil());
			stmt.setInt(5, alimento.getId_ref());

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
	public void remover(int id_ref) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "DELETE FROM T_ALIMENTO WHERE  ID_REF = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, id_ref);
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
	public Alimento buscar(int id) {
		Alimento alimento = new Alimento();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_ATIVIDADE WHERE ID_ATIVIDADE = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {

				int id_ref = rs.getInt("ID_REF");
				String tp_ref = rs.getString("TP_REF");
				java.sql.Date data = rs.getDate("DT_REF");
				Calendar dt_ref = Calendar.getInstance();
				dt_ref.setTimeInMillis(data.getTime());
				double nr_cal = rs.getDouble("NR_CAL");

				Perfil perfil = new Perfil();
				perfil.setId_perfil(rs.getInt("T_PERFIL_ID_PERFIL"));
				alimento.setPerfil(perfil);

				alimento = new Alimento(id_ref, tp_ref, dt_ref, nr_cal, perfil);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				rs.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return alimento;
	}

	@Override
	public List<Alimento> listar() {
		List<Alimento> lista = new ArrayList<Alimento>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM TB_PRODUTO");
			rs = stmt.executeQuery();

			// Percorre todos os registros encontrados
			while (rs.next()) {
				Alimento a = new Alimento();

				int id_ref = rs.getInt("ID_REF");
				String tp_ref = rs.getString("TP_REF");
				java.sql.Date data = rs.getDate("DT_REF");
				Calendar dt_ref = Calendar.getInstance();
				dt_ref.setTimeInMillis(data.getTime());
				double nr_cal = rs.getDouble("NR_CAL");

				Perfil perfil = new Perfil();
				perfil.setId_perfil(rs.getInt("T_PERFIL_ID_PERFIL"));
				a.setPerfil(perfil);

				Alimento alimento = new Alimento(id_ref, tp_ref, dt_ref, nr_cal, perfil);
				lista.add(alimento);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
