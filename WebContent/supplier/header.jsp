<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

      <!-- Sidebar - Brand -->
      <a class="sidebar-brand d-flex align-items-center justify-content-center" href="/Inventory/index.jsp">
        <div class="sidebar-brand-icon rotate-n-15">
          <i class="fas fa-laugh-wink"></i>
        </div>
        <div class="sidebar-brand-text mx-3">Mini Mart<sup><i class="fas fa-heart"></i></sup></div>
      </a>

      <!-- Divider -->
      <hr class="sidebar-divider my-0">

      <!-- Nav Item - Dashboard -->
      <li class="nav-item active">
        <a class="nav-link" href="/Inventory/index.jsp">
          <i class="fas fa-fw fa-tachometer-alt"></i>
          <span>Home</span></a>
      </li>

      <!-- Divider -->
      <hr class="sidebar-divider">

      <!-- Heading -->
      <div class="sidebar-heading">
        Menu
      </div>

      <!-- Nav Item - Pages Collapse Menu -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo">
          <i class="fas fa-user-circle"></i>
          <span>Accounts</span>
        </a>
		<%	String email = (String)session.getAttribute("currentSessionUser");%>
        <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">Account:</h6>
            <a class="collapse-item" href="/Inventory/UserController?action=viewAccount&email=<c:out value="<%=email %>"/>">View Account</a>
			<a class="collapse-item" href="/Inventory/UserController?action=updateAccount&email=<c:out value="<%=email%>" />">Update Account</a>
			<%if(String.valueOf(session.getAttribute("currentSessionUserRole")).equalsIgnoreCase("Admin")) {%>
			<a class="collapse-item" href="/Inventory/UserController?action=search">Create Account</a>
			<a class="collapse-item" href="/Inventory/UserController?action=listAll">List Account</a>
			<%} %>
          </div>
        </div>
      </li>
      <%if(!String.valueOf(session.getAttribute("currentSessionUserRole")).equalsIgnoreCase("Teacher")) {%>

      <!-- Nav Item - Utilities Collapse Menu -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities" aria-expanded="true" aria-controls="collapseUtilities">
          <i class="fas fa-industry"></i>
          <span>Supplier</span>
        </a>
        <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">Supplier:</h6>
            <a class="collapse-item" href="/Inventory/supplier/createSupplier.jsp">Add Supplier</a>
            <a class="collapse-item" href="/Inventory/SupplierController?action=listSupplier">View Supplier</a>
          </div>
        </div>
      </li>
	  
	  <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseProduct" aria-expanded="true" aria-controls="collapseUtilities">
          <i class="fas fa-pencil-ruler"></i>
          <span>Product</span>
        </a>
        <div id="collapseProduct" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">Product:</h6>
            <a class="collapse-item" href="/Inventory/ProductController?action=searchSupplier">Add Product</a>
            <a class="collapse-item" href="/Inventory/ProductController?action=viewFood">View Food</a>
			<a class="collapse-item" href="/Inventory/ProductController?action=viewStationery">View Stationery</a>
          </div>
        </div>
      </li>
	  
	  <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseOrder" aria-expanded="true" aria-controls="collapseUtilities">
         <i class="fas fa-edit"></i>
          <span>Order To Supplier</span>
        </a>
        <div id="collapseOrder" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">Order:</h6>
            <a class="collapse-item" href="/Inventory/OrderController?action=createOrder">Add Order</a>
            <a class="collapse-item" href="/Inventory/OrderController?action=viewOrder">View Order</a>
          </div>
        </div>
      </li>
      <%} %>
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseOrderTo" aria-expanded="true" aria-controls="collapseUtilities">
         <i class="fas fa-edit"></i>
          <span>Order To Mini Mart</span>
        </a>
        <div id="collapseOrderTo" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">Order:</h6>
            <a class="collapse-item" href="/Inventory/TeacherProductController?action=createOrder">Add Order</a>
            <a class="collapse-item" href="/Inventory/TeacherProductController?action=viewOrder&email=<c:out value="<%=email %>"/>">View Your Order</a>
            <%if(!String.valueOf(session.getAttribute("currentSessionUserRole")).equalsIgnoreCase("Teacher")) {%>
            <a class="collapse-item" href="/Inventory/TeacherProductController?action=listOrder">List All Orders</a>
            <%} %>
          </div>
        </div>
      </li>
	  
	  <li class="nav-item">
        <a class="nav-link" href="/Inventory/LoginController?action=Logout" onclick="return confirm('Are you sure want to Logout?')">
          <i class="fas fa-sign-out-alt"></i>
          <span>Logout</span></a>
      </li>

    </ul>
    <!-- End of Sidebar -->
        
        