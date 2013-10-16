package br.com.secitec.menu.presenter;

import br.com.secitec.client.RPCServiceAsync;
import br.com.secitec.menu.event.LoginEvent;
import br.com.secitec.menu.view.LoginView;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class MenuLateralPresenter implements Presenter {

	public interface Display {

		Anchor getLogin();
		
		Anchor getMinhasAtividades();
		
		Widget asWidget();
	}

	private final RPCServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;

	public MenuLateralPresenter(RPCServiceAsync rpcService,
			HandlerManager eventBus, Display view) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
	}

	public void bind(){
		display.getLogin().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Presenter presenter = new LoginPresenter(rpcService, eventBus,
						new LoginView());
				if (presenter != null)
					presenter.go();
			}
		});

		display.getMinhasAtividades().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				eventBus.fireEvent(new LoginEvent("login"));
			}
		});
	}
	
	@Override
	public void go(HasWidgets container) {
		// TODO Auto-generated method stub
		bind();
		container.clear();
		container.add(display.asWidget());
	}

	@Override
	public void go() {
		// TODO Auto-generated method stub
		
	}
	
	public Anchor getLogin(){
		return display.getLogin();
	}
	
	public Anchor getMinhasAtividades(){
		return display.getMinhasAtividades();
	}

}
