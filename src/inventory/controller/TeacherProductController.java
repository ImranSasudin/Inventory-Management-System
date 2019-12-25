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
import inventory.dao.TeacherDAO;
import inventory.dao.TeacherProductDAO;
import inventory.model.OrderProduct;
import inventory.model.Orders;
import inventory.model.ProductBean;
import inventory.model.TeacherBean;
import inventory.model.TeacherProduct;

/**
 * Servlet implementation class TeacherProductController
 */
@WebServlet("/TeacherProductController")
public class TeacherProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String CREATE = "/teacher_orders/addOrder.jsp";
	private static String VIEW = "/teacher_orders/viewOrder.jsp";
	private static String UPDATE = "/teacher_orders/updateOrder.jsp";
	private static String LIST = "/teacher_orders/listOrder.jsp";

	String forward="";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeacherProductController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equalsIgnoreCase("createOrder")){
     		forward = CREATE;
     		ProductDAO productDao = new ProductDAO();
            List<ProductBean> product = ProductDAO.getAllProduct();
         	request.setAttribute("products", product);     	
         	RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);
  		}
		else if (action.equalsIgnoreCase("viewOrder")){
     		forward = VIEW;
     		String email = request.getParameter("email");
    		TeacherBean teacher = TeacherDAO.getTeacherByEmail(email);
            List<TeacherProduct> teacherProduct = TeacherProductDAO.getAllTeacherOrder(teacher.getTeacherid());
            List<ProductBean> product = ProductDAO.getAllProduct();
         	request.setAttribute("teacherProducts", teacherProduct);  
         	request.setAttribute("products", product);
         	RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);
  		}
		else if (action.equalsIgnoreCase("updateOrder")){
     		forward = UPDATE;
     		String orderid = request.getParameter("orderid");
            TeacherProduct teacherProduct = TeacherProductDAO.getOrderById(orderid);
            List<ProductBean> product = ProductDAO.getAllProduct();
         	request.setAttribute("teacherProduct", teacherProduct);  
         	request.setAttribute("products", product);
         	RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);
  		}
		else if (action.equalsIgnoreCase("deleteOrder")) {
			int orderid = Integer.parseInt(request.getParameter("orderid"));
			String email = request.getParameter("email");
			TeacherProductDAO.deleteOrder(orderid);
			response.setContentType("text/html");
		      PrintWriter pw = response.getWriter();
		      pw.println("<script>");
		      pw.println("alert('The order has been updated');");
		      pw.println("window.location.href='/Inventory/TeacherProductController?action=viewOrder&email=" + email + "';");
		      pw.println("</script>");
		}
		else if (action.equalsIgnoreCase("listOrder")) {
			forward = LIST;
			List<ProductBean> product = ProductDAO.getAllProduct();
			List<TeacherProduct> teacherProduct = TeacherProductDAO.getAllTeacherOrders();
			List<TeacherBean> teacher = TeacherDAO.getAllTeacher();
			request.setAttribute("teacherProducts", teacherProduct);
			request.setAttribute("teachers", teacher);
			request.setAttribute("products", product);
			RequestDispatcher view = request.getRequestDispatcher(forward);
			view.forward(request, response);
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TeacherProduct teacherProduct = new TeacherProduct();
		Orders order = new Orders();
		ProductBean product = new ProductBean();
		ProductDAO productdao = new ProductDAO();
		OrderDAO orderdao = new OrderDAO();
		OrderProductDAO orderproductdao = new OrderProductDAO();
		String action = request.getParameter("action");
		
		if(action.equalsIgnoreCase("Submit")) {
			String status = " ";
	        String email = request.getParameter("email");
			TeacherBean teacher = TeacherDAO.getTeacherByEmail(email);
			teacherProduct.setTeacherid(teacher.getTeacherid());
	        String[] prodid2 = request.getParameterValues("prod");
	        
	        int[] prodid = new int[prodid2.length];
	        for(int i=0;i<prodid2.length;i++){
	        	String[] str = prodid2[i].split(" ");
				prodid[i]=Integer.parseInt(str[0]);
	        }
	        
	        String[] qty2 = request.getParameterValues("orderProdQuantity");
	        
	        int[] qty = new int[qty2.length];
	        for(int i=0;i<qty2.length;i++){
	        	qty[i]=Integer.parseInt(qty2[i]);
	        }
	        teacherProduct.setStatus("Pending");
	        
	        for(int i = 0; i < prodid.length; i++) {
	        	teacherProduct.setProdid(prodid[i]);
	        	teacherProduct.setQuantity(qty[i]);
	        	TeacherProductDAO.add(teacherProduct);
	        }
	        
	        response.setContentType("text/html");
		      PrintWriter pw = response.getWriter();
		      pw.println("<script>");
		      pw.println("alert('The order has been created');");
		      pw.println("window.location.href='/Inventory/TeacherProductController?action=viewOrder&email=" + email + "';");
		      pw.println("</script>");
		}
		else if (action.equalsIgnoreCase("Update")) {
			Integer orderProdQuantity = Integer.parseInt(request.getParameter("orderProdQuantity"));
			int orderid = Integer.parseInt(request.getParameter("orderid"));
			String email = request.getParameter("email");
			
			teacherProduct.setOrderid(orderid);
			teacherProduct.setQuantity(orderProdQuantity);
			teacherProduct.setEmail(email);
			
			try {
				TeacherProductDAO.updateOrder(teacherProduct);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.setContentType("text/html");
		      PrintWriter pw = response.getWriter();
		      pw.println("<script>");
		      pw.println("alert('The order has been updated');");
		      pw.println("window.location.href='/Inventory/TeacherProductController?action=viewOrder&email=" + email + "';");
		      pw.println("</script>");
		}
		
		
	}

}
