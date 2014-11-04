package br.com.secitec.menu.presenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.secitec.client.RPCServiceAsync;
import br.com.secitec.menu.event.LoginEvent;
import br.com.secitec.menu.view.LoginView;
import br.com.secitec.menu.view.MaisInformacaoView;
import br.com.secitec.popup.ConfirmacaoPopup;
import br.com.secitec.popup.InformacaoPopup;
import br.com.secitec.popup.LoadingPopup;
import br.com.secitec.shared.model.Atividade;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class UsuarioPresenter implements Presenter {

	public interface Display {
		void setTabelaUsuario(List<Atividade> atividades);

		void setData(List<Atividade> oficinas, List<Atividade> minicursos,
				List<Atividade> palestras, List<Atividade> atividadesUsuario);
		
		void setDataAtividades(List<Atividade> atividades, List<Atividade> atividadesUsuario);
		
		void setTabelaOficina(List<Atividade> oficinas, List<Atividade> atividadesUsuario);
		
		void setTabelaMinicurso(List<Atividade> minicursos, List<Atividade> atividadesUsuario);
		
		void setTabelaPalestras(List<Atividade> palestras, List<Atividade> atividadesUsuario);

		int[] getUsuario(ClickEvent event);

		int[] getOficina(ClickEvent event);

		int[] getMinicurso(ClickEvent event);

		int[] getPalestra(ClickEvent event);
		
		HTML getMsg();
		
		VerticalPanel getVpUsuario();

		FlexTable getListaUsuario();

		FlexTable getListaOficina();

		FlexTable getListaMinicurso();

		FlexTable getListaPalestra();

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

	private void eventoMaisInformacao(int idAtividade, String msg){
		Presenter presenter = new MaisInformacaoPresenter(
				rpcService, eventBus, new MaisInformacaoView(msg),
				idAtividade);
		if (presenter != null)
			presenter.go();
	}
	
	private void eventoCancelarInscricao(final int idAtividade){
		final LoadingPopup pp = new LoadingPopup("Aguarde...");
		rpcService.getSessao(new AsyncCallback<Boolean>() {
			@Override
			public void onFailure(Throwable caught) {
				pp.hide();
			}

			@Override
			public void onSuccess(Boolean result) {
				pp.hide();
				if (result) {
					cp = new ConfirmacaoPopup("Deseja realmente cancelar a inscrição?");
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
							cancelarInscricao(idAtividade);
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
	
	private void eventoInscreverOficina(final int idAtividade){
		final LoadingPopup pp = new LoadingPopup("Aguarde...");
		rpcService.getSessao(new AsyncCallback<Boolean>() {
			@Override
			public void onFailure(Throwable caught) {
				pp.hide();
			}

			@Override
			public void onSuccess(Boolean result) {
				
				if (result) {
					rpcService.inscrever(idAtividade,
							new AsyncCallback<Integer>() {
								@Override
								public void onFailure(Throwable caught) {
									pp.hide();
								}

								@Override
								public void onSuccess(Integer result) {
									pp.hide();
									if(result ==9){
										ip = new InformacaoPopup("Cada aluno pode se inscrever em no máximo seis atividades, desde que não exista conflito de horários, sendo no máximo, dois minicursos e o restante entre colóquios e oficinas.");
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
									else if (result == 2) {
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
														atualizaOficina();
//														display.setDataAtividades(oficinas, atividades);
													}
												});
										ip.getFechar().addClickHandler(
												new ClickHandler() {
													@Override
													public void onClick(
															ClickEvent event) {
														ip.getTela().hide();
														setDadosUsuario();
														atualizaOficina();
//														display.setDataAtividades(oficinas, atividades);
													}
												});
									} else if(result == 0){
										inscritoMesmoHorario();
									}
									else if(result == 1){
										ip = new InformacaoPopup(
												"Limite de vagas esgotado!");
										ip.getTela().center();
										ip.getOk().addClickHandler(
												new ClickHandler() {
													@Override
													public void onClick(
															ClickEvent event) {
														ip.getTela().hide();
//														setDadosUsuario();
														atualizaOficina();
////														display.setDataAtividades(oficinas, atividades);
													}
												});
										ip.getFechar().addClickHandler(
												new ClickHandler() {
													@Override
													public void onClick(
															ClickEvent event) {
														ip.getTela().hide();
//														setDadosUsuario();
														atualizaOficina();
//														display.setDataAtividades(oficinas, atividades);
													}
												});
									}
									else{
										ip = new InformacaoPopup(
												"Não foi possível efetuar a inscrição! Tente mais tarde.");
										ip.getTela().center();
										ip.getOk().addClickHandler(
												new ClickHandler() {
													@Override
													public void onClick(
															ClickEvent event) {
														ip.getTela().hide();
//														setDadosUsuario();
//														atualizaOficina();
////														display.setDataAtividades(oficinas, atividades);
													}
												});
										ip.getFechar().addClickHandler(
												new ClickHandler() {
													@Override
													public void onClick(
															ClickEvent event) {
														ip.getTela().hide();
//														setDadosUsuario();
//														atualizaOficina();
//														display.setDataAtividades(oficinas, atividades);
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
	
	private void eventoInscreverMinicurso(final int idAtividade){
		final LoadingPopup pp = new LoadingPopup("Aguarde...");
		rpcService.getSessao(new AsyncCallback<Boolean>() {
			@Override
			public void onFailure(Throwable caught) {
				pp.hide();
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
							pp.hide();
						}

						@Override
						public void onSuccess(Boolean result) {
							pp.hide();
							// TODO Auto-generated method stub
							if(result)
								inscrever(idAtividade);
							else{
								//n�o pode ser inscrito
								ip = new InformacaoPopup("Você já está inscrito em dois Minicurso! Você pode-se inscrever em apenas dois minicurso.");
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
	
	public void bindUsuario(){
		//Eventos tabela USUÁRIO
		//Mais Informação
		for (int i = 0; i < display.getListaUsuario().getRowCount(); i++) {
			final int j = i;
			((Button)display.getListaUsuario().getWidget(i, 4)).addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					int idAtividade = Integer.parseInt(display.getListaUsuario().getRowFormatter().getElement(j).getAttribute("id"));
					eventoMaisInformacao(idAtividade, "Cancelar inscrição");
				}
			});
		}
		//Cancelar
		for (int i = 0; i < display.getListaUsuario().getRowCount(); i++) {
			final int j = i;
			((Button)display.getListaUsuario().getWidget(i, 5)).addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					int idAtividade = Integer.parseInt(display.getListaUsuario().getRowFormatter().getElement(j).getAttribute("id"));
					eventoCancelarInscricao(idAtividade);
				}
			});
		}	
	}
	
	public void bindMiniCurso(){
		
		//Eventos tabela MINICURSOS
				//Mais Informação
				for (int i = 0; i < display.getListaMinicurso().getRowCount(); i++) {
					final int j = i;
					((Button)display.getListaMinicurso().getWidget(i, 4)).addClickHandler(new ClickHandler() {
						
						@Override
						public void onClick(ClickEvent event) {
							int idAtividade = Integer.parseInt(display.getListaMinicurso().getRowFormatter().getElement(j).getAttribute("id"));
							eventoMaisInformacao(idAtividade, "Inscrever");
						}
					});
				}
				//Inscrever
				for (int i = 0; i < display.getListaMinicurso().getRowCount(); i++) {
					final int j = i;
					((Button)display.getListaMinicurso().getWidget(i, 5)).addClickHandler(new ClickHandler() {
						
						@Override
						public void onClick(ClickEvent event) {
							int idAtividade = Integer.parseInt(display.getListaMinicurso().getRowFormatter().getElement(j).getAttribute("id"));
									eventoInscreverMinicurso(idAtividade);
						}
					});
				}
		
	}
	
	public void bindOficinas(){
		
		//Eventos tabela OFICINAS
				//Mais Informação
				for (int i = 0; i < display.getListaOficina().getRowCount(); i++) {
					final int j = i;
					((Button)display.getListaOficina().getWidget(i, 4)).addClickHandler(new ClickHandler() {
						
						@Override
						public void onClick(ClickEvent event) {
							int idAtividade = Integer.parseInt(display.getListaOficina().getRowFormatter().getElement(j).getAttribute("id"));
							eventoMaisInformacao(idAtividade, "Inscrever");
						}
					});
				}
				//Inscrever
				for (int i = 0; i < display.getListaOficina().getRowCount(); i++) {
					final int j = i;
					((Button)display.getListaOficina().getWidget(i, 5)).addClickHandler(new ClickHandler() {
						
						@Override
						public void onClick(ClickEvent event) {
							int idAtividade = Integer.parseInt(display.getListaOficina().getRowFormatter().getElement(j).getAttribute("id"));
									eventoInscreverOficina(idAtividade);
						}
					});
				}
	}
	
	public void bindPalestras(){
		
		//Eventos tabela PALESTRAS
				//Mais Informação
				for (int i = 0; i < display.getListaPalestra().getRowCount(); i++) {
					final int j = i;
					((Button)display.getListaPalestra().getWidget(i, 4)).addClickHandler(new ClickHandler() {
						
						@Override
						public void onClick(ClickEvent event) {
							int idAtividade = Integer.parseInt(display.getListaPalestra().getRowFormatter().getElement(j).getAttribute("id"));
							eventoMaisInformacao(idAtividade, "Inscrever");
						}
					});
				}
	}
	
	public void bind() {
		
		bindMiniCurso();
		bindOficinas();
		bindPalestras();
		
		
		
		
		
	}

	private void cancelarInscricao(final int e){
		final LoadingPopup pp = new LoadingPopup("Aguarde...");
		rpcService.cancelar(e,
				new AsyncCallback<Boolean>() {
					@Override
					public void onFailure(Throwable caught) {
						pp.hide();
					}

					@Override
					public void onSuccess(Boolean result) {
						pp.hide();
						if (result) {
							ip = new InformacaoPopup("Inscrição cancelada com sucesso!");
							ip.getTela().center();
							ip.getFechar().addClickHandler(new ClickHandler() {
								@Override
								public void onClick(ClickEvent event) {
									ip.getTela().hide();
									atualizarCancelar(e);
//									setDadosUsuario();
								}
							});
							ip.getOk().addClickHandler(new ClickHandler() {
								@Override
								public void onClick(ClickEvent event) {
									ip.getTela().hide();
									atualizarCancelar(e);
//									setDadosUsuario();
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

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
		final LoadingPopup pp = new LoadingPopup("Aguarde...");
		setDadosUsuario();
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
					else if (a.getTipoAtiv().equalsIgnoreCase("Minicurso"))
						minicursos.add(a);
				}
				Collections.sort(oficinas);
				display.setTabelaOficina(oficinas, atividades);
				Collections.sort(minicursos);
				display.setTabelaMinicurso(minicursos, atividades);
				Collections.sort(palestras);
				display.setTabelaPalestras(palestras, atividades);
//				display.setData(oficinas, minicursos, palestras, atividades);
				
				pp.hide();
				bind();
			}

			@Override
			public void onFailure(Throwable caught) {
				pp.hide();
				Window.alert("Erro: " + caught.getMessage());
			}
		});
	}

	public void setDadosUsuario() {
		final LoadingPopup pp = new LoadingPopup("Aguarde...");
		rpcService.getAtividadesUsuario(new AsyncCallback<List<Atividade>>() {
			@Override
			public void onFailure(Throwable caught) {
				pp.hide();
			}

			@Override
			public void onSuccess(List<Atividade> result) {
				pp.hide();
				atividades = null;
				if (result != null) {
					atividades = new ArrayList<Atividade>();
					for (int i = 0; i < result.size(); i++) {
						Atividade a = result.get(i);
						atividades.add(a);
					}
				}
				if(atividades != null){
					Collections.sort(atividades);
					display.setTabelaUsuario(atividades);
					display.getMsg().setVisible(false);
					display.getVpUsuario().setVisible(true);
					bindUsuario();
				}
				else{
					display.getMsg().setText("Você não está inscrito em nenhuma atividade.");
					display.getMsg().setVisible(true);
					display.getVpUsuario().setVisible(false);
				}
			}
		});
	}
	
	public void atualizarCancelar(int idAtividade){
		for(int i = 0;i < oficinas.size();i++){
			Atividade a = oficinas.get(i);
			if(idAtividade == a.getIdAtiv()){
				setDadosUsuario();
				atualizaOficina();
				return;
			}
		}
		for (int i = 0; i < minicursos.size(); i++) {
			Atividade a = minicursos.get(i);
			if(idAtividade == a.getIdAtiv()){
				setDadosUsuario();
				atualizaMinicurso();
				return;
			}
		}
	}

	public void atualizaOficina(){
		final LoadingPopup pp = new LoadingPopup("Aguarde...");
//		setDadosUsuario();
		rpcService.getAtividades(new AsyncCallback<List<Atividade>>() {
			@Override
			public void onSuccess(List<Atividade> result) {
				
				oficinas = new ArrayList<Atividade>();
//				palestras = new ArrayList<Atividade>();
//				minicursos = new ArrayList<Atividade>();
				for (int i = 0; i < result.size(); i++) {
					Atividade a = result.get(i);
					if (a.getTipoAtiv().equalsIgnoreCase("Oficina"))
						oficinas.add(a);
//					else if (a.getTipoAtiv().equalsIgnoreCase("Palestra"))
//						palestras.add(a);
//					else if (a.getTipoAtiv().equalsIgnoreCase("Minicurso"))
//						minicursos.add(a);
				}
				Collections.sort(oficinas);
				display.setTabelaOficina(oficinas, atividades);
//				Collections.sort(minicursos);
//				display.setTabelaMinicurso(minicursos, atividades);
//				Collections.sort(palestras);
//				display.setTabelaPalestras(palestras, atividades);
//				display.setData(oficinas, minicursos, palestras, atividades);
				
				pp.hide();
				bindOficinas();
			}

			@Override
			public void onFailure(Throwable caught) {
				pp.hide();
				Window.alert("Erro: " + caught.getMessage());
			}
		});
		
//		display.setTabelaOficina(oficinas, atividades);
	}
	
	public void atualizaMinicurso(){
		final LoadingPopup pp = new LoadingPopup("Aguarde...");
//		setDadosUsuario();
		rpcService.getAtividades(new AsyncCallback<List<Atividade>>() {
			@Override
			public void onSuccess(List<Atividade> result) {
				
//				oficinas = new ArrayList<Atividade>();
//				palestras = new ArrayList<Atividade>();
				minicursos = new ArrayList<Atividade>();
				for (int i = 0; i < result.size(); i++) {
					Atividade a = result.get(i);
					if (a.getTipoAtiv().equalsIgnoreCase("Minicurso"))
						minicursos.add(a);
//					else if (a.getTipoAtiv().equalsIgnoreCase("Palestra"))
//						palestras.add(a);
//					else if (a.getTipoAtiv().equalsIgnoreCase("Minicurso"))
//						minicursos.add(a);
				}
				Collections.sort(minicursos);
				display.setTabelaMinicurso(minicursos, atividades);
//				display.setTabelaOficina(oficinas, atividades);
//				Collections.sort(minicursos);
//				Collections.sort(palestras);
//				display.setTabelaPalestras(palestras, atividades);
//				display.setData(oficinas, minicursos, palestras, atividades);
				
				pp.hide();
				bindMiniCurso();
			}

			@Override
			public void onFailure(Throwable caught) {
				pp.hide();
				Window.alert("Erro: " + caught.getMessage());
			}
		});
	}
	
	@Override
	public void go() {

	}
	
	private void inscrever(int e){
		final LoadingPopup pp = new LoadingPopup("Aguarde...");
		rpcService.inscreverMinicurso(e,
				new AsyncCallback<Integer>() {
					@Override
					public void onFailure(Throwable caught) {
						pp.hide();
					}
					@Override
					public void onSuccess(Integer result) {
						pp.hide();
						if(result == 2){
							ip = new InformacaoPopup("Inscrição efetuada com sucesso!");
							ip.getTela().center();
							ip.getOk().addClickHandler(new ClickHandler() {														
								@Override
								public void onClick(ClickEvent event) {
									ip.getTela().hide();
									//eventBus.fireEvent(new LoginEvent("login"));
									setDadosUsuario();
									atualizaMinicurso();
//									display.setTabelaMinicurso(minicursos, atividades);
//									display.setDataAtividades(oficinas, atividades);
								}
							});
							ip.getFechar().addClickHandler(new ClickHandler() {														
								@Override
								public void onClick(ClickEvent event) {
									ip.getTela().hide();
									eventBus.fireEvent(new LoginEvent("login"));
									setDadosUsuario();
									atualizaMinicurso();
//									display.setTabelaMinicurso(minicursos, atividades);
//									display.setDataAtividades(oficinas, atividades);
								}
							});
							
						}
						else if(result == 0){
							inscritoMesmoHorario();
						}
						else if(result == 1){
							ip = new InformacaoPopup("Limite de vagas esgotado!");
							ip.getTela().center();
							ip.getOk().addClickHandler(new ClickHandler() {														
								@Override
								public void onClick(ClickEvent event) {
									ip.getTela().hide();
									//eventBus.fireEvent(new LoginEvent("login"));
//									setDadosUsuario();
									atualizaMinicurso();
//									display.setTabelaMinicurso(minicursos, atividades);
//									display.setDataAtividades(oficinas, atividades);
								}
							});
							ip.getFechar().addClickHandler(new ClickHandler() {														
								@Override
								public void onClick(ClickEvent event) {
									ip.getTela().hide();
									eventBus.fireEvent(new LoginEvent("login"));
//									setDadosUsuario();
									atualizaMinicurso();
//									display.setTabelaMinicurso(minicursos, atividades);
//									display.setDataAtividades(oficinas, atividades);
								}
							});
						}
						else{
							ip = new InformacaoPopup(
									"Não foi possível efetuar a inscrição! Tente mais tarde.");
							ip.getTela().center();
							ip.getOk().addClickHandler(
									new ClickHandler() {
										@Override
										public void onClick(
												ClickEvent event) {
											ip.getTela().hide();
//											setDadosUsuario();
//											atualizaOficina();
////											display.setDataAtividades(oficinas, atividades);
										}
									});
							ip.getFechar().addClickHandler(
									new ClickHandler() {
										@Override
										public void onClick(
												ClickEvent event) {
											ip.getTela().hide();
//											setDadosUsuario();
//											atualizaOficina();
//											display.setDataAtividades(oficinas, atividades);
										}
									});
						}
					}
				});
	}
	
	public void inscritoMesmoHorario(){
		final InformacaoPopup ip = new InformacaoPopup(
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