package br.com.secitec.menu.presenter;

import java.util.List;

import br.com.secitec.client.RPCServiceAsync;
import br.com.secitec.menu.event.ApresentationEvent;
import br.com.secitec.shared.model.User;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class InfoUsuarioPresenter implements Presenter{
	public interface Display {

		Label getLbNomeUsuario();
		
		Anchor getSair();
		
		void setData(List<String> data);

		Widget asWidget();
	}

	private final RPCServiceAsync rpcService;
	private final HandlerManager eventBus;
	private final Display display;

	public InfoUsuarioPresenter(RPCServiceAsync rpcService,
			HandlerManager eventBus, Display view) {
		this.rpcService = rpcService;
		this.eventBus = eventBus;
		this.display = view;
	}

	public void bind() {
		display.getSair().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				rpcService.removeSessao(new AsyncCallback<Void>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(Void result) {
						// TODO Auto-generated method stub
						RootPanel.get("menuDir").setVisible(false);
						String s = History.getToken();
						if(s.equals("apresentacao"))
							Window.Location.reload();
						else
							eventBus.fireEvent(new ApresentationEvent("apresentacao"));
						//Window.Location.replace("http://127.0.0.1:8888/SecitecGWT.html?gwt.codesvr=127.0.0.1:9997#apresentacao");
					}
				});
			}
		});
	}

	public void go(final HasWidgets container) {
		rpcService.getSession(new AsyncCallback<User>() {
			
			@Override
			public void onSuccess(User result) {
				// TODO Auto-generated method stub
				display.getLbNomeUsuario().setText(result.getNome_partic());
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
		bind();
		container.clear();
		container.add(display.asWidget());
	}

	@Override
	public void go() {
		// TODO Auto-generated method stub
		
	}
	
	public Widget getDisplay(){
		return this.getDisplay();
	}
}