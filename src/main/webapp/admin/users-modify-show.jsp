<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/jsp-head.jsp"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close reload-button"
				data-dismiss="modal" aria-hidden="true">&times;</button>
			<h4 class="modal-title" id="myModalLabel">修改用户</h4>
		</div>
		<div class="modal-body" id="content-body">
			<form role="form" class="form-horizontal" id="modifyForm">
				<div aria-hidden="true" class="alert alert-danger" id="errorMsg"
					style="display: none;"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">用户名</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" readonly="readonly"
							name="uname" value="${uname }">
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-3 control-label">密码</label>
					<div class="col-sm-9">
						<input type="password" class="form-control"
							name="password" value="">
					</div>
				</div>
				
			</form>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default reload-button"
				data-dismiss="modal">关闭</button>
			<button type="button" class="btn btn-primary" id="saveButton"
				onclick="modifyUsers(this);">确认</button>
		</div>
	</div>
</div>
<script>
  	function modifyUsers(btn){
  		$.ajax({
		        url:"${appName}/admin/users-modify-operate.do",  
		        type:"post",
		        data:{"uname":$("input[name='uname']").val(),
		        	"password":$("input[name='password']").val()},
		        dataType:"json",  
		        success:function(jsonData){
	        		$("#content-body").html("修改成功");
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

