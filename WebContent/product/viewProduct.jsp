<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

 <% if((String)session.getAttribute("currentSessionUser") == null )
      response.sendRedirect("/Inventory/login.jsp"); %>


<!DOCTYPE html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="icon" type="image/png" href="/Inventory/logintemplate/images/icons/favicon.ico"/>
	    <link href="/Inventory/css/all.min.css" rel="stylesheet">
	   	<link href="/Inventory/js/all.min.js" rel="stylesheet">
	    
	    <script src="/Inventory/js/all.min.js"></script>
		 
		 <link rel="stylesheet" href="/Inventory/css/bootstrap.min.css">
		 <link href="/Inventory/css/sb-admin-2.min.css" rel="stylesheet">
		<link rel="stylesheet" href="/Inventory/css/dataTables.min.css">

		<title>List Products</title>
	
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
            <h1 class="h3 mb-0 text-gray-800">List Products</h1>
          </div>
          <div class="row">
			<div class="table mx-auto">
			<table  id="myTable" class="display" border="1" cellpadding="5">
 	<thead>
            <tr>
                <th>Product Id</th>
                <th>Product Name</th>
                <th>Product Price (RM)</th>
                <th>Sell Price (RM)</th>
                <th>Product Quantity</th>
                <th>Supplier ID</th>
                <th>Product Type</th>
                <c:choose>
                <c:when test="${type == 'food'}">
                <th>Food Type</th>
                </c:when>
                <c:otherwise>
                <th>Brand</th>
                </c:otherwise>
                </c:choose>
                
                <th>Update</th>
                <th>Delete</th>
                
            </tr>
            </thead>
            <tbody>
        <c:forEach var="product" items="${products}" >
                <tr>
                    <td><c:out value="${product.prodid}" /></td>
                    <td><c:out value="${product.prodName}" /></td>
                    <td><c:out value="${product.prodPrice}" /></td>
                    <td><c:out value="${product.sellPrice}" /></td>
                    <td><c:out value="${product.prodQuantity}" /></td>
                    <td><c:out value="${product.supplierid}" /></td>
                    <td><c:out value="${product.prodType}" /></td>
                    <c:choose>
               		<c:when test="${type == 'food'}">
               		<td><c:out value="${product.foodType}" /></td>
               		</c:when>
               		 <c:otherwise>
               		 <td><c:out value="${product.stationeryBrand}" /></td>
               		 </c:otherwise>
               		 </c:choose>
                    <td><a href="ProductController?action=updateProduct&prodid=<c:out value="${product.prodid}" />&prodType=<c:out value="${product.prodType}" />">Update</a></td>
                   	<td><a href="ProductController?action=deleteProduct&prodid=<c:out value="${product.prodid}" />">Delete</a></td>
                </tr>
        </c:forEach>
        </tbody>
    </table>
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
<script src="/Inventory/js/dataTables.min.js"></script>
	<script>
	$(document).ready( function () {
		   $('#myTable').DataTable();
		} );
	</script>
</html>
