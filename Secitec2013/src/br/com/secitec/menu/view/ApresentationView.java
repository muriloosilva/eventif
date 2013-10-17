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
				"que já está na sua quarta edição, é um  evento que visa promover uma aproximação do Instituto " +
				"Federal com a comunidade.  No evento são oferecidos minicursos, oficinas, palestras e uma exposição " +
				"de trabalhos  de alunos e professores. Junto à programação da SECITEC teremos os seguintes eventos paralelos: " +
				"<ul><li><p align='justify'>Conhecendo o IFG: O projeto tem como principal finalidade a divulgação do Instituto Federal de Goiás nas " +
				"escolas públicas municipais e estaduais das localidades em que os câmpus estão inseridos. O projeto acontece de duas " +
				"formas: os servidores do câmpus visitam as escolas, levando informações, e as escolas também visitam o câmpus, para os alunos " +
				"conhecerem mais de perto como é a unidade. O evento está em sua terceira edição;</p></li>" +
				"<li><p align='justify'>InstallFest: é um evento em que pessoas se reúnem para realizar instalações de vários sistemas operacionais em sua maioria " +
				"livres. Os alunos do 7º período do curso de Manutenção e Suporte em Informática auxiliaram os participantes a realizarem suas " +
				"instalações. Traga seu notebook ou o gabinete da sua máquina para instalar um novos sistema livre " +
				"(caso tenha um sistema operacional pago, daremos suporte na instalação).</p></li>" +
				"<li><p align='justify'>Manutenção e Suporte em Informática: é um evento em que pessoas se reúnem para trocarem experiências de manutenção física " +
				"(hardware) e lógica (programas) " +
				"de computadores. Os alunos do 6º período do curso de Manutenção e Suporte em Informática auxiliaram os participantes na resolução de " +
				"problemas de seus computadores.</p> </li></ul></p><p><br/>A abertura ocorrerá no  dia 21 de outubro às " +
				"19 horas com a palestra &quot;Interdisciplinaridade nos  estudos sobre Recursos Hídricos&quot; ministrada pelo Prof. " +
				"Dr.&nbsp;Ludgero  Cardoso Galli Vieira, e seu aluno de mestrado do PPG Mader (Pós Graduação em  Meio Ambiente e Desenvolvimento " +
				"Rural), Leonardo Fernandes Gomes no Teatro do  Campus. A programação completa você pode encontrar no menu  acima.</p>--" +
				"<p>Sobre as incrições:<br>" +
				"Não é necessário se inscrever nas palestras.<br>" +
				"Você pode se inscrever em até 4 oficinas, desde que não haja choque de horário no mesmo dia<br>" +
				"Você pode ser inscrever em apenas 1 minicurso.<br>" +
				"Dúvidas com o site, utilize o Fale Conosco no menu acima.</p>"));
		initWidget(apstViewPage);
	}

	public void setData(List<String> data) {

	}

	public Widget asWidget() {
		return this;
	}
}
