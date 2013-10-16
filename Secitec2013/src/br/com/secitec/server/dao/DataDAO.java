package br.com.secitec.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import br.com.secitec.shared.model.Data;

public class DataDAO {
	
	public static int addData(Data d) {
		
		TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo")); 
		
		PreparedStatement stmt;
		String sql = "insert into datas(data, hr_ini, hr_fim) values (?,?,?)";
		int id = 0;	
		try {
			Connection con = ConnectionMannager.getConnetion();
			stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			System.out.println("###inserindo datas:");
			System.out.println("###Data:" + d.getData());
			System.out.println("###Hora ini:"+ d.getHrInicio());
			System.out.println("###Hora fim:"+ d.getHrFim());
			stmt.setDate(1, d.getData());
			stmt.setTime(2, d.getHrInicio());
			stmt.setTime(3, d.getHrFim());
			
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys(); 
			 if(rs.next()){  
			        id = rs.getInt(1); 
			    }  
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	private static Time formatTimeShow(Time ti){
		SimpleDateFormat sdf=null;  
		Calendar ca = null;
		try {
			
			TimeZone tz = TimeZone.getTimeZone("America/Sao_Paulo");  
	        TimeZone.setDefault(tz);  
	        ca = GregorianCalendar.getInstance(tz);
	        
	        sdf =  new SimpleDateFormat("HH:mm"); 
	        
	        ca.setTime(sdf.parse(ti.toString()));
			 
			//data = new Date(((java.util.Date) fmt.parse(hora)).getTime());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Time t = new Time(ca.getTime().getTime());
		return t;
	}

	public static List<Data> getData(int idAtivi) {
		
		TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));  
		
//		System.out.println("id: "+idAtivi);
		PreparedStatement stmt;
		List<Data> datas = new ArrayList<Data>();
		try {
			Connection con = ConnectionMannager.getConnetion();
			stmt = con
					.prepareStatement("select data, hr_ini, hr_fim from datas d inner join ati_data ad on "
							+ "d.id_data = ad.fk_id_data where ad.fk_id_ativid= "+idAtivi+" order by data, hr_ini");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Data data = new Data(); 
				
				data.setData(rs.getDate(1));
				data.setHrInicio(formatTimeShow(rs.getTime(2)));
				data.setHrFim(formatTimeShow(rs.getTime(3)));
				
				System.out.println("###Pegando datas:");
				System.out.println("###Data:" + data.getData());
				System.out.println("###Hora ini:"+ data.getHrInicio());
				System.out.println("###Hora fim:"+ data.getHrFim());
							
				datas.add(data);
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (datas.isEmpty())
			return null;
		else
			return datas;
	}
	
}
