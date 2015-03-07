<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>用户主页</title>
	<link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/jquery-1.7.1.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
	<style type="text/css">
		#navigation{width:1000px; margin:0 auto;}
		#allNewsList{width:1200px; position: absolute;top: 70px; left: 70px;}
		#cateNameh3{text-decoration:underline;}
		.allNewsList li span{
		    color: #666666;
		    float: right;
		    font-size: 12px;		
		}
	</style>
	<script type="text/javascript">
		$(document).ready(function()
		{
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
				    		$("#navbarul").append("<li><a href='javascript:void(0)' onclick=\"specifyNewsList(\'"+ datas[i] +"\')\">" + datas[i] +"</a></li>");
				    	}
					},
				    error : function(data) {
				       
				    }
				});			
				
				//点击搜索按钮	
				$("#searchBtn").click(function(){
					var keyword = $("#keyword").val();
					//清空ul下的所有li
					$("#catesul").empty();
					$("#cateNameh3").text("搜索结果");
					$.ajax({
						    url : "searchNews.do",
						    type : "POST",
						    contentType : "application/json",
						    dataType : "json",
						    data:JSON.stringify(keyword),
						    success : function(data) {
								var map = data.data;
								var titles = map.title;
								var times = map.time;
								var nid = map.nid;
								for(var i in titles)
								{
									$("#catesul").append("<li><a target=\"_blank\" href='viewNews.do?nid="+  nid[i] +"'>" + titles[i] + "</a><span>" + times[i] + "</span>");
								}
								
							},
						    error : function(data) {
						       
						    }
						});					
				});
		});
		

		
		
		//查看指定版块的新闻列表,包括搜索功能
		function specifyNewsList(cateName)
		{
			//清空ul下的所有li
			$("#catesul").empty();
			$("#cateNameh3").text(cateName);
			$.ajax({
				    url : "specifyNewsList.do",
				    type : "POST",
				    contentType : "application/json",
				    dataType : "json",
				    data:JSON.stringify(cateName),
				    success : function(data) {
						var map = data.data;
						var titles = map.title;
						var times = map.time;
						var nid = map.nid;
						for(var i in titles)
						{
							$("#catesul").append("<li><a target=\"_blank\" href='viewNews.do?nid="+  nid[i] +"'>" + titles[i] + "</a><span>" + times[i] + "</span>");
						}
						
					},
				    error : function(data) {
				       
				    }
				});
		}
		
	</script>
  </head>
  
  <body>
	<!--导航条开始-->
	<nav class="navbar navbar-default navbar-static-top" role="navigation" id="navigation">
	  <div class="navbar-header" id="bs-example-navbar-collapse-1">
		<ul class="nav navbar-nav" id="navbarul">
		  <li><a href="javascript:void(0)">欢迎你：<%=session.getAttribute("name") %></a></li>		
	 	  <li class="active"><a href="javascript:void(0);">首页</a></li>
		</ul>
		<form class="navbar-form navbar-left" role="search">
		  <div class="form-group">
			<input type="text" class="form-control" id="keyword"/>
			<button type="button" class="btn btn-default" id="searchBtn">搜索</button>
		  </div>
		</form>
	  </div>
	 </nav>
	 <!--导航条结束-->
	 <!--标题栏开始-->
	 <div id="allNewsList" class="allNewsList">
	 		<h3 id="cateNameh3"></h3>	
			<ul id="catesul"></ul>
	 </div>
	<!--标题栏结束-->
  </body>
</html>
