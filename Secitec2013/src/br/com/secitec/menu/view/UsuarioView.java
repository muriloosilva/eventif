package br.com.secitec.menu.view;

import java.util.List;

import br.com.secitec.menu.presenter.UsuarioPresenter;
import br.com.secitec.shared.model.Atividade;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class UsuarioView extends Composite implements UsuarioPresenter.Display{

	private VerticalPanel main;
	private VerticalPanel usuario;
	private VerticalPanel oficinas;
	private VerticalPanel minicursos;
	private VerticalPanel palestras;
	private VerticalPanel conteudoUsuario;
	private VerticalPanel conteudoOficinas;
	private VerticalPanel conteudoMinicursos;
	private VerticalPanel conteudoPalestras;
	private FlexTable tabelaUsuario;
	private FlexTable tabelaOficinas;
	private FlexTable tabelaMinicursos;
	private FlexTable tabelaPalestras;

	public UsuarioView() {
		//String value = Window.Location.getParameter("param");
		main = new VerticalPanel();
		initWidget(main);
		main.setBorderWidth(0);
		main.setSpacing(5);
		main.setVerticalAlignment(VerticalPanel.ALIGN_MIDDLE);
		main.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		main.setWidth("100%");
		main.setHeight("100%");
		main.addStyleName("vpMainPageContent");
		
		HorizontalPanel hpTop = new HorizontalPanel();
		hpTop.setBorderWidth(0);
		hpTop.setSpacing(0);
		hpTop.setVerticalAlignment(VerticalPanel.ALIGN_MIDDLE);
		hpTop.setHorizontalAlignment(HorizontalPanel.ALIGN_LEFT);
		hpTop.setWidth("100%");
		hpTop.setHeight("24px");

		HTML titlePage = new HTML("Área do Participante");
		titlePage.setStyleName("lbTitle");
		hpTop.add(titlePage);
		
		usuario =  new VerticalPanel();
		tabelaUsuario = new FlexTable();
		montarPainel(usuario, conteudoUsuario, "Dados do Usuario", tabelaUsuario);
		
		oficinas = new VerticalPanel();
		tabelaOficinas = new FlexTable();
		montarPainel(oficinas, conteudoOficinas, "Oficinas", tabelaOficinas);

		minicursos = new VerticalPanel();
		tabelaMinicursos = new FlexTable();
		montarPainel(minicursos, conteudoMinicursos, "Minicursos",
				tabelaMinicursos);

		palestras = new VerticalPanel();
		tabelaPalestras = new FlexTable();
		montarPainel(palestras, conteudoPalestras, "Palestras", tabelaPalestras);

		VerticalPanel hpUsuario = new VerticalPanel();
		hpUsuario.setBorderWidth(0);
		hpUsuario.setSpacing(0);
		hpUsuario.setVerticalAlignment(VerticalPanel.ALIGN_MIDDLE);
		hpUsuario.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		hpUsuario.setWidth("100%");
		hpUsuario.setHeight("auto");

		titlePage = new HTML("Atividades Inscritas");
		titlePage.setStyleName("lbTitle");
		hpUsuario.add(titlePage);
		
		hpUsuario.add(usuario);
		
		VerticalPanel hpAtividades = new VerticalPanel();
		hpAtividades.setBorderWidth(0);
		hpAtividades.setSpacing(0);
		hpAtividades.setVerticalAlignment(VerticalPanel.ALIGN_MIDDLE);
		hpAtividades.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		hpAtividades.setWidth("100%");
		hpAtividades.setHeight("auto");

		titlePage = new HTML("Atividades em aberto");
		titlePage.setStyleName("lbTitle");
		hpAtividades.add(titlePage);
		
		hpAtividades.add(oficinas);
		hpAtividades.add(minicursos);
		hpAtividades.add(palestras);
		
		main.add(hpTop);
		main.add(hpUsuario);
		main.add(hpAtividades);
//		main.add(usuario);
//		main.add(oficinas);
//		main.add(minicursos);
//		main.add(palestras);
	}

	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		return this;
	}
	
	@Override
	public void setData(List<Atividade> oficinas, List<Atividade> minicursos,
			List<Atividade> palestras) {
		preencheTabela(tabelaOficinas, oficinas);
		preencheTabela(tabelaMinicursos, minicursos);
		preencheTabela(tabelaPalestras, palestras);
	}
	
	@Override
	public void setDataUsuario(List<Atividade> atividades) {
		// TODO Auto-generated method stub
		preencheTabelaUsuario(tabelaUsuario, atividades);
	}

	public int[] getUsuario(ClickEvent event) {
		return getAtividade(event, tabelaUsuario);
	}
	
	@Override
	public int[] getOficina(ClickEvent event) {
		return getAtividade(event, tabelaOficinas);
	}

	@Override
	public int[] getMinicurso(ClickEvent event) {
		return getAtividade(event, tabelaMinicursos);
	}

	@Override
	public int[] getPalestra(ClickEvent event) {
		return getAtividade(event, tabelaPalestras);
	}

	@Override
	public HasClickHandlers getListaOficina() {
		// TODO Auto-generated method stub
		return tabelaOficinas;
	}

	@Override
	public HasClickHandlers getListaMinicurso() {
		// TODO Auto-generated method stub
		return tabelaMinicursos;
	}

	@Override
	public HasClickHandlers getListaPalestra() {
		// TODO Auto-generated method stub
		return tabelaPalestras;
	}

	@Override
	public HasClickHandlers getListaUsuario() {
		// TODO Auto-generated method stub
		return tabelaUsuario;
	}
	
	private int[] getAtividade(ClickEvent event, FlexTable ftb) {
		int linha = -1;
		int col = -1;
		int idAtividade = -1;
		HTMLTable.Cell cel = ftb.getCellForEvent(event);

		if (cel != null) {
			linha = cel.getRowIndex();
			col = cel.getCellIndex();
		}

		idAtividade = Integer.parseInt(ftb.getRowFormatter().getElement(linha)
				.getAttribute("id"));

		int e[] = { idAtividade, col };
		return e;
	}

	private void preencheTabela(FlexTable ftb, List<Atividade> lista) {
		ftb.removeAllRows();
		for (int i = 0; i < lista.size(); ++i) {
			Atividade a = lista.get(i);
			ftb.setText(i, 0, a.getNomeAtiv());
			ftb.setWidget(i, 1, new Button("Mais Informações"));
			ftb.setWidget(i, 2, new Button("Inscrever"));
			ftb.getRowFormatter().getElement(i)
					.setAttribute("id", String.valueOf(a.getIdAtiv()));
		}
	}
	
	private void preencheTabelaUsuario(FlexTable ftb, List<Atividade> lista) {
		ftb.removeAllRows();
		for (int i = 0; i < lista.size(); ++i) {
			Atividade a = lista.get(i);
			ftb.setText(i, 0, a.getNomeAtiv());
			ftb.setWidget(i, 1, new Button("Mais Informações"));
			ftb.setWidget(i, 2, new Button("Cancelar"));
			ftb.getRowFormatter().getElement(i)
					.setAttribute("id", String.valueOf(a.getIdAtiv()));
		}
	}

	private HorizontalPanel titulo(String s) {
		HorizontalPanel titulo = new HorizontalPanel();
		titulo.setVerticalAlignment(VerticalPanel.ALIGN_MIDDLE);
		titulo.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		titulo.setWidth("100%");
		titulo.setHeight("auto");
		titulo.add(new HTML(s));

		return titulo;
	}

	private void vpAtividade(VerticalPanel vp) {
		vp.setBorderWidth(1);
		vp.setVerticalAlignment(VerticalPanel.ALIGN_MIDDLE);
		vp.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		vp.setWidth("100%");
		vp.setHeight("auto");
	}

	private VerticalPanel vpConteudo() {
		VerticalPanel vpConteudo = new VerticalPanel();
		vpConteudo.setVerticalAlignment(VerticalPanel.ALIGN_MIDDLE);
		vpConteudo.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		vpConteudo.setWidth("100%");
		vpConteudo.setHeight("auto");

		return vpConteudo;
	}

	private FlexTable tabela(FlexTable tb) {
		tb.setCellSpacing(0);
		tb.setCellPadding(0);
		tb.setWidth("100%");
		tb.getColumnFormatter().setWidth(0, "60%");
		tb.getColumnFormatter().setWidth(1, "20%");
		tb.getColumnFormatter().setWidth(2, "20%");
		tb.setBorderWidth(1);

		return tb;
	}

	private void montarPainel(VerticalPanel vp, VerticalPanel conteudo,
			String s, FlexTable tb) {
		vpAtividade(vp);
		vp.add(titulo(s));
		conteudo = vpConteudo();
		conteudo.add(tabela(tb));
		vp.add(conteudo);
	}
}