<%@ page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>广告位管理</title>
<%@ include file="/WEB-INF/jsp/common.jsp"%>
<script type="text/javascript"
	src="${ctx }/js/tool/easyui/plugins/jquery.combo.js"></script>
<script type="text/javascript" src="${ctx }/js/ms/adv/init_adv.js"></script>
</head>
<body>
	<table border="0" width="100%" cellspacing="0" cellpadding="4">
		<tr bgcolor="#f4f4f4">
			<td height="22" colspan="7"><b>查询条件</b></td>
		</tr>
		<tr>
			<td width="8%" height="22" align="right">广告位类型：</td>
			<td width="13%"><select id="typeCode" name="typeCode"
				style="height: 22px">
					<option value="">--全部--</option>
					<c:forEach items="${advTypeList}" var="advTypeList">
						<option value="${advTypeList.typeCode}">${advTypeList.typeName}</option>
					</c:forEach>
			</select></td>
			<td width="8%" align="right">地区：</td>
			<td width="13%"><select id="code2" name="code2"
				onchange="selectCity(this.value);" style="height: 22px">
					<option value="">--全部--</option>
					<c:forEach items="${regionList}" var="regionList">
						<option value="${regionList.code}">${regionList.name}</option>
					</c:forEach>
			</select> <select id="code" name="code" style='display:none;height: 22px'>
					<option value="">--请选择--</option>
			</select>
			</td>
			<td width="8%" align="right">广告位状态：</td>
			<td width="21%"><select id="advStatus" name="advStatus"
				style="height: 22px">
					<option value="">--全部--</option>
					<option value="0">可用</option>
					<option value="1">已坏</option>
			</select>
			</td>
			<td align="right"><a id="btnQuery1" href="javascript:void(0);"
				class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
			</td>
		</tr>
	</table>

	<div style="margin-top:10px;"></div>
	<table id="dg1"></table>
</body>

</html>
