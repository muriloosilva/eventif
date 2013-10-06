package br.com.secitec.server;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import br.com.secitec.client.RPCService;
import br.com.secitec.server.dao.AtividadeDAO;
import br.com.secitec.server.dao.InscricaoDAO;
import br.com.secitec.server.dao.LoginDAO;
import br.com.secitec.server.dao.ParticipanteDAO;
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
	
	public void enviaEmail(User user){
		
		String host = "smtp.gmail.com";
		String userName = "secitecifgformosa@secitecifgformosa.com.br";
		String passwd = "12323556111122";
		Properties props = System.getProperties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", userName);
		props.put("mail.smtp.password", passwd);
		props.put("mail.smtp.port", "465"); // 587 is the port number of yahoo mail
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.port", "465");    
		props.put("mail.smtp.socketFactory.fallback", "false");    
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");  
		
		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(userName));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail_partic()));

			message.setSubject("sending in a group");
			message.setText("Para confirmar seu cadastro clique aqui ...");
			// alternately, to send HTML mail:
			// message.setContent("<p>Welcome to JavaMail</p>", "text/html");
			Transport transport = session.getTransport("smtp");
			transport.connect(host, userName, passwd);
			transport.sendMessage(message, message.getAllRecipients());
			
			transport.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
	}

	@Override
	public boolean cadastraUsuario(User user) {
		if (ParticipanteDAO.loginDisponivel(user.getLogin_partic())) {
			ParticipanteDAO.cadastraParticipante(user);
			enviaEmail(user);
			return true;
		} else
			return false;
	}

	@Override
	public boolean login(String login, String senha) {
		// TODO Auto-generated method stub
		User user = LoginDAO.loginUsuario(login, senha);
		if (user.isLogado()) {
			HttpSession session = getThreadLocalRequest().getSession();
			session.setAttribute("user", user);
		}
		return user.isLogado();
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

}
