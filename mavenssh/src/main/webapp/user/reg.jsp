<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<script type="text/javascript">
   $( function(){
	   $('#user_reg_regform').form({
		   url:'${pageContext.request.contextPath}/userAction!addReg.action',
           success:function(data){
           	
           	var obj=jQuery.parseJSON(data);
           	 console.info(obj);
           	 if (obj.success){
           		$('#user_reg_regDialog').dialog('close');
           	 }
           	  
           	 $.messager.show({
           			title:'提示',
           			msg:obj.msg,
           			timeout:5000,
           			showType:'slide'
           		});
		   
           }
	   });
	   $('#user_reg_regform input').bind('keyup', function(event){
		   if (event.keyCode=='13'){
			   $('#user_reg_regform').submit();
		   }
	   });
   });
   
</script>  
<div id="user_reg_regDialog" class="easyui-dialog" title="注册" style="width:400px;height:200px;padding:10px"
    data-options="resizable:true,modal:true,closed:true,
    				buttons: [{
					text:'注册',
					iconCls:'icon-ok',
					handler:function(){
					$('#user_reg_regform').submit();
					
					   <!--  console.info($('#index_regform')); -->
					  <!--  var t = $(this); -->
<%-- 					 	$('#user_reg_regform').form('submit', {
            url:'${pageContext.request.contextPath}/userAction!addReg.action',
            success:function(data){
            	
            	var obj=jQuery.parseJSON(data);
            	 console.info(obj);
            	 if (obj.success){
            		$('#user_reg_regDialog').dialog('close');
            	 }
            	  
            	 $.messager.show({
            			title:'提示',
            			msg:obj.msg,
            			timeout:5000,
            			showType:'slide'
            		});
    	    }
         
     });  --%> 
		
		               }
				
					}]
    ">
    
  <form id="user_reg_regform"  method="post">
   <table>
     
	     <tr>
	      <th>用户名</th>
	      <td><input name="name" class="easyui-validatebox" data-options="required:true"/>
	      </td>
	     </tr>
	     <tr>
	      <th>密码</th>
	      <td><input name="pwd" type="password" class="easyui-validatebox" data-options="required:true ,validType:'isPwd'"/>
	      </td>
	     </tr> 
	      <tr>
	      <th>重复密码</th>
	      <td><input name="rePwd" class="easyui-validatebox" data-options="required:true,validType:'eqPwd[\'#user_reg_regform input[name=pwd]\']'"/>
	      </td>
	     </tr>     
     </table>
   </form>

  </div>