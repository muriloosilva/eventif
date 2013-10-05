package br.com.secitec.menu.presenter;

import br.com.secitec.client.RPCServiceAsync;
import br.com.secitec.menu.event.LoginEvent;
import br.com.secitec.menu.event.ProgramacaoEvent;
import br.com.secitec.menu.view.LoginView;
import br.com.secitec.popup.InformacaoPopup;
import br.com.secitec.shared.model.Atividade;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

public class MaisInformacaoPresenter implements Presenter {
	public interface Display {
		PopupPanel getPopup();

		void setData(Atividade atividade);

		Widget asWidget();

		Button getInscrever();
	}

	private final RPCServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;
	private UsuarioPresenter usuario;
	private int idAtividade;
	private InformacaoPopup ip;

	public MaisInformacaoPresenter(RPCServiceAsync rpcService,
			HandlerManager eventBus, Display view, int idAtividade) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
		this.idAtividade = idAtividade;
	}

	public MaisInformacaoPresenter(RPCServiceAsync rpcService,
			HandlerManager eventBus, Display view, int idAtividade,
			UsuarioPresenter usuario) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
		this.idAtividade = idAtividade;
		this.usuario = usuario;
	}

	public void bind() {
		display.getInscrever().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (display.getInscrever().getText().equals("Inscrever"))
					inscrever();
				else
					cancelar();
			}
		});
	}

	public void inscrever() {
		rpcService.getSessao(new AsyncCallback<Boolean>() {
			@Override
			public void onSuccess(Boolean result) {
				if (result) {
					rpcService.inscrever(idAtividade,
							new AsyncCallback<Boolean>() {
								@Override
								public void onFailure(Throwable caught) {

								}

								@Override
								public void onSuccess(Boolean result) {
									if (result) {
										display.getPopup().hide();
										ip = new InformacaoPopup(
												"Inscrição efetuada com sucesso!");
										ip.getTela().center();
										ip.getOk().addClickHandler(
												new ClickHandler() {
													@Override
													public void onClick(
															ClickEvent event) {
														ip.getTela().hide();
														if (History
																.getToken()
																.equals("login"))
															usuario.setDadosUsuario();
														else
															eventBus.fireEvent(new LoginEvent(
																	"login"));
													}
												});
									} else {
										display.getPopup().hide();
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
									}
								}
							});
				} else {
					display.getPopup().hide();
					Presenter presenter = new LoginPresenter(rpcService,
							eventBus, new LoginView());
					if (presenter != null)
						presenter.go();
				}
			}

			@Override
			public void onFailure(Throwable caught) {

			}
		});
	}

	public void cancelar() {
		rpcService.cancelar(idAtividade, new AsyncCallback<Boolean>() {
			@Override
			public void onFailure(Throwable caught) {

			}

			@Override
			public void onSuccess(Boolean result) {
				if (result) {
					display.getPopup().hide();
					ip = new InformacaoPopup("Inscrição cancelada com sucesso!");
					ip.getTela().center();
					ip.getOk().addClickHandler(new ClickHandler() {
						@Override
						public void onClick(ClickEvent event) {
							ip.getTela().hide();
							usuario.setDadosUsuario();
						}
					});
				} else {
					display.getPopup().hide();
					ip = new InformacaoPopup(
							"Não foi possível cancelar a inscrição");
					ip.getTela().center();
					ip.getOk().addClickHandler(new ClickHandler() {
						@Override
						public void onClick(ClickEvent event) {
							ip.getTela().hide();
						}
					});
				}
			}
		});
	}

	public void go() {
		bind();
		display.getPopup().center();
		rpcService.getAtividade(idAtividade, new AsyncCallback<Atividade>() {
			@Override
			public void onSuccess(Atividade result) {
				display.setData(result);
			}

			@Override
			public void onFailure(Throwable caught) {

			}
		});
	}

	@Override
	public void go(HasWidgets container) {

	}
}