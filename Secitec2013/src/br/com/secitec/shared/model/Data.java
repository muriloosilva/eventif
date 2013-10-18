package br.com.secitec.shared.model;

import java.io.Serializable;

public class Data implements Serializable{
	private String data;
	private String hrInicio;
	private String hrFim;
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getHrInicio() {
		return hrInicio;
	}
	public void setHrInicio(String hrInicio) {
		this.hrInicio = hrInicio;
	}
	public String getHrFim() {
		return hrFim;
	}
	public void setHrFim(String hrFim) {
		this.hrFim = hrFim;
	}
		
}
