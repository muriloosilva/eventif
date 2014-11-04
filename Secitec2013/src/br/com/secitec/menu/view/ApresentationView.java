package br.com.secitec.menu.view;

import java.util.List;

import br.com.secitec.content.PageContent;
import br.com.secitec.menu.presenter.ApresentationPresenter;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class ApresentationView extends Composite implements
		ApresentationPresenter.Display {

	private PageContent apstViewPage;

	public ApresentationView() {
		apstViewPage = new PageContent("Apresentação");
		apstViewPage.setContent(new HTML("<p align='justify'>A SECITEC, Semana de  Educação, Ciência e Tecnologia do IFG, " +
				"que já está na sua quinta edição, é um  evento que visa promover uma aproximação do Instituto " +
				"Federal com a comunidade.  No evento são oferecidos minicursos, oficinas, colóquios e palestras e uma exposição " +
				"de trabalhos  de alunos e professores.<br><br>" +
				"<p>Sobre as incrições:<br>" +
				"Todos devem se cadastrar no evento, mesmo se não participará de minicursos, oficinas ou colóquios <br>"+
				"Cadastros realizados no ano passado não estão válidos, deverá ser realizado um novo cadastro.<br>"+
				"Cada aluno pode se inscrever em no máximo seis atividades, desde que não exista conflito de horários, sendo:<br>"+
				"	- no máximo, dois minicursos;<br>"+
				"	- e o restante entre colóquios e oficinas<br>"+
				"Não é necessário se inscrever nas palestras.<br>" +
				"Dúvidas com o site, utilize o Fale Conosco no menu acima.<br>--<br>" +
				"Para uma melhor experiência nesse site, recomendamos os navegadores Google Chrome (versão 30 ou superior), Firefox (versão 24 ou superior) e Internet Explorer (versões 9 ou superior)</p>"));
		initWidget(apstViewPage);
	}

	public void setData(List<String> data) {

	}

	public Widget asWidget() {
		return this;
	}
}
