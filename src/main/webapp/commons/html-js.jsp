<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script
	src="${appName}/resources/jquery/jquery-1.11.1.min.js"></script>
	<script
	src="${appName}/resources/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="${appName}/resources/js/jquery.autocomplete.js"></script>
<script
	src="${appName}/resources/bootstrap/js/bootstrap.min.js"></script>
<script src="${appName}/resources/js/commons/common.js"></script>
<script src="${appName}/resources/js/commons/WdatePicker.js"></script>
<script type="text/javascript"
	src="${appName}/resources/js/bootstrap-datetimepicker.js"
	charset="UTF-8"></script>
<script type="text/javascript"
	src="${appName}/resources/js/bootstrap-datetimepicker.zh-CN.js"
	charset="UTF-8"></script>
<script type="text/javascript"
	src="${appName}/resources/js/jquery.chosen.min.js"
	charset="UTF-8"></script>
<script language="javascript" type="text/javascript"
	src="${appName}/resources/js/jquery.flot.js"></script>
<script src="${appName}/resources/js/highcharts.js"></script>
<script src="${appName}/resources/js/tip.1.0.js"></script>
<script src="${appName}/resources/js/uploadPreview.min.js"></script>
<script src="${appName}/resources/js/jquery.livequery.js"></script>
<script>
		var count=0;
		$(".tree").click(function(){
			if(count%2==0){
				$(this).parent("li").find(".nav-self").show();
				$(this).find(".myicon").removeClass("glyphicon glyphicon-chevron-left")
								.addClass("glyphicon glyphicon-chevron-down");
				count++;
			}else{
				$(this).parent("li").find(".nav-self").hide();
				$(this).find(".myicon").removeClass("glyphicon glyphicon-chevron-down")
								  .addClass("glyphicon glyphicon-chevron-left");
				count--;
			}
		});
		function searchLog(obj,type,way){
			if(way=="1"){
				var day=$(obj).val();
				$("#"+type).html("<img src='${appName}/resources/bootstrap/fonts/ajax-loader.gif'>");
				$("#"+type).load("${appName}/data/data-operatefromMap.action",{"type":type,"day":day},function(){
			    	 
			    });
			}else{
				var beginTime=$(obj).siblings("input[type=text][name='beginTime']").val();
				var endTime=$(obj).siblings("input[type=text][name='endTime']").val();
				$("#"+type).html("<img src='${appName}/resources/bootstrap/fonts/ajax-loader.gif'>");
				$("#"+type).load("${appName}/data/data-operate.action",{"type":type,"beginTime":beginTime,"endTime":endTime},function(){
			    	 
			    });
			}
		}

		$(".img").click(function(){
			if($(this).hasClass("add_img")){
				$(this).addClass("clear_img");
				$(this).attr("src",$(this).attr("src").replace("icon","bigger"));
				$(this).removeClass("add_img");
			}else if($(this).hasClass("clear_img")){
				$(this).addClass("add_img");
				$(this).removeClass("clear_img");
				$(this).attr("src",$(this).attr("src").replace("bigger","icon"));
			}
		});
	</script>