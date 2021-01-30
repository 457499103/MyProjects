<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">

/**
 * @author 孙宇
 * 
 * @requires jQuery,EasyUI
 * 
 * 扩展datagrid，添加动态增加或删除Editor的方法
 * 
 * 例子如下，第二个参数可以是数组
 * 
 * datagrid.datagrid('removeEditor', 'cpwd');
 * 
 * datagrid.datagrid('addEditor', [ { field : 'ccreatedatetime', editor : { type : 'datetimebox', options : { editable : false } } }, { field : 'cmodifydatetime', editor : { type : 'datetimebox', options : { editable : false } } } ]);
 * 
 */
$.extend($.fn.datagrid.methods, {
	addEditor : function(jq, param) {
		if (param instanceof Array) {
			$.each(param, function(index, item) {
				var e = $(jq).datagrid('getColumnOption', item.field);
				e.editor = item.editor;
			});
		} else {
			var e = $(jq).datagrid('getColumnOption', param.field);
			e.editor = param.editor;
		}
	},
	removeEditor : function(jq, param) {
		if (param instanceof Array) {
			$.each(param, function(index, item) {
				var e = $(jq).datagrid('getColumnOption', item);
				e.editor = {};
			});
		} else {
			var e = $(jq).datagrid('getColumnOption', param);
			e.editor = {};
		}
	}
});


