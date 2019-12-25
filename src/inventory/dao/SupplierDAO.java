package inventory.dao;

import java.beans.Statement;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import inventory.connection.ConnectionManager;
import inventory.model.SupplierBean;
import inventory.model.TeacherBean;


public class SupplierDAO {
	static Connection currentCon = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static java.sql.Statement stmt = null;
	String suppName, suppAddress, suppEmail ,suppContact;
	static int supplierid;

	
	public void add (SupplierBean bean) {
		
		suppName = bean.getSuppName();
		suppAddress = bean.getSuppAddress();
		suppContact = bean.getSuppContact();
		suppEmail = bean.getSuppEmail();
		
		try {
			currentCon = ConnectionManager.getConnection();
			ps=currentCon.prepareStatement("insert into supplier(suppName, suppAddress, suppContact, suppEmail)values(?,?,?,?)");
			ps.setString(1,suppName);
			ps.setString(2,suppAddress);
			ps.setString(3,suppContact);
			ps.setString(4,suppEmail);
			ps.executeUpdate();
			
			System.out.println("Your supplier name is " + suppName);
		
			
		}
		
		catch (Exception ex) {
			System.out.println("failed: An Exception has occured!" + ex);
		}
		
		finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (Exception e) {
			
					ps = null;
				}
			
			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
			
					currentCon = null;
				}
			}
			}
		}
	}
	//get all supplier
	public static List<SupplierBean> getAllSupplier() {
		  List<SupplierBean> suppliers = new ArrayList<SupplierBean>();
		  
		  try {
			  currentCon = ConnectionManager.getConnection();
	            ps=currentCon.prepareStatement("select * from supplier");
		  
	            ResultSet rs = ps.executeQuery();

		      
		      while (rs.next()) {
		          SupplierBean supplier = new SupplierBean();
		          
		          
		          supplier.setSupplierid(rs.getInt("supplierid"));
		          supplier.setSuppName(rs.getString("suppName"));
		          supplier.setSuppAddress(rs.getString("suppAddress"));
		          supplier.setSuppContact(rs.getString("suppContact"));
		          supplier.setSuppEmail(rs.getString("suppEmail"));
		          suppliers.add(supplier);
		      }
		  } catch (SQLException e) {
		      e.printStackTrace();
		  }

		  return suppliers;
	}
	 public static SupplierBean getSupplierBySupplierid(int supplierid) {
	       SupplierBean supplier = new SupplierBean();
	        try {
	        	currentCon = ConnectionManager.getConnection();
	            ps=currentCon.prepareStatement("select * from supplier where supplierid=?");
	            
	            ps.setInt(1, supplierid);
	          

	            ResultSet rs = ps.executeQuery();

	            if (rs.next()) {
	                supplier.setSupplierid(rs.getInt("supplierid"));
	                supplier.setSuppName(rs.getString("suppName"));
	                supplier.setSuppAddress(rs.getString("suppAddress"));
	                supplier.setSuppContact(rs.getString("suppContact"));
	                supplier.setSuppEmail(rs.getString("suppEmail"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return supplier;
	    } 
	 public  SupplierBean getSupplier(SupplierBean bean)  {
     	
         supplierid = bean.getSupplierid();

         String searchQuery = "select * from supplier where supplierid='" + supplierid + "'";

         try {
             currentCon = ConnectionManager.getConnection();
             stmt = currentCon.createStatement();
             rs = stmt.executeQuery(searchQuery);
             boolean more = rs.next();

             // if user exists set the isValid variable to true
             if (more) {
             	int supplierid = rs.getInt("supplierid");
            
                 bean.setSupplierid(supplierid);
                 bean.setValid(true);
            	}
            
             else if (!more) {
             	System.out.println("Sorry");
             	bean.setValid(false);
             }
            
         }

         catch (Exception ex) {
             System.out.println(" An Exception has occurred! " + ex);
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

         return bean;
     }
	 //update supplier
	  public void updateSupplier(SupplierBean bean) throws NoSuchAlgorithmException {

		  supplierid = bean.getSupplierid();
		  suppName = bean.getSuppName();
		  suppAddress = bean.getSuppAddress();
		  suppContact = bean.getSuppContact();
		  suppEmail = bean.getSuppEmail();
	    	
	    
	       
	        String searchQuery = "UPDATE supplier SET suppName= '"+ suppName +"', suppAddress='" + suppAddress + "', suppContact='" + suppContact + "', suppEmail='" + suppEmail +"' WHERE supplierid= '" + supplierid + "'";
	    	System.out.println(searchQuery);
	    	try {

	            currentCon = ConnectionManager.getConnection();
	            stmt = currentCon.createStatement();
	            stmt.executeUpdate(searchQuery);
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	 //delete supplier
	 public void deleteSupplier(int supplierid) {
	        try {
	        	currentCon = ConnectionManager.getConnection();
	        	ps=currentCon.prepareStatement("delete from supplier where supplierid=?");
	            ps.setInt(1, supplierid);
	            ps.executeUpdate();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	
	
	
}
	


