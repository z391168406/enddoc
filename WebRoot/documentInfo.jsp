<script>
  $(document).ready(function(){
  	var docId = <% out.print(request.getParameter("id")); %>
  	var detailAttrList = [];
	
  	$.ajax({
  		type: "post",
  		dataType: "json",
  		url: "documents_findDocInfo.action",
  		data: "id=" + docId,
  		success: function(data){
  			/* load basic info*/
  			var docInfo = data.docLists[0];
  			$("#doc-title .left").html(docInfo.title);
  			$("#doc-author").append(docInfo.author);
  			$("#doc-year").html(docInfo.year);
  			$("#doc-pages").html(docInfo.pages);
  			$("#doc-publisher").html(docInfo.publisher);
  			$("#doc-keywords").html(docInfo.keywords);
  			$("#doc-url").html('<a class="text-danger" href="'+ docInfo.url +'">'+ docInfo.url +'</a>');
  			$("#document-abstract dd").html(docInfo.abstracts);
 			
 			/*load attachments*/
 			if(data.attachmentList.length > 0){
	 			$.each(data.attachmentList,function(i,item){
	 				$("#document-attachment dd").append(
	 				'<span class="glyphicon glyphicon-paperclip"></span> <a href="'+ item.url +'" class="text-danger">'+ item.title +'</a>'
	 				);
	 			});
	 		}else{
	 			$("#document-attachment dd").append('<span class="text-muted">No attachments</a>');
	 		}
	 		/* load src_reference*/
	 		if(data.refList.length > 0){
	 			$.each(data.refList,function(i,item){
	 				$("#document-src-related").append('<dd><a href="loadDoc('+ item.des_did +')" class="text-danger left">'+ item.des +'</a><span class="relation-type right text-muted">'+ item.ref_type +'</span></dd><br>')
	 			});
	 		}
	 		
	 		/* load des_reference*/
	 		if(data.refedList.length > 0){
	 			$.each(data.refedList,function(i,item){
	 				$("#document-des-related").append('<dd><a href="loadDoc('+ item.src_did +')" class="text-danger left">'+ item.src +'</a><span class="relation-type right text-muted">'+ item.ref_type +'</span></dd><br>')
	 			});
	 		}
 			
 			/* load tags*/
 			if(data.tagList.length > 0){
	 			$.each(data.tagList,function(i,item){
	 				$("#tag-panel dd").append('<span class="left label label-info">' + item.title + '</span>');
	 			});
	 		}else{
	 			$("#tag-panel dd").append('<span class="text-muted">No tags</a>');
	 		}
 			
 			/* load rate*/
 			var rateLevel = [0,0,0,0,0];
 			var rateAmount = 0;
 			var rateSum = 0;
 			var rateAvg = 0;
 			$.each(data.rateList,function(i,item){
 				rateAmount += item.amount;
 				rateSum += item.amount * item.score;
 				rateLevel[item.score-1] = item.amount;
 			});
 			if (rateAmount > 0)
	 			rateAvg = rateSum / rateAmount * 2;
	 			
		    var fullWidth = 100;
		    var bigstarNum = 10 - Math.floor(rateAvg);
		    $("#rate-amount").html(rateAmount);									//评分人数
		    $("#rate-avg").html(rateAvg.toFixed(1));							//平均评分
		    $(".bigstar").css({"backgroundPosition":"0px -"+ bigstarNum * 14 +"px"});	//平均几颗星
		    
		    for(var i = 4; i >= 0; i--){
		    	var ratio = 0;
		    	if (rateAmount > 0)
		    		ratio = rateLevel[4-i] / rateAmount;
		    	$(".star-power:eq("+i+")").width(ratio * fullWidth);					//各级评分长度
		    	$(".rate-ratio:eq("+i+")").html((ratio * 100).toFixed(1));				//各级评分百分比
		    }
 			
 			/* load comment*/
 			loadBriefComment();
 			loadDetailComment();
 			loadDetailDraft();
  		},
  		error: function(){
  			
  		}
  	});
  	
  	$("#doc-title .btn").click(function(){
		$.ajax({  
	            type: "post",
	            dataType: "text",
	            data: "id="+docId,
	            url: "editDoc.jsp",
	            success: function(data){$("#content-container").html(data);},
	            error: function(data){}
       	});  
  	});
 	
  	$.post("detailComment_findallAttr",{},function(data){
  		var text = "";
  		$.each(data.ldvos,function(i,item){
  			detailAttrList.push(item.attribute);
  			text += '<div class="form-group">' +
		      		'<label>' + item.attribute +' :</label>' +
			  		'<textarea row="1" id="textarea-'+ item.attribute +'" class="form-control"></textarea></div>';
  		});
  		$("#detail-comment-form").html(text);
  		
	  	$.post("detailComment_showDraft.action","did="+docId,function(data){
			var str = data.list[0];
			var items = str.split("&");
			for (var i = 2; i < items.length - 1; i++){
				var attr = items[i].split("=")[0];
				$("#textarea-"+attr).val(items[i].split("=")[1]);
			}
	  	});
  	});
  	
  	loadBriefComment = function(){
  		//$("#comment-list").html('');
	  	$.ajax({
	  		url: "briefComment_queryByDid.action",
	  		type: "POST",
	  		data: "did=" + docId,
	  		dataType: "json",
	  		success: function(data){
	  			var text = "";
	  			$.each(data.list,function(i,item){
	  				console.log(item.create_time);
	  				text += '<div class="panel panel-default"><div class="panel-heading ofh"><span class="left">'+item.username+'</span>'+
	  							   '<span class="right">'+item.create_time+'</span></div>'+
								 '<div class="panel-body">'+ item.content +
								 '</div></div>';
	  			});
	  			$("#comment-list").append(text);
	  		},
	  		error: function(data){
	  		}
	  	});
	  };
	 
	  loadDetailComment	= function(){
	  	 $.ajax({
	  		url: "detailComment_queryByDid.action",
	  		type: "POST",
	  		data: "did=" + docId,
	  		dataType: "json",
	  		success: function(data){
	  			var text = "";
	  			$.each(data.list,function(i,str){
	  				items = str.split("&");
	  				
	  				text += '<div class="panel panel-default"><div class="panel-heading ofh">'+
					  		'<span class="left">'+ items[1].split("=")[1] +'</span>'+
						  	'<span class="right">'+ items[items.length-1].split("=")[1] +'</span></div>'+
							'<div class="panel-body">';
					for(var i = 2; i < items.length-1;i++){		
						text +=	'<div class="ofh">'+
								'<label class="w100 left">'+ items[i].split("=")[0] +':</label>'+
								'<div class="col-sm-10 left">'+ items[i].split("=")[1] +'</div></div>';
	  				}
	  				text += '</div></div>';
	  			});
	  			$("#comment-list").append(text);
	  		},
	  		error: function(data){
	  		
	  		}
	  	});
  	};

<<<<<<< .mine
  	loadDetailDraft = function(){
	  	 $.ajax({
	  		url: "detailComment_showDraft.action",
	  		type: "POST",
	  		data: "did=" + docId,
	  		dataType: "json",
	  		success: function(data){
	  			var text = "";
	  			$("#comment-list").append(text);
	  		},
	  		error: function(data){
	  		
	  		}
	  	});
  	};
  	
=======
	loadDefaultTag = function(){
		$.post("tag_findall.action",{},function(data){
	  		var text = "";
	  		$.each(data.ldvos,function(i,item){
	  			text += '<button class="btn btn-info btn-xs left m2">' + item.title +'</button>';
	  		});
	  		$("#default-tag-panel").html(text);
	  		 	
		  	$("#default-tag-panel button").click(function(){
		  		$(this).attr('disabled',"true");
		  		var lastTxt =  $("#inputTag").val();
		  		var txt = ( lastTxt=="" ? "" : lastTxt + "," ) + $(this).html();
		  		$("#inputTag").val(txt);
		  	});
  		});
	};
>>>>>>> .r194
    
    var lastRate = 0;
    
    $("#input-rate li").hover(function(){
    	var index = $(this).index() + 1;
    	$("#input-rate li").css({"backgroundPosition":"0px 0px"});
    	$("#input-rate li:lt("+ index +")").css({"backgroundPosition":"0px -28px"});
    },function(){
    	$("#input-rate li").css({"backgroundPosition":"0px 0px"});	
    	$("#input-rate li:lt("+ lastRate +")").css({"backgroundPosition":"0px -28px"});
    });
    
    $("#input-rate li").click(function(){
    	var rateText = ["Poor","Fair","Average","Good","Excellent"];
    	var index = $(this).index();
    	lastRate = index + 1;
    	$("#rate-text").html(rateText[index]);
    	$.ajax({
    		url: "rate_addRate.action",
    		type: "POST",
    		data: "did=" + docId + "&score=" + (index + 1),
    		dataType: "json",
    		success: function(data){
    			
    		},
    		error: function(data){
    		
    		}
    	});
    });
    
 	/*Comment Nav*/
 	$("#comment-nav a:first").tab('show');
 	$("#comment-nav a").click(function (e) {
 		e.preventDefault();
  		$(this).tab('show');
	});
	
  	$("#brief-submit").click(function(){
  		var param = "did="+ docId + "&content=" + $("#brief-comment-text").val() + "&isDraft=" + $("#brief-draft").is(':checked');
  		$.ajax({
  			url: "briefComment_addBriefComment.action",
  			dataType: "json",
  			data: param,
  			type: "POST",
  			success: function(data){
  				$.ajax({  
		            type: "post",
		            dataType: "text",
		            data: "id="+docId,
		            url: "documentInfo.jsp",
		            success: function(data){$("#content-container").html(data);},
		            error: function(data){}
       			});  
  			},
  			error: function(data){
  			
  			}
  		});
  	});
  	
  	$("#detail-submit").click(function(){
  		var param = "did="+ docId +"&isDraft="+ $("#detail-draft").is(':checked') +"&form=";
  		console.log(param);
  		for (var i = 0; i < detailAttrList.length; i++){
  			param += detailAttrList[i] + "=" + $("#detail-comment-form textarea:eq("+ i +")").val() + "/";
  		}
  		$.ajax({
  			url: "detailComment_addDetailComment.action",
  			dataType: "json",
  			data: param,
  			type: "POST",
  			success: function(data){
  				$.ajax({  
		            type: "post",
		            dataType: "text",
		            data: "id="+docId,
		            url: "documentInfo.jsp",
		            success: function(data){$("#content-container").html(data);},
		            error: function(data){}
       			});  
  			},
  			error: function(data){
  			
  			}
  		});
  	});
  	
  	$.post("briefComment_showDraft.action","did="+docId,function(data){
  		$("#brief-comment-text").val(data.list[0].content);
  	});
  	
  	loadDefaultTag();
  	$("#input-tag-container").animate({opacity:'toggle'});
  	$("#input-tag-label").click(function(){
  		$("#input-tag-container").animate({opacity:'toggle'});
  	});
  	$("#tag-submit-btn").click(function(){
  		var tagText = $("#inputTag").val();
  		var param = "did=" + docId + "&title=" + tagText;
  		$.ajax({
  			url: "tag_addTag.action",
  			type: "POST",
  			data: param,
  			dataType: "json",
  			success: function(data){
  				$("#input-tag-container").animate({opacity:'toggle'});
  				var tagList = tagText.split(",");
  				for (key in tagList){
	 				$("#tag-panel dd").append('<span class="left label label-info">' + tagList[key] + '</span>');
	 			}
  			},
  			error: function(data){}
  		});
  	});
  
});

