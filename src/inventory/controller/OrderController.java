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

import inventory.dao.OrderDAO;
import inventory.dao.OrderProductDAO;
import inventory.dao.ProductDAO;
import inventory.dao.SupplierDAO;
import inventory.dao.TeacherDAO;
import inventory.model.OrderProduct;
import inventory.model.Orders;
import inventory.model.ProductBean;
import inventory.model.SupplierBean;
import inventory.model.TeacherBean;


/**
 * Servlet implementation class OrderController
 */
@WebServlet("/OrderController")
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String VIEW = "/orders/viewOrders.jsp";
	private static String DELETE = "/orders/deleteOrders.jsp";
	private static String UPDATE = "/orders/updateOrders.jsp";
	private static String SEARCH = "/orders/createOrder.jsp";
	String forward="";
    /**
     * @see HttpServlet#HttpServlet()
     */
	 public OrderController() {
	        super();
	       
	        // TODO Auto-generated constructor stub
	    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		if(action.equalsIgnoreCase("viewOrder")) {
			forward = VIEW;
	        request.setAttribute("ordersProduct", OrderProductDAO.getAllOrderProduct());   
	        request.setAttribute("products", ProductDAO.getAllProduct());
	        request.setAttribute("teachers", TeacherDAO.getAllTeacher());
		}
		else if(action.equalsIgnoreCase("deleteOrder")) {
			forward = DELETE;
            int orderid = Integer.parseInt(request.getParameter("orderid"));
            OrderProduct orderProduct = new OrderProduct();
            orderProduct = OrderProductDAO.getOrderProductByOrderid(orderid);
        	request.setAttribute("orderProduct", orderProduct);
		}
		else if(action.equalsIgnoreCase("updateOrder")) {
			forward = UPDATE;
            int orderid = Integer.parseInt(request.getParameter("orderid"));
            OrderProduct orderProduct = new OrderProduct();
            orderProduct = OrderProductDAO.getOrderProductByOrderid(orderid);
        	request.setAttribute("orderProduct", orderProduct);
		}
		else if (action.equalsIgnoreCase("createOrder")){
     		forward = SEARCH;
            List<ProductBean> product = ProductDAO.getAllProduct();
         	request.setAttribute("products", product);     	
  		}
		RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int orderid = Integer.parseInt(request.getParameter("orderid"));
		int prodid = Integer.parseInt(request.getParameter("prodid"));
		String action = request.getParameter("action");
		if(action.equalsIgnoreCase("Delete")) {
        	OrderDAO.deleteOrder(orderid);
        	response.setContentType("text/html");
		      PrintWriter pw = response.getWriter();
		      pw.println("<script>");
		      pw.println("alert('The order has been deleted');");
		      pw.println("window.location.href='/Inventory/OrderController?action=viewOrder';");
		      pw.println("</script>");
        	}
		else if(action.equalsIgnoreCase("Submit")) {
			String status = request.getParameter("status");
			int orderProdQuantity = Integer.parseInt(request.getParameter("orderProdQuantity"));
			OrderProduct orderProduct = new OrderProduct();
			orderProduct.setOrderid(orderid);
			orderProduct.setProdid(prodid);
			orderProduct.setStatus(status);
			orderProduct.setOrderProdQuantity(orderProdQuantity);
			String email = request.getParameter("email");
			TeacherBean teacher = TeacherDAO.getTeacherByEmail(email);
			orderProduct.setTeacherid(teacher.getTeacherid());
			
			if(status.equalsIgnoreCase("Received")) {
				try {
					OrderProductDAO.updateOrder(orderProduct);
					response.setContentType("text/html");
				      PrintWriter pw = response.getWriter();
				      pw.println("<script>");
				      pw.println("alert('The order has been updated');");
				      pw.println("window.location.href='/Inventory/OrderController?action=viewOrder';");
				      pw.println("</script>");
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
