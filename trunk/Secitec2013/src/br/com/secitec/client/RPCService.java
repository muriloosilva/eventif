package br.com.secitec.client;

import java.util.List;

import br.com.secitec.shared.model.Atividade;
import br.com.secitec.shared.model.Data;
import br.com.secitec.shared.model.User;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("RPCService")
public interface RPCService extends RemoteService {

	List<Atividade> getAtividades();
	List<Atividade> getAtividadesUsuario();
	boolean cadastraUsuario(User user);
	int login(String login, String senha);
	User getSession();
	boolean faleConosco(String name, String email, String msg);
	boolean insereAtividade(Atividade a, List<Data> ld);
	boolean reenviaConfirmacaoCadastro(User user);
	boolean reenviaConfirmacaoAlteracaoDados(User user);
	int alterarDados(User user);
	boolean recuperarSenha(User user);
	boolean getSessao();
	int inscrever(int codAtividade);
	int inscreverMinicurso(int codAtividade);
	boolean cancelar(int codAtividade);
	void removeSessao();
	Atividade getAtividade(int idAtividade);
	boolean getCpf(String cpf);
	boolean getEmail(String email);
	boolean getMinicursosDoAluno();
}
