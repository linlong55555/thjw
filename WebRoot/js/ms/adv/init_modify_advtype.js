var fileName = '';
var editor;
$(function() {
	textareaCss();
});

function textareaCss() {
	//下拉框判断
		for(var j=0;j<document.getElementById("parentTypeCode").options.length;j++) 
		{ 
			if(document.getElementById("parentTypeCode").options[j].value==$('#parentTypeCode1').val()){
				document.getElementById("parentTypeCode").options[j].selected=true;
			}
		} 

	// 浏览器
	var browserName = navigator.userAgent.toLowerCase();

	String.prototype.trim = function() {
		return this.replace(/(^\s*)|(\s*$)/g, "");
	};
	

	// 保存广告类型
	$('#btnSave').click(function() {
		// 提交数据
		$.ajax({
			type : 'post',
			cache : false,
			url : 'modify_advtype.do',
			data : {
				'typeCode' : $('#typeCode').val(),// 类型编码
				'parentTypeCode' : $('#parentTypeCode').val(),//父类编码
				'typeName' : $('#typeName').val(),// 广告类型名称
				'typeContent' :$('#typeContent').val(),// 备注
				'typeState' : $('#typeState').val()// 状态
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

	});
}

function clearForm() {
	$('#advForm').form('clear');
	// parent.$('#win').window('close');
}

function showOrHide(val) {
	if (val == "01") {
		$("#showOrHide").show();
		$("#imgrows").attr("rowspan",6);
	} else {
		$("#advPosition").val("");
		$("#showOrHide").hide();
		$("#imgrows").attr("rowspan",5);
	}
}