if ($.fn.datagrid){
	$.fn.datagrid.defaults.pageSize = 10;//这里一定要用datagrid.defaults.pageSize，用pagination.defaults.pageSize一直不行
	$.fn.datagrid.defaults.pageList = [10,20,30,50,1000];//这里一定要有，不然上面的也不起效

	}
	
 $(function(){
	 
	    var editRow = undefined;
	    $('#admin_yhgl_dg').datagrid({
	        url:'${pageContext.request.contextPath}/userAction!dataGrid.action',
	        fit:true,
	        //fitColumns:true,
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
	            {field:'name',title:'登录名称',width:150,editor: {type: 'validatebox', options:{required :true}}},
	        ]],
	        columns:[[


	            {field:'pwd',title:'密码',width:150,align:'left',
	            	formatter: function(value,row,index){
	            		return '********';
	            	},editor: {type: 'validatebox', options:{required :true}}
	            
	            },
	            {field:'creaetime',title:'创建时间',width:150,editor: {type: 'datetimebox', options:{required :true}}},
	            {field:'lastupdatetime',title:'更新时间',width:150,editor: {type: 'datetimebox', options:{required :true}}}
	        ]],
	        toolbar:[{
	        	text:'行编辑添加',
	        	iconCls:'icon-add',
	        	handler: function(){
	        		
	        		if(editRow!=undefined){
	        			$('#admin_yhgl_dg').datagrid('endEdit', editRow);
	        		}
	        		if(editRow==undefined){
	        			
	        			changeEditorAddRow();
	        			$('#admin_yhgl_dg').datagrid('insertRow',{
		        			index:0,
		        			row :{}
		        		});
		        		var getrows =$('#admin_yhgl_dg').datagrid('getRows');
		        		console.info(getrows.length);
		        		$('#admin_yhgl_dg').datagrid('beginEdit', 0);
		        		editRow=0;
	        		}
	        		
	        	}
	        },'-',{
	        	text:'添加',
	        	iconCls:'icon-add',
	        	handler: function(){
	        		appendFun();
	        	}
	        },'-',{
	        	text:'删除',
	        	iconCls:'icon-remove',
                 handler: function(){
                	 deleteFun();
	        	}
	        },'-',{
	        	text:'修改',
	        	iconCls:'icon-edit',
                 handler: function(){
                	 
                	 editFun();
	        		
	        	}
	        	
	        },'-',{
	        	text:'批量修改',
	        	iconCls:'icon-edit',
                 handler: function(){
                	 
                	 edit1Fun();
	        		
	        	}
	        	
	        },'-',{
	        	text:'行修改',
	        	iconCls:'icon-edit',
                 handler: function(){
                	 
                	 var rowEdit= $('#admin_yhgl_dg').datagrid('getSelections');
                	 console.info(rowEdit);
                	 if(rowEdit.length==1){
                		
                		 if(editRow!=undefined){
                			$('#admin_yhgl_dg').datagrid('endEdit', editRow);
                		}
                		if(editRow==undefined){
                			changeEditorEditRow();
                			var index = $('#admin_yhgl_dg').datagrid('getRowIndex', rowEdit[0]);
        	        		$('#admin_yhgl_dg').datagrid('beginEdit', index);
        	        		editRow=index;
                		}
                		 
                	 }
	        		
	        	}
	        	
	        },'-',{
	        	text:'保存行修改',
	        	iconCls:'icon-edit',
                 handler: function(){
                	 
                	// $('#admin_yhgl_dg').datagrid('endEdit', 0);
                	 $('#admin_yhgl_dg').datagrid('endEdit', editRow);
	        		
	        	}
	        	
	        },'-',{
	        	text:'取消行修改',
	        	iconCls:'icon-redo',
                 handler: function(){
                	 editRow = undefined;
                	// $('#admin_yhgl_dg').datagrid('endEdit', 0);
                	 $('#admin_yhgl_dg').datagrid('rejectChanges');
	        		
	        	}
	        	
	        }
	        
	        
	        ],
	        onAfterEdit: function (rowIndex, rowData, changes){
	        	console.info(rowData);
	        	console.info("提交行编辑内容去后台。。");
	        	var inserted = $('#admin_yhgl_dg').datagrid('getChanges', 'inserted');
	        	var updated = $('#admin_yhgl_dg').datagrid('getChanges', 'updated');
	        	
	        	if (inserted.length<0 && updated.length<0 ){
	        		editRow = undefined;
	        		$('#admin_yhgl_dg').datagrid('unselectAll');
	        		return;
	        	}
	        	
	        	console.info(inserted);
	        	console.info(updated);
	        	var url='';
	        	
	        	if (inserted.length>0){
	        		url='${pageContext.request.contextPath}/userAction!add.action';
	        	}
	        	if (updated.length>0){
	        		url='${pageContext.request.contextPath}/userAction!update.action';
	        	}
	        	console.info(inserted.length>0);
	        	console.info(updated.length>0);
	        	console.info(url);
	        	
	        	
				  $.ajax({
					  url: url,
					  data:rowData,
					  dataType:'json',
					  success : function(r){
						  
						  if(r && r.success){
							  $('#admin_yhgl_dg').datagrid('acceptChanges'); 
							  
							  $.messager.show({
					     			title:'成功提示',
					     			msg:r.msg,
					     			timeout:5000,
					     			showType:'slide'
					     		});
						  }else{
							  $('#admin_yhgl_dg').datagrid('rejectChanges'); 
							  $.messager.alert('错误提示',r.msg,'error');
							  
						  }
						  editRow = undefined;
                          
					  }
					  
					  
				  });
	        	
	        	
	        	
	        	
	        },
	        onDblClickRow: function (rowIndex, rowData){
	        	//changeEditorAddRow();
        		if(editRow!=undefined){
        			$('#admin_yhgl_dg').datagrid('endEdit', editRow);
        		}
        		if(editRow==undefined){
        			changeEditorEditRow();
	        		$('#admin_yhgl_dg').datagrid('beginEdit',rowIndex);
	        		editRow=rowIndex;
        		}
	        },
	        onRowContextMenu(e, rowIndex, rowData){
		    	
		    	 e.preventDefault();
		    	 $('#righ_click_menu').menu('show', {left: e.pageX, e.pageY});
		        	
		        }
	          
	    })

	    
 });
 
