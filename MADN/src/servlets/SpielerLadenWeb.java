package servlets;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import testmadn.DatenzugriffSerialisiert;
import testmadn.SpielBean;
import testmadn.iBediener;
import testmadn.iDatenzugriff;

/**
 * Servlet implementation class SpielerLadenWeb
 */
@WebServlet("/SpielerLadenWeb")
public class SpielerLadenWeb extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static iBediener backend;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SpielerLadenWeb() {
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
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		SpielerLadenWeb.createGameLaden(request, response);
		try{
			out.println(BrettLaden.getHeader());
			out.println(BrettLaden.getMenu());
		}finally{
			out.println(BrettLaden.getFooter());
			out.close();
		}
		
	}
	
	public static void createGameLaden(HttpServletRequest request, HttpServletResponse response){
		
		iDatenzugriff ser=new DatenzugriffSerialisiert();
		String	pfad = request.getParameter("ser");
		backend= (SpielBean)ser.laden("/Users/sevenvista/Desktop/"+pfad);
	}
	
	public static iBediener getGameLaden(){
	 	return backend;
	}
	
	

}
