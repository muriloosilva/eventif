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
		apstViewPage.setContent(new HTML("<p>A SECITEC, Semana de  Educação, Ciência e Tecnologia do IFG, " +
				"que já está na sua quarta edição, é um  evento que visa promover uma aproximação do Instituto " +
				"Federal com a comunidade.  No evento são oferecidos minicursos, oficinas, palestras e uma exposição " +
				"de trabalhos  de alunos e professores. Junto ao evento estará acontecendo o Café Empresarial  e o programa " +
				"Conhecendo o IFG, que também está em sua quarta edição.</p><p><br/>A abertura ocorrerá no  dia 21 de outubro às " +
				"19 horas com a palestra &quot;Interdisciplinaridade nos  estudos sobre Recursos Hídricos&quot; ministrada pelo Prof. " +
				"Dr.&nbsp;Ludgero  Cardoso Galli Vieira, e seu aluno de mestrado do PPG Mader (Pós Graduação em  Meio Ambiente e Desenvolvimento " +
				"Rural), Leonardo Fernandes Gomes no Teatro do  Campus. A programação completa você pode encontrar no menu  acima.</p>"));
		initWidget(apstViewPage);
	}

	public void setData(List<String> data) {

	}

	public Widget asWidget() {
		return this;
	}
}
