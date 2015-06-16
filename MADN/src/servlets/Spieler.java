package servlets;

import java.io.IOException;





import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;



/**
 * Servlet implementation class Login
 */
@WebServlet("/Spieler")
public class Spieler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String wait = "/Wait.jsp";


    /**
     * @see HttpServlet#HttpServlet()
     */
    public Spieler() {
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
		HttpSession session=request.getSession();
		
		ServletContext sc = this.getServletContext();
		RequestDispatcher rd2 = sc.getRequestDispatcher(wait);
		
		
		
		
		
		String name = request.getParameter("name");	
		String farbe = request.getParameter("farbe");
		session.setAttribute("farbe", farbe);
		String spielerart = request.getParameter("spielerart");
		
//		if(Index.getGame().bestandSpielerlist()==0){
			Index.getGame().newSpieler(name, farbe, spielerart);
			
//			session.setAttribute("farbe1", farbe);
//		}
//		else if(farbe.equals(session.getAttribute("farbe1"))){
//			
//			rd1.forward(request, response);
//			System.out.println("der spieler existiert schon");
//		}
//		else if(!farbe.equals(session.getAttribute("farbe1"))){
//			Index.getGame().newSpieler(name, farbe, spielerart);
//			session.setAttribute("farbe1", farbe);
//		}	
		
	
		
		
		
		if(Index.getGame().getAnzahlWeb()==1){
			
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			try{
				out.println(Brett.getHeader());
				out.println(Brett.getMenu());
			}finally{
				out.println(Brett.getFooter());
				out.close();
			}
			
			Index.getGame().spielStart();
		}else{
			rd2.forward(request, response);
		}
	}
	
	
	

	
	
	
}
