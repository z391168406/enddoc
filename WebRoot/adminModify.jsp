<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理</title>
<link rel="stylesheet" type="text/css" media="screen"
	href="css/reset.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="css/admin-style.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="css/invalid.css" />
<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="js/simpla.jquery.configuration.js"></script>
<script type="text/javascript" src="js/facebox.js"></script>
<script type="text/javascript" src="js/jquery.wysiwyg.js"></script>
<script type="text/javascript" src="js/jquery.datePicker.js"></script>
<script type="text/javascript" src="js/jquery.date.js"></script>
<link rel="stylesheet" href="css/jquery.ui.all.css">
<script src="js/jquery-1.9.1.js"></script>
<script src="js/jquery.ui.core.js"></script>
<script src="js/jquery.ui.widget.js"></script>
<script src="js/jquery.ui.mouse.js"></script>
<script src="js/jquery.ui.draggable.js"></script>
<script src="js/jquery.ui.position.js"></script>
<script src="js/jquery.ui.resizable.js"></script>
<script src="js/jquery.ui.button.js"></script>
<script src="js/jquery.ui.dialog.js"></script>
<script src="js/jquery.ui.effect.js"></script>
<script src="js/jquery.ui.datepicker.js"></script>
<link rel="stylesheet" href="css/demos.css">

<script>
		function logout() {
			$.ajax({
				type : "POST",
				url : "logout.action",
				dataType : "json",
				success : function(data) {
					alert("注销成功");
					window.location.href = "index.jsp";
					},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("注销失败");
				}
			});
		}
		
	function loadUser() {
		var myName = "<%=session.getAttribute("username")%>";
		if (myName == "null") {
			alert("未登录，请先登录");
			window.location.href = "index.jsp";
		 }
	}
	/*收集窗口数据，并提交到服务器
	 */
	function savePassword() {
		var password = $("#password").val();
		var newPassword = $("#newPassword").val();
		var verify = $("#verify").val();
		if((password=="")||(newPassword=="")||(verify==""))
			alert("密码不能为空");
		else if(newPassword!=verify)
			alert("确认密码不匹配");
		else{
		var myName = "<%=session.getAttribute("username")%>";
			$.ajax({
				type : "POST",
				url : "user_savePassword.action",
				dataType : "json",
				data : "username=" + myName + "&password=" + $("#password").val()
						+ "&newPassword=" + $("#newPassword").val(),
				success : function(data) {
					alert("密码修改成功，正在刷新数据！");
					$("#password").val("");
					$("#newPassword").val("");
					$("#verify").val("");
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("原密码不正确，请修改后重试！");
				}
			});
		}
	}
	$(function() {
		$("#save").button().click(function() {
			savePassword();
		});
	});
	 window.onload = loadUser; 
</script>
</head>
<body>
	<div id="body-wrapper">

		<div id="sidebar">
			<div id="sidebar-wrapper">
				<h1 id="sidebar-title">
					<a href="#"></a>
				</h1>
				<a><img id="logo" src="images/logo.png" style="top: 40px; left: 0px; width: 220px; height: 56px;"/> </a>
				<div id="profile-links">
					您好, <a href="#" title="Edit your profile">管理员</a><br /> <br /> <a
						<a href="index.jsp" title="Sign Out">退出</a>
				</div>


				<ul id="main-nav">
					<li><a href="admin-index.jsp" class="nav-top-item no-submenu">用户管理</a>
					<li><a href="#" class="nav-top-item">文献配置</a>
						<ul>
							<li><a href="typeConfigure.jsp">文献种类配置</a></li>
							<li><a href="attributeConfigure.jsp">文献属性配置</a></li>
							<li><a href="relationshipConfigure.jsp">文献引用关系配置</a></li>
							<li><a href="abbreviationConfigure.jsp">文献缩写配置</a></li>
							<li><a href="evaluationConfigure.jsp">文献详细评价配置</a></li>
							<li><a href="labelConfigure.jsp">文献预定义标签配置</a></li>
						</ul>
					</li>

					<li><a href="#" class="nav-top-item current">个人设置 </a>
						<ul>
							<li><a href="adminModify.jsp" class="current">修改密码</a></li>
						</ul>
					</li>

				</ul>
			</div>
		</div>
		<!-- End #sidebar -->
		<div id="main-content">
			<h2>欢迎使用，个人设置</h2>
			<br> <br> <br>
			<div class="content-box">
				<!-- Start Content Box -->
				<div class="content-box-header">
					<h3>个人密码管理</h3>
					<ul class="content-box-tabs">
						<li><a href="#tab1" class="default-tab">密码</a>
						</li>
					</ul>
					<div class="clear"></div>
				</div>
				<!-- End .content-box-header -->
				<div class="content-box-content">
					<div class="tab-content default-tab" id="tab1">
						<form id="form">
							<fieldset>
								<!-- Set class to "column-left" or "column-right" on fieldsets to divide the form into columns -->
								<p>
									<label> 原密码 </label> <input class="text-input small-input"
										style="width: 15% !important" type="password" id="password"
										name="password" /> <br />
								</p>
								<p>
									<label> 新密码 </label> <input class="text-input small-input"
										style="width: 15% !important" type="password" id="newPassword"
										name="newPassword" /> <br />
								</p>
								<p>
									<label> 确认密码 </label> <input class="text-input small-input"
										style="width: 15% !important" type="password" id="verify"
										name="verify" /> <br />
								</p>
								<p>
									<input type="button" id="save" value="保存" />
								</p>
							</fieldset>
							<div class="clear"></div>
							<!-- End .clear -->
						</form>
					</div>
					<!-- End #tab1 -->
				</div>
				<!-- End .content-box-content -->
			</div>
		</div>
</body>