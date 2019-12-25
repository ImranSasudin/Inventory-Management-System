package inventory.dao;

import java.sql.Statement;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import inventory.connection.ConnectionManager;
import inventory.model.TeacherBean;



public class TeacherDAO {
	static Connection currentCon = null;
	static ResultSet rs = null;
	static PreparedStatement ps = null;
	static Statement stmt = null;
	static String name, phone, email, password, address, role;
	static int teacherid, managerid;

	 //login
    public static TeacherBean login(TeacherBean bean) throws NoSuchAlgorithmException {
    	
    	email = bean.getEmail();
    	password = bean.getPassword();

        //convert the password to MD5
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
 
        byte byteData[] = md.digest();
 
        //convert the byte to hex format
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        
        String pass = sb.toString();
        String searchQuery = "select * from teacher where email='" + email + "' AND password='" + pass + "'";

        System.out.println("Your email is " + email);
        System.out.println("Your password is " + password);
        System.out.println("Query: " + searchQuery);

        try {
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.createStatement();
            rs = stmt.executeQuery(searchQuery);
            boolean more = rs.next();

            // if user exists set the isValid variable to true
            if (more) {
            	String email = rs.getString("email");
           
           		System.out.println("Welcome " + email);
                bean.setEmail(email);
                bean.setValid(true);
           	}
           
            // if user does not exist set the isValid variable to false
            else if (!more) {
            	System.out.println("Sorry, you are not a registered user! Please sign up first");
            	bean.setValid(false);
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

        return bean;
    }
	//create teacher
    public void add (TeacherBean bean) throws NoSuchAlgorithmException {
		
    	name = bean.getName();
    	phone = bean.getPhone();
    	address = bean.getAddress();
    	email = bean.getEmail();
    	password = bean.getPassword();
		managerid = bean.getManagerid();
		role = bean.getRole();
		
		MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
 
        byte byteData[] = md.digest();
 
        //convert the byte to hex format
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        String pass = sb.toString();
		
		try {
			currentCon = ConnectionManager.getConnection();
			ps=currentCon.prepareStatement("insert into teacher(name, phone, address, email, password, managerid, role)values(?,?,?,?,?,?,?)");
			ps.setString(1,name);
			ps.setString(2,phone);
			ps.setString(3,address);
			ps.setString(4,email);
			ps.setString(5,pass);
			ps.setInt(6,managerid);
			ps.setString(7,role);
			ps.executeUpdate();
			
			System.out.println("Your email is " + email);
			System.out.println("Your password is " + password);
			
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
	
	  //get user by email
    public static TeacherBean getTeacherByEmail(String email) {
        TeacherBean teacher = new TeacherBean();
        try {
        	currentCon = ConnectionManager.getConnection();
            ps=currentCon.prepareStatement("select * from teacher where email=?");
            
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                
            	teacher.setTeacherid(rs.getInt("teacherid"));
                teacher.setEmail(rs.getString("email"));
                teacher.setName(rs.getString("name"));
                teacher.setAddress(rs.getString("address"));
                teacher.setPhone(rs.getString("phone"));
                teacher.setPassword(rs.getString("password"));
                teacher.setManagerid(rs.getInt("managerid"));
                teacher.setRole(rs.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teacher;
    }
    
    public TeacherBean getTeacherByTeacherid(int teacherid) {
        TeacherBean teacher = new TeacherBean();
        try {
        	currentCon = ConnectionManager.getConnection();
            ps=currentCon.prepareStatement("select * from teacher where teacherid=?");
            
            ps.setInt(1, teacherid);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                teacher.setTeacherid(rs.getInt("teacherid"));
                teacher.setEmail(rs.getString("email"));
                teacher.setName(rs.getString("name"));
                teacher.setAddress(rs.getString("address"));
                teacher.setPhone(rs.getString("phone"));
                teacher.setPassword(rs.getString("password"));
                teacher.setManagerid(rs.getInt("managerid"));
                teacher.setRole(rs.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teacher;
    } 
    //getallaccount
    
    public static TeacherBean getTeacher() {
        TeacherBean teacher = new TeacherBean();
        try {
        	currentCon = ConnectionManager.getConnection();
            ps=currentCon.prepareStatement("select * from teacher");
          
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                teacher.setTeacherid(rs.getInt("teacherid"));
                teacher.setName(rs.getString("name"));
                teacher.setEmail(rs.getString("email"));
                teacher.setManagerid(rs.getInt("managerid"));
               // teacher.setAddress(rs.getString("address"));
               // teacher.setPhone(rs.getString("phone"));
                //teacher.setPassword(rs.getString("password"));
              
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teacher;
    }
  
	
    //update account
    public void updateAccount(TeacherBean bean) throws NoSuchAlgorithmException {

    	name = bean.getName();
    	email = bean.getEmail();
    	address = bean.getAddress();
       phone = bean.getPhone();
    	password = bean.getPassword();
    	managerid = bean.getManagerid();
    	role = bean.getRole();
    	String searchQuery = "";
    	
       if(role != null) {
           searchQuery = "UPDATE teacher SET name ='"+ name +"', address='" + address + "', phone='" + phone + "', managerid='"+ managerid + "', role='"+ role +"' WHERE email= '" + email + "'";
       }
       else {
           searchQuery = "UPDATE teacher SET name ='"+ name +"', address='" + address + "', phone='" + phone + "', managerid='"+ managerid + "' WHERE email= '" + email + "'";    	   
       }
    	   
    	
    	try {

            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.createStatement();
            stmt.executeUpdate(searchQuery);
            System.out.println(searchQuery);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    	public static TeacherBean getTeacher(TeacherBean bean)  {
        	
    		email = bean.getEmail();

            String searchQuery = "select * from teacher where email='" + email + "'";

            try {
                currentCon = ConnectionManager.getConnection();
                stmt = currentCon.createStatement();
                rs = stmt.executeQuery(searchQuery);
                boolean more = rs.next();

                // if user exists set the isValid variable to true
                if (more) {
                	String email = rs.getString("email");
               
                    bean.setEmail(email);
                    bean.setValid(true);
               	}
               
                else if (!more) {
                	System.out.println("Sorry");
                	bean.setValid(false);
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

            return bean;
        }
    	 public void deleteTeacher(int teacherid) {
    	        try {
    	        	currentCon = ConnectionManager.getConnection();
    	        	ps=currentCon.prepareStatement("delete from teacher where teacherid=?");
    	            ps.setInt(1, teacherid);
    	            ps.executeUpdate();

    	        } catch (SQLException e) {
    	            e.printStackTrace();
    	        }
    	    }
    	 //allteacher
    	 public static List<TeacherBean> getAllTeacher() {
   		  List<TeacherBean> teachers = new ArrayList<TeacherBean>();
   		  
   		  try {
   		  	currentCon = ConnectionManager.getConnection();
   		  	stmt = currentCon.createStatement();
   		  
   		  	  String q = "select * from teacher";
   		      ResultSet rs = stmt.executeQuery(q);
   		      
   		      while (rs.next()) {
   		          TeacherBean teacher = new TeacherBean();
   		          
   		          
   		          teacher.setTeacherid(rs.getInt("teacherid"));
   		          teacher.setName(rs.getString("name"));
   		          teacher.setPhone(rs.getString("phone"));
   		          teacher.setEmail(rs.getString("email"));
   		          teacher.setManagerid(rs.getInt("managerid"));
   		          teacher.setRole(rs.getString("role"));
   		          teachers.add(teacher);
   		      }
   		  } catch (SQLException e) {
   		      e.printStackTrace();
   		  }

   		  return teachers;
   	}
    	 //getAllmanager
    	 public static List<TeacherBean> getAllAdmin() {
      		  List<TeacherBean> teachers = new ArrayList<TeacherBean>();
      		  
      		  try {
      		  	currentCon = ConnectionManager.getConnection();
      		  	stmt = currentCon.createStatement();
      		  
      		  	  String q = "select * from teacher where role='Admin'";
      		      ResultSet rs = stmt.executeQuery(q);
      		      
      		      while (rs.next()) {
      		          TeacherBean teacher = new TeacherBean();
      		          
      		          
      		          teacher.setTeacherid(rs.getInt("teacherid"));
      		          teacher.setName(rs.getString("name"));
      		          teacher.setEmail(rs.getString("email"));
      		          teacher.setManagerid(rs.getInt("managerid"));
      		          teacher.setRole(rs.getString("role"));
      		          teachers.add(teacher);
      		      }
      		  } catch (SQLException e) {
      		      e.printStackTrace();
      		  }

      		  return teachers;
      	}
    	 public static TeacherBean getPassword(TeacherBean bean) throws NoSuchAlgorithmException  {
         	
     		email = bean.getEmail();
     		password = bean.getPassword();
     		//convert the password to MD5
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        md.update(password.getBytes());
	 
	        byte byteData[] = md.digest();
	 
	        //convert the byte to hex format
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < byteData.length; i++) {
	         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	        }
	        
	        String pass = sb.toString();
             String searchQuery = "select * from teacher where email='" + email + "' AND password='"+ pass + "'";

             try {
                 currentCon = ConnectionManager.getConnection();
                 stmt = currentCon.createStatement();
                 rs = stmt.executeQuery(searchQuery);
                 boolean more = rs.next();

                 // if user exists set the isValid variable to true
                 if (more) {
                 	String email = rs.getString("email");
                
                     bean.setEmail(email);
                     bean.setPassword(pass);
                     bean.setValid(true);
                	}
                
                 else if (!more) {
                 	System.out.println("Sorry");
                 	bean.setValid(false);
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

             return bean;
         }
    	 //update pass
    	    public static void updatePass(TeacherBean bean) throws NoSuchAlgorithmException {

    	    	
    	      	email = bean.getEmail();
    	    	password = bean.getPassword();
    	    	
    	    	
    	    	//convert the password to MD5
    	        MessageDigest md = MessageDigest.getInstance("MD5");
    	        md.update(password.getBytes());
    	 
    	        byte byteData[] = md.digest();
    	 
    	        //convert the byte to hex format
    	        StringBuffer sb = new StringBuffer();
    	        for (int i = 0; i < byteData.length; i++) {
    	         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
    	        }
    	        
    	        String pass = sb.toString();
    	       
    	        String searchQuery = "UPDATE teacher SET  password='" + pass +"' WHERE email = '" + email + "'";
    	       
    	    	try {

    	            currentCon = ConnectionManager.getConnection();
    	            stmt = currentCon.createStatement();
    	            stmt.executeUpdate(searchQuery);
    	            
    	        } catch (SQLException e) {
    	            e.printStackTrace();
    	        }
    	    }

    
    
	
	
	
}
	


