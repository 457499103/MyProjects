<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<form id="admin_yhgl_edit1form" method="post">
<table>
<tr>
 <th>编号</th>
 <td><input name="id" readonly="readonly"/>
 </td>
  <th>登录名称</th>
 <td><input name="name" readonly="readonly"/>
 </td>
</tr>

<tr>
 <th>密码</th>
 <td><input name="pwd" class="easyui-validatebox" data-options="required:true"/>
 </td>
  <th>创建时间</th>
 <td><input name="creaetime" readonly="readonly"/>
 </td>
</tr>

<tr>
 <th>最后修改时间</th>
 <td><input class="easyui-datetimebox" name="lastupdatetime"  data-options="required:true,showSeconds:true" style="width:150px"  />
 </td>
  <th></th>
  <td></td>
</tr>

</table>
</form>
