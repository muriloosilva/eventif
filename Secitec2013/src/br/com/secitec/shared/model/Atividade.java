package br.com.secitec.shared.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Atividade implements Serializable{
	private int idAtiv;
	private int idEvento;
	private Date dtAtiv;
	private String nomeAtiv;
	private String tipoAtiv;
	private String descAtiv;
	private int vagasAtiv;
	private Time hrInicio;
	private Time hrFim;
	private int vagasDisponiveis;
	private List<Data> datas = new ArrayList<Data>();
	
	public List<Data> getDatas() {
		return datas;
	}
	public void setDatas(List<Data> datas) {
		this.datas = datas;
	}
	public int getVagasDisponiveis() {
		return vagasDisponiveis;
	}
	public void setVagasDisponiveis(int vagasDisponiveis) {
		this.vagasDisponiveis = vagasDisponiveis;
	}
	public int getIdEvento() {
		return idEvento;
	}
	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}
	public int getIdAtiv() {
		return idAtiv;
	}
	public void setIdAtiv(int idAtiv) {
		this.idAtiv = idAtiv;
	}
	public Date getDtAtiv() {
		return dtAtiv;
	}
	public void setDtAtiv(Date dtAtiv) {
		this.dtAtiv = dtAtiv;
	}
	public String getNomeAtiv() {
		return nomeAtiv;
	}
	public void setNomeAtiv(String nomeAtiv) {
		this.nomeAtiv = nomeAtiv;
	}
	public String getTipoAtiv() {
		return tipoAtiv;
	}
	public void setTipoAtiv(String tipoAtiv) {
		this.tipoAtiv = tipoAtiv;
	}
	public String getDescAtiv() {
		return descAtiv;
	}
	public void setDescAtiv(String descAtiv) {
		this.descAtiv = descAtiv;
	}
	public int getVagasAtiv() {
		return vagasAtiv;
	}
	public void setVagasAtiv(int vagasAtiv) {
		this.vagasAtiv = vagasAtiv;
	}
	public Time getHrInicio() {
		return hrInicio;
	}
	public void setHrInicio(Time hrInicio) {
		this.hrInicio = hrInicio;
	}
	public Time getHrFim() {
		return hrFim;
	}
	public void setHrFim(Time hrFim) {
		this.hrFim = hrFim;
	}
	
//	@Override
//	public int compareTo(Atividade a) {
//		for (int i = 0; i < this.datas.size(); i++) {
//			if(String.valueOf(this.dtAtiv).compareToIgnoreCase(String.valueOf(a.getDtAtiv())) < 0)
//				return -1;
//			else if(String.valueOf(this.dtAtiv).compareToIgnoreCase(String.valueOf(a.getDtAtiv())) > 0)
//				return 1;
//			return String.valueOf(this.hrInicio).compareToIgnoreCase(String.valueOf(a.getHrInicio()));
//		}
////		if(String.valueOf(this.dtAtiv).compareToIgnoreCase(String.valueOf(a.getDtAtiv())) < 0)
////			return -1;
////		else if(String.valueOf(this.dtAtiv).compareToIgnoreCase(String.valueOf(a.getDtAtiv())) > 0)
////			return 1;
////		return String.valueOf(this.hrInicio).compareToIgnoreCase(String.valueOf(a.getHrInicio()));
//	}
}
