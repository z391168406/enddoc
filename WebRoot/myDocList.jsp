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

	$.post("documents_findDocsByUser.action",{},function(data){
		var mydocTxt = "";
		var mydraftTxt = "";
		$.each(data.docLists,function(i,item){
			if(item.is_draft){
				mydocTxt += '<div class="w100p mydoc-item">'+
				      		'<div><a href="javascript:loadDocPage('+ item.id +')" class="doc-title text-danger">'+ item.title +'</a></div>'+
				      		'<div class="doc-author h5 text-muted">' + item.author +'</div>'+
				      		'<div class="doc-abstract h5">'+ item.abstracts +
							'</div></div>';
			}else{
				mydraftTxt += '<div class="w100p mydoc-item">'+
			      		'<div><a href="javascript:loadDocPage('+ item.id +')" class="doc-title text-danger">'+ item.title +'</a></div>'+
			      		'<div class="doc-author h5 text-muted">' + item.author +'</div>'+
			      		'<div class="doc-abstract h5">'+ item.abstracts +
						'</div></div>';
			}
			$(".mydoc-draft-container").html(mydocTxt);
			$(".mydoc-uploaded-container").html(mydraftTxt);
		});
	},"json");

});
</script>

<div class="panel panel-success mh400">
  <div class="panel-heading">My Document List</div>
  <div class="panel-body">
  
	<div class="panel-group" id="accordion">
	  <div class="panel panel-default">
	    <div class="panel-heading">
	      <h4 class="panel-title">
	        <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
	          Uploaded Documents
	        </a>
	      </h4>
	    </div>
	    <div id="collapseOne" class="panel-collapse collapse in">
	      <div class="panel-body mydoc-uploaded-container"></div>
	    </div>
	  </div>
  </div>
</div>	  