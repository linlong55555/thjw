var dg1;
var dg2;
$(function() {
	$("#dg1").width(($(window).width() - 30));
	dg1 = datagrid1('dg1');
	$('#btnQuery1').click(function() { // 查询在做刷新
		reload1();
	});
});

function showmsg(msg) {
	$.messager.alert("提示", msg, 'info');
}

function reload1() {
	dg1.datagrid('reload');
}

function datagrid1(id) {
	return $('#' + id)
			.datagrid(
					{
						title : '广告位发布记录',
						method : 'post',
						sortName : 'advplace_id', // 排序的列
						sortOrder : 'desc', // 倒序
						collapsible : false,// 可折叠
						striped : true, // 奇偶行颜色不同
						pagination : true, // 显示分页
						rownumbers : true, // 显示行号
						pageList : [ 10, 20, 30, 50 ],
						url : 'query_advplace_list.do',
						idField : 'advplaceId', // 主键字段
						onBeforeLoad : function(param) {
							var beginDateS = $('#beginDateS').datebox(
									'getValue'); // 
							if (beginDateS != null && beginDateS != "") {
								param['beginDateS'] = beginDateS;
							}
							var endDateS = $('#endDateS').datebox('getValue');
							if (endDateS != null && endDateS != "") {
								param['endDateS'] = endDateS;
							}
							var typeCode = $("#typeCode").val();
							var advplaceState = $("#advplaceState").val();
							if (typeCode != null && typeCode != "") {
								param['typeCode'] = typeCode;
							}
							if (advplaceState != null && advplaceState != "") {
								param['advplaceState'] = advplaceState;
							}
							param['sort'] = param.sort;
							param['order'] = param.order;
							param['page'] = param.page;
							param["rows"] = param.rows;
						},
						onLoadSuccess : function(data) {
							if (data.rows != null && data.rows != '') {
								dg1.datagrid('selectRow', 0);
							}
						},
						onSelect : function(rowIndex, rowData) {// 单机体验馆事件
						},
						columns : [ [
								{
									field : 'advplaceId',
									title : '广告位发布编号'
								},
								{
									field : 'advAttribute',
									title : '广告位类型',
									formatter : function(value, row, index) {
										return value.advType.typeName;
									}
								},
								{
									field : 'regionName',
									title : '广告位地址',
									formatter : function(value, row, index) {
										return row.advAttribute.regionName
												+ row.advAttribute.advArea;
									}
								},
								{
									field : 'startBiddingPeriod',
									title : '开始竞拍时间'
								},
								{
									field : 'endBiddingPeriod',
									title : '结束竞拍时间'
								},
								{
									field : 'advplaceState',
									title : '发布状态',
									formatter : function(value, row, index) {
										if (value == 0) {
											return '<span class="ui-label" >发布中</span>';
										} else if (value == -1) {
											return '<span class="ui-label ui-label-public" >发布结束</span>';
										} else if (value == 1) {
											return '<span class="ui-label ui-label-success" >已售出</span>';
										}
									}
								} ] ],
						fitColumns : false,
						singleSelect : true,
						toolbar : [
								{
									id : 'btnAdd',
									text : '新增',
									iconCls : 'icon-add',
									handler : function() {
										if ($("#winadd").html() == null
												|| $("#winadd").html() == "") {
											$(document.body).append(
													"<div id='winadd'></div>");
										}
										openWin("winadd", "新增广告位发布记录",
												"init_add_advposire.do", $(
														window).width() - 50,
												$(window).height() - 50);
									}
								},
								'-',
								{
									id : 'btnEdit',
									text : '修改',
									iconCls : 'icon-edit',
									handler : function() {
										if ($("#winedit").html() == null
												|| $("#winedit").html() == "") {
											$(document.body).append(
													"<div id='winedit'></div>");
										}

										var row = dg1.datagrid("getSelected");
										if (row) {
											if (row.advplaceState == 0) {
												openWin(
														"winedit",
														"修改广告位发布信息",
														"init_modify_advposire.do?advplaceId="
																+ row.advplaceId,
														$(window).width() - 50,
														$(window).height() - 50);
											} else {
												$.messager.alert("提示",
														"只有发布中的广告发布信息可以修改",
														"info");
											}
										} else {
											$.messager.alert("提示", "请选择要修改的行",
													"question");
										}
									}
								},
								'-',
								{
									id : 'btnDetail',
									text : '详情',
									iconCls : 'icon-list',
									handler : function() {
										if ($("#windetail").html() == null
												|| $("#windetail").html() == "") {
											$(document.body)
													.append(
															"<div id='windetail'></div>");
										}
										var row = dg1.datagrid("getSelected");
										if (row) {
											openWin("windetail", "广告位发布详情",
													"init_detail_advplace.do?advplaceId="
															+ row.advplaceId,
													$(window).width() - 50,
													$(window).height() - 50);
										} else {
											$.messager.alert("提示", "请选择要编辑的行",
													"question");
										}
									}
								} ]
					// 只允许选择一行 单选属性
					});
}
