<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">  
    <title>EndDoc</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<script src="js/jquery-1.9.1.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script>
	$(document).ready(function(){
	  var page_map = new Array("myDocList.jsp",
							   "upload.jsp",
							   "search.jsp",
							   "stats.jsp"); 
							   
	  $("#menu > div > button").click(function(){
	   	$.ajax({  
	            type: "post",
	            dataType: "text",
	            data: "id=1",
	            url: page_map[$(this).index()],
	            success: function(data){$("#content-container").html(data);},
	            error: function(data){}
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
  			<button type="button" class="btn btn-default">My Docs</button>
  			<button type="button" class="btn btn-default">Upload</button>
			<button type="button" class="btn btn-default">Search</button>
  			<button type="button" class="btn btn-default">Stats</button>
		  </div>
		</div>	
		<div id="content-container" class="w820 right"></div>
	  </section>
	  
	  <%@ include file="footer.jsp" %>
	</div>
  </body>
</html>
