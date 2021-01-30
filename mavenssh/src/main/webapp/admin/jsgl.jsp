<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
 <script type="text/javascript">
 
 if ($.fn.datagrid){
		$.fn.datagrid.defaults.pageSize = 10;//这里一定要用datagrid.defaults.pageSize，用pagination.defaults.pageSize一直不行
		$.fn.datagrid.defaults.pageList = [10,20,30,50,100];//这里一定要有，不然上面的也不起效

		}
 
 
 $(function(){
	    $('#admin_jsgl_dg').datagrid({
	        url:'',
	        fit:true,
	        fitColumns:true,
	        border:false,
	        pagination:true,
	        idField:'id',
	        sortName:'name',
	        sortOrder:'asc',
	        checkOnSelect:false,
	        selectOnCheck:false,
	        singleSelect:true,
	        
	        frozenColumns:[[
	            {field:'id',title:'编号',width:150,hidden: true},
	            {field:'ck',checkbox:true},
	            {field:'name',title:'登录名称',width:150},
	        ]],
	        columns:[[


	            {field:'role',title:'角色',width:150,align:'left'},
	            {field:'creaetime',title:'创建时间',width:150},
	            {field:'lastupdatetime',title:'更新时间',width:150}
	        ]],
	        toolbar:[{
	        	text:'添加',
	        	iconCls:'icon-add',
	        	handler: function(){
	        		alert('添加'); 
	        	}
	        },'-',{
	        	text:'删除',
	        	iconCls:'icon-remove',
              handler: function(){
            	  alert('删除'); 
	        	}
	        },'-',{
	        	text:'修改',
	        	iconCls:'icon-edit',
              handler: function(){
             	 
            	  alert('修改'); 
	        		
	        	}
	        	
	        },'-',{
	        	text:'批量修改',
	        	iconCls:'icon-edit',
              handler: function(){
             	 
            	  alert('批量修改'); 
	        		
	        	}
	        	
	        }
	        
	        
	        ]
	    });
});
 
 </script>
    
<div id="admin_jsgl_layout" class="easyui-layout" data-options="fit:true">
    <div data-options="region:'north',title:'高级查询 ',split:true" style="height:100px;">
    <form id="admin_jsgl_searchform">
          用户名： <input name="name"/>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun1();">查询</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="clearFun();">清空</a>
    </form>
    </div>
    <div data-options="region:'center',border:false" >
    <table id="admin_jsgl_dg" class="easyui-datagrid" data-options="rownumbers:true,singleSelect:false,
     	onLoadSuccess: function(data){
        
        console.info('权限管理');

         

	}
    " ></table>
    </div>
</div>