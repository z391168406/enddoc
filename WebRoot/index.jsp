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
	  $("#login-alert").hide();
	  
	  $(".close").click(function(){
	  	 $("#login-alert").hide();
	  });
	  
	  $("#submit_button").click(function() {
	    event.preventDefault();
	 	var qs = $("#login-form").serialize();
    	$.ajax({
			type : "POST",
			url : "login.action",
			dataType : "json",
			data : qs,
			success : function(data) {
				console.log(data.message);
				if(data.message=="Admin")
					window.location.href = "admin.jsp";
				else 
					window.location.href = "main.jsp";
			},
			error : function(err) {
				$("#login-alert").show();
			}
		});
	  }); 	
	  
	});
	</script>
  </head>
  
  <body>
    <div id="wrapper">	 
      <%@ include file="header.jsp" %>
	  <section id="login-panel" class="ofh">
	  	<div id="title" class="center">
	  	  <div>Sign In</div>
	  	</div>
	  	<div id="left-panel" class="left">
	  	  <form id="login-form" action="" method="post">
			<div id="input-panel">
			  <div class="form-group">
    			<label for="username">Username</label>
   				<input type="text" class="form-control" name="username" placeholder="">
  			  </div>
			  <div class="form-group">
    			<label for="password">Password</label>
   				<input type="password" class="form-control" name="password" placeholder="">
  			  </div>
			  <button id="submit_button" type="submit" class="btn btn-primary w100p">Sign In</button>
			  <div id="login-alert" class="alert alert-danger alert-dismissable fade in mt15">
  				<button type="button" class="close">&times;</button>
  				<strong>Sorry!</strong> username or password is wrong.
			  </div>
			</div> 
		  </form>
	  	</div>
	  	<div id="right-panel" class="right">
	  	  <p id="description"><strong>EndDoc</strong> is a free reference manager and academic social network that can help you organize your research, collaborate with others online, and discover the latest research.
	  	  </p>	  
	  	  <img id="des-img" class="right" alt="description image" src="img/desc.jpg" height="250" width="320">	
	  	</div>
	  </section>
	  	
	</div>
	  
  </body>
</html>
