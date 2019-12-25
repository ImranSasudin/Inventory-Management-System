<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

 <% if((String)session.getAttribute("currentSessionUser") == null )
      response.sendRedirect("/Inventory/login.jsp"); %>
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


		<title>Update Account</title>
</head>
<link href="/Inventory/css/select2.min.css" rel="stylesheet">

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
            <h1 class="h3 mb-0 text-gray-800">Update Account</h1>
          </div>
          <div class="row">
			<div class="mx-auto" style="width: 400px;">
			<form method="post"  action="UserController">
       		
			<br>Email:<input type="email" class="form-control" name="email" value="<c:out value="${teacher.email}" />" />
			<br>Name:<input type="text" class="form-control" name="name"  pattern="^[_A-z]*((-|\s)*[_A-z])*$" title="Name cannot contain any special character or number" value="<c:out value="${teacher.name}" />" />		
			<br>Address:<input type="text" class="form-control" name="address"  value="<c:out value="${teacher.address}" />"/>	
			<br>Phone:<input type="text" class="form-control" name="phone" pattern="^[0-9]*$" title="Put Number Only" value="<c:out value="${teacher.phone}" />"/>	
			<br>Password: <a href="/Inventory/user/updatePass.jsp" class="btn btn-info">Update Password</a><br>
			<br>Manager ID:
			<select class="autocomplete form-control" name="managerid">
				<c:forEach items="${teachers}" var="teacher2">
					<c:choose>
						<c:when test="${teacher2.teacherid == teacher.managerid }">
						<option selected hidden value="<c:out value="${teacher2.teacherid}"/>"><c:out value="${teacher2.name}"/>   (ID : <c:out value="${teacher2.teacherid}"/>)</option>
						</c:when>
						<c:otherwise>
						<option value="<c:out value="${teacher2.teacherid}"/>"><c:out value="${teacher2.name}"/>   (ID : <c:out value="${teacher2.teacherid}"/>)</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				</select>				
				<%if(String.valueOf(session.getAttribute("currentSessionUserRole")).equalsIgnoreCase("Admin")) {%>
				<br><br>Role:
				<select class="form-control" name="role">
					<option selected hidden value="<c:out value="${teacher.role}"/>"><c:out value="${teacher.role}"/></option>
					<option value="Admin">Admin</option>
					<option value="Staff">Staff</option>
					<option value="Teacher">Teacher</option>
				</select>
				<%} %>
				
			

			<br><br><input type = "submit" name="action" class="btn btn-primary" value = "Submit">

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
<script src="/Inventory/js/select2.full.min.js"></script>
	<script>
$(document).ready(function(){
	$('.autocomplete').select2();
});
</script>
</html>
