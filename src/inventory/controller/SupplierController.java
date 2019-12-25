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


import inventory.dao.SupplierDAO;
import inventory.model.SupplierBean;
import inventory.model.TeacherBean;



/**
 * Servlet implementation class SupplierController
 */
@WebServlet("/SupplierController")
public class SupplierController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String LIST_SUPPLIER = "/supplier/viewAllSupplier.jsp";
	private static String UPDATE = "/supplier/updateSupplier.jsp";
	private static String DELETE = "/supplier/deleteSupplier.jsp";

	private String forward;
	SupplierDAO suppdao = new SupplierDAO();

   
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupplierController() {
        super();
        //suppdao = new SupplierDAO();

        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

    	 if (action.equalsIgnoreCase("listSupplier")){
             forward = LIST_SUPPLIER;
             request.setAttribute("suppliers", SupplierDAO.getAllSupplier()); 
         }
    	 else if (action.equalsIgnoreCase("updateSupplier")){
 			forward = UPDATE;
         	int supplierid = Integer.parseInt(request.getParameter("supplierid"));
         	SupplierBean supplier = SupplierDAO.getSupplierBySupplierid(supplierid);
         	request.setAttribute("supplier", supplier);
 		}
    	 else if (action.equalsIgnoreCase("deleteSupplier")){
  			forward = DELETE;
          	int supplierid = Integer.parseInt(request.getParameter("supplierid"));
          	SupplierBean supplier = SupplierDAO.getSupplierBySupplierid(supplierid);
          	request.setAttribute("supplier", supplier);
  		}
    
   
    		
    	  
    	 RequestDispatcher view = request.getRequestDispatcher(forward);
 	    view.forward(request, response);
 	    
    
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */ 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String action = request.getParameter("action");
		SupplierBean supplier = new SupplierBean();

		if(action.equalsIgnoreCase("Search")) {
            int supplierid = Integer.parseInt(request.getParameter("supplierid"));
            supplier.setSupplierid(supplierid);
            supplier = SupplierDAO.getSupplierBySupplierid(supplierid);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/supplier/viewSupplier.jsp");
        	request.setAttribute("supplier", supplier);
    		dispatcher.forward(request, response); 
		}
		else if(action.equalsIgnoreCase("Add") || (action.equalsIgnoreCase("Update")))  {
			
			
			String suppName = request.getParameter("suppName");
			String suppAddress = request.getParameter("suppAddress");
			String suppContact = request.getParameter("suppContact");
			String suppEmail = request. getParameter("suppEmail");
			
			if(action.equalsIgnoreCase("Update")) {
				int supplierid = Integer.parseInt(request.getParameter("supplierid"));
				supplier.setSupplierid(supplierid);
			}
			
			supplier.setSuppName(suppName);
			supplier.setSuppAddress(suppAddress);
			supplier.setSuppContact(suppContact);
			supplier.setSuppEmail(suppEmail);
			
			suppdao = new SupplierDAO();

			
			supplier = suppdao.getSupplier(supplier);
			if(!supplier.isValid()) {
				suppdao.add(supplier);
				response.setContentType("text/html");
			      PrintWriter pw = response.getWriter();
			      pw.println("<script>");
			      pw.println("alert('The account has been created');");
			      pw.println("window.location.href='/Inventory/SupplierController?action=listSupplier';");
			      pw.println("</script>");

			}
			else {
				try {
				suppdao.updateSupplier(supplier);
				response.setContentType("text/html");
			      PrintWriter pw = response.getWriter();
			      pw.println("<script>");
			      pw.println("alert('The account has been updated');");
			      pw.println("window.location.href='/Inventory/SupplierController?action=listSupplier';");
			      pw.println("</script>");
				}catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
				
			   }
			}
		
		  else if (action.equalsIgnoreCase("Delete")){
	            int supplierid = Integer.parseInt(request.getParameter("supplierid"));  
	            supplier.setSupplierid(supplierid);
	   			suppdao.deleteSupplier(supplierid);
	   			response.setContentType("text/html");
			      PrintWriter pw = response.getWriter();
			      pw.println("<script>");
			      pw.println("alert('The account has been deleted');");
			      pw.println("window.location.href='/Inventory/SupplierController?action=listSupplier';");
			      pw.println("</script>");
	       }

	}
}
