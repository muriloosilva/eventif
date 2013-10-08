package br.com.secitec.content;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class PageContent extends Composite {

	private String title = "";
	private HTML titlePage = null;
	private SimplePanel contentPage = null;

	public PageContent() {
		init();
	}

	public PageContent(String title) {
		this.title = title;
		init();
	}

	public void init() {
		VerticalPanel vpMain = new VerticalPanel();
		initWidget(vpMain);
		vpMain.setBorderWidth(0);
		vpMain.setSpacing(5);
		vpMain.setVerticalAlignment(VerticalPanel.ALIGN_MIDDLE);
		vpMain.setHorizontalAlignment(HorizontalPanel.ALIGN_LEFT);
		vpMain.setWidth("100%");
		vpMain.setHeight("100%");
		vpMain.addStyleName("vpMainPageContent");

		HorizontalPanel hpTop = new HorizontalPanel();
		hpTop.setBorderWidth(0);
		hpTop.setSpacing(0);
		hpTop.setVerticalAlignment(VerticalPanel.ALIGN_MIDDLE);
		hpTop.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		hpTop.setWidth("100%");
		hpTop.setHeight("24px");

		titlePage = new HTML(title);
		titlePage.setStyleName("lbTitle");
		hpTop.add(titlePage);

		HorizontalPanel hpMiddle = new HorizontalPanel();
		hpMiddle.setBorderWidth(0);
		hpMiddle.setSpacing(0);
		hpMiddle.setVerticalAlignment(VerticalPanel.ALIGN_MIDDLE);
		hpMiddle.setHorizontalAlignment(HorizontalPanel.ALIGN_LEFT);
		hpMiddle.setWidth("100%");
		hpMiddle.addStyleName("hpMiddlePageContent");

		contentPage = new SimplePanel();
		hpMiddle.add(contentPage);

		vpMain.add(hpTop);
		vpMain.add(hpMiddle);
	}

	public void setTitle(String title) {
		this.title = title;
		titlePage.setHTML(title);

	}

	public void setContent(Widget content) {
		contentPage.add(content);
	}
}