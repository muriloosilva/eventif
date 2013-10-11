package br.com.secitec.server;

import java.util.List;

import javax.servlet.http.HttpSession;

import br.com.secitec.client.RPCService;
import br.com.secitec.server.dao.AtividadeDAO;
import br.com.secitec.server.dao.InscricaoDAO;
import br.com.secitec.server.dao.LoginDAO;
import br.com.secitec.server.dao.ParticipanteDAO;
import br.com.secitec.server.util.ConfirmacaoCadastro;
import br.com.secitec.server.util.MailUtil;
import br.com.secitec.shared.model.Atividade;
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
			ConfirmacaoCadastro.enviaConfirmacaoCadastro(user);
			return true;
		} else
			return false;
	}

	@Override
	public int login(String login, String senha) {
		//0 erro no login
		//1 inativo cadastro
		//2 login ok
		// TODO Auto-generated method stub
		User user = LoginDAO.loginUsuario(login, senha);
		if(user.getAtivo()==0){
			return 1;
		}
		else if (user.isLogado()) {
			HttpSession session = getThreadLocalRequest().getSession();
			session.setAttribute("user", user);
			return 2;
		}
		else{
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

	@Override
	public boolean inscrever(int codAtividade) {
		// TODO Auto-generated method stub
		User user = getSession();
		List<Atividade> atividades = getAtividadesUsuario();
		Atividade atividade = getAtividade(codAtividade);
		boolean b = false;

		if (atividades != null) {
			for (int i = 0; i < atividades.size(); i++) {
				double hora_ini = formataHora(atividade.getHrInicio().toString());
				double hora_fim = formataHora(atividade.getHrFim().toString());
				double hr_ini = formataHora(atividades.get(i).getHrInicio().toString());
				double hr_fim = formataHora(atividades.get(i).getHrFim().toString());

				if (atividade.getDtAtiv().equals(atividades.get(i).getDtAtiv())) {
					if (hora_ini > hr_ini) {
						if (hora_ini < hr_fim) {
							b = true;
							break;
						}
					} else {
						if (hora_fim > hr_ini) {
							b = true;
							break;
						}
					}
				}

			}
		}
		if (b) {
			return false;
		} else {
			InscricaoDAO.inscrever(codAtividade, user.getId_partic());
			return true;
		}
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

}
