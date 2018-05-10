<%@ page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>竞拍属性管理</title>
<%@ include file="/WEB-INF/jsp/common.jsp"%>
<script type="text/javascript"
	src="${ctx }/js/tool/easyui/plugins/jquery.combo.js"></script>
<!--script type="text/javascript" src="${ctx }/js/tool/My97DatePicker/WdatePicker.js"></script-->
<script type="text/javascript">
var ctx="${ctx}";
</script>
<script type="text/javascript" src="${ctx }/js/ms/auctionAttribute/init.js"></script>
</head>
<body>
	<table border="0" width="100%" cellspacing="0" cellpadding="4">
		<tr bgcolor="#f4f4f4">
			<td height="22" colspan="7"><b>查询条件</b></td>
		</tr>
		<tr>
			<td width="8%" height="22" align="right">属性名称：</td>
			<td width="13%">
			    <input type="text" id="attrName" name="attrName" style="height: 22px"/>
			</td>
			<td width="8%" align="right">是否使用：</td>
			<td width="21%">
			    <select id="isUse" name="isUse" style="height: 22px">
			      <option value="">全部</option>
			      <option value="0">未用</option>
			      <option value="1">使用</option>
			    </select>
			</td>
			<td width="8%" align="right">是否必选：</td>
			<td width="21%">
                <select id="isMustSelect" name="isMustSelect" style="height: 22px">
                  <option value="">全部</option>
                  <option value="0">非必选</option>
                  <option value="1">必选</option>
                </select>
			</td>
			<td align="right">
			   <a id="btnQuery1" href="javascript:void(0);"
				  class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
			</td>
		</tr>
	</table>

	<div style="margin-top:10px;"></div>
	<table id="datagrid"></table>
</body>

</html>
