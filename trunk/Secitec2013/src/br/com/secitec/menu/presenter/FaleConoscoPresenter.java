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

public class FaleConoscoPresenter implements Presenter {

	public interface Display {
		void setData(List<String> data);

		Widget asWidget();
		
		Button getEnviar();

		TextBox getTbNome();
		
		TextBox getTbEmail();
		
		TextBox getTbAssunto();
		
		TextBox getTbMensagem();

		PopupPanel getPopup();
	}

	private final RPCServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	private InformacaoPopup ip;

	public FaleConoscoPresenter(RPCServiceAsync rpcService, HandlerManager eventBus, Display view) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
	}

	public void bind() {
		display.getEnviar().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				
				String nome = display.getTbNome().getText();
				String email = display.getTbEmail().getText();
				String assunto = display.getTbAssunto().getText();
				String mensagem = display.getTbMensagem().getText();


				/*if (!nome.equals("") && !email.equals("")&& !assunto.equals("")&& !mensagem.equals("")) {
					rpcService.login(user.getLogin_partic(), user.getSenha_partic(), new AsyncCallback<Boolean>() {
								@Override
								public void onFailure(Throwable caught) {
								}
								@Override
								public void onSuccess(Boolean b) {
									if (b) {
										display.getPopup().hide();
										if (History.getToken().equals("login")) {
											Presenter presenter = new UsuarioPresenter(rpcService, eventBus, new UsuarioView());
											if (presenter != null)
												presenter.go(RootPanel.get("corpoEsq"));
										} else
											eventBus.fireEvent(new LoginEvent("login"));
									} else {
										display.getPopup().hide();
										ip = new InformacaoPopup("Login ou senha inválidos!");
										ip.getTela().center();
										ClickHandler ch = new ClickHandler() {
											@Override
											public void onClick(ClickEvent event) {
												display.getPopup().center();
//												display.getTbLogin().setText("");
//												display.getTbSenha().setText("");
//												display.getTbLogin().setFocus(true);
												ip.getTela().hide();
											}
										};
										ip.getOk().addClickHandler(ch);
										ip.getFechar().addClickHandler(ch);
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
//							display.getTbLogin().setText("");
//							display.getTbSenha().setText("");
//							display.getTbLogin().setFocus(true);
							ip.getTela().hide();
						}
					};
					ip.getOk().addClickHandler(ch);
					ip.getFechar().addClickHandler(ch);
				}*/

			}
		});

	}

	public void go(final HasWidgets container) {}

	@Override
	public void go() {
		bind();
		display.getPopup().center();
		display.getTbNome().setFocus(true);
	}
}