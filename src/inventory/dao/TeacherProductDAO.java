package inventory.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import inventory.connection.ConnectionManager;
import inventory.model.OrderProduct;
import inventory.model.Orders;
import inventory.model.TeacherBean;
import inventory.model.TeacherProduct;

public class TeacherProductDAO {

	static Connection currentCon = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static Statement stmt = null;
	static String status,email;
	static int teacherid, prodid, quantity;
	
	public static void add(TeacherProduct teacherProduct) {
		 
		teacherid = teacherProduct.getTeacherid();
        prodid = teacherProduct.getProdid();
        status = teacherProduct.getStatus();
        quantity = teacherProduct.getQuantity();
       
    	try {
    		currentCon = ConnectionManager.getConnection();
    		ps=currentCon.prepareStatement("insert into TEACHER_ORDER (teacherid, prodid, status, quantity, order_date)values(?,?,?,?,sysdate)");
    		ps.setInt(1,teacherid);
    		ps.setInt(2,prodid);
    		ps.setString(3,status);
    		ps.setInt(4,quantity);
    		ps.executeUpdate();
    	
    		System.out.println("Your order status is " + status);
    		System.out.println("Your order product quantity is " + quantity);
    		
    		
            
    	}

    	catch (Exception ex) {
    		System.out.println("failed: An Exception has occurred! " + ex);
    	}

    	finally {
    		if (ps != null) {
    			try {
    				ps.close();
    			} catch (Exception e) {
    			}
    			ps = null;
    		}
    		
    		if (currentCon != null) {
    			try {
    				currentCon.close();
    			} catch (Exception e) {
    			}
    			currentCon = null;
    		}
    	}
		
	}
	public static List<TeacherProduct> getAllTeacherOrder(int teacherid) {
 		  List<TeacherProduct> teacherProducts = new ArrayList<TeacherProduct>();
 		  
 		  try {
 		  	currentCon = ConnectionManager.getConnection();
 		  	stmt = currentCon.createStatement();
 		  
 		  	  String q = "select teacherid, prodid, status, quantity, to_char(order_date,'dd-MON-yyyy'), orderid from teacher_order where teacherid ='" + teacherid + "'";
 		  	  System.out.println(q);
 		      ResultSet rs = stmt.executeQuery(q);
 		      
 		      while (rs.next()) {
 		    	 TeacherProduct teacherProduct = new TeacherProduct();
 		          
 		          
 		    	teacherProduct.setTeacherid(rs.getInt(1));
 		    	teacherProduct.setProdid(rs.getInt(2));
 		    	teacherProduct.setOrderid(rs.getInt(6));
 		    	teacherProduct.setStatus(rs.getString(3));
 		    	teacherProduct.setOrderDate(rs.getString(5));
 		    	teacherProduct.setQuantity(rs.getInt(4));
 		        teacherProducts.add(teacherProduct);
 		      }
 		  } catch (SQLException e) {
 		      e.printStackTrace();
 		  }

 		  return teacherProducts;
 	}
	public static TeacherProduct getOrderById(String orderid) {
		TeacherProduct teacherProduct = new TeacherProduct();
	    try {
	    	currentCon = ConnectionManager.getConnection();
	        ps=currentCon.prepareStatement("select teacherid, prodid, status, quantity, to_char(order_date,'dd-MON-yyyy'), orderid from teacher_order where orderid=?");
	        
	        ps.setString(1, orderid);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	        	teacherProduct.setTeacherid(rs.getInt(1));
 		    	teacherProduct.setProdid(rs.getInt(2));
 		    	teacherProduct.setOrderid(rs.getInt(6));
 		    	teacherProduct.setStatus(rs.getString(3));
 		    	teacherProduct.setOrderDate(rs.getString(5));
 		    	teacherProduct.setQuantity(rs.getInt(4));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    
	    return teacherProduct;
	}
	 public static void updateOrder(TeacherProduct bean) throws NoSuchAlgorithmException {

	    	int orderid = bean.getOrderid();
	    	quantity = bean.getQuantity();
	   
	    	String searchQuery = "UPDATE teacher_order SET quantity ='"+ quantity +"' WHERE orderid= '" + orderid + "'";
	    	
	    	try {

	            currentCon = ConnectionManager.getConnection();
	            stmt = currentCon.createStatement();
	            stmt.executeUpdate(searchQuery);
	            System.out.println(searchQuery);
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	 public static void deleteOrder(int orderid) {
	        try {
	        	currentCon = ConnectionManager.getConnection();
	        	ps=currentCon.prepareStatement("delete from teacher_order where orderid=?");
	            ps.setInt(1, orderid);
	            ps.executeUpdate();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	 public static List<TeacherProduct> getAllTeacherOrders() {
		  List<TeacherProduct> teacherProducts = new ArrayList<TeacherProduct>();
		  
		  try {
		  	currentCon = ConnectionManager.getConnection();
		  	stmt = currentCon.createStatement();
		  
		  	  String q = "select teacherid, prodid, status, quantity, to_char(order_date,'dd-MON-yyyy'), orderid from teacher_order order by 5 ";
		  	  System.out.println(q);
		      ResultSet rs = stmt.executeQuery(q);
		      
		      while (rs.next()) {
		    	 TeacherProduct teacherProduct = new TeacherProduct();
		          
		          
		    	teacherProduct.setTeacherid(rs.getInt(1));
		    	teacherProduct.setProdid(rs.getInt(2));
		    	teacherProduct.setOrderid(rs.getInt(6));
		    	teacherProduct.setStatus(rs.getString(3));
		    	teacherProduct.setOrderDate(rs.getString(5));
		    	teacherProduct.setQuantity(rs.getInt(4));
		        teacherProducts.add(teacherProduct);
		      }
		  } catch (SQLException e) {
		      e.printStackTrace();
		  }

		  return teacherProducts;
	}
	 public static void updateStatus(TeacherProduct bean) throws NoSuchAlgorithmException {

	    	int orderid = bean.getOrderid();
	    	status = bean.getStatus();
	   
	    	String searchQuery = "UPDATE teacher_order SET status ='"+ status +"' WHERE orderid= '" + orderid + "'";
	    	
	    	try {

	            currentCon = ConnectionManager.getConnection();
	            stmt = currentCon.createStatement();
	            stmt.executeUpdate(searchQuery);
	            System.out.println(searchQuery);
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	
}
