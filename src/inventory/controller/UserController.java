package inventory.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.List;

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
 * Servlet implementation class UserController
 */
@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String VIEW ="/user/viewAccount.jsp";
    private String VIEWALL ="/user/listAllTeacher.jsp";
    private static String UPDATE = "/user/updateAccount.jsp";
    private static String UPDATEPASS = "/user/updatePass.jsp";
    private static String DELETE = "/user/deleteAccount.jsp";
    private static String SEARCH = "/user/createTeacher.jsp";
   
    String forward;
    private TeacherDAO dao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
       dao = new TeacherDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if(action.equalsIgnoreCase("viewAccount")) {
			forward = VIEW;
			String email = request.getParameter("email");
			TeacherBean teacher = TeacherDAO.getTeacherByEmail(email);
			List<TeacherBean> teacher2 = TeacherDAO.getAllAdmin();
			request.setAttribute("teachers", teacher2);
			request.setAttribute("teacher", teacher);
		}
		else if (action.equalsIgnoreCase("listAll")) {
			forward = VIEWALL;
            request.setAttribute("teachers", dao.getAllTeacher()); 

		}
		else if (action.equalsIgnoreCase("updateAccount")){
			forward = UPDATE;
        	String email = request.getParameter("email");
        	TeacherBean teacher = TeacherDAO.getTeacherByEmail(email);
        	List<TeacherBean> teacher2 = TeacherDAO.getAllAdmin();
        	request.setAttribute("teachers", teacher2);
        	request.setAttribute("teacher", teacher);
		}
		else if (action.equalsIgnoreCase("updatePass")){
			forward = UPDATEPASS;
        	String email = request.getParameter("email");
        	TeacherBean teacher = TeacherDAO.getTeacherByEmail(email);
        	request.setAttribute("teacher", teacher);
		}
		
		
        else if (action.equalsIgnoreCase("search")){
    		forward = SEARCH;
           	List<TeacherBean> teacher = TeacherDAO.getAllAdmin();
        	request.setAttribute("teachers", teacher);

           	
    }
        else if (action.equalsIgnoreCase("deleteAccount")){
        	forward = DELETE;
        	String email = request.getParameter("email");
        	TeacherBean teacher = TeacherDAO.getTeacherByEmail(email);
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
		String action = request.getParameter("action");
		if(action.equalsIgnoreCase("Submit")) {
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		int managerid = Integer.parseInt(request.getParameter("managerid"));
		
		TeacherBean teacher = new TeacherBean();
		
		teacher.setEmail(email);
		teacher.setName(name);
		teacher.setPhone(phone);
		teacher.setAddress(address);
		teacher.setManagerid(managerid);
		HttpSession session = request.getSession(true);
		if(String.valueOf(session.getAttribute("currentSessionUserRole")).equalsIgnoreCase("Admin")) {
			String role = request.getParameter("role");
			teacher.setRole(role);
		}
		
		
		dao = new TeacherDAO();
		try {
			dao.updateAccount(teacher);
			response.setContentType("text/html");
		      PrintWriter pw = response.getWriter();
		      pw.println("<script>");
		      pw.println("alert('The account is updated');");
		      pw.println("window.location.href='/Inventory/index.jsp';");
		      pw.println("</script>");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		else if(action.equalsIgnoreCase("Delete")) {
			int id = Integer.parseInt(request.getParameter("teacherid"));
			dao = new TeacherDAO();
			dao.deleteTeacher(id);
			response.setContentType("text/html");
		      PrintWriter pw = response.getWriter();
		      pw.println("<script>");
		      pw.println("alert('The account has been deleted');");
		      pw.println("window.location.href='/Inventory/UserController?action=listAll';");
		      pw.println("</script>");
		}
	}
	
	
	
}
