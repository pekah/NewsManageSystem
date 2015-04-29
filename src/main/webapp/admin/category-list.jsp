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
<title>栏目管理中心</title>
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
					<button class="btn btn-primary" type="button" onclick="addCategory();">添加栏目</button>
				</form>
				
				<div class="table-responsive">
					<table id="dataTable" class="table table-striped">
						<thead>
							<tr>
								<th>栏目</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="category" items="${catrgories}">
								<tr>
								
									<td>${category.cname}</td>
									<td>
										<div class="btn-group">
											<button
												onclick="toDeleteCategory('${category.cname}')"
												type="button" class="btn btn-danger" data-toggle="dropdown"
												style="margin-right: 10px;">删除</button>
										</div>									
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				
			</div>
		</div>
	</div>
	<%@ include file="/commons/html-js.jsp"%>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true"></div>
	<script>
	function toDeleteCategory(cname) {
 		$('#myModal').load("${appName}/admin/category-del-show.do",{"cname":cname},function(){
			$('#myModal').modal('show');
		});
	}

	function addCategory() {
		$('#myModal').load("${appName}/admin/category-add-show.do",function(){
			$('#myModal').modal('show');
		});
	}
	</script>
</body>
</html>