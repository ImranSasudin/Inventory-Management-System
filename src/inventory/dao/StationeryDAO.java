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
import inventory.model.Stationery;
import inventory.model.Food;
import inventory.model.ProductBean;
//import school.model.TeacherSubject;

public class StationeryDAO {
	
	static Connection currentCon = null;
	static ResultSet rs = null; 
	static PreparedStatement ps=null;
	static Statement stmt=null;
    static String stationeryBrand;
    static int prodid;
   // static TeacherSubjectDAO daoTeacherSubject;

	public void add(Stationery stationery) throws NoSuchAlgorithmException{
		
        prodid = stationery.getProdid();
        stationeryBrand = stationery.getStationeryBrand();        
            
    	try {
    		currentCon = ConnectionManager.getConnection();
    		ps=currentCon.prepareStatement("insert into Stationery (prodid,stationeryBrand)values(?,?)");
    		ps.setInt(1,prodid);
    		ps.setString(2,stationeryBrand);
    		ps.executeUpdate();
    	
    		System.out.println("Your product ID is " + prodid);
    		System.out.println("Your stationery brand is " + stationeryBrand);
            
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
	
	public List<Stationery> getAllStationery() {
		
		  List<Stationery> stationeries = new ArrayList<Stationery>();
		  
		  try {
		  	currentCon = ConnectionManager.getConnection();
		    ps=currentCon.prepareStatement("select p.prodid, p.prodname , p.prodprice , p.sellprice, p.prodquantity , p.prodtype , p.supplierid , s.stationerybrand \r\n" + 
		    		"from product p \r\n" + 
		    		"join stationery s\r\n" + 
		    		"on (p.prodid = s.prodid)");
			  
	        ResultSet rs = ps.executeQuery();
		      
		      while (rs.next()) {
		    	  Stationery stationery = new Stationery();
		    	  
		    	  stationery.setProdName(rs.getString(2));
		    	  stationery.setProdid(rs.getInt(1));
		    	  stationery.setProdPrice(rs.getDouble(3));
		    	  stationery.setSellPrice(rs.getDouble(4));
		    	  stationery.setProdQuantity(rs.getInt(5));
		    	  stationery.setProdType(rs.getString(6));
		    	  stationery.setSupplierid(rs.getInt(7));
		    	  stationery.setStationeryBrand(rs.getString(8));
		    	  stationeries.add(stationery);
		      }
		  } catch (SQLException e) {
		      e.printStackTrace();
		  }

		  return stationeries;
		}
	public Stationery getProductByProdid(int prodid) {
		Stationery stationery = new Stationery();
	        try {
	        	currentCon = ConnectionManager.getConnection();
	            ps=currentCon.prepareStatement("select * from stationery where prodid=?");
	            
	            ps.setInt(1, prodid);
	          

	            ResultSet rs = ps.executeQuery();

	            if (rs.next()) {
	            	stationery.setProdid(rs.getInt("prodid"));
	            	stationery.setStationeryBrand(rs.getString("stationerybrand"));
	            	
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return stationery;
	    }
	public void updateStationery(Stationery stationery) throws NoSuchAlgorithmException {

		 prodid = stationery.getProdid();
		 stationeryBrand = stationery.getStationeryBrand();
		
	    	
	       
	        String searchQuery = "UPDATE stationery SET stationerybrand = '"+ stationeryBrand + "' WHERE prodid= '" + prodid + "'";
	    	
	    	try {

	            currentCon = ConnectionManager.getConnection();
	            stmt = currentCon.createStatement();
	            stmt.executeUpdate(searchQuery);
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	 }

	/* public Permanent getPermanentbyId(Teacher teacher) {
		Permanent permanent = new Permanent();		
		
		id = teacher.getId();
			
		String searchQuery = "select * from permanent where teacherid='" + id + "'";

        try {
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.createStatement();
            rs = stmt.executeQuery(searchQuery);
            boolean more = rs.next();

            // if user exists set the isValid variable to true
            if (more) {
            	permanent.setId(teacher.getId());
	        	permanent.setName(teacher.getName());
	        	permanent.setType(teacher.getType());
	        	permanent.setGrade(rs.getString("grade"));
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
	    permanent.setTeacherSubjects(teacherSubjects);

	    return permanent;
	}*/

}
