<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<div class="easyui-panel" title="功能导航"
	data-options="fit:true,border:false">
	<div class="easyui-accordion" data-options="fit:true">
		<div title="系统菜单" data-options="iconCls:'icon-save', selected:true"
			style="overflow: auto; padding: 10px;">
			<ul id="layout_west_tree" class="easyui-tree"
				data-options="url:'${pageContext.request.contextPath}/menuAction!getAllTreeNode.action',parentField:'pid',lines:true
						, onLoadSuccess:function(node, data){$(this).tree('collapseAll')}
						, onClick: function(node){
						  console.info(node);
						  if(node.attributes.url){
						  var url='${pageContext.request.contextPath}'+node.attributes.url;
						  addTabs({title:node.text,closable:true,href:url});
						  }
	                    }"></ul>
		</div>
		<div title="Title2" data-options="iconCls:'icon-reload'"
			style="padding: 10px;"></div>
	</div>

</div>


