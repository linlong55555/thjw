<%@ page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增竞拍活动</title>
<%@ include file="/WEB-INF/jsp/common.jsp"%>
<script type="text/javascript"
	src="${ctx}/js/tool/easyui/plugins/jquery.form.js"></script>
<!-- script type="text/javascript" src="${ctx}/js/jquery.form.min.js"></script-->
<script type="text/javascript">
var ctx="${ctx}";
</script>
<script type="text/javascript" src="${ctx}/js/ms/auctionAttribute/add.js"></script>
</head>
<body>
	<form id="advForm" name="advForm" method="post">
		<table class="commonTab" border="1" style="border-color:#e3e8f9;">
			<tr bgcolor="#f4f4f4">
				<td height="22" colspan="4" align="left" style="margin-left:20px;"><b>竞拍属性信息</b>
				</td>
				<td align="right"><a id="btnSave" href="javascript:void(0);"
					class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
				</td>
			</tr>
			<tr>	
				<td width="10%" height="22" align="right">*属性编号:</td>
				<td width="27%" colspan="4">
				<input type="text" name="attrId" id="attrId" style="width: 60%" onblur="attrId_blur(this);"
				     class="easyui-validatebox" required="true"/>
				</td>
			</tr>
			<tr>
				<td width="10%" height="22">*属性名称:</td>
				<td width="27%" colspan="4">
				<input type="text" name="attrName" id="attrName" style="width: 60%" 
				    class="easyui-validatebox" required="true"/>
				</td>
			</tr>
			<tr>
				<td width="10%" height="22">*是否使用:</td>
				<td width="27%" colspan="4">
				<select name="isUse" id="isUse" style="width: 60%">
				    <option value="1">使用</option>
				    <option value="0">不使用</option>
				</select>
				</td>
			</tr>
			<tr>
				<td width="10%" height="22">*是否必选:</td>
				<td width="27%" colspan="4">
				<select name="isMustSelect" id="isMustSelect" style="width: 60%">
                    <option value="0">非必选</option>
                    <option value="1">必选</option>
                </select>
				</td>
			</tr>
			<tr>
				<td width="10%" height="22">*元素类型:</td>
				<td width="27%" colspan="4">
				<input type="text" name="eleType" id="eleType" style="width: 60%" 
				    class="easyui-validatebox" required="true"/>
				</td>
			</tr>
			<tr>
				<td width="10%" height="22">*默认值:</td>
				<td width="27%" colspan="4">
				<input name="defaultValue" id="defaultValue" style="width: 60%"
				    class="easyui-validatebox" required="true"/>
				</td>
			</tr>

		</table>
	</form>
</body>
</html>