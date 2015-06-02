package servlets;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import testmadn.SpielBean;
import testmadn.iBediener;
/**
 * Servlet implementation class Login
 */
@WebServlet("/Index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String Login = "/Login.jsp";
	private iBediener backend;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Index() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=ISO-8859-1");
		ServletContext sc = this.getServletContext();
		if (sc.getAttribute("spiel") == null) {
			backend = new SpielBean();
			sc.setAttribute("spiel", backend);
		} else {
			System.out.println("es existiert bereits ein spiel");
		}
		RequestDispatcher rd = sc.getRequestDispatcher(Login);
		
		rd.forward(request, response);
		
		
	}
	
}
