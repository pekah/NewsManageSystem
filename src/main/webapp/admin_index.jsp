<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=GBK">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <base href="<%=basePath%>">
    <title>管理员主页</title>
	<link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/jquery-1.7.1.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
	<style type="text/css">
		#newsBtns{float:left; width:10%; position: absolute;top: 70px;display: none;}
		#cateBtns{float:left; width:10%; position: absolute;top: 180px;display: none;}	
		#revBtns{float:left; width:10%; position: absolute;top: 290px;display: none;}
		#addNews{float:right; width:70%; position: absolute;top: 70px;left: 200px;display: none;}
		#removeNews{float:right; width:70%; position: absolute;top: 70px;left: 200px;display: none;}
		#addCate{float:right; width:70%; position: absolute;top: 180px;left: 200px;display: none;}
		#removeCate{float:right; width:70%; position: absolute;top: 215px;left: 200px;display: none;}
		#addUsers{float:right; width:70%; position: absolute;top: 285px;left: 200px;display: none;}}
	</style>
	<script type="text/javascript">
		$(document).ready(function()
		{
			$("#addNewsBtn").click(function(){
				$("#addNews").show();
				$("#removeNews").hide();
				//通过ajax获取所有的栏目
				$.ajax({
				    url : "getCategorys.do",
				    type : "POST",
				    contentType : "application/json",
				    dataType : "json",
				    //data:JSON.stringify(dataValue),
				    success : function(data) {
				        var datas;
				   		for(var i in data)
				   		{
				   			datas = data[i];
				   		}
				    	for(var i in datas)
				    	{
				    		$("#catesSelect").append("<option>"+ datas[i] +"</option>");
				    	}
					},
				    error : function(data) {
				        alert("栏目为空，请先添加栏目");
				    }
				});				
			});
			$("#removeNewsBtn").click(function(){
				$("#addNews").hide();
				$("#removeNews").show();
			});			
			$("#addCateBtn").click(function(){
				$("#removeCate").hide();
				$("#addCate").show();
			});
			$("#removeCateBtn").click(function(){
				$("#addCate").hide();
				$("#removeCate").show();
			});			
			$("#addUsersBtn").click(function(){
				$("#addUsers").show();
			});				
			
			
		});
		function newsManage()
		{
			$("#newsBtns").show();
			$("#cateBtns").hide();
			$("#revBtns").hide();
			$("#addCate").hide();
			$("#removeCate").hide();
		};
		function cateManage()
		{
			$("#newsBtns").hide();
			$("#cateBtns").show();
			$("#revBtns").hide();
			$("#addNews").hide();
			$("#removeNews").hide();
		};
		function usersManage()
		{
			$("#newsBtns").hide();
			$("#cateBtns").hide();
			$("#revBtns").show();
			$("#addNews").hide();
			$("#addCate").hide();
			$("#removeNews").hide();
			$("#removeCate").hide();
		};		
	</script>
  </head>
  
  <body>
	<!--导航条开始-->
	<nav class="navbar navbar-default navbar-static-top" role="navigation" id="navigation">
	  <div class="navbar-header" id="bs-example-navbar-collapse-1">
		<ul class="nav navbar-nav">
		  <li class="active"><a href="#">管理员主页</a></li>
		  <li><a href="javascript:void(0)" onclick="newsManage()">新闻管理</a></li>
		  <li><a href="javascript:void(0)" onclick="cateManage()">栏目管理</a></li>
		  <li><a href="javascript:void(0)" onclick="usersManage()">用户管理</a></li>
		  <li><a href="javascript:void(0)">欢迎你：<%=session.getAttribute("name") %></a></li>		  
		</ul>
	  </div>
	 </nav>
	 <!--导航条结束-->
	 <!--新闻管理按钮组开始-->
	 <div id="newsBtns">
		<button type="button" class="btn btn-info" id="addNewsBtn">添加新闻</button><br/>
		<button type="button" class="btn btn-info" id="removeNewsBtn">删除新闻</button><br/>
		<button type="button" class="btn btn-info">修改新闻</button><br/>
	 </div>
     <!--新闻管理按钮组结束-->
	 <!--栏目管理按钮组开始-->
	 <div id="cateBtns">
		<button type="button" class="btn btn-info" id="addCateBtn">添加栏目</button><br/>
		<button type="button" class="btn btn-info" id="removeCateBtn">删除栏目</button><br/>
	 </div>
     <!--栏目管理按钮组结束-->
	 <!--用户管理按钮组开始-->
	 <div id="revBtns">
		<button type="button" class="btn btn-info" id="addUsersBtn">添加用户</button><br/>
		<button type="button" class="btn btn-info" id="removeUsersBtn">删除用户</button><br/>
		<button type="button" class="btn btn-info" id="modifyUsersBtn">修改用户</button><br/>
	 </div>
     <!--用户管理按钮组结束-->
     
     <!-- 添加新闻版块开始 -->
     <div id="addNews">
		 <form role="form" action="addNews.do" method="post">
		  <div class="form-group">
		   <label>新闻标题：</label>
		    <input type="text" class="form-control" name="title" placeholder="请输入新闻标题"/>
		  </div>
		  <div class="form-group">
		    <span class="label label-success">作者</span>
		    <input type="text" class="form-control" name="author" placeholder="请输入作者名"/>
		    <span class="label label-success">编辑</span>
		    <input type="text" class="form-control" name="editor" placeholder="请输入编辑名"/>
		    <span class="label label-success">栏目</span>
			<select id="catesSelect" name="category">
			</select>
			<label>新闻内容：</label>
			<textarea class="form-control" name="content" rows="3" style="width: 769px; height: 407px;"></textarea>
		  </div>
		  <button type="submit" class="btn btn-default">提交</button>
		</form>    	
     </div>
     <!-- 添加新闻版块结束 -->
     
     <!-- 删除新闻版块开始 -->
     <div id="removeNews">
      	<form role="form" method="post" action="removeNews.do">
	     	<span class="label label-success">新闻名</span>
	     	<input type="text" class="form-control" name="newsTitle" placeholder="请输入新闻名"/>
	     	<button type="submit" class="btn btn-danger">删除</button>
     	</form>    	
     </div>
     <!-- 删除新闻版块结束 -->
          
     <!-- 添加栏目版块开始 -->
     <div id="addCate">
     	<form role="form" method="post" action="addCate.do">
	     	<span class="label label-success">栏目名</span>
	     	<input type="text" class="form-control" name="cateName" placeholder="请输入栏目名"/>
	     	<button type="submit" class="btn btn-primary">添加</button>
     	</form>
     </div>
     <!-- 添加栏目版块结束 -->
     <!-- 删除栏目版块开始 -->
     <div id="removeCate">
      	<form role="form" method="post" action="removeCate.do">
	     	<span class="label label-success">栏目名</span>
	     	<input type="text" class="form-control" name="cateName" placeholder="请输入栏目名"/>
	     	<button type="submit" class="btn btn-danger">删除</button>
     	</form>    	
     </div>
     <!-- 删除栏目版块结束 -->
     
     <!-- 添加用户版块开始 -->
     <div id="addUsers">
     	<form role="form" method="post" action="addUsers.do">
	     	<span class="label label-success">用户名</span>
	     	<input type="text" class="form-control" name="username" placeholder="请输入用户名"/>
	     	<span class="label label-success">密码</span>
	     	<input type="text" class="form-control" name="password" placeholder="请输入用户密码"/>	 
	     	<button type="submit" class="btn btn-primary">添加</button>
     	</form>
     </div>     
     <!-- 添加用户版块结束 -->
  </body>
</html>
