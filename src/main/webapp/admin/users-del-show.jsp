<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/jsp-head.jsp"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close reload-button"
				data-dismiss="modal" aria-hidden="true">&times;</button>
			<h4 class="modal-title" id="myModalLabel">删除用户</h4>
		</div>
		<div class="modal-body" id="content-body">
			<input type="hidden" name="uname" value="${uname}" /> 确认删除用户？
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default reload-button"
				data-dismiss="modal">关闭</button>
			<button type="button" class="btn btn-primary" id="saveButton"
				onclick="delUsers(this);">确认</button>
		</div>
	</div>
</div>
<script>
  	function delUsers(btn){
  		$.ajax({
		        url:"${appName}/admin/users-del-operate.do",  
		        type:"post",
		        data:{"uname":$("input[name='uname']").val()},
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

