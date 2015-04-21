<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>新闻页</title>
	<link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
	<link href="css/umeditor.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="js/jquery-1.7.1.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="js/umeditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="js/umeditor.min.js"></script>
    <script type="text/javascript" src="js/lang/zh-cn/zh-cn.js"></script>
	<style type="text/css">
		#navigation{width:1000px; margin:0 auto;}
		#allNewsList{width:1200px; position: absolute;top: 70px; left: 70px;}
		#viewNews{width:1200px; position: absolute;top: 70px; left: 70px;}
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
        .btn {
            display: inline-block;
            *display: inline;
            padding: 4px 12px;
            margin-bottom: 0;
            *margin-left: .3em;
            font-size: 14px;
            line-height: 20px;
            color: #333333;
            text-align: center;
            text-shadow: 0 1px 1px rgba(255, 255, 255, 0.75);
            vertical-align: middle;
            cursor: pointer;
            background-color: #f5f5f5;
            *background-color: #e6e6e6;
            background-image: -moz-linear-gradient(top, #ffffff, #e6e6e6);
            background-image: -webkit-gradient(linear, 0 0, 0 100%, from(#ffffff), to(#e6e6e6));
            background-image: -webkit-linear-gradient(top, #ffffff, #e6e6e6);
            background-image: -o-linear-gradient(top, #ffffff, #e6e6e6);
            background-image: linear-gradient(to bottom, #ffffff, #e6e6e6);
            background-repeat: repeat-x;
            border: 1px solid #cccccc;
            *border: 0;
            border-color: #e6e6e6 #e6e6e6 #bfbfbf;
            border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25);
            border-bottom-color: #b3b3b3;
            -webkit-border-radius: 4px;
            -moz-border-radius: 4px;
            border-radius: 4px;
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ffffffff', endColorstr='#ffe6e6e6', GradientType=0);
            filter: progid:DXImageTransform.Microsoft.gradient(enabled=false);
            *zoom: 1;
            -webkit-box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05);
            -moz-box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05);
            box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05);
        }

        .btn:hover,
        .btn:focus,
        .btn:active,
        .btn.active,
        .btn.disabled,
        .btn[disabled] {
            color: #333333;
            background-color: #e6e6e6;
            *background-color: #d9d9d9;
        }

        .btn:active,
        .btn.active {
            background-color: #cccccc \9;
        }

        .btn:first-child {
            *margin-left: 0;
        }

        .btn:hover,
        .btn:focus {
            color: #333333;
            text-decoration: none;
            background-position: 0 -15px;
            -webkit-transition: background-position 0.1s linear;
            -moz-transition: background-position 0.1s linear;
            -o-transition: background-position 0.1s linear;
            transition: background-position 0.1s linear;
        }

        .btn:focus {
            outline: thin dotted #333;
            outline: 5px auto -webkit-focus-ring-color;
            outline-offset: -2px;
        }

        .btn.active,
        .btn:active {
            background-image: none;
            outline: 0;
            -webkit-box-shadow: inset 0 2px 4px rgba(0, 0, 0, 0.15), 0 1px 2px rgba(0, 0, 0, 0.05);
            -moz-box-shadow: inset 0 2px 4px rgba(0, 0, 0, 0.15), 0 1px 2px rgba(0, 0, 0, 0.05);
            box-shadow: inset 0 2px 4px rgba(0, 0, 0, 0.15), 0 1px 2px rgba(0, 0, 0, 0.05);
        }

        .btn.disabled,
        .btn[disabled] {
            cursor: default;
            background-image: none;
            opacity: 0.65;
            filter: alpha(opacity=65);
            -webkit-box-shadow: none;
            -moz-box-shadow: none;
            box-shadow: none;
        }
	</style>
	<script type="text/javascript">
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
	<nav class="navbar navbar-default navbar-static-top" role="navigation" id="navigation">
	  <div class="navbar-header" id="bs-example-navbar-collapse-1">
		<ul class="nav navbar-nav" id="navbarul">
		  <li><a href="javascript:void(0)">欢迎：<%=session.getAttribute("name") %></a></li>		
		  <li class="active"><a href="#">首页</a></li>
	 	
	 	  <c:forEach var="cate" items="${category }">
	 	  		<li><a href="specifyNewsList.do?cname=${cate.cname}">${cate.cname }</a></li>
	 	  </c:forEach>		  
		  
		</ul>
		<form class="navbar-form navbar-left" role="search">
		  <div class="form-group">
			<input type="text" class="form-control">
			<button type="submit" class="btn btn-default" disabled="disabled">搜索</button>
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
	<!-- 新闻栏开始 -->
	<div id="viewNews">
		<h1>${data.news[0]}</h1>
		<span class="label label-success">作者：</span>
		<span class="label">${data.news[2]}</span>
		<span class="label label-success">编辑：</span>
		<span class="label">${data.news[3]}</span>
		<span class="label label-success">发布时间：</span>
		<span class="label">${data.news[4]}</span>
		<span class="label label-success">评论数：</span>
		<span class="label">${data.news[5]}</span>
		<br/>
		<br/>
		<p>	
			${data.news[1]}
		</p>
		<form action="addReview.do" method="post">
			<textarea class="form-control" name="content" style="width: 1179px; height: 134px;" rows="3" placeholder="请输入您的评论"></textarea>
			<input type="hidden" name="nid" value="<%=request.getAttribute("nid") %>"/>
			<button type="submit" class="btn btn-default">发表评论</button>
		</form>		
		  <c:forEach items="${data.reviewsList}" var="reviews">
		  	<p>${reviews[1]},发表于${reviews[2]}</p>
		  	<p>${reviews[0]}</p>
		  </c:forEach>
	</div>
	<!-- 新闻栏结束 -->
  </body>
</html>
