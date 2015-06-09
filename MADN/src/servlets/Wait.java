package servlets;

import java.io.IOException;
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
	
	private static final String brett = "/Spielbrett.jsp";
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
		
//		System.out.println(Index.getGame().nameSpieler());
		
		response.setContentType("text/html;charset=ISO-8859-1");
		ServletContext sc = this.getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher(brett);
		RequestDispatcher rd1 = sc.getRequestDispatcher(wait);
		
		
//		String a=(String) request.getSession(false).getAttribute("farbe1");
//		int anzahl = (Integer)request.getSession(false).getAttribute("anz");
//		System.out.println(a);
//		System.out.println(anzahl);
//		for(int i=0;i<Index.getGame().spielerName().length;i++){
//			System.out.println(Index.getGame().spielerName());
//		}
//		
		
		
		
		
		System.out.println(Index.getGame().nameWeb1());
		System.out.println(Index.getGame().nameWeb2());
		
		String a=Index.getGame().nameWeb1();
		String b=Index.getGame().nameWeb2();
		
//		
		request.setAttribute("a", a);
		request.setAttribute("b", b);
		
		if(list.size()==0){
			list.add(request.getSession().getId());
//			Object name1=Index.getGame().ladeName(0);
//			request.setAttribute("name1", name1);
		}else if(list.size()==1&&list.get(0)!=request.getSession().getId()){
			list.add(request.getSession().getId());
//			Object name2=Index.getGame().ladeName(1);
//			request.setAttribute("name2", name2);
			Index.getGame().spielStart();
		}else if(list.size()==2&&list.get(1)!=request.getSession().getId()&&list.get(0)!=request.getSession().getId()){
			list.add(request.getSession().getId());
			
		}else if(list.size()==3&&list.get(2)!=request.getSession().getId()&&list.get(1)!=request.getSession().getId()&&list.get(0)!=request.getSession().getId()){
			list.add(request.getSession().getId());
		}else if(list.size()==4&&list.get(3)!=request.getSession().getId()&&list.get(2)!=request.getSession().getId()&&list.get(1)!=request.getSession().getId()&&list.get(0)!=request.getSession().getId()){
			list.add(request.getSession().getId());
		}
//		
		if(Index.getGame().getAnzahlWeb()==list.size()){
			
			rd.forward(request, response);
		}else{
//			
			rd1.forward(request, response);
		}
		
	}

	

	
	
	
}
