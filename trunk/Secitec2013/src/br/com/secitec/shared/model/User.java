package br.com.secitec.shared.model;

import java.io.Serializable;

public class User implements Serializable{
	private int id_partic;
	private String nome_partic;
	private String cpf_partic;
	private String email_partic;
	private String login_partic;
	private String senha_partic;
	private String matr_aluno_partic;
	private boolean logado;
	
	public int getId_partic() {
		return id_partic;
	}
	public void setId_partic(int id_partic) {
		this.id_partic = id_partic;
	}
	public String getNome_partic() {
		return nome_partic;
	}
	public void setNome_partic(String nome_partic) {
		this.nome_partic = nome_partic;
	}
	public String getCpf_partic() {
		return cpf_partic;
	}
	public void setCpf_partic(String cpf_partic) {
		this.cpf_partic = cpf_partic;
	}
	public String getEmail_partic() {
		return email_partic;
	}
	public void setEmail_partic(String email_partic) {
		this.email_partic = email_partic;
	}
	public String getLogin_partic() {
		return login_partic;
	}
	public void setLogin_partic(String login_partic) {
		this.login_partic = login_partic;
	}
	public String getSenha_partic() {
		return senha_partic;
	}
	public void setSenha_partic(String senha_partic) {
		this.senha_partic = senha_partic;
	}
	public String getMatr_aluno_partic() {
		return matr_aluno_partic;
	}
	public void setMatr_aluno_partic(String matr_aluno_partic) {
		this.matr_aluno_partic = matr_aluno_partic;
	}
	public boolean isLogado() {
		return logado;
	}
	public void setLogado(boolean logado) {
		this.logado = logado;
	}
}
