<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
/* 				$.ajax({
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
				});		 */	
				
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
		
		function formSubmit(pageNumber,flag){
			var form=$("#searchForm");
			if(flag=='true'){
				//resetForm();
			}
			$("input[type='hidden'][name='pageNumber']").val(pageNumber);
			$(form).submit();
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
	 	  <c:forEach var="cate" items="${category }">
	 	  		<li><a href="specifyNewsList.do?cname=${cate.cname}">${cate.cname }</a></li>
	 	  </c:forEach>
		</ul>
		<form class="navbar-form navbar-left" role="search" id="searchForm">
		  <input type="hidden" name="pageNumber" value="${newsPage.pageNumber}" />
		  <input type="hidden" name="cname" value="${currentCName}" />
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
	 		<h3 id="cateNameh3">${currentCName }</h3>	
	 		<c:forEach var="news" items="${newsPage.pageList }">
	 			<li><a target='_blank' href='viewNews.do?nid=${news.nid }'>${news.ntitle}</a><span>${news.ntime }</span>
	 		</c:forEach>
			<ul id="catesul"></ul>
	 </div>
	<!--标题栏结束-->
 	<!-- 分页div -->
	<div class="col-xs-6">
		<div class="dataTables_info" id="example2_info">总共
			${newsPage.totalRow} 记录</div>
	</div>
	<div class="col-xs-6">
		<div class="dataTables_paginate paging_bootstrap">
			<ul class="pagination">
				<c:if test="${newsPage.totalPage>0 }">
					<li class="prev" onclick="formSubmit('1','false')"><a>首页</a></li>
					<li
						class="prev <c:if test="${newsPage.pageNumber <=1}">disabled</c:if>"><a
						<c:if test="${newsPage.pageNumber > 1}">onclick="formSubmit('${newsPage.pageNumber-1}','false')";</c:if>>←
							上一页</a></li>
				</c:if>
				<c:forEach begin="1" end="${newsPage.totalPage}"
					var="pageNumber">
					<c:if
						test="${pageNumber+3>newsPage.pageNumber&&pageNumber-3<newsPage.pageNumber}">
						<li
							<c:if test="${pageNumber == newsPage.pageNumber}">class="active"</c:if>>
							<a onclick="formSubmit('${pageNumber}','false');">${pageNumber}</a>
						</li>
					</c:if>
				</c:forEach>
				<c:if test="${newsPage.totalPage>0 }">
					<li
						class="next <c:if test="${newsPage.pageNumber == newsPage.totalPage}">disabled</c:if>"><a
						<c:if test="${newsPage.pageNumber < newsPage.totalPage}">onclick="formSubmit('${newsPage.pageNumber+1}','false');"</c:if>>下一页
							→ </a></li>
					<li class="next"
						onclick="formSubmit('${newsPage.totalPage}','false')"><a>尾页</a></li>
				</c:if>
			</ul>
		</div>
	</div>
</body>
</html>
