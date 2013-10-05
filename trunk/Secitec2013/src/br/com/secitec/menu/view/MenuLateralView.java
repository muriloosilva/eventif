package br.com.secitec.menu.view;

import br.com.secitec.menu.presenter.MenuLateralPresenter;

import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class MenuLateralView extends Composite implements MenuLateralPresenter.Display{

	private VerticalPanel vp;
	private Anchor inscrever;
	
	public MenuLateralView(){
		vp = new VerticalPanel();
		initWidget(vp);
		vp.setBorderWidth(0);
		vp.setSpacing(5);
		vp.setVerticalAlignment(VerticalPanel.ALIGN_MIDDLE);
		vp.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		vp.setWidth("100%");
		vp.setHeight("100%");
		vp.addStyleName("vpMainPageContent");
		
		Image img = new Image();
		img.setUrl("images/inscreva-se.png");
		img.setTitle("inscreva-se");
		img.setHeight("100px");
		img.setWidth("100px");
		
		Button b = new Button();
		inscrever = new Anchor();
		inscrever.setHref("www.google.com.br");
		inscrever.setLayoutData(img);
		
		FlowPanel fp = new FlowPanel();
		fp.setWidth("auto");
		fp.setHeight("auto");
		//fp.add(img);
		
		vp.add(fp);
	}

	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		return this;
	}

}
