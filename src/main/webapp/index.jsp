<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>���¼</title>
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
	<link href="css/login.css" rel="stylesheet" type="text/css" />
    <script src="js/jquery-1.7.1.js" type="text/javascript"></script>
    <script src="js/bootstrap.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(document).ready(function()
		{
			$("#register").onclick(function(){
				
			});
		});
	</script>
	<style>
		body{background:url(img/bg.jpg) center 0 no-repeat;}
		.loginBox {background:rgba(255,255,255,.5);}
	</style>
  </head>
  
  <body>
    <form action="login.do" method="post">
		<div class="container">
			<section class="loginBox row-fluid">
				<section class="span7 left">
				<h2>
					��¼
				</h2>
				<p>
					<input type="text" name="username" class="span11"/>
				</p>
				<p>
					<input type="password" name="password" class="span11"/>
				</p>
					<section class="row-fluid">
						<section class="span8 lh30">
					      <span class="input-group-addon">
					        <input type="radio" name="identity" checked="checked" value="users">�û�
					        <input type="radio" name="identity" value="admin">����Ա
					      </span>
						</section>
						<section class="span1">
						<input type="submit" value="��¼ " class="btn btn-primary">
						</section>
					</section>
				</section>
				<section class="span5 right">
				<h2>
					û���ʻ���
				</h2>
					<section>
					<input type="button" value=" ע�� " class="btn" onclick="javascript:location.href='register.jsp'">
					</p>
					</section>
				</section>
			</section>
			<!-- /loginBox -->
		</div>
		</form>
  </body>
</html>
