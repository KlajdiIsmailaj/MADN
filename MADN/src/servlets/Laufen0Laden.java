package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Laufen
 */
@WebServlet("/Laufen0Laden")
public class Laufen0Laden extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Laufen0Laden() {
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
		
		
		SpielerLadenWeb.getGameLaden().laufen(0);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try{
			out.println(BrettLaden.getHeader());
			out.println(BrettLaden.getMenu());
		}finally{
			out.println(BrettLaden.getFooter());
			out.close();
		}
		
	
	}
	
	

}