/*  $('#admin_yhgl_dg').datagrid({
		toolbar: '#admin_yhgl_tb'
	}); */
 
 function searchFun(){
	 $('#admin_yhgl_dg').datagrid('load',{name:$('#admin_yhgl_tb input[name=name]').val()});
 }
 
 serializeObject = function(form) {
		var o = {};
		$.each(form.serializeArray(), function(index) {
			if (o[this['name']]) {
				o[this['name']] = 0[this['name']] + ',' + this['value'];
			} else {
				o[this['name']] = this['value'];
			}
		});
        console.info(o);
		return o;
	};
 
 function searchFun1(){
	 console.info(serializeObject($('#admin_yhgl_searchform')));
	 $('#admin_yhgl_dg').datagrid('load',serializeObject($('#admin_yhgl_searchform')));
	 //$('#admin_yhgl_dg').datagrid('load',{name:$('#admin_yhgl_searchform input').val(),creatdatatimestart:$('#admin_yhgl_searchform input').val(),creatdatatimeend:$('#admin_yhgl_searchform input').val() });		 
 }
 
 function clearFun(){
	 $('#admin_yhgl_tb input').val('');
	 $('#admin_yhgl_dg').datagrid('load',{});
 }
 
 function appendFun(){
	 $('#admin_yhgl_addform input').val('');
	 $('#admin_yhgl_adddialog').dialog('open');
 }
 
 function deleteFun(){
	var rows= $('#admin_yhgl_dg').datagrid('getChecked');
	  console.info(rows);
	  var ids=[];
	  if(rows.length>0){
		  
		  $.messager.confirm('Confirm','Are you sure you want to delete record?',function(r){
			    if (r){
					  for(var i=0; i<rows.length;i++){
						  ids.push(rows[i].id);
					  }
					  $.ajax({
						  url: '${pageContext.request.contextPath}/userAction!remove.action',
						  data:{ids: ids.join(',')
						  },
						  dataType:'json',
						  success : function(r){
							  
							  $('#admin_yhgl_dg').datagrid('load');
							  $('#admin_yhgl_dg').datagrid('unselectAll');
							  $.messager.show({
					     			title:'提示',
					     			msg: r.msg,
					     			timeout:5000,
					     			showType:'slide'
					     		});
						  }
						  
						  
					  });
					  
			    }
			});


		  
	  }else{
		  $.messager.show({
     			title:'提示',
     			msg:'请勾选要删除的记录',
     			timeout:5000,
     			showType:'slide'
     		});
		  
	  }
	
    
	//var rows1= $('#admin_yhgl_dg').datagrid('getSelected');
	//console.info(rows1);
 }
 
 function selectFun(){
		//var rows= $('#admin_yhgl_dg').datagrid('getChecked');
		//console.info(rows);
		var rows2= $('#admin_yhgl_dg').datagrid('getSelected');
		console.info(rows2);
		
	 }
 
 function edit1Fun(){
	 
	 var rows3= $('#admin_yhgl_dg').datagrid('getChecked');
	 var ids=[];
	 if(rows3.length>0){

        var d1= $('<div/>').dialog({
		 width: 500,
		 height:200,
		 href:'${pageContext.request.contextPath}/admin/yhgledit1.jsp',
		 modal: true,
		 title:'批量编辑',
		 buttons:[{
				text:'Ok',
				iconCls:'icon-ok',
				handler:function(){
					
					  for(var i=0; i<rows3.length;i++){
							  ids.push(rows3[i].id);
						  }
						  console.info(ids);
						  console.info(rows3);
						  var myJSON = JSON.stringify(rows3);
						  console.info(myJSON);
						  /* $.ajax({
							  url: '${pageContext.request.contextPath}/userAction!batchupdate.action',
							  data:{ids: ids.join(',')
							  },
							  dataType:'json',
							  success : function(r){
								  
								  $('#admin_yhgl_dg').datagrid('load');
								  $('#admin_yhgl_dg').datagrid('selectedAll');
								  $.messager.show({
						     			title:'提示',
						     			msg:'r.msg',
						     			timeout:5000,
						     			showType:'slide'
						     		});
							  }
							  
							  
						  });*/
						  
				    
				}
			},{
				text:'Cancel',
				handler:function(){
				d1.dialog('close');
				}
			}],
			onClose: function (){
				$(this).dialog('destroy');
			},
			onLoad: function(){
				//console.info( $('#admin_yhgl_edit1form'));
				
				$('#admin_yhgl_edit1form').form('load', rows3[0]);
			}
	 });
	 }else{
		  $.messager.show({
    			title:'提示',
    			msg:'请勾选要编辑的记录',
    			timeout:5000,
    			showType:'slide'
    		});
		  
	  }
 }

 
 function editFun(){
	 
	 var rows2= $('#admin_yhgl_dg').datagrid('getSelected');
	 console.info(rows2);
	 
	 $('#admin_yhgl_editform input[name=id]').val(rows2.id);
	 $('#admin_yhgl_editform input[name=name]').val(rows2.name);
	 $('#admin_yhgl_editform input[name=creaetime]').val(rows2.creaetime);
	 $('#admin_yhgl_editform input[name=lastupdatetime]').val(rows2.lastupdatetime);
	 $('#admin_yhgl_editdialog').dialog('open');
	 
 }
 
 function  edit2Fun(){
	 
 };
 
 function changeEditorAddRow() {
	 
	  $('#admin_yhgl_dg').datagrid('removeEditor',[ 'creaetime','lastupdatetime' ] ); 
	 
 	 $('#admin_yhgl_dg').datagrid('addEditor', {
		    field:'pwd',
			editor: {type: 'validatebox', options:{required :true}}
					
			});
	 
	 
	 
 };
 
 