</script>

<div class="panel panel-success mh400">
  <div class="panel-heading">Document Details</div>
  <div class="panel-body">
  	<div id="document-left" class="left small ofh w500">
  	  <div id="doc-title" class="ofh">
  	  	<h4 class="left"></h4>
  	  	<a href="javascript:void(0)" class="btn btn-xs btn-default right">Edit <span class="glyphicon glyphicon-pencil"></span></a>
	  </div>
  	  <p id="doc-author" class="text-muted">by</p>
	  <dl>
  		<dt class="left">Year</dt><dd id="doc-year" class="ml15 left text-danger"></dd>
	  </dl>
	  <dl>
  		<dt class="left">Pages</dt><dd id="doc-pages" class="ml15 left text-danger"></dd>
	  </dl>
	  <dl>
  		<dt class="left">Publisher</dt><dd id="doc-publisher" class="ml15 left text-danger"></dd>
	  </dl>
	  <dl>
  		<dt class="left">Keywords</dt><dd id="doc-keywords" class="ml15 left text-danger"></dd>
	  </dl>
	  <dl>
  		<dt class="left">URL</dt><dd id="doc-url" class="ml15 left text-danger"></dd>
	  </dl>
	  <br>
	  <dl id="document-abstract">
  		<dt class="h4 borderb-c mb5">Abstract</dt>
  		<dd></dd>
	  </dl>
	  <br>
	  <dl id="document-attachment">
	  	<dt class="h4 borderb-c mb5">Attachments</dt>
	    <dd></dd>
	  </dl>
	  <br>
	  <dl id="document-src-related">
	  	<dt class="h4 borderb-c mb5">Cite</dt>
	  </dl>
	  <dl id="document-des-related">
	  	<dt class="h4 borderb-c mb5">Cited by</dt>
	  </dl>
  	</div>
  	<!-- end of document-left -->
  	
  	<div id="document-right" class="right col-md-4">
  	  <div id="rate-panel">
  	  	<span class="bigstar left"></span>
  	  	<span class="left rate-num"><strong id="rate-avg"></strong></span>
  	  	<br>
  	  	<span class="text-primary small">( voted by <span id="rate-amount"></span> readers )</span>
  	  	<br>
  	  	<span class="star star5"></span>
  	  	<span class="star-power" style="width:20px;"></span><span class="rate-ratio"></span>%
  	  	<br>
  	  	<span class="star star4"></span>
  	  	<span class="star-power" style="width:20px;"></span><span class="rate-ratio"></span>%
  	  	<br>
  	  	<span class="star star3"></span>
  	  	<span class="star-power" style="width:20px;"></span><span class="rate-ratio"></span>%
  	  	<br>
  	  	<span class="star star2"></span>
  	  	<span class="star-power" style="width:20px;"></span><span class="rate-ratio"></span>%
  	  	<br>
  	  	<span class="star star1"></span>
  	  	<span class="star-power" style="width:20px;"></span><span class="rate-ratio"></span>%
  	  	<br>
  	  </div>
  	  <div id="tag-panel" class="mt30">
  	  	<dl id="tag-panel">
  		  <dt class="h4">Tags</dt>
  		  <dd></dd>
	    </dl>
  	  
  	  </div>
  	</div>
  	<!-- end of document-right -->
  </div>
