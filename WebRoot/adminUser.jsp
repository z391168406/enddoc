<script>
  $(document).ready(function(){
    loadUser = function(){
      $.post("user_findall.action",{},function(data){
		var userList = data.ldvos;
		var text = "";
		$.each(userList,function(i,item){
			text +=   '<tr><td>'+ item.username +'</td>'+
				  '<td>'+ item.password +'</td>'+
				  '<td><a href="javascript:removeUser('+ item.username +')"><span class="glyphicon glyphicon-remove"></span></a>'+
				  '<a href="javascript:modifyUser('+ item.username +')"><span class="ml15 glyphicon glyphicon-pencil"></span></a>'+
				  '</td></tr>';								
		});
 		$("#admin-user-table tbody").html(text);
      });
	};
	
	removeUser = function(username){
		var param = "username=" + username;
		$.post("user_delUser.action",param,function(data) {
			console.log(data);
			loadUser();
		});
	};

	modifyUser = function(username){
		$("#modal-username").html(username);
		$("#modify-modal").modal('show');
	};
		
	$("#modal-modify-btn").click(function(){
		var param = "username=" + $("#modal-username").html() +
					"&newPassword=" + $("#modifyPasswordInput").val() +
					"&password=" + $("#modifyPasswordInput").val();
		$.post("user_savePassword.action",param,function(data) {
			$("#modify-modal").modal('toggle');
			loadUser();
		});
	});
	
	$("#add-user-btn").click(function(){
		$("#add-modal").modal('show');
	});
	$("#modal-add-btn").click(function(){
		if(!($("#addUsernameInput").val()=="" || $("#addPasswordInput").val()=="")){
			var param = "username=" + $("#addUsernameInput").val() +
					"&password=" + $("#addPasswordInput").val();
			$.post("user_addUser.action",param,function(data) {
			console.log(data);
				$("#add-modal").modal('toggle');
				loadUser();
			});
		}
	});
	
	loadUser();
	
	$("#admin-user-table tr:gt(0)").hover(function(){
		$(this).addClass("warning");
	},function(){
		$(this).removeClass("warning");
	});

	$("#user-search-btn").click(function(){
		var query = "username=" + $("#query").val() + "&name=";
		$.ajax({
			url: "user_search.action",
			data: query,
			dataType: "json",
			success: function(data){
				if(data.ldvos.length > 0){
					var text = "";
					$.each(data.ldvos,function(i,item){
						text += '<tr><td>'+ item.username +'</td>'+
						  '<td>'+ item.password +'</td>'+
						  '<td><a href="javascript:removeUser('+ item.username +')"><span class="glyphicon glyphicon-remove"></span></a>'+
						  '<a href="javascript:modifyUser('+ item.username +')"><span class="ml15 glyphicon glyphicon-pencil"></span></a>'+
						  '</td></tr>';		
					});
					$("#admin-user-table tbody").html(text);
				}else{
					$("#admin-user-table tbody").html('<div class="text-muted pd10">No results found!</class>');
				}
			},
			error: function(data){
				$("#admin-user-table tbody").html('<div class="text-muted pd10">No results found!</class>');
			}
		});
	});
/*	
	var itemsPerPage = 2;
	var pageNum = userList.data.length / itemsPerPage + 1;
	if(pageNum > 1){
		for (var i = 1; i<=pageNum; i++){
			if(i <= 5){
				$(".pagenation").append('<li><a href="javascript:loadPage(1)">1</a></li>');
			}else{
				$(".pagenation").append('<li class="hide"><a href="javascript:loadPage(1)">1</a></li>');
			}
		}
	}else{
		$(".pagenation").hide();
	}
	*/
  });

	
</script>

<div class="panel panel-success mh400">
  <div class="panel-heading">User Configuration</div>
  <div class="panel-body">
  	<div class="left w500">
	  <div class="input-group">
	  	<input type="text" class="form-control" placeholder="search" id="query" name="query" value="">
	  	<div class="input-group-btn">
		  <button id="user-search-btn" type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>
	    </div>
	  </div>
	</div>
  	<button type="button" id="add-user-btn" class="ml15 btn btn-default"><span class="glyphicon glyphicon-plus"></span>Add</button>
  	<table id="admin-user-table" class="table mt30">
  	  <thead>
  	  <tr class="active">
  	  	<th>Username</th>
  	  	<th>Password</th>
  	  	<th>Action</th>
  	  </tr>
  	  </thead>
  	  <tbody></tbody>
	</table>
    <!--  div class="text-center">
    	<ul class="pagination">
		  <li class="disabled"><a href="#">&laquo;</a></li>
		  <li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>
		  <li><a href="javascript:loadPage(1)">1</a></li>
		  <li><a href="javascript:loadPage(1)">2</a></li>
		  <li><a href="javascript:loadPage(1)">3</a></li>
		  <li><a href="javascript:loadPage(1)">4</a></li>
		  <li><a href="javascript:loadPage(1)">5</a></li>
		  <li><a href="#">&raquo;</a></li>
		</ul>
    </div!-->
  </div>
</div>	  

<div class="modal fade" id="modify-modal" tabindex="-1" role="dialog"  aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
    
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">Modify User</h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal" role="form">
		  <div class="form-group">
		    <label class="col-sm-3 control-label">Username</label>
		    <div class="col-sm-6">
		      <p id="modal-username" class="form-control-static"></p>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputPassword" class="col-sm-3 control-label">Password</label>
		    <div class="col-sm-6">
		      <input type="text" class="form-control" id="modifyPasswordInput">
		    </div>
		  </div>
		 </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" id="modal-modify-btn">Save changes</button>
      </div>
    </div>
  </div>
</div>

<div class="modal fade" id="add-modal" tabindex="-1" role="dialog"  aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
    
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">Add User</h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal" role="form">
		  <div class="form-group">
		    <label for="addUsernameInput" class="col-sm-3 control-label">Username</label>
		    <div class="col-sm-6">
		      <input type="text" class="form-control" id="addUsernameInput">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputPassword" class="col-sm-3 control-label">Password</label>
		    <div class="col-sm-6">
		      <input type="text" class="form-control" id="addPasswordInput">
		    </div>
		  </div>
		  </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" id="modal-add-btn">Confirm</button>
      </div>
    </div>
  </div>
</div>