function changeEditorEditRow() {
	$('#admin_yhgl_dg').datagrid('removeEditor', 'pwd' );
	
	$('#admin_yhgl_dg').datagrid('addEditor', [{field:'creaetime',
		editor: {type: 'datetimebox', options:{required :true}}
	
	},{field:'lastupdatetime',
		editor: {type: 'datetimebox', options:{required :true}}
	
	}]);
 };
 

 
</script>

<div id="admin_yhgl_layout" class="easyui-layout" data-options="fit:true">
    <div data-options="region:'north',title:'高级查询 ',split:true" style="height:200px">
    <form id="admin_yhgl_searchform">
    <table id="admin_yhgl_tb" class="tableForm datagrid-toolbar" style="width:100%;height:100%">
    <tr>
      <th> 用户名： </th>
      <td><input name="name"/>
      </td>
    </tr>
    <tr>
    <th> 创建时间：</th>
    <td> <input name="creatdatatimestart" class="easyui-datetimebox" editable="false" style="width:140px" /> 至
    <input name="creatdatatimeend" class="easyui-datetimebox"  style="width:150px" />
    </td>
    </tr> 
    <tr>
    <th> 最后修改时间：</th>
    <td> <input name="lastupdatedatatimestart" class="easyui-datetimebox" editable="false" style="width:140px" /> 至
    <input name="lastupdatedatatimeend" class="easyui-datetimebox"  style="width:150px" />
    <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun1();">查询</a>
    <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="clearFun();">清空</a>
    </tr>  
    </table>
    </form>
    <form  id="admin_yhgl_updateform" >
             用户名： <input id="id1" name="name" value="1234"/>
             密码： <input name="pwd"/>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="updateFun1();">更新</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="clearFun();">清空</a>
    </form>
    </div>
    <div data-options="region:'center',border:false" >
    <table id="admin_yhgl_dg" class="easyui-datagrid" data-options="rownumbers:true,singleSelect:false,
     	onLoadSuccess: function(data){
          
        console.info('数据加载成功！');
         

	}
    " ></table>
    </div>
