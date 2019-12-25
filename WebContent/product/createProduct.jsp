<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 <% if((String)session.getAttribute("currentSessionUser") == null )
      response.sendRedirect("/Inventory/login.jsp"); %>
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
		<link href="/Inventory/css/select2.min.css" rel="stylesheet">

		<title>Add Product</title>
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
            <h1 class="h3 mb-0 text-gray-800">Add Product</h1>
          </div>
          <div class="row">
			<div class="mx-auto" style="width: 400px;">
			<form action = "ProductController" method = "post">
	
	 
	Product name : <br>
	<input type = "text" class="form-control" placeholder = "Enter product name" name=prodName required><br>
	Product price(RM) : <br>
	<input type = "text" class="form-control" placeholder = "Enter product price" pattern="^[0-9]*\.[0-9]{1,2}$" title="(Example: 20.00)" name=prodPrice required><br>
	Sell price(RM) : <br>
	<input type = "text" class="form-control" placeholder = "Enter sell price" pattern="^[0-9]*\.[0-9]{1,2}$" title="(Example: 20.00)" name=sellPrice required><br>
	Product quantity : <br>
	<input type = "number" class="form-control" placeholder = "Enter product quantity" name=prodQuantity required><br>
	Supplier ID : <br>
	<select class="autocomplete form-control" name="supplierid">
	<c:forEach items="${suppliers}" var="supplier">
		<option value="<c:out value="${supplier.supplierid}"/>"><c:out value="${supplier.suppName}"/>   (ID : <c:out value="${supplier.supplierid}"/>)</option>
	</c:forEach>
	</select>
	<br>
	Product type : <br>
	<select name="prodType" class="form-control" required>
	<option value="Food">Food</option>
	<option value="Stationery">Stationery</option>
	</select>
	<br/>
	 
	Enter the type of food/brand of stationary:<br>
  		<input type="text" placeholder=""  name=product class="form-control" required/>
  		<br>
 
 	
	<hr>
	<input type = "submit" name="action" value = "Submit" class="btn btn-primary">

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