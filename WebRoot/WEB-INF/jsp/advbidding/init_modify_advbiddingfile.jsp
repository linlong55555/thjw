<%@ page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>广告文件详情</title>
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
	src="${ctx}/js/ms/advbidding/init_modify_advbiddingfile.js"></script>
</head>
<body>
	<form id="advbiddingForm" id="advbiddingForm" method="post"
		enctype="multipart/form-data" accept-charset="utf-8">
		<input type="hidden" id="biddingId" name="biddingId"
			value="${advBidding.biddingId }" />
		<table class="commonTab" border="1" style="border-color:#e3e8f9;">
			<tr bgcolor="#f4f4f4">
				<td height="22" colspan="3" align="left" style="margin-left:20px;"><b>广告竞价信息</b>
				</td>
				<td align="right"><a id="btnSave" href="javascript:subform();"
					class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
				</td>
			</tr>
			<tr>
				<td width="10%" height="22">旺乐用户名:</td>
				<td><input type="text" value="${advBidding.memberName}"
					readonly="readonly" style="width: 65%" />
				</td>
				<td width="10%" height="22">旺乐卡号:</td>
				<td><input type="text" value="${advBidding.memberNo}"
					readonly="readonly" style="width: 65%" />
				</td>
			</tr>
			<tr>
				<td width="10%" height="22">竞价总价格:</td>
				<td align="left"><font color="red"><span
						style="width: 65%"> ${advBidding.biddingPrice}</span> </font>
				</td>
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
						</c:if> </span>
				</td>
			</tr>
			<c:if
				test="${advBidding.biddingStatus==1||advBidding.biddingStatus==2}">
				<tr>
					<td width="10%" height="22" align="right">合同编号:</td>
					<td><input type="text" value="${advBidding.agreementNo }"
						style="width: 65%" readonly="readonly" /></td>
					<td width="10%" height="22" align="right">合同名称:</td>
					<td><input type="text" value="${advBidding.agreementName }"
						style="width: 65%" readonly="readonly" /></td>
				</tr>
				<tr>
					<td width="10%" height="22" align="right">*广告文件:</td>
					<td align="left"><span>${advBidding.fileName}</span></td>
					<td colspan="2" align="left"><input type="file" id="fileName"
						name="fileName" />
					</td>
				</tr>
			</c:if>
		</table>
	</form>
</body>


</html>