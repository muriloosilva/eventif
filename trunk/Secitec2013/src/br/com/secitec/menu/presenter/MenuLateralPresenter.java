package br.com.secitec.menu.presenter;

import br.com.secitec.client.RPCServiceAsync;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class MenuLateralPresenter implements Presenter {

	public interface Display {

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

	@Override
	public void go(HasWidgets container) {
		// TODO Auto-generated method stub
		container.clear();
		container.add(display.asWidget());
	}

	@Override
	public void go() {
		// TODO Auto-generated method stub
		
	}

}
