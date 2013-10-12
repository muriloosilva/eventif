package br.com.secitec.server.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.com.secitec.shared.model.User;

public class MailUtil {
	
	static String host = "smtp.gmail.com";
	static String userName = "secitecifgformosa@secitecifgformosa.com.br";
	static String passwd = "12323556111122";
	
	public static boolean confirmacaoDeCadastro(User user, String msg){
		
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

			message.setSubject("SECITEC IFG FORMOSA - Confirmação de Cadastro");
			//message.setText(msg);
			// alternately, to send HTML mail:
			 message.setContent("<p>SECITEC IFG FORMOSA - Confirmação de Cadastro</p><br><br>" +
			 		"Para confirmar seu cadastro clique no link abaixo:<br>" +
					 "<a href='"+msg+"'>Confirme seu cadastro clicando aqui.</a>", "text/html");
			Transport transport = session.getTransport("smtps");
			transport.connect(host, userName, passwd);
			transport.sendMessage(message, message.getAllRecipients());
			
			transport.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static boolean faleConosco(String name, String email, String mesg){
		
		
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
			message.setFrom(new InternetAddress("faleconosco@secitecifgformosa.com.br"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(userName));

			message.setSubject("FALE CONOSCO - SECITEC IFG FORMOSA");
			//message.setText(msg);
			// alternately, to send HTML mail:
			message.setContent("Usuário: " + name + "<br> Email: "+ email + "<br> Mensagem: " + mesg, "text/html");
			Transport transport = session.getTransport("smtps");
			transport.connect(host, userName, passwd);
			transport.sendMessage(message, message.getAllRecipients());
			
			transport.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;

		
		
	}
	
}

