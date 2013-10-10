package br.com.secitec.menu.presenter;

import java.util.ArrayList;
import java.util.List;

import br.com.secitec.client.RPCServiceAsync;
import br.com.secitec.menu.event.LoginEvent;
import br.com.secitec.menu.view.LoginView;
import br.com.secitec.menu.view.MaisInformacaoView;
import br.com.secitec.popup.ConfirmacaoPopup;
import br.com.secitec.popup.InformacaoPopup;
import br.com.secitec.shared.model.Atividade;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class UsuarioPresenter implements Presenter {

	public interface Display {
		void setDataUsuario(List<Atividade> atividades);

		void setData(List<Atividade> oficinas, List<Atividade> minicursos,
				List<Atividade> palestras);

		int[] getUsuario(ClickEvent event);

		int[] getOficina(ClickEvent event);

		int[] getMinicurso(ClickEvent event);

		int[] getPalestra(ClickEvent event);

		HasClickHandlers getListaUsuario();

		HasClickHandlers getListaOficina();

		HasClickHandlers getListaMinicurso();

		HasClickHandlers getListaPalestra();

		Widget asWidget();
	}

	private final RPCServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	private List<Atividade> oficinas;
	private List<Atividade> minicursos;
	private List<Atividade> palestras;
	private List<Atividade> atividades;
	private InformacaoPopup ip;
	private ConfirmacaoPopup cp;

	public UsuarioPresenter(RPCServiceAsync rpcService,
			HandlerManager eventBus, Display view) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
	}

	public void bind() {
		display.getListaUsuario().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				int e[] = display.getUsuario(event);
				eventoBotoesUsuario(e);
			}
		});
		display.getListaOficina().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				int e[] = display.getOficina(event);
				eventoBotoesAtividades(e);
			}
		});
		display.getListaMinicurso().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				int e[] = display.getMinicurso(event);
				eventoBotoesMinicursos(e);
			}
		});
		display.getListaPalestra().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				int e[] = display.getPalestra(event);
				eventoBotoesAtividades(e);
			}
		});
	}

	public void eventoBotoesUsuario(final int[] e) {
		if (e[1] == 4) {
			Presenter presenter = new MaisInformacaoPresenter(rpcService,
					eventBus, new MaisInformacaoView("Cancelar inscrição"),
					e[0], this);
			if (presenter != null)
				presenter.go();
		} else if (e[1] == 5) {
			rpcService.getSessao(new AsyncCallback<Boolean>() {
				@Override
				public void onFailure(Throwable caught) {

				}

				@Override
				public void onSuccess(Boolean result) {
					if (result) {
						cp = new ConfirmacaoPopup("Deseja realmente cancelar a inscri��o?");
						cp.getTela().center();
						
						ClickHandler chNao = new ClickHandler() {
							
							@Override
							public void onClick(ClickEvent event) {
								cp.getTela().hide();
							}
						};
						ClickHandler chSim = new ClickHandler() {
							
							@Override
							public void onClick(ClickEvent event) {
								cp.getTela().hide();
								cancelarInscricao(e);
							}
						};		
						
						cp.getBtNao().addClickHandler(chNao);
						cp.getFechar().addClickHandler(chNao);
						cp.getSim().addClickHandler(chSim);
					} else {
						Presenter presenter = new LoginPresenter(rpcService,
								eventBus, new LoginView());
						if (presenter != null)
							presenter.go();
					}
				}
			});
		}
	}

	private void cancelarInscricao(int e[]){
		rpcService.cancelar(e[0],
				new AsyncCallback<Boolean>() {
					@Override
					public void onFailure(Throwable caught) {

					}

					@Override
					public void onSuccess(Boolean result) {
						if (result) {
							ip = new InformacaoPopup("Inscrição cancelada com sucesso!");
							ip.getTela().center();
							ip.getFechar().addClickHandler(new ClickHandler() {
								@Override
								public void onClick(ClickEvent event) {
									ip.getTela().hide();
									setDadosUsuario();
								}
							});
							ip.getOk().addClickHandler(new ClickHandler() {
								@Override
								public void onClick(ClickEvent event) {
									ip.getTela().hide();
									setDadosUsuario();
								}
							});
						} else {
							ip = new InformacaoPopup(
									"Não foi possível cancelar a inscrição");
							ip.getTela().center();
							ip.getOk().addClickHandler(new ClickHandler() {
								@Override
								public void onClick(ClickEvent event) {
									ip.getTela().hide();
								}
							});
							ip.getFechar().addClickHandler(new ClickHandler() {
								@Override
								public void onClick(ClickEvent event) {
									ip.getTela().hide();
								}
							});
						}
					}
				});
	}
	
	public void eventoBotoesAtividades(final int[] e) {
		if (e[1] == 4) {
			Presenter presenter = new MaisInformacaoPresenter(rpcService,
					eventBus, new MaisInformacaoView("Inscrever"), e[0], this);
			if (presenter != null)
				presenter.go();
		} else if (e[1] == 5) {
			rpcService.getSessao(new AsyncCallback<Boolean>() {
				@Override
				public void onFailure(Throwable caught) {

				}

				@Override
				public void onSuccess(Boolean result) {
					if (result) {
						rpcService.inscrever(e[0],
								new AsyncCallback<Boolean>() {
									@Override
									public void onFailure(Throwable caught) {

									}

									@Override
									public void onSuccess(Boolean result) {
										if (result) {
											ip = new InformacaoPopup(
													"Inscrição efetuada com sucesso!");
											ip.getTela().center();
											ip.getOk().addClickHandler(
													new ClickHandler() {
														@Override
														public void onClick(
																ClickEvent event) {
															ip.getTela().hide();
															setDadosUsuario();
														}
													});
											ip.getFechar().addClickHandler(
													new ClickHandler() {
														@Override
														public void onClick(
																ClickEvent event) {
															ip.getTela().hide();
															setDadosUsuario();
														}
													});
										} else {
											ip = new InformacaoPopup(
													"Você está inscrito em outra(s) atividade(s) no mesmo"
															+ " horário!");
											ip.getTela().center();
											ip.getOk().addClickHandler(
													new ClickHandler() {
														@Override
														public void onClick(
																ClickEvent event) {
															ip.getTela().hide();
														}
													});
											ip.getFechar().addClickHandler(
													new ClickHandler() {
														@Override
														public void onClick(
																ClickEvent event) {
															ip.getTela().hide();
														}
													});
										}
									}
								});
					} else {
						Presenter presenter = new LoginPresenter(rpcService,
								eventBus, new LoginView());
						if (presenter != null)
							presenter.go();
					}
				}
			});
		}
	}

	@Override
	public void go(HasWidgets container) {
		bind();
		container.clear();
		container.add(display.asWidget());
		rpcService.getAtividades(new AsyncCallback<List<Atividade>>() {
			@Override
			public void onSuccess(List<Atividade> result) {
				oficinas = new ArrayList<Atividade>();
				palestras = new ArrayList<Atividade>();
				minicursos = new ArrayList<Atividade>();
				for (int i = 0; i < result.size(); i++) {
					Atividade a = result.get(i);
					if (a.getTipoAtiv().equalsIgnoreCase("Oficina"))
						oficinas.add(a);
					else if (a.getTipoAtiv().equalsIgnoreCase("Palestra"))
						palestras.add(a);
					else
						minicursos.add(a);
				}
				display.setData(oficinas, minicursos, palestras);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Erro: " + caught.getMessage());
			}
		});
		setDadosUsuario();
	}

	public void setDadosUsuario() {
		rpcService.getAtividadesUsuario(new AsyncCallback<List<Atividade>>() {
			@Override
			public void onFailure(Throwable caught) {

			}

			@Override
			public void onSuccess(List<Atividade> result) {
				atividades = new ArrayList<Atividade>();
				if (result != null) {
					for (int i = 0; i < result.size(); i++) {
						Atividade a = result.get(i);
						atividades.add(a);
					}
				}
				display.setDataUsuario(atividades);
			}
		});
	}

	@Override
	public void go() {

	}
	
	public void eventoBotoesMinicursos(final int[] e){
		if (e[1] == 4) {
			Presenter presenter = new MaisInformacaoPresenter(
					rpcService, eventBus, new MaisInformacaoView("Inscrever"),
					e[0]);
			if (presenter != null)
				presenter.go();
		} else if (e[1] == 5) {
			rpcService.getSessao(new AsyncCallback<Boolean>() {
				@Override
				public void onFailure(Throwable caught) {

				}
				@Override
				public void onSuccess(Boolean result) {
					//se estiver logado
					if (result) {
						//verificar minicurso
						rpcService.getMinicursosDoAluno(new AsyncCallback<Boolean>() {

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
								
							}

							@Override
							public void onSuccess(Boolean result) {
								// TODO Auto-generated method stub
								if(result)
									inscrever(e);
								else{
									//n�o pode ser inscrito
									ip = new InformacaoPopup("Você j� est� inscrito em um Minicurso!");
									ip.getTela().center();
									ip.getOk().addClickHandler(new ClickHandler() {													
										@Override
										public void onClick(ClickEvent event) {
											ip.getTela().hide();
										}
									});
									ip.getFechar().addClickHandler(new ClickHandler() {													
										@Override
										public void onClick(ClickEvent event) {
											ip.getTela().hide();
										}
									});
								}
									
							}
						});
					} else {
						Presenter presenter = new LoginPresenter(
								rpcService, eventBus, new LoginView());
						if (presenter != null)
							presenter.go();
					}
				}
			});
		}
	}
	
	private void inscrever(int[] e){
		rpcService.inscrever(e[0],
				new AsyncCallback<Boolean>() {
					@Override
					public void onFailure(Throwable caught) {
					
					}
					@Override
					public void onSuccess(Boolean result) {
						if(result){
							ip = new InformacaoPopup("Inscrição efetuada com sucesso!");
							ip.getTela().center();
							ip.getOk().addClickHandler(new ClickHandler() {														
								@Override
								public void onClick(ClickEvent event) {
									ip.getTela().hide();
									eventBus.fireEvent(new LoginEvent("login"));
								}
							});
							ip.getFechar().addClickHandler(new ClickHandler() {														
								@Override
								public void onClick(ClickEvent event) {
									ip.getTela().hide();
									eventBus.fireEvent(new LoginEvent("login"));
								}
							});
							
						}
						else{
							ip = new InformacaoPopup("Você está inscrito em outra(s) atividade(s) no mesmo"
									+ " horário!");
							ip.getTela().center();
							ip.getOk().addClickHandler(new ClickHandler() {													
								@Override
								public void onClick(ClickEvent event) {
									ip.getTela().hide();
								}
							});
							ip.getFechar().addClickHandler(new ClickHandler() {													
								@Override
								public void onClick(ClickEvent event) {
									ip.getTela().hide();
								}
							});
						}
					}
				});
	}
}