</div>

<div class="panel panel-success">
  <div class="panel-heading">Comments</div>
  <div class="panel-body">
  	<div id="comment-list">
	</div>
	<div class="h4 borderb-c">Leave comments</div>
	<div class="mt15 ofh">
	  <span class="left">Rate : </span>
	  <ul id="input-rate" class="left list-unstyled">
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
	  </ul>
	  <span id="rate-text" class="left ml15 label label-info"></span>
	</div>
	
	<div class="mt15 w100p ofh">
	  <div id="input-tag-label" class="w100 left"><a href="javascript:void(0);">+Add Tag</a></div>
	  <div id="input-tag-container" class="left ofh col-xs-9 border-d">
	  	<input type="text" class="form-control" id="inputTag" name="inputTag">
	  	<div class="mt10">
	  		<div class="w100 left ml15 text-primary">default tags:</div>
	  		<div id="default-tag-panel" class="col-xs-8 left ofh"></div>
	  		<div class="left"><button id="tag-submit-btn" class="btn btn-default btn-xs">Submit</button></div>
	  	</div>
	  
	  </div>
	</div>
		
	<div class="w100p mt15">
	  <ul class="nav nav-tabs" id="comment-nav">
 		<li><a href="#brief-comment" data-toggle="tab">Brief Comment</a></li>
  		<li><a href="#detail-comment" data-toggle="tab">Detail Comment</a></li>
	  </ul>
	  <div class="tab-content">
	    <div class="tab-pane active fade in comment-panel" id="brief-comment">
	      <textarea row="5" id="brief-comment-text" class="form-control" placeholder="Please input comment..."></textarea>
     	  <div class="mt10"><input type="checkbox" id="brief-draft"> Save as draft</div>
	      <buttom id="brief-submit" class="btn btn-default mt15">Submit</buttom>
	    </div>
	    <div class="tab-pane fade comment-panel" id="detail-comment">
		  <form role="form" id="detail-comment-form">
		  </form>
		  <div class="mt10"><input type="checkbox" id="detail-draft"> Save as draft</div>
		  <buttom id="detail-submit" class="btn btn-default mt15">Submit</buttom>
		</div>
	  </div>
	  
	</div>
  </div>
</div>


