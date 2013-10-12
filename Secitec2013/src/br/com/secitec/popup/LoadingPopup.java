package br.com.secitec.popup;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PopupPanel;

public class LoadingPopup {
	
	private final PopupPanel pp = new PopupPanel(false);
	
	public LoadingPopup(String mensagem){
		
		pp.setGlassEnabled(true);
		
		Image imgFechar = new Image();
		imgFechar.setUrl("images/ajax-loader.gif");
		imgFechar.setSize("20px", "20px");
		imgFechar.addStyleName("loadingPopup");
		imgFechar.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				pp.hide();
			}
		});
		//hpFechar.add(imgFechar);
		
		
		HorizontalPanel hp = new HorizontalPanel();
		hp.setSpacing(30);
		hp.add(imgFechar);
		
		HTML h = new HTML(mensagem);
		h.removeStyleName("gwt-HTML");
		h.addStyleName("loadingPopup");
		
		hp.add(h);
		//hp.setWidth("100px");
		hp.setHeight("40px");
		
		pp.addStyleName("demo-popup");
		pp.add(hp);
		
		pp.center();
	}
	
	public void hide(){
		pp.hide();
	}

}
