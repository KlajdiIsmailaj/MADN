package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Gegner
 */
@WebServlet("/Gegner")
public class Gegner extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
//	private static final String Spieler = "/Spieler.jsp";
	private static final String wait = "/Wait.jsp";
	private static final String Ki = "/Ki.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Gegner() {
        super();
        // TODO Auto-generated constructor stub
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
		RequestDispatcher rd = sc.getRequestDispatcher(wait);
		RequestDispatcher rd2= sc.getRequestDispatcher(Ki);
		
		String typ1 = request.getParameter("spielerart1");
		request.getSession().setAttribute("typ1", typ1);
		String typ2 = request.getParameter("spielerart2");
		request.getSession().setAttribute("typ2", typ2);
		String typ3 = request.getParameter("spielerart3");
		request.getSession().setAttribute("typ3", typ3);
		
		
		Integer anz=(Integer)request.getSession(false).getAttribute("anzahl");
		
		if(anz==2){
			if(typ1.equals("Mensch")){
				rd.forward(request, response);
			}else if(typ1.equals("Ki")){
				rd2.forward(request, response);
			}
		}else if(anz==3){
			if(typ1.equals("Ki")||typ2.equals("Ki")){
				rd2.forward(request, response);
			}else{
				rd.forward(request, response);
			}
		}else if(anz==4){
			if(typ1.equals("Ki")||typ2.equals("Ki")||typ3.equals("Ki")){
				rd2.forward(request, response);
			}else{
				rd.forward(request, response);
			}
		}
		
	}

}
