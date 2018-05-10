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
						title : '广告押金信息',
						method : 'post',
						sortName : 'deposit_id', // 排序的列
						sortOrder : 'desc', // 倒序
						collapsible : false,// 可折叠
						striped : true, // 奇偶行颜色不同
						pagination : true, // 显示分页
						rownumbers : true, // 显示行号
						pageList : [ 10, 20, 30, 50 ],
						url : 'query_deposit_list.do',
						idField : 'depositId', // 主键字段
						onBeforeLoad : function(param) {
							var memberName = $('#memberName').val();// 旺乐用户名
							if (memberName != null && $.trim(memberName) != "") {
								param['memberName'] = $.trim(memberName);
							}
							var isReturn = $('#isReturn').val();// 是否归还
							if (isReturn != null && isReturn != "") {
								param['isReturn'] = isReturn;
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
									field : 'depositId',
									title : '押金记录编号',
									width : 100
								},
								{
									field : 'memberId',
									title : '旺乐用户编号',
									width : 100
								},
								{
									field : 'memberName',
									title : '旺乐用户名',
									width : 100
								},
								{
									field : 'memberNo',
									title : '旺乐卡号',
									width : 100
								},
								{
									field : 'glideNumber',
									title : '流水帐号',
									width : 100
								},
								{
									field : 'depositPrice',
									title : '押金金额(元)',
									width : 100
								},
								{
									field : 'isReturn',
									title : '是否归还',
									width : 100,
									formatter : function(value, row, index) {
										if (value == 1) {
											return '<span class="ui-label ui-label-success">已归还</span>';
										} else if (value == 0) {
											return '<span class="ui-label ui-label-failure">未归还</span> ';
										} else if (value == 2) {
											return '<span class="ui-label">违&nbsp;&nbsp;约</span> ';
										}
									}
								} ] ],
						toolbar : [

						{
							id : 'btnModify',
							text : '修改状态',
							iconCls : 'icon-tip',
							handler : function() {
								// 删除时先获取选择行
								var rows = dg1.datagrid("getSelections");
								// 选择要删除的行
								if (rows.length == 1) {
									if (rows[0].isReturn == 0) {

										$.messager
												.confirm(
														"提示",
														"你确定要修改为违约吗?",
														function(r) {
															if (r) {
																$
																		.ajax({
																			type : "post",
																			url : "modify_deposit.do",
																			dataType : "json",
																			data : {
																				'depositId' : rows[0].depositId
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
										$.messager.alert("提示", "只有未归还状态才能修改",
												"info");
									}

								} else {
									$.messager.alert("提示", "请选择要修改的行", "error");
								}
							}
						} ],
						fitColumns : true,
						singleSelect : true
					});
}