</div>


<div id="admin_yhgl_adddialog" title="添加用户" class="easyui-dialog" data-options="closed:true, modal:true,buttons: [{
					text:'Ok',
					iconCls:'icon-ok',
					handler:function(){
						alert('ok');
						$('#admin_yhgl_addform').form('submit',{url:'${pageContext.request.contextPath}/userAction!add.action', 
						success : function(r){
					var obj=jQuery.parseJSON(r);
           	        console.info(obj);
           	        if (obj.success){

                    $('#admin_yhgl_dg').datagrid('insertRow', {
           	            index:0,
           	            row: obj.obj
           	          });
                      $('#admin_yhgl_adddialog').dialog('close');
                 
           	        }
           	  
           	 $.messager.show({
           			title:'提示',
           			msg:obj.msg,
           			timeout:5000,
           			showType:'slide'
           		});
						
						
						}
						
						
						
						});
					}
				},{
					text:'Cancel',
					handler:function(){
						$('#admin_yhgl_adddialog').dialog('close');
					}
				}]

" style="width:500px; height:200px;" >

<form id="admin_yhgl_addform" method="post">
<table>
<tr>
 <th>编号</th>
 <td><input name="id" readonly="readonly"/>
 </td>
  <th>登录名称</th>
 <td><input name="name" class="easyui-validatebox" data-options="required:true"/>
 </td>
</tr>

<tr>
 <th>密码</th>
 <td><input name="pwd" type="password" class="easyui-validatebox" data-options="required:true"/>
 </td>
  <th>创建时间</th>
 <td><input name="creaetime" readonly="readonly"/>
 </td>
</tr>

<tr>
 <th>最后修改时间</th>
 <td><input name="lastupdatetime" readonly="readonly"/>
 </td>
  <th></th>
  <td></td>
</tr>

</table>
</form>
</div>


<div id="admin_yhgl_editdialog" title="修改用户" class="easyui-dialog" data-options="closed:true, modal:true,buttons: [{
					text:'Ok',
					iconCls:'icon-ok',
					handler:function(){
						alert('ok');
						$('#admin_yhgl_editform').form('submit',{url:'${pageContext.request.contextPath}/userAction!update.action', 
						success : function(r){
					var obj=jQuery.parseJSON(r);
           	        console.info(obj);
           	        if (obj.success){

                      $('#admin_yhgl_dg').datagrid('load');
                      $('#admin_yhgl_editdialog').dialog('close');
                 
           	        }
           	  
           	 $.messager.show({
           			title:'提示',
           			msg:obj.msg,
           			timeout:5000,
           			showType:'slide'
           		});
						
						
						}
						
						
						
						});
					}
				},{
					text:'Cancel',
					handler:function(){
						$('#admin_yhgl_editdialog').dialog('close');
					}
				}]

" style="width:500px; height:200px;" >

<form id="admin_yhgl_editform" method="post">
<table>
<tr>
 <th>编号</th>
 <td><input name="id" readonly="readonly"/>
 </td>
  <th>登录名称</th>
 <td><input name="name" />
 </td>
</tr>

<tr>
 <th>密码</th>
 <td><input name="pwd" type="password" class="easyui-validatebox" data-options="required:true"/>
 </td>
  <th>创建时间</th>
 <td><input name="creaetime" readonly="readonly"/>
 </td>
</tr>

<tr>
 <th>最后修改时间</th>
 <td><input name="lastupdatetime" class="easyui-datetimebox"/>
 </td>
  <th></th>
  <td></td>
</tr>

</table>
</form>
</div>

<div id="righ_click_menu" class="easyui-menu" style="width:120px ; display: none;">
  <div onclick="appendFun();" iconCls="icon-add">添加</div>
  <div onclick="deleteFun();" iconCls="icon-remove">删除</div>
  <div onclick="editFun();" iconCls="icon-edit">编辑</div>
</div>



