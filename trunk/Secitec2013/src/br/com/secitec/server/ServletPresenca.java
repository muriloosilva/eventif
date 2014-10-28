package br.com.secitec.server;




import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.secitec.server.dao.ParticipanteDAO;
import br.com.secitec.server.util.HashUtil;
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
		String atividade = request.getParameter("atividade");
		String status = request.getParameter("status");
		
		System.out.println("cpf:"+cpf);
		System.out.println("atividade:"+atividade);
		System.out.println("status:"+status);


		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
