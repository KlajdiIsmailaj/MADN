package servlets;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Spieler3")
public class Spieler3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String brett = "/Spielbrett.jsp";
	private static final String Spieler4 = "/Spieler4.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Spieler3() {
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
		RequestDispatcher rd = sc.getRequestDispatcher(brett);
		RequestDispatcher rd2 = sc.getRequestDispatcher(Spieler4);
		
		int anzahl = (Integer)request.getSession(false).getAttribute("anz");
		
		if(anzahl==3){
			rd.forward(request, response);
		}else{
			rd2.forward(request, response);
		}
		
	}
	
}
