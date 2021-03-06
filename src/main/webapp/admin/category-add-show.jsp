<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/jsp-head.jsp"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close reload-button"
				data-dismiss="modal" aria-hidden="true">&times;</button>
			<h4 class="modal-title" id="myModalLabel">添加栏目</h4>
		</div>
		<div class="modal-body" id="content-body">
			<form role="form" class="form-horizontal" id="addForm">
				<div aria-hidden="true" class="alert alert-danger" id="errorMsg"
					style="display: none;"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">栏目名</label>
					<div class="col-sm-9">
						<input type="text" placeholder="必填" class="form-control"
							name="cname">
					</div>
				</div>
			</form>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default reload-button"
				data-dismiss="modal">关闭</button>
			<button type="button" class="btn btn-primary" id="saveButton"
				onclick="saveCategory();">保存</button>
		</div>
	</div>
</div>
<script>
	$(document).ready(function(){
		$(".chosen-select").chosen({width:"250px"});
	});
  	function saveCategory(){
  		var cname=$("input[name='cname']").val();
  		
  		if(cname==''||cname==null){
  			$("#errorMsg").addClass("alert-danger alert").html("栏目名不为空！").show();
  			return;
  		}
  		
  		$("#errorMsg").removeClass().hide();
  		$.ajax({
	        url:"<%=request.getContextPath()%>/admin/category-add-operate.do",  
	        type:"post",
	        data:{
	        	"cname":cname,
	        },
	        dataType:"json",  
	        success:function(jsonData){
        		$("#content-body").html("增加成功");
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

