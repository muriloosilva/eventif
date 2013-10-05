package br.com.secitec.shared;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class InformacaoPopup{

	private PopupPanel tela;
	private VerticalPanel vp;
	private Button bt;
	
	public InformacaoPopup(){
		
	}
	
	public void informacao(String msg){
		tela = new PopupPanel(true);
		tela.setStyleName("demo-popup");
		
		vp = new VerticalPanel();
		vp.setBorderWidth(2);
		vp.setSpacing(5);
		vp.setVerticalAlignment(VerticalPanel.ALIGN_MIDDLE);
		vp.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		vp.setWidth("400");
		vp.setHeight("300px");
		vp.addStyleName("vpMainPageContent");
		vp.add(new HTML(msg));
		
		bt = new Button();
		bt.setText("OK");
		bt.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				tela.hide();
			}
		});
		
		vp.add(bt);
		
		tela.add(vp);
		tela.setGlassEnabled(true);
//		tela.setStyleName("popup-panel");
		tela.center();
	}
	
	public static void mensagemErro(String erro) {
		PopupPanel popup = new PopupPanel(false);
		VerticalPanel panel = new VerticalPanel();
		Label text = new Label(erro); 
		popup.setStyleName("popup-panel");
//		popup.setHorizontalAlignment(HasAlignment.ALIGN_CENTER);
		popup.setGlassEnabled(true);
		 popup.setAutoHideEnabled(true);
		popup.setWidget(panel);
		popup.center();
		popup.center();
	}	
}