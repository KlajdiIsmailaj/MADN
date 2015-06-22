package servlets;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;



/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String Spieler = "/Spieler.jsp";
//	private static final String Gegner = "/Gegner.jsp";
	
	

    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		ServletContext sc = this.getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher(Spieler); 
//		RequestDispatcher rd2 = sc.getRequestDispatcher(Gegner);
		
		int anz = Integer.parseInt(request.getParameter("spielerAnz"));
		request.getSession().setAttribute("anzahl", anz);
		Index.getGame().setAnzahlWeb(anz);
		
//		if(anz==1){
			rd.forward(request, response);
//		}else{
//			rd2.forward(request, response);
//		}
		
		
		
	}
	
	
	


}
