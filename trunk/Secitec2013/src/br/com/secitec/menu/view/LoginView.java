package br.com.secitec.menu.view;

import java.util.List;

import br.com.secitec.menu.presenter.LoginPresenter;
import br.com.secitec.popup.CPF;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
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
import com.google.gwt.user.client.ui.HTMLTable.CellFormatter;

public class LoginView extends Composite implements
LoginPresenter.Display{

	private PopupPanel tela;
	private VerticalPanel vp;
	private FlexTable tabela;
	private Button login;
	private Anchor cadastro;
	private Anchor esqueceuSenha;
	private TextBox cpf;
	
	public LoginView() {
		tela = new PopupPanel(false);
		tela.setStyleName("demo-popup");

		vp = new VerticalPanel();
		vp.setBorderWidth(0);
		vp.setSpacing(0);
		vp.setVerticalAlignment(VerticalPanel.ALIGN_MIDDLE);
		vp.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		vp.setWidth("400px");

		HorizontalPanel hpTop = new HorizontalPanel();
		hpTop.setSpacing(0);
		hpTop.setWidth("400px");
		hpTop.setHeight("10px");
		hpTop.setHorizontalAlignment(HorizontalPanel.ALIGN_RIGHT);
		hpTop.setVerticalAlignment(VerticalPanel.ALIGN_TOP);
		
		HorizontalPanel hpTitulo = new HorizontalPanel();
		hpTitulo.setSpacing(0);
		hpTitulo.setWidth("400px");
		hpTitulo.setHeight("40px");
		hpTitulo.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		hpTitulo.setVerticalAlignment(VerticalPanel.ALIGN_MIDDLE);
		
		HTML titulo = new HTML("Login");
		titulo.addStyleName("titulo");
		hpTitulo.add(titulo);
		
		Image imgFechar = new Image();
		imgFechar.setUrl("images/fechar.png");
		imgFechar.setSize("20px", "20px");
		imgFechar.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				tela.hide();
			}
		});
		hpTop.add(imgFechar);
		
		tabela = new FlexTable();
		tabela = tabela(tabela);
		preencheTabela(tabela);
		
//		HorizontalPanel hpRodape = new HorizontalPanel();
//		hpRodape.setHeight("40px");
//		hpRodape.setWidth("300px");
//		hpRodape.setSpacing(0);
//		hpRodape.setHorizontalAlignment(HorizontalPanel.ALIGN_RIGHT);
//		
//		HorizontalPanel hpLogin = new HorizontalPanel();
//		hpLogin.setSpacing(0);
//		hpLogin.setHeight("40px");
//		
//		login = new Button();
//		login.setText("Login");
//		login.setHeight("40px");
//		
//		hpLogin.add(login);
//		
//		HorizontalPanel hpCadastrar = new HorizontalPanel();
//		hpCadastrar.addStyleName("botaoCadastrar");
//		hpCadastrar.setSpacing(8);
//		hpCadastrar.setHeight("40px");
//		
//		cadastro = new Anchor();
//		cadastro.setText("Cadastre-se");
//		cadastro.setHeight("40px");
//
//		hpCadastrar.add(new HTML("ou"));
//		hpCadastrar.add(cadastro);
//		
//		hpRodape.add(hpLogin);
//		hpRodape.add(hpCadastrar);
		
		vp.add(hpTop);
		vp.add(hpTitulo);
		vp.add(tabela);
//		vp.add(hpRodape);
		tela.add(vp);
		tela.setGlassEnabled(true);
	}

	private void preencheTabela(FlexTable tb) {
		HTML hCPF = new HTML("CPF: ");
		hCPF.addStyleName("alignDir");
		tb.setWidget(0, 0, hCPF);
		cpf = new TextBox();
		cpf.setWidth("250px");
		setCpf();
		tb.setWidget(0, 1, cpf);

		HTML hSenha = new HTML("Senha: ");
		hSenha.addStyleName("alignDir");
		tb.setWidget(1, 0, hSenha);
		tb.getRowFormatter().getElement(1).addClassName("passwd");
		
		PasswordTextBox senha = new PasswordTextBox();
		senha.setWidth("250px");
		//tb.setWidget(1, 1, senha);
		
		VerticalPanel vpEsqueceuSenha = new VerticalPanel();
		vpEsqueceuSenha.setSpacing(0);
		vpEsqueceuSenha.setHeight("20px");
		vpEsqueceuSenha.setVerticalAlignment(VerticalPanel.ALIGN_BOTTOM);
		
		vpEsqueceuSenha.add(senha);
		
		HorizontalPanel hpEsqueceuSenha = new HorizontalPanel();
		hpEsqueceuSenha.setHorizontalAlignment(HorizontalPanel.ALIGN_RIGHT);
		hpEsqueceuSenha.setVerticalAlignment(VerticalPanel.ALIGN_TOP);
		hpEsqueceuSenha.setWidth("264px");
		
		esqueceuSenha = new Anchor();
		esqueceuSenha.setText("esqueceu sua senha?");
		esqueceuSenha.setHeight("80px");

		hpEsqueceuSenha.add(esqueceuSenha);
		vpEsqueceuSenha.add(hpEsqueceuSenha);
		
		tb.setWidget(1, 1, vpEsqueceuSenha);
		
		
		
		HorizontalPanel hpAction = new HorizontalPanel();
		hpAction.setHorizontalAlignment(HorizontalPanel.ALIGN_RIGHT);
		hpAction.setVerticalAlignment(VerticalPanel.ALIGN_MIDDLE);
		hpAction.setHeight("50px");
		hpAction.setWidth("200px");
		hpAction.setSpacing(10);
		
		this.login = new Button();
		this.login.setText("Login");
		this.login.setHeight("40px");
		
		hpAction.add(this.login);
		
		HTML ou = new HTML("ou");
		ou.setWidth("40px");
		
		hpAction.add(ou);
		
		cadastro = new Anchor();
		cadastro.setText("Cadastre-se");
		cadastro.setHeight("40px");
		cadastro.setWidth("80px");

		hpAction.add(cadastro);
		
		tb.setWidget(2, 1, hpAction);
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
	
	public Anchor getRecuperarSenha(){
		return this.esqueceuSenha;
	}
	
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
	
	public TextBox getTbCPF(){
		return (TextBox)tabela.getWidget(0, 1);
	}
	
	public PasswordTextBox getTbSenha(){
		return (PasswordTextBox) ((VerticalPanel)tabela.getWidget(1, 1)).getWidget(0);
	}
	
	public PopupPanel getPopup(){
		return this.tela;
	}
	
	private void setCpf() {
		cpf.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				System.out.println("teclado: " + event.getNativeEvent().getKeyCode());
				if (verificaTecla(event)) {
					if (event.getNativeEvent().getKeyCode() != 8) {
						if (cpf.getValue().length() == 3
								|| cpf.getValue().length() == 7) {
							cpf.setValue(cpf.getValue() + ".");
						}
						if (cpf.getValue().length() == 11) {
							cpf.setValue(cpf.getValue() + "-");
						}
					}
				} else {
					cpf.cancelKey();
				}
			}
		});
	}

	private boolean verificaTecla(KeyPressEvent event) {
		if (event.getCharCode() == 48 || event.getCharCode() == 49
				|| event.getCharCode() == 50 || event.getCharCode() == 51
				|| event.getCharCode() == 52 || event.getCharCode() == 53
				|| event.getCharCode() == 54 || event.getCharCode() == 55
				|| event.getCharCode() == 56 || event.getCharCode() == 57
				|| event.getNativeEvent().getKeyCode() == 8) {
			return true;
		} else
			return false;
	}
}