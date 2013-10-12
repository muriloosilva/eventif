package br.com.secitec.client;

import br.com.secitec.menu.event.ApresentationEvent;
import br.com.secitec.menu.event.ApresentationEventHandler;
import br.com.secitec.menu.event.FaleConoscoEvent;
import br.com.secitec.menu.event.FaleConoscoEventHandler;
import br.com.secitec.menu.event.LoginEvent;
import br.com.secitec.menu.event.LoginEventHandler;
import br.com.secitec.menu.event.ProgramacaoEvent;
import br.com.secitec.menu.event.ProgramacaoEventHandler;
import br.com.secitec.menu.event.SobreEvent;
import br.com.secitec.menu.event.SobreEventHandler;
import br.com.secitec.menu.presenter.ApresentationPresenter;
import br.com.secitec.menu.presenter.FaleConoscoPresenter;
import br.com.secitec.menu.presenter.InfoUsuarioPresenter;
import br.com.secitec.menu.presenter.MenuLateralPresenter;
import br.com.secitec.menu.presenter.MenuPresenter;
import br.com.secitec.menu.presenter.Presenter;
import br.com.secitec.menu.presenter.ProgramacaoPresenter;
import br.com.secitec.menu.presenter.SobrePresenter;
import br.com.secitec.menu.presenter.UsuarioPresenter;
import br.com.secitec.menu.view.ApresentationView;
import br.com.secitec.menu.view.FaleConoscoView;
import br.com.secitec.menu.view.InfoUsuarioView;
import br.com.secitec.menu.view.MenuLateralView;
import br.com.secitec.menu.view.MenuView;
import br.com.secitec.menu.view.ProgramacaoView;
import br.com.secitec.menu.view.SobreView;
import br.com.secitec.menu.view.UsuarioView;
import br.com.secitec.popup.LoadingPopup;
import br.com.secitec.shared.model.User;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.RootPanel;

public class AppController implements Presenter, ValueChangeHandler<String> {

	private final HandlerManager eventBus;
	private final RPCServiceAsync rpcService;
	private HasWidgets container;
	private MenuPresenter presenterMenu;
	private InfoUsuarioPresenter presenterInfoUsuario;
	private String token;

	public AppController(RPCServiceAsync rpcService, HandlerManager eventBus) {
		this.eventBus = eventBus;
		this.rpcService = rpcService;
		loadMenu();
		loadMenuLateral();
		bind();
	}

	private void bind() {
		History.addValueChangeHandler(this);

		eventBus.addHandler(ApresentationEvent.TYPE,
				new ApresentationEventHandler() {
					public void onApresentation(ApresentationEvent event) {
						History.newItem("apresentacao");
					}
				});

		eventBus.addHandler(ProgramacaoEvent.TYPE,
				new ProgramacaoEventHandler() {
					public void onProgramacao(ProgramacaoEvent event) {
						History.newItem("programacao");
					}
				});

		eventBus.addHandler(LoginEvent.TYPE, new LoginEventHandler() {
			public void onLogin(LoginEvent event) {
				History.newItem("login");
			}
		});
		eventBus.addHandler(FaleConoscoEvent.TYPE, new FaleConoscoEventHandler() {
			public void onFaleConosco(FaleConoscoEvent event) {
				History.newItem("faleConosco");
			}
		});
		eventBus.addHandler(SobreEvent.TYPE, new SobreEventHandler() {
			public void onSobre(SobreEvent event) {
				History.newItem("sobre");
			}
		});
	}

	@Override
	public void go(HasWidgets container) {
		this.container = container;

		if ("".equals(History.getToken())) {
			History.newItem("apresentacao");
		} else {
			History.fireCurrentHistoryState();
		}
	}

	public void onValueChange(ValueChangeEvent<String> event) {
		token = event.getValue();

		if (token != null) {
			verificaSessao();
		}
	}

	private void loadMenu() {
		presenterMenu = new MenuPresenter(rpcService, eventBus, new MenuView());
		presenterMenu.getHpUsuario().setVisible(false);
		presenterMenu.go(RootPanel.get("menuHorizontal"));
	}

	private void loadMenuLateral() {
		Presenter presenter = new MenuLateralPresenter(rpcService, eventBus,
				new MenuLateralView());
		presenter.go(RootPanel.get("corpoDir"));
	}

	@Override
	public void go(){}

	public void infoUsuario() {
		presenterInfoUsuario = new InfoUsuarioPresenter(rpcService, eventBus,
				new InfoUsuarioView());
		presenterInfoUsuario.go(RootPanel.get("menuDir"));
	} 

	public void configurarMenu(){
		rpcService.getSession(new AsyncCallback<User>() {
			
			@Override
			public void onSuccess(User result) {
				presenterMenu.getHpUsuario().setVisible(true);
				presenterMenu.getNomeUsuario().setText(result.getNome_partic());
				presenterMenu.getLogin().setVisible(false);
				presenterMenu.getAtividades().setVisible(true);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				
			}
		});
	}
	
