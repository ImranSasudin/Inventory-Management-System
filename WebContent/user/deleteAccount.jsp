<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<% if((String)session.getAttribute("currentSessionUser") == null )
      response.sendRedirect("/Inventory/login.jsp");
  else if(!String.valueOf(session.getAttribute("currentSessionUserRole")).equalsIgnoreCase("Admin"))
      response.sendRedirect("/Inventory/index.jsp");  %>
      
<!DOCTYPE html>

<html lang="en">

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


		<title>Delete Account</title>
</head>
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
            <h1 class="h3 mb-0 text-gray-800">Delete Account</h1>
          </div>
          <div class="row">
			<div class="mx-auto" style="width: 400px;">
			<form action="UserController" method="post">
                     
		    Teacher ID : <br>
			<input type="text" name="teacherid" class="form-control"  value="<c:out value="${teacher.teacherid}"/>" readonly/><br>
			Teacher Name : <br>
			<input type="text" name="name" class="form-control" value="<c:out value="${teacher.name}" />" readonly/><br>
			Teacher Address : <br>
			<input type="text" name="address" class="form-control" value = "<c:out value="${teacher.address}" />" readonly/><br>
			Teacher Phone : <br>
			<input type="text" name="phone" class="form-control" value="<c:out value="${teacher.phone}" />" readonly/><br>
			Manager ID : <br>
			<input type="text" name="managerid" class="form-control" value=" <c:out value="${teacher.managerid}" />" readonly/><br>
		
			<input type = "submit"  class="btn btn-primary" name=action value="Delete" onclick="return confirm('Are you sure want to delete this data? \nYou can\'t recover the data if u delete it')">
				
			</form>
	<br>
	
	<br>	
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
<script src="select2.full.min.js"></script>
	
</html>

