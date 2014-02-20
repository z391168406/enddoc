<script>
$(document).ready(function(){
	var docId = <% out.print(request.getParameter("id")); %>
  	refList = [];
  	abbrList = {};
  
  	$.ajax({
  		type: "post",
  		dataType: "json",
  		url: "documents_findDocInfo.action",
  		data: "id=" + docId,
  		success: function(data){
  			/* load basic info*/
  			var docInfo = data.docLists[0];
  			$("#select-type").val(docInfo.type);
  			$("#inputTitle").val(docInfo.title);
  			$("#inputAuthor").val(docInfo.author);
  			$("#inputYear").val(docInfo.year);
  			$("#inputPage").val(docInfo.pages);
  			$("#inputPublisher").val(docInfo.publisher);
  			$("#inputKeywords").val(docInfo.keywords);
  			$("#inputUrl").val(docInfo.url);
  			$("#inputAbstract").val(docInfo.abstracts);
 			
 			/*load attachments*/
 		/*	if(data.attachmentList.length > 0){
	 			$.each(data.attachmentList,function(i,item){
	 				$("#document-attachment dd").append(
	 				'<span class="glyphicon glyphicon-paperclip"></span> <a href="'+ item.url +'" class="text-danger">'+ item.title +'</a>'
	 				);
	 			});
	 		}else{
	 			$("#document-attachment dd").append('<span class="text-muted">No attachments</a>');
	 		}*/
 			
  		},
  		error: function(){
  			
  		}
  	});
  
  	$.post("abbr_findall.action",{},function(data){
  		$.each(data.ldvos,function(i,item){
  			abbrList[item.abbrword] = item.fullword;
  		});
  	});

  	$.post("documents_findAllType.action",{},function(data){
  		$.each(data.docTypeLists,function(i,item){
  			$("#select-type").append('<option value="'+ item.type +'">'+ item.type +'</option>');
  		});
  		loadExtraAttr($("#select-type option:first").val());
  		$("#select-type").change(function(){
  			loadExtraAttr($("#select-type").val());
  		});
  	});
  	
  	loadExtraAttr = function(type){
		var param = "type=" + type;
		$.post("documents_findDocType.action",param,function(data){
			var text = "";
 			$.each(data.ldas,function(i,extra){
 				text += '<div class="form-group">'+
			    				'<label for="input'+ extra.attribute +'" class="col-sm-2 control-label">'+ extra.attribute +
			    				'</label><div class="col-sm-5">'+
			      				'<input type="text" class="form-control" name="'+ extra.attribute.toLowerCase() +'" id="input'+ extra.attribute +'">'+
			    				'</div></div>';
 			});
 			$("#extra-form").html(text);
		});
	};
  
  	$("#update-submit-btn").click(function(){
  		$("#upload-form").submit();
  		alert('Upload Successfully!');
  	});
  	
  	$("#simple-search-btn").click(function(){
  		var param = "title=" + $("#simple-query").val();
  		$.ajax({
  			url: "documents_simpleSearch.action",
  			data: param,
  			dataType: "json",
  			type: "POST",
  			success: function(docData){
	  			if(docData.docLists.length > 0){
	  				var text = "";
	  				var refText = "<option value=\"\">Add Reference</option>";
				  	$.post("ref_findall.action",{},function(refData){
				  		$.each(refData.ldvos,function(i,item){
				  			refText += '<option value="' + item.type +'">'+ item.type +'</option>';
				  		});
	  					$.each(docData.docLists,function(i,item){
							text += '<div class="w100p mydoc-item">'+
					      		'<div class="ofh"><a href="javascript:loadDocPage('+ item.id +')" class="left doc-title text-danger">'+ item.title +
					      		'</a>'+
					      		'<select data-id="'+ item.id +'" class="right ref-select">'+
					      		refText +
					      		'</select>'+
					      		'</div>'+
					      		'<div class="doc-author h5 text-muted">' + item.author +'</div>'+
					      		'<div class="doc-abstract h5">'+ item.abstracts +
								'</div></div>';
						});
						$("#search-result-container").removeClass('hide');
						$("#search-result-panel").html(text);
						var refCount = 0;
						$(".ref-select").change(function(){
							var desId = $(this).attr('data-id');
							if($(this).val() != ""){
								refList.push([desId,$(this).val()]);
								$("#upload-form").append('<input type="hidden" name="refList['+ refCount +'].ref_type" value="'+ $(this).val() +'">');
								$("#upload-form").append('<input type="hidden" name="refList['+ refCount +'].des_did" value="'+ desId +'">');
								refCount += 1;
								$("#ref-selected-panel").removeClass("hide");
								$("#ref-selected-list").append('<div class="w100p mydoc-item">'+
					      		'<div class="ofh"><a href="javascript:loadDocPage('+ desId +')" class="left doc-title text-danger">'+ $(this).parent().find('a').html() +
					      		'</a>'+
					      		'<div class="right text-muted">'+ $(this).val() +'</div>'+
								'</div></div>');
								$(this).parent().parent().remove();
							}
						});
					});
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
	
	$("#draft-submit-btn").click(function(){
		var param = "";
		for (var i=0; i < refList.length; i++){
			param += "refList["+ i +"].ref_type=" + refList[i][1] +
					 "&refList["+ i +"].des_did=" + refList[i][0] + "&";
		}
		param = param.substring(0, param.length - 1);
		console.log(param);
		$.post("document_test.action",param,function(){});
	});
	
	$("#inputPublisher").keyup(function(){
		var key = $(this).val();
		if(key in abbrList){
			$("#publisher-dropdown-btn").next('ul').html('<li><a href="javascript:" id="abbr-'+ abbrList[key] +'">'+ abbrList[key] +'</a></li>');
			$("#publisher-dropdown-btn").click();
			$("#abbr-"+abbrList[key]).click(function(e){
				$("#inputPublisher").val($(this).html());
			});
		}
	});
	
	var fileCount = 0;
	$("#add-file-btn").click(function(){
		fileCount += 1;
		$(this).parent().before('<input type="file" class="form-control-static" name="image['+ fileCount +']">');
	});
 
  });
</script>

<div class="panel panel-success mh400">
  <div class="panel-heading">Edit document</div>
  <div class="panel-body">
  	<div class="panel-group" id="accordion">
  	  <form action="document_addDocument.action" method="post" id="upload-form" autocomplete="off" enctype="multipart/form-data">
  	  <div class="panel panel-default">
        <div class="panel-heading">
          <h4 class="panel-title">
            <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
            Basic Info
            </a>
          </h4>
    	</div>
        <div id="collapseOne" class="panel-collapse collapse in">
          <div class="panel-body">
		    <div class="form-horizontal">
			  <div class="form-group">
			    <label for="select-type" class="col-sm-2 control-label">Doc Type</label>
			  	<div class="col-sm-3">
					<select id="select-type" class="form-control" name="type">
					</select>
				</div>
			  </div>
			  <div class="form-group">
			    <label for="inputTitle" class="col-sm-2 control-label">Title</label>
			    <div class="col-sm-6">
			      <input type="text" class="form-control" id="inputTitle" name="title">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="inputAuthor" class="col-sm-2 control-label">Author</label>
			    <div class="col-sm-6">
			      <input type="text" class="form-control" id="inputAuthor" name="author">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="inputYear" class="col-sm-2 control-label">Publish Year</label>
			    <div class="col-sm-2">
			      <input type="text" class="form-control" id="inputYear" name="year">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="inputPage" class="col-sm-2 control-label">Pages</label>
			    <div class="col-sm-2">
			      <input type="text" class="form-control" id="inputPage" name="pages">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="inputAbstract" class="col-sm-2 control-label">Abstract</label>
			    <div class="col-sm-10">
			      <textarea row="5" class="form-control" id="inputAbstract" name="abstracts" placeholder="Please input document abstract..."></textarea>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="inputKeywords" class="col-sm-2 control-label">Keywords</label>
			    <div class="col-sm-8">
			      <input type="text" class="form-control" id="inputKeywords" name="keywords">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="inputPublisher" class="col-sm-2 control-label">Publisher</label>
			    <div class="col-sm-8">
			      <input type="text" class="form-control" id="inputPublisher" name="publisher">
			      <button id="publisher-dropdown-btn" class="hide" data-toggle="dropdown"></button>
			      <ul class="col-sm-11 ml15 dropdown-menu" role="menu"></ul>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="inputUrl" class="col-sm-2 control-label">URL</label>
			    <div class="col-sm-8">
			      <input type="text" class="form-control" id="inputUrl" name="url">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="inputFile" class="col-sm-2 control-label">File input</label>
			    <div class="col-sm-8">
			    	<input type="file" class="form-control-static" id="inputFile" name="image[0]" size="16">
			    	<div class="mt10 form-control-static"><a id="add-file-btn" href="javascipt:void()"><span class="glyphicon glyphicon-plus-sign"></span> Add File</a></div>
			    </div>
			  </div>
			</div>
      	  </div>
        </div>
  	  </div>
      <div class="panel panel-default">
    	<div class="panel-heading">
      	  <h4 class="panel-title">
        	<a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">
            Extra Info
        	</a>
      	  </h4>
    	</div>
    	<div id="collapseTwo" class="panel-collapse collapse">
      	  <div class="panel-body">
      	    <div id="extra-form" class="form-horizontal">
      	    </div>
      	  </div>
    	</div>
  	  </div>
  	  
  	  <div class="panel panel-default">
    	<div class="panel-heading">
      	  <h4 class="panel-title">
        	<a data-toggle="collapse" data-parent="#accordion" href="#collapseThree">
            Related Documents
        	</a>
      	  </h4>
    	</div>
    	<div id="collapseThree" class="panel-collapse collapse">
      	  <div class="panel-body">
      	  	<div id="ref-selected-panel" class="hide">
		    	<div class="w100p h4">Selected Documents</div>
		    	<div id="ref-selected-list"></div>
		  	</div>
      	  	<div class="input-group mt30">
			  <input type="text" class="form-control" placeholder="search documents" id="simple-query" value="">
			  <div class="input-group-btn">
				<div id="simple-search-btn" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></div>
			  </div>
			</div>
      	  	<div id="search-result-container" class="mt30 hide">
		    	<div class="w100p h4">Search Results</div>
		    	<div id="search-result-panel"></div>
		  	</div>
      	  </div>
    	</div>
  	  </div>
  	  </form>
	</div>
	
	<div class="center mt30">
      <button id="update-submit-btn" class="btn btn-default w150">Update Document</button>
	</div>
	
  </div>
</div>	  