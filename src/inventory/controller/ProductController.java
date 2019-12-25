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

import inventory.dao.FoodDAO;
import inventory.dao.ProductDAO;
import inventory.dao.StationeryDAO;
import inventory.dao.SupplierDAO;
import inventory.model.ProductBean;
import inventory.model.Food;
import inventory.model.Stationery;
import inventory.model.SupplierBean;





/**
 * Servlet implementation class ProductController
 */
@WebServlet("/ProductController")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String VIEW = "/product/viewProduct.jsp";
	private static String DELETE = "/product/deleteProduct.jsp";
	private static String SEARCH = "/product/createProduct.jsp";
    private static String UPDATE = "/product/updateProduct.jsp";
	
	String forward="";
 	private ProductDAO daoProduct;
	private FoodDAO daoFood;
	private StationeryDAO daoStationery;
		
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductController() {
            super();
            daoProduct = new ProductDAO();
            daoFood = new FoodDAO();
            daoStationery = new StationeryDAO();
            // TODO Auto-generated constructor stub
        }

    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  		// TODO Auto-generated method stub
  String action = request.getParameter("action");
          
  		if(action.equalsIgnoreCase("viewFood")) {
			forward = VIEW;
	        request.setAttribute("products", daoFood.getAllFood());  
	        request.setAttribute("type", "food");
          }
  		else if(action.equalsIgnoreCase("viewStationery")) {
			forward = VIEW;
			 request.setAttribute("products", daoStationery.getAllStationery());  
		     request.setAttribute("type", "stationery");        
          }
  		else if (action.equalsIgnoreCase("searchSupplier")){
     		forward = SEARCH;
            List<SupplierBean> supplier = SupplierDAO.getAllSupplier();
         	request.setAttribute("suppliers", supplier);
  		}
  		else if (action.equalsIgnoreCase("updateProduct")){
			forward = UPDATE;
            int prodid = Integer.parseInt(request.getParameter("prodid"));
            String prodType = request.getParameter("prodType");
            ProductBean product = new ProductBean();
            Food food = new Food();
            Stationery stationery = new Stationery();
            FoodDAO foodDao = new FoodDAO();
            StationeryDAO stationeryDao = new StationeryDAO();
     
            if(prodType.equalsIgnoreCase("Food")) {
            	product = daoProduct.getProductByProdid(prodid);
            	food = foodDao.getProductByProdid(prodid);
            	request.setAttribute("food", food);
            }
            else {
            	product = daoProduct.getProductByProdid(prodid);
            	stationery = stationeryDao.getProductByProdid(prodid);
            	request.setAttribute("stationery", stationery);
            }
        	request.setAttribute("product", product);
        	
		}
    	   else if (action.equalsIgnoreCase("deleteProduct")){
           	forward = DELETE;
            int prodid = Integer.parseInt(request.getParameter("prodid"));
            ProductBean product = new ProductBean();
            product = daoProduct.getProductByProdid(prodid);
        	request.setAttribute("product", product);
       }
          RequestDispatcher view = request.getRequestDispatcher(forward);
          view.forward(request, response);
  	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	//int prodid = Integer.parseInt(request.getParameter("prodid"));
    	ProductBean product = new ProductBean();
		Food food = new Food();
		Stationery stationery = new Stationery();
    	String action = request.getParameter("action");
    	if(action.equalsIgnoreCase("Submit")) {
		String prodName = request.getParameter("prodName");
		double prodPrice = Double.parseDouble(request.getParameter("prodPrice"));
		double sellPrice = Double.parseDouble(request.getParameter("sellPrice"));
		int prodQuantity = Integer.parseInt(request.getParameter("prodQuantity"));
		int supplierid = Integer.parseInt(request.getParameter("supplierid"));
		String prodType = request.getParameter("prodType");
		String prod = request.getParameter("product");
		
		product.setProdName(prodName);
		product.setProdPrice(prodPrice);
		product.setProdQuantity(prodQuantity);
		product.setSupplierid(supplierid);
		product.setProdType(prodType);
		product.setSellPrice(sellPrice);
		
		
		//product = ProductDAO.getProduct(product);
		try {
				daoProduct.add(product);	
			
				if (prodType.equalsIgnoreCase("Food")) {
					food = new Food(product.getProdid(), prodName, prodPrice,sellPrice,prodQuantity,supplierid, prodType, false, prod);
					daoFood.add(food);
					response.setContentType("text/html");
				      PrintWriter pw = response.getWriter();
				      pw.println("<script>");
				      pw.println("alert('The product has been created');");
				      pw.println("window.location.href='/Inventory/ProductController?action=viewFood';");
				      pw.println("</script>");
				} else if (prodType.equalsIgnoreCase("Stationery")) {
					stationery = new Stationery(product.getProdid(), prodName, prodPrice,sellPrice, prodQuantity,supplierid,prodType,false, prod);
					daoStationery.add(stationery);
					response.setContentType("text/html");
				      PrintWriter pw = response.getWriter();
				      pw.println("<script>");
				      pw.println("alert('The product has been created');");
				      pw.println("window.location.href='/Inventory/ProductController?action=viewStationery';");
				      pw.println("</script>");
				}
			
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
		}
    }
    	else if(action.equalsIgnoreCase("Update")) {
    		

    	int prodid = Integer.parseInt(request.getParameter("prodid"));
		String prodName = request.getParameter("prodName");
		double prodPrice = Double.parseDouble(request.getParameter("prodPrice"));
		double sellPrice = Double.parseDouble(request.getParameter("sellPrice"));
		int prodQuantity = Integer.parseInt(request.getParameter("prodQuantity"));
		int supplierid = Integer.parseInt(request.getParameter("supplierid"));
		String prodType = request.getParameter("prodType");
		if(prodType.equalsIgnoreCase("Food")) {
			String foodType = request.getParameter("foodType");
			food.setProdid(prodid);
			food.setFoodType(foodType);
			product.setProdid(prodid);
			product.setProdName(prodName);
			product.setProdPrice(prodPrice);
			product.setSellPrice(sellPrice);
			product.setProdQuantity(prodQuantity);
			product.setSupplierid(supplierid);
			product.setProdType(prodType);
			try {
				daoProduct.updateProduct(product);
				daoFood.updateFood(food);
				response.setContentType("text/html");
			      PrintWriter pw = response.getWriter();
			      pw.println("<script>");
			      pw.println("alert('The product has been updated');");
			      pw.println("window.location.href='/Inventory/ProductController?action=viewFood';");
			      pw.println("</script>");
				
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else {
			String stationeryBrand = request.getParameter("stationeryBrand");
			stationery.setProdid(prodid);
			stationery.setStationeryBrand(stationeryBrand);
			product.setProdid(prodid);
			product.setProdName(prodName);
			product.setProdPrice(prodPrice);
			product.setSellPrice(sellPrice);
			product.setProdQuantity(prodQuantity);
			product.setSupplierid(supplierid);
			product.setProdType(prodType);
			try {
				daoProduct.updateProduct(product);
				daoStationery.updateStationery(stationery);
				response.setContentType("text/html");
			      PrintWriter pw = response.getWriter();
			      pw.println("<script>");
			      pw.println("alert('The product has been updated');");
			      pw.println("window.location.href='/Inventory/ProductController?action=viewStationery';");
			      pw.println("</script>");
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
    		
		
    	}
    	else if(action.equalsIgnoreCase("Delete")) {
    		
        	int prodid = Integer.parseInt(request.getParameter("prodid"));
    		
    		daoProduct = new ProductDAO(); 
    		
    		daoProduct.deleteProduct(prodid);
    		response.setContentType("text/html");
		      PrintWriter pw = response.getWriter();
		      pw.println("<script>");
		      pw.println("alert('The product has been deleted');");
		      pw.println("window.location.href='/Inventory/ProductController?action=viewStationery';");
		      pw.println("</script>");
        	}
    	
		//daoProduct.add(product);
		//response.sendRedirect("/Inventory/index.jsp"); // logged-in page
		
    }
	
	
}
