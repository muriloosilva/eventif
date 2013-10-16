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
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
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

public class RecuperarSenhaPopup {

	private PopupPanel tela;
	private VerticalPanel vp;
	private FlexTable tabela;
	private Button recuperar;
	private User user;
	private final RPCServiceAsync rpcService;
	private final HandlerManager eventBus;
	private InformacaoPopup ip;
	private TextBox cpf;
	private TextBox email;
	private Image imgFechar;
	private HTML vCpf, vEmail;
	private VerticalPanel vpCpf, vpEmail;

	public RecuperarSenhaPopup(RPCServiceAsync rpcService, HandlerManager eventBus) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		montaTela();
		addEvento(recuperar);
	}

	private void montaTela() {
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
		
		HTML titulo = new HTML("Recuperar Senha");
		titulo.addStyleName("titulo");
		hpTitulo.add(titulo);
		
		imgFechar = new Image();
		imgFechar.setUrl("images/fechar.png");
		imgFechar.setSize("20px", "20px");
//		imgFechar.addClickHandler(new ClickHandler() {
//			
//			@Override
//			public void onClick(ClickEvent event) {
//				tela.hide();
//			}
//		});
		hpTop.add(imgFechar);
		
		
		
//		HTML titulo = new HTML("Recuperar Senha");
//		titulo.addStyleName("titulo");
//		hpTop.add(titulo);
//
//		HorizontalPanel hpFechar = new HorizontalPanel();
//		hpFechar.setSpacing(0);
//		hpFechar.setWidth("20px");
////		hpFechar.addStyleName("botaoFecharCadastro");
//		hpFechar.setHorizontalAlignment(HorizontalPanel.ALIGN_RIGHT);
//
//		imgFechar = new Image();
//		imgFechar.setUrl("images/fechar.png");
//		imgFechar.setSize("20px", "20px");
//
//		hpFechar.add(imgFechar);
//		hpTop.add(hpFechar);

		tabela = new FlexTable();
		tabela = tabela(tabela);
		preencheTabela(tabela);

		HorizontalPanel hpRodape = new HorizontalPanel();
		hpRodape.addStyleName("btCadastrar");

		recuperar = new Button();
		recuperar.setText("Recuperar");
		hpRodape.add(recuperar);

		vp.add(hpTop);
		vp.add(hpTitulo);
		vp.add(tabela);
		vp.add(hpRodape);
		tela.add(vp);
		tela.setGlassEnabled(true);
		tela.center();
		cpf.setFocus(true);
	}

	private void preencheTabela(FlexTable tb) {
		
		vpCpf = new VerticalPanel();
		vpCpf.setWidth("260px");

		HTML h1 = new HTML("CPF:");
		h1.addStyleName("alignDir");
		tb.setWidget(0, 0, h1);
		
		cpf = new TextBox();
		cpf.setMaxLength(14);
		setCpf();
		cpf.setWidth("260px");		
//		tb.setWidget(1, 1, cpf);
		
		vCpf = new HTML("*CPF inválido!");
		vCpf.setVisible(false);
		vCpf.addStyleName("vCPF");
//		vCpf.setWidth("100px");
//		tb.setWidget(1, 2, vCpf);
		
		vpCpf.add(vCpf);
		vpCpf.add(cpf);
		tb.setWidget(0, 1, vpCpf);

		vpEmail = new VerticalPanel();
		vpEmail.setWidth("260px");
		
		HTML h2 = new HTML("Email:");
		h2.addStyleName("alignDir");
		tb.setWidget(1, 0, h2);
		
		email = new TextBox();
		email.setWidth("260px");
		setEmail();
//		tb.setWidget(2, 1, email);

		vEmail = new HTML("*Email inválido!");
		vEmail.setVisible(false);
		vEmail.addStyleName("vCPF");
		
		vpEmail.add(vEmail);
		vpEmail.add(email);
		tb.setWidget(1, 1, vpEmail);
		
	}

	private FlexTable tabela(FlexTable tb) {
		tb.setWidth("300px");
		tb.setHeight("80px");
		tb.setBorderWidth(0);
		tb.addStyleName("tabelaRecuperarSenha");

		return tb;
	}

	public Image getFechar() {
		return this.imgFechar;
	}

	public PopupPanel getTela() {
		return this.tela;
	}

	private boolean verificaCampos() {
		if (cpf.getText().equals("")
				|| email.getText().equals("")){
			return false;
		}
		return true;
	}

	private void addEvento(Button bt) {

		bt.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (verificaCampos()) {
					user = new User();

					user.setCpf_partic(cpf.getText());
					user.setEmail_partic(email.getText());

					tela.hide();
					final LoadingPopup pp = new LoadingPopup("Recuperando senha, aguarde...");
					rpcService.recuperarSenha(user,
							new AsyncCallback<Boolean>() {
								@Override
								public void onSuccess(Boolean result) {
									if (result) {
										tela.hide();
										pp.hide();
										ip = new InformacaoPopup(
												"Foi enviado para o seu e-mail a sua senha. Se não encontrar, verifique em sua caixa de SPAM ou no LIXO de se e-mail.");
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
										//tela.hide();
										pp.hide();
										ip = new InformacaoPopup(
												"Não foi possível recuperar sua senha. Verifique se o CPF e o email digitado são os mesmos cadastrados no sistema");
										ip.getTela().center();
										ClickHandler ch = new ClickHandler() {
											@Override
											public void onClick(ClickEvent event) {
												ip.getTela().hide();
												tela.center();
												cpf.setText("");
												cpf.setFocus(true);
											}
										};
										ip.getOk().addClickHandler(ch);
										ip.getFechar().addClickHandler(ch);
									}
								}

								@Override
								public void onFailure(Throwable caught) {
									pp.hide();
									Window.alert("Erro: " + caught.getMessage());
								}
							});
				} else {
					//tela.hide();
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

	private void camposVazios() {
		if (cpf.getText().equals(""))
			cpf.setFocus(true);
		else if (email.getText().equals(""))
			email.setFocus(true);
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
		cpf.addBlurHandler(new BlurHandler() {
			@Override
			public void onBlur(BlurEvent event) {
				if(!CPF.isCPF(cpf.getValue()) && !cpf.getValue().equals("")){
					vCpf.setText("*CPF inválido!");
					vCpf.setVisible(true);
					cpf.setText("");
					cpf.setFocus(true);
				}
				else{
					vCpf.setVisible(false);
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
	
	private void setEmail(){
		email.addBlurHandler(new BlurHandler() {
			
			@Override
			public void onBlur(BlurEvent event) {
				if(!verificaEmail(email.getValue()) && !email.getValue().equals("")){
					vEmail.setText("*Email inválido!");
					vEmail.setVisible(true);
					email.setText("");
					email.setFocus(true);
				}
				else {
					vEmail.setVisible(false);
				}
//				if(!verificaEmail(tbEmail.getValue()) && !tbEmail.getValue().equals("")){
//					vEmail.setVisible(true);
//					email.setSelectionRange(0, tbEmail.getValue().length());
//				}else
//					vEmail.setVisible(false);
			}
		});
		
		email.addKeyUpHandler(new KeyUpHandler() {
			
			@Override
			public void onKeyUp(KeyUpEvent event) {
				if(event.getNativeEvent().getKeyCode() != 9)
					email.setValue(email.getValue().toLowerCase());				
			}
		});
	}
	
	private boolean verificaEmail(String email){
		if(!email.matches("^([a-zA-Z0-9_.\\-+])+@(([a-zA-Z0-9\\-])+\\.)+[a-zA-Z0-9]{2,4}$"))
			return false;
		else
			return true;
	}
	
}