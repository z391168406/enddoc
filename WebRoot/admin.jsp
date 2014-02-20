<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">  
    <title>Admin - EndDoc</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<script src="js/jquery-1.9.1.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script>
	$(document).ready(function(){
	  var page_map = new Array("adminUser.jsp",
							   "adminDoc.jsp"
							   ); 
							   
	  $("#menu > div > button").click(function(){
	   	$.ajax({  
	            type: "post",
	            dataType: "text",
	            url: page_map[$(this).index()],
	            success: function(data){$("#content-container").html(data);}  
       	});  
	  });
	  
	  $("#menu > div > button:eq(0)").click();
	  
	});
	</script>
  </head>
  
  <body>
    <div id="wrapper">	 
      <%@ include file="header.jsp" %>
      
	  <section id="main-container" class="ofh">
		<div id="menu" class="left h200">
	  	  <div class="btn-group-vertical">
  			<button type="button" class="btn btn-default"><span class="glyphicon glyphicon-user"></span> User Config</button>
  			<button type="button" class="btn btn-default"><span class="glyphicon glyphicon-list-alt"></span> Doc Config&nbsp;</button>
		  </div>
		</div>	
		<div id="content-container" class="w820 right"></div>
	  </section>
	  
	  <%@ include file="footer.jsp" %>
	</div>
  </body>
</html>
