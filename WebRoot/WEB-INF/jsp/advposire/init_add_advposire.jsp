<%@ page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增广告位发布</title>
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
	src="${ctx}/js/ms/advposire/init_add_advposire.js"></script>
</head>
<body>
	<form id="advplacereleaseForm" id="advplacereleaseForm" method="post">
		<input type="hidden" id="ctx1" value="${ctx }" />
		<table class="commonTab" border="1" style="border-color:#e3e8f9;">
			<tr bgcolor="#f4f4f4">
				<td height="22" colspan="3" align="left" style="margin-left:20px;"><b>广告位发布信息</b>
				</td>
				<td align="right"><a id="btnSave" href="javascript:void();"
					class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
				</td>
			</tr>

			<tr>
				<td height="22" align="right" style="width: 100px">*广告位:</td>
				<td colspan="3">
					<div id="advheight"
						style="height:90px;overflow-y:auto;overflow-x:hidden;">
						<table class="work-table" id="advinfo">
							<tr>
								<td width="3%">序号</td>
								<td height="22" align="center">地区名称</td>
								<td width="60%" height="22" align="center">详细地址</td>
								<td height="22" align="center">广告位类型</td>
							</tr>
							<tr>
								<td id="seqenceadv" align="center">1</td>
								<td align="center"><input type="hidden" name="advId" /> <input
									type="text" name="regionName" style="height:20px;padding:0px;"
									onclick="selectAdv();" />
								</td>
								<td align="center"><input type="text"
									name="detailedAddress" style="height:20px;padding:0px;"
									readonly="readonly" />
								</td>
								<td align="center"><input name="typeCode" type="hidden" />
									<input type="text" name="typeName"
									style="width: 60%;height:20px;padding:0px;" readonly="readonly" />
								</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
			<tr>
				<td height="22">起拍价:</td>
				<td><input type="text" id="startingPrice" name="startingPrice"
					style="width: 65%" class="easyui-numberbox"
					data-options="min:0,max:999999999,precision:0" /></td>
				<td height="22">加价幅度:</td>
				<td><input type="text" id="increase" name="increase"
					style="width: 65%" class="easyui-numberbox"
					data-options="min:0,max:99999,precision:0" /></td>
			</tr>
			<tr>
				<td height="22">评估价:</td>
				<td><input type="text" id="assessmentPrice"
					name="assessmentPrice" style="width: 65%" class="easyui-numberbox"
					data-options="min:0,max:999999999,precision:0" />
				</td>
			</tr>
			<tr>
				<td height="22" align="right">开始竞拍时间:</td>
				<td align="left"><input type="text" id="startBiddingPeriod"
					name="startBiddingPeriod" class="easyui-datebox" /></td>
				<td height="22" align="right">结束竞拍时间:</td>
				<td align="left"><input type="text" id="endBiddingPeriod"
					name="endBiddingPeriod" class="easyui-datebox" /></td>
			</tr>
			<tr>
				<td height="22" align="right">开始展示时间:</td>
				<td align="left"><input type="text" id="startUseTime"
					name="startUseTime" class="easyui-datebox" />
				</td>
				<td height="22" align="right">结束展示时间:</td>
				<td align="left"><input type="text" id="endUseTime"
					name="endUseTime" class="easyui-datebox" />
				</td>
			</tr>
			<tr>
				<td height="22" align="right">*竞价须知:</td>
				<td colspan="4"><textarea name="biddingNotice"
						id="biddingNotice"
						style="width:100%;height:300px;visibility:hidden;"></textarea></td>
			</tr>
			<tr>
				<td height="22" align="right">*竞拍公告:</td>
				<td colspan="4"><textarea name="biddingBulletin"
						id="biddingBulletin"
						style="width:100%;height:300px;visibility:hidden;"></textarea></td>
			</tr>
		</table>
	</form>
</body>
</html>