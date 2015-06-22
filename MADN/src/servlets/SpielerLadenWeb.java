package servlets;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import testmadn.DatenzugriffCSV;
import testmadn.DatenzugriffPDF;
import testmadn.DatenzugriffSerialisiert;
import testmadn.DatenzugriffXML;
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
	private static final String waitLaden = "/WaitLaden.jsp";
	
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
		
		ServletContext sc = this.getServletContext();
		RequestDispatcher rd2 = sc.getRequestDispatcher(waitLaden);
		
		SpielerLadenWeb.createGameLaden(request, response);
		
		if(SpielerLadenWeb.getGameLaden().bestandSpielerlist()==1){
			this.tableGame(request, response);
		}else if(SpielerLadenWeb.getGameLaden().menschDrin()==true){
			rd2.forward(request, response);
		}else if(SpielerLadenWeb.getGameLaden().menschDrin()==false){
			this.tableGame(request, response);
		}
		
	}
	
	public static void createGameLaden(HttpServletRequest request, HttpServletResponse response){
		
		iDatenzugriff ser=new DatenzugriffSerialisiert();
		iDatenzugriff csv=new DatenzugriffCSV();
		iDatenzugriff pdf=new DatenzugriffPDF();
		iDatenzugriff xml=new DatenzugriffXML();
		
		String	pfad = request.getParameter("ser");
		
		if(pfad.endsWith(".pdf")){
			backend= (SpielBean)pdf.laden("/Users/sevenvista/Desktop/"+pfad);
		}
		else if(pfad.endsWith(".csv")){
			backend= (SpielBean)csv.laden("/Users/sevenvista/Desktop/"+pfad);
		}
		else if(pfad.endsWith(".ser")){
			backend= (SpielBean)ser.laden("/Users/sevenvista/Desktop/"+pfad);
		}
		else if(pfad.endsWith(".xml")){
			backend= (SpielBean)xml.laden("/Users/sevenvista/Desktop/"+pfad);
		}
		
		
	}
	
	public static iBediener getGameLaden(){
	 	return backend;
	}
	
	public void tableGame(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
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
