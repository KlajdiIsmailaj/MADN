package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Wait")
public class Wait extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String wait = "/Wait.jsp";
	ArrayList<String> list = new ArrayList<String>();
	

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Wait() {
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
		
		
		response.setContentType("text/html;charset=ISO-8859-1");
		ServletContext sc = this.getServletContext();
		RequestDispatcher rd1 = sc.getRequestDispatcher(wait);
		
		String a=Index.getGame().nameWeb1();
		String b=Index.getGame().nameWeb2();
		
		request.setAttribute("a", a);
		request.setAttribute("b", b);
		
		if(list.size()==0){
			list.add(request.getSession().getId());
		}else if(list.size()==1&&list.get(0)!=request.getSession().getId()){
			list.add(request.getSession().getId());	
		}else if(list.size()==2&&list.get(1)!=request.getSession().getId()&&list.get(0)!=request.getSession().getId()){
			list.add(request.getSession().getId());
			
		}else if(list.size()==3&&list.get(2)!=request.getSession().getId()&&list.get(1)!=request.getSession().getId()&&list.get(0)!=request.getSession().getId()){
			list.add(request.getSession().getId());
		}else if(list.size()==4&&list.get(3)!=request.getSession().getId()&&list.get(2)!=request.getSession().getId()&&list.get(1)!=request.getSession().getId()&&list.get(0)!=request.getSession().getId()){
			list.add(request.getSession().getId());
		}
		
		if(Index.getGame().getAnzahlWeb()==list.size()){
			
			
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			try{
				out.println(Brett.getHeader());
				out.println(Brett.getMenu());
			}finally{
				out.println(Brett.getFooter());
				out.close();
			}
			
			Index.getGame().spielStart();
		}else{
			rd1.forward(request, response);
		}
	}

	

	
	
	
}
