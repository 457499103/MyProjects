<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello Admin</title>
</head>
<body>
  <h1>${msg}</h1>
  
<!-- <input type="button" value="点我退出" onclick="javascrtpt:close()" /> -->
<a href="javascript:window.opener=null;window.open('','_self');window.close();">关闭</a>
    
</body>

<!-- <script>
function close(){
	
    window.opener = null;
        window.open('','_self');
        window.close(); 
        
        if (confirm("您确定要退出报名系统吗？")) {
            window.opener = null;
            window.open('', '_self');
            window.close();

	
}
	  
</script>  -->

<!-- <script type="text/JavaScript">
    var mywin=window.open('http://localhost:8080/mavenssh/admin/hello1'); //将新打的窗口对象，存储在变量mywin中
    mywin.close();
</script> -->
</body>
</html>