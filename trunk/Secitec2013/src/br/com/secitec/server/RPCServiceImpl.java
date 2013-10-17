package br.com.secitec.server;

import java.util.List;

import javax.servlet.http.HttpSession;

import br.com.secitec.client.RPCService;
import br.com.secitec.server.dao.AtiDataDAO;
import br.com.secitec.server.dao.AtividadeDAO;
import br.com.secitec.server.dao.DataDAO;
import br.com.secitec.server.dao.InscricaoDAO;
import br.com.secitec.server.dao.LoginDAO;
import br.com.secitec.server.dao.ParticipanteDAO;
import br.com.secitec.server.util.ConfirmacaoCadastro;
import br.com.secitec.server.util.MailUtil;
import br.com.secitec.shared.model.Atividade;
import br.com.secitec.shared.model.Data;
import br.com.secitec.shared.model.User;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class RPCServiceImpl extends RemoteServiceServlet implements RPCService {

	@Override
	public List<Atividade> getAtividades() {

		List<Atividade> atividades = AtividadeDAO.getTodasAtividades();

		return atividades;
	}

	@Override
	public boolean cadastraUsuario(User user) {
		if (ParticipanteDAO.loginDisponivel(user.getLogin_partic())) {
			ParticipanteDAO.cadastraParticipante(user);
			return ConfirmacaoCadastro.enviaConfirmacaoCadastro(user);
		} else
			return false;
	}
	
	@Override
	public boolean reenviaConfirmacaoCadastro(User user) {
		return ConfirmacaoCadastro.enviaConfirmacaoCadastro(user);
	}

	@Override
	public int login(String login, String senha) {
		//0 erro no login
		//1 inativo cadastro
		//2 login ok
		//3 não confirmou alteração dados
		// TODO Auto-generated method stub
		User user = LoginDAO.loginUsuario(login, senha);
		if(user != null){
//			System.out.println("user logado:" +user.isLogado());
			if(user.isLogado()){
				if(user.getAtivo()==0){
					return 1;
				}
				else if (user.getAtivo()==1) {
					HttpSession session = getThreadLocalRequest().getSession();
					session.setAttribute("user", user);
					return 2;
				}
				else if(user.getAtivo()==2){
					return 3;
				}
				else{
					return 0;
				}
			}
			else{
				return 0;
			}
		}
		else {
			return 0;
		}
		
	}

	@Override
	public User getSession() {
		// TODO Auto-generated method stub
		HttpSession session = getThreadLocalRequest().getSession();
		User user = (User) session.getAttribute("user");
		return user;
	}

	@Override
	public boolean getSessao() {
		User user = null;
		HttpSession session = getThreadLocalRequest().getSession();
		user = (User) session.getAttribute("user");
		if (user != null)
			return user.isLogado();
		else
			return false;
	}

	public double formataHora(String h) {
		double dH;
		if (h.charAt(0) == '0') {
			h = h.substring(1);
			dH = Double.parseDouble(h.substring(0, 4).replace(':', '.'));
		} else {
			dH = Double.parseDouble(h.substring(0, 5).replace(':', '.'));
		}
		return dH;
	}

	//return 0: conflito de horário
	//return 1: não tem vagas
	//return 2: ok
	@Override
	public int inscrever(int codAtividade) {
		// TODO Auto-generated method stub
		User user = getSession();
		List<Atividade> atividades = getAtividadesUsuario();
		Atividade atividade = getAtividade(codAtividade);
		int r = -1;

		if (atividades != null) {
			
			for (int i = 0; i < atividades.size(); i++) {
				//verificar se já está inscrito na atividade
				List<Data> datas = atividades.get(i).getDatas();
				
				for (int j = 0; j < datas.size(); j++) {
					double hr_ini_data = formataHora(datas.get(j).getHrInicio().toString());
					double hr_fim_data = formataHora(datas.get(j).getHrFim().toString());
					List<Data> datasAtividadesInteresse = atividade.getDatas();
					
					for (int k = 0; k < datasAtividadesInteresse.size(); k++) {
						double hora_ini_interesse = formataHora(datasAtividadesInteresse.get(k).getHrInicio().toString());
						double hora_fim_interesse = formataHora(datasAtividadesInteresse.get(k).getHrFim().toString());
						
						if (datasAtividadesInteresse.get(k).getData().equals(datas.get(j).getData())) {
							if (hora_ini_interesse > hr_ini_data) {
								if (hora_ini_interesse < hr_fim_data) {
									r = 0;
								}
							} else {
								if (hora_fim_interesse > hr_ini_data) {
									r = 0;
								}
							}
						}
						
					}
				
				}
			}
		}
		if(atividade.getVagasDisponiveis() > 0){
			InscricaoDAO.inscrever(codAtividade, user.getId_partic());
			AtividadeDAO.decrementaVagas(codAtividade);
			r = 2;
		}
		else{
			r = 1;
		}
			
		return r;
	}

	@Override
	public List<Atividade> getAtividadesUsuario() {
		// TODO Auto-generated method stub
		User user = (User) getThreadLocalRequest().getSession(true)
				.getAttribute("user");
		List<Atividade> atividades = AtividadeDAO.getAtividadesUsuario(user
				.getId_partic());

		if (atividades != null)
			return atividades;
		else
			return null;
	}

	@Override
	public void removeSessao() {
		// TODO Auto-generated method stub
		getThreadLocalRequest().getSession(true).removeAttribute("user");
		getThreadLocalRequest().getSession(true).invalidate();
		User user = (User) getThreadLocalRequest().getSession(true)
				.getAttribute("user");
		if (user != null)
			System.out.println("usuario na sessão: " + user.getNome_partic());
		else
			System.out.println("sem usuario na sessão");
	}

	@Override
	public Atividade getAtividade(int idAtividade) {
		// TODO Auto-generated method stub
		Atividade atividade = AtividadeDAO.getAtividade(idAtividade);
		return atividade;
	}

	@Override
	public boolean cancelar(int codAtividade) {
		User user = getSession();
		return InscricaoDAO.cancelar(codAtividade, user.getId_partic());
	}

	@Override
	public boolean getCpf(String cpf) {
		return ParticipanteDAO.getCPF(cpf);
	}

	@Override
	public boolean getEmail(String email) {
		// TODO Auto-generated method stub
		return ParticipanteDAO.getEmail(email);
	}

	@Override
	public boolean getMinicursosDoAluno() {
		User user = (User) getThreadLocalRequest().getSession(true)
				.getAttribute("user");
		
		return AtividadeDAO.getMinicursosDoAluno(user.getId_partic());
	}

	@Override
	public boolean faleConosco(String name, String email, String msg) {
		return MailUtil.faleConosco(name, email, msg);
	}

	@Override
	public boolean recuperarSenha(User user) {
		User userBD = ParticipanteDAO.getParticipante(user.getEmail_partic());
		if(userBD != null){
			if(user.getCpf_partic().equals(userBD.getCpf_partic())){
				return MailUtil.recuperarSenha(userBD);
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
		// TODO Auto-generated method stub
	}
	
	@Override
	public boolean reenviaConfirmacaoAlteracaoDados(User user) {
		return ConfirmacaoCadastro.enviaConfirmacaoAlteracaoDados(user);
	}

	@Override
	public int alterarDados(User user) {
		//0 erro
		//1 alerado, mas com confirmação
		//2 alterado, sem confirmação
		//3 já existe um usuário com este cpf
		//4 já ... com este email
		User userSession = getSession();
		if(userSession!= null && user != null){
			if(!user.getCpf_partic().equals(userSession.getCpf_partic())){
				if(ParticipanteDAO.getCPF(user.getCpf_partic()) == false)
					return 3;
			}
			else{
				if(!user.getEmail_partic().trim().equals(userSession.getEmail_partic().trim())){
					if(ParticipanteDAO.getParticipante(user.getEmail_partic())!= null){
						return 4;
					}
					else{
						//confirmar alteração por e-mail
						ParticipanteDAO.alterarDados(userSession.getEmail_partic(), user, true);
						if(ConfirmacaoCadastro.enviaConfirmacaoAlteracaoDados(user)){
							return 1;
						}
						else{
							return 0;
						}
					}
				}
				else{
					//alterar dados apenas
					if(ParticipanteDAO.alterarDados(userSession.getEmail_partic(), user, false)){
						return 2;
					}
					else{
						return 0;
					}
				}
			}
		}
		return 0;
	}

	@Override
	public boolean insereAtividade(Atividade a,  List<Data> ld) {
		
		int idAtividade = AtividadeDAO.addAtividade(a);
		System.out.println("Id Atividade: " + idAtividade);
		if(idAtividade == 0)
			return false;
		for(int i = 0; i<ld.size(); i++){
			int id = DataDAO.addData(ld.get(i));
			System.out.println("Id Data: " + id);
			if(id == 0)
				return false;
			else{
				AtiDataDAO.addAtiData(id, idAtividade);
			}
		}
		return true;
	}
}
