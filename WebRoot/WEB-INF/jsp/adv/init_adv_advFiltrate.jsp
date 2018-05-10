<%@ page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>广告类型管理</title>
<%@ include file="/WEB-INF/jsp/common.jsp"%>
<script type="text/javascript"
	src="${ctx }/js/tool/easyui/plugins/jquery.combo.js"></script>
<script type="text/javascript"
	src="${ctx }/js/ms/adv/init_adv_advFiltrate.js"></script>
</head>
<body>
	<table border="0" width="100%" cellspacing="0" cellpadding="4">
		<tr bgcolor="#f4f4f4">
			<td height="22" colspan="7"><b>筛选条件管理</b>
			</td>
		</tr>
		<tr>
		
			<td width="8%" height="22" align="right">添加条件：</td>
			<td width="33%"><input type="text" id="typeName"
				name="typeName"  />---<input type="text" id="typeName"
				name="typeName"  />
			</td>
		
			<td align="right"><a id="btnQuery1" href="javascript:void(0);"
				class="easyui-linkbutton" data-options="iconCls:'icon-search'">添加</a>
			</td>
		</tr>
	</table>
	<div style="margin-top:10px;"></div>
    <table id="edit" ></table>
	<script>
		var rows = [
		            { "filtrateName": "300", "group": "文件大小(kb)", "content": "asf"},
		            { "filtrateName": "400", "group": "文件大小(kb)", "content": "asdas" },
		            { "filtrateName": "职位",  "group": "其它" ,"content": "111"}
		        ];

		$('#edit').propertygrid({
			title : '筛选信息',
	        height: 'auto',
	        showGroup: true,
	        scrollbarSize: 0,
	        columns: [[
	                { field: 'filtrateName', title: '原条件结构', width: 50, resizable: true },
	                { field: 'content', title: '筛选条件内容', width: 50, resizable: false },
	                { field: 'filtrateName', title: '操作属性', width: 100, resizable: false }
	        ]]
	    });
		

		 $('#edit').propertygrid('loadData', rows);
					
		
	</script>
</body>

</html>
