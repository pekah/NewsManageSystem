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
<title>添加新闻</title>
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
	 <!-- 添加新闻版块开始 -->
     <div id="addNews">
		 <form role="form" action="addNews.do" method="post">
		  <div class="form-group">
		   <label>新闻标题：</label>
		    <input type="text" class="form-control" name="title" placeholder="请输入新闻标题"/>
		  </div>
		  <div class="form-group">
		    <span class="label label-success">作者</span>
		    <input type="text" class="form-control" name="author" placeholder="请输入作者名"/>
		    <span class="label label-success">编辑</span>
		    <input type="text" class="form-control" name="editor" placeholder="请输入编辑名"/>
		    <span class="label label-success">栏目</span>
			<select id="catesSelect" name="category">
			</select>
			<label>新闻内容：</label>
			<textarea class="form-control" name="content" rows="3" style="width: 769px; height: 407px;"></textarea>
		  </div>
		  <button type="submit" class="btn btn-default">提交</button>
		</form>    	
     </div>
     <!-- 添加新闻版块结束 -->
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