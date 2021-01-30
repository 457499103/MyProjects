<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的第一个easyUI页面</title>
<c:set var="ctx" value="${pageContext.request.contextPath}" />  
<c:set var="lang" value="${request.locale}" /> 
<c:out value="${lang}"/> 


<script type="text/javascript" src="jslib/jquery-easyui-1.9.11/jquery.min.js"></script>
<script type="text/javascript" src="jslib/jquery-easyui-1.9.11/jquery.easyui.min.js"></script>
<!-- <script type="text/javascript" src="jslib/jquery-easyui-1.9.11/locale/easyui-lang-zh_CN.js"></script> -->
<link rel="stylesheet" type="text/css" href="jslib/jquery-easyui-1.9.11/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jslib/jquery-easyui-1.9.11/themes/icon.css">







<script type="text/javascript" src="jslib/myutil.js"></script>

</head>
<body>
<c:out value="${request.getLanguage()}"/>
 <c:out value="${ctx}"/>
     <div id="cc" class="easyui-layout" style="100%;height:900px;">
        <div data-options="region:'north',split:true" style="height:50px;"></div>
        <div data-options="region:'south',split:true" style="height:100px;"></div>
        <div data-options="region:'east',title:'East',split:true" style="width:200px;"></div>
        <div data-options="region:'west'" style="width:200px;">
         <jsp:include page="layout/west.jsp"></jsp:include>
        </div>
        <div data-options="region:'center',title:'center title'" style="padding:5px;background:#eee;">
        <jsp:include page="layout/center.jsp"></jsp:include>
        </div>
    </div>
 <jsp:include page="user/login.jsp"></jsp:include>   
 <jsp:include page="user/reg.jsp"></jsp:include>


</body>

<script type="text/javascript">
var lang = navigator.language||navigator.userLanguage;
var  theString= navigator.language||navigator.userLanguage;
$(function (){
	
	
	console.info(lang),
	console.info(theString),
	console.info(theString.substr(0,2) =='zh'),
	console.info((navigator.language||navigator.userLanguage).substr(0,2) =='zh'),
	console.info(${fn:contains(lang, 'zh')}),
	console.info(call())

})

function call(){
	var lang = navigator.language||navigator.userLanguage;
	return (navigator.language||navigator.userLanguage).substr(0,2)
}


if (navigator.browserLanguage != "undefined" && navigator.browserLanguage != null) {
if (navigator.systemLanguage == "zh-CN") {
    document.write("<script src='jslib/jquery-easyui-1.9.11/locale/easyui-lang-zh_CN.js'><\/script>");
}
}
//firefox、chrome,360
else {
if (navigator.language == "zh-CN") {
    document.write("<script src='jslib/jquery-easyui-1.9.11/locale/easyui-lang-zh_CN.js'><\/script>");
}
}

/*function regUser(){
	
	

     
    if($('#index_regform').form('validate')){
    	$.ajax({

 		   type: "POST",

 		   url: '${pageContext.request.contextPath}/userAction!addReg.action',

 		   data: $('#index_regform').serialize() ,
 		   
  		   {
 			   name: $('#index_regform input[name=name]').val(),
 			   pwd: $('#index_regform input[name=pwd]').val(),
 			   
 		   }, 
            dataType: 'json',
 		   success: function(obj, textStatus,jqXHR){

           	 
           	 if (obj.success){
           		 $('#index_regDialog').dialog('close');
           	 }
           	  
           	 $.messager.show({
           			title:'提示',
           			msg:obj.msg,
           			timeout:5000,
           			showType:'slide'
           		});

 		   }

 		});
    	
    } else{
    	alert("注册失败");
    	
    }

     
     } */
</script>
</html>