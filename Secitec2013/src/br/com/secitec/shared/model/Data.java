package br.com.secitec.shared.model;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Date;

import com.google.gwt.i18n.shared.DateTimeFormat;

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
		
        java.util.Date date = null;
        try {
        	DateTimeFormat fmt = DateTimeFormat.getFormat("yyyy-MM-dd");
            date = (java.util.Date)fmt.parse(data);
            
        } catch (Exception e) {            
            throw e;
        }
        return new Date(date.getTime());
	}
	
	public Time formatTime(String hora){
		DateTimeFormat fmt = DateTimeFormat.getFormat("HH:mm:ss");  
		Date data = null;
		try {
			data = new Date(((java.util.Date) fmt.parse(hora)).getTime());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		Time time = new Time(data.getTime());
		return time;
	}
	
	
}
