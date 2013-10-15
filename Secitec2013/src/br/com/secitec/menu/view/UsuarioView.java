package br.com.secitec.menu.view;

import java.util.List;

import br.com.secitec.menu.presenter.UsuarioPresenter;
import br.com.secitec.shared.model.Atividade;
import br.com.secitec.shared.model.Data;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class UsuarioView extends Composite implements UsuarioPresenter.Display {

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
	private Button btInsc, btM;
	private HTML msg;

	public UsuarioView() {
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
		hpTop.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		hpTop.setWidth("100%");
		hpTop.setHeight("24px");

		HTML titlePage = new HTML("Minhas atividades");
		titlePage.setStyleName("lbTitle");
		hpTop.add(titlePage);

		usuario = new VerticalPanel();
		tabelaUsuario = new FlexTable();
		montarPainel(usuario, conteudoUsuario, "Atividades Inscritas",
				tabelaUsuario);

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

		msg = new HTML();
//		titlePage.setStyleName("lbTitle");
		msg.removeStyleName("gwt-HTML");
		msg.setVisible(false);
		hpUsuario.add(msg);

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
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void setData(List<Atividade> oficinas, List<Atividade> minicursos,
			List<Atividade> palestras, List<Atividade> atividadesUsuario) {
		System.out.println("Preenchenco oficinas...");
		preencheTabela(tabelaOficinas, oficinas, atividadesUsuario);
		System.out.println("Preenchenco minicursos...");
		preencheTabela(tabelaMinicursos, minicursos, atividadesUsuario);
		preencheTabela(tabelaPalestras, palestras, atividadesUsuario);
	}

	@Override
	public void setTabelaUsuario(List<Atividade> atividades) {
		if(atividades != null)
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
	public FlexTable getListaOficina() {
		return tabelaOficinas;
	}

	@Override
	public FlexTable getListaMinicurso() {
		return tabelaMinicursos;
	}

	@Override
	public FlexTable getListaPalestra() {
		return tabelaPalestras;
	}

	@Override
	public FlexTable getListaUsuario() {
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

	private void preencheTabela(FlexTable ftb, List<Atividade> lista, List<Atividade> atividadesUsuario) {
		ftb.removeAllRows();
		for (int i = 0; i < lista.size(); ++i) {
			Atividade a = lista.get(i);

			HTML nome = new HTML();
			nome.setText(a.getNomeAtiv());
			nome.removeStyleName("gwt-HTML");

			VerticalPanel vpDatas = new VerticalPanel();
			vpDatas.addStyleName("vpDatas");
			for (int j = 0; j < a.getDatas().size(); j++) {
				Data d = a.getDatas().get(j);
				HTML htData = new HTML();
				htData.setText(formataData(String.valueOf(d.getData())));
				htData.removeStyleName("gwt-HTML");
				htData.addStyleName("tbAtividadesCol2");
				vpDatas.add(htData);
			}

			VerticalPanel vpHorario = new VerticalPanel();
			vpHorario.addStyleName("vpHorario");
			for (int j = 0; j < a.getDatas().size(); j++) {
				Data d = a.getDatas().get(j);
				HTML horario = new HTML();
				horario.setText(""+String.valueOf(d.getHrInicio()).substring(0, 5)+"h - "+String.valueOf(d.getHrFim()).substring(0, 5)+"h");
				horario.removeStyleName("gwt-HTML");
				horario.addStyleName("tbAtividadesCol3");
				vpHorario.add(horario);
			}

			HTML vagas = new HTML();
			vagas.setText("Vagas: "+a.getVagasDisponiveis());
			vagas.removeStyleName("gwt-HTML");
						
			ftb.setWidget(i, 0, nome);
			ftb.setWidget(i, 1, vpDatas);
			ftb.setWidget(i, 2, vpHorario);
			ftb.setWidget(i, 3, vagas);
			
			btM = new Button("Mais Informações");
			btM.removeStyleName("gwt-Button");
			btM.addStyleName("btn-info");
			ftb.setWidget(i, 4, btM);
			
			if(a.getTipoAtiv().equals("Oficina") || a.getTipoAtiv().equals("Minicurso")){
				if(!atividadesUsuario.isEmpty()){
					System.out.println("Lista de atividades do usuario vazia? "+atividadesUsuario.isEmpty());
					for (int j = 0; j < atividadesUsuario.size(); j++) {
						Atividade aUsuario = atividadesUsuario.get(j);
						btInsc = new Button("Inscrever");
						btInsc.removeStyleName("gwt-Button");
						btInsc.addStyleName("btn-info");
//						ftb.setWidget(i, 5, btInsc);
						if(a.getIdAtiv() == aUsuario.getIdAtiv()){
							System.out.println("Atividade inscrito");
							btInsc = new Button("Inscrito");
							btInsc.removeStyleName("gwt-Button");
							btInsc.addStyleName("btn-info");
							btInsc.setEnabled(false);
//							ftb.setWidget(i, 5, btInsc);
						} //else if(a.getIdAtiv() != aUsuario.getIdAtiv()){
//							System.out.println("Atividade nao inscrito");
//							btInsc = new Button("Inscrever");
//							btInsc.removeStyleName("gwt-Button");
//							btInsc.addStyleName("btn-info");
//							ftb.setWidget(i, 5, btInsc);
//						}
						ftb.setWidget(i, 5, btInsc);
					}
				} else{
					System.out.println("Lista de atividade do usuario está VAZIA");
					btInsc = new Button("Inscrever");
					btInsc.removeStyleName("gwt-Button");
					btInsc.addStyleName("btn-info");
					ftb.setWidget(i, 5, btInsc);
				}
				ftb.getCellFormatter().addStyleName(i, 4, "col5");
				ftb.getCellFormatter().addStyleName(i, 5, "col6");
				ftb.getRowFormatter().getElement(i).setAttribute("id", String.valueOf(a.getIdAtiv()));
			}
			else {				
				ftb.getCellFormatter().addStyleName(i, 4, "col4Palestra");
				ftb.getRowFormatter().getElement(i).setAttribute("id", String.valueOf(a.getIdAtiv()));
			}
			ftb.getCellFormatter().addStyleName(i, 0, "col1");
			ftb.getCellFormatter().addStyleName(i, 1, "col2");
			ftb.getCellFormatter().addStyleName(i, 2, "col3");
			ftb.getCellFormatter().addStyleName(i, 3, "col4");
		}
	}

	private String formataData(String data) {
		String dataFormatada;
		dataFormatada = data.substring(8, 10) + "/" + data.substring(5, 7)
				+ "/" + data.substring(2, 4);
//		System.out.println("DATA: " + dataFormatada);
		return dataFormatada;
	}

	private void preencheTabelaUsuario(FlexTable ftb, List<Atividade> lista) {
		ftb.removeAllRows();
		for (int i = 0; i < lista.size(); ++i) {
			Atividade a = lista.get(i);
			
			HTML nome = new HTML();
			nome.setText(a.getNomeAtiv());
			nome.removeStyleName("gwt-HTML");

			VerticalPanel vpDatas = new VerticalPanel();
			vpDatas.addStyleName("vpDatas");
			for (int j = 0; j < a.getDatas().size(); j++) {
				Data d = a.getDatas().get(j);
				HTML htData = new HTML();
				htData.setText(formataData(String.valueOf(d.getData())));
				htData.removeStyleName("gwt-HTML");
				htData.addStyleName("tbAtividadesCol2");
				vpDatas.add(htData);
			}

			VerticalPanel vpHorario = new VerticalPanel();
			vpHorario.addStyleName("vpHorario");
			for (int j = 0; j < a.getDatas().size(); j++) {
				Data d = a.getDatas().get(j);
				HTML horario = new HTML();
				horario.setText(""+String.valueOf(d.getHrInicio()).substring(0, 5)+"h - "+String.valueOf(d.getHrFim()).substring(0, 5)+"h");
				horario.removeStyleName("gwt-HTML");
				horario.addStyleName("tbAtividadesCol3");
				vpHorario.add(horario);
			}

			HTML vagas = new HTML();
//			vagas.setText("Vagas: " + a.getVagasDisponiveis());
			vagas.removeStyleName("gwt-HTML");

			ftb.setWidget(i, 0, nome);
			ftb.setWidget(i, 1, vpDatas);
			ftb.setWidget(i, 2, vpHorario);
			ftb.setWidget(i, 3, vagas);

			Button btM = new Button("Mais Informações");
			btM.removeStyleName("gwt-Button");
			btM.addStyleName("btn-info");
			ftb.setWidget(i, 4, btM);
			
			Button btInsc = new Button("Cancelar");
			btInsc.addStyleName("btn-info");
			btInsc.removeStyleName("gwt-Button");
			ftb.setWidget(i, 5, btInsc);
			
			ftb.getCellFormatter().addStyleName(i, 0, "col1");
			ftb.getCellFormatter().addStyleName(i, 1, "col2");
			ftb.getCellFormatter().addStyleName(i, 2, "col3");
			ftb.getCellFormatter().addStyleName(i, 3, "col4");
			ftb.getCellFormatter().addStyleName(i, 4, "col5");
			ftb.getCellFormatter().addStyleName(i, 5, "col6");
			
			ftb.getRowFormatter().getElement(i)
					.setAttribute("id", String.valueOf(a.getIdAtiv()));
		}
	}

	private HorizontalPanel titulo(String s) {
		HorizontalPanel titulo = new HorizontalPanel();
		titulo.setVerticalAlignment(VerticalPanel.ALIGN_MIDDLE);
		titulo.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		titulo.setWidth("100%");
		HTML lbTitulo = new HTML(s);
		lbTitulo.addStyleName("lbTituloAtividade");
		titulo.addStyleName("backLabelAtividades");
		titulo.add(lbTitulo);

		return titulo;
	}

	private void vpAtividade(VerticalPanel vp) {
		vp.setBorderWidth(0);
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
		tb.addStyleName("tbAtividades");
		tb.setBorderWidth(0);

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

	@Override
	public void setDataAtividades(List<Atividade> atividades,
			List<Atividade> atividadesUsuario) {
		
		if(atividades.get(0).getTipoAtiv().equals("Oficina"))
			setTabelaOficina(atividades, atividadesUsuario);
		else
			setTabelaMinicurso(atividades, atividadesUsuario);
	}

	@Override
	public void setTabelaOficina(List<Atividade> oficinas,
			List<Atividade> atividadesUsuario) {
		for (int i = 0; i < oficinas.size(); ++i) {
			Atividade oficina = oficinas.get(i);

			HTML nome = new HTML();
			nome.setText(oficina.getNomeAtiv());
			nome.removeStyleName("gwt-HTML");

			VerticalPanel vpDatas = new VerticalPanel();
			vpDatas.addStyleName("vpDatas");
			for (int j = 0; j < oficina.getDatas().size(); j++) {
				Data d = oficina.getDatas().get(j);
				HTML htData = new HTML();
				htData.setText(formataData(String.valueOf(d.getData())));
				htData.removeStyleName("gwt-HTML");
				htData.addStyleName("tbAtividadesCol2");
				vpDatas.add(htData);
			}

			VerticalPanel vpHorario = new VerticalPanel();
			vpHorario.addStyleName("vpHorario");
			for (int j = 0; j < oficina.getDatas().size(); j++) {
				Data d = oficina.getDatas().get(j);
				HTML horario = new HTML();
				horario.setText(""+String.valueOf(d.getHrInicio()).substring(0, 5)+"h - "+String.valueOf(d.getHrFim()).substring(0, 5)+"h");
				horario.removeStyleName("gwt-HTML");
				horario.addStyleName("tbAtividadesCol3");
				vpHorario.add(horario);
			}

			HTML vagas = new HTML();
			vagas.setText("Vagas: "+oficina.getVagasDisponiveis());
			vagas.removeStyleName("gwt-HTML");
						
			tabelaOficinas.setWidget(i, 0, nome);
			tabelaOficinas.setWidget(i, 1, vpDatas);
			tabelaOficinas.setWidget(i, 2, vpHorario);
			tabelaOficinas.setWidget(i, 3, vagas);
			
			btM = new Button("Mais Informações");
			btM.removeStyleName("gwt-Button");
			btM.addStyleName("btn-info");
			tabelaOficinas.setWidget(i, 4, btM);
			
			if(atividadesUsuario != null){
				System.out.println("\nOFICINAS");
				System.out.println("Lista de atividades do usuario cheia");				
				for (int j = 0; j < atividadesUsuario.size(); j++) {
					Atividade aUsuario = atividadesUsuario.get(j);
//					btInsc = new Button("Inscrever");
//					btInsc.removeStyleName("gwt-Button");
//					btInsc.addStyleName("btn-info");
					if(oficina.getIdAtiv() == aUsuario.getIdAtiv()){
						System.out.println("Oficina inscrita");
						btInsc = new Button("Inscrito");
						btInsc.removeStyleName("gwt-Button");
						btInsc.addStyleName("btn-info");
						btInsc.setWidth("69px");
						btInsc.setEnabled(false);
						tabelaOficinas.setWidget(i, 5, btInsc);
						break;
					}
					else{
						System.out.println("Oficina não inscrita");
						btInsc = new Button("Inscrever");
						btInsc.removeStyleName("gwt-Button");
						btInsc.addStyleName("btn-info");
						tabelaOficinas.setWidget(i, 5, btInsc);
					}
				}
			} else{
				btInsc = new Button("Inscrever");
				btInsc.removeStyleName("gwt-Button");
				btInsc.addStyleName("btn-info");
				tabelaOficinas.setWidget(i, 5, btInsc);
			}
			tabelaOficinas.getCellFormatter().addStyleName(i, 0, "col1");
			tabelaOficinas.getCellFormatter().addStyleName(i, 1, "col2");
			tabelaOficinas.getCellFormatter().addStyleName(i, 2, "col3");
			tabelaOficinas.getCellFormatter().addStyleName(i, 3, "col4");
			tabelaOficinas.getCellFormatter().addStyleName(i, 4, "col5");
			tabelaOficinas.getCellFormatter().addStyleName(i, 5, "col6");
			tabelaOficinas.getRowFormatter().getElement(i).setAttribute("id", String.valueOf(oficina.getIdAtiv()));
		}
	}

	@Override
	public void setTabelaMinicurso(List<Atividade> minicursos,
			List<Atividade> atividadesUsuario) {
		for (int i = 0; i < minicursos.size(); ++i) {
			Atividade minicurso = minicursos.get(i);

			HTML nome = new HTML();
			nome.setText(minicurso.getNomeAtiv());
			nome.removeStyleName("gwt-HTML");

			VerticalPanel vpDatas = new VerticalPanel();
			vpDatas.addStyleName("vpDatas");
			for (int j = 0; j < minicurso.getDatas().size(); j++) {
				Data d = minicurso.getDatas().get(j);
				HTML htData = new HTML();
				htData.setText(formataData(String.valueOf(d.getData())));
				htData.removeStyleName("gwt-HTML");
				htData.addStyleName("tbAtividadesCol2");
				vpDatas.add(htData);
			}

			VerticalPanel vpHorario = new VerticalPanel();
			vpHorario.addStyleName("vpHorario");
			for (int j = 0; j < minicurso.getDatas().size(); j++) {
				Data d = minicurso.getDatas().get(j);
				HTML horario = new HTML();
				horario.setText(""+String.valueOf(d.getHrInicio()).substring(0, 5)+"h - "+String.valueOf(d.getHrFim()).substring(0, 5)+"h");
				horario.removeStyleName("gwt-HTML");
				horario.addStyleName("tbAtividadesCol3");
				vpHorario.add(horario);
			}

			HTML vagas = new HTML();
			vagas.setText("Vagas: "+minicurso.getVagasDisponiveis());
			vagas.removeStyleName("gwt-HTML");
						
			tabelaMinicursos.setWidget(i, 0, nome);
			tabelaMinicursos.setWidget(i, 1, vpDatas);
			tabelaMinicursos.setWidget(i, 2, vpHorario);
			tabelaMinicursos.setWidget(i, 3, vagas);
			
			btM = new Button("Mais Informações");
			btM.removeStyleName("gwt-Button");
			btM.addStyleName("btn-info");
			tabelaMinicursos.setWidget(i, 4, btM);
			
			if(atividadesUsuario != null){
				System.out.println("\nMINICURSOS");
				System.out.println("Lista de atividades do usuario cheia");
				for (int j = 0; j < atividadesUsuario.size(); j++) {
					Atividade aUsuario = atividadesUsuario.get(j);
//					btInsc = new Button();
					if(minicurso.getIdAtiv() == aUsuario.getIdAtiv()){
						System.out.println("Inscrito no Minicurso");
						btInsc = new Button("Inscrito");
						btInsc.removeStyleName("gwt-Button");
						btInsc.addStyleName("btn-info");
						btInsc.setWidth("69px");
						btInsc.setEnabled(false);
						tabelaMinicursos.setWidget(i, 5, btInsc);
						break;
					}
					else{
						System.out.println("Não inscrito no Minicurso");
						btInsc = new Button("Inscrever");
						btInsc.setText("Inscrever");
						btInsc.removeStyleName("gwt-Button");
						btInsc.addStyleName("btn-info");
						tabelaMinicursos.setWidget(i, 5, btInsc);
					}
				}
			} else{
				btInsc = new Button("Inscrever");
				btInsc.removeStyleName("gwt-Button");
				btInsc.addStyleName("btn-info");
				tabelaMinicursos.setWidget(i, 5, btInsc);
			}
			tabelaMinicursos.getCellFormatter().addStyleName(i, 0, "col1");
			tabelaMinicursos.getCellFormatter().addStyleName(i, 1, "col2");
			tabelaMinicursos.getCellFormatter().addStyleName(i, 2, "col3");
			tabelaMinicursos.getCellFormatter().addStyleName(i, 3, "col4");
			tabelaMinicursos.getCellFormatter().addStyleName(i, 4, "col5");
			tabelaMinicursos.getCellFormatter().addStyleName(i, 5, "col6");
			tabelaMinicursos.getRowFormatter().getElement(i).setAttribute("id", String.valueOf(minicurso.getIdAtiv()));
		}
	}

	@Override
	public void setTabelaPalestras(List<Atividade> palestras,
			List<Atividade> atividadesUsuario) {
		for (int i = 0; i < palestras.size(); ++i) {
			Atividade a = palestras.get(i);

			HTML nome = new HTML();
			nome.setText(a.getNomeAtiv());
			nome.removeStyleName("gwt-HTML");

			VerticalPanel vpDatas = new VerticalPanel();
			vpDatas.addStyleName("vpDatas");
			for (int j = 0; j < a.getDatas().size(); j++) {
				Data d = a.getDatas().get(j);
				HTML htData = new HTML();
				htData.setText(formataData(String.valueOf(d.getData())));
				htData.removeStyleName("gwt-HTML");
				htData.addStyleName("tbAtividadesCol2");
				vpDatas.add(htData);
			}

			VerticalPanel vpHorario = new VerticalPanel();
			vpHorario.addStyleName("vpHorario");
			for (int j = 0; j < a.getDatas().size(); j++) {
				Data d = a.getDatas().get(j);
				HTML horario = new HTML();
				horario.setText(""+String.valueOf(d.getHrInicio()).substring(0, 5)+"h - "+String.valueOf(d.getHrFim()).substring(0, 5)+"h");
				horario.removeStyleName("gwt-HTML");
				horario.addStyleName("tbAtividadesCol3");
				vpHorario.add(horario);
			}

			HTML vagas = new HTML();
//			vagas.setText("Vagas: "+a.getVagasDisponiveis());
			vagas.removeStyleName("gwt-HTML");
						
			tabelaPalestras.setWidget(i, 0, nome);
			tabelaPalestras.setWidget(i, 1, vpDatas);
			tabelaPalestras.setWidget(i, 2, vpHorario);
			tabelaPalestras.setWidget(i, 3, vagas);
			
			btM = new Button("Mais Informações");
			btM.removeStyleName("gwt-Button");
			btM.addStyleName("btn-info");
//			btM.setWidth("199px");
			tabelaPalestras.setWidget(i, 4, btM);

			tabelaPalestras.getCellFormatter().addStyleName(i, 0, "col1");
			tabelaPalestras.getCellFormatter().addStyleName(i, 1, "col2");
			tabelaPalestras.getCellFormatter().addStyleName(i, 2, "col3");
			tabelaPalestras.getCellFormatter().addStyleName(i, 3, "col4");
			tabelaPalestras.getCellFormatter().addStyleName(i, 4, "col4Palestra");
			tabelaPalestras.getRowFormatter().getElement(i).setAttribute("id", String.valueOf(a.getIdAtiv()));
		}
	}

	@Override
	public HTML getMsg() {
		// TODO Auto-generated method stub
		return this.msg;
	}

	@Override
	public VerticalPanel getVpUsuario() {
		// TODO Auto-generated method stub
		return this.usuario;
	}
}