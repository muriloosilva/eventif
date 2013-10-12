package br.com.secitec.server.util;

import br.com.secitec.shared.model.User;

public class ConfirmacaoCadastro {
	
	public static boolean enviaConfirmacaoCadastro(User user){
		
		String hash = HashUtil.stringHexa(HashUtil.gerarHash(user.getCpf_partic()+user.getLogin_partic()+user.getSenha_partic(), "SHA-1"));
		return MailUtil.confirmacaoDeCadastro(user, "http://secitecifgformosa.com.br/confirmRegistration?id="+user.getEmail_partic()+"&key="+hash);
		//Gerar hash
		//
	}

}
