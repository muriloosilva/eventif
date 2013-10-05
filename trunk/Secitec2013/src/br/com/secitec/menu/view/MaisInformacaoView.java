package br.com.secitec.menu.view;

import br.com.secitec.menu.presenter.MaisInformacaoPresenter;
import br.com.secitec.shared.model.Atividade;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class MaisInformacaoView extends Composite implements MaisInformacaoPresenter.Display{

	private PopupPanel popup;
	private VerticalPanel vpMain;
	private VerticalPanel vpConteudo;
	private HorizontalPanel hpRodape;
	private Button voltar;
	private Button inscrever;
	
	public MaisInformacaoView(String bt){
		montaPopup(bt);
//		popup.center();
	}
	
	public void montaPopup(String bt){
		popup = new PopupPanel(true);
		popup.setStyleName("demo-popup");

		vpMain = new VerticalPanel();
		vpMain.setBorderWidth(2);
		vpMain.setSpacing(5);
		vpMain.setVerticalAlignment(VerticalPanel.ALIGN_MIDDLE);
		vpMain.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		vpMain.setWidth("1000px");
		vpMain.setHeight("680px");
		vpMain.addStyleName("vpMainPageContent");
		
		vpConteudo = new VerticalPanel();
		vpConteudo.setBorderWidth(2);
		vpConteudo.setSpacing(5);
		vpConteudo.setVerticalAlignment(VerticalPanel.ALIGN_MIDDLE);
		vpConteudo.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		vpConteudo.setWidth("1000px");
		vpConteudo.setHeight("400px");
		
		hpRodape = new HorizontalPanel();
		hpRodape.setBorderWidth(2);
		hpRodape.setSpacing(5);
		hpRodape.setVerticalAlignment(VerticalPanel.ALIGN_MIDDLE);
		hpRodape.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		hpRodape.setWidth("1000px");
		hpRodape.setHeight("180px");
		
		voltar = new Button();
		voltar.setText("Voltar");
		
		voltar.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				popup.hide();
			}
		});
		
		inscrever = new Button();
		inscrever.setText(bt);
		
		hpRodape.add(inscrever);
		hpRodape.add(voltar);
		
		vpMain.add(vpConteudo);
		vpMain.add(hpRodape);
		popup.add(vpMain);
		popup.setGlassEnabled(true);
	}
	
	public PopupPanel getPopup(){
		return this.popup;
	}

	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setData(Atividade atividade) {
		// TODO Auto-generated method stub
		vpConteudo.add(new HTML("Atividade: "+atividade.getNomeAtiv()));
		vpConteudo.add(new HTML("<br/>Descrição: "+atividade.getDescAtiv()));
		vpConteudo.add(new HTML("<br/>Data: "+atividade.getDtAtiv()));
	}

	@Override
	public Button getInscrever() {
		// TODO Auto-generated method stub
		return this.inscrever;
	}

}
