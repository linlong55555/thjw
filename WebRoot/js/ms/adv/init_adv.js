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
						title : '广告位信息',
						method : 'post',
						sortName : 'adv_id', // 排序的列
						sortOrder : 'desc', // 倒序
						collapsible : false,// 可折叠
						striped : true, // 奇偶行颜色不同
						pagination : true, // 显示分页
						rownumbers : true, // 显示行号
						pageList : [ 10, 20, 30, 50 ],
						url : 'query_adv_list.do',
						idField : 'advId', // 主键字段
						onBeforeLoad : function(param) {
							var typeCode = $("#typeCode").val(); // 
							var code = $("#code option:selected").val(); // 
							var advStatus = $("#advStatus").val();

							if (typeCode != null && typeCode != "") {// 
								param['typeCode'] = typeCode;
							}
							if (code != null && code != "") {
								param['code'] = code;
							}
							if (advStatus != null && advStatus != "") {
								param['advStatus'] = advStatus;
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
									field : 'advId',
									title : '广告位编号'
								},
								{
									field : 'advType',
									title : '广告位类型',
									formatter : function(value, row, index) {
										return value.typeName;
									}
								},
								{
									field : 'advStatus',
									title : '状态',
									formatter : function(value, row, index) {
										return value == 0 ? '<span class="ui-label ui-label-success" onclick="changeState(this)" advId="'
												+ row.advId
												+ '" advStatus="1")>可用</span>'
												: '<span class="ui-label ui-label-failure" onclick="changeState(this)" advId="'
														+ row.advId
														+ '" advStatus="0">已坏</span>';
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
										openWin("winadd", "新增广告位",
												"init_add_adv.do", $(window)
														.width() - 50,
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
											openWin("winedit", "修改广告位信息",
													"init_modify_adv.do?advId="
															+ row.advId,
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
											openWin("windetail", "广告位详情",
													"init_detail_adv.do?advId="
															+ row.advId,
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

									id : 'btnModify',
									text : '修改状态',
									iconCls : 'icon-tip',
									handler : function() {
										var rows = dg1
												.datagrid("getSelections");
										if (rows.length == 1) {
											$.messager
													.confirm(
															"提示",
															"你确定要修改吗?",
															function(r) {
																if (r) {
																	$
																			.ajax({
																				type : "post",
																				url : "update_adv_status.do",
																				dataType : "json",
																				data : {
																					'advId' : rows[0].advId,
																					'advStatus' : rows[0].advStatus == 0 ? 1
																							: 0
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
											$.messager.alert("提示", "请选择要修改的行",
													"error");
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
																	var ids = [];
																	for ( var i = 0; i < rows.length; i++) {
																		ids
																				.push(rows[i].advId);
																	}
																	// 将选择到的行存入数组并用,分隔转换成字符串，
																	// 本例只是前台操作没有与数据库进行交互所以此处只是弹出要传入后台的id
																	// alert(ids.join(','));
																	$
																			.ajax({
																				type : "post",
																				url : "remove_adv.do",
																				dataType : "json",
																				data : {
																					'advIds' : ids
																							.join(',')
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

function selectCity(val) {
	if (val == "") {
		$("#code").hide();
		$("#code").html("<option value=''></option>");
	} else {
		$.ajax({
			type : 'post',
			cache : false,
			url : 'select_city.do',
			data : {
				code : val
			},
			dataType : 'json',
			success : function(data) {
				if (data.code == 1) {
					$("#code").show();
					var cityList = data.cityList;
					$("#code").html("");
					for ( var i = 0; i < cityList.length; i++) {
						$("#code").append(
								"<option value=" + cityList[i].code + ">"
										+ cityList[i].name + "</option>");
					}
				} else {
					if (data.code == 9) {
						$.messager.alert('提示', data.text, 'info');
					}
				}
			}
		});

	}
}

function changeState(val) {
	$.messager.confirm("提示", "你确定要修改吗?", function(r) {
		if (r) {
			var advId = $(val).attr("advId");
			var advStatus = $(val).attr("advStatus");
			$.ajax({
				type : "post",
				url : "update_adv_status.do",
				dataType : "json",
				data : {
					'advId' : advId,
					'advStatus' : advStatus
				},
				success : function(data) {
					$.messager.alert('提示', data.text, 'info');
					dg1.datagrid('reload');
				}
			});
		}
	});

}