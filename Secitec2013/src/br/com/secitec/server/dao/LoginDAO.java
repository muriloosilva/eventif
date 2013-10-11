package br.com.secitec.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.secitec.shared.model.User;

public class LoginDAO {
	public static User loginUsuario(String login, String senha){
		User user = null;
		
		try {
			Connection con = ConnectionMannager.getConnetion();
			PreparedStatement stmt = con
					.prepareStatement("select * from participantes "
							+ "where email_partic = ? and senha_partic = ?");
			stmt.setString(1, login);
			stmt.setString(2, senha);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setId_partic(rs.getInt("id_partic"));
				user.setNome_partic(rs.getString("nome_partic"));
				user.setCpf_partic(rs.getString("cpf_partic"));
				user.setEmail_partic(rs.getString("email_partic"));
				user.setLogin_partic(rs.getString("login_partic"));
				user.setSenha_partic(rs.getString("senha_partic"));
				user.setMatr_aluno_partic(rs.getString("matr_aluno_partic"));
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (user != null) {
			if (user.getEmail_partic().equals(login)
					&& user.getSenha_partic().equals(
							senha)) {
				user.setLogado(true);
			} else {
				user.setLogado(false);
			}
		}
		else{
			user = new User();
			user.setLogado(false);
		}
		return user;
	}
}
