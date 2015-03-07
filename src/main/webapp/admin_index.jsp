<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=GBK">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <base href="<%=basePath%>">
    <title>����Ա��ҳ</title>
	<link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/jquery-1.7.1.js"></script>
	<script type="text/javascript" src="js/bootstrap.js"></script>
	<style type="text/css">
		#newsBtns{float:left; width:10%; position: absolute;top: 70px;display: none;}
		#cateBtns{float:left; width:10%; position: absolute;top: 180px;display: none;}	
		#revBtns{float:left; width:10%; position: absolute;top: 290px;display: none;}
		#addNews{float:right; width:70%; position: absolute;top: 70px;left: 200px;display: none;}
		#removeNews{float:right; width:70%; position: absolute;top: 70px;left: 200px;display: none;}
		#addCate{float:right; width:70%; position: absolute;top: 180px;left: 200px;display: none;}
		#removeCate{float:right; width:70%; position: absolute;top: 215px;left: 200px;display: none;}
		#addUsers{float:right; width:70%; position: absolute;top: 285px;left: 200px;display: none;}}
	</style>
	<script type="text/javascript">
		$(document).ready(function()
		{
			$("#addNewsBtn").click(function(){
				$("#addNews").show();
				$("#removeNews").hide();
				//ͨ��ajax��ȡ���е���Ŀ
				$.ajax({
				    url : "getCategorys.do",
				    type : "POST",
				    contentType : "application/json",
				    dataType : "json",
				    //data:JSON.stringify(dataValue),
				    success : function(data) {
				        var datas;
				   		for(var i in data)
				   		{
				   			datas = data[i];
				   		}
				    	for(var i in datas)
				    	{
				    		$("#catesSelect").append("<option>"+ datas[i] +"</option>");
				    	}
					},
				    error : function(data) {
				        alert("��ĿΪ�գ����������Ŀ");
				    }
				});				
			});
			$("#removeNewsBtn").click(function(){
				$("#addNews").hide();
				$("#removeNews").show();
			});			
			$("#addCateBtn").click(function(){
				$("#removeCate").hide();
				$("#addCate").show();
			});
			$("#removeCateBtn").click(function(){
				$("#addCate").hide();
				$("#removeCate").show();
			});			
			$("#addUsersBtn").click(function(){
				$("#addUsers").show();
			});				
			
			
		});
		function newsManage()
		{
			$("#newsBtns").show();
			$("#cateBtns").hide();
			$("#revBtns").hide();
			$("#addCate").hide();
			$("#removeCate").hide();
		};
		function cateManage()
		{
			$("#newsBtns").hide();
			$("#cateBtns").show();
			$("#revBtns").hide();
			$("#addNews").hide();
			$("#removeNews").hide();
		};
		function usersManage()
		{
			$("#newsBtns").hide();
			$("#cateBtns").hide();
			$("#revBtns").show();
			$("#addNews").hide();
			$("#addCate").hide();
			$("#removeNews").hide();
			$("#removeCate").hide();
		};		
	</script>
  </head>
  
  <body>
	<!--��������ʼ-->
	<nav class="navbar navbar-default navbar-static-top" role="navigation" id="navigation">
	  <div class="navbar-header" id="bs-example-navbar-collapse-1">
		<ul class="nav navbar-nav">
		  <li class="active"><a href="#">����Ա��ҳ</a></li>
		  <li><a href="javascript:void(0)" onclick="newsManage()">���Ź���</a></li>
		  <li><a href="javascript:void(0)" onclick="cateManage()">��Ŀ����</a></li>
		  <li><a href="javascript:void(0)" onclick="usersManage()">�û�����</a></li>
		  <li><a href="javascript:void(0)">��ӭ�㣺<%=session.getAttribute("name") %></a></li>		  
		</ul>
	  </div>
	 </nav>
	 <!--����������-->
	 <!--���Ź���ť�鿪ʼ-->
	 <div id="newsBtns">
		<button type="button" class="btn btn-info" id="addNewsBtn">�������</button><br/>
		<button type="button" class="btn btn-info" id="removeNewsBtn">ɾ������</button><br/>
		<button type="button" class="btn btn-info">�޸�����</button><br/>
	 </div>
     <!--���Ź���ť�����-->
	 <!--��Ŀ����ť�鿪ʼ-->
	 <div id="cateBtns">
		<button type="button" class="btn btn-info" id="addCateBtn">�����Ŀ</button><br/>
		<button type="button" class="btn btn-info" id="removeCateBtn">ɾ����Ŀ</button><br/>
	 </div>
     <!--��Ŀ����ť�����-->
	 <!--�û�����ť�鿪ʼ-->
	 <div id="revBtns">
		<button type="button" class="btn btn-info" id="addUsersBtn">����û�</button><br/>
		<button type="button" class="btn btn-info" id="removeUsersBtn">ɾ���û�</button><br/>
		<button type="button" class="btn btn-info" id="modifyUsersBtn">�޸��û�</button><br/>
	 </div>
     <!--�û�����ť�����-->
     
     <!-- ������Ű�鿪ʼ -->
     <div id="addNews">
		 <form role="form" action="addNews.do" method="post">
		  <div class="form-group">
		   <label>���ű��⣺</label>
		    <input type="text" class="form-control" name="title" placeholder="���������ű���"/>
		  </div>
		  <div class="form-group">
		    <span class="label label-success">����</span>
		    <input type="text" class="form-control" name="author" placeholder="������������"/>
		    <span class="label label-success">�༭</span>
		    <input type="text" class="form-control" name="editor" placeholder="������༭��"/>
		    <span class="label label-success">��Ŀ</span>
			<select id="catesSelect" name="category">
			</select>
			<label>�������ݣ�</label>
			<textarea class="form-control" name="content" rows="3" style="width: 769px; height: 407px;"></textarea>
		  </div>
		  <button type="submit" class="btn btn-default">�ύ</button>
		</form>    	
     </div>
     <!-- ������Ű����� -->
     
     <!-- ɾ�����Ű�鿪ʼ -->
     <div id="removeNews">
      	<form role="form" method="post" action="removeNews.do">
	     	<span class="label label-success">������</span>
	     	<input type="text" class="form-control" name="newsTitle" placeholder="������������"/>
	     	<button type="submit" class="btn btn-danger">ɾ��</button>
     	</form>    	
     </div>
     <!-- ɾ�����Ű����� -->
          
     <!-- �����Ŀ��鿪ʼ -->
     <div id="addCate">
     	<form role="form" method="post" action="addCate.do">
	     	<span class="label label-success">��Ŀ��</span>
	     	<input type="text" class="form-control" name="cateName" placeholder="��������Ŀ��"/>
	     	<button type="submit" class="btn btn-primary">���</button>
     	</form>
     </div>
     <!-- �����Ŀ������ -->
     <!-- ɾ����Ŀ��鿪ʼ -->
     <div id="removeCate">
      	<form role="form" method="post" action="removeCate.do">
	     	<span class="label label-success">��Ŀ��</span>
	     	<input type="text" class="form-control" name="cateName" placeholder="��������Ŀ��"/>
	     	<button type="submit" class="btn btn-danger">ɾ��</button>
     	</form>    	
     </div>
     <!-- ɾ����Ŀ������ -->
     
     <!-- ����û���鿪ʼ -->
     <div id="addUsers">
     	<form role="form" method="post" action="addUsers.do">
	     	<span class="label label-success">�û���</span>
	     	<input type="text" class="form-control" name="username" placeholder="�������û���"/>
	     	<span class="label label-success">����</span>
	     	<input type="text" class="form-control" name="password" placeholder="�������û�����"/>	 
	     	<button type="submit" class="btn btn-primary">���</button>
     	</form>
     </div>     
     <!-- ����û������� -->
  </body>
</html>
