<%@ page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>广告位发布详情</title>
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
	<form id="advplacereleaseForm" id="advplacereleaseForm" method="post">
		<table class="commonTab" border="1" style="border-color:#e3e8f9;">
			<tr bgcolor="#f4f4f4">
				<td height="22" colspan="4" align="left" style="margin-left:20px;"><b>广告位发布信息</b>
				</td>
			</tr>
			<tr>
				<td width="15%" height="22">广告位编号:</td>
				<td><input type="text"
					value="${advplaceRelease.advAttribute.advId}" readonly="readonly"
					style="width: 65%" />
				</td>
				<td width="15%" height="22">广告位类型:</td>
				<td><input type="text"
					value="${advplaceRelease.advAttribute.advType.typeName}"
					readonly="readonly" style="width: 65%" />
				</td>
			</tr>
			<tr>
				<td width="15%" height="22">广告位地区:</td>
				<td><input type="text"
					value="${advplaceRelease.advAttribute.regionName}"
					readonly="readonly" style="width: 65%" />
				</td>
				<td width="15%" height="22">体验馆/频道:</td>
				<td><input type="text"
					value="${advplaceRelease.advAttribute.advArea}" readonly="readonly"
					style="width: 65%" />
				</td>
			</tr>
			<tr>
				<td width="15%" height="22">体验馆内区域/页面地址:</td>
				<td><input type="text"
					value="${advplaceRelease.advAttribute.advPage}" readonly="readonly"
					style="width: 65%" />
				</td>
				<td width="15%" height="22">网页中具体位置:</td>
				<td><input type="text"
					value="${advplaceRelease.advAttribute.advPosition}"
					readonly="readonly" style="width: 65%" />
				</td>
			</tr>
			<tr>
				<td width="15%" height="22">起拍价:</td>
				<td><input type="text" value="${advplaceRelease.startingPrice}"
					readonly="readonly" style="width: 65%" />
				</td>
				<td width="15%" height="22">加价幅度:</td>
				<td><input type="text" value="${advplaceRelease.increase}"
					readonly="readonly" style="width: 65%" />
				</td>
			</tr>
			<tr>
				<td width="15%" height="22">评估价:</td>
				<td><input type="text"
					value="${advplaceRelease.assessmentPrice}" readonly="readonly"
					style="width: 65%" />
				</td>
				<td width="15%" height="22">发布状态 :</td>
				<td align="left"><span style="width: 65%"> <c:if
							test="${advplaceRelease.advplaceState==0}">
							<font>发布中</font>
						</c:if> <c:if test="${advplaceRelease.advplaceState==-1}">
							<font color="red">发布结束</font>
						</c:if> <c:if test="${advplaceRelease.advplaceState==1}">
							<font color="greed">已售出</font>
						</c:if> </span></td>
			</tr>
			<tr>
				<td width="15%" height="22" align="right">开始竞拍时间:</td>
				<td><input type="text"
					value="${advplaceRelease.startBiddingPeriod }" readonly="readonly"
					style="width: 65%" /></td>
				<td width="15%" height="22" align="right">结束竞拍时间:</td>
				<td><input type="text" id="endDate" name="endDate"
					value="${advplaceRelease.endBiddingPeriod }" readonly="readonly"
					style="width: 65%" /></td>
			</tr>
			<tr>
				<td width="15%" height="22" align="right">开始展示时间:</td>
				<td><input type="text" id="startDate" name="startDate"
					value="${advplaceRelease.startUseTime }" readonly="readonly"
					style="width: 65%" /></td>
				<td width="15%" height="22" align="right">结束展示时间:</td>
				<td><input type="text" id="endDate" name="endDate"
					value="${advplaceRelease.endUseTime }" readonly="readonly"
					style="width: 65%" /></td>
			</tr>
			<tr>
				<td height="22" align="right" style="min-width: 100px">竞价须知:</td>
				<td colspan="3" align="left">
					<div style="width:100%;min-height: 100px;">${advplaceRelease.biddingNotice}</div>
				</td>
			</tr>
			<tr>
				<td height="22" align="right" style="min-width: 100px">竞拍公告:</td>
				<td colspan="3" align="left">
					<div style="width:100%;min-height: 100px;">${advplaceRelease.biddingBulletin}</div>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>