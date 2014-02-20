<script src="js/simpleSearch.js"></script>
<script>
  $(document).ready(function(){
  	loadDocPage = function(id){
		$.ajax({  
	            type: "post",
	            dataType: "text",
	            data: "id="+id,
	            url: "documentInfo.jsp",
	            success: function(data){$("#content-container").html(data);},
	            error: function(data){}
       	});  
	
	};
	
	$("#simple-search-btn").click(function(){
  		var param = "title=" + $("#simple-query").val();
  		$.ajax({
  			url: "documents_simpleSearch.action",
  			data: param,
  			dataType: "json",
  			type: "POST",
  			success: function(data){
	  			if(data.docLists.length > 0){
	  				var text = "";
	  				$.each(data.docLists,function(i,item){
						text += '<div class="w100p mydoc-item">'+
					      		'<div><a href="javascript:loadDocPage('+ item.id +')" class="doc-title text-danger">'+ item.title +'</a></div>'+
					      		'<div class="doc-author h5 text-muted">' + item.author +'</div>'+
					      		'<div class="doc-abstract h5">'+ item.abstracts +
								'</div></div>';
						});
					$("#search-result-container").removeClass('hide');
					$("#search-result-panel").html(text);
				}else{
					$("#search-result-container").removeClass('hide');
					$("#search-result-panel").html('<div class="mydoc-item text-muted h4">No results found!</class>');
				}
			},
			error: function(data){
				$("#search-result-container").removeClass('hide');
				$("#search-result-panel").html('<div class="mydoc-item text-muted h4">No results found!</class>');
			}
		});
  		
	});
  
  	$("#advanced-search-panel").hide();
  	$("#advanced-search-switch").click(function(){
  		if($(this).hasClass('active')){
  			$("#advanced-search-panel").fadeOut();
  		}else{
  			$("#advanced-search-panel").fadeIn();
  		}
  	});
  	
  	$("#advanced-search-btn").click(function(){
  		reg = /^(19|20){1}\d{2}$/;
  		var year = $("#inputYear").val();
  		var endYear = $("#inputEndYear").val();
  		$("#inputYearAlert").html("");
  		if(year != "" || endYear != ""){
  			if( ((year!="") && (endYear!="")) && reg.test(year) && reg.test(endYear)){
  				$("#inputYearAlert").html("");
  			}else{
  				$("#inputYearAlert").html("Input format is wrong!");
  				return;
  			}
  		}
  		
  		var param = $("#advanced-search-form").serialize();
  		$.ajax({
	  			url: "documents_search.action",
	  			type: "POST",
				data: param,
				dataType: "json",
				success: function(data){
			  			if(data.docLists.length > 0){
			  				var text = "";
			  				$.each(data.docLists,function(i,item){
					  			$("#search-result-container").removeClass('hide');
					  			text += '<div class="w100p mydoc-item">'+
							      		'<div><a href="javascript:loadDocPage('+ item.id +')" class="doc-title text-danger">'+ item.title +'</a></div>'+
							      		'<div class="doc-author h5 text-muted">' + item.author +'</div>'+
							      		'<div class="doc-abstract h5">'+ item.abstracts +
										'</div></div>';
							});
							$("#search-result-panel").html(text);
						}else{
							$("#search-result-container").removeClass('hide');
							$("#search-result-panel").html('<div class="mydoc-item text-muted h4">No results found!</class>');
						}
	  				},
	  			error: function(){
	  				$("#search-result-container").removeClass('hide');
					$("#search-result-panel").html('<div class="mydoc-item text-muted h4">No results found!</class>');
	  			}
  			});
  	});
  	
  });
</script>

<div class="panel panel-success mh400">
  <div class="panel-heading">Search documents</div>
  <div class="panel-body">
  	<div class="input-group">
	  <input type="text" class="form-control" placeholder="search documents" id="simple-query" value="">
	  <div class="input-group-btn">
		<button id="simple-search-btn" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>
	  </div>
	</div>
	
	<p class="mt10 text-left"><button type="button" id="advanced-search-switch" class="btn btn-default" data-toggle="button">Advanced search</button></p>
	
    <div id="advanced-search-panel" class="panel panel-default mt15">
      <div class="panel-heading">Advanced search options</div>
      <div class="panel-body">
  	 	<form id="advanced-search-form" class="form-horizontal" role="form">
		  <div class="form-group">
		    <label for="selectType" class="col-sm-3 control-label">Document Type</label>
		  	<div class="col-sm-3">
				<select id="selectType" class="form-control" name="type">
					<option value="">any type</option>
		  			<option value="Book">Book</option>
		  			<option value="Book Section">Book Section</option>
		  			<option value="Journal">Journal</option>
		  			<option value="Conference">Conference</option>
		  			<option value="Thesis">Thesis</option>
		  			<option value="Report">Report</option>
		  			<option value="Online">Online</option>
				</select>
			</div>
		  </div>
		  <div class="form-group">
		    <label for="inputYear" class="col-sm-3 control-label">Year</label>
		    <div class="ml15 w100 left">
		      <input type="text" class="form-control" id="inputYear" name="year">
		    </div>
		    <div class="left ml15 control-label">-</div>
		    <div class="ml15 w100 left">
		      <input type="text" class="form-control" id="inputEndYear" name="endyear">
		    </div>
		    <div class="left ml15 text-danger control-label" id="inputYearAlert"></div>
		  </div>
		  <div class="form-group">
		    <label for="inputTitle" class="col-sm-3 control-label">Within title</label>
		    <div class="col-sm-6">
		      <input type="text" class="form-control" id="inputTitle" name="title">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputAuthor" class="col-sm-3 control-label">Within author</label>
		    <div class="col-sm-6">
		      <input type="text" class="form-control" id="inputAuthor" name="author">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputAbstract" class="col-sm-3 control-label">Within abstract</label>
		    <div class="col-sm-6">
		      <input type="text" class="form-control" id="inputAbstract" name="abstracts">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputTags" class="col-sm-3 control-label">With Tags</label>
		    <div class="col-sm-6">
		      <input type="text" class="form-control" id="inputTags" name="tag" placeholder="separated by commas">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputTags" class="col-sm-3 control-label"> </label>
		    <div class="col-sm-6">
		      <button id="advanced-search-btn" type="button" class="btn btn-default w150">Search</button>
		    </div>
		  </div>
		</form>
      </div>
    </div>
    
    <div id="search-result-container" class="mt30 hide">
    	<div class="w100p h4">Search Results</div>
    	<div id="search-result-panel"></div>
  	</div>
</div>	  