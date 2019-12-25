package inventory.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import inventory.dao.TeacherDAO;
import inventory.model.TeacherBean;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private TeacherDAO dao;
    HttpServletRequest request;
    HttpServletResponse response;
    String forward="";
    String action="";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        dao = new TeacherDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	action = request.getParameter("action");
		HttpSession session = request.getSession(true);
		if (action.equalsIgnoreCase("Logout")){ //logout
		session.setAttribute("currentSessionUser", null);
		session.setAttribute("currentSessionUserRole", null);
		session.invalidate();
		
		response.setContentType("text/html");
	      PrintWriter pw = response.getWriter();
	      pw.println("<script>");
	      pw.println("alert('Logout Success');");
	      pw.println("window.location.href='/Inventory/login.jsp';");
	      pw.println("</script>");
		}
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
        	try {
    			
        		String email = request.getParameter("email");
        		String password = request.getParameter("password");
        		
    			TeacherBean teacher = new TeacherBean();
    			teacher.setEmail(email);
    			teacher.setPassword(password);

    			teacher = TeacherDAO.login(teacher);

    			if(teacher.isValid())
    			{
    				teacher = TeacherDAO.getTeacherByEmail(email);
    				HttpSession session = request.getSession(true);
    				session.setAttribute("currentSessionUser", teacher.getEmail());
    				session.setAttribute("currentSessionUserRole", teacher.getRole());
    				session.setAttribute("currentSessionUserName", teacher.getName());
    				response.setContentType("text/html");
    			      PrintWriter pw = response.getWriter();
    			      pw.println("<script>");
    			      pw.println("alert('Login Successful');");
    			      pw.println("window.location.href='/Inventory/index.jsp';");
    			      pw.println("</script>");
    			}
    			else
    			{
    				response.setContentType("text/html");
    			      PrintWriter pw = response.getWriter();
    			      pw.println("<script>");
    			      pw.println("alert('Incorrect Email or Password');");
    			      pw.println("window.location.href='/Inventory/login.jsp';");
    			      pw.println("</script>");
    			}
    			
    		}

    		catch (Throwable ex) {
    			System.out.println(ex);
    		}
 
	}
}
