package br.com.secitec.menu.view;

import br.com.secitec.menu.presenter.AdminPresenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class AdminView extends Composite implements
AdminPresenter.Display{

	private PopupPanel tela;
	private VerticalPanel vp;
	private FlexTable tabela;
	private Button cadastrar;
	private Button mais;
	private FlexTable tabelaData;
	
	public AdminView() {
		tela = new PopupPanel(false);
		tela.setStyleName("demo-popup");

		vp = new VerticalPanel();
		vp.setBorderWidth(0);
		vp.setSpacing(0);
		vp.setVerticalAlignment(VerticalPanel.ALIGN_MIDDLE);
		vp.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		vp.setWidth("650px");
		
		HorizontalPanel hpTop = new HorizontalPanel();
		hpTop.setSpacing(0);
		hpTop.setWidth("450px");
		hpTop.setHeight("10px");
		hpTop.setHorizontalAlignment(HorizontalPanel.ALIGN_RIGHT);
		hpTop.setVerticalAlignment(VerticalPanel.ALIGN_TOP);

		HorizontalPanel hpTitulo = new HorizontalPanel();
		hpTitulo.setSpacing(0);
		hpTitulo.setWidth("450px");
		hpTitulo.setHeight("40px");
		hpTitulo.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		hpTitulo.setVerticalAlignment(VerticalPanel.ALIGN_MIDDLE);
		
		HTML titulo = new HTML("Cadastrar Atividade");
		titulo.addStyleName("titulo");
		hpTitulo.add(titulo);
		
		Image imgFechar = new Image();
		imgFechar.setUrl("images/fechar.png");
		imgFechar.setSize("20px", "20px");
		imgFechar.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				tela.hide();
			}
		});
		//hpFechar.add(imgFechar);
		hpTop.add(imgFechar);
		
		tabela = new FlexTable();
		tabela = tabela(tabela);
		preencheTabela(tabela);
		
		HorizontalPanel hpRodape = new HorizontalPanel();
		hpRodape.setHeight("60px");
		hpRodape.setWidth("450px");
		hpRodape.setSpacing(0);
		hpRodape.setHorizontalAlignment(HorizontalPanel.ALIGN_RIGHT);
		
		HorizontalPanel hpFaleConosco = new HorizontalPanel();
		hpFaleConosco.setSpacing(0);
		hpFaleConosco.setHeight("40px");
		hpFaleConosco.setWidth("79px");
		hpFaleConosco.setHorizontalAlignment(HorizontalPanel.ALIGN_LEFT);
		
		cadastrar = new Button();
		cadastrar.setText("Cadastrar");
		cadastrar.setHeight("40px");
		
		hpFaleConosco.add(cadastrar);
		
		hpRodape.add(hpFaleConosco);
		
		vp.add(hpTop);
		vp.add(hpTitulo);
		vp.add(tabela);
		vp.add(hpRodape);
		tela.add(vp);
		tela.setGlassEnabled(true);
	}

	private void preencheTabela(FlexTable tb) {
		HTML hNome = new HTML("Nome: ");
		hNome.addStyleName("alignDir");
		tb.setWidget(0, 0, hNome);
		TextBox nome = new TextBox();
		nome.setWidth("336px");
		tb.setWidget(0, 1, nome);
		
		HTML hDescricao = new HTML("Descrição: ");
		hDescricao.addStyleName("alignDir");
		tb.setWidget(1, 0, hDescricao);
		TextArea descricao = new TextArea();
		descricao.getElement().getStyle().setProperty("maxWidth","340px");
		descricao.getElement().getStyle().setProperty("maxHeight","200px");
		descricao.getElement().getStyle().setProperty("minWidth","340px");
		descricao.getElement().getStyle().setProperty("minHeight","200px");
		descricao.setWidth("340px");
		descricao.setHeight("200px");
		tb.setWidget(1, 1, descricao);

		HTML hVagas = new HTML("Vagas:");
		hVagas.addStyleName("alignDir");
		tb.setWidget(2, 0, hVagas);
		TextBox vagas = new TextBox();
		vagas.setWidth("336px");
		tb.setWidget(2, 1, vagas);
		
		HTML hTipo = new HTML("Tipo:");
		hTipo.addStyleName("alignDir");
		tb.setWidget(3, 0, hTipo);
		ListBox tipo = new ListBox();
		tipo.insertItem("Oficina", 0);
		tipo.insertItem("Minicurso", 1);
		tipo.insertItem("Palestra", 2);
		tipo.setWidth("336px");
		tb.setWidget(3, 1, tipo);
		
		HTML hData = new HTML("Data: ");
		hData.addStyleName("alignDir");
		
		tb.setWidget(4, 0, hData);
		
		tabelaData = new FlexTable();
		tabelaData.setWidth("350px");
		//tabelaData.setHeight("80px");
		tabelaData.getColumnFormatter().setWidth(0, "50px");
//		tb.getColumnFormatter().addStyleName(0, "alignDir");
		tabelaData.getColumnFormatter().setWidth(1, "50px");
		tabelaData.getColumnFormatter().setWidth(2, "50px");
		tabelaData.getColumnFormatter().setWidth(3, "50px");
		tabelaData.getColumnFormatter().setWidth(4, "50px");
		tabelaData.getColumnFormatter().setWidth(5, "50px");
		tabelaData.getColumnFormatter().setWidth(6, "50px");
		tabelaData.getColumnFormatter().setWidth(7, "50px");
		tabelaData.setBorderWidth(0);
		tabelaData.addStyleName("tabelaLogin");
		
		addData(0, tabelaData);
		
		tb.setWidget(4, 1, tabelaData);
		
		mais = new Button();
		mais.setText("+");
		
		tb.setWidget(4, 2, mais);
		
	}
	
	private void addData(int linha, FlexTable ft){
		
		TextBox data = new TextBox();
		data.setWidth("80px");
		ft.setWidget(linha, 0, data);
		
		HTML hHoraInicio = new HTML("Hora Ini.: ");
		hHoraInicio.addStyleName("alignDir");
		ft.setWidget(linha, 1, hHoraInicio);
		TextBox horaInicio = new TextBox();
		horaInicio.setWidth("50px");
		ft.setWidget(linha, 2, horaInicio);
		
		HTML hHoraFim = new HTML("Hora Fim: ");
		hHoraFim.addStyleName("alignDir");
		ft.setWidget(linha, 3, hHoraFim);
		TextBox horaFim = new TextBox();
		horaFim.setWidth("50px");
		ft.setWidget(linha, 4, horaFim);
		
		
	}

	private FlexTable tabela(FlexTable tb) {
		tb.setWidth("450px");
		tb.setHeight("80px");
		tb.getColumnFormatter().setWidth(0, "50px");
//		tb.getColumnFormatter().addStyleName(0, "alignDir");
		tb.getColumnFormatter().setWidth(1, "240px");
		tb.setBorderWidth(0);
		tb.addStyleName("tabelaLogin");

		return tb;
	}
	
	
	public PopupPanel getPopup(){
		return this.tela;
	}

	@Override
	public Button getCadastrar() {
		return cadastrar;
	}

	@Override
	public TextArea getTbDescricao() {
		return (TextArea) tabela.getWidget(1, 1);
	}

	@Override
	public TextBox getTbVagas() {
		return (TextBox) tabela.getWidget(2, 1);
	}

	@Override
	public ListBox getLbTipo() {
		return (ListBox) tabela.getWidget(3, 1);
	}

	@Override
	public FlexTable getFlexTableDatas() {
		return (FlexTable) tabela.getWidget(4, 1);
	}

	@Override
	public TextBox getTbNome() {
		return (TextBox) tabela.getWidget(0, 1);
	}

	@Override
	public Button getAddData() {
		return mais;
	}

	@Override
	public void addData(int l) {
		addData(l, tabelaData);
		
	}
}