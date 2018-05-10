<%@ page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改广告发布</title>
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
<script type="text/javascript"
	src="${ctx}/js/ms/advrelease/init_modify_advrelease.js"></script>
</head>
<body>
	<form id="advreleaseForm" id="advreleaseForm" method="post">
		<input type="hidden" id="ctx1" value="${ctx }" /> <input
			type="hidden" id="releaseId" value="${advRelease.releaseId }" />
		<table class="commonTab" border="1" style="border-color:#e3e8f9;">

			<tr bgcolor="#f4f4f4">
				<td height="22" colspan="3" align="left" style="margin-left:20px;"><b>广告发布信息</b>
				</td>
				<td align="right"><a id="btnSave" href="javascript:void();"
					class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
				</td>
			</tr>

			<tr>
				<td height="22" align="right" style="min-width: 100px">*广告发布名:</td>
				<td align="left"><input type="text" id="releaseName"
					name="releaseName" style="width: 85%"
					value="${advRelease.releaseName }" />
				</td>
				<td height="22" align="right" style="min-width: 100px">竞价编号:</td>
				<td align="left"><input type="text" id="biddingId"
					name="biddingId" style="width: 85%"
					value="${advRelease.biddingId }" />
				</td>
			</tr>
			<tr>
				<td height="22" align="right" style="min-width: 100px">*开始展示时间:</td>
				<td align="left"><input type="text" id="startDate"
					name="startDate" class="easyui-datebox"
					value="${advRelease.startDate }" />
				</td>
				<td height="22" align="right" style="min-width: 100px">*结束展示时间:</td>
				<td align="left"><input type="text" id="endDate" name="endDate"
					class="easyui-datebox" value="${advRelease.endDate }" /></td>
			</tr>

			<tr>
				<td height="22" align="right" style="min-width: 100px">*广告位:</td>
				<td colspan="3">
					<div id="advheight"
						style="height:360px;overflow-y:auto;overflow-x:hidden;">
						<table class="work-table" id="advinfo">
							<tr>
								<td width="3%">序号</td>
								<td width="15%" height="22" align="center">地区名称</td>
								<td width="60%" height="22" align="center">详细地址</td>
								<td width="15%" height="22" align="center">广告位类型</td>
								<td width="7%" height="22" align="center">删除</td>
							</tr>
							<c:forEach items="${advRelease.advAttributeList }"
								var="advAttributeList" varStatus="status">
								<tr>
									<td id="seqenceadv" align="center">${status.count}</td>
									<td align="center"><input type="hidden" name="advId"
										value="${advAttributeList.advId }" /> <input type="text"
										name="regionName" style="height:20px;padding:0px;"
										readonly="readonly" onclick="selectAdv();"
										value="${advAttributeList.regionName }" />
									</td>
									<td align="center"><input type="text"
										name="detailedAddress" style="height:20px;padding:0px;"
										readonly="readonly"
										value="${advAttributeList.advArea }${advAttributeList.advPage }${advAttributeList.advPosition }" />
									</td>
									<td align="center"><input name="typeCode" type="hidden"
										value="${advAttributeList.advType.typeCode }" /> <input
										type="text" name="typeName"
										style="width: 60%;height:20px;padding:0px;"
										readonly="readonly"
										value="${advAttributeList.advType.typeName }" /></td>
									<td align="center"><a href="javascript:void(0);"
										onclick="advDel(this);"><img alt="删除"
											src="${ctx}/images/del_hui.png" /> </a></td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</td>
			</tr>

			<tr id="advreHide" style="display: none;">
				<td height="22" align="right" style="min-width: 100px">*发布时间段:</td>
				<td colspan="3">
					<div id="workheight"
						style="height:200px;overflow-y:auto;overflow-x:hidden;">
						<table class="work-table" id="advreleaseinfo">
							<tr>
								<td width="3%">序号<a href="javascript:advreleaseAdd();"><img
										id="add" alt="新增" src="${ctx}/images/add.png" /> </a>
								</td>
								<td height="22" align="center">播放开始时间</td>
								<td height="22" align="center">播放结束时间</td>
								<td height="22" align="center">播放次数</td>
								<td height="22" align="center">删除</td>
							</tr>

							<c:if test="${fn:length(advRelease.releaseTimeList) > 0 }">
								<c:forEach items="${advRelease.releaseTimeList }"
									var="releaseTimeList" varStatus="releasestatus">
									<tr>
										<td id="seqence" align="center">${releasestatus.count}</td>
										<td align="center"><input type="text" name="startTime"
											style="height:20px;padding:0px;" class="easyui-timespinner"
											data-options="showSeconds:true"
											value="${releaseTimeList.startTime }" /></td>
										<td align="center"><input type="text" name="endTime"
											style="height:20px;padding:0px;" class="easyui-timespinner"
											data-options="showSeconds:true"
											value="${releaseTimeList.endTime }" /></td>
										<td align="center"><input type="text" name="totalNumber"
											style="width: 60%;height:20px;padding:0px;"
											class="easyui-numberbox"
											value="${releaseTimeList.totalNumber }" />
										</td>
										<td align="center"><a href="javascript:void(0);"
											onclick="advreleaseDel(this);"><img alt="删除"
												src="${ctx}/images/del_hui.png" /> </a></td>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${fn:length(advRelease.releaseTimeList) == 0 }">
								<tr>
									<td id="seqence" align="center">1</td>
									<td align="center"><input type="text" name="startTime"
										style="height:20px;padding:0px;" class="easyui-timespinner"
										data-options="showSeconds:true" /></td>
									<td align="center"><input type="text" name="endTime"
										style="height:20px;padding:0px;" class="easyui-timespinner"
										data-options="showSeconds:true" /></td>
									<td align="center"><input type="text" name="totalNumber"
										style="width: 60%;height:20px;padding:0px;"
										class="easyui-numberbox" value="0" />
									</td>
									<td align="center"><a href="javascript:void(0);"
										onclick="advreleaseDel(this);"><img alt="删除"
											src="${ctx}/images/del_hui.png" /> </a></td>
								</tr>
							</c:if>
						</table>
					</div>
				</td>
			</tr>

			<tr>
				<td height="22" align="right">*发布说明:</td>
				<td colspan="4"><textarea name="releaseContent"
						id="releaseContent"
						style="width:100%;height:300px;visibility:hidden;">${advRelease.releaseContent}</textarea>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>