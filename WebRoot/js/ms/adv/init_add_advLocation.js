
$(function() {
	textareaCss();
});

function textareaCss() {
	KindEditor.ready(function(K) {
		editor = K.create('textarea[name="typeContent"]', {
			resizeType : 1,
			allowPreviewEmoticons : false,
			uploadJson : '../advLocation/upload_adv_locationImage.do',
			items : [ 'image', 'source', 'undo', 'redo', 'preview',
					'plainpaste', 'wordpaste', 'justifyleft', 'justifycenter',
					'justifyright', 'justifyfull', 'insertorderedlist',
					'insertunorderedlist', 'subscript', 'superscript',
					'clearhtml', 'formatblock', 'fontname', 'fontsize',
					'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
					'strikethrough', 'lineheight', 'removeformat', 'table',
					'hr', 'anchor', 'link', 'unlink' ]
		});
	});
	
	
	// 浏览器
	var browserName = navigator.userAgent.toLowerCase();

	String.prototype.trim = function() {
		return this.replace(/(^\s*)|(\s*$)/g, "");
	}
	
	// 保存广告类型
	$('#btnSave').click(function() {
		// 提交数据
		$.ajax({
			type : 'post',
			cache : false,
			url : 'add_advtype.do',
			data : {
				'parentTypeCode' : $('#parentTypeCode').val(),// 类型编码
				'typeName' : $('#typeName').val(),// 广告类型名称
				'typeState' : $('#typeState').val(),// 状态
				'typeContent' : $('#typeContent').val()  	// 说明
			},
			dataType : 'json',
			success : function(data) {
				if (data.code == 1) {
					parent.$('#winadd').window('close');// 关闭新增窗体
					parent.showmsg("增加成功");
					parent.reload1();
				} else {
					parent.showmsg(data.text);
				}
			}
		});

	});

}

function clearForm() {
	$('#advtypeForm').form('clear');
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