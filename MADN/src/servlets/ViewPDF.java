package servlets;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class ViewPDF
 */
@WebServlet("/ViewPDF")
public class ViewPDF extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String error = "/Error.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewPDF() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType( "application/pdf" );
		String file=(String)request.getSession().getAttribute("filePDF");
		String fileURL = "/Users/sevenvista/Desktop/"+file+".pdf";	
		
		if(file==null){
			response.sendRedirect("/MADNWeb/Error.jsp");
			
		}else{
			
			OutputStream out = response.getOutputStream();
	        FileInputStream in = new FileInputStream(new File(fileURL));
	        byte[] buffer = new byte[4096];
	        int length;
	        while ((length = in.read(buffer)) > 0) {
	            out.write(buffer, 0, length);
	        }
	        in.close();
	        out.flush();
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		this.doGet(request, response);
		
		
	}

}
