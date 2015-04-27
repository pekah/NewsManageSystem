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
     	
     	<li>
     		<a href="#" class="tree">
              <i class="glyphicon glyphicon-th-large"></i>
              <span>栏目管理</span>
              <i class="glyphicon  <c:if test="${menu==2||menu==3}">glyphicon-chevron-down</c:if><c:if test="${!(menu==2||menu==3)}">glyphicon-chevron-left</c:if> myicon"></i>
          </a>
          <ul class="nav nav-self" <c:if test="${menu==2||menu==3}">style='display:block;'</c:if>>
            <li><a href="${appName}/sensitive/sensitivekind-list" <c:if test="${menu==2}">style='color:white;background-color:#428bca'</c:if>><i class="glyphicon glyphicon-indent-left"></i>添加栏目</a></li>
            <li><a href="${appName}/sensitive/sensitiveword-list" <c:if test="${menu==3}">style='color:white;background-color:#428bca'</c:if>><i class="glyphicon glyphicon-indent-left"></i>删除栏目</a></li>
          </ul>
     	</li> 
     	<li>
            <a href="#" class="tree">
                <i class="glyphicon glyphicon-th-large"></i>
                <span>用户管理</span>
                <i class="glyphicon  <c:if test="${menu==4||menu==5}">glyphicon-chevron-down</c:if><c:if test="${!(menu==4||menu==5)}">glyphicon-chevron-left</c:if> myicon"></i>
            </a>
            <ul class="nav nav-self" <c:if test="${menu==4||menu==5}">style='display:block;'</c:if>>
                <li><a href="${appName}/cutting/creditcutting-list" <c:if test="${menu==4}">style='color:white;background-color:#428bca'</c:if>><i class="glyphicon glyphicon-indent-left"></i>添加用户</a></li>
                <li><a href="${appName}/cutting/similaritycutting-list" <c:if test="${menu==5}">style='color:white;background-color:#428bca'</c:if>><i class="glyphicon glyphicon-indent-left"></i>删除用户</a></li>
                <li><a href="${appName}/cutting/similaritycutting-list" <c:if test="${menu==5}">style='color:white;background-color:#428bca'</c:if>><i class="glyphicon glyphicon-indent-left"></i>修改用户</a></li>
            </ul>
         </li>   
         
         <li <c:if test="${menu==1}"> class='active'</c:if>><a href="${appName}/admin/news-spider-index.do"><i class="glyphicon glyphicon-th-large"></i>爬虫管理</a></li>
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