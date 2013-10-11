package br.com.secitec.menu.view;

import java.util.List;

import br.com.secitec.menu.presenter.FaleConoscoPresenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class FaleConoscoView extends Composite implements
FaleConoscoPresenter.Display{

	private PopupPanel tela;
	private VerticalPanel vp;
	private FlexTable tabela;
	private Button enviar;
	
	public FaleConoscoView() {
		tela = new PopupPanel(false);
		tela.setStyleName("demo-popup");

		vp = new VerticalPanel();
		vp.setBorderWidth(0);
		vp.setSpacing(0);
		vp.setVerticalAlignment(VerticalPanel.ALIGN_MIDDLE);
		vp.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		vp.setWidth("450px");
		
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
		
		HTML titulo = new HTML("Fale Conosco");
		titulo.addStyleName("titulo");
		hpTitulo.add(titulo);
		
		HorizontalPanel hpFechar = new HorizontalPanel();
		hpFechar.setSpacing(0);
		hpFechar.setWidth("20px");
		hpFechar.addStyleName("botaoFecharLogin");
		hpFechar.setHorizontalAlignment(HorizontalPanel.ALIGN_RIGHT);
		
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
		
		enviar = new Button();
		enviar.setText("Enviar");
		enviar.setHeight("40px");
		
		hpFaleConosco.add(enviar);
		
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

		HTML hEmail = new HTML("E-mail: ");
		hEmail.addStyleName("alignDir");
		tb.setWidget(1, 0, hEmail);
		TextBox email = new TextBox();
		email.setWidth("336px");
		tb.setWidget(1, 1, email);
		
		HTML hAssunto = new HTML("Assunto: ");
		hAssunto.addStyleName("alignDir");
		tb.setWidget(2, 0, hAssunto);
		TextBox assunto = new TextBox();
		assunto.setWidth("336px");
		tb.setWidget(2, 1, assunto);
		
		HTML hMensagem = new HTML("Mensagem: ");
		hMensagem.addStyleName("alignDir");
		tb.setWidget(3, 0, hMensagem);
		TextArea mensagem = new TextArea();
		mensagem.getElement().getStyle().setProperty("maxWidth","340px");
		mensagem.getElement().getStyle().setProperty("maxHeight","300px");
		mensagem.getElement().getStyle().setProperty("minWidth","340px");
		mensagem.getElement().getStyle().setProperty("minHeight","300px");
		mensagem.setWidth("340px");
		mensagem.setHeight("300px");
		tb.setWidget(3, 1, mensagem);
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

//	public void addEventoLogin(Button bt) {
//		bt.addClickHandler(new ClickHandler() {
//
//			@Override
//			public void onClick(ClickEvent event) {
//				// TODO Auto-generated method stub
//				user = new User();
//
//				TextBox login = (TextBox) tabela.getWidget(0, 1);
//				PasswordTextBox senha = (PasswordTextBox) tabela
//						.getWidget(1, 1);
//
//				user.setLogin_partic(login.getText());
//				user.setSenha_partic(senha.getText());
//
////				rpcService.login(user.getLogin_partic(),
////						user.getSenha_partic(), new AsyncCallback<Boolean>() {
////
////							@Override
////							public void onFailure(Throwable caught) {
////								// TODO Auto-generated method stub
////
////							}
////
////							@Override
////							public void onSuccess(Boolean b) {
////								// TODO Auto-generated method stub
////							}
////						});
//
//			}
//		});
//	}

//	public void addEventoCadastro(Button bt) {
//		bt.addClickHandler(new ClickHandler() {
//
//			@Override
//			public void onClick(ClickEvent event) {
//				// TODO Auto-generated method stub
//				tela.hide();
//				//CadastroPopup popup = new CadastroPopup();
//			}
//		});
//	}
	

	public Button getEnviar(){
		return this.enviar;
	}
	
	@Override
	public void setData(List<String> data) {
		// TODO Auto-generated method stub
		
	}
	
	public TextBox getTbNome(){
		return (TextBox) tabela.getWidget(0, 1);
	}
	
	public TextBox getTbEmail(){
		return (TextBox) tabela.getWidget(1, 1);
	}
	
	public TextBox getTbAssunto(){
		return (TextBox) tabela.getWidget(2, 1);
	}
	
	public TextArea getTbMensagem(){
		return (TextArea) tabela.getWidget(3, 1);
	}
	
	public PopupPanel getPopup(){
		return this.tela;
	}
}