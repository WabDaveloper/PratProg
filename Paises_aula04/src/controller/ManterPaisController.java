package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modelo.Pais;
import Service.PaisService;

/**
 * Servlet implementation class ManterPaisController
 */
@WebServlet("/ManterPais.do")
public class ManterPaisController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
	}
	
	
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// PEGAR TUDO QUE ESTA NO FORMULARIO E GUARDAR DENTRO DE UMA STRING
		String pNome = request.getParameter("nome");
		String pPopulacao = request.getParameter("populacao");
		String pArea = request.getParameter("area");
		String acao = request.getParameter("acao");
		
		//CRIAR O OBJETO DENTRO DA CLASSE PAIS
		Pais paises = new Pais();
		paises.setNome(pNome);
		paises.setPopulacao(Long.parseLong(pPopulacao));
		paises.setArea(Double.parseDouble(pArea));
		
		PaisService tt = new PaisService();
		RequestDispatcher dispatcher = null;


		switch(acao){
		case "Incluir":
			tt.criar(paises);
			paises = tt.carregar(paises.getId());
			request.setAttribute("paises", paises);  //Solicita que o servidor crie um novo atributo ou modifica um existente
			dispatcher = request.getRequestDispatcher("Pais.jsp");
			break;
			
		case "Listar":
			pPopulacao = "0";
			pArea = "0";
			
			ArrayList<Pais> pais = tt.listarTodos();
			request.setAttribute("paises", pais);
			dispatcher = request.getRequestDispatcher("ListaPais.jsp");
			
			
		}
		
		dispatcher.forward(request, response);

}}
