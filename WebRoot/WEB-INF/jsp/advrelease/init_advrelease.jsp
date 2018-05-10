<%@ page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>广告发布管理</title>
<%@ include file="/WEB-INF/jsp/common.jsp"%>
<script type="text/javascript"
	src="${ctx }/js/tool/easyui/plugins/jquery.combo.js"></script>
<script type="text/javascript"
	src="${ctx }/js/ms/advrelease/init_advrelease.js"></script>
</head>
<body>
	<table border="0" width="100%" cellspacing="0" cellpadding="4">
		<tr bgcolor="#f4f4f4">
			<td height="22" colspan="7"><b>查询条件</b>
			</td>
		</tr>
		<tr>
			<td width="8%" height="22" align="right">开始展示时间：</td>
			<td width="33%"><input type="text" id="beginDateS"
				name="beginDateS" class="easyui-datebox" />~<input type="text"
				id="endDateS" name="endDateS" class="easyui-datebox"/>
			</td>
			<td width="8%" height="22" align="right">结束展示时间：</td>
			<td width="33%"><input type="text" id="beginDateE"
				name="beginDateE" class="easyui-datebox" />~<input type="text"
				id="endDateE" name="endDateE" class="easyui-datebox" /></td>
			<td align="right"><a id="btnQuery1" href="javascript:void(0);"
				class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
			</td>
		</tr>
	</table>
	<div style="margin-top:10px;"></div>
	<table id="dg1"></table>
</body>

</html>
