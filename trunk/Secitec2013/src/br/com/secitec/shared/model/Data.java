package br.com.secitec.shared.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.i18n.client.constants.TimeZoneConstants;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.client.Window;

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
        String d = null;
        DateTimeFormat fmt = null;
        try {
        	TimeZoneConstants timeZoneConstants = GWT.create(TimeZoneConstants.class);
        	fmt = DateTimeFormat.getFormat("yyyy-MM-dd");
            date = (java.util.Date)fmt.parse(data);
            d = fmt.format(date, com.google.gwt.i18n.client.TimeZone.createTimeZone(timeZoneConstants.americaSaoPaulo()));
            
        } catch (Exception e) {            
            throw e;
        }
        
        return new Date(((java.util.Date)fmt.parse(d)).getTime());
	}
	
	public Time formatTimeShow(String hora){
		DateTimeFormat fmt= null;
		java.util.Date date = null;
		String d = null;
		try {
			TimeZoneConstants timeZoneConstants = GWT.create(TimeZoneConstants.class);
			fmt = DateTimeFormat.getFormat("HH:mm:ss"); 
			date = fmt.parse(hora);
			d = fmt.format(date, com.google.gwt.i18n.client.TimeZone.createTimeZone(timeZoneConstants.americaRioBranco()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return new Time(((java.util.Date)fmt.parse(d)).getTime());
	}	
	
	public Time formatTime(String hora){
		DateTimeFormat fmt=null;  
		java.util.Date date = null;
		String d = null;
		try {
			TimeZoneConstants timeZoneConstants = GWT.create(TimeZoneConstants.class);
			fmt = DateTimeFormat.getFormat("HH:mm:ss");  
			date = fmt.parse(hora);
			d = fmt.format(date, com.google.gwt.i18n.client.TimeZone.createTimeZone(timeZoneConstants.americaSaoPaulo()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return new Time(((java.util.Date)fmt.parse(d)).getTime());
	}	
	
}
