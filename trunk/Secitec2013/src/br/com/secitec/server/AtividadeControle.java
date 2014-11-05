package br.com.secitec.server;

import java.util.ArrayList;

import br.com.secitec.server.dao.AtividadeDAO;
import br.com.secitec.shared.model.AtividadeJson;

public class AtividadeControle {
	
	public static ArrayList<AtividadeJson> listarTodos(){
		System.out.println("Enviando para o GIT");
		return AtividadeDAO.pegaAtividadesJson();
	}

}
