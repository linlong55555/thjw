<%@ page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>广告管理系统</title>
<%@ include file="/WEB-INF/jsp/common.jsp"%>
<script type="text/javascript" src="${ctx}/js/index.js"></script>
<script type="text/javascript" src="${ctx}/js/yhtExtend.js"></script>
</head>

<body id="cc" class="easyui-layout">
	<div data-options="region:'north',border:false"
		style="height:80px;background-color:#f1f5fe;background-image:url('${ctx}/images/guanggao.png');background-repeat:no-repeat;">
		<!-- 登录信息 -->
		<div style="float: right; margin-left: 18px;">
			<div style="float: left; line-height: 25px; margin-left: 70px;">
				<span style="color: #386780">QQ咨询:</span> <span
					style="color: #408080">未公开</span> &nbsp;&nbsp;&nbsp;&nbsp;<span
					style="color: #386780">当前用户:</span> <span style="color: #408080">${emp.empName}</span>
				&nbsp;&nbsp;&nbsp;&nbsp; <span style="color: #386780">职务:</span> <span
					style="color: #408080">${part.partName}</span>
			</div>
			<a href="javascript:void(0)" id="kzmb" class="easyui-menubutton"
				data-options="menu:'#kzmbSub',iconCls:'icon-edit'">控制面板</a>
			<div id="kzmbSub" style="width: 150px;">
				<div data-options="iconCls:'icon-undo'">个人信息</div>
				<div data-options="iconCls:'icon-redo'">修改密码</div>
				<div class="menu-sep"></div>
				<div>XXX</div>
				<div>XXX</div>
				<div>说道</div>
				<div class="menu-sep"></div>
				<div data-options="iconCls:'icon-remove'">听越</div>
				<div>三大</div>
			</div>
			<a href="javascript:void(0)" id="zx" class="easyui-menubutton"
				data-options="menu:'#zxSub',iconCls:'icon-edit'">注销</a>
			<div id="zxSub" style="width: 150px;">
				<div data-options="iconCls:'icon-undo'">退出系统</div>
			</div>
			<div
				style="float: right; margin-left: 8px; margin-right: 5px; margin-top: 5px;">
				<%-- <img src="${ctx}/images/layout_button_up.gif" style="cursor:pointer" onclick="panelCollapase()"> --%>
			</div>
		</div>
	</div>
	<!-- 登录信息 -->
	<div id="tab_rightmenu" class="easyui-menu" style="width: 150px;">
		<div name="tab_menu-tabcloseall">关闭全部标签</div>
		<div name="tab_menu-tabcloseother">关闭其它标签</div>
		<div name="tab_menu-tabcloseright">关闭右侧标签</div>
		<div name="tab_menu-tabcloseleft">关闭左侧标签</div>
	</div>

	<div data-options="region:'west',split:true" style="width: 180px;">

		<div class="easyui-accordion" data-options="fit:true"
			style="border: 0;">

			<div title="广告位管理" style="padding: 10px;">
				<div style="line-height: 180%">
					<a id="initadvtpye" href="javascript:void(0);"
						onclick="addTab('adv1','广告类型管理','${ctx}/advtype/init_adv_type.do');">广告类型管理</a>
				</div>
				<div style="line-height: 180%">
					<a id="initadv" href="javascript:void(0);"
						onclick="addTab('adv','广告位管理','${ctx}/advLocation/init_adv_Location.do');">广告位管理</a>
				</div>
				<div style="line-height: 180%">
					<a id="initAdvFiltrate" href="javascript:void(0);"
						onclick="addTab('AdvFiltrate','筛选条件管理','${ctx}/advFiltrate/init_adv_advFiltrate.do');">筛选条件管理</a>
				</div>
				<div style="line-height: 180%">
					<a id="initrelAdvType" href="javascript:void(0);"
						onclick="addTab('relAdvType','筛选条件广告位关联管理','${ctx}/relAdvType/init_adv_relAdvType.do');">筛选条件广告类关联管理</a>
				</div>
			</div>

		



		<div title="广告位管理" style="padding: 10px;">
			<!-- <div style="line-height:180%">
					<a id="initadvtpye" href="javascript:void(0);"
						onclick="addTab('adv1','广告类型管理','${ctx}/advtype/init_adv_type.do');">广告类型管理</a>
				</div>
				<div style="line-height:180%">
					<a id="initadv" href="javascript:void(0);"
						onclick="addTab('adv','广告位管理','${ctx}/adv/init_adv.do');">广告位管理</a>
				</div>
			
				<div style="line-height:180%">
					<a id="initadv1" href="javascript:void(0);"
						onclick="addTab('adv1','广告位1管理','${ctx}/advvideo/init_adv_vido.do');">广告位1管理</a>
				-->


			<div style="line-height: 180%">
				<a id="initadvposire" href="javascript:void(0);"
					onclick="addTab('advposire','广告位发布管理','${ctx}/advposire/init_advposire.do');">广告位发布管理</a>
			</div>
			<div style="line-height: 180%">
				<a id="initadvbidding" href="javascript:void(0);"
					onclick="addTab('advbidding','广告位竞价管理','${ctx}/advbidding/init_advbidding.do');">广告位竞价管理</a>
			</div>
			<div style="line-height: 180%">
				<a id="initdeposit" href="javascript:void(0);"
					onclick="addTab('deposit','广告押金管理','${ctx}/deposit/init_deposit.do');">广告押金管理</a>
			</div>
			<div style="line-height: 180%">
				<a id="initadvauction" href="javascript:void(0);"
					onclick="addTab('advauction','广告竞拍活动管理','${ctx}/activity/init.do');">广告竞拍活动管理</a>
			</div>
			<div style="line-height: 180%">
				<a id="initadvauction" href="javascript:void(0);"
					onclick="addTab('auctionAttribute','竞拍属性管理','${ctx}/auctionAttribute/init.do');">竞拍属性管理</a>
			</div>
		</div>
		<div style="line-height: 180%">
			<a id="initadvrelease" href="javascript:void(0);"
				onclick="addTab('advrelease','广告发布管理','${ctx}/advrelease/init_advrelease.do');">广告发布管理</a>
		</div>
		</div>
		<%--
			<c:forEach items="${userModuleList}" var="modules">
				<div title="${modules.menu_name}" style="padding:10px;">
					<c:forEach items="${modules.list}" var="childModules">
						<div style="line-height:180%">
							<a id="a_module_${childModules.menu_name}"
								href="javascript:void(0);"
								onclick="addTab('${childModules.menu_code}','${childModules.menu_name}','<%=request.getContextPath() %>${childModules.url}');">${childModules.menu_name}</a>
						</div>
					</c:forEach>
				</div>
			</c:forEach>
--%>
	</div>
	<div id="mainFrame" data-options="region:'center'" class="easyui-tabs">
	</div>
</body>
</html>
