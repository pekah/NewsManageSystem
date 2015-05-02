<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    <title>用户主页</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<style>
		#navigation{ margin-bottom:30px;}
		#allNewsList{width:1100px; float:left;}
		.allNewsList li a{ font-size:20px; line-height:30px;}
		.allNewsList li span{
		    color: #666666;
		    float: right;
		    font-size: 20px;	
		    line-height:30px;	
		}
		#cateNameh3{text-decoration:underline;}

		.row .col-md-6{ margin-bottom:20px; border-bottom:1px solid #CCCCCC;}
		.row .col-md-6 ul li a{font-size:18px; color:black;font-weight:normal;}
		
		.col4 {float:left;}
		.col8 {float:right;}
		.container_2 {width:1100px;margin:0 auto;}
		.col8 a{cursor:pointer;}
		.col-md-6 ul li:first-child{list-style-type:none;}
		.col-md-6 ul li:first-child a{font-size:20px; color:black; font-weight: bold;  }
		
	</style>
	<script>

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
		
		function search(pageNumber,flag){
			var keyword = $("#keyword").val();
			if(keyword == undefined || keyword == null || keyword == ""){
				keyword = 1;
			}
			window.open('searchNews.do?keyword=' + keyword)
			
		}
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
	 <div class="container">
	 	<div id="allNewsList" class="allNewsList">
	 		<h3 id="cateNameh3">${currentCName }</h3>	
	 		<c:forEach var="news" items="${newsPage.pageList }">
	 			<li><a target='_blank' href='viewNews.do?nid=${news.nid }'>${news.ntitle}</a><span>${news.ntime }</span>
	 		</c:forEach>
			<ul id="catesul"></ul>
	 </div>
	 </div>
	<!--标题栏结束-->
	
	<!-- 新闻表格 -->
	<div class="container">
	<c:if test="${flag == 'index' }">
		<div class="row">
	  		<div class="col-md-6">
	  			<ul>		
					<c:forEach var="news" items="${ycwb}">
						<li><a href="viewNews.do?nid=${news.nid }">${news.ntitle}</a></li>
					</c:forEach>
	  			</ul>
	  		</div>
	  		<div class="col-md-6">
	  			<ul>		
					<c:forEach var="news" items="${history}">
						<li><a href="viewNews.do?nid=${news.nid }">${news.ntitle}</a></li>
					</c:forEach>
	  			</ul>			
	  		</div>
		</div>
		<div class="row">
	  		<div class="col-md-6">
	  			<ul>		
					<c:forEach var="news" items="${taijiquan}">
						<li><a href="viewNews.do?nid=${news.nid }">${news.ntitle}</a></li>
					</c:forEach>
	  			</ul>	
	  		</div>
	  		<div class="col-md-6">
	  			<ul>		
					<c:forEach var="news" items="${squareDance}">
						<li><a href="viewNews.do?nid=${news.nid }">${news.ntitle}</a></li>
					</c:forEach>
	  			</ul>				
	  		</div>
		</div>
		<div class="row">
	  		<div class="col-md-6">
	  			<ul>		
					<c:forEach var="news" items="${rumour}">
						<li><a href="viewNews.do?nid=${news.nid }">${news.ntitle}</a></li>
					</c:forEach>
	  			</ul>		
	  		</div>
	  		<div class="col-md-6">
	  			<ul>		
					<c:forEach var="news" items="${nation}">
						<li><a href="viewNews.do?nid=${news.nid }">${news.ntitle}</a></li>
					</c:forEach>
	  			</ul>
			</div>
		</div>
	</c:if>
	<!-- 新闻表格结束 -->
	</div>	
 	<!-- 分页div -->
 	<div class="container_2">
		<div class="col4">
			<div class="dataTables_info" id="example2_info">总共
				${newsPage.totalRow} 记录</div>
		</div>
		<div class="col8">
			<div class="dataTables_paginate paging_bootstrap">
				<ul class="pagination">
					<c:if test="${newsPage.totalPage>0 }">
						<li class="prev" onclick="formSubmit('1','false')"><a>首页</a></li>
						<li
							class="prev <c:if test="${newsPage.pageNumber <=1}">disabled</c:if>"><a
							<c:if test="${newsPage.pageNumber > 1}">onclick="formSubmit('${newsPage.pageNumber-1}','false')";</c:if>>
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
								</a></li>
						<li class="next"
							onclick="formSubmit('${newsPage.totalPage}','false')"><a>尾页</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
<script src="js/jquery-2.1.3.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
