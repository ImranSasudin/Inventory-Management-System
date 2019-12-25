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


import inventory.dao.OrderDAO;
import inventory.dao.OrderProductDAO;
import inventory.dao.ProductDAO;
import inventory.dao.TeacherDAO;
import inventory.model.OrderProduct;
import inventory.model.Orders;
import inventory.model.ProductBean;
import inventory.model.TeacherBean;
import inventory.model.TeacherProduct;


/**
 * Servlet implementation class OrderProductController
 */
@WebServlet("/OrderProductController")
public class OrderProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String VIEW = "/orders/viewOrders.jsp";
	private static String SEARCH = "/orders/deleteOrders.jsp";
	private static String DELETE = "/orders/deleteOrders.jsp";

	 String forward="";
	private OrderProductDAO daoOrderProduct;

	 	

   
	
    /**
     * @see HttpServlet#HttpServlet()
     */
	 public OrderProductController() {
	        super();
	        daoOrderProduct = new OrderProductDAO();
	        // TODO Auto-generated constructor stub
	    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		  String action = request.getParameter("action");

		if(action.equalsIgnoreCase("view")) {
			forward = VIEW;
	        request.setAttribute("ordersProduct", OrderProductDAO.getAllOrderProduct());         
          }
		else if (action.equalsIgnoreCase("Search")){
     		forward = SEARCH;
            int orderid = Integer.parseInt(request.getParameter("orderid"));
           OrderProduct orderProduct = new OrderProduct();
           orderProduct.setOrderid(orderid);
           orderProduct = OrderProductDAO.getOrderProductByOrderid(orderid);
         	request.setAttribute("orderProduct", orderProduct);     	
     }
		  else if (action.equalsIgnoreCase("Delete")){
	           	forward = DELETE;
	            int orderid = Integer.parseInt(request.getParameter("orderid"));
	            OrderProduct orderProduct = new OrderProduct();
	            orderProduct.setOrderid(orderid);
	            daoOrderProduct.deleteOrderProduct(orderid);
	       }
		 RequestDispatcher view = request.getRequestDispatcher(forward);
         view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrderProduct orderproduct = new OrderProduct();
		Orders order = new Orders();
		ProductBean product = new ProductBean();
		ProductDAO productdao = new ProductDAO();
		OrderDAO orderdao = new OrderDAO();
		OrderProductDAO orderproductdao = new OrderProductDAO();
		TeacherProduct teacherProduct = new TeacherProduct();
		
		String status = " ";
		String email = request.getParameter("email");
		TeacherBean teacher = TeacherDAO.getTeacherByEmail(email);
		orderproduct.setTeacherid(teacher.getTeacherid());
        
        String[] prodid2 = request.getParameterValues("prod");
        
        int[] prodid = new int[prodid2.length];
        for(int i=0;i<prodid2.length;i++){
            prodid[i]=Integer.parseInt(prodid2[i]);
        }
        
        String[] qty2 = request.getParameterValues("orderProdQuantity");
        
        int[] qty = new int[qty2.length];
        for(int i=0;i<qty2.length;i++){
        	qty[i]=Integer.parseInt(qty2[i]);
        }
        orderproduct.setStatus("Pending");
        order.setTeacherid(teacher.getTeacherid());
        
        for(int i = 0; i < prodid.length; i++) {
        	OrderDAO.add(order);
        	orderproduct.setProdid(prodid[i]);
        	orderproduct.setOrderProdQuantity(qty[i]);
        	orderproduct.setOrderid(order.getOrderid());
        	orderproductdao.add(orderproduct);
        }
        
        response.setContentType("text/html");
	      PrintWriter pw = response.getWriter();
	      pw.println("<script>");
	      pw.println("alert('The order has been created');");
	      pw.println("window.location.href='/Inventory/OrderController?action=viewOrder';");
	      pw.println("</script>"); // logged-in page

		//orderproduct = new OrderProduct(orderid, prodid, status, orderProdQuantity, false);
		
		
		
	}

}
