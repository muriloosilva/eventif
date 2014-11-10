package br.com.secitec.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.TimeZone;

import br.com.secitec.server.util.DataUtil;
import br.com.secitec.shared.model.Frequencia;

public class FrequenciaDAO{
	
	public static boolean registraFrequenciaAtividade(Frequencia frequencia) {
		
		TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo")); 
		
		PreparedStatement stmt;
		String sql = "insert into frequencia(cpf_partic, id_ativid, status, horario) values (?,?,?,?)";
		
		try {
			Connection con = ConnectionMannager.getConnetion();
			stmt = con.prepareStatement(sql);
			stmt.setString(1, frequencia.getCPF());
			stmt.setInt(2, frequencia.getAtividade());
			stmt.setInt(3, frequencia.getStatus());
			stmt.setTime(4,DataUtil.pegaHoraAtual());
			
			stmt.executeUpdate();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			return false;
	}
		return true;
	
	}
	
	public static boolean registraFrequenciaEvento(Frequencia frequencia) {
		
		TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo")); 
		
		PreparedStatement stmt;
		String sql = "insert into frequencia_evento(cpf_participante, data, hora) values (?,?,?)";
		
		try {
			Connection con = ConnectionMannager.getConnetion();
			stmt = con.prepareStatement(sql);
			stmt.setString(1, frequencia.getCPF());
			stmt.setDate(2, DataUtil.pegaDataAtual());
			stmt.setTime(3,DataUtil.pegaHoraAtual());
			
			stmt.executeUpdate();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
	}
		return true;
	
	}
	
}
