<%@ page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>广告发布详情</title>
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
	<form id="advreleaseForm" id="advreleaseForm" method="post">
		<table class="commonTab" border="1" style="border-color:#e3e8f9;">
			<tr bgcolor="#f4f4f4">
				<td height="22" colspan="4" align="left" style="margin-left:20px;"><b>广告发布信息</b>
				</td>
			</tr>
			<tr>
				<td width="10%" height="22">广告发布名:</td>
				<td><input type="text" id="releaseName" name="releaseName"
					value="${advRelease.releaseName}" readonly="readonly"
					style="width: 65%" />
				</td>
				<td width="10%" height="22">广告竞价主键:</td>
				<td><input type="text" id="biddingId" name="biddingId"
					value="${advRelease.biddingId==null?'无':advRelease.biddingId}"
					readonly="readonly" style="width: 65%" />
				</td>
			</tr>
			<tr>
				<td width="10%" height="22" align="right">开始展示时间:</td>
				<td><input type="text" id="startDate" name="startDate"
					value="${advRelease.startDate }" readonly="readonly"
					style="width: 65%" /></td>
				<td width="10%" height="22" align="right">结束展示时间:</td>
				<td><input type="text" id="endDate" name="endDate"
					value="${advRelease.endDate }" readonly="readonly"
					style="width: 65%" /></td>
			</tr>

			<tr>
				<td width="10%" height="22" align="right">广告位:</td>
				<td colspan="3" align="left"><c:if
						test="${fn:length(advRelease.advAttributeList) > 0 }">
						<table border="0" width="100%" cellspacing="0" cellpadding="0">
							<tr>
								<th height="22" align="center" width="15%">地区名称</th>
								<th height="22" align="center" width="70%">详细地址</th>
								<th height="22" align="center" width="15%">广告位类型</th>
							</tr>
							<c:forEach items="${advRelease.advAttributeList }"
								var="advAttributeList">
								<tr>

									<td align="center"><input type="text"
										value="${advAttributeList.regionName }" readonly="readonly"
										style="width: 99%" /></td>
									<td align="center"><input type="text"
										value="${advAttributeList.advArea }${advAttributeList.advPage }${advAttributeList.advPosition }"
										readonly="readonly" style="width: 99%" /></td>
									<td align="center"><input type="text"
										value="${advAttributeList.advType.typeName }"
										readonly="readonly" style="width: 99%" /></td>
								</tr>
							</c:forEach>
						</table>
					</c:if> <c:if test="${fn:length(advRelease.advAttributeList) == 0 }">
						<font>无广告机</font>
					</c:if></td>
			</tr>

			<tr>
				<td width="10%" height="22" align="right">发布时段:</td>
				<td colspan="3" align="left"><c:if
						test="${fn:length(advRelease.releaseTimeList) > 0 }">
						<table border="0" width="100%" cellspacing="0" cellpadding="0">
							<tr>
								<th height="22" align="center">播放开始时间</th>
								<th height="22" align="center">播放结束时间</th>
								<th height="22" align="center">播放次数</th>
							</tr>
							<c:forEach items="${advRelease.releaseTimeList }"
								var="releaseTimeList">
								<tr>
									<td align="center"><input type="text"
										value="${releaseTimeList.startTime }" readonly="readonly"
										style="width: 99%" /></td>
									<td align="center"><input type="text"
										value="${releaseTimeList.endTime }" readonly="readonly"
										style="width: 99%" /></td>
									<td align="center"><input type="text"
										value="${releaseTimeList.totalNumber }" readonly="readonly"
										style="width: 99%" /></td>
								</tr>
							</c:forEach>
						</table>
					</c:if> <c:if test="${fn:length(advRelease.releaseTimeList) == 0 }">
						<font>全天</font>
					</c:if></td>
			</tr>

			<tr>
				<td height="22" align="right" style="min-width: 100px">发布说明:</td>
				<td colspan="3" align="left">
					<div id="releaseContent" style="width:100%;min-height: 100px;">${advRelease.releaseContent}</div>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>