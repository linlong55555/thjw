$(function() {
	$('#btnSave').click(function() {
		modify();
	});
});

function clearForm() {
	$('#advreleaseForm').form('clear');
}

function modify() {
	var biddingId = $("#biddingId").val();// 竞价编号
	var agreementNo = $("#agreementNo").val();// 合同编号
	var agreementName = $("#agreementName").val();// 合同名

	if ($.trim(agreementNo) == '') {
		$.messager.alert('提示', '请输入合同编号！', 'info');
		//$("#agreementNo")[0].focus();
		return false;
	} else if ($.trim(agreementName) == '') {
		$.messager.alert('提示', '请输入合同名称！', 'info');
		return false;
	}

	// 提交数据
	$.ajax({
		type : 'post',
		cache : false,
		url : '../advbidding/modify_advbidding.do',
		data : {
			"biddingId" : biddingId,
			"agreementNo" : agreementNo,
			"agreementName" : agreementName
		},
		dataType : 'json',
		success : function(data) {
			if (data.code == 1) {
				parent.$('#winedit').window('close');// 关闭新增窗体
				parent.showmsg(data.text);
				parent.reload1();
			} else {
				parent.showmsg(data.text);
			}
		}
	});

}
