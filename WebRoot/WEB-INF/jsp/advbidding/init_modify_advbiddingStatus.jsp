<%@ page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>竞价状态修改</title>
<%@ include file="/WEB-INF/jsp/common.jsp"%>
<script type="text/javascript"
	src="${ctx}/js/tool/easyui/plugins/jquery.combo.js"></script>
<script type="text/javascript"
	src="${ctx}/js/ms/advbidding/init_modify_advbiddingStatus.js"></script>
</head>
<body>
	<form method="post">
		<table class="commonTab" border="1" style="border-color:#e3e8f9;">
			<tr bgcolor="#f4f4f4">
				<td align="right" height="22" colspan="2"><a id="btnSave"
					href="javascript:subform();" class="easyui-linkbutton"
					data-options="iconCls:'icon-save'">保存</a>
				</td>
			</tr>
			<tr>
				<td>*竞价状态:</td>
				<td><select id="biddingStatus" name="biddingStatus"
					style="width: 90%">
						<c:if test="${biddingStatus==0 }">
							<option value="1">竞价成功</option>
						</c:if>
						<c:if test="${biddingStatus==1 }">
							<option value="2">已发布</option>
						</c:if>
						<option value="-1">竞价失败</option>
				</select>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>