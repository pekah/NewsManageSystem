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
     	<%-- <li <c:if test="${menu==1}"> class='active'</c:if>><a href="${appName}/keywords/keywordset-list"><i class="glyphicon glyphicon-th-large"></i> 关键词集合管理</a></li> 	
       --%>
      	<li>
     		<a href="#" class="tree">
              <i class="glyphicon glyphicon-th-large"></i>
              <span>新闻管理 </span>
              <i class="glyphicon  <c:if test="${menu==7||menu==8||menu==9||menu==10}">glyphicon-chevron-down</c:if><c:if test="${!(menu==7||menu==8||menu==9||menu==10)}">glyphicon-chevron-left</c:if> myicon"></i>
          </a>
          <ul class="nav nav-self" <c:if test="${menu==7||menu==8||menu==9||menu==10}">style='display:block;'</c:if>>
            <li><a href="${appName}/admin/addNewsShow.do" onclick="addKeywordset();" <c:if test="${menu==10}">style='color:white;background-color:#428bca'</c:if>><i class="glyphicon glyphicon-indent-left"></i>添加新闻</a></li>
            <li><a href="${appName}/admin/removeNewsShow.do" <c:if test="${menu==7}">style='color:white;background-color:#428bca'</c:if>><i class="glyphicon glyphicon-indent-left"></i>删除新闻</a></li>
            <li><a href="${appName}/admin/sentencesSet-list.do" <c:if test="${menu==8}">style='color:white;background-color:#428bca'</c:if>><i class="glyphicon glyphicon-indent-left"></i>修改新闻</a></li>
          </ul>
     	</li>  
     	
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