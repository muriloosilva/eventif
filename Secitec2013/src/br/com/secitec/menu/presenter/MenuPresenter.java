package br.com.secitec.menu.presenter;

import java.util.List;

import br.com.secitec.client.RPCServiceAsync;
import br.com.secitec.menu.event.ApresentationEvent;
import br.com.secitec.menu.event.LoginEvent;
import br.com.secitec.menu.event.ProgramacaoEvent;
import br.com.secitec.menu.view.LoginView;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class MenuPresenter implements Presenter {

	public interface Display {
		HasClickHandlers getApresentationLabel();

		HasClickHandlers getProgramacaoLabel();

		HasClickHandlers getLoginLabel();
		
		HasClickHandlers getAtividadesLabel();

		void setData(List<String> data);

		Widget asWidget();
	}

	private final RPCServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;

	public MenuPresenter(RPCServiceAsync rpcService,
			HandlerManager eventBus, Display view) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
	}

	public void bind() {
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
		
		display.getLoginLabel().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Presenter presenter = new LoginPresenter(rpcService, eventBus,
						new LoginView());
				if (presenter != null)
					presenter.go();
			}
		});
		
		display.getAtividadesLabel().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				eventBus.fireEvent(new LoginEvent("login"));
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
	
	public Anchor getLogin(){
		return (Anchor)display.getLoginLabel();
	}
	
	public Anchor getAtividades(){
		return (Anchor)display.getAtividadesLabel();
	}
}