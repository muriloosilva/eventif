package br.com.secitec.popup;

import br.com.secitec.client.RPCServiceAsync;
import br.com.secitec.shared.model.User;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class LoginPopup {
	private PopupPanel tela;
	private VerticalPanel vp;
	private FlexTable tabela;
	private Button login, cadastro;
	private User user;
	private final RPCServiceAsync rpcService;

	public LoginPopup(RPCServiceAsync rpcService) {
		this.rpcService = rpcService;
		montaTela();
		addEventoLogin(login);
		addEventoCadastro(cadastro);
	}

	public void montaTela() {
		tela = new PopupPanel(true);
		tela.setStyleName("demo-popup");

		vp = new VerticalPanel();
		vp.setBorderWidth(2);
		vp.setSpacing(2);
		vp.setVerticalAlignment(VerticalPanel.ALIGN_MIDDLE);
		vp.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		vp.setWidth("1000px");
		vp.setHeight("680px");
		vp.addStyleName("vpMainPageContent");

		tabela = new FlexTable();
		tabela = tabela(tabela);
		preencheTabela(tabela);

		login = new Button();
		login.setText("Login");

		cadastro = new Button();
		cadastro.setText("Cadastro");

		vp.add(tabela);
		vp.add(cadastro);
		vp.add(login);
		tela.add(vp);
		tela.setGlassEnabled(true);
		tela.center();
	}

	private void preencheTabela(FlexTable tb) {
		tb.setWidget(0, 0, new HTML("Login: "));
		TextBox login = new TextBox();
		login.setFocus(true);
		login.setWidth("80%");
		tb.setWidget(0, 1, login);

		tb.setWidget(1, 0, new HTML("Senha: "));
		PasswordTextBox senha = new PasswordTextBox();
		senha.setWidth("80%");
		tb.setWidget(1, 1, senha);
	}

	private FlexTable tabela(FlexTable tb) {
		tb.setCellSpacing(0);
		tb.setCellPadding(0);
		tb.setWidth("80%");
		tb.getColumnFormatter().setWidth(0, "50%");
		tb.getColumnFormatter().setWidth(1, "50%");
		tb.setBorderWidth(1);

		return tb;
	}

	public void addEventoLogin(Button bt) {
		bt.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				user = new User();

				TextBox login = (TextBox) tabela.getWidget(0, 1);
				PasswordTextBox senha = (PasswordTextBox) tabela
						.getWidget(1, 1);

				user.setLogin_partic(login.getText());
				user.setSenha_partic(senha.getText());

				rpcService.login(user.getLogin_partic(),
						user.getSenha_partic(), new AsyncCallback<Boolean>() {

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onSuccess(Boolean b) {
								// TODO Auto-generated method stub
							}
						});

			}
		});
	}

	public void addEventoCadastro(Button bt) {
		bt.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				tela.hide();
			}
		});
	}
}