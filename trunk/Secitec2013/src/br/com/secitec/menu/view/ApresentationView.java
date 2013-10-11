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
		apstViewPage.setContent(new HTML("<p>O interesse pelo texto como objeto de estudo gerou vários "
				+ "trabalhos importantes de te�ricos da Ling��stica Textual, que percorreram fases "
				+ "diversas cujas caracter�sticas principais eram transpor os limites da frase "
				+ "descontextualizada da gram�tica tradicional e ainda incluir os relevantes pap�is "
				+ "do autor e do leitor na constru��o de textos. Um texto pode ser escrito ou oral e, "
				+ "em sentido lato, pode ser tamb�m n�o verbal. Texto cr�tico � uma produ��o textual "
				+ "que parte de um processo reflexivo e anal�tico gerando um conte�do com cr�tica "
				+ "construtiva e bem fundamentada. </p><p>Em artes gr�ficas, o texto � a parte verbal, ling��stica, "
				+ "por oposi��o �s ilustra��es. Todo texto tem que ter alguns aspectos formais, ou seja, "
				+ "tem que ter estrutura, elementos que estabelecem rela�ao entre si. Dentro dos "
				+ "aspectos formais temos a coes�o e a coer�ncia, que d�o sentido e forma ao texto. "
				+ "A coes�o textual � a rela��o, a liga��o, a conex�o entre as palavras, express�es "
				+ "ou frases do texto� . A coer�ncia est� relacionada com a compreens�o, a interpreta��o "
				+ "do que se diz ou escreve. </p><p>Um texto precisa ter sentido, isto �, precisa ter coer�ncia. "
				+ "Embora a coes�o n�o seja condi��o suficiente para que enunciados se constituam em textos, "
				+ "s�o os elementos coesivos que lhes d�o maior legibilidade e evidenciam as rela��es "
				+ "entre seus diversos componentes, a coer�ncia depende da coes�o.</p>"));
		initWidget(apstViewPage);
	}

	public void setData(List<String> data) {

	}

	public Widget asWidget() {
		return this;
	}
}
