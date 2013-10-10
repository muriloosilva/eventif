package br.com.secitec.menu.view;

import java.util.List;

import br.com.secitec.menu.presenter.MenuPresenter;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class MenuView extends Composite implements MenuPresenter.Display {
	private Anchor apresentacao;
	private Anchor programacao;
	private Anchor login;
	private Anchor atividades;
	private Anchor faleConosco;
	private HorizontalPanel menuHorizontal, hpUsuario;
	
	private Anchor sair;
	private Label nomeUsuario;
	private Label lbNomeUsuario;
	
	public MenuView() {
		menuHorizontal = new HorizontalPanel();
		initWidget(menuHorizontal);
		
		apresentacao = new Anchor("Apresentação");
		apresentacao.addStyleName("aMenuHorizontal");
		programacao = new Anchor("Programação");
		programacao.addStyleName("aMenuHorizontal");
		login = new Anchor("Login ou Cadastre-se");
		login.addStyleName("aMenuHorizontal");
		atividades = new Anchor("Minhas Atividades");
		atividades.addStyleName("aMenuHorizontal");
		atividades.setVisible(false);
		faleConosco = new Anchor("Fale Conosco");
        faleConosco.addStyleName("aMenuHorizontal");
		
		menuHorizontal.add(apresentacao);
		menuHorizontal.add(programacao);
		menuHorizontal.add(atividades);
		menuHorizontal.add(login);
		menuHorizontal.add(faleConosco);
		
		hpUsuario = new HorizontalPanel();
		hpUsuario.setHorizontalAlignment(HorizontalPanel.ALIGN_RIGHT);
		hpUsuario.addStyleName("hpUsuario");
		
		sair = new Anchor("Sair");
		sair.addStyleName("aMenuUsuario");
		
		lbNomeUsuario = new Label("Usuário:");
		lbNomeUsuario.addStyleName("aMenuUsuario");
	
		nomeUsuario = new Label();
		nomeUsuario.addStyleName("nomeUsuario");
		
		hpUsuario.add(lbNomeUsuario);
		hpUsuario.add(nomeUsuario);
		hpUsuario.add(sair);
		
		menuHorizontal.add(hpUsuario);
		
	}
	
	public HasClickHandlers getFaleConosco() {
		return faleConosco;
	}

	public HasClickHandlers getApresentationLabel() {
		return apresentacao;
	}

	public HasClickHandlers getProgramacaoLabel() {
		return programacao;
	}
	
	public HasClickHandlers getLoginLabel() {
		return login;
	}

	public Widget asWidget() {
		return this;
	}
	
	public HorizontalPanel getHpUsuario(){
		return this.hpUsuario;
	}

	@Override
	public void setData(List<String> data) {
		// TODO Auto-generated method stub	
	}

	@Override
	public HasClickHandlers getAtividadesLabel() {
		// TODO Auto-generated method stub
		return atividades;
	}
	
	@Override
	public Anchor getSair() {
		return this.sair;
	}
	
	@Override
	public Label getNomeUsuario() {
		// TODO Auto-generated method stub
		return this.nomeUsuario;
	}
}
