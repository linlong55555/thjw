<%@ page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>广告押金管理</title>
<%@ include file="/WEB-INF/jsp/common.jsp"%>
<script type="text/javascript"
	src="${ctx }/js/tool/easyui/plugins/jquery.combo.js"></script>
<script type="text/javascript"
	src="${ctx }/js/ms/deposit/init_deposit.js"></script>
</head>
<body>
	<table border="0" width="100%" cellspacing="0" cellpadding="4">
		<tr bgcolor="#f4f4f4">
			<td height="22" colspan="7"><b>查询条件</b>
			</td>
		</tr>
		<tr>
			<td width="8%" align="right" height="22px">旺乐用户名：</td>
			<td width="25%"><input type="text" id="memberName"
				name="memberName" style="width: 60%;padding:0px " />
			</td>
			<td width="8%" height="22" align="right">是否归还：</td>
			<td width="20%"><select name="isReturn" id="isReturn"
				style="height: 22px;">
					<option value="">--全部--</option>
					<option value="0">未归还</option>
					<option value="1">已归还</option>
					<option value="2">违约</option>
			</select></td>
			<td align="right"><a id="btnQuery1" href="javascript:void(0);"
				class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
			</td>
		</tr>
	</table>
	<div style="margin-top:10px;"></div>
	<table id="dg1"></table>
</body>

</html>
