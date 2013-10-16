package br.com.secitec.menu.view;

import br.com.secitec.menu.presenter.MenuLateralPresenter;

import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class MenuLateralView extends Composite implements MenuLateralPresenter.Display{

	private VerticalPanel vp;
	private Anchor login, minhasAtividades;
	
	public MenuLateralView(){
		vp = new VerticalPanel();
		initWidget(vp);
		vp.setBorderWidth(0);
		vp.setSpacing(0);
		vp.setVerticalAlignment(VerticalPanel.ALIGN_MIDDLE);
		vp.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		vp.setWidth("100%");
		vp.setHeight("100%");
		vp.addStyleName("vpLogin");
//		vp.addStyleName("vpMainPageContent");
		
		login = new Anchor("Login ou Cadastre-se");
//		login.removeStyleName("gwt-Anchor");
		login.setWidth("136px");
		login.addStyleName("loginCadastro");
		
		minhasAtividades = new Anchor("Minhas Atividades");
		minhasAtividades.addStyleName("loginCadastro");
		minhasAtividades.setWidth("110px");
		minhasAtividades.setVisible(false);
		
		vp.add(login);
		vp.add(minhasAtividades);
		
//		Image img = new Image();
//		img.setUrl("images/inscreva-se.png");
//		img.setTitle("inscreva-se");
//		img.setHeight("100px");
//		img.setWidth("100px");
//		
//		Button b = new Button();
//		inscrever = new Anchor();
//		inscrever.setHref("www.google.com.br");
//		inscrever.setLayoutData(img);
//		
//		FlowPanel fp = new FlowPanel();
//		fp.setWidth("auto");
//		fp.setHeight("auto");
//		//fp.add(img);
//		
//		vp.add(fp);
	}

	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public Anchor getLogin() {
		// TODO Auto-generated method stub
		return this.login;
	}

	@Override
	public Anchor getMinhasAtividades() {
		// TODO Auto-generated method stub
		return this.minhasAtividades;
	}

}
