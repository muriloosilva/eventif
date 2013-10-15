package br.com.secitec.shared.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Data implements Serializable{
	private Date data;
	private Time hrInicio;
	private Time hrFim;
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
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
	
	public Date formataData(String data) throws Exception { 
		if (data == null || data.equals(""))
			return null;
		
        Date date = null;
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            date = (Date)formatter.parse(data);
        } catch (Exception e) {            
            throw e;
        }
        return date;
	}
	
	public Time formatTime(String hora){
		SimpleDateFormat formatador = new SimpleDateFormat("HH:mm:ss");  
		Date data = null;
		try {
			data = (Date) formatador.parse(hora);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		Time time = new Time(data.getTime());
		return time;
	}
	
	
}
