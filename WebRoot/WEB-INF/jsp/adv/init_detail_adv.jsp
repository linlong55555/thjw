<%@ page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>广告位详情</title>
<%@ include file="/WEB-INF/jsp/common.jsp"%>
<link rel="stylesheet"
	href="${ctx}/js/tool/kindeditor/themes/default/default.css" />
<script type="text/javascript"
	src="${ctx}/js/tool/kindeditor/kindeditor-min.js"></script>
<script type="text/javascript"
	src="${ctx}/js/tool/kindeditor/lang/zh_CN.js"></script>
<script type="text/javascript"
	src="${ctx}/js/tool/easyui/plugins/jquery.combo.js"></script>
</head>
<body>
	<form id="advForm" id="advForm" method="post">
		<table class="commonTab" border="1" style="border-color:#e3e8f9;">
			<tr bgcolor="#f4f4f4">
				<td height="22" colspan="5" align="left" style="margin-left:20px;"><b>广告位详细信息</b>
				</td>
			</tr>
			<tr>
				<td width="10%" height="22">广告位类型:</td>
				<td width="27%" align="left"><span style="width: 65%">${advAttribute.advType.typeName}</span>
				</td>
				<td width="10%" height="22" align="right">地区名称:</td>
				<td width="27%" align="left"><span style="width: 65%">${advAttribute.regionName}</span>
				</td>
				<td rowspan="5" align="left"><img
					src="../upload/adv/${advAttribute.imgName}" width="220px"
					height="220px" style="cursor: pointer;" /></td>
			</tr>
			<tr>
				<td height="22" align="right" style="min-width: 100px">*体验馆/频道:</td>
				<td align="left"><span style="width: 85%">${advAttribute.advArea}</span>
				</td>
				<td height="22" align="right" style="min-width: 140px">*体验馆内区域/页面地址:</td>
				<td align="left"><span style="width: 85%">${advAttribute.advPage}</span>
				</td>
			</tr>
			<tr>
				<td height="22" align="right" style="min-width: 100px">*网页中具体位置:</td>
				<td align="left"><span style="width: 85%">${advAttribute.advPosition}</span>
				</td>
			</tr>
			<tr>
				<td width="10%" height="22" align="right">格式:</td>
				<td align="left"><span style="width: 65%">${advAttribute.advPattern}</span>
				</td>
				<td width="10%" height="22" align="right">广告位尺寸:</td>
				<td align="left"><span style="width: 65%">${advAttribute.advSize}</span>
				</td>
			</tr>
			<tr>
				<td width="10%" height="22" align="right">静态链接url:</td>
				<td align="left"><span style="width: 65%">${advAttribute.webpageUrl==''?'无':advAttribute.webpageUrl}</span>
				</td>
				<td width="10%" height="22" align="right">广告位状态:</td>
				<td align="left"><span style="width: 65%">${advAttribute.advStatus==0?'可用':'已坏'}</span>
				</td>
			</tr>
			<tr>
				<td height="22" align="right">广告位说明:</td>
				<td colspan="4" align="left">
					<div id="advExplain" style="width:100%;min-height: 100px">
						${advAttribute.advExplain }</div>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>