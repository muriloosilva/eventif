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
		apstViewPage = new PageContent("ApresentaÃ§Ã£o");
		apstViewPage.setContent(new HTML("<p>O interesse pelo texto como objeto de estudo gerou vários "
				+ "trabalhos importantes de teóricos da Lingüística Textual, que percorreram fases "
				+ "diversas cujas características principais eram transpor os limites da frase "
				+ "descontextualizada da gramática tradicional e ainda incluir os relevantes papéis "
				+ "do autor e do leitor na construção de textos. Um texto pode ser escrito ou oral e, "
				+ "em sentido lato, pode ser também não verbal. Texto crítico é uma produção textual "
				+ "que parte de um processo reflexivo e analítico gerando um conteúdo com crítica "
				+ "construtiva e bem fundamentada. </p><p>Em artes gráficas, o texto é a parte verbal, lingüística, "
				+ "por oposição às ilustrações. Todo texto tem que ter alguns aspectos formais, ou seja, "
				+ "tem que ter estrutura, elementos que estabelecem relaçao entre si. Dentro dos "
				+ "aspectos formais temos a coesão e a coerência, que dão sentido e forma ao texto. "
				+ "A coesão textual é a relação, a ligação, a conexão entre as palavras, expressões "
				+ "ou frases do texto” . A coerência está relacionada com a compreensão, a interpretação "
				+ "do que se diz ou escreve. </p><p>Um texto precisa ter sentido, isto é, precisa ter coerência. "
				+ "Embora a coesão não seja condição suficiente para que enunciados se constituam em textos, "
				+ "são os elementos coesivos que lhes dão maior legibilidade e evidenciam as relações "
				+ "entre seus diversos componentes, a coerência depende da coesão.</p>"));
		initWidget(apstViewPage);
	}

	public void setData(List<String> data) {

	}

	public Widget asWidget() {
		return this;
	}
}
