<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/jsp-head.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href="../../assets/ico/favicon.ico">
<title>新闻管理中心</title>
<%@ include file="/commons/html-css.jsp"%>
<%@ include file="/commons/html-compatible.jsp"%>
<link href="${appName}/resources/css/dashboard.css"
	rel="stylesheet">
</head>

<body>
	<!-- 头部嵌入页面 -->
	<%@ include file="/commons/head.jsp"%>
	<div class="container-fluid">
		<div class="row">
			<!-- 左边菜单栏 -->
			<%@ include file="/commons/left.jsp"%>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<form id="searchForm" class="form-inline" role="form" method="post">
					<input type="hidden" name="pageNumber" value="${keywordSetPage.pageNumber}" />
					<!-- <button class="btn btn-primary" type="button" onclick="addKeywordset();">新增关键词与关键句子</button> -->
					<br><br>
					<div class="form-group">
						<label for="keywordValue">关键词 </label> 
						<input id="keyword"
							 name="keywordValue" class="form-control" placeholder="关键词"
							 value="${keywordValue}">
					</div>	
					
					<button type="button" class="btn btn-danger"
							onclick="formSubmit(1,'false');">搜索</button>
					<button type="button" class="btn btn-info"
							onclick="resetForm(this);">重置</button>					
				</form>
				
				<div class="table-responsive">
					<table id="dataTable" class="table table-striped">
						<thead>
							<tr>
								<th>sample_hashcode</th>
								<th>关键词</th>
								<th>关键句子</th>
								
							</tr>
						</thead>
						<tbody>
							<c:forEach var="keywordSet" items="${keywordSetPage.pageList}">
								<tr>
									<td>${keywordSet.sample_hashcode}</td>
									<td>${keywordSet.keyword}</td>
									<td>${keywordSet.sentence}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				
				
				<!-- 分页div -->				 
				<div class="col-xs-6">
					<div class="dataTables_info" id="example2_info">总共
						${keywordSetPage.totalRow} 记录</div>
				</div>
				<div class="col-xs-6">
					<div class="dataTables_paginate paging_bootstrap">
						<ul class="pagination">
							<c:if test="${keywordSetPage.totalPage>0 }">
								<li class="prev" onclick="formSubmit('1','false')"><a>首页</a></li>
								<li
									class="prev <c:if test="${keywordSetPage.pageNumber <=1}">disabled</c:if>"><a
									<c:if test="${keywordSetPage.pageNumber > 1}">onclick="formSubmit('${keywordSetPage.pageNumber-1}','false')";</c:if>>←
										上一页</a></li>
							</c:if>
							<c:forEach begin="1" end="${keywordSetPage.totalPage}"
								var="pageNumber">
								<c:if
									test="${pageNumber+3>keywordSetPage.pageNumber&&pageNumber-3<keywordSetPage.pageNumber}">
									<li
										<c:if test="${pageNumber == keywordSetPage.pageNumber}">class="active"</c:if>>
										<a onclick="formSubmit('${pageNumber}','false');">${pageNumber}</a>
									</li>
								</c:if>
							</c:forEach>
							<c:if test="${keywordSetPage.totalPage>0 }">
								<li
									class="next <c:if test="${keywordSetPage.pageNumber == keywordSetPage.totalPage}">disabled</c:if>"><a
									<c:if test="${keywordSetPage.pageNumber < keywordSetPage.totalPage}">onclick="formSubmit('${keywordSetPage.pageNumber+1}','false');"</c:if>>下一页
										→ </a></li>
								<li class="next"
									onclick="formSubmit('${keywordSetPage.totalPage}','false')"><a>尾页</a></li>
							</c:if>
						</ul>
					</div>
				</div> 			
			</div>
		</div>
	</div>
	<%@ include file="/commons/html-js.jsp"%>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true"></div>
	<script>
	
	function addKeywordset() {
		$('#myModal').load("${appName}/keywords/keywordset-add-show",function(){
			$('#myModal').modal('show');
		});
	}
	
	function formSubmit(pageNumber,flag){
		var form=$("#searchForm");
		if(flag=='true'){
			resetForm();
		}
		$("input[type='hidden'][name='pageNumber']").val(pageNumber);
		$(form).submit();
	}
	function resetForm(btn){
		var form=$("#searchForm");
		form[0].reset();
		$(form).find("select,input").each(function(){
			$(this).val("");
		});
	}
	
	function gotoPage(){
    	var pageNumber=$("#goto").val();
    	if(pageNumber==""){
    		pageNumber=1;
    	}
    	window.location.href='${appName}/keywords/sentencesSet-list?pageNumber='+pageNumber;
    }
	</script>
</body>
</html>