package servlets;


import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.swing.ImageIcon;



/**
 * Servlet implementation class Login
 */
@WebServlet("/Spielbrett")
public class Spielbrett extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String brett = "/Spielbrett.jsp";
	ImageIcon i;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Spielbrett() {
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
		
		
		rd.forward(request, response);
		
		
		
		
	}
	
}
