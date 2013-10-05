package br.com.secitec.menu.view;

import java.util.List;

import br.com.secitec.menu.presenter.LoginPresenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class LoginView extends Composite implements
LoginPresenter.Display{

	private PopupPanel tela;
	private VerticalPanel vp;
	private FlexTable tabela;
	private Button login;
	private Anchor cadastro;
	
	public LoginView() {
		tela = new PopupPanel(true);
		tela.setStyleName("demo-popup");

		vp = new VerticalPanel();
		vp.setBorderWidth(0);
		vp.setSpacing(0);
		vp.setVerticalAlignment(VerticalPanel.ALIGN_MIDDLE);
		vp.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		vp.setWidth("400px");

		HorizontalPanel hpTop = new HorizontalPanel();
		hpTop.setSpacing(0);
		hpTop.setWidth("300px");
		hpTop.setHeight("30px");
		hpTop.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		
		HTML titulo = new HTML("Login");
		titulo.addStyleName("titulo");
		hpTop.add(titulo);
		
		HorizontalPanel hpFechar = new HorizontalPanel();
		hpFechar.setSpacing(0);
		hpFechar.setWidth("20px");
		hpFechar.addStyleName("botaoFecharLogin");
		hpFechar.setHorizontalAlignment(HorizontalPanel.ALIGN_RIGHT);
		
		Image imgFechar = new Image();
		imgFechar.setUrl("images/fechar.png");
		imgFechar.setSize("20px", "20px");
		imgFechar.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				tela.hide();
			}
		});
		hpFechar.add(imgFechar);
		hpTop.add(hpFechar);
		
		tabela = new FlexTable();
		tabela = tabela(tabela);
		preencheTabela(tabela);
		
		HorizontalPanel hpRodape = new HorizontalPanel();
		hpRodape.setHeight("40px");
		hpRodape.setWidth("300px");
		hpRodape.setSpacing(0);
		hpRodape.setHorizontalAlignment(HorizontalPanel.ALIGN_RIGHT);
		
		HorizontalPanel hpLogin = new HorizontalPanel();
		hpLogin.setSpacing(0);
		hpLogin.setHeight("40px");
		
		login = new Button();
		login.setText("Login");
		login.setHeight("40px");
		
		hpLogin.add(login);
		
		HorizontalPanel hpCadastrar = new HorizontalPanel();
		hpCadastrar.addStyleName("botaoCadastrar");
		hpCadastrar.setSpacing(8);
		hpCadastrar.setHeight("40px");
		
		cadastro = new Anchor();
		cadastro.setText("Cadastre-se");
		cadastro.setHeight("40px");

		hpCadastrar.add(new HTML("ou"));
		hpCadastrar.add(cadastro);
		
		hpRodape.add(hpLogin);
		hpRodape.add(hpCadastrar);
		
		vp.add(hpTop);
		vp.add(tabela);
		vp.add(hpRodape);
		tela.add(vp);
		tela.setGlassEnabled(true);
	}

	private void preencheTabela(FlexTable tb) {
		HTML hLogin = new HTML("Login: ");
		hLogin.addStyleName("alignDir");
		tb.setWidget(0, 0, hLogin);
		TextBox login = new TextBox();
		login.setWidth("140px");
		tb.setWidget(0, 1, login);

		HTML hSenha = new HTML("Senha: ");
		hSenha.addStyleName("alignDir");
		tb.setWidget(1, 0, hSenha);
		PasswordTextBox senha = new PasswordTextBox();
		senha.setWidth("140px");
		tb.setWidget(1, 1, senha);
	}

	private FlexTable tabela(FlexTable tb) {
		tb.setWidth("200px");
		tb.setHeight("80px");
		tb.getColumnFormatter().setWidth(0, "50px");
//		tb.getColumnFormatter().addStyleName(0, "alignDir");
		tb.getColumnFormatter().setWidth(1, "140px");
		tb.setBorderWidth(0);
		tb.addStyleName("tabelaLogin");

		return tb;
	}

//	public void addEventoLogin(Button bt) {
//		bt.addClickHandler(new ClickHandler() {
//
//			@Override
//			public void onClick(ClickEvent event) {
//				// TODO Auto-generated method stub
//				user = new User();
//
//				TextBox login = (TextBox) tabela.getWidget(0, 1);
//				PasswordTextBox senha = (PasswordTextBox) tabela
//						.getWidget(1, 1);
//
//				user.setLogin_partic(login.getText());
//				user.setSenha_partic(senha.getText());
//
////				rpcService.login(user.getLogin_partic(),
////						user.getSenha_partic(), new AsyncCallback<Boolean>() {
////
////							@Override
////							public void onFailure(Throwable caught) {
////								// TODO Auto-generated method stub
////
////							}
////
////							@Override
////							public void onSuccess(Boolean b) {
////								// TODO Auto-generated method stub
////							}
////						});
//
//			}
//		});
//	}

//	public void addEventoCadastro(Button bt) {
//		bt.addClickHandler(new ClickHandler() {
//
//			@Override
//			public void onClick(ClickEvent event) {
//				// TODO Auto-generated method stub
//				tela.hide();
//				//CadastroPopup popup = new CadastroPopup();
//			}
//		});
//	}
	
	public Anchor getCadastrar(){
		return this.cadastro;
	}

	public Button getLogin(){
		return this.login;
	}
	
	@Override
	public void setData(List<String> data) {
		// TODO Auto-generated method stub
		
	}
	
	public TextBox getTbLogin(){
		return (TextBox) tabela.getWidget(0, 1);
	}
	
	public PasswordTextBox getTbSenha(){
		return (PasswordTextBox) tabela
				.getWidget(1, 1);
	}
	
	public PopupPanel getPopup(){
		return this.tela;
	}
}