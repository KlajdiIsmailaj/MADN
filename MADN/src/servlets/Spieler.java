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
	
//	private static final String wait = "/Wait.jsp";
	private static final String Gegner = "/Gegner.jsp";
	
	


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
		RequestDispatcher rd2 = sc.getRequestDispatcher(Gegner);
		
		
//		String typ1=(String) request.getSession(false).getAttribute("typ1");
//		String typ2=(String) request.getSession(false).getAttribute("typ2");
//		String typ3=(String) request.getSession(false).getAttribute("typ3");
//		
//		Integer anz=(Integer)request.getSession(false).getAttribute("anzahl");
		
		String name = request.getParameter("name");	
		String farbe = request.getParameter("farbeSpieler");
		session.setAttribute("farbeSpieler", farbe);
//		String spielerart = request.getParameter("spielerart");
		
//		if(Index.getGame().bestandSpielerlist()==0){
			Index.getGame().newSpieler(name, farbe, "Mensch");
			
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
			
			this.tableGame(request, response);
			
		}else{
			rd2.forward(request, response);
		}
		
//		if(anz==2){
//			if(typ1.startsWith("M")){ 
//				rd2.forward(request, response);
//			}else{
//				this.tableGame(request, response);
//			}
//		}else if(anz==3){
//			if(typ1.startsWith("M")||typ2.startsWith("M")){
//				rd2.forward(request, response);
//			}else{
//				this.tableGame(request, response);
//			}
//		}else if(anz==4){
//			if(typ1.startsWith("M")||typ2.startsWith("M")||typ3.startsWith("M")){ 
//				rd2.forward(request, response);
//			}else{
//				this.tableGame(request, response);
//			}
//		}
		
		
	}
	
	public void tableGame(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Index.getGame().spielStart();
		try{
			out.println(Brett.getHeader());
			out.println(Brett.getMenu());
			out.println(Brett.getTable());
			out.println(Brett.getMenuEnd());
		}finally{
			out.println(Brett.getFooter());
			out.close();
		}
	}
	
	

	
	
	
}
