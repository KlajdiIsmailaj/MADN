package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import testmadn.DatenzugriffCSV;
import testmadn.DatenzugriffPDF;
import testmadn.iDatenzugriff;

/**
 * Servlet implementation class SpeichernCSV
 */
@WebServlet("/SpeichernCSV")
public class SpeichernCSV extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SpeichernCSV() {
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
		String namefile =request.getParameter("textareaCSV");
		
		iDatenzugriff csv = new DatenzugriffCSV();
		
		if(Index.getGame()!=null){
			
			csv.speichern("/Users/sevenvista/Desktop/"+ namefile, Index.getGame());
			
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
			
		}else if(SpielerLadenWeb.getGameLaden()!=null){
			csv.speichern("/Users/sevenvista/Desktop/"+ namefile, SpielerLadenWeb.getGameLaden());
			
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
		
	}

}
