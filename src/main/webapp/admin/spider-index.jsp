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
<title>爬虫抓取中心</title>
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
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main" id="content-body">
				<form id="searchForm" name="searchForm" class="form-inline" role="form" method="post" action="">
					<input type="hidden" name="cname" value="${cname}" />
					<button class="btn btn-primary" type="button" onclick="spiderNews('all');">一键全部抓取</button>
					<h1>本次一共抓取${total }条新闻</h1>
					<br/><br/>
					<button type="button" class="btn btn-info" onclick="formSubmit('ycwb');">抓取羊城晚报</button>		
					<button type="button" class="btn btn-info" onclick="formSubmit('history');">抓取历史新闻</button>			
					<button type="button" class="btn btn-info" onclick="formSubmit('rumour');">抓取流言百科</button>			
					<button type="button" class="btn btn-info" onclick="formSubmit('squareDance');">抓取广场舞</button>			
					<button type="button" class="btn btn-info" onclick="formSubmit('taijiquan');">抓取太极拳</button>			
					<button type="button" class="btn btn-info" onclick="formSubmit('nation');">抓取国际热点</button>		
				</form>
				
				<div class="table-responsive">
					<table id="dataTable" class="table table-striped">
						<thead>
							<tr>
								<th>标题</th>
								<th>时间</th>
								<th>作者</th>
								<th>编辑</th>
								<th>栏目</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="news" items="${newsList}">
								<tr>
								
									<td>${news.ntitle}</td>
									<%-- <td>${news.ncontent}</td> --%>
									<td>${news.ntime}</td>
									<td>${news.nauthor}</td>
									<td>${news.neditor}</td>
									<td>${cname }</td>
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
				
			</div>
		</div>
	</div>
	<%@ include file="/commons/html-js.jsp"%>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true"></div>
	<script>
	
	function spiderNews(type) {
  		$.ajax({
	        url:"${appName}/spider/news-" + type +".do",  
	        type:"POST",
		    contentType : "application/json",
		    dataType : "json",
	        success:function(data){
	        	var data = data.model;
	        	
	        	alert("本次抓取" + data.total + "条新闻");
	        	
        		$(".reload-button").attr("onclick","javascript:window.location.reload();");
	        },
	        error:function(e){
	        }
		});
	}
	
	function formSubmit(type){
		var form=$("#searchForm");
		form.attr("action", "${appName}/spider/news-" + type + ".do");
		$(form).submit();
	}
	</script>
</body>
</html>