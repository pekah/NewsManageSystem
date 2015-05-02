<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <base href="<%=basePath%>">
    <title>新闻页</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/umeditor.css" rel="stylesheet">
	<style>
		
		#allNewsList{width:1200px; position: absolute;top: 70px; left: 70px;}
		#viewNews{}
		#cateNameh3{text-decoration:underline;}
		.allNewsList li span{
		    color: #666666;
		    float: right;
		    font-size: 12px;		
		}
		h1{
            font-family: "微软雅黑";
            font-weight: normal;
        }
        .label{font-size:15px; color:#336699; background:#C6D8D8;}
        
        .container p{font-size:18px;}
	</style>
	<script>
		/* $(document).ready(function()
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
				    		$("#navbarul").append("<li><a href='javascript:void(0)'\">" + datas[i] +"</a></li>");
				    	}
					},
				    error : function(data) {
				       
				    }
				});				
		}); */
		
	</script>
  </head>
  
  <body>
	<!--导航条开始-->
	<nav class="navbar navbar-default" role="navigation" id="navigation">
	  <div class="container">
		  <div class="navbar-header">
		      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
		        <span class="sr-only">Toggle navigation</span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		      </button>
		      <a class="navbar-brand" href="javascript:void(0)">欢迎你：<%=session.getAttribute("name") %></a>
		  </div><!--navbar-header-->
		  <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav" id="navbarul"> 		
		 	  <li><a href="specifyNewsList.do?cname=">首页</a></li>
		 	  <c:forEach var="cate" items="${category }">
		 	  		<li><a href="specifyNewsList.do?cname=${cate.cname}">${cate.cname }</a></li>
		 	  </c:forEach>
			</ul>
			<form class="navbar-form navbar-left" role="search" id="searchForm" name="searchForm" action="">
			  <input type="hidden" name="pageNumber" value="${newsPage.pageNumber}" />
			  <input type="hidden" name="cname" value="${currentCName}" />
			  <input type="hidden" name="keyword" value="${keyword}" />
			  <div class="form-group">
				<input type="text" class="form-control" id="keyword"/>
				<!-- <button type="button" class="btn btn-default" onclick="window.open('searchNews.do?keyword=1')">搜索</button> -->
				<button type="button" class="btn btn-default" onclick="search(1,'false')">搜索</button>
			  </div>
			</form>
		   </div><!-- collapse -->
	  </div>
	 </nav>
	 <!--导航条结束-->
	 <!--标题栏开始-->
	 <div id="allNewsList" class="allNewsList">
	 		<h3 id="cateNameh3"></h3>
			<ul id="catesul"></ul>
	 </div>
	<!--标题栏结束-->
	<!-- 新闻栏开始 -->
	<div id="viewNews">
	  <div class="container">
		<h1>${data.news[0]}</h1>
		<span class="label label-info">作者：</span>
		<span class="label">${data.news[2]}</span>
		<span class="label label-info">编辑：</span>
		<span class="label">${data.news[3]}</span>
		<span class="label label-info">发布时间：</span>
		<span class="label">${data.news[4]}</span>
		<span class="label label-info">评论数：</span>
		<span class="label">${data.news[5]}</span>
		<br/>
		<br/>
		<p>	
			${data.news[1]}
		</p>
		<form action="addReview.do" method="post">
			<textarea class="form-control" name="content" style="width:100%; height: 134px;" rows="3" placeholder="请输入您的评论"></textarea>
			<input type="hidden" name="nid" value="<%=request.getAttribute("nid") %>"/>
			<button type="submit" class="btn btn-default">发表评论</button>
		</form>		
		  <c:forEach items="${data.reviewsList}" var="reviews">
		  	<p>${reviews[1]},发表于${reviews[2]}</p>
		  	<p>${reviews[0]}</p>
		  </c:forEach>
	  </div>
	</div>
	<!-- 新闻栏结束 -->
	<script type="text/javascript" src="js/jquery-2.1.3.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="js/umeditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="js/umeditor.min.js"></script>
    <script type="text/javascript" src="js/lang/zh-cn/zh-cn.js"></script>
  </body>
</html>
