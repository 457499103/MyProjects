<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<script type="text/javascript">
   
   $(function(){
	   $('#user_login_loginform').form({
		   url:'${pageContext.request.contextPath}/userAction!login.action',
           success:function(data){
           	
           	var obj=jQuery.parseJSON(data);
           	 console.info(obj);
           	 if (obj.success){
                 $('#user_login_loginDialog').dialog('close');
                 
           	 }
           	  
           	 $.messager.show({
           			title:'提示',
           			msg:obj.msg,
           			timeout:5000,
           			showType:'slide'
           		});
		   
           }
	   });
	   $('#user_login_loginform input').bind('keyup', function(event){
		   if (event.keyCode=='13'){
			   $('#user_login_loginform').submit();
		   }
	   });
   });
   
 	function clearForm(){
		$('#user_login_loginform').form('clear');
	} 
 	
   
</script>  
    
<div id="user_login_loginDialog" class="easyui-dialog" title="登录" style="width:400px;height:200px;padding:10px"
    data-options="resizable:true,modal:true,closable:false,
    				buttons: [{
					text:'注册',
					iconCls:'icon-ok',
					handler:function(){
					    $('#user_reg_regform input').val('');
						$('#user_reg_regDialog').dialog('open');
					}
				},{
					text:'登录',
					handler:function(){
						$('#user_login_loginform').submit();

						
					}
				}]
    ">
    
    <form id="user_login_loginform"  method="post">
     <table>
     
	     <tr>
	      <th>用户名</th>
	      <td><input name="name" class="easyui-validatebox" data-options="required:true" />
	      </td>
	     </tr>
	     <tr>
	      <th>密码</th>
	      <td><input name="pwd" type="password" class="easyui-validatebox" data-options="required:true ,validType:'isPwd'"/>
	      </td>
	     </tr>
    
     </table>
    </form>
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">Clear</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="reg()">Reg</a>
</div>