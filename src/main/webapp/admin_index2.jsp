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
					<input type="hidden" name="pageNumber" value="${newsPage.pageNumber}" />
					<button class="btn btn-primary" type="button" onclick="addNews();">添加新闻</button>
					<br><br>
					<div class="form-group">
						<input type="text" name="keyword" class="form-control" placeholder="标题或内容" value="${keyword}">
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
								<th>标题</th>
								<!-- <th>内容</th> -->
								<th>时间</th>
								<th>作者</th>
								<th>编辑</th>
							<!-- 	<th>栏目</th> -->
								
								
							</tr>
						</thead>
						<tbody>
							<c:forEach var="news" items="${newsPage.pageList}">
								<tr>
								
									<td>${news.ntitle}</td>
									<%-- <td>${news.ncontent}</td> --%>
									<td>${news.ntime}</td>
									<td>${news.nauthor}</td>
									<td>${news.neditor}</td>
									<td>
										<div class="btn-group">
											<button
												onclick="toDeleteNews('${news.nid}')"
												type="button" class="btn btn-danger" data-toggle="dropdown"
												style="margin-right: 10px;">删除</button>
											<button
												onclick="toModifyNew('${news.nid}')"
												type="button" class="btn btn-primary" data-toggle="dropdown"
												style="margin-right: 10px;">修改</button>
										</div>									
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				
				
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
			</div>
		</div>
	</div>
	<%@ include file="/commons/html-js.jsp"%>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true"></div>
	<script>
	function toDeleteNews(nid) {
 		$('#myModal').load("${appName}/admin/news-del-show.do",{"nid":nid},function(){
			$('#myModal').modal('show');
		});
	}

	function toModifyNew(id) {
		$('#myModal')
				.load(
						"${appName}/admin/new-modify-show",
						function() {
							$("input[name='accountId']").val(id);
							$('#myModal').modal('show');
						});
	}
	
	function addNews() {
		$('#myModal').load("${appName}/admin/news-add-show.do",function(){
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
	</script>
</body>
</html>