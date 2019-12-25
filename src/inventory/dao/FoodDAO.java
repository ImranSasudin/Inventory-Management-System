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
import inventory.model.ProductBean;
//import inventory.model.TeacherSubject;
import inventory.model.Food;
import inventory.model.OrderProduct;

public class FoodDAO {
	
	static Connection currentCon = null;
	static ResultSet rs = null; 
	static PreparedStatement ps=null;
	static Statement stmt=null;
    static String foodType;
    static int prodid;
    //static TeacherSubjectDAO daoTeacherSubject;

	public void add(Food bean) throws NoSuchAlgorithmException {
		
		
        prodid = bean.getProdid();
        foodType = bean.getFoodType();        
       
    	try {
    		currentCon = ConnectionManager.getConnection();
    		ps=currentCon.prepareStatement("insert into food (prodid,foodType)values(?,?)");
    		ps.setInt(1,prodid);
    		ps.setString(2,foodType);
    		ps.executeUpdate();
    	
    		System.out.println("Your Product ID is " + prodid);
    		System.out.println("Your Food type is " + foodType);
            
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

	public List<Food> getAllFood() {
		
		  List<Food> foods = new ArrayList<Food>();
		  
		  try {
		  	currentCon = ConnectionManager.getConnection();
		    ps=currentCon.prepareStatement("select p.prodid, p.prodname , p.prodprice , p.sellprice, p.prodquantity , p.prodtype , p.supplierid , f.foodtype \r\n" + 
		    		"from product p \r\n" + 
		    		"join food f\r\n" + 
		    		"on (p.prodid = f.prodid)");
			  
	        ResultSet rs = ps.executeQuery();
		      
		      while (rs.next()) {
		    	  Food food = new Food();
		    	  
		    	  food.setProdName(rs.getString(2));
		    	  food.setProdid(rs.getInt(1));
		    	  food.setProdPrice(rs.getDouble(3));
		    	  food.setSellPrice(rs.getDouble(4));
		    	  food.setProdQuantity(rs.getInt(5));
		    	  food.setProdType(rs.getString(6));
		    	  food.setSupplierid(rs.getInt(7));
		    	  food.setFoodType(rs.getString(8));
		    	  foods.add(food);
		      }
		  } catch (SQLException e) {
		      e.printStackTrace();
		  }

		  return foods;
		}
	
	public Food getProductByProdid(int prodid) {
		Food food = new Food();
	        try {
	        	currentCon = ConnectionManager.getConnection();
	            ps=currentCon.prepareStatement("select * from food where prodid=?");
	            
	            ps.setInt(1, prodid);
	          

	            ResultSet rs = ps.executeQuery();

	            if (rs.next()) {
	            	food.setProdid(rs.getInt("prodid"));
	            	food.setFoodType(rs.getString("foodtype"));
	            	
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return food;
	    }
	 public void updateFood(Food food) throws NoSuchAlgorithmException {

		 prodid = food.getProdid();
		 foodType = food.getFoodType();
		
	    	
	       
	        String searchQuery = "UPDATE food SET foodtype = '"+ foodType + "' WHERE prodid= '" + prodid + "'";
	    	System.out.println(searchQuery);
	    	try {

	            currentCon = ConnectionManager.getConnection();
	            stmt = currentCon.createStatement();
	            stmt.executeUpdate(searchQuery);
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	 }
/*	public Temp getTempById(Teacher teacher) {
		Temp temp = new Temp();
		
		id = teacher.getId();
		
		
		String searchQuery = "select * from temp where teacherid='" + id + "'";

        try {
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.createStatement();
            rs = stmt.executeQuery(searchQuery);
            boolean more = rs.next();

            // if user exists set the isValid variable to true
            if (more) {
            	temp.setId(teacher.getId());
		        temp.setName(teacher.getName());
		        temp.setType(teacher.getType());
		        temp.setDuration(rs.getInt("duration"));
           	}
           
            else if (!more) {
            	System.out.println("Sorry");
            }
           
        }catch (SQLException e) {
	        e.printStackTrace();
	    }
		
 
	    daoTeacherSubject = new TeacherSubjectDAO();
	    List<TeacherSubject> teacherSubjects = new ArrayList<TeacherSubject>();
	    teacherSubjects = daoTeacherSubject.getAllSubjectByTeacher(id);
	    temp.setTeacherSubjects(teacherSubjects);

	    return temp;
	}*/

}
