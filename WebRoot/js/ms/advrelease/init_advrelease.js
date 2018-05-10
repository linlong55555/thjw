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
						title : '广告发布信息',
						method : 'post',
						sortName : 'release_id', // 排序的列
						sortOrder : 'desc', // 倒序
						collapsible : false,// 可折叠
						striped : true, // 奇偶行颜色不同
						pagination : true, // 显示分页
						rownumbers : true, // 显示行号
						pageList : [ 10, 20, 30, 50 ],
						url : 'query_advrelease_list.do',
						idField : 'releaseId', // 主键字段
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
							var beginDateE = $('#beginDateE').datebox(
									'getValue'); // 
							if (beginDateE != null && beginDateE != "") {
								param['beginDateE'] = beginDateE;
							}
							var endDateE = $('#endDateE').datebox('getValue');
							if (endDateE != null && endDateE != "") {
								param['endDateE'] = endDateE;
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
						columns : [ [ {
							field : 'releaseId',
							title : '广告发布编号'
						}, {
							field : 'releaseName',
							title : '广告发布名'
						}, {
							field : 'startDate',
							title : '开始展示时间'
						}, {
							field : 'endDate',
							title : '结束展示时间'
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
										openWin("winadd", "新增广告发布",
												"init_add_advrelease.do", $(
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
											openWin("winedit", "修改广告发布信息",
													"init_modify_advrelease.do?releaseId="
															+ row.releaseId,
													$(window).width() - 50,
													$(window).height() - 50);
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
											openWin("windetail", "广告发布详情",
													"init_detail_advrelease.do?releaseId="
															+ row.releaseId,
													$(window).width() - 50,
													$(window).height() - 50);
										} else {
											$.messager.alert("提示", "请选择要编辑的行",
													"question");
										}
									}
								},
								'-',
								{
									id : 'btnClubDel',
									text : '删除',
									iconCls : 'icon-remove',
									handler : function() {
										// 删除时先获取选择行
										var rows = dg1
												.datagrid("getSelections");
										// 选择要删除的行
										if (rows.length > 0) {
											$.messager
													.confirm(
															"提示",
															"你确定要删除吗?",
															function(r) {
																if (r) {
																	$.ajax({
																				type : "post",
																				url : "remove_advrelease.do",
																				dataType : "json",
																				data : {
																					'releaseId' : rows[0].releaseId
																				},
																				success : function(
																						data) {
																					$.messager
																							.alert(
																									'提示',
																									data.text,
																									'info');
																					dg1
																							.datagrid('reload');
																				}
																			});
																}
															});
										} else {
											$.messager.alert("提示", "请选择要删除的行",
													"error");
										}
									}
								} ]
					// 只允许选择一行 单选属性
					});
}
