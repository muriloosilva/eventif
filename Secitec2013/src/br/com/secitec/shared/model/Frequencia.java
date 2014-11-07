package br.com.secitec.shared.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.Window;

@SuppressWarnings("serial")
public class Frequencia implements Serializable{
	
	private String CPF;
	private int atividade;
	private int status;	
	
	
	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public int getAtividade() {
		return atividade;
	}

	public void setAtividade(int atividade) {
		this.atividade = atividade;
	}

	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}

	
	

}
