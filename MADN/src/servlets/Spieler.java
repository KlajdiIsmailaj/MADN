package servlets;

import java.awt.image.BufferedImage;
import java.io.IOException;





import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Spieler")
public class Spieler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String brett = "/Spielbrett.jsp";
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
		ServletContext sc = this.getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher(brett);
		RequestDispatcher rd2 = sc.getRequestDispatcher(wait);
		
		String name = request.getParameter("name");
//		request.getSession(true).setAttribute("name",name);	
		
		String farbe = request.getParameter("farbe");
//		request.getSession(true).setAttribute("farbe",farbe);
		
		String spielerart = request.getParameter("spielerart");
//		request.getSession(true).setAttribute("spielerart",spielerart);	
		
		Index.getGame().newSpieler(name, farbe, spielerart);
		
		
//		HttpSession s = request.getSession(true);
//		if(s.getAttribute("Speil")==null){
//			s.setAttribute("Spiel", Index.getGame());
//		}
//		SpielBean spiel= (SpielBean) s.getAttribute("Spiel");
//		System.out.println("server-konsole" + spiel);
//		response.setContentType("text/html");
//		PrintWriter out = response.getWriter();
//		try{
//			
//			out.println(Brett.getHeader());
//			out.println(Brett.getMenu());
//			out.println("<center>");
//			out.println("<h1> Wellcome  !!!!</h1>");
//			out.println("</center>");
//			
//			
//			
//		}finally{
//			out.println(Brett.getFooter());
//			out.close();
//			
//		}
		
		
		
		
		
		if(Index.getGame().getAnzahlWeb()==1){
			Index.getGame().spielStart();
			
			
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			try{
				
				out.println(Brett.getHeader());
				out.println(Brett.getMenu());
//				out.println("<center>");
//				
//				out.println("</center>");
				
				
				
			}finally{
				out.println(Brett.getFooter());
				out.close();
				
			}
			
			
			rd.forward(request, response);
		}else{
			rd2.forward(request, response);
		}
	}

	

	
	
	
}
