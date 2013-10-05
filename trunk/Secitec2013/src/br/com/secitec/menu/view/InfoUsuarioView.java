package br.com.secitec.menu.view;

import java.util.List;

import br.com.secitec.menu.presenter.InfoUsuarioPresenter;

import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public class InfoUsuarioView extends Composite implements InfoUsuarioPresenter.Display{

	private Anchor sair;
	private HorizontalPanel menuHorizontal;
	private Label nomeUsuario;
	
	public InfoUsuarioView() {
		menuHorizontal = new HorizontalPanel();
		initWidget(menuHorizontal);
		
		sair = new Anchor("Sair");
		sair.addStyleName("aMenuDir");
		
		Label lb = new Label("Usuário:");
		lb.addStyleName("aMenuDir");
	
		nomeUsuario = new Label();
		nomeUsuario.addStyleName("nomeUsuario");
		
		menuHorizontal.add(lb);
		menuHorizontal.add(nomeUsuario);
		menuHorizontal.add(sair);
	}
	
	@Override
	public void setData(List<String> data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Label getLbNomeUsuario() {
		// TODO Auto-generated method stub
		return this.nomeUsuario;
	}

	@Override
	public Anchor getSair() {
		// TODO Auto-generated method stub
		return this.sair;
	}

}
