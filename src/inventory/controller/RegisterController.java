package inventory.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import inventory.dao.TeacherDAO;
import inventory.model.TeacherBean;

/**
 * Servlet implementation class RegisterController
 */
@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	  private String VIEW ="/user/viewAccount.jsp";
	private TeacherDAO dao;
    String forward="";   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if(action.equalsIgnoreCase("viewAccount")) {
			forward = VIEW;
			String email = request.getParameter("email");
			TeacherBean teacher = dao.getTeacherByEmail(email);
			request.setAttribute("teacher", teacher);
		}
		
		RequestDispatcher view = request.getRequestDispatcher(forward);
	    view.forward(request, response);
	}

    


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		int managerid = Integer.parseInt(request.getParameter("managerid"));
		String role = request.getParameter("role");

		
		TeacherBean teacher = new TeacherBean();
		
		teacher.setName(name);
		teacher.setPhone(phone);
		teacher.setAddress(address);
		teacher.setEmail(email);
		teacher.setPassword(password);
		teacher.setManagerid(managerid);
		teacher.setRole(role);

		
	
		
		dao = new TeacherDAO();
		
		try {
			dao.add(teacher);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setContentType("text/html");
	      PrintWriter pw = response.getWriter();
	      pw.println("<script>");
	      pw.println("alert('The account has been created');");
	      pw.println("window.location.href='/Inventory/UserController?action=listAll';");
	      pw.println("</script>");
		//response.sendRedirect("orders.html");
		
	}

}
