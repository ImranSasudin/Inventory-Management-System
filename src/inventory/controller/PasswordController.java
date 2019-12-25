package inventory.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inventory.dao.TeacherDAO;
import inventory.model.TeacherBean;

/**
 * Servlet implementation class PasswordController
 */
@WebServlet("/PasswordController")
public class PasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PasswordController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String currentPassword = request.getParameter("currentPassword");
		String newPassword = request.getParameter("newPassword");
		
		TeacherBean teacher = new TeacherBean();
		teacher.setEmail(email);
		teacher.setPassword(currentPassword);
		
		try {
			teacher = TeacherDAO.getPassword(teacher);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(teacher.isValid()) {
			teacher.setPassword(newPassword);
			try {
				TeacherDAO.updatePass(teacher);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.setContentType("text/html");
		      PrintWriter pw = response.getWriter();
		      pw.println("<script>");
		      pw.println("alert('Password Updated!');");
		      pw.println("window.location.href='/Inventory/UserController?action=viewAccount&email="+ email +"';");
		      pw.println("</script>");
		}
		else {
			response.setContentType("text/html");
		      PrintWriter pw = response.getWriter();
		      pw.println("<script>");
		      pw.println("alert('Current Password is Incorrect!');");
		      pw.println("window.location.href='/Inventory/user/updatePass.jsp';");
		      pw.println("</script>");
			
		}
	}

}
