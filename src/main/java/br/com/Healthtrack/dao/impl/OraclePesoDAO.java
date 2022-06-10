package br.com.Healthtrack.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.Healthtrack.bean.Perfil;
import br.com.Healthtrack.bean.Peso;
import br.com.Healthtrack.dao.PesoDAO;
import br.com.Healthtrack.exception.DBException;
import br.com.Healthtrack.singleton.ConnectionManager;

public class OraclePesoDAO implements PesoDAO{
	
	private Connection conexao;

	@Override
	public void cadastrar(Peso peso) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO T_PESO (ID_PESO, VL_PESO, DT_PESO, T_PERFIL_ID_PERFIL) VALUES (SQ_T_PESO.NEXTVAL, ?, ?, (SELECT MAX(ID_PERFIL)FROM T_PERFIL))";
			stmt = conexao.prepareStatement(sql);
			stmt.setDouble(1, peso.getVl_peso());
			java.sql.Date data = new java.sql.Date(peso.getDt_peso().getTimeInMillis());
			stmt.setDate(2, data);
			

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
	public void atualizar(Peso peso) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_PESO SET VL_PESO= ?, DT_PESO = ?, T_PERFIL_ID_PERFIL = ? WHERE ID_PESO = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setDouble(1, peso.getVl_peso());
			java.sql.Date data = new java.sql.Date(peso.getDt_peso().getTimeInMillis());
			stmt.setDate(2, data);
			stmt.setInt(3, peso.getPerfil().getId_perfil());
			stmt.setInt(4, peso.getId_peso());

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
	public void remover(int id_peso) throws DBException {
			PreparedStatement stmt = null;

			try {
				conexao = ConnectionManager.getInstance().getConnection();
				String sql = "DELETE FROM T_PESO WHERE ID_PESO = ?";
				stmt = conexao.prepareStatement(sql);
				stmt.setInt(1, id_peso);
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
	public Peso buscar(int id) {
		Peso peso = new Peso();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_PESO WHERE ID_PESO = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()){
				
				int id_peso = rs.getInt("ID_PESO");
				double vl_peso = rs.getDouble("VL_PESO");
				java.sql.Date data = rs.getDate("DT_PESO");
				Calendar dt_peso = Calendar.getInstance();
				dt_peso.setTimeInMillis(data.getTime());
				
				Perfil perfil = new Perfil();
				perfil.setId_perfil(rs.getInt("T_PERFIL_ID_PERFIL"));
				peso.setPerfil(perfil);
				
				peso = new Peso (id_peso, vl_peso, dt_peso, perfil);
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
		return peso;
	}

	@Override
	public List<Peso> listar() {
		List<Peso> lista = new ArrayList<Peso>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_PESO");
			rs = stmt.executeQuery();

			//Percorre todos os registros encontrados
			while (rs.next()) {
				Peso peso = new Peso();
				
				int id_peso = rs.getInt("ID_PESO");
				double vl_peso = rs.getDouble("VL_PESO");
				java.sql.Date data = rs.getDate("DT_PESO");
				Calendar dt_peso = Calendar.getInstance();
				dt_peso.setTimeInMillis(data.getTime());
				
				Perfil perfil = new Perfil();
				perfil.setId_perfil(rs.getInt("T_PERFIL_ID_PERFIL"));
				peso.setPerfil(perfil);
				
				Peso peso1 = new Peso (id_peso, vl_peso, dt_peso, perfil);
				lista.add(peso1);
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
