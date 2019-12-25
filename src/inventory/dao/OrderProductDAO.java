package inventory.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import inventory.connection.ConnectionManager;
import inventory.model.ProductBean;
import inventory.model.SupplierBean;
import inventory.model.TeacherBean;
import inventory.model.Orders;
import inventory.model.OrderProduct;

public class OrderProductDAO {
	
	static Connection currentCon = null;
	static ResultSet rs = null; 
	static PreparedStatement ps=null;
	static Statement stmt=null;
    static int  orderid;
    static int prodid;
	static int orderProdQuantity, teacherid;
    static String status;
    static ProductDAO dao;  

	public static OrderProduct getOrderProduct(OrderProduct orderProduct) {
		
		prodid = orderProduct.getProdid();
		orderid = orderProduct.getOrderid();
		
		
	        String searchQuery = "select * from orders_Product where orderid='" + orderid + "'" + " and prodid='" + prodid + "'";
	        System.out.println(searchQuery);

	        try {
	            currentCon = ConnectionManager.getConnection();
	            stmt = currentCon.createStatement();
	            rs = stmt.executeQuery(searchQuery);
	            boolean more = rs.next();

	            //if user exists set the isValid variable to true
	            if (more) {
	            	int orderid = rs.getInt("orderid");
	           
	            	//orderProduct.setOrderid(orderid);;
	            	orderProduct.setValid(true);
	           	}
	           
	            else if (!more) {
	            	System.out.println("Sorry");
	            	orderProduct.setValid(false);
	            }
	           
	        }

	        catch (Exception ex) {
	            System.out.println("Log In failed: An Exception has occurred! " + ex);
	        }

	        finally {
	            if (rs != null) {
	                try {
	                    rs.close();
	                } catch (Exception e) {
	                }
	                rs = null;
	            }

	            if (stmt != null) {
	                try {
	                    stmt.close();
	                } catch (Exception e) {
	                }
	                stmt = null;
	            }

	            if (currentCon != null) {
	                try {
	                    currentCon.close();
	                } catch (Exception e) {
	                }

	                currentCon = null;
	            }
	        }

	        return orderProduct;
	}
	//add for bridge
	public void add(OrderProduct orderProduct) {
 
		orderid = orderProduct.getOrderid();
        prodid = orderProduct.getProdid();
        status = orderProduct.getStatus();
        orderProdQuantity = orderProduct.getOrderProdQuantity();
        teacherid = orderProduct.getTeacherid();
       
    	try {
    		currentCon = ConnectionManager.getConnection();
    		ps=currentCon.prepareStatement("insert into orders_Product (orderid, prodid, status, orderProdQuantity)values(?,?,?,?)");
    		ps.setInt(1,orderid);
    		ps.setInt(2,prodid);
    		ps.setString(3,status);
    		ps.setInt(4,orderProdQuantity);
    		ps.executeUpdate();
    	
    		System.out.println("Your Order ID is " + orderid);
    		System.out.println("Your order status is " + status);
    		System.out.println("Your order product quantity is " + orderProdQuantity);
    		
    		
            
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
	public static OrderProduct getOrderProductByOrderid(int orderid) {
		OrderProduct orderProduct = new OrderProduct();
	        try {
	        	currentCon = ConnectionManager.getConnection();
	            ps=currentCon.prepareStatement("select * from orders_Product where orderid=?");
	            
	            ps.setInt(1, orderid);
	          

	            ResultSet rs = ps.executeQuery();

	            if (rs.next()) {
	            	orderProduct.setOrderid(rs.getInt("orderid"));
	            	orderProduct.setProdid(rs.getInt("prodid"));
	            	orderProduct.setStatus(rs.getString("status"));
	            	orderProduct.setOrderProdQuantity(rs.getInt("orderProdQuantity"));
	            	
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return orderProduct;
	    }
	 
	//delete orders
	 public void deleteOrderProduct(int orderid) {
	        try {
	        	currentCon = ConnectionManager.getConnection();
	        	ps=currentCon.prepareStatement("delete from orders_Product where orderid=?");
	            ps.setInt(1, orderid);
	            ps.executeUpdate();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	//list all order
		public static List<OrderProduct> getAllOrderProduct() {
			
		  List<OrderProduct> ordersProduct = new ArrayList<OrderProduct>();
		  
		  try {
		  	currentCon = ConnectionManager.getConnection();
		    ps=currentCon.prepareStatement("select op.orderid, op.prodid, op.status, op.orderprodquantity, to_char(o.orderdate,'dd-MON-yyyy'), o.teacherid, op.teacherid from orders_Product op join orders o on (o.orderid = op.orderid)");
			  
	        ResultSet rs = ps.executeQuery();
		      
		      while (rs.next()) {
		    	  OrderProduct orderProduct = new OrderProduct();
		    	  
		    	  orderProduct.setOrderid(rs.getInt(1));
		    	  orderProduct.setProdid(rs.getInt(2));
		    	  orderProduct.setStatus(rs.getString(3));
		    	  orderProduct.setOrderProdQuantity(rs.getInt(4));
		    	  orderProduct.setOrderDate(rs.getString(5));
		    	  orderProduct.setTeacherid(rs.getInt(6)); //order by
		    	  orderProduct.setTeacherid2(rs.getInt(7)); //comfirm by
		    	  ordersProduct.add(orderProduct);
		      }
		  } catch (SQLException e) {
		      e.printStackTrace();
		  }

		  return ordersProduct;
		}
	 public static void updateOrder(OrderProduct bean) throws NoSuchAlgorithmException {

		    	orderid = bean.getOrderid();
		    	prodid = bean.getProdid();
		    	status = bean.getStatus();
		    	orderProdQuantity = bean.getOrderProdQuantity();
		    	teacherid = bean.getTeacherid();
		    	
		        String searchQuery = "UPDATE orders_product SET status ='"+ status +"' , teacherid = '" + teacherid + "' where orderid='" + orderid + "'";
		        String searchQuery1 = "UPDATE product SET prodquantity=prodquantity + '"+ orderProdQuantity + "' where prodid = '"+ prodid + "'";
		    	
		    	try {

		            currentCon = ConnectionManager.getConnection();
		            stmt = currentCon.createStatement();
		            stmt.executeUpdate(searchQuery);
		            stmt.executeUpdate(searchQuery1);
		            System.out.println(searchQuery);
		            System.out.println(searchQuery1);
		            
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
}
	