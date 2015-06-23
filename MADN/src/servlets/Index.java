package servlets;

import java.io.IOException;
import java.util.ArrayList;

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
//	private static final String Spieler = "/Spieler.jsp";
	private static final String NextSpieler = "/NextSpieler.jsp";
	private static final String Error = "/Error.jsp";
	private static final String waitLaden = "/WaitLaden.jsp";
	
	
//	private String[]sessionId=new String[1];
	ArrayList<String> list = new ArrayList<String>();
	private static iBediener backend;
	
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
		list.add(request.getSession().getId());
		
		ServletContext sc = this.getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher(Login);
		RequestDispatcher rd2 = sc.getRequestDispatcher(Error);
		RequestDispatcher rd3 = sc.getRequestDispatcher(NextSpieler);
		RequestDispatcher rd4 = sc.getRequestDispatcher(waitLaden);
		
//		System.out.println(list.size());
		
		if(SpielerLadenWeb.getGameLaden()!=null){
			
//			System.out.println(SpielerLadenWeb.getGameLaden().gibAnzahlMensch());
			int anzahlDrin=SpielerLadenWeb.getGameLaden().gibAnzahlMensch()-1;
			
			if(anzahlDrin==list.size()){
				rd4.forward(request, response);
			}else{
				rd2.forward(request, response);
			}
		}
		else if(Index.getGame()==null){
			
			Index.createGame();
			rd.forward(request, response);
			
		}else if(Index.getGame()!=null){
			if(Index.getGame().getAnzahlWeb()>Index.getGame().bestandSpielerlist()){
				rd3.forward(request, response);
			}else{
				rd2.forward(request, response);
			}
		}
	}
	
	
	public static void createGame(){
		backend = new SpielBean();
	}
	
	public static iBediener getGame(){
	 	return backend;
	}
	
}
