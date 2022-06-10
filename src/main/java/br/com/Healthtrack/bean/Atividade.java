package br.com.Healthtrack.bean;

import java.util.Calendar;

public class Atividade {
	private int id_atividade;
	private String nm_atividade;
	private double nr_cal;
	private Calendar dt_atividade;
	private Perfil perfil;
	
	public Atividade() {
		super();
		
	}

	public Atividade(int id_atividade, String nm_atividade, double nr_cal, Calendar dt_atividade, Perfil perfil) {
		super();
		this.id_atividade = id_atividade;
		this.nm_atividade = nm_atividade;
		this.nr_cal = nr_cal;
		this.dt_atividade = dt_atividade;
		this.perfil = perfil;
	}

	public int getId_atividade() {
		return id_atividade;
	}

	public void setId_atividade(int id_atividade) {
		this.id_atividade = id_atividade;
	}

	public String getNm_atividade() {
		return nm_atividade;
	}

	public void setNm_atividade(String nm_atividade) {
		this.nm_atividade = nm_atividade;
	}

	public double getNr_cal() {
		return nr_cal;
	}

	public void setNr_cal(double nr_cal) {
		this.nr_cal = nr_cal;
	}

	public Calendar getDt_atividade() {
		return dt_atividade;
	}

	public void setDt_atividade(Calendar dt_atividade) {
		this.dt_atividade = dt_atividade;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	
	

}
