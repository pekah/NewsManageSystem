<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <style>
		<!--
		.myicon{
		    float:right;
		 }
    	.nav-self{
    		display:none;
    		margin-left:50px;
    	}
		-->
</style>

<div class="col-sm-3 col-md-2 sidebar">
     <ul class="nav nav-sidebar">     	
      	<li <c:if test="${menu==1}"> class='active'</c:if>><a href="${appName}/admin/news-listAllNews.do"><i class="glyphicon glyphicon-th-large"></i>新闻管理</a></li>
     	<li <c:if test="${menu==2}"> class='active'</c:if>><a href="${appName}/admin/news-listAllCategory.do"><i class="glyphicon glyphicon-th-large"></i>栏目管理</a></li>
     	<li <c:if test="${menu==3}"> class='active'</c:if>><a href="${appName}/admin/news-listAllUsers.do"><i class="glyphicon glyphicon-th-large"></i>用户管理</a></li>
         <li <c:if test="${menu==4}"> class='active'</c:if>><a href="${appName}/admin/news-spider-index.do"><i class="glyphicon glyphicon-th-large"></i>爬虫管理</a></li>
     </ul>
   </div>
   
   <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>
   	<script>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true"></div>	
	function addKeywordset() {
		$('#myModal').load("${appName}/keywords/keywordset-add-show",function(){
			$('#myModal').modal('show');
		});
	}
	
	</script>