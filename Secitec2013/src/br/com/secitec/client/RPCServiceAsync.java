package br.com.secitec.client;

import java.util.List;

import br.com.secitec.shared.model.Atividade;
import br.com.secitec.shared.model.User;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface RPCServiceAsync {

	public void getAtividades(AsyncCallback<List<Atividade>> callback);

	public void cadastraUsuario(User user, AsyncCallback<Boolean> callback);
	
	public void login(String login, String senha, AsyncCallback<Boolean> callback);

	public void getSession(AsyncCallback<User> callback);

	public void getSessao(AsyncCallback<Boolean> callback);

	public void inscrever(int codAtividade, AsyncCallback<Boolean> callback);
	
	public void cancelar(int codAtividade, AsyncCallback<Boolean> callback);

	public void getAtividadesUsuario(AsyncCallback<List<Atividade>> callback);

	public void removeSessao(AsyncCallback<Void> callback);

	public void getAtividade(int idAtividade, AsyncCallback<Atividade> callback);

	public void getCpf(String cpf, AsyncCallback<Boolean> callback);

	public void getEmail(String email, AsyncCallback<Boolean> callback);

	public void getMinicursosDoAluno(AsyncCallback<Boolean> callback);
}
