package br.com.secitec.menu.view;

import br.com.secitec.menu.presenter.MaisInformacaoPresenter;
import br.com.secitec.shared.model.Atividade;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class MaisInformacaoView extends Composite implements MaisInformacaoPresenter.Display{

	private PopupPanel popup;
	private VerticalPanel vpMain;
	private HorizontalPanel hpRodape, hpTop, hpFechar;
	private Button inscrever;
	private HTML titulo;
	private HTML nome;
	private HTML desc;
	private HTML data;
	private HTML hrIni;
	private HTML hrFim;
	private HTML vagas;
	private HTML vagasDispo;
	private FlexTable tabela;
	
	public MaisInformacaoView(String bt){
		montaPopup(bt);
//		popup.center();
	}
	
	public void montaPopup(String bt){
		popup = new PopupPanel(true);
		popup.setStyleName("demo-popup");

		vpMain = new VerticalPanel();
		vpMain.setBorderWidth(0);
		vpMain.setSpacing(0);
		vpMain.setVerticalAlignment(VerticalPanel.ALIGN_MIDDLE);
		vpMain.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		vpMain.setWidth("500px");
//		vpMain.setHeight("680px");
//		vpMain.addStyleName("vpMainPageContent");
		
		hpTop = new HorizontalPanel();
		hpTop.setSpacing(0);
		hpTop.setWidth("300px");
		hpTop.setHeight("30px");
		hpTop.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		
		titulo = new HTML();
		titulo.addStyleName("titulo");
		hpTop.add(titulo);
		
		hpFechar = new HorizontalPanel();
		hpFechar.setSpacing(0);
		hpFechar.setWidth("20px");
		hpFechar.addStyleName("botaoFecharMaisInformacao");
		hpFechar.setHorizontalAlignment(HorizontalPanel.ALIGN_RIGHT);
		
		Image imgFechar = new Image();
		imgFechar.setUrl("images/fechar.png");
		imgFechar.setSize("20px", "20px");
		imgFechar.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				popup.hide();
			}
		});
		hpFechar.add(imgFechar);
		hpTop.add(hpFechar);
		
		tabela = new FlexTable();
		tabela = tabela(tabela);
		preencheTabela(tabela);
		
		inscrever = new Button();
		inscrever.setText(bt);
		
//		hpRodape.add(inscrever);
		
		vpMain.add(hpTop);
		vpMain.add(tabela);
//		vpMain.add(hpRodape);
		popup.add(vpMain);
		popup.setGlassEnabled(true);
	}
	
	private void preencheTabela(FlexTable tb) {
		HTML h = new HTML("Atividade:");
		h.addStyleName("alignDir");
		tb.setWidget(0, 0, h);
		nome = new HTML();
		tb.setWidget(0, 1, nome);

		HTML h1 = new HTML("Descri��o:");
		h1.addStyleName("alignDir");
		tb.setWidget(1, 0, h1);
		desc = new HTML();
		tb.setWidget(1, 1, desc);

		HTML h2 = new HTML("Data:");
		h2.addStyleName("alignDir");
		tb.setWidget(2, 0, h2);
		data = new HTML();
		tb.setWidget(2, 1, data);

		HTML h3 = new HTML("Hora de in�cio:");
		h3.addStyleName("alignDir");
		tb.setWidget(3, 0, h3);
		hrIni = new HTML();
		tb.setWidget(3, 1, hrIni);

		HTML h4 = new HTML("Hora de t�rmino:");
		h4.addStyleName("alignDir");
		tb.setWidget(4, 0, h4);
		hrFim = new HTML();
		tb.setWidget(4, 1, hrFim);

		HTML h5 = new HTML("Vagas:");
		h5.addStyleName("alignDir");
		tb.setWidget(5, 0, h5);
		vagas = new HTML();
		tb.setWidget(5, 1, vagas);
		
		HTML h6 = new HTML("Vagas dispon�veis:");
		h6.addStyleName("alignDir");
		tb.setWidget(6, 0, h6);
		vagasDispo = new HTML();
		tb.setWidget(5, 1, vagasDispo);
	}

	private FlexTable tabela(FlexTable tb) {
		tb.setWidth("400px");
		tb.setHeight("80px");
		tb.getColumnFormatter().setWidth(0, "50px");
		tb.getColumnFormatter().setWidth(1, "260px");
		tb.setBorderWidth(0);
		tb.addStyleName("tabelaCadastro");

		return tb;
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
		titulo.setText(atividade.getNomeAtiv());
		nome.setText(atividade.getNomeAtiv());
		desc.setText(atividade.getDescAtiv());
		data.setText(""+atividade.getDtAtiv());
		hrIni.setText(""+atividade.getHrInicio());
		hrFim.setText(""+atividade.getHrFim());
		vagas.setText(""+atividade.getVagasAtiv());
		vagasDispo.setText(""+atividade.getVagasDisponiveis());
	}

	@Override
	public Button getInscrever() {
		// TODO Auto-generated method stub
		return this.inscrever;
	}

}
