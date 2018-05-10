<%@ page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改广告位</title>
<%@ include file="/WEB-INF/jsp/common.jsp"%>
<link rel="stylesheet"
	href="${ctx}/js/tool/kindeditor/themes/default/default.css" />
<script type="text/javascript"
	src="${ctx}/js/tool/kindeditor/kindeditor-min.js"></script>
<script type="text/javascript"
	src="${ctx}/js/tool/kindeditor/lang/zh_CN.js"></script>
<script type="text/javascript"
	src="${ctx}/js/tool/easyui/plugins/jquery.combo.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.form.min.js"></script>
<script type="text/javascript"
	src="${ctx }/js/ms/adv/init_modify_advtype.js"></script>
</head>
<body>
	<form id="advForm" id="advForm" method="post"
		enctype="multipart/form-data" accept-charset="utf-8">
		<table class="commonTab" border="1" style="border-color:#e3e8f9;">
			<tr bgcolor="#f4f4f4">
				<td height="22" colspan="4" align="left" style="margin-left:20px;"><b>广告类型信息</b>
				</td>
				<td align="right"><a id="btnSave" href="javascript:void();"
					class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
				</td>
			</tr>
			
			<tr>
				<td width="12%" height="22"><input type="hidden" id="typeCode"
					name="typeCode" value="${advType.typeCode}" />
					<input type="hidden" id="parentTypeCode1" name="parentTypeCode1" value="${advType.parentTypeCode}" />*广告位父类型:</td>
				<td width="27%"><select id="parentTypeCode" name="parentTypeCode"
					style="width: 90%" onchange="showOrHide(this.value);">
							<option value="01">视频类</option>
							<option value="02">非视频类</option>
				</select></td>
				<td height="22" align="right" style="min-width: 100px">*广告类名称:</td>
				<td align="left"><input  type="text" id="typeName"
					name="typeName" style="width: 90%" value="${advType.typeName }" /></td>
			</tr>
		
			<tr>
				<td width="10%" height="22" align="right">*广告位状态:</td>
				<td><select id="typeState" name="typeState" style="width: 65%">
						<option value="0"
							<c:if test="${advType.typeState==0 }">selected</c:if>>可用</option>
						<option value="1"
							<c:if test="${advType.typeState==1 }">selected</c:if>>已坏</option>
				</select>
				</td>
			</tr>
			<tr>
				<td height="22" align="right">备注:</td>
				<td colspan="4"><textarea name="typeContent" id="typeContent"
						style="width:100%;height:300px;">${advType.typeContent}</textarea>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>