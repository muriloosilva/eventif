package br.com.secitec.popup;

import br.com.secitec.client.RPCServiceAsync;
import br.com.secitec.menu.event.ApresentationEvent;
import br.com.secitec.menu.presenter.LoginPresenter;
import br.com.secitec.menu.presenter.Presenter;
import br.com.secitec.menu.view.LoginView;
import br.com.secitec.shared.model.User;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
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

public class VisualizarAlterarDadosPopup {

	private PopupPanel tela;
	private VerticalPanel vp;
	private FlexTable tabela;
	private Button alterarDados, ok;
	private User user;
	private final RPCServiceAsync rpcService;
	private final HandlerManager eventBus;
	private InformacaoPopup ip;
	private TextBox nome;
	private TextBox cpf;
	private TextBox email;
	private TextBox matricula;
	private PasswordTextBox senha;
	private Image imgFechar;
	private HTML vCpf, vEmail, vMatricula;
	private VerticalPanel vpCpf, vpEmail, vpMatricula;

	public VisualizarAlterarDadosPopup(RPCServiceAsync rpcService, HandlerManager eventBus) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		montaTela();
		addEvento(alterarDados);
		carregaDados();
	}

	private void montaTela() {
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

		HTML titulo = new HTML("Visualizar Dados");
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
		hpRodape.setSpacing(10);
		hpRodape.addStyleName("btCadastrar");
		hpRodape.setWidth("200px");

		alterarDados = new Button();
		alterarDados.setText("Editar Dados");
		hpRodape.add(alterarDados);
		
		ok = new Button();
		ok.setText("Fechar");
		hpRodape.add(ok);

		vp.add(hpTop);
		vp.add(tabela);
		vp.add(hpRodape);
		tela.add(vp);
		tela.setGlassEnabled(true);
		tela.center();
		nome.setFocus(true);
		
	}
	
	private void carregaDados(){
		final LoadingPopup pp = new LoadingPopup("Aguarde ...");
		rpcService.getSession(new AsyncCallback<User>() {
			
			@Override
			public void onSuccess(User result) {
				pp.hide();
				
				nome.setText(result.getNome_partic());
				cpf.setText(result.getCpf_partic());
				email.setText(result.getEmail_partic());
				senha.setText(result.getSenha_partic());
				matricula.setText(result.getMatr_aluno_partic());
				
				nome.setEnabled(false);
				cpf.setEnabled(false);
				email.setEnabled(false);
				senha.setEnabled(false);
				matricula.setEnabled(false);
				
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				pp.hide();
				
			}
		});
		
	}

	private void preencheTabela(FlexTable tb) {
		HTML h = new HTML("Nome:");
		h.addStyleName("alignDir");
		tb.setWidget(0, 0, h);
		nome = new TextBox();
		nome.setWidth("260px");
		tb.setWidget(0, 1, nome);
		
		vpCpf = new VerticalPanel();
		vpCpf.setWidth("260px");

		HTML h1 = new HTML("CPF:");
		h1.addStyleName("alignDir");
		tb.setWidget(1, 0, h1);
		
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
		tb.setWidget(1, 1, vpCpf);

		vpEmail = new VerticalPanel();
		vpEmail.setWidth("260px");
		
		HTML h2 = new HTML("Email:");
		h2.addStyleName("alignDir");
		tb.setWidget(2, 0, h2);
		
		email = new TextBox();
		email.setWidth("260px");
		setEmail();
//		tb.setWidget(2, 1, email);

		vEmail = new HTML("*Email inválido!");
		vEmail.setVisible(false);
		vEmail.addStyleName("vCPF");
		
		vpEmail.add(vEmail);
		vpEmail.add(email);
		tb.setWidget(2, 1, vpEmail);
		
		HTML h3 = new HTML("Senha:");
		h3.addStyleName("alignDir");
		tb.setWidget(3, 0, h3);
		senha = new PasswordTextBox();
		senha.setWidth("260px");
		senha.setText("");
		tb.setWidget(3, 1, senha);

		vpMatricula = new VerticalPanel();
		vpMatricula.setWidth("260px");
		
		HTML h5 = new HTML("Matrícula:");
		h5.addStyleName("alignDir");
		tb.setWidget(5, 0, h5);
		
		matricula = new TextBox();
		matricula.setMaxLength(14);
		matricula.setWidth("260px");
		setMatricula(matricula);
		
		vMatricula = new HTML("* Matrícula inválida!");
		vMatricula.setVisible(false);
		vMatricula.addStyleName("vCPF");
		
		vpMatricula.add(vMatricula);
		vpMatricula.add(matricula);
		tb.setWidget(5, 1, vpMatricula);
	}

	private FlexTable tabela(FlexTable tb) {
		tb.setWidth("400px");
		tb.setHeight("80px");
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

	private boolean verificaCampos() {
		if (nome.getText().equals("") || cpf.getText().equals("")
				|| email.getText().equals("") || senha.getText().equals("") || matricula.getText().equals("")) {
			return false;
		}
		return true;
	}

	private void addEvento(final Button bt) {
		
		ok.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				tela.hide();
				
			}
		});
		
		bt.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(bt.getText().equals("Editar Dados")){
					bt.setText("Alterar");
					nome.setEnabled(true);
					cpf.setEnabled(true);
					email.setEnabled(true);
					senha.setEnabled(true);
					matricula.setEnabled(true);
				}
				else{
					if (verificaCampos()) {
						user = new User();

						user.setNome_partic(nome.getText());
						user.setCpf_partic(cpf.getText());
						user.setEmail_partic(email.getText());
//						user.setLogin_partic(login.getText());
						user.setSenha_partic(senha.getText());
						user.setMatr_aluno_partic(matricula.getText());

						tela.hide();
						final LoadingPopup pp = new LoadingPopup("Alterando dados, aguarde...");
						rpcService.alterarDados(user,
								new AsyncCallback<Integer>() {
									@Override
									public void onSuccess(Integer result) {
										if (result==1) {
											tela.hide();
											pp.hide();
											ip = new InformacaoPopup(
													"Os seus dados foram alterados. Foi enviado para o seu e-mail o link para a confirmação da alteração. Antes de fazer login, confirme a alteração clicando" +
													" no link que foi enviado. Se não encontrar, verifique em sua caixa de SPAM ou no LIXO de se e-mail.");
											ip.getTela().center();
											ClickHandler ch = new ClickHandler() {
												@Override
												public void onClick(ClickEvent event) {
													ip.getTela().hide();
													final LoadingPopup pp = new LoadingPopup("Aguarde...");
													rpcService.removeSessao(new AsyncCallback<Void>() {

														@Override
														public void onFailure(Throwable caught) {
															pp.hide();

														}

														@Override
														public void onSuccess(Void result) {
															pp.hide();
															// TODO Auto-generated method stub
//															RootPanel.get("menuDir").setVisible(false);
															String s = History.getToken();
															if (!s.equals("apresentacao")){
																eventBus.fireEvent(new ApresentationEvent("apresentacao"));
															}
															
																
															// Window.Location.replace("http://127.0.0.1:8888/SecitecGWT.html?gwt.codesvr=127.0.0.1:9997#apresentacao");
														}
													});
												}
											};
											ip.getOk().addClickHandler(ch);
											ip.getFechar().addClickHandler(ch);
										} else if(result == 0){
											//tela.hide();
											pp.hide();
											ip = new InformacaoPopup(
													"Não foi possível realizar as alterações. Tente novamente mais tarde.");
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
									 else if(result == 3){
										//tela.hide();
										pp.hide();
										ip = new InformacaoPopup(
												"Não foi possível realizar as alterações. O CPF informado já está cadastrado.");
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
									 else if(result == 4){
											//tela.hide();
											pp.hide();
											ip = new InformacaoPopup(
													"Não foi possível realizar as alterações. O email informado já está cadastrado.");
											ip.getTela().center();
											ClickHandler ch = new ClickHandler() {
												@Override
												public void onClick(ClickEvent event) {
													ip.getTela().hide();
													tela.center();
													email.setText("");
													email.setFocus(true);
												}
											};
											ip.getOk().addClickHandler(ch);
											ip.getFechar().addClickHandler(ch);
										}
										else if(result ==2){
											tela.hide();
											pp.hide();
											ip = new InformacaoPopup(
													"Os seus dados foram alterados. Você será deslogado. Faça login novamente.");
											ip.getTela().center();
											
											final LoadingPopup lp = new LoadingPopup("Aguarde ...");
											rpcService.removeSessao(new AsyncCallback<Void>() {

												@Override
												public void onFailure(Throwable caught) {
													lp.hide();

												}

												@Override
												public void onSuccess(Void result) {
													lp.hide();
													// TODO Auto-generated method stub
//													RootPanel.get("menuDir").setVisible(false);
													String s = History.getToken();
													if (!s.equals("apresentacao")){
														eventBus.fireEvent(new ApresentationEvent("apresentacao"));
													}
													
														
													// Window.Location.replace("http://127.0.0.1:8888/SecitecGWT.html?gwt.codesvr=127.0.0.1:9997#apresentacao");
												}
											});
											
											ClickHandler ch = new ClickHandler() {
												@Override
												public void onClick(ClickEvent event) {
													ip.getTela().hide();
													final LoadingPopup pp = new LoadingPopup("Aguarde...");
													rpcService.removeSessao(new AsyncCallback<Void>() {
														
														@Override
														public void onSuccess(Void result) {
															pp.hide();
															Presenter presenter = new LoginPresenter(
																	rpcService, eventBus,
																	new LoginView());
															if (presenter != null)
																presenter.go();
															
														}
														
														@Override
														public void onFailure(Throwable caught) {
															pp.hide();
															
														}
													});
													// tela.hide();
													
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

			}
		});
	}

	private void camposVazios() {
		if (nome.getText().equals(""))
			nome.setFocus(true);
		else if (cpf.getText().equals(""))
			cpf.setFocus(true);
		else if (email.getText().equals(""))
			email.setFocus(true);
		else if (senha.getText().equals(""))
			senha.setFocus(true);
		else if(matricula.getText().equals(""))
			matricula.setFocus(true);
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
				/*else{
					if(!cpf.getText().isEmpty()){
						rpcService.getCpf(cpf.getValue(), new AsyncCallback<Boolean>() {
	
							@Override
							public void onFailure(Throwable caught) {
							}
	
							@Override
							public void onSuccess(Boolean result) {
								//se true, cpf dispon�vel
								if(result){
									vCpf.setVisible(false);
								}
								else{
									vCpf.setText("*CPF já cadastrado!");
									vCpf.setVisible(true);
									cpf.setText("");
									cpf.setFocus(true);
								}
							}
						});
					}

				}*/
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
		
		email.addFocusHandler(new FocusHandler() {
			
			@Override
			public void onFocus(FocusEvent event) {
				email.setText("");
			}
		});
		
		email.addBlurHandler(new BlurHandler() {
			
			@Override
			public void onBlur(BlurEvent event) {
				if(!verificaEmail(email.getValue()) && !email.getValue().equals("")){
					vEmail.setText("*Email inválido!");
					vEmail.setVisible(true);
					email.setText("");
					email.setFocus(true);
				}
				else{
					vEmail.setVisible(false);
				}
				/*else{
					if(!email.getText().isEmpty()){
						rpcService.getEmail(email.getValue(), new AsyncCallback<Boolean>() {
	
							@Override
							public void onFailure(Throwable caught) {
							}
	
							@Override
							public void onSuccess(Boolean result) {
								//se true, cpf dispon�vel
								System.out.println("result email: "+result);
								if(result){
									vEmail.setVisible(false);
								}
								else{
									vEmail.setText("*Email já cadastrado!");
									vEmail.setVisible(true);
									email.setText("");
									email.setFocus(true);
								}
							}
						});
					}

				}*/
				
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
	
	private void setMatricula(final TextBoxBase tbMatr){
		tbMatr.addKeyPressHandler(new KeyPressHandler() {
			
			@Override
			public void onKeyPress(KeyPressEvent event) {
				if(verificaTecla(event))
					tbMatr.setValue(tbMatr.getValue());					
				else
					tbMatr.cancelKey();
			}
		});
		
		tbMatr.addBlurHandler(new BlurHandler() {
			
			@Override
			public void onBlur(BlurEvent event) {
				if(tbMatr.getValue().length() < 14 && !tbMatr.getValue().equals("")){
					vMatricula.setVisible(true);
					matricula.setFocus(true);
					matricula.setText("");
					matricula.setFocus(true);
				}
				else
					vMatricula.setVisible(false);
			}
		});
	}
}