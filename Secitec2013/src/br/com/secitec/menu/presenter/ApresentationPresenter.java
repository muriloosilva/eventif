package br.com.secitec.menu.presenter;

import java.util.List;

import br.com.secitec.client.RPCServiceAsync;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class ApresentationPresenter implements Presenter {

	public interface Display {
		void setData(List<String> data);
		Widget asWidget();
	}

	private final RPCServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;

	public ApresentationPresenter(RPCServiceAsync rpcService,
			HandlerManager eventBus, Display view) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
	}

	public void bind() {

		/*
		 * display.getDeleteButton().addClickHandler(new ClickHandler() { public
		 * void onClick(ClickEvent event) { deleteSelectedContacts(); } });
		 */

	}

	public void go(final HasWidgets container) {
		bind();
		container.clear();
		container.add(display.asWidget());
		// se necessário, buscar o conteúdo da pagina no BD por aqui
	}

	@Override
	public void go() {
		// TODO Auto-generated method stub
		
	}

}
