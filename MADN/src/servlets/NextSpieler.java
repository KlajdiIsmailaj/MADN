package servlets;

import java.io.IOException;





import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;



/**
 * Servlet implementation class Login
 */
@WebServlet("/NextSpieler")
public class NextSpieler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String wait = "/Wait.jsp";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NextSpieler() {
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
		String farbe = request.getParameter("farbeNext");
		session.setAttribute("farbeNext", farbe);
		
		Index.getGame().newSpieler(name, farbe, "Mensch");
		rd2.forward(request, response);
		
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
