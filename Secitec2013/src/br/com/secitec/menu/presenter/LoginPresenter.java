package br.com.secitec.menu.presenter;

import java.util.List;

import br.com.secitec.client.RPCServiceAsync;
import br.com.secitec.menu.event.LoginEvent;
import br.com.secitec.menu.view.UsuarioView;
import br.com.secitec.popup.CadastroPopup;
import br.com.secitec.popup.InformacaoPopup;
import br.com.secitec.shared.model.User;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class LoginPresenter implements Presenter {

	public interface Display {
		void setData(List<String> data);

		Widget asWidget();

		Anchor getCadastrar();

		Button getLogin();

		TextBox getTbLogin();

		PasswordTextBox getTbSenha();

		PopupPanel getPopup();
	}

	private final RPCServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	private InformacaoPopup ip;

	public LoginPresenter(RPCServiceAsync rpcService, HandlerManager eventBus, Display view) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
	}

	public void bind() {
		display.getLogin().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				login();
			}
		});
		
		display.getCadastrar().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				display.getPopup().hide();
				final CadastroPopup cadastro = new CadastroPopup(rpcService, eventBus);
				cadastro.getFechar().addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						cadastro.getTela().hide();
						//display.getPopup().center();
					}
				});
			}
		});
	}

	private void login(){
		User user = new User();

		TextBox login = display.getTbLogin();
		PasswordTextBox senha = display.getTbSenha();

		System.out.println("Email: "+login.getText());
		System.out.println("Senha: "+senha.getText());
		
		user.setEmail_partic(login.getText());
		user.setSenha_partic(senha.getText());

		if (!login.getText().equals("") && !senha.getText().equals("")) {
			rpcService.login(login.getText(), senha.getText(), new AsyncCallback<Integer>() {
						@Override
						public void onFailure(Throwable caught) {
						}
						@Override
						public void onSuccess(Integer b) {
							if (b == 2) {
								display.getPopup().hide();
								if (History.getToken().equals("login")) {
									Presenter presenter = new UsuarioPresenter(rpcService, eventBus, new UsuarioView());
									if (presenter != null)
										presenter.go(RootPanel.get("corpoEsq"));
								} else
									eventBus.fireEvent(new LoginEvent("login"));
							} else if(b==0){
								display.getPopup().hide();
								ip = new InformacaoPopup("Login ou senha inválidos!");
								ip.getTela().center();
								ClickHandler ch = new ClickHandler() {
									@Override
									public void onClick(ClickEvent event) {
										display.getPopup().center();
										display.getTbLogin().setText("");
										display.getTbSenha().setText("");
										display.getTbLogin().setFocus(true);
										ip.getTela().hide();
									}
								};
								ip.getOk().addClickHandler(ch);
								ip.getFechar().addClickHandler(ch);
								//LOGIN DANDO PROBLEMA
							}
							
							else if(b==1){
								display.getPopup().hide();
								ip = new InformacaoPopup("Você ainda não confirmou o seu cadastro. Caso não tenha recebido o e-mail" +
										", na tela de login, clique em reenviar e-mail para confirmação!");
								ip.getTela().center();
								ClickHandler ch = new ClickHandler() {
									@Override
									public void onClick(ClickEvent event) {
										display.getPopup().center();
										display.getTbLogin().setText("");
										display.getTbSenha().setText("");
										display.getTbLogin().setFocus(true);
										ip.getTela().hide();
									}
								};
								ip.getOk().addClickHandler(ch);
								ip.getFechar().addClickHandler(ch);
								//LOGIN DANDO PROBLEMA
							}
						}
					});
		} else {
			display.getPopup().hide();
			ip = new InformacaoPopup("Preencha os campos corretamente!");
			ip.getTela().center();
			ClickHandler ch = new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {									
					display.getPopup().center();
					display.getTbLogin().setText("");
					display.getTbSenha().setText("");
					display.getTbLogin().setFocus(true);
					ip.getTela().hide();
				}
			};
			ip.getOk().addClickHandler(ch);
			ip.getFechar().addClickHandler(ch);
		}
	}
	
	public void go(final HasWidgets container) {}

	@Override
	public void go() {
		bind();
		display.getPopup().center();
		display.getTbLogin().setFocus(true);
	}
}