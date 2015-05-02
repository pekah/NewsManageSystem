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
		#allNewsList{width:1200px; position: absolute;top: 70px; left: 70px;}
		#cateNameh3{text-decoration:underline;}
		.allNewsList li span{
		    color: #666666;
		    float: right;
		    font-size: 12px;		
		}
		.row .col-md-6{ margin-bottom:20px; border-bottom:1px solid #CCCCCC;}
		.row a{ font-size:20px;color:black; font-weight:bold; }
		.row li a{font-size:18px; color:black;font-weight:normal;}
		.col4 {float:left;}
		.col8 {float:right;}
		.col8 a{cursor:pointer;}
	</style>
	<script>
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
				
/* 				//点击搜索按钮	
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
				}); */
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
		 	  <li class="active"><a href="javascript:void(0);">首页</a></li>
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
	 		<h3 id="cateNameh3">${currentCName }</h3>	
	 		<c:forEach var="news" items="${newsPage.pageList }">
	 			<li><a target='_blank' href='viewNews.do?nid=${news.nid }'>${news.ntitle}</a><span>${news.ntime }</span>
	 		</c:forEach>
			<ul id="catesul"></ul>
	 </div>
	<!--标题栏结束-->
	<!-- 新闻表格 -->
	<div class="container">
		<div class="row">
	  		<div class="col-md-6">
	  			<ul>
	  				<a href="#">联通联手西班牙电信入局大数据 数据安全成问题</a>
	  				<li><a href="#">触控技术需求增大 指纹识别仍将与密码并存</a></li>
	  				<li><a href="#">世界三大杀软测试机构：取消奇虎360所有认证</a></li>
	  				<li><a href="#">广东广乐高速16车追尾 9车燃烧18人受伤</a></li>
	  				<li><a href="#">美术基础教育困境：以前受政治约束，现在受功利绑架</a></li>
	  				<li><a href="#">中石化一季度净利十年最低 中石油业绩八年最差</a></li>
	  			</ul>
	  		</div>
	  		<div class="col-md-6">
				<ul>
					<a href="#">中央向党组织涣散村选派第一书记 任期1至3年</a>
					<li><a href="#">解放军仪仗队在俄彩排 高唱喀秋莎通过红场(图)</a></li>
					<li><a href="#">环境维权困境：村民苦寻水泥厂与唐氏综合征的关系</a></li>
					<li><a href="#">厦蓉高速江西境内发生三车追尾事故已致8人遇难</a></li>
					<li><a href="#">黄金价格或再次探底 已接近5年最低点</a></li>
					<li><a href="#">环境维权困境：村民苦寻水泥厂与唐氏综合征的关系</a></li>
				</ul>
			</div>
		</div>
		<div class="row">
	  		<div class="col-md-6">
	  			<ul>
	  				<a href="#">联通联手西班牙电信入局大数据 数据安全成问题</a>
	  				<li><a href="#">触控技术需求增大 指纹识别仍将与密码并存</a></li>
	  				<li><a href="#">世界三大杀软测试机构：取消奇虎360所有认证</a></li>
	  				<li><a href="#">广东广乐高速16车追尾 9车燃烧18人受伤</a></li>
	  				<li><a href="#">美术基础教育困境：以前受政治约束，现在受功利绑架</a></li>
	  				<li><a href="#">中石化一季度净利十年最低 中石油业绩八年最差</a></li>
	  			</ul>
	  		</div>
	  		<div class="col-md-6">
				<ul>
					<a href="#">中央向党组织涣散村选派第一书记 任期1至3年</a>
					<li><a href="#">解放军仪仗队在俄彩排 高唱喀秋莎通过红场(图)</a></li>
					<li><a href="#">环境维权困境：村民苦寻水泥厂与唐氏综合征的关系</a></li>
					<li><a href="#">厦蓉高速江西境内发生三车追尾事故已致8人遇难</a></li>
					<li><a href="#">黄金价格或再次探底 已接近5年最低点</a></li>
					<li><a href="#">环境维权困境：村民苦寻水泥厂与唐氏综合征的关系</a></li>
				</ul>
			</div>
		</div>
		<div class="row">
	  		<div class="col-md-6">
	  			<ul>
	  				<a href="#">联通联手西班牙电信入局大数据 数据安全成问题</a>
	  				<li><a href="#">触控技术需求增大 指纹识别仍将与密码并存</a></li>
	  				<li><a href="#">世界三大杀软测试机构：取消奇虎360所有认证</a></li>
	  				<li><a href="#">广东广乐高速16车追尾 9车燃烧18人受伤</a></li>
	  				<li><a href="#">美术基础教育困境：以前受政治约束，现在受功利绑架</a></li>
	  				<li><a href="#">中石化一季度净利十年最低 中石油业绩八年最差</a></li>
	  			</ul>
	  		</div>
	  		<div class="col-md-6">
				<ul>
					<a href="#">中央向党组织涣散村选派第一书记 任期1至3年</a>
					<li><a href="#">解放军仪仗队在俄彩排 高唱喀秋莎通过红场(图)</a></li>
					<li><a href="#">环境维权困境：村民苦寻水泥厂与唐氏综合征的关系</a></li>
					<li><a href="#">厦蓉高速江西境内发生三车追尾事故已致8人遇难</a></li>
					<li><a href="#">黄金价格或再次探底 已接近5年最低点</a></li>
					<li><a href="#">环境维权困境：村民苦寻水泥厂与唐氏综合征的关系</a></li>
				</ul>
			</div>
		</div>
	</div>
	<!-- 新闻表格结束 -->
 	<!-- 分页div -->
 	<div class="container">
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
