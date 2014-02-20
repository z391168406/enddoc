<script>
  $(document).ready(function(){
    loadDocType = function(){
	  	$.post("documents_findAllType.action",{},function(data){
	  		var typeText = "";
	  		$.each(data.docTypeLists,function(i,item){
	  			typeText += '<option tid="'+ item.id +'" value="'+ item.type +'">'+ item.type +'</option>';
		  		$("#doc-type-select").html(typeText);
	  		});
	  		$("#doc-type-select option").click(function(){
	  			loadExtraAttr($(this).val());
	  		});
	  	});	
	 };

	loadExtraAttr = function(type){
		var param = "type=" + type;
		$.post("documents_findDocType.action",param,function(data){
			var text = "";
 			$.each(data.ldas,function(i,extra){
 				text += '<option value="'+ extra.id +'">'+ extra.attribute +'</option>';
 			});
 			$("#doc-extra-select").html(text);
		});
	};
	 	 
	loadDetailCommentAttr = function(){
		$.post("comment_findallAttr.action",{},function(data){
			var text = "";
			$.each(data.ldvos,function(i,attr){
				text += '<option value="'+ attr.id +'">'+ attr.attribute +'</option>';
  			});
  			 $("#detail-comment-select").html(text);
		});
	};
	 
	loadDefaultTag = function(){
		$.post("tag_findall.action",{},function(data){
			var text = "";
			$.each(data.ldvos,function(i,tag){
				text += '<option value="'+ tag.id +'">'+ tag.title +'</option>';
  			});
  			 $("#default-tag-select").html(text);
		});
	};
	
	loadAbbr = function(){
		$.post("abbr_findall.action",{},function(data){
			var text = "";
			$.each(data.ldvos,function(i,pair){
				text += '<option value="'+ pair.abbrword +'">'+ pair.abbrword + ' - ' + pair.fullword +'</option>';
  			});
  			$("#abbr-select").html(text);
		});
	};
	 
	loadDocType();
	loadDetailCommentAttr();
	loadDefaultTag();
	loadAbbr();
  	
  	/* add button*/
  	$("#doc-type-add-btn").click(function(){
  		var param = "type=" + $(this).parent().next('input').val();
  		$.post("documents_addDocType.action",param,function(data){
  			loadDocType();
  		});
  	});
  	$("#doc-type-remove-btn").click(function(){
  		var param = "id=" + $("#doc-type-select :selected").attr('tid');
  		$.post("documents_delType.action",param,function(data){
  			loadDocType();
  		});
  	});
  	$("#doc-extra-add-btn").click(function(){
  		var param = "type=" + $("#doc-type-select").val() + "&attribute=" + $(this).parent().next('input').val();
  		$.post("documents_addDocAttribute.action",param,function(data){
  			loadExtraAttr($("#doc-type-select").val());
  		});
  	});
  	$("#doc-extra-remove-btn").click(function(){
  		var param = "id=" + $("#doc-extra-select").val();
  		$.post("documents_delAttribute.action",param,function(data){
  			loadExtraAttr($("#doc-type-select").val());
  		});
  	});
  	$("#detail-comment-add-btn").click(function(){
  		var param = "attribute=" + $(this).parent().next('input').val();
  		$.post("comment_addDetailCommentAttri.action",param,function(data){
  			loadDetailCommentAttr();
  		});
  	});
  	$("#detail-comment-remove-btn").click(function(){
  		var param = "id=" + $("#detail-comment-select").val();
  		$.post("comment_delDetailCommentAttri.action",param,function(data){
  			loadDetailCommentAttr();
  		});
  	});
  	$("#default-tag-add-btn").click(function(){
  		var param = "title=" + $(this).parent().next('input').val();
  		$.post("tag_addDefaultTag.action",param,function(data){
  			loadDefaultTag();
  		});
  	});
  	$("#default-tag-remove-btn").click(function(){
  		var param = "id=" + $("#default-tag-select").val();
  		$.post("tag_delDefaultTag.action",param,function(data){
  			loadDefaultTag();
  		});
  	});
  	$("#abbr-add-btn").click(function(){
  		var param = "abbrword=" + $("#abbr-input").val() + "&fullword=" + $("#full-input").val();
  		$.post("abbr_addAbbreviation.action",param,function(data){
  			loadAbbr();
  		});
  	});
  	$("#abbr-remove-btn").click(function(){
  		var param = "abbrword=" + $("#abbr-select").val();
  		$.post("abbr_delAbbreviation.action",param,function(data){
  			loadAbbr();
  		});
  	});
  	
 });
</script>

