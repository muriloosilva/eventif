package br.com.secitec.server;




import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.secitec.server.dao.FrequenciaDAO;
import br.com.secitec.server.dao.ParticipanteDAO;
import br.com.secitec.server.util.HashUtil;
import br.com.secitec.shared.model.Frequencia;
import br.com.secitec.shared.model.User;

/**
 * Servlet implementation class Horas
 */
public class ServletPresenca extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cpf = request.getParameter("cpf");
		int atividade = Integer.parseInt(request.getParameter("atividade"));
		int status = Integer.parseInt(request.getParameter("status"));
		
		System.out.println("cpf:"+cpf);
		System.out.println("atividade:"+atividade);
		System.out.println("status:"+status);

		Frequencia f = new Frequencia();
		f.setCPF(cpf);
		f.setAtividade(atividade);
		f.setStatus(status);
		
		boolean b = FrequenciaDAO.registraFrequenciaAtividade(f);
		
		PrintWriter pw = response.getWriter();
		if(b)
			pw.write("ok");
		else
			pw.write("erro");
		
		response.flushBuffer();

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
