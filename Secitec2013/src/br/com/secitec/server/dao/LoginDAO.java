package br.com.secitec.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.secitec.shared.model.User;

public class LoginDAO {
	
	public static final int DESLOGADO = 0;
	public static final int LOGADO = 1;
	public static final int INATIVO = 2;
	public static final int ATIVO = 3;
	
	public static User loginUsuario(String cpf, String senha){
		
		User user = null;
		
		try {
			Connection con = ConnectionMannager.getConnetion();
			PreparedStatement stmt = con
					.prepareStatement("select * from participantes "
							+ "where cpf_partic = ? and senha_partic = ?");
			stmt.setString(1, cpf);
			stmt.setString(2, senha);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setCpf_partic(rs.getString("cpf_partic"));
				user.setNome_partic(rs.getString("nome_partic"));
				user.setEmail_partic(rs.getString("email_partic"));
				user.setSenha_partic(rs.getString("senha_partic"));
				user.setMatr_aluno_partic(rs.getString("matr_aluno_partic"));
				user.setAtivo(rs.getInt("ativo"));
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (user != null) {
			System.out.println("email: " + user.getEmail_partic().trim());
			System.out.println("senha: " + user.getSenha_partic());
			System.out.println("email1: " + cpf);
			System.out.println("senha1: " + senha);
			System.out.println("usuario existe");
			if (user.getCpf_partic().trim().equals(cpf)
					&& user.getSenha_partic().equals(
							senha)) {
				System.out.println("Usuário autenticado");
				user.setLogado(true);
			} else {
				System.out.println("Usuário não autenticado");
				user.setLogado(false);
			}
		}
		else{
			System.out.println("User null");
		}
		return user;
	}
}
