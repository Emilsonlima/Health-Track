package br.com.Healthtrack.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.Healthtrack.bean.Perfil;
import br.com.Healthtrack.bean.Press_art;
import br.com.Healthtrack.dao.Press_artDAO;
import br.com.Healthtrack.exception.DBException;
import br.com.Healthtrack.singleton.ConnectionManager;

public class OraclePress_artDAO implements Press_artDAO{
	
	private Connection conexao;

	@Override
	public void cadastrar(Press_art press_art) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO T_PRESS_ART (ID_PRESS,VL_PRESS_SIS,VL_PRESS_DIS,DT_PRESS,T_PERFIL_ID_PERFIL) VALUES (SQ_T_PRESS_ART.NEXTVAL, ?, ?, ?, (SELECT MAX(ID_PERFIL)FROM T_PERFIL))";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, press_art.getVl_press_sis());
			stmt.setInt(2, press_art.getVl_press_dis());
			java.sql.Date data = new java.sql.Date(press_art.getDt_press().getTimeInMillis());
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
	public void atualizar(Press_art press_art) throws DBException {
		PreparedStatement stmt = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_PRESS_ART SET VL_PRESS_SIS = ?, VL_PRESS_DIS = ?, DT_PRESS = ?, T_PERFIL_ID_PERFIL = ? WHERE ID_PRESS = ?";
			stmt = conexao.prepareStatement(sql);
			
			stmt.setInt(1, press_art.getVl_press_sis());
			stmt.setInt(2, press_art.getVl_press_dis());
			java.sql.Date data = new java.sql.Date(press_art.getDt_press().getTimeInMillis());
			stmt.setDate(3, data);
			stmt.setInt(4, press_art.getPerfil().getId_perfil());
			stmt.setInt(5, press_art.getId_press());

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
	public void remover(int id_press) throws DBException {
			PreparedStatement stmt = null;

			try {
				conexao = ConnectionManager.getInstance().getConnection();
				String sql = "DELETE FROM T_PRESS_ART WHERE  ID_PRESS = ?";
				stmt = conexao.prepareStatement(sql);
				stmt.setInt(1, id_press);
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
	public Press_art buscar(int id) {
		Press_art press_art = new Press_art();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_PRESS_ART WHERE ID_PRESS = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()){
				
				int id_press = rs.getInt("ID_PRESS");
				int vl_press_sis = rs.getInt("VL_PRESS_SIS");
				int vl_press_dis = rs.getInt("VL_PRESS_DIS");
				java.sql.Date data = rs.getDate("DT_PRESS");
				Calendar dt_press = Calendar.getInstance();
				dt_press.setTimeInMillis(data.getTime());
				
				Perfil perfil = new Perfil();
				perfil.setId_perfil(rs.getInt("T_PERFIL_ID_PERFIL"));
				press_art.setPerfil(perfil);
				
				press_art = new Press_art (id_press,vl_press_sis,vl_press_dis,dt_press,perfil);
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
		return press_art;
	}

	@Override
	public List<Press_art> listar() {
		List<Press_art> lista = new ArrayList<Press_art>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM TB_PRODUTO");
			rs = stmt.executeQuery();

			//Percorre todos os registros encontrados
			while (rs.next()) {
				Press_art p = new Press_art();
				
				int id_press = rs.getInt("ID_PRESS");
				int vl_press_sis = rs.getInt("VL_PRESS_SIS");
				int vl_press_dis = rs.getInt("VL_PRESS_DIS");
				java.sql.Date data = rs.getDate("DT_PRESS");
				Calendar dt_press = Calendar.getInstance();
				dt_press.setTimeInMillis(data.getTime());
				
				Perfil perfil = new Perfil();
				perfil.setId_perfil(rs.getInt("T_PERFIL_ID_PERFIL"));
				p.setPerfil(perfil);
				
				Press_art press_art = new Press_art (id_press,vl_press_sis,vl_press_dis,dt_press,perfil);
				lista.add(press_art);
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
