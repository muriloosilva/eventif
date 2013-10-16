package br.com.secitec.menu.presenter;

import java.util.List;

import br.com.secitec.client.RPCServiceAsync;
import br.com.secitec.menu.event.ApresentationEvent;
import br.com.secitec.menu.event.LoginEvent;
import br.com.secitec.menu.event.ProgramacaoEvent;
import br.com.secitec.menu.view.FaleConoscoView;
import br.com.secitec.menu.view.LoginView;
import br.com.secitec.menu.view.SobreView;
import br.com.secitec.popup.LoadingPopup;
import br.com.secitec.popup.VisualizarAlterarDadosPopup;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class MenuPresenter implements Presenter {

	public interface Display {
		HasClickHandlers getApresentationLabel();

		HasClickHandlers getProgramacaoLabel();

		HasClickHandlers getLoginLabel();

		HasClickHandlers getAtividadesLabel();

		HorizontalPanel getHpUsuario();
		
		HasClickHandlers getFaleConosco();
		
		HasClickHandlers getSobre();
		
		Label getLbUsuario();

		Anchor getSair();
		
		Anchor getNomeUsuario();

		void setData(List<String> data);

		Widget asWidget();
	}

	private final RPCServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;

	public MenuPresenter(RPCServiceAsync rpcService, HandlerManager eventBus,
			Display view) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
	}

	public void bind() {
		
		display.getNomeUsuario().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				final VisualizarAlterarDadosPopup vadp =new VisualizarAlterarDadosPopup(rpcService, eventBus);
				vadp.getTela().center();
				vadp.getFechar().addClickHandler(new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						vadp.getTela().hide();
					}
				});
			}
		});
		
		display.getApresentationLabel().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new ApresentationEvent("apresentacao"));
			}
		});

		display.getProgramacaoLabel().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new ProgramacaoEvent("programacao"));
			}
		});

//		display.getLoginLabel().addClickHandler(new ClickHandler() {
//
//			@Override
//			public void onClick(ClickEvent event) {
//				Presenter presenter = new LoginPresenter(rpcService, eventBus,
//						new LoginView());
//				if (presenter != null)
//					presenter.go();
//			}
//		});

//		display.getAtividadesLabel().addClickHandler(new ClickHandler() {
//
//			@Override
//			public void onClick(ClickEvent event) {
//				// TODO Auto-generated method stub
//				eventBus.fireEvent(new LoginEvent("login"));
//			}
//		});
		
		display.getFaleConosco().addClickHandler(new ClickHandler(){
			
			@Override
			public void onClick(ClickEvent event) {
				Presenter presenter = new FaleConoscoPresenter(rpcService, eventBus,
						new FaleConoscoView());
				if (presenter != null)
					presenter.go();
				
			}
		});
		
		display.getSobre().addClickHandler(new ClickHandler(){
			
			@Override
			public void onClick(ClickEvent event) {
				Presenter presenter = new SobrePresenter(new SobreView());
				if (presenter != null)
					presenter.go();
				
			}
		});

		display.getSair().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
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
//						RootPanel.get("menuDir").setVisible(false);
						String s = History.getToken();
						if (s.equals("apresentacao")){
//							Window.Location.reload();
//							Anchor a = (Anchor) display.getAtividadesLabel();
//							a.setVisible(false);
//							Anchor b = (Anchor) display.getLoginLabel();
//							b.setVisible(true);
							display.getHpUsuario().setVisible(false);
						}
						else
							eventBus.fireEvent(new ApresentationEvent(
									"apresentacao"));
						// Window.Location.replace("http://127.0.0.1:8888/SecitecGWT.html?gwt.codesvr=127.0.0.1:9997#apresentacao");
					}
				});
			}
		});
	}

	public void go(final HasWidgets container) {
		bind();
		container.clear();
		container.add(display.asWidget());
	}

	@Override
	public void go() {
		// TODO Auto-generated method stub

	}

	public Anchor getLogin() {
		return (Anchor) display.getLoginLabel();
	}

	public HorizontalPanel getHpUsuario() {
		return display.getHpUsuario();
	}

	public Anchor getAtividades() {
		return (Anchor) display.getAtividadesLabel();
	}
	
	public Anchor getNomeUsuario(){
		return display.getNomeUsuario();
	}
}