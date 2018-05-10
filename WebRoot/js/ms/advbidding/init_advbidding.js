var dg1;
var biddingRoes;
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
						title : '竞价信息',
						method : 'post',
						sortName : 'bidding_price', // 排序的列 价格
						sortOrder : 'desc', // 倒序
						collapsible : false,// 可折叠
						striped : true, // 奇偶行颜色不同
						pagination : true, // 显示分页
						rownumbers : true, // 显示行号
						pageList : [ 10, 20, 30, 50 ],
						url : 'query_advbidding_list.do',
						idField : 'biddingId', // 主键字段
						onBeforeLoad : function(param) {
							var biddingStatus = $("#biddingStatus").val();
							if (biddingStatus != null && biddingStatus != "") {
								param['biddingStatus'] = biddingStatus;
							}

							param['sort'] = param.sort;
							param['order'] = param.order;
							param['page'] = param.page;
							param["rows"] = param.rows;
						},
						onLoadSuccess : function(data) {
						},
						onSelect : function(rowIndex, rowData) {// 单机体验馆事件
						},
						columns : [ [ {
							field : 'ck',
							checkbox : true
						}, {
							field : 'biddingId',
							title : '竞价编号',
							width : 60
						}, {
							field : 'memberId',
							title : '旺乐用户编号',
							width : 120
						}, {
							field : 'memberName',
							title : '旺乐用户名',
							width : 100
						}, {
							field : 'memberNo',
							title : '旺乐卡号',
							width : 120
						}, {
							field : 'avgbiddingPrice',
							title : '竞价平均价格',
							width : 100
						}, {
							field : 'biddingPrice',
							title : '竞价总价格',
							width : 100
						}, {
							field : 'biddingStatus',
							title : '竞价状态',
							width : 80,
							formatter : function(value, row, index) {
								if (value == 0) {
									return '<span class="ui-label" >竞价中</span>';
								} else if (value == 1) {
									return '<span class="ui-label ui-label-success" >竞价成功</span>';
								} else if (value == 2) {
									return '<span class="ui-label ui-label-public" >已发布</span>';
								} else if (value == -1) {
									return '<span class="ui-label ui-label-failure" >竞价失败</span>';
								}
							}
						} ] ],
						fitColumns : true,
						singleSelect : false,
						toolbar : [
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
										var rows = dg1
												.datagrid("getSelections");
										if (rows && rows.length == 1) {
											openWin(
													"windetail",
													"竞价详情",
													"init_detail_advbidding.do?biddingId="
															+ rows[0].biddingId,
													$(window).width() - 50,
													$(window).height() - 50);
										} else {
											$.messager.alert("提示", "请选择要查看的一行",
													"question");
										}
									}
								},
								'-',
								{
									id : 'btnEdit',
									text : '修改状态',
									iconCls : 'icon-tip',
									handler : function() {
										if ($("#wineditstue").html() == null
												|| $("#wineditstue").html() == "") {
											$(document.body)
													.append(
															"<div id='wineditstue'></div>");
										}
										// 先获取选择行
										var rows = dg1
												.datagrid("getSelections");
										if (rows.length > 0) {
											var boo = true;
											for ( var i = 1; i < rows.length; i++) {
												if (rows[0].biddingStatus != rows[i].biddingStatus) {
													boo = false;
													break;
												}
											}
											if (boo) {// 所有要修改行的竞技状态相同
												if (rows[0].biddingStatus == 0
														|| rows[0].biddingStatus == 1) {// 竞价中或者竞价成功
													biddingRoes = rows;
													openWin(
															"wineditstue",
															"竞价状态修改",
															"init_modify_advbiddingStatus.do?biddingStatus="
																	+ rows[0].biddingStatus,
															350, 200);
												} else {
													$.messager
															.alert(
																	"提示",
																	"仅“竞价中”或“竞价成功”状态时才可修改!",
																	"question");
												}
											} else {
												$.messager.alert("提示",
														"请选择同一状态要修改的行!",
														"question");
											}

										} else {
											$.messager.alert("提示",
													"请选择要修改状态的行", "question");
										}
									}
								},
								'-',
								{
									id : 'btnView',
									text : '合同',
									iconCls : 'icon-view',
									handler : function() {
										if ($("#winedit").html() == null
												|| $("#winedit").html() == "") {
											$(document.body).append(
													"<div id='winedit'></div>");
										}
										var rows = dg1
												.datagrid("getSelections");
										if (rows && rows.length == 1) {
											if (rows[0].biddingStatus == 1
													|| rows[0].biddingStatus == 2) {
												openWin(
														"winedit",
														"竞价合同",
														"init_modify_advbidding.do?biddingId="
																+ rows[0].biddingId,
														$(window).width() - 50,
														$(window).height() - 50);
											} else {
												$.messager
														.alert(
																"提示",
																"仅“竞价成功”或“已发布”状态时才可修改合同信息！",
																"question");
											}
										} else {
											$.messager.alert("提示",
													"请选择要修改合同的一行", "question");
										}
									}
								},
								'-',
								{
									id : 'btnFile',
									text : '文件',
									iconCls : 'icon-folder',
									handler : function() {
										if ($("#winfile").html() == null
												|| $("#winfile").html() == "") {
											$(document.body).append(
													"<div id='winfile'></div>");
										}
										var rows = dg1
												.datagrid("getSelections");
										if (rows && rows.length == 1) {
											if (rows[0].biddingStatus == 1
													|| rows[0].biddingStatus == 2) {
												openWin(
														"winfile",
														"广告文件",
														"init_modify_advbiddingfile.do?biddingId="
																+ rows[0].biddingId,
														$(window).width() - 50,
														$(window).height() - 50);
											} else {
												$.messager
														.alert(
																"提示",
																"仅“竞价成功”或“已发布”状态时才可修改文件信息！",
																"question");
											}
										} else {
											$.messager.alert("提示",
													"请选择要修改文件的一行", "question");
										}
									}
								} ]
					// 只允许选择一行 单选属性
					});
}

function changeState(bidStatusval) {
	var ids = [];
	for ( var i = 0; i < biddingRoes.length; i++) {
		ids.push(biddingRoes[i].biddingId);
	}
	$.ajax({
		type : "post",
		url : "modify_advbiddingStatus.do",
		dataType : "json",
		data : {
			'ids' : ids.join(','),
			'biddingStatus' : bidStatusval
		},
		success : function(data) {
			if (data.code == 1) {// 成功
				$('#wineditstue').window('close');// 关闭新增窗体
				dg1.datagrid('reload');
			}
			$.messager.alert('提示', data.text, 'info');

		}
	});

}
