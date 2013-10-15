package br.com.secitec.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.secitec.shared.model.Data;

public class AtiDataDAO {
	
	public static void addAtiData(int idData, int idAtividade) {
		PreparedStatement stmt;
		String sql = "insert into ati_data(fk_id_data, fk_id_ativid) values (?,?)";
		try {
			Connection con = ConnectionMannager.getConnetion();
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, idData);
			stmt.setInt(2, idAtividade);
			
			stmt.executeUpdate();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static List<Data> getData(int idAtivi) {
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
				data.setHrInicio(rs.getTime(2));
				data.setHrFim(rs.getTime(3));
							
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
