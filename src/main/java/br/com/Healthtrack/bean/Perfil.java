package br.com.Healthtrack.bean;

public class Perfil {
	private int id_perfil;
	private String nm_perfil;
	private int nr_idade;
	private String nm_sexo;
	private double vl_altura;
	
	public Perfil() {
		super();
		
	}

	public Perfil(int id_perfil, String nm_perfil, int nr_idade, String nm_sexo, double vl_altura) {
		super();
		this.id_perfil = id_perfil;
		this.nm_perfil = nm_perfil;
		this.nr_idade = nr_idade;
		this.nm_sexo = nm_sexo;
		this.vl_altura = vl_altura;
	}

	public int getId_perfil() {
		return id_perfil;
	}

	public void setId_perfil(int id_perfil) {
		this.id_perfil = id_perfil;
	}

	public String getNm_perfil() {
		return nm_perfil;
	}

	public void setNm_perfil(String nm_perfil) {
		this.nm_perfil = nm_perfil;
	}

	public int getNr_idade() {
		return nr_idade;
	}

	public void setNr_idade(int nr_idade) {
		this.nr_idade = nr_idade;
	}

	public String getNm_sexo() {
		return nm_sexo;
	}

	public void setNm_sexo(String nm_sexo) {
		this.nm_sexo = nm_sexo;
	}

	public double getVl_altura() {
		return vl_altura;
	}

	public void setVl_altura(double vl_altura) {
		this.vl_altura = vl_altura;
	}
	
	

}
