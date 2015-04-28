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
<title>用户管理中心</title>
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
					<input type="hidden" name="pageNumber" value="${usersPage.pageNumber}" />
					<button class="btn btn-primary" type="button" onclick="addUsers();">添加用户</button>
					<br><br>
					<div class="form-group">
						<input type="text" name="keyword" class="form-control" placeholder="用户名" value="${keyword}">
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
								<th>用户名</th>
								<th>密码</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="users" items="${usersPage.pageList}">
								<tr>
									<td>${users.uname}</td>
									<td>**************</td>
									<td>
										<div class="btn-group">
											<button
												onclick="toDeleteUsers('${users.uname}')"
												type="button" class="btn btn-danger" data-toggle="dropdown"
												style="margin-right: 10px;">删除</button>
											<button
												onclick="toModifyUsers('${users.uname}')"
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
						${usersPage.totalRow} 记录</div>
				</div>
				<div class="col-xs-6">
					<div class="dataTables_paginate paging_bootstrap">
						<ul class="pagination">
							<c:if test="${usersPage.totalPage>0 }">
								<li class="prev" onclick="formSubmit('1','false')"><a>首页</a></li>
								<li
									class="prev <c:if test="${usersPage.pageNumber <=1}">disabled</c:if>"><a
									<c:if test="${usersPage.pageNumber > 1}">onclick="formSubmit('${usersPage.pageNumber-1}','false')";</c:if>>←
										上一页</a></li>
							</c:if>
							<c:forEach begin="1" end="${usersPage.totalPage}"
								var="pageNumber">
								<c:if
									test="${pageNumber+3>usersPage.pageNumber&&pageNumber-3<usersPage.pageNumber}">
									<li
										<c:if test="${pageNumber == usersPage.pageNumber}">class="active"</c:if>>
										<a onclick="formSubmit('${pageNumber}','false');">${pageNumber}</a>
									</li>
								</c:if>
							</c:forEach>
							<c:if test="${usersPage.totalPage>0 }">
								<li
									class="next <c:if test="${usersPage.pageNumber == usersPage.totalPage}">disabled</c:if>"><a
									<c:if test="${usersPage.pageNumber < usersPage.totalPage}">onclick="formSubmit('${usersPage.pageNumber+1}','false');"</c:if>>下一页
										→ </a></li>
								<li class="next"
									onclick="formSubmit('${usersPage.totalPage}','false')"><a>尾页</a></li>
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
	function toDeleteUsers(uname) {
 		$('#myModal').load("${appName}/admin/users-del-show.do",{"uname":uname},function(){
			$('#myModal').modal('show');
		});
	}

	function toModifyUsers(uid) {
		$('#myModal')
				.load(
						"${appName}/admin/users-modify-show",
						function() {
							$("input[name='accountId']").val(id);
							$('#myModal').modal('show');
						});
	}
	
	function addUsers() {
		$('#myModal').load("${appName}/admin/users-add-show.do",function(){
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