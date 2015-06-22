package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Beenden
 */
@WebServlet("/Beenden")
public class Beenden extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Beenden() {
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
		
		if(request.getParameter("beendenLaden")!=null){
			
			if(SpielerLadenWeb.getGameLaden().ermittleSpielerAmZugFarbe().equals(SpielerLadenWeb.getGameLaden().gibFarbeSession())){
				BrettLaden.press=true;
			}
			
			SpielerLadenWeb.getGameLaden().beenden();
			SpielerLadenWeb.getGameLaden().laufKi();
			
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			try{
				out.println(BrettLaden.getHeader());
				out.println(BrettLaden.getMenu());
				out.println(BrettLaden.getTable());
				out.println(BrettLaden.getMenuEnd());
				
			}finally{
				out.println(BrettLaden.getFooter());
				out.close();
			}
			
		}
		if(request.getParameter("beendenNormal")!=null){
			
			if(Index.getGame().ermittleSpielerAmZugFarbe().equals(Index.getGame().gibFarbeSession())){
				Brett.press=true;
			}
			
			Index.getGame().beenden();
			Index.getGame().laufKi();
			
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
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

}