	public void verificaSessao() {
		rpcService.getSessao(new AsyncCallback<Boolean>() {
			@Override
			public void onSuccess(Boolean result) {
				if (result) {
					if(token.equals("apresentacao")){
//						presenterMenu.getHpUsuario().setVisible(true);
//						presenterMenu.getNomeUsuario().setText();
//						presenterMenu.getLogin().setVisible(false);
//						presenterMenu.getAtividades().setVisible(true);
//						RootPanel.get("menuDir").setVisible(true);
//						infoUsuario();
						configurarMenu();
						Presenter presenter = new ApresentationPresenter(rpcService, eventBus,
								new ApresentationView());
						if (presenter != null)
							presenter.go(container);
					}
					else if(token.equals("programacao")){
//						presenterMenu.getHpUsuario().setVisible(true);
//						presenterMenu.getLogin().setVisible(false);
//						presenterMenu.getAtividades().setVisible(true);
//						RootPanel.get("menuDir").setVisible(true);
//						infoUsuario();
						configurarMenu();
						Presenter presenter = new ProgramacaoPresenter(rpcService, eventBus,
								new ProgramacaoView());
						if (presenter != null)
							presenter.go(container);
					}
					else if(token.equals("login")){
//						presenterMenu.getHpUsuario().setVisible(true);
//						presenterMenu.getLogin().setVisible(false);
//						presenterMenu.getAtividades().setVisible(true);
//						RootPanel.get("menuDir").setVisible(true);
//						infoUsuario();
						configurarMenu();
						Presenter presenter = new UsuarioPresenter(rpcService, eventBus,
								new UsuarioView());
						if (presenter != null)
							presenter.go(container);
					}
					else if(token.equals("faleConosco")){
//						presenterMenu.getLogin().setVisible(false);
//						presenterMenu.getAtividades().setVisible(true);
//						RootPanel.get("menuDir").setVisible(true);
//						infoUsuario();
						configurarMenu();
						Presenter presenter = new FaleConoscoPresenter(rpcService, eventBus,
								new FaleConoscoView());
						if (presenter != null)
							presenter.go(container);
					}
					else if(token.equals("sobre")){
//						presenterMenu.getLogin().setVisible(false);
//						presenterMenu.getAtividades().setVisible(true);
//						RootPanel.get("menuDir").setVisible(true);
//						infoUsuario();
						configurarMenu();
						Presenter presenter = new SobrePresenter(new SobreView());
						if (presenter != null)
							presenter.go(container);
					}
				} else {
					if(token.equals("apresentacao")){
						//new LoadingPopup("Aguarde ...");
						loadMenu();
						presenterMenu.getHpUsuario().setVisible(false);
						presenterMenu.getLogin().setVisible(true);
						presenterMenu.getAtividades().setVisible(false);
//						RootPanel.get("menuDir").setVisible(false);
						Presenter presenter = new ApresentationPresenter(rpcService, eventBus,
								new ApresentationView());
						if (presenter != null)
							presenter.go(container);
					}
					else if(token.equals("programacao")){
						loadMenu();
						presenterMenu.getHpUsuario().setVisible(false);
						presenterMenu.getLogin().setVisible(true);
						presenterMenu.getAtividades().setVisible(false);
//						RootPanel.get("menuDir").setVisible(false);
						Presenter presenter = new ProgramacaoPresenter(rpcService, eventBus,
								new ProgramacaoView());
						if (presenter != null)
							presenter.go(container);
					}
					else if(token.equals("login")){
						loadMenu();
						presenterMenu.getHpUsuario().setVisible(false);
						presenterMenu.getLogin().setVisible(true);
						presenterMenu.getAtividades().setVisible(false);
//						RootPanel.get("menuDir").setVisible(false);
						Presenter presenter = new ApresentationPresenter(rpcService, eventBus,
								new ApresentationView());
						if (presenter != null)
							presenter.go(container);
					}
					else if(token.equals("faleConosco")){
						presenterMenu.getHpUsuario().setVisible(false);
						presenterMenu.getLogin().setVisible(true);
						presenterMenu.getAtividades().setVisible(false);
//						RootPanel.get("menuDir").setVisible(true);
						//infoUsuario();
						Presenter presenter = new FaleConoscoPresenter(rpcService, eventBus,
								new FaleConoscoView());
						if (presenter != null)
							presenter.go(container);
					}
					else if(token.equals("sobre")){
						presenterMenu.getHpUsuario().setVisible(false);
						presenterMenu.getLogin().setVisible(true);
						presenterMenu.getAtividades().setVisible(false);
//						RootPanel.get("menuDir").setVisible(true);
						//infoUsuario();
						Presenter presenter = new SobrePresenter(new SobreView());
						if (presenter != null)
							presenter.go(container);
					}
				}
			}

			@Override
			public void onFailure(Throwable caught) {
			}
		});
	}
}