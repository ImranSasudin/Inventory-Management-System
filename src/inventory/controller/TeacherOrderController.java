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

import inventory.dao.ProductDAO;
import inventory.dao.TeacherDAO;
import inventory.dao.TeacherProductDAO;
import inventory.model.ProductBean;
import inventory.model.TeacherBean;
import inventory.model.TeacherProduct;

/**
 * Servlet implementation class TeacherOrderController
 */
@WebServlet("/TeacherOrderController")
public class TeacherOrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String UPDATE = "/teacher_orders/adminUpdateOrder.jsp";
	String forward="";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeacherOrderController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		if(action.equalsIgnoreCase("updateOrder")) {
			String orderid = request.getParameter("orderid");
			forward = UPDATE;
            TeacherProduct teacherProduct = TeacherProductDAO.getOrderById(orderid);
            List<ProductBean> product = ProductDAO.getAllProduct();
            List<TeacherBean> teacher = TeacherDAO.getAllTeacher();
         	request.setAttribute("teacherProduct", teacherProduct);  
         	request.setAttribute("products", product);
         	request.setAttribute("teachers", teacher);
         	RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		TeacherProduct teacherProduct = new TeacherProduct();
		ProductBean product = new ProductBean();
		if(action.equalsIgnoreCase("Update")) {
			String status = request.getParameter("status");
			int quantity = Integer.parseInt(request.getParameter("orderProdQuantity"));
			int orderid = Integer.parseInt(request.getParameter("orderid"));
			int prodid = Integer.parseInt(request.getParameter("prodid"));
			teacherProduct.setOrderid(orderid);
			teacherProduct.setStatus(status);
			teacherProduct.setQuantity(quantity);
			product.setProdQuantity(quantity);
			product.setProdid(prodid);
			if(status.equalsIgnoreCase("Completed")) {
				try {
					TeacherProductDAO.updateStatus(teacherProduct);
					ProductDAO.updateQuantity2(product);
					response.setContentType("text/html");
				      PrintWriter pw = response.getWriter();
				      pw.println("<script>");
				      pw.println("alert('The order has been updated');");
				      pw.println("window.location.href='/Inventory/TeacherProductController?action=listOrder';");
				      pw.println("</script>");
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
			}
			else {
				response.setContentType("text/html");
			      PrintWriter pw = response.getWriter();
			      pw.println("<script>");
			      pw.println("alert('No Changes');");
			      pw.println("window.location.href='/Inventory/TeacherProductController?action=listOrder';");
			      pw.println("</script>");
			}
		}
	}

}
