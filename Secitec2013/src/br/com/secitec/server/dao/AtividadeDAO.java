package br.com.secitec.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.secitec.shared.model.Atividade;
import br.com.secitec.shared.model.Data;

public class AtividadeDAO {
	
	public static void incrementaVagas(int idAtividade) {
		PreparedStatement stmt;
		String sql = "UPDATE atividades SET vagas_dispon=vagas_dispon+1 WHERE id_ativid = "+idAtividade;
		
		try {
			Connection con = ConnectionMannager.getConnetion();
			stmt = con.prepareStatement(sql);			
			stmt.execute();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void decrementaVagas(int idAtividade) {
		PreparedStatement stmt;
		String sql = "UPDATE atividades SET vagas_dispon=vagas_dispon-1 WHERE id_ativid = "+idAtividade;
		
		try {
			Connection con = ConnectionMannager.getConnetion();
			stmt = con.prepareStatement(sql);
			stmt.execute();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean getMinicursosDoAluno(int idParticipante){
		PreparedStatement stmt;
		List<Atividade> atividades = new ArrayList<Atividade>();
		try {
			Connection con = ConnectionMannager.getConnetion();
			stmt = con
					.prepareStatement("select a.id_ativid, a.nome_ativid, "
							+ "a.tipo_ativid "
							+ "from atividades a inner join "
							+ "inscricoes i on a.id_ativid = i.id_ativid "
							+ "inner join participantes p on "
							+ "p.id_partic = i.id_partic where "
							+ "p.id_partic = "+idParticipante+" and a.tipo_ativid= 'Minicurso'");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				System.out.println("JÁ ESTÁ INSCRITO EM MINICURSO!!!");
				Atividade atividade = new Atividade();
				
				int id = Integer.parseInt(rs.getString(1));
				List<Data> datas = DataDAO.getData(id);
				atividade.setDatas(datas);
				
				atividade.setIdAtiv(rs.getInt(1));
				atividade.setNomeAtiv(rs.getString(2));
				atividade.setTipoAtiv(rs.getString(3));

				atividades.add(atividade);
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (atividades.size() >= 1)
			return false;
		else
			return true;
	}
	
//	public static Atividade criarAtividade(Atividade atividade) {
//		ResultSet rs;
//		Atividade ativCriada = new Atividade();
//
//		String sql = "insert into atividades "
//				+ "(id_event,dt_ativid,hr_inicio_ativid,hr_fim_ativid,vagas_ativid,tipo_ativid,"
//				+ "nome_ativid,desc_ativid,vagas_dispon) values (?,?,?,?,?,?,?,?,?)";
//
//		try {
//			Connection con = ConnectionMannager.getConnetion();
//			PreparedStatement stmt = con.prepareStatement(sql,
//					Statement.RETURN_GENERATED_KEYS);
//
//			stmt.setInt(1, atividade.getIdEvento());
//			stmt.setDate(2, atividade.getDtAtiv());
//			stmt.setTime(3, atividade.getHrInicio());
//			stmt.setTime(4, atividade.getHrFim());
//			stmt.setInt(5, atividade.getVagasAtiv());
//			stmt.setString(6, atividade.getTipoAtiv());
//			stmt.setString(7, atividade.getNomeAtiv());
//			stmt.setString(8, atividade.getDescAtiv());
//			stmt.setInt(9, atividade.getVagasDisponiveis());
//
//			stmt.execute();
//
//			rs = stmt.getGeneratedKeys();
//			rs.next();
//
//			ativCriada.setIdAtiv(Integer.parseInt(rs.getString(1)));
//			ativCriada.setIdEvento(Integer.parseInt(rs.getString(2)));
//			ativCriada.setDtAtiv(rs.getDate(3));
//			ativCriada.setHrInicio(rs.getTime(4));
//			ativCriada.setHrFim(rs.getTime(5));
//			ativCriada.setVagasAtiv(Integer.parseInt(rs.getString(6)));
//			ativCriada.setTipoAtiv(rs.getString(7));
//			ativCriada.setNomeAtiv(rs.getString(8));
//			ativCriada.setDescAtiv(rs.getString(9));
//			ativCriada.setVagasDisponiveis(rs.getInt(10));
//
//			rs.close();
//			stmt.close();
//			con.close();
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		}
//
//		return ativCriada;
//	}

//	public static List<Atividade> getTodasAtividades(int idEvento) {
//		PreparedStatement stmt;
//		List<Atividade> atividades = new ArrayList<Atividade>();
//		try {
//			Connection con = ConnectionMannager.getConnetion();
//			stmt = con
//					.prepareStatement("select id_ativid, nome_ativid from atividades where id_event="
//							+ idEvento);
//			ResultSet rs = stmt.executeQuery();
//			while (rs.next()) {
//				Atividade atividade = new Atividade();
//				atividade.setIdAtiv(rs.getInt(1));
//				atividade.setNomeAtiv(rs.getString(2));
//
//				atividades.add(atividade);
//			}
//			rs.close();
//			stmt.close();
//			con.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		if (atividades.isEmpty())
//			return null;
//		else
//			return atividades;
//	}

	public static List<Atividade> getTodasAtividades() {
		PreparedStatement stmt;
		List<Atividade> atividades = new ArrayList<Atividade>();
		try {
			Connection con = ConnectionMannager.getConnetion();
			stmt = con
					.prepareStatement("select *from atividades order by id_ativid");
			ResultSet rs = stmt.executeQuery();
				
			
			while (rs.next()) {
				Atividade atividade = new Atividade();
				
				int id = Integer.parseInt(rs.getString(1));
				List<Data> datas = DataDAO.getData(id);
				atividade.setDatas(datas);
				
				atividade.setIdAtiv(Integer.parseInt(rs.getString(1)));
				atividade.setIdEvento(Integer.parseInt(rs.getString(2)));
				atividade.setVagasAtiv(Integer.parseInt(rs.getString(3)));
				atividade.setTipoAtiv(rs.getString(4));
				atividade.setNomeAtiv(rs.getString(5));
				atividade.setDescAtiv(rs.getString(6));
				atividade.setVagasDisponiveis(rs.getInt(7));
				
				atividades.add(atividade);
			}
//			System.out.println("ATIVIDADES TAMANHO: "+atividades.size());
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (atividades.isEmpty())
			return null;
		else
			return atividades;
	}
	
	public static Atividade getAtividade(int idAtividade) {
		PreparedStatement stmt;
		Atividade atividade = new Atividade();
		try {
			Connection con = ConnectionMannager.getConnetion();
			stmt = con
					.prepareStatement("select * from atividades where id_ativid = "+idAtividade);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = Integer.parseInt(rs.getString(1));
				List<Data> datas = DataDAO.getData(id);
				atividade.setDatas(datas);
				
				atividade.setIdAtiv(Integer.parseInt(rs.getString(1)));
				atividade.setIdEvento(Integer.parseInt(rs.getString(2)));
				atividade.setVagasAtiv(Integer.parseInt(rs.getString(3)));
				atividade.setTipoAtiv(rs.getString(4));
				atividade.setNomeAtiv(rs.getString(5));
				atividade.setDescAtiv(rs.getString(6));
				atividade.setVagasDisponiveis(rs.getInt(7));
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return atividade;
	}
	
	public static List<Atividade> getAtividadesUsuario(int idParticipante) {
		PreparedStatement stmt;
		List<Atividade> atividades = new ArrayList<Atividade>();
		try {
			Connection con = ConnectionMannager.getConnetion();
			stmt = con
					.prepareStatement("select a.id_ativid, a.nome_ativid, "
							+ "a.tipo_ativid "
							+ "from atividades a inner join "
							+ "inscricoes i on a.id_ativid = i.id_ativid "
							+ "inner join participantes p on "
							+ "p.id_partic = i.id_partic where "
							+ "p.id_partic = "+idParticipante);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Atividade atividade = new Atividade();
				
				int id = Integer.parseInt(rs.getString(1));
				List<Data> datas = DataDAO.getData(id);
				atividade.setDatas(datas);
				
				atividade.setIdAtiv(rs.getInt(1));
				atividade.setNomeAtiv(rs.getString(2));
				atividade.setTipoAtiv(rs.getString(3));

				atividades.add(atividade);
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (atividades.isEmpty())
			return null;
		else
			return atividades;
	}
}
