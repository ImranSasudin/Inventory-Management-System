package inventory.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import inventory.connection.ConnectionManager;
import inventory.model.Orders;
import inventory.model.ProductBean;
import inventory.model.OrderProduct;

public class OrderDAO {
	
	static Connection currentCon = null;
	static ResultSet rs = null; 
	static PreparedStatement ps=null;
	static Statement stmt=null;
    static String orderDate;
    static int orderid,teacherid;
    
    
	public static Orders getOrders(Orders orders) {
		
		orderid = orders.getOrderid();

        String searchQuery = "select * from order where orderid='" + orderid + "'";

        try {
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.createStatement();
            rs = stmt.executeQuery(searchQuery);
            boolean more = rs.next();

            // if user exists set the isValid variable to true
            if (more) {
            	int orderid = rs.getInt("id");
           
                orders.setOrderid(orderid);
                orders.setValid(true);
           	}
           
            else if (!more) {
            	System.out.println("Sorry");
            	orders.setValid(false);
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

        return orders;
		
	}


	public static void add(Orders orders) {
		
       orderid = orders.getOrderid();
        orderDate = orders.getOrderDate();
        teacherid = orders.getTeacherid();
    
    	try {
    		currentCon = ConnectionManager.getConnection();
    		ps=currentCon.prepareStatement("insert into orders (orderid, orderDate, teacherid)values(ORDER_SEQ.nextval,sysdate,?)");    
    		ps.setInt(1, teacherid);
    		ps.executeUpdate();
    	
    		
    		System.out.println("Your Order ID is " + orderid);
    		System.out.println("Your Order date is " + orderDate);
            
    		String searchQuery = "select ORDER_SEQ.currval from dual";

			
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			boolean more = rs.next();
			
			if(more) {
				int orderid = rs.getInt(1);
				System.out.println(orderid);
				orders.setOrderid(orderid);

			}
			else if(!more) {
				System.out.println("SORRY");
			}
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


	public Orders getOrderById(String orderid) {
		Orders orders = new Orders();
	    try {
	    	currentCon = ConnectionManager.getConnection();
	        ps=currentCon.prepareStatement("select * from order where orderid=?");
	        
	        ps.setString(1, orderid);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	        	orders.setOrderid(rs.getInt("orderid"));
	        	orders.setOrderDate(rs.getString("orderDate"));  
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    
	    return orders;
	}
	public List<Orders> getAllOrder() {
		
		  List<Orders> orders = new ArrayList<Orders>();
		  
		  try {
		  	currentCon = ConnectionManager.getConnection();
		    ps=currentCon.prepareStatement("select * from orders");
			  
	        ResultSet rs = ps.executeQuery();
		      
		      while (rs.next()) {
		    	  Orders order = new Orders();
		    	  
		    	  order.setOrderid(rs.getInt("orderid"));
		    	  order.setOrderDate(rs.getString("orderdate"));
		    	  
		          orders.add(order);
		      }
		  } catch (SQLException e) {
		      e.printStackTrace();
		  }

		  return orders;
		}
	 public static void deleteOrder(int orderid) {
	        try {
	        	currentCon = ConnectionManager.getConnection();
	        	ps=currentCon.prepareStatement("delete from orders where orderid=?");
	            ps.setInt(1, orderid);
	            ps.executeUpdate();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	
	

}
