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

import inventory.connection.ConnectionManager;
import inventory.model.ProductBean;
import inventory.model.SupplierBean;
import inventory.model.TeacherBean;




public class ProductDAO {
	static Connection currentCon = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static java.sql.Statement stmt = null;
	static String prodName, prodType;
	static double prodPrice;
	static int prodQuantity;
	static int prodid;
	static double sellPrice;
	int supplierid;
	
	//add product
	public void add (ProductBean bean) {
		
		prodName = bean.getProdName();
		prodPrice = bean.getProdPrice();
		prodQuantity = bean.getProdQuantity();
		supplierid = bean.getSupplierid();
		prodType = bean.getProdType();
		sellPrice = bean.getSellPrice();
		
		
		try {
			currentCon = ConnectionManager.getConnection();
			ps=currentCon.prepareStatement("insert into product(prodid, prodName, prodPrice, sellPrice, prodQuantity, supplierid,prodType)values(PROD_SEQ.nextval,?,?,?,?,?,?)");
			ps.setString(1,prodName);
			ps.setDouble(2,prodPrice);
			ps.setDouble(3,sellPrice);
			ps.setInt(4,prodQuantity);
			ps.setInt(5,supplierid);
			ps.setString(6,prodType);
			ps.executeUpdate();
			
			System.out.println("Your product name is " + prodName);
			System.out.println("Your product quantity is " + prodQuantity);
			System.out.println("Your supplier ID is " + supplierid);
			
			String searchQuery = "select PROD_SEQ.currval from dual";

			
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			boolean more = rs.next();
			
			if(more) {
				int prodid = rs.getInt(1);
				bean.setProdid(prodid);

			}
			else if(!more) {
				System.out.println("SORRY");
			}
			
		}
		
		catch (Exception ex) {
			System.out.println("failed: An Exception has occured!" + ex);
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
	//list all product
	public static List<ProductBean> getAllProduct() {
		
	  List<ProductBean> products = new ArrayList<ProductBean>();
	  
	  try {
	  	currentCon = ConnectionManager.getConnection();
	    ps=currentCon.prepareStatement("select * from product");
		  
        ResultSet rs = ps.executeQuery();
	      
	      while (rs.next()) {
	    	  ProductBean product = new ProductBean();
	    	  
	    	  product.setProdid(rs.getInt("prodid"));
	    	  product.setProdName(rs.getString("prodName"));
	    	  product.setProdPrice(rs.getDouble("prodPrice"));
	    	  product.setSellPrice(rs.getDouble("sellPrice"));
	    	  product.setProdQuantity(rs.getInt("prodQuantity"));
	    	  product.setSupplierid(rs.getInt("supplierid"));
	    	  product.setProdType(rs.getString("prodType"));
	          products.add(product);
	      }
	  } catch (SQLException e) {
	      e.printStackTrace();
	  }

	  return products;
	}
	 public static ProductBean getProduct(ProductBean product)  {
    	
        prodid = product.getProdid();
  

        String searchQuery = "select * from product where prodid='" + prodid + "'";

        try {
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.createStatement();
            rs = stmt.executeQuery(searchQuery);
            boolean more = rs.next();

            // if user exists set the isValid variable to true
            if (more) {
            	int prodid = rs.getInt("prodid");
           
                product.setProdid(prodid);
                product.setValid(true);
           	}
           
            else if (!more) {
            	System.out.println("Sorry");
            	product.setValid(false);
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

        return product;
    }
	 
	
	  public static void updateQuantity(ProductBean bean) throws NoSuchAlgorithmException {

	        prodid = bean.getProdid();
	        prodQuantity = bean.getProdQuantity();
	    	
	       
	        String searchQuery = "UPDATE product SET prodquantity = prodquantity + '" + prodQuantity + "' WHERE prodid= '" + prodid + "'";
	    	
	    	try {

	            currentCon = ConnectionManager.getConnection();
	            stmt = currentCon.createStatement();
	            stmt.executeUpdate(searchQuery);
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	  public static void updateQuantity2(ProductBean bean) throws NoSuchAlgorithmException {

	        prodid = bean.getProdid();
	        prodQuantity = bean.getProdQuantity();
	    	
	       
	        String searchQuery = "UPDATE product SET prodquantity = prodquantity - '" + prodQuantity + "' WHERE prodid= '" + prodid + "'";
	    	
	    	try {

	            currentCon = ConnectionManager.getConnection();
	            stmt = currentCon.createStatement();
	            stmt.executeUpdate(searchQuery);
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	  
	  //get product by id
	  public ProductBean getProductByProdid(int prodid) {
		  ProductBean product = new ProductBean();
	        try {
	        	currentCon = ConnectionManager.getConnection();
	            ps=currentCon.prepareStatement("select * from product where prodid=?");
	            
	            ps.setInt(1, prodid);
	          

	            ResultSet rs = ps.executeQuery();

	            if (rs.next()) {
	            	product.setProdid(rs.getInt("prodid"));
	            	product.setProdName(rs.getString("prodName"));
	            	product.setProdPrice(rs.getDouble("prodPrice"));
	            	product.setSellPrice(rs.getDouble("sellPrice"));
	            	product.setProdQuantity(rs.getInt("prodQuantity"));
	            	product.setSupplierid(rs.getInt("supplierid"));
	            	product.setProdType(rs.getString("prodType"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return product;
	    }
	  //delete product
		 public void deleteProduct(int prodid) {
		        try {
		        	currentCon = ConnectionManager.getConnection();
		        	ps=currentCon.prepareStatement("delete from product where prodid=?");
		            ps.setInt(1, prodid);
		            ps.executeUpdate();

		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		 //update product
		 public void updateProduct(ProductBean product) throws NoSuchAlgorithmException {

			 prodid = product.getProdid();
			 prodName = product.getProdName();
			 prodPrice = product.getProdPrice();
			 prodQuantity =product.getProdQuantity();
			 supplierid = product.getSupplierid();
			 prodType = product.getProdType();
			 sellPrice = product.getSellPrice();
		    	
		       
		        String searchQuery = "UPDATE product SET prodName = '"+ prodName + "', prodPrice='" + prodPrice + "', sellPrice = '" + sellPrice + "', prodQuantity='" + prodQuantity + "' WHERE prodid= '" + prodid + "'";
		    	
		    	try {

		            currentCon = ConnectionManager.getConnection();
		            stmt = currentCon.createStatement();
		            stmt.executeUpdate(searchQuery);
		            
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		 }
}
	


