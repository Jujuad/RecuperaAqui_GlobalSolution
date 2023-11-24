package br.com.fiap.gs.model;

import java.sql.Timestamp;

public class Usuario {
    private int idUsuario;
    private String nomeUsuario;
    private String emailUsuario;
    private String senhaUsuario;
    private Timestamp dtNascUsuario;

    public Usuario(int idUsuario, String nomeUsuario, String emailUsuario, String senhaUsuario, Timestamp dtNascUsuario) {
        this.idUsuario = idUsuario;
        this.nomeUsuario = nomeUsuario;
        this.emailUsuario = emailUsuario;
        this.senhaUsuario = senhaUsuario;
        this.dtNascUsuario = dtNascUsuario;
    }

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public String getSenhaUsuario() {
		return senhaUsuario;
	}

	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}

	public Timestamp getDtNascUsuario() {
		return dtNascUsuario;
	}

	public void setDtNascUsuario(Timestamp dtNascUsuario) {
		this.dtNascUsuario = dtNascUsuario;
	}

	public int calcularEscalaEmocional() {
		// TODO Auto-generated method stub
		return 0;
	}

    
}