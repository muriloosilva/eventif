package br.com.secitec.server;

import java.util.ArrayList;

import br.com.secitec.shared.model.AtividadeJson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.Gson;

@Path("/atividade")
public class ServletAtividadeJson {

	@GET
	@Path("/listarTodos")
	@Produces("application/json")
	public ArrayList<AtividadeJson> listarTodos(){
		return AtividadeControle.listarTodos();
	}

}
