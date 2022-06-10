package br.com.Healthtrack.bean;

import java.util.Calendar;

public class Press_art {
	private int id_press;
	private int vl_press_sis;
	private int vl_press_dis;
	private Calendar dt_press;
	private Perfil perfil;
	
	public Press_art() {
		super();
		
	}

	public Press_art(int id_press, int vl_press_sis, int vl_press_dis, Calendar dt_press, Perfil perfil) {
		super();
		this.id_press = id_press;
		this.vl_press_sis = vl_press_sis;
		this.vl_press_dis = vl_press_dis;
		this.dt_press = dt_press;
		this.perfil = perfil;
	}

	public int getId_press() {
		return id_press;
	}

	public void setId_press(int id_press) {
		this.id_press = id_press;
	}

	public int getVl_press_sis() {
		return vl_press_sis;
	}

	public void setVl_press_sis(int vl_press_sis) {
		this.vl_press_sis = vl_press_sis;
	}

	public int getVl_press_dis() {
		return vl_press_dis;
	}

	public void setVl_press_dis(int vl_press_dis) {
		this.vl_press_dis = vl_press_dis;
	}

	public Calendar getDt_press() {
		return dt_press;
	}

	public void setDt_press(Calendar dt_press) {
		this.dt_press = dt_press;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	
	
	

}
