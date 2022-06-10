package br.com.Healthtrack.bean;

import br.com.Healthtrack.util.CriptografiaUtils;

public class Usuario {
	private int usuario_id;
	private String nm_email;
	private String nr_senha;
	private Perfil perfil;
	
	public Usuario() {
		super();
		
	}

	public Usuario(int usuario_id, String nm_email, String nr_senha, Perfil perfil) {
		super();
		this.usuario_id = usuario_id;
		this.nm_email = nm_email;
		setNr_senha ( nr_senha);
		this.perfil = perfil;
	}

	public int getUsuario_id() {
		return usuario_id;
	}

	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}

	public String getNm_email() {
		return nm_email;
	}

	public void setNm_email(String nm_email) {
		this.nm_email = nm_email;
	}

	public String getNr_senha() {
		return nr_senha;
	}

	public void setNr_senha(String nr_senha) {
		try {
			this.nr_senha = CriptografiaUtils.criptografar(nr_senha);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	
	

}
