<script>
  $(document).ready(function(){
  	var byTime = "all";
  	
    loadUser = function(){
      $.post("statistics_statistics.action",{},function(data){
		var statisticsList = data.statisticsList;
		var text = "";
		$.each(statisticsList,function(i,item){
			text +=   '<tr><td>'+ item.username +'</td>'+
				  '<td>'+ item.countDoc +'</td>'+
				  '<td>'+ item.countAttachment +'</td>'+
				  '<td>'+ item.countBrief +'</td>'+
				  '<td>'+ item.countDetail +'</td>'+
				  '</td></tr>';								
		});
 		$("#admin-user-table tbody").html(text);
      });
	};
	
	$("#select-time a").click(function(){
		$("#select-time-btn span:first").html($(this).html()+" ");
		byTime = $(this).attr('data-time');
	});
	
	loadUser();
	$("#user-search-btn").click(function(){
		var query = "username=" + $("#search-name").val() + "&time="+ byTime;
		console.log(query);
		$.ajax({
			url: "statistics_statistics.action",
			data: query,
			dataType: "json",
			success: function(data){
				if(data.statisticsList.length > 0){
					var text = "";
					$.each(data.statisticsList,function(i,item){
						text +=   '<tr><td>'+ item.username +'</td>'+
				  '<td>'+ item.countDoc +'</td>'+
				  '<td>'+ item.countAttachment +'</td>'+
				  '<td>'+ item.countBrief +'</td>'+
				  '<td>'+ item.countDetail +'</td>'+
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
  });

	
</script>

<div class="panel panel-success mh400">
  <div class="panel-heading">Statistics</div>
  <div class="panel-body">
  	<div class="w500">
	    <div class="input-group">
	      <div class="input-group-btn">
	        <button id="select-time-btn" type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"><span>all time </span><span class="caret"></span></button>
	        <ul class="dropdown-menu" id="select-time">
	          <li><a href="javascript:;" data-time="all">all time</a></li>
	          <li><a href="javascript:;" data-time="week">this week</a></li>
	          <li><a href="javascript:;" data-time="month">this month</a></li>
	          <li><a href="javascript:;" data-time="year">this year</a></li>
	        </ul>
	      </div>
	      <input type="text" class="form-control" id="search-name" placeholder="search username...">
	      <span class="input-group-btn">
	        <button class="btn btn-default" id="user-search-btn" type="button"><span class="glyphicon glyphicon-search"></span></button>
	      </span>
	    </div><!-- /input-group -->
    </div><!-- /.col-lg-6 -->
  
  	<table id="admin-user-table" class="table mt15">
  	  <thead>
  	  <tr class="active">
  	  	<th>Username</th>
  	  	<th>document</th>
  	  	<th>attachment</th>
  	  	<th>BriefComment</th>
  	  	<th>DetailComment</th>
  	  </tr>
  	  </thead>
  	  <tbody></tbody>
	</table>
  </div>
</div>	  