package br.com.Healthtrack.bean;

import java.util.Calendar;

public class Peso {
	private int id_peso;
	private double vl_peso;
	private Calendar dt_peso;
	private Perfil perfil;
	
	
	public Peso() {
		super();
		
	}


	public Peso(int id_peso, double vl_peso, Calendar dt_peso, Perfil perfil) {
		super();
		this.id_peso = id_peso;
		this.vl_peso = vl_peso;
		this.dt_peso = dt_peso;
		this.perfil = perfil;
	}


	public int getId_peso() {
		return id_peso;
	}


	public void setId_peso(int id_peso) {
		this.id_peso = id_peso;
	}


	public double getVl_peso() {
		return vl_peso;
	}


	public void setVl_peso(double vl_peso) {
		this.vl_peso = vl_peso;
	}


	public Calendar getDt_peso() {
		return dt_peso;
	}


	public void setDt_peso(Calendar dt_peso) {
		this.dt_peso = dt_peso;
	}


	public Perfil getPerfil() {
		return perfil;
	}


	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	
	

}
