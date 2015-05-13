<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/jsp-head.jsp"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close reload-button"
				data-dismiss="modal" aria-hidden="true">&times;</button>
			<h4 class="modal-title" id="myModalLabel">添加新闻</h4>
		</div>
		<div class="modal-body" id="content-body">
			<form role="form" class="form-horizontal" id="addForm">
				<div aria-hidden="true" class="alert alert-danger" id="errorMsg"
					style="display: none;"></div>
				<div class="form-group">
					<label class="col-sm-3 control-label">新闻标题</label>
					<div class="col-sm-9">
						<input type="text" placeholder="必填" class="form-control"
							name="title">
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-3 control-label">作者</label>
					<div class="col-sm-9">
						<input type="text" placeholder="必填" class="form-control"
							name="author">
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-3 control-label">编辑</label>
					<div class="col-sm-9">
						<input type="text" placeholder="必填" class="form-control"
							name="editor">
					</div>
				</div>				
				
				<div class="form-group">
					<label class="col-sm-3 control-label">栏目</label>
					<div class="col-sm-9">
						<select class="form-control" id="catesSelect" name="category">
							<c:forEach var="cate" items="${result }">
								<option class="form-control" value="${cate.cname }">${cate.cname }</option>
							</c:forEach>
						</select>	
					</div>					
			
				</div>				
				
				<div class="form-group">
					<label class="col-sm-3 control-label">新闻内容</label>
					<div class="col-sm-3">
						<input type="text" style="width: 410px;height: 400px;" placeholder="必填" class="form-control"
							name="content">
					</div>
				</div>
				
				
			</form>
		</div>
		<div class="modal-footer">
			<button type="button" class="btn btn-default reload-button"
				data-dismiss="modal">关闭</button>
			<button type="button" class="btn btn-primary" id="saveButton"
				onclick="saveNews();">保存</button>
		</div>
	</div>
</div>
<script>
	$(document).ready(function(){
		$(".chosen-select").chosen({width:"250px"});
	});
  	function saveNews(){
  		var title=$("input[name='title']").val();
  		var author=$("input[name='author']").val();
  		var editor=$("input[name='editor']").val();
  		var category=$('#catesSelect option:selected').val();
  		var content=$("input[name='content']").val();
  		
  		if(title==''||title==null){
  			$("#errorMsg").addClass("alert-danger alert").html("标题不为空！").show();
  			return;
  		}
  		
  		if(author==''||author==null){
  			$("#errorMsg").addClass("alert-danger alert").html("作者不为空！").show();
  			return;
  		}  		
  		
  		if(editor==''||editor==null){
  			$("#errorMsg").addClass("alert-danger alert").html("编辑不为空！").show();
  			return;
  		}
  		
  		if(content==''||content==null){
  			$("#errorMsg").addClass("alert-danger alert").html("内容不为空！").show();
  			return;
  		} 		
  		
  		$("#errorMsg").removeClass().hide();
  		$.ajax({
	        url:"<%=request.getContextPath()%>/admin/news-add-operate.do",  
	        type:"post",
	        data:{
	        	"title":title,
	        	"author":author,
	        	"editor":editor,
	        	"category":category,
	        	"content":content,
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

