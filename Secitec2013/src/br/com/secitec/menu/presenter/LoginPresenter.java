package br.com.secitec.menu.presenter;

import java.util.List;

import br.com.secitec.client.RPCServiceAsync;
import br.com.secitec.menu.event.LoginEvent;
import br.com.secitec.menu.view.UsuarioView;
import br.com.secitec.popup.CadastroPopup;
import br.com.secitec.popup.InformacaoPopup;
import br.com.secitec.popup.LoadingPopup;
import br.com.secitec.popup.RecuperarSenhaPopup;
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
		
		Anchor getRecuperarSenha();

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
		
		//LOGIN DANDO PROBLEMA
		
		display.getRecuperarSenha().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				display.getPopup().hide();
				final RecuperarSenhaPopup recuperarSenhaPopup = new RecuperarSenhaPopup(rpcService, eventBus);
				recuperarSenhaPopup.getFechar().addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						recuperarSenhaPopup.getTela().hide();
						//display.getPopup().center();
					}
				});
				
			}
		});
		
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
		final User user = new User();

		TextBox login = display.getTbLogin();
		PasswordTextBox senha = display.getTbSenha();

		System.out.println("Email: "+login.getText());
		System.out.println("Senha: "+senha.getText());
		
		user.setEmail_partic(login.getText());
		user.setSenha_partic(senha.getText());

		if (!login.getText().equals("") && !senha.getText().equals("")) {
			
			final LoadingPopup pp = new LoadingPopup("Aguarde ...");
			
			rpcService.login(login.getText(), senha.getText(), new AsyncCallback<Integer>() {
						@Override
						public void onFailure(Throwable caught) {
							pp.hide();
							display.getPopup().hide();
							ip = new InformacaoPopup("Houve um erro na sua solicitação. Tente novamente.");
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
						@Override
						public void onSuccess(Integer b) {
							pp.hide();
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
								
								Anchor a = new Anchor("clique aqui");
								
								ip = new InformacaoPopup("Você ainda não confirmou o seu cadastro. Caso não tenha recebido o e-mail clique no link abaixo");
								ip.setOtherWidget(a);
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
								
								a.addClickHandler(new ClickHandler() {
									
									@Override
									public void onClick(ClickEvent event) {
										ip.getTela().hide();
										final LoadingPopup pp = new LoadingPopup("Aguarde ...");
										rpcService.reenviaConfirmacaoCadastro(user, new AsyncCallback<Boolean>() {
											@Override
											public void onSuccess(Boolean result) {
												pp.hide();
												if(result){
													ip = new InformacaoPopup("Foi reenviado para o seu e-mail a confirmação de cadastro. Verifique em" +
															" sua caixa de SPAM ou no LIXO.");
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
												else{
													ip = new InformacaoPopup("Não foi possível reenviar o e-mail para confirmação de cadastro. Tente" +
															" mais tarde.");
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
											@Override
											public void onFailure(
													Throwable caught) {
												pp.hide();
												ip = new InformacaoPopup("Não foi possível reenviar o e-mail para confirmação de cadastro. Tente" +
														" mais tarde.");
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
										});
										
									}
								});
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