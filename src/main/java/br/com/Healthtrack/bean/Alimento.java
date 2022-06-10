package br.com.Healthtrack.bean;

import java.util.Calendar;

public class Alimento {
	private int id_ref;
	private String tp_ref;
	private Calendar dt_ref;
	private double nr_cal;
	private Perfil perfil;
	
	public Alimento() {
		super();
		
	}

	public Alimento(int id_ref, String tp_ref, Calendar dt_ref, double nr_cal, Perfil perfil) {
		super();
		this.id_ref = id_ref;
		this.tp_ref = tp_ref;
		this.dt_ref = dt_ref;
		this.nr_cal = nr_cal;
		this.perfil = perfil;
	}

	public int getId_ref() {
		return id_ref;
	}

	public void setId_ref(int id_ref) {
		this.id_ref = id_ref;
	}

	public String getTp_ref() {
		return tp_ref;
	}

	public void setTp_ref(String tp_ref) {
		this.tp_ref = tp_ref;
	}

	public Calendar getDt_ref() {
		return dt_ref;
	}

	public void setDt_ref(Calendar dt_ref) {
		this.dt_ref = dt_ref;
	}

	public double getNr_cal() {
		return nr_cal;
	}

	public void setNr_cal(double nr_cal) {
		this.nr_cal = nr_cal;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	

}
