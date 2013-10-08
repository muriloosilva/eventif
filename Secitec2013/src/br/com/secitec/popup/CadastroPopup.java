package br.com.secitec.popup;

import br.com.secitec.client.RPCServiceAsync;
import br.com.secitec.menu.presenter.LoginPresenter;
import br.com.secitec.menu.presenter.Presenter;
import br.com.secitec.menu.view.LoginView;
import br.com.secitec.shared.model.User;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.TextBoxBase;
import com.google.gwt.user.client.ui.VerticalPanel;

public class CadastroPopup {

	private PopupPanel tela;
	private VerticalPanel vp;
	private FlexTable tabela;
	private Button cadastrar;
	private User user;
	private final RPCServiceAsync rpcService;
	private final HandlerManager eventBus;
	private InformacaoPopup ip;
	private TextBox nome;
	private TextBox cpf;
	private TextBox email;
	private TextBox login;
	private TextBox matricula;
	private PasswordTextBox senha;
	private Image imgFechar;

	public CadastroPopup(RPCServiceAsync rpcService, HandlerManager eventBus) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		montaTela();
		addEvento(cadastrar);
	}

	public void montaTela() {
		tela = new PopupPanel(true);
		tela.setStyleName("demo-popup");

		vp = new VerticalPanel();
		vp.setBorderWidth(0);
		vp.setSpacing(0);
		vp.setVerticalAlignment(VerticalPanel.ALIGN_MIDDLE);
		vp.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		vp.setWidth("500px");

		HorizontalPanel hpTop = new HorizontalPanel();
		hpTop.setSpacing(0);
		hpTop.setWidth("300px");
		hpTop.setHeight("30px");
		hpTop.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);

		HTML titulo = new HTML("Cadastro");
		titulo.addStyleName("titulo");
		hpTop.add(titulo);

		HorizontalPanel hpFechar = new HorizontalPanel();
		hpFechar.setSpacing(0);
		hpFechar.setWidth("20px");
		hpFechar.addStyleName("botaoFecharCadastro");
		hpFechar.setHorizontalAlignment(HorizontalPanel.ALIGN_RIGHT);

		imgFechar = new Image();
		imgFechar.setUrl("images/fechar.png");
		imgFechar.setSize("20px", "20px");

		hpFechar.add(imgFechar);
		hpTop.add(hpFechar);

		tabela = new FlexTable();
		tabela = tabela(tabela);
		preencheTabela(tabela);

		HorizontalPanel hpRodape = new HorizontalPanel();
		hpRodape.addStyleName("btCadastrar");

		cadastrar = new Button();
		cadastrar.setText("Cadastrar");
		hpRodape.add(cadastrar);

		vp.add(hpTop);
		vp.add(tabela);
		vp.add(hpRodape);
		tela.add(vp);
		tela.setGlassEnabled(true);
		tela.center();
		nome.setFocus(true);
	}

	private void preencheTabela(FlexTable tb) {
		HTML h = new HTML("Nome:");
		h.addStyleName("alignDir");
		tb.setWidget(0, 0, h);
		nome = new TextBox();
		nome.setWidth("260px");
		tb.setWidget(0, 1, nome);

		HTML h1 = new HTML("CPF:");
		h1.addStyleName("alignDir");
		tb.setWidget(1, 0, h1);
		cpf = new TextBox();
		cpf.setMaxLength(14);
		setCpf(cpf);
		cpf.setWidth("260px");
		tb.setWidget(1, 1, cpf);

		HTML h2 = new HTML("Email:");
		h2.addStyleName("alignDir");
		tb.setWidget(2, 0, h2);
		email = new TextBox();
		email.setWidth("260px");
		tb.setWidget(2, 1, email);

		HTML h3 = new HTML("Login:");
		h3.addStyleName("alignDir");
		tb.setWidget(3, 0, h3);
		login = new TextBox();
		login.setWidth("260px");
		tb.setWidget(3, 1, login);

		HTML h4 = new HTML("Senha:");
		h4.addStyleName("alignDir");
		tb.setWidget(4, 0, h4);
		senha = new PasswordTextBox();
		senha.setWidth("260px");
		tb.setWidget(4, 1, senha);

		HTML h5 = new HTML("Matrícula:");
		h5.addStyleName("alignDir");
		tb.setWidget(5, 0, h5);
		matricula = new TextBox();
		matricula.setWidth("260px");
		tb.setWidget(5, 1, matricula);
	}

	private FlexTable tabela(FlexTable tb) {
		tb.setWidth("400px");
		tb.setHeight("80px");
		tb.getColumnFormatter().setWidth(0, "50px");
		tb.getColumnFormatter().setWidth(1, "160px");
		tb.setBorderWidth(0);
		tb.addStyleName("tabelaCadastro");

		return tb;
	}

	public Image getFechar() {
		return this.imgFechar;
	}

	public PopupPanel getTela() {
		return this.tela;
	}

	public boolean verificaCampos() {
		if (nome.getText().equals("") || cpf.getText().equals("")
				|| email.getText().equals("") || login.getText().equals("")
				|| senha.getText().equals("")) {
			return false;
		}
		return true;
	}

	public void addEvento(Button bt) {

		bt.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (verificaCampos()) {
					user = new User();

					user.setNome_partic(nome.getText());
					user.setCpf_partic(cpf.getText());
					user.setEmail_partic(email.getText());
					user.setLogin_partic(login.getText());
					user.setSenha_partic(senha.getText());
					if (!matricula.getText().equals(null))
						user.setMatr_aluno_partic(matricula.getText());

					rpcService.cadastraUsuario(user,
							new AsyncCallback<Boolean>() {
								@Override
								public void onSuccess(Boolean result) {
									if (result) {
										tela.hide();
										ip = new InformacaoPopup(
												"Cadastro realizado com sucesso! Efetue login.");
										ip.getTela().center();
										ClickHandler ch = new ClickHandler() {
											@Override
											public void onClick(ClickEvent event) {
												ip.getTela().hide();
												// tela.hide();
												Presenter presenter = new LoginPresenter(
														rpcService, eventBus,
														new LoginView());
												if (presenter != null)
													presenter.go();
											}
										};
										ip.getOk().addClickHandler(ch);
										ip.getFechar().addClickHandler(ch);
									} else {
										tela.hide();
										ip = new InformacaoPopup(
												"Login indisponível! Tente outro.");
										ip.getTela().center();
										ClickHandler ch = new ClickHandler() {
											@Override
											public void onClick(ClickEvent event) {
												ip.getTela().hide();
												tela.center();
												login.setText("");
												login.setFocus(true);
											}
										};
										ip.getOk().addClickHandler(ch);
										ip.getFechar().addClickHandler(ch);
									}
								}

								@Override
								public void onFailure(Throwable caught) {
									Window.alert("Erro: " + caught.getMessage());
								}
							});
				} else {
					tela.hide();
					ip = new InformacaoPopup("Preencha os campos corretamente!");
					ip.getTela().center();
					ClickHandler ch = new ClickHandler() {
						@Override
						public void onClick(ClickEvent event) {
							ip.getTela().hide();
							tela.center();
							camposVazios();
						}
					};
					ip.getOk().addClickHandler(ch);
					ip.getFechar().addClickHandler(ch);
				}

			}
		});
	}

	public void camposVazios() {
		if (nome.getText().equals(""))
			nome.setFocus(true);
		else if (cpf.getText().equals(""))
			cpf.setFocus(true);
		else if (email.getText().equals(""))
			email.setFocus(true);
		else if (login.getText().equals(""))
			login.setFocus(true);
		else
			senha.setFocus(true);
	}

	public void setCpf(final TextBoxBase cpfText) {
		cpfText.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				System.out.println("teclado: " + event.getNativeEvent().getKeyCode());
				if (verificaTecla(event)) {
					if (event.getNativeEvent().getKeyCode() != 8) {
						if (cpfText.getValue().length() == 3
								|| cpfText.getValue().length() == 7) {
							cpfText.setValue(cpfText.getValue() + ".");
						}
						if (cpfText.getValue().length() == 11) {
							cpfText.setValue(cpfText.getValue() + "-");
						}
					}
				} else {
					cpfText.cancelKey();
				}
			}
		});
		cpfText.addBlurHandler(new BlurHandler() {
			@Override
			public void onBlur(BlurEvent event) {
				if(!CPF.isCPF(cpfText.getValue())){
					tela.hide();
					ip = new InformacaoPopup("CPF inv�lido!");
					ip.getTela().center();
					ClickHandler ch = new ClickHandler() {
						
						@Override
						public void onClick(ClickEvent event) {
							ip.getTela().hide();
							tela.center();
							cpf.setSelectionRange(0, 14);
						}
					};
					ip.getOk().addClickHandler(ch);
					ip.getFechar().addClickHandler(ch);
				}
			}
		});
	}

	public boolean verificaTecla(KeyPressEvent event) {
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