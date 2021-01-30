<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="layout_center_tabs" class="easyui-tabs"
	data-options="fit:true,border:false">
	<div title="首页" href= "${pageContext.request.contextPath}/admin/dashboard.jsp" data-options="closable:true"></div>
</div>

<script type="text/javascript">
function addTabs(opts){
    var t = $('#layout_center_tabs');
    if(t.tabs('exists',opts.title)){
    	t.tabs('select',opts.title);
    }else{
    	t.tabs('add',opts);
    }
    
}
</script>

