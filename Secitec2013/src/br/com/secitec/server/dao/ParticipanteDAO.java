package br.com.secitec.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.secitec.shared.model.User;

public class ParticipanteDAO {
	public static boolean getCPF(String cpf){
		boolean b;
		try {
			Connection con = ConnectionMannager.getConnetion();
			PreparedStatement stmt = con.prepareStatement("select *from participantes where cpf_partic='"+cpf+"'");

			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				rs.close();
				stmt.close();
				con.close();
				b = false;
			} else {
				rs.close();
				stmt.close();
				con.close();
				b = true;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return b;
	}
	
	public static boolean getEmail(String email){
		boolean b;
		try {
			Connection con = ConnectionMannager.getConnetion();
			PreparedStatement stmt = con.prepareStatement("select *from participantes where email_partic='"+email+"'");

			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				rs.close();
				stmt.close();
				con.close();
				b = false;
			} else {
				rs.close();
				stmt.close();
				con.close();
				b = true;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return b;
	}
	
	public static boolean loginDisponivel(String login){
		boolean b;
		try {
			Connection con = ConnectionMannager.getConnetion();
			PreparedStatement stmt = con.prepareStatement("select *from participantes where email_partic='"+login+"'");

			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				rs.close();
				stmt.close();
				con.close();
				System.out.println("Login indisponível");
				b = false;
			} else {
				rs.close();
				stmt.close();
				con.close();
				System.out.println("Login disponível");
				b = true;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return b;
	}
	
	public static void cadastraParticipante(User user){
		Connection con = ConnectionMannager.getConnetion();
		try {
			PreparedStatement stmt = con.prepareStatement("insert into participantes (nome_partic,cpf_partic,email_partic,"
					+ "senha_partic,matr_aluno_partic) values (?,?,?,?,?)");
			stmt.setString(1, user.getNome_partic());
			stmt.setString(2, user.getCpf_partic());
			stmt.setString(3, user.getEmail_partic());
			stmt.setString(4, user.getSenha_partic());
			stmt.setString(5, user.getMatr_aluno_partic());
			
			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
