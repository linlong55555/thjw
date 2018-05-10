<%@ page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增竞拍活动</title>
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
<script type="text/javascript" src="${ctx}/js/ms/adv/init_add_adv.js"></script>
</head>
<body>
	<form id="advForm" id="advForm" method="post"
		enctype="multipart/form-data" accept-charset="utf-8">
		<table class="commonTab" border="1" style="border-color:#e3e8f9;">
			<tr bgcolor="#f4f4f4">
				<td height="22" colspan="4" align="left" style="margin-left:20px;"><b>竞拍信息</b>
				</td>
				<td align="right"><a id="btnSave" href="javascript:void();"
					class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
				</td>
			</tr>
			<tr>	
				<td width="10%" height="22" align="right">*活动描述:</td>
				<td width="27%" colspan="4">
				<input type="text" name="desc" id="desc" style="width: 60%"/>
				</td>
			</tr>
			<tr>
				<td width="10%" height="22">*广告类型:</td>
				<td width="27%" colspan="4"><select id="typeCode" name="typeCode"
					style="width: 10%" onchange="">
						<c:forEach items="${advTypes}" var="advType">
							<option value="${advType.typeCode}">${advType.typeName}</option>
						</c:forEach>
				</select></td>
			</tr>

		</table>
	</form>
</body>
</html>