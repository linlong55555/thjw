<%@ page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增广告位</title>
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
<script type="text/javascript" src="${ctx}/js/ms/adv/init_add_advLocation.js"></script>
</head>
<body>
	<form id="advtypeForm" id="advtypeForm" method="post"
		enctype="multipart/form-data" accept-charset="utf-8">
		<table class="commonTab" border="1" style="border-color:#e3e8f9;">
			<tr bgcolor="#f4f4f4">
				<td height="22" colspan="4" align="left" style="margin-left:20px;"><b>广告位信息</b>
				</td>
				<td align="right"><a id="btnSave" href="javascript:void();"
					class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
				</td>
			</tr>
			
			<tr>
				<td width="10%" height="22">*广告位类型:</td>
				<td width="27%"><select id="typeCode" name="typeCode"
					style="width: 90%" onchange="showOrHide(this.value);">
						<c:forEach items="${advTypeList}" var="advTypeList">
							<option value="${advTypeList.typeCode}">${advTypeList.typeName}</option>
						</c:forEach>
				</select></td>
				<td height="22" align="right" style="min-width: 100px">*广告位地点:</td>
				<td align="left"><input type="text" id="advPosition"
					name="advPosition" style="width: 90%" /></td>
			</tr>
			
			<tr>
				<td width="10%" height="22">*文件大小:</td>
				<td><input type="text" id="advFilesize" name="advFilesize" style="width: 90%" /></td>
				<td height="22" align="right" style="min-width: 100px">*广告位格式:</td>
				<td align="left"><input type="text" id="advFormat"
					name="advFormat" style="width: 90%" /></td>
			</tr>
			
			<tr>
				<td width="10%" height="22">*尺寸:</td>
				<td><input type="text" id="advSize" name="advSize" style="width: 90%" /></td>
				<td height="22" align="right" style="min-width: 100px">*位置编号:</td>
				<td align="left"><input type="text" id="advNo"
					name="advNo" style="width: 90%" /></td>
			</tr>
			
			<tr>
				<td width="12%" height="22" align="right">*广告位状态:</td>
				<td><select id="typeState" name="typeState" style="width: 90%">
						<option value="0">可用</option>
						<option value="1">已坏</option>
				</select>
				</td>
			</tr>
			
			<tr>
				<td height="22" align="right">竞买详情(图片，描述):</td>
				<td colspan="4"><textarea name="typeContent" id="typeContent"
						style="width:100%;height:300px;visibility:hidden;"></textarea></td>
			</tr>
			
			<tr>
				<td height="22" align="right">竞买公告:</td>
				<td colspan="4"><textarea name="advAffiche" id="advAffiche"
						style="width:100%;height:300px;"></textarea></td>
			</tr>
			
			<tr>
				<td height="22" align="right">竞买须知:</td>
				<td colspan="4"><textarea name="advNotice" id="advNotice"
						style="width:100%;height:300px;"></textarea></td>
			</tr>
			
		</table>
	</form>
</body>
</html>