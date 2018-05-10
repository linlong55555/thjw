<%@ page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>广告竞价详情</title>
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
	<form id="advbiddingForm" id="advbiddingForm" method="post">
		<table class="commonTab" border="1" style="border-color:#e3e8f9;">
			<tr bgcolor="#f4f4f4">
				<td height="22" colspan="4" align="left" style="margin-left:20px;"><b>广告竞价信息</b>
				</td>
			</tr>
			<tr>
				<td width="10%" height="22">旺乐用户名:</td>
				<td><input type="text" id="memberName" name="memberName"
					value="${advBidding.memberName}" readonly="readonly"
					style="width: 65%" /></td>
				<td width="10%" height="22">旺乐卡号:</td>
				<td><input type="text" id="memberNo" name="memberNo"
					value="${advBidding.memberNo}" readonly="readonly"
					style="width: 65%" /></td>
			</tr>
			<tr>
				<td width="10%" height="22">竞价总价格:</td>
				<td align="left"><font color="red"><span
						style="width: 65%"> ${advBidding.biddingPrice}</span> </font></td>
				<td width="10%" height="22">竞价状态:</td>
				<td align="left"><span style="width: 65%"> <c:if
							test="${advBidding.biddingStatus==0}">
							<font>竞价中</font>
						</c:if> <c:if test="${advBidding.biddingStatus==1}">
							<font color="red">竞价成功</font>
						</c:if> <c:if test="${advBidding.biddingStatus==2}">
							<font color="greed">已发布</font>
						</c:if> <c:if test="${advBidding.biddingStatus==-1}">
							<font color="grey">竞价失败</font>
						</c:if> </span></td>
			</tr>
			<c:if
				test="${advBidding.biddingStatus==1||advBidding.biddingStatus==2}">
				<tr>
					<td width="10%" height="22" align="right">合同编号:</td>
					<td><input type="text" id="agreementNo" name="agreementNo"
						value="${advBidding.agreementNo }" readonly="readonly"
						style="width: 65%" />
					</td>
					<td width="10%" height="22" align="right">合同名称:</td>
					<td><input type="text" id="agreementName" name="agreementName"
						value="${advBidding.agreementName }" readonly="readonly"
						style="width: 65%" />
					</td>
				</tr>
				<tr>
					<td width="10%" height="22" align="right">广告文件:</td>
					<td colspan="3" align="left"><c:if
							test="${advBidding.fileName!=null&&advBidding.fileName!='' }">
							<a
								href="${ctx }/advbidding/download_advbidding_file.do?fileName=${advBidding.fileName }"><font
								color="blue">${advBidding.fileName}</font> </a>
						</c:if></td>
				</tr>
			</c:if>


			<tr>
				<td width="10%" height="22" align="right">广告位:</td>
				<td colspan="3" align="left"><c:if
						test="${fn:length(advBidding.advplaceReleaseList) > 0 }">
						<table border="0" width="100%" cellspacing="0" cellpadding="0">
							<tr>
								<th height="22" align="center" width="8%">起拍价</th>
								<th height="22" align="center" width="8%">评估价</th>
								<th height="22" align="center" width="10%">开始展示时间</th>
								<th height="22" align="center" width="10%">结束展示时间</th>
								<th height="22" align="center" width="8%">发布状态</th>
								<th height="22" align="center" width="45%">详细地址</th>
								<th height="22" align="center" width="13%">广告位类型</th>
							</tr>
							<c:forEach items="${advBidding.advplaceReleaseList }"
								var="advplaceReleaseList">
								<tr>
									<td align="center"><input type="text"
										value="${advplaceReleaseList.startingPrice}"
										readonly="readonly" style="width: 99%" />
									</td>
									<td align="center"><input type="text"
										value="${advplaceReleaseList.assessmentPrice}"
										readonly="readonly" style="width: 99%" />
									</td>
									<td align="center"><input type="text"
										value="${advplaceReleaseList.startUseTime}"
										readonly="readonly" style="width: 99%" />
									</td>
									<td align="center"><input type="text"
										value="${advplaceReleaseList.endUseTime}" readonly="readonly"
										style="width: 99%" />
									</td>
									<td align="center"><span style="width: 65%"> <c:if
												test="${advplaceReleaseList.advplaceState==0}">
												<font>发布中</font>
											</c:if> <c:if test="${advplaceReleaseList.advplaceState==-1}">
												<font color="red">发布结束</font>
											</c:if> <c:if test="${advplaceReleaseList.advplaceState==1}">
												<font color="greed">已售出</font>
											</c:if> </span></td>
									<td align="center"><input type="text"
										value="${advplaceReleaseList.advAttribute.regionName }:${advplaceReleaseList.advAttribute.advArea }${advplaceReleaseList.advAttribute.advPage }${advplaceReleaseList.advAttribute.advPosition }"
										readonly="readonly" style="width: 99%" />
									</td>
									<td align="center"><input type="text"
										value="${advplaceReleaseList.advAttribute.advType.typeName }"
										readonly="readonly" style="width: 99%" />
									</td>
								</tr>
							</c:forEach>
						</table>
					</c:if> <c:if test="${fn:length(advBidding.advplaceReleaseList) == 0 }">
						<font>无广告位</font>
					</c:if>
				</td>
			</tr>
			<tr>
				<td width="10%" height="22" align="right">竞价时间段:</td>
				<td colspan="3" align="left"><c:if
						test="${fn:length(advBidding.biddingTimeList) > 0 }">
						<table border="0" width="100%" cellspacing="0" cellpadding="0">
							<tr>
								<th height="22" align="center">播放开始时间</th>
								<th height="22" align="center">播放结束时间</th>
								<th height="22" align="center">播放次数</th>
							</tr>
							<c:forEach items="${advBidding.biddingTimeList }"
								var="biddingTimeList">
								<tr>
									<td align="center"><input type="text"
										value="${biddingTimeList.startTime }" readonly="readonly"
										style="width: 99%" />
									</td>
									<td align="center"><input type="text"
										value="${biddingTimeList.endTime }" readonly="readonly"
										style="width: 99%" />
									</td>
									<td align="center"><input type="text"
										value="${biddingTimeList.totalNumber }" readonly="readonly"
										style="width: 99%" />
									</td>
								</tr>
							</c:forEach>
						</table>
					</c:if> <c:if test="${fn:length(advBidding.biddingTimeList) == 0 }">
						<font>全天</font>
					</c:if>
				</td>
			</tr>
			<tr>
				<td height="22" align="right" style="min-width: 100px">广告内容描述:</td>
				<td colspan="3" align="left">
					<div id="adverContent" style="width:100%;min-height: 100px;">${advBidding.adverContent}</div>
				</td>
			</tr>
		</table>
	</form>
</body>


</html>