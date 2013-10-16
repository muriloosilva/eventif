package br.com.secitec.popup;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ConfirmacaoPopup {
	private PopupPanel tela;
	private VerticalPanel vp;
	private Button btSim, btNao;
	private Image imgFechar;
	
	public ConfirmacaoPopup(String msg){
		tela = new PopupPanel(false);
		tela.setStyleName("demo-popup");
		
		vp = new VerticalPanel();
		vp.setBorderWidth(0);
		vp.setSpacing(0);
		vp.setVerticalAlignment(VerticalPanel.ALIGN_MIDDLE);
		vp.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		vp.setWidth("300px");
		
		HorizontalPanel hpTop = new HorizontalPanel();
		hpTop.setSpacing(0);
		hpTop.setWidth("300px");
//		hpTop.addStyleName("botaoFechar");
		hpTop.setHorizontalAlignment(HorizontalPanel.ALIGN_RIGHT);
		hpTop.setVerticalAlignment(VerticalPanel.ALIGN_TOP);
		
		imgFechar = new Image();
		imgFechar.setUrl("images/fechar.png");
		imgFechar.setSize("20px", "20px");
		hpTop.add(imgFechar);
		
		HorizontalPanel hpMsg = new HorizontalPanel();
		hpMsg.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		hpMsg.setWidth("300px");
		hpMsg.addStyleName("hpMsg");
		
		HTML h = new HTML(msg);
		hpMsg.add(h);
		
		HorizontalPanel hpBt = new HorizontalPanel();
		hpBt.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		hpBt.setWidth("300px");
		hpBt.setSpacing(5);
		hpBt.addStyleName("hpBt");
		
		btSim = new Button();
		btSim.setText("Sim");
		hpBt.add(btSim);
		
		btNao = new Button();
		btNao.setText("Não");
		hpBt.add(btNao);
		
		vp.add(hpTop);
		vp.add(hpMsg);
		vp.add(hpBt);		
		tela.add(vp);
		tela.setGlassEnabled(true);
	}
	
	public PopupPanel getTela(){
		return this.tela;
	}
	
	public Button getSim(){
		return this.btSim;
	}
	
	public Button getBtNao(){
		return this.btNao;
	}
	
	public Image getFechar(){
		return this.imgFechar;
	}
}
