<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
     <% if((String)session.getAttribute("currentSessionUser") == null )
      response.sendRedirect("/Inventory/login.jsp"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
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
		<%	String email = (String)session.getAttribute("currentSessionUser");%>

		<title>Add Order</title>
</head>
<script src="/Inventory/js/jquery-3.4.1.min.js"></script>
<script>
$(document).ready(function(){
	
	$("#add").click(function (e){
		event.preventDefault()
		$("#items").append('<div class="items">Product ID: <br><select class="autocomplete form-control select" name="prod" required>' + 
				'<c:forEach items="${products}" var="product">' +
				'<option value="<c:out value="${product.prodid}"/> <c:out value="${product.sellPrice}"/>"><c:out value="${product.prodName}"/>  (RM<c:out value="${product.sellPrice}"/> per unit)</option></c:forEach> </select> <br><br>' +
				'Quantity :<br> <input type="text" class="form-control" name="orderProdQuantity" pattern="^[0-9]*$" title="Number only" required value="0"> <input type="button" value="Delete" id="delete" class="btn btn-danger align-right"></div><br><br>');
		$('.autocomplete').select2();
	});	

	$('body').on('click','#delete',function (e){
		$(this).parent('div').remove();
	});
	
});
</script>
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
            <h1 class="h3 mb-0 text-gray-800">Create Order</h1>
          </div>
          <div class="row">
			<div class="mx-auto" style="width: 400px;">
			 <form action = "/Inventory/TeacherProductController" method = "post">
		
				<div id="items">
				<div class="items">
				Product: <br>
				<select class="autocomplete form-control" name="prod" required>
				<c:forEach items="${products}" var="product">
					<option value="<c:out value="${product.prodid}"/> <c:out value="${product.sellPrice}"/>"><c:out value="${product.prodName}"/>  (RM<c:out value="${product.sellPrice}"/> per unit)</option>
				</c:forEach>
				</select> <br><br>
				Quantity :<br>
				<input type = "text"  class="form-control" name="orderProdQuantity" pattern="^[0-9]*$" title="Number only" required><br>
				</div>
				</div>
				<button type="button" value="Add Order" id="add" role="button" class="btn btn-secondary">
				Add Order
				</button>
			
				<hr>
				<input hidden name="email" value="<c:out value="<%=email %>"/>">
				<button type="button" id="calculate" class="btn btn-success">Calculate Total</button><input id="total" class="form-control" type="text" readonly><br>
				<input type = "submit" value = "Submit" name ="action" class="btn btn-primary">
							
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
$('#calculate').click(function ()
		{
	      var total = 0;
	      $('select').each(function(){
			var res = parseFloat($(this).val().split(" ")[1]);
	       var one = res * jQuery(this).siblings('input').first().val();
	       total += one;
	      });
	      $("#total").val("RM" + total.toFixed(2));
		});
$(document).ready(function(){
	$('.autocomplete').select2();
});
</script>
</html>