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
		.propertygrid({
		title : '筛选信息',
        height: 'auto',
        url : 'query_advFiltrate_list.do',
        showGroup: true,
        scrollbarSize: 0,
        columns: [[
                { field: 'filtrateName', title: '原条件结构', width: 50, resizable: true },
                { field: 'content', title: '筛选条件内容', width: 50, resizable: false },
                { field: 'filtrateName', title: '操作属性', width: 100, resizable: false }
        ]]
    });
	
}



function changeState(val) {
	$.messager.confirm("提示", "你确定要修改吗?", function(r) {
		if (r) {
			var typeCode = $(val).attr("typeCode");
			var typeState = $(val).attr("typeState");
			
			if(typeState==0){
				typeState=1;
			}else{
				typeState=0;
			}
			
			$.ajax({
				type : "post",
				url : "update_adv_status.do",
				dataType : "json",
				data : {
					'typeCode' : typeCode,
					'typeState' : typeState
				},
				success : function(data) {
					$.messager.alert('提示', data.text, 'info');
					dg1.datagrid('reload');
				}
			});
		}
	});

}