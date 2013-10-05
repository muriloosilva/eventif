package br.com.secitec.menu.view;

import java.util.List;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

import br.com.secitec.menu.presenter.MenuPresenter;;

public class MenuView extends Composite implements MenuPresenter.Display {
	private Anchor apresentacao;
	private Anchor programacao;
	private Anchor login;
	private Anchor atividades;
	private HorizontalPanel menuHorizontal;
	
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
		
		menuHorizontal.add(apresentacao);
		menuHorizontal.add(programacao);
		menuHorizontal.add(atividades);
		menuHorizontal.add(login);
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

	@Override
	public void setData(List<String> data) {
		// TODO Auto-generated method stub	
	}

	@Override
	public HasClickHandlers getAtividadesLabel() {
		// TODO Auto-generated method stub
		return atividades;
	}
}
