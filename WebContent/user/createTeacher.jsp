<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<% if((String)session.getAttribute("currentSessionUser") == null )
      response.sendRedirect("/Inventory/login.jsp");
  else if(!String.valueOf(session.getAttribute("currentSessionUserRole")).equalsIgnoreCase("Admin"))
      response.sendRedirect("/Inventory/index.jsp");  %>

<!DOCTYPE html>
<html>
<head>
		<meta charset="UTF-8">
	    <meta name="description" content="">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	    <link rel="icon" type="image/png" href="/Inventory/logintemplate/images/icons/favicon.ico"/>
	    <link href="/Inventory/css/all.min.css" rel="stylesheet">
	   	<link href="/Inventory/js/all.min.js" rel="stylesheet">
	    
	    <script src="/Inventory/js/all.min.js"></script>
		 
		 <link rel="stylesheet" href="/Inventory/css/bootstrap.min.css">
		 <link href="/Inventory/css/sb-admin-2.min.css" rel="stylesheet">


		<title>Register Teacher</title>
</head>

<link href="/Inventory/css/select2.min.css" rel="stylesheet">


<body>
  <body id="page-top">
	<!-- Page Wrapper -->
  <div id="wrapper">

   <jsp:include page="header.jsp" />

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div id="content">

       <!-- Page Top Navigation  -->
		<jsp:include page="navigation.jsp" />

        <!-- Begin Page Content -->
        <div class="container-fluid">
        <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 class="h3 mb-0 text-gray-800">Register Account</h1>
          </div>
          <div class="row">
			<div class="mx-auto" style="width: 400px;">
			 <form action = "RegisterController" method = "post">
	
			 <!--  <select class="js-example-basic-single" name="state">
		  <option value="AL">Alabama</option>
		  <option value="WY">Wyoming</option> </select> -->
		  
		   <div class="">
				Name: <br><input class="form-control" type = "text" placeholder = "Enter Name" pattern="^[_A-z]*((-|\s)*[_A-z])*$" title="Name cannot contain any special character or number" name=name required><br>
		  </div>
			 <div class="">
		      	Phone: <input class="form-control" type = "text" placeholder = "Enter Phone" name=phone pattern="^[0-9]*$" title="Put Number Only" required><br>
		    </div>
		   <div class="">
		    	 Address: <input class="form-control" type = "text" placeholder = "Enter Address" name=address required><br>
		   </div>
		  
			 <div class="">
			     Email: <input class="form-control" type = "email" placeholder = "Enter Email" name=email required><br>
			 </div>
			 <div class="">
			     Password: <input class="form-control" type = "password" placeholder = "Enter Password" name=password required><br>
			  </div>
			
			Manager ID : <br>
				<select class="autocomplete form-control" name="managerid">
				<c:forEach items="${teachers}" var="teacher">
					<option value="<c:out value="${teacher.teacherid}"/>"><c:out value="${teacher.name}"/>   (ID : <c:out value="${teacher.teacherid}"/>)</option>
				</c:forEach>
				</select>
				<br>
		 
				Role : <br>
				<select name="role" class="form-control">
				<option value="Admin">Admin</option>
				<option value="Staff">Staff</option>
				<option value="Teacher">Teacher</option>
				</select>
				<br>
	
	<br>	
	<input type = "submit" value = "Submit" class="btn btn-primary">
	</form>	
			</div>
          </div>         
        </div>
        <!-- /.container-fluid -->
      </div>
      <!-- End of Main Content -->

      <!-- Footer -->
      <footer class="sticky-footer bg-white">
        <div class="container my-auto">
          <div class="copyright text-center my-auto">
            <span>Copyright &copy; ELM CO. 2019</span>
          </div>
        </div>
      </footer>
      <!-- End of Footer -->

    </div>
    <!-- End of Content Wrapper -->

  </div>
  <!-- End of Page Wrapper -->

  <!-- Scroll to Top Button-->
  <a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
  </a>
</body>
 <script src="/Inventory/js/jquery-3.4.1.min.js"></script>
   <script src="/Inventory/js/bootstrap.min.js"></script>
  
    <script src="/Inventory/js/sb-admin-2.min.js"></script>
<script src="/Inventory/js/select2.full.min.js"></script>
<script>
$(document).ready(function(){
	$('.autocomplete').select2();
});
</script>
	</html> 
