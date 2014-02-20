<script>
$(document).ready(function(){
	$("#signout-link").click(function(){
		$.ajax({
			type : "POST",
			url : "logout.action",
			dataType : "json",
			success : function(data) {
				window.location.href = "index.jsp";
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("sign out failed!");
			}
		});
	});
	
	$("#user-dashbord").hide();
	$("#username-link").click(function(){
		$("#user-dashbord").slideToggle();
	});
});

</script>

<header class="ofh h150">
  <a href="<%out.print(basePath);%>" id="logo-link" class="block mt30 w300 left">
    <img id="logo" alt="EndDoc logo" src="img/logo.png" title="EndDoc">
  </a>	  	
  <% if (session.getAttribute("username")!=null){ %>
  <div class="panel panel-default w250 right mt15">
    <div class="panel-heading ofh">
      <span class="left">Welcome back , <a id="username-link" href="javascript:void(0)"><strong>${sessionScope.username }</strong></a></span>
      <span class="right"><a id="signout-link" href="javascript:void(0)">Sign Out</a></span>
    </div>
    <div class="panel-body pd10" id="user-dashbord">
      <ul class="list-unstyled">
  	    <li class="left ml15"><button type="button" class="btn btn-default btn-xs"><span class="glyphicon glyphicon-user"></span> Profile</button></li>
  		<li class="left ml15"><button type="button" class="btn btn-default btn-xs"><span class="glyphicon glyphicon-wrench"></span> Edit</button></li>
	  </ul>
    </div>
  </div>
  <% } %>
  <div class="clear"></div> 
</header>