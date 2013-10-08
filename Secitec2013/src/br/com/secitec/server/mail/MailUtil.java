package br.com.secitec.server.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.com.secitec.shared.model.User;

public class MailUtil {
	
	public static void confirmacaoDeCadastro(User user){
		
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

			message.setSubject("SECITEC IFG FORMOSA - Confirmação de Cadastro");
			message.setText("Para realizar o cadastro ...");
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

}
