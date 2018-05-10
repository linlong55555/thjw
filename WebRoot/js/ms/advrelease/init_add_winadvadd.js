var dg1;
var dg2;
$(function() {
	dg1 = datagrid1('dg1');
	$('#btnQuery1').click(function() { // 查询在做刷新
		reload1();
	});
	$('#btnQuery2').click(function() { // 反选
		selectAdv();
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
						// pagination : true, // 显示分页
						rownumbers : true, // 显示行号
						// pageList : [ 10, 20, 30, 50 ],
						url : 'query_advadd_list.do',
						idField : 'advId', // 主键字段
						onBeforeLoad : function(param) {
							var typeCode = $("#typeCode").val(); // 
							var code = $("#code option:selected").val(); // 

							if (typeCode != null && typeCode != "") {// 
								param['typeCode'] = typeCode;
							}
							if (code != null && code != "") {
								param['code'] = code;
							}
							param['sort'] = param.sort;
							param['order'] = param.order;
							param['page'] = 1;
							param["rows"] = 1000;
						},
						onLoadSuccess : function(data) {
							/*
							 * if (data.rows != null && data.rows != '') {
							 * dg1.datagrid('selectRow', 0); }
							 */
						},
						onSelect : function(rowIndex, rowData) {// 单机体验馆事件
						},
						columns : [ [
								{
									field : 'ck',
									checkbox : true

								},
								{
									field : 'advId',
									title : '广告位主键'
								},
								{
									field : 'advType',
									title : '广告位类型',
									formatter : function(value, row, index) {
										return value.typeName;
									}
								},
								{
									field : 'regionName',
									title : '地区名称'
								},
								{
									field : 'advArea',
									title : '体验馆/频道',
									formatter : function(value, row, index) {
										if (row.advPage != null
												&& row.advPage != '') {
											return row.advArea + row.advPage;
										} else {
											return row.advArea;
										}
									}
								},
								{
									field : 'advSize',
									title : '尺寸大小'
								},
								{
									field : 'xq',
									title : '详情',
									formatter : function(value, row, index) {
										return '<span class="ui-label ui-label-success" onclick="openDetail(this)" advId="'
												+ row.advId + '")>详情</span>';

									}
								} ] ],
						fitColumns : false,
						singleSelect : false,
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

function openDetail(val) {
	var advId = $(val).attr("advId");
	if ($("#windetail").html() == null || $("#windetail").html() == "") {
		$(document.body).append("<div id='windetail'></div>");
	}
	openWin("windetail", "广告位详情", "init_detail_adv.do?advId=" + advId,
			$(window).width(), $(window).height());
}

function selectAdv() {
	var rows = dg1.datagrid("getSelections");
	var boo = true;
	if (rows.length > 0) {
		if ($("#tyId").val() == 2) {// 广告发布
			for ( var i = 1; i < rows.length; i++) {
				if (rows[0].advType.typeCode != rows[i].advType.typeCode) {// 广告类型不同
					boo = false;
					break;
				}
			}
			if (boo) {
				parent.selectAdvCallBack(rows);
				parent.$('#winadvadd').window('close');// 关闭新增窗体
			} else {
				$.messager.alert("提示", "请选择同一类型的广告位!", "error");
			}
		} else if ($("#tyId").val() == 1) {// 广告位发布
			if (rows.length == 1) {
				parent.selectAdvCallBack(rows);
				parent.$('#winadvadd').window('close');// 关闭新增窗体
			} else {
				$.messager.alert("提示", "只能选择一个广告位!", "error");
			}
		}

	} else {
		$.messager.alert("提示", "请选择要发布广告的广告位!", "error");
	}
}