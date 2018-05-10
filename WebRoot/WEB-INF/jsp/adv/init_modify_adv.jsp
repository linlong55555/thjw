<%@ page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改广告位</title>
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
	src="${ctx }/js/ms/adv/init_modify_adv.js"></script>
</head>
<body>
	<form id="advForm" id="advForm" method="post"
		enctype="multipart/form-data" accept-charset="utf-8">
		<table class="commonTab" border="1" style="border-color:#e3e8f9;">
			<tr bgcolor="#f4f4f4">
				<td height="22" colspan="4" align="left" style="margin-left:20px;"><b>广告位信息</b>
				</td>
				<td align="right"><a id="btnSave" href="javascript:void();"
					class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
				</td>
			</tr>
			<tr>
				<td width="10%" height="22"><input type="hidden" id="advId"
					name="advId" value="${advAttribute.advId}" />*广告位类型:</td>
				<td width="27%"><select id="typeCode" name="typeCode"
					style="width: 65%" onchange="showOrHide(this.value);">
						<c:forEach items="${advTypeList}" var="advTypeList">
							<option value="${advTypeList.typeCode}"
								<c:if test="${advTypeList.typeCode==advAttribute.advType.typeCode}">selected</c:if>>${advTypeList.typeName}</option>
						</c:forEach>
				</select>
				</td>
				<td width="10%" height="22" align="right">*地区名称:</td>
				<td width="27%"><select id="code2" name="code2"
					onchange="selectCity(this.value);" style="height: 22px;width: 30%;">
						<c:forEach items="${regionList}" var="regionList">
							<option value="${regionList.code}">${regionList.name}</option>
						</c:forEach>
				</select> <select id="regionCode" name="regionCode"
					style='height: 22px;width: 30%;'>
				</select><input type="hidden" id="regionC"
					value="${advAttribute.regionCode }" /></td>
				<td rowspan="6" id="imgrows"><input id="basepath"
					value="${ctx }/upload/adv/" style="display: none" /> <img
					id="imgName" src="${ctx }/upload/adv/${advAttribute.imgName}"
					width="220px" height="220px" alt="广告位无图片" /></td>
			</tr>
			<tr>
				<td height="22" align="right" style="min-width: 100px">*体验馆/频道:</td>
				<td align="left"><input type="text" id="advArea" name="advArea"
					style="width: 90%" value="${advAttribute.advArea }" /></td>
				<td height="22" align="right" style="min-width: 140px">*体验馆内区域/页面地址:</td>
				<td align="left"><input type="text" id="advPage" name="advPage"
					style="width: 90%" value="${advAttribute.advPage }" /></td>
			</tr>
			<tr id="showOrHide">
				<td height="22" align="right" style="min-width: 100px">*网页中具体位置:</td>
				<td align="left"><input type="text" id="advPosition"
					name="advPosition" style="width: 90%"
					value="${advAttribute.advPosition }" /></td>
			</tr>

			<tr>
				<td height="22" align="right" style="min-width: 100px">*广告位图片:</td>
				<td align="left" colspan="3"><input type="file" id="fileName"
					name="fileName" /></td>
			</tr>

			<tr>
				<td width="10%" height="22" align="right">格式:</td>
				<td><input type="text" id="advPattern" name="advPattern"
					style="width: 65%" value="${advAttribute.advPattern }" />
				</td>
				<td width="10%" height="22" align="right">*广告位尺寸:</td>
				<td><input type="text" id="advSize" name="advSize"
					style="width: 65%" value="${advAttribute.advSize }" />
				</td>
			</tr>
			<tr>
				<td width="10%" height="22" align="right">静态链接url:</td>
				<td><input type="text" id="webpageUrl" name="webpageUrl"
					style="width: 65%" value="${advAttribute.webpageUrl }" />
				</td>
				<td width="10%" height="22" align="right">*广告位状态:</td>
				<td><select id="advStatus" name="advStatus" style="width: 65%">
						<option value="0"
							<c:if test="${advAttribute.advStatus==0 }">selected</c:if>>可用</option>
						<option value="1"
							<c:if test="${advAttribute.advStatus==1 }">selected</c:if>>已坏</option>
				</select>
				</td>
			</tr>
			<tr>
				<td height="22" align="right">*广告位说明:</td>
				<td colspan="4"><textarea name="advExplain" id="advExplain"
						style="width:100%;height:300px;visibility:hidden;">${advAttribute.advExplain }</textarea>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>