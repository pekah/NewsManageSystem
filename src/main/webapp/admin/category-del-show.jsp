<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/jsp-head.jsp"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close reload-button"
				data-dismiss="modal" aria-hidden="true">&times;</button>
			<h4 class="modal-title" id="myModalLabel">删除栏目</h4>
		</div>
		<div class="modal-body" id="content-body">
			<input type="hidden" name="cname" value="${cname}" /> 确认删除栏目？
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default reload-button"
				data-dismiss="modal">关闭</button>
			<button type="button" class="btn btn-primary" id="saveButton"
				onclick="delCategory(this);">确认</button>
		</div>
	</div>
</div>
<script>
  	function delCategory(btn){
  		$.ajax({
		        url:"${appName}/admin/category-del-operate.do",  
		        type:"post",
		        data:{"cname":$("input[name='cname']").val()},
		        dataType:"json",  
		        success:function(jsonData){
	        		$("#content-body").html("删除成功");
	        		$("#saveButton").remove();
	        		$(".reload-button").attr("onclick","javascript:window.location.reload();");
		        },
		        error:function(e){
		        	$("#content-body").html("网络超时，请稍后再试！");
	        		$("#saveButton").remove();
		        }
 		});
  	}
  </script>

