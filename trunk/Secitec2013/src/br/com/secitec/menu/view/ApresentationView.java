package br.com.secitec.menu.view;

import java.util.List;

import br.com.secitec.content.PageContent;
import br.com.secitec.menu.presenter.ApresentationPresenter;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ApresentationView extends Composite implements
		ApresentationPresenter.Display {

	private PageContent apstViewPage;

	public ApresentationView() {
		apstViewPage = new PageContent("Apresentação");
		apstViewPage.setContent(new HTML("Teste"));
		initWidget(apstViewPage);
	}

	public void setData(List<String> data) {

	}

	public Widget asWidget() {
		return this;
	}
}