<div class="panel panel-success mh400">
  <div class="panel-heading">Document Configuration</div>
  <div class="panel-body">
    <div class="panel-group" id="accordion">
	  <div class="panel panel-default">
	    <div class="panel-heading">
	      <h4 class="panel-title">
	        <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
	          Document type
	        </a>
	      </h4>
	    </div>
	    <div id="collapseOne" class="panel-collapse collapse in">
	      <div class="panel-body">
	      
			<div id="doc-type-panel" class="col-xs-5">
				<p class="text-center">Document Type</p>
				<select multiple id="doc-type-select" class="form-control mh150"></select>
				<div class="mt10">
				  <div class="input-group input-group-sm col-xs-8 left-i">
				      <span class="input-group-btn">
				        <button id="doc-type-add-btn" class="btn btn-default" type="button"><span class="glyphicon glyphicon-plus"></span>Add</button>
				      </span>
				      <input type="text" class="form-control">
			      </div>
			      <button id="doc-type-remove-btn" type="button" class="right btn btn-default btn-sm"><span class="glyphicon glyphicon-remove"></span>Remove</button>
  				</div>
			</div>			
			<div id="doc-extra-panel" class="col-xs-5">
				<p class="text-center">Extra Attribute</p>
				<select multiple id="doc-extra-select" class="form-control mh150"></select>
				<div class="mt10">
				  <div class="input-group input-group-sm col-xs-8 left-i">
				      <span class="input-group-btn">
				        <button id="doc-extra-add-btn"  class="btn btn-default" type="button"><span class="glyphicon glyphicon-plus"></span>Add</button>
				      </span>
				      <input type="text" class="form-control">
			      </div>
			      <button id="doc-extra-remove-btn" type="button" class="right btn btn-default btn-sm"><span class="glyphicon glyphicon-remove"></span>Remove</button>
  				</div>
			</div>
			
	      </div><!-- end of panel-body -->
	    </div>
	  </div>
	  <div class="panel panel-default">
	    <div class="panel-heading">
	      <h4 class="panel-title">
	        <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">
	          Detail comment fields
	        </a>
	      </h4>
	    </div>
	    <div id="collapseTwo" class="panel-collapse collapse">
	      <div class="panel-body">
	      
	      	<div id="doc-extra-panel" class="col-xs-5">
				<select multiple id="detail-comment-select" class="form-control mh150"></select>
				<div class="mt10">
				  <div class="input-group input-group-sm col-xs-8 left-i">
				      <span class="input-group-btn">
				        <button id="detail-comment-add-btn" class="btn btn-default" type="button"><span class="glyphicon glyphicon-plus"></span>Add</button>
				      </span>
				      <input type="text" class="form-control">
			      </div>
			      <button id="detail-comment-remove-btn" type="button" class="right btn btn-default btn-sm"><span class="glyphicon glyphicon-remove"></span>Remove</button>
  				</div>
			</div>
	      	
	      </div><!-- end of panel-body -->
	    </div>
	  </div>
	  <div class="panel panel-default">
	    <div class="panel-heading">
	      <h4 class="panel-title">
	        <a data-toggle="collapse" data-parent="#accordion" href="#collapseThree">
	          Default tags
	        </a>
	      </h4>
	    </div>
	    <div id="collapseThree" class="panel-collapse collapse">
	      <div class="panel-body">
	      	<div id="default-tag-panel" class="col-xs-5">
				<select multiple id="default-tag-select" class="form-control mh150"></select>
				<div class="mt10">
				  <div class="input-group input-group-sm col-xs-8 left-i">
				      <span class="input-group-btn">
				        <button id="default-tag-add-btn" class="btn btn-default" type="button"><span class="glyphicon glyphicon-plus"></span>Add</button>
				      </span>
				      <input type="text" class="form-control">
			      </div>
			      <button id="default-tag-remove-btn" type="button" class="right btn btn-default btn-sm"><span class="glyphicon glyphicon-remove"></span>Remove</button>
  				</div>
			</div>
	      </div><!-- end of panel-body -->
	    </div>
	  </div>
	  <div class="panel panel-default">
	    <div class="panel-heading">
	      <h4 class="panel-title">
	        <a data-toggle="collapse" data-parent="#accordion" href="#collapseFour">
	          Abbreviation Mapping
	        </a>
	      </h4>
	    </div>
	    <div id="collapseFour" class="panel-collapse collapse">
	      <div class="panel-body">
	      	<div id="abbr-panel" class="col-xs-6">
				<select multiple id="abbr-select" class="form-control mh150"></select>
				<div class="mt10">
			      <div class="ofh">
				      <button id="abbr-add-btn" class="left btn btn-default btn-sm" type="button"><span class="glyphicon glyphicon-plus"></span>Add</button>
					  <input id="abbr-input" type="text" class="left form-control input-sm" placeholder="Abbreviation">
					  <input id="full-input" type="text" class="left form-control input-sm" placeholder="Full Word">
			          <button id="abbr-remove-btn" type="button" class="right btn btn-default btn-sm"><span class="glyphicon glyphicon-remove"></span>Remove</button>
  				</div>
			</div>
	      </div><!-- end of panel-body -->
	    </div>
	  </div>
	</div>
  </div>
</div>	  