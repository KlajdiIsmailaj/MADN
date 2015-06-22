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

/**
 * Servlet implementation class Ki
 */
@WebServlet("/Ki")
public class Ki extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String wait = "/Wait.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ki() {
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
		RequestDispatcher rd3 = sc.getRequestDispatcher(wait);
		
		Integer anz=(Integer)request.getSession(false).getAttribute("anzahl");
		
		if(anz==2){
			String farbe2 = request.getParameter("kifarbe2");
			String typ2 = request.getParameter("kityp2");
			
			Index.getGame().newSpieler("Ki 2", farbe2, typ2);
			this.tableGame(request, response);
			
		}else if(anz==3){
			String farbe2 = request.getParameter("kifarbe2");
			String typ2 = request.getParameter("kityp2");
			
			String farbe3 = request.getParameter("kifarbe3");
			String typ3 = request.getParameter("kityp3");
			
			if(typ2!=null&&typ2.startsWith("K")){
				Index.getGame().newSpieler("Ki 2", farbe2, typ2);
			}if(typ3!=null&&typ3.startsWith("K")){
				Index.getGame().newSpieler("Ki 3", farbe3, typ3);
			}
			
			if(typ2!=null&&typ3!=null){
				this.tableGame(request, response);
			}else{
				rd3.forward(request, response);
			}
			
			
		}else if(anz==4){
			String farbe2 = request.getParameter("kifarbe2");
			String typ2 = request.getParameter("kityp2");
			
			String farbe3 = request.getParameter("kifarbe3");
			String typ3 = request.getParameter("kityp3");
			
			String farbe4 = request.getParameter("kifarbe4");
			String typ4 = request.getParameter("kityp4");
			
			
			if(typ2!=null&&typ2.startsWith("K")){
				Index.getGame().newSpieler("Ki 2", farbe2, typ2);
			}if(typ3!=null&&typ3.startsWith("K")){
				Index.getGame().newSpieler("Ki 3", farbe3, typ3);
			}if(typ4!=null&&typ4.startsWith("K")){
				Index.getGame().newSpieler("Ki 4", farbe4, typ4);
			}
			
			if(typ2!=null&&typ3!=null&&typ4!=null){
				this.tableGame(request, response);
			}else{
				rd3.forward(request, response);
			}
			
		}
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
