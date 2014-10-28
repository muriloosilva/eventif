package br.com.secitec.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InscricaoDAO {
	public static void inscrever(int idAtividade, String cpf) {
		PreparedStatement stmt;
		String sql = "insert into inscricoes(cpf_partic, id_ativid, status_insc, "
				+ "status_pres) values (?,?,?,?)";
		
		try {
			Connection con = ConnectionMannager.getConnetion();
			stmt = con.prepareStatement(sql);
			stmt.setString(1, cpf);
			stmt.setInt(2, idAtividade);
			stmt.setBoolean(3, true);
			stmt.setBoolean(4, false);
			
			stmt.execute();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean cancelar(int idAtividade, String cpf){
		PreparedStatement stmt;
		String sql = "delete from inscricoes where cpf_partic = ? and id_ativid = ?";
		
		try {
			Connection con = ConnectionMannager.getConnetion();
			stmt = con.prepareStatement(sql);
			stmt.setString(1, cpf);
			stmt.setInt(2, idAtividade);
			
			stmt.execute();
			stmt.close();
			con.close();
			
			AtividadeDAO.incrementaVagas(idAtividade);
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
