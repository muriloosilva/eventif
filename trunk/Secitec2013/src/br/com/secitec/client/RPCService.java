package br.com.secitec.client;

import java.util.List;

import br.com.secitec.shared.model.Atividade;
import br.com.secitec.shared.model.User;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("RPCService")
public interface RPCService extends RemoteService {

	List<Atividade> getAtividades();
	List<Atividade> getAtividadesUsuario();
	boolean cadastraUsuario(User user);
	boolean login(String login, String senha);
	User getSession();
	boolean getSessao();
	boolean inscrever(int codAtividade);
	boolean cancelar(int codAtividade);
	void removeSessao();
	Atividade getAtividade(int idAtividade);
}
