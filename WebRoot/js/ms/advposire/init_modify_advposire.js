var editor1;
var editor2;
$(function() {
	textareaCss();
});

function textareaCss() {
	KindEditor.ready(function(K) {
		editor1 = K.create('textarea[name="biddingNotice"]', {
			resizeType : 1,
			allowPreviewEmoticons : false,
			uploadJson : '../advposire/upload_advposire_image.do',
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

	KindEditor.ready(function(K) {
		editor2 = K.create('textarea[name="biddingBulletin"]', {
			resizeType : 1,
			allowPreviewEmoticons : false,
			uploadJson : '../advposire/upload_advposire_image.do',
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

	$('#btnSave').click(function() {
		save();
	});

}

function clearForm() {
	$('#advplacereleaseForm').form('clear');
}

function selectAdv() {
	if ($("#winadvadd").html() == null || $("#winadvadd").html() == "") {
		$(document.body).append("<div id='winadvadd'></div>");
	}
	openWin("winadvadd", "添加广告位", "../adv/init_add_winadvadd.do?id=1", $(window)
			.width() - 5, $(window).height() - 5);
}

function selectAdvCallBack(advList) {
	if (advList.length == 1) {
		var advAtr = advList[0];
		$("#advinfo").find("tr").last().find("input[name='advId']").val(
				advAtr.advId);
		$("#advinfo").find("tr").last().find("input[name='regionName']").val(
				advAtr.regionName);
		if (advAtr.advPosition != null && advAtr.advPosition != '') {
			$("#advinfo").find("tr").last().find(
					"input[name='detailedAddress']").val(
					advAtr.advArea + advAtr.advPage + advAtr.advPosition);
		} else {
			$("#advinfo").find("tr").last().find(
					"input[name='detailedAddress']").val(
					advAtr.advArea + advAtr.advPage);
		}
		$("#advinfo").find("tr").last().find("input[name='typeCode']").val(
				advAtr.advType.typeCode);
		$("#advinfo").find("tr").last().find("input[name='typeName']").val(
				advAtr.advType.typeName);
	} else {
		$.messager.alert('提示', '一次只能发布一条广告位！', 'info');
	}

}

function save() {

	var advId = "";
	$("#advinfo input").each(function() {
		var name = $(this).attr("name");
		if (name == "advId") {
			if ($(this).val() == "") {
				$.messager.alert('提示', '请选择要发布的广告位！', 'info');
				throw $break;
			}
			advId = $(this).val();
		}
	});
	var advplaceId = $("#advplaceId").val();// id
	var startingPrice = $('#startingPrice').numberbox('getValue');// 起拍价
	var increase = $('#increase').numberbox('getValue');// 加价幅度
	var assessmentPrice = $('#assessmentPrice').numberbox('getValue');// 评估价
	var startBiddingPeriod = $('#startBiddingPeriod').datebox('getValue'); // 获取日期输入框的值
	// 开始时间
	var endBiddingPeriod = $('#endBiddingPeriod').datebox('getValue'); // 获取日期输入框的值
	// 结束时间
	var startUseTime = $('#startUseTime').datebox('getValue'); // 获取日期输入框的值
	// 开始时间
	var endUseTime = $('#endUseTime').datebox('getValue'); // 获取日期输入框的值 结束时间
	var biddingNotice = editor1.html();// 竞价须知
	var biddingBulletin = editor2.html();// 竞价公告

	if ($.trim(startingPrice) == '') {
		$.messager.alert('提示', '请输入起拍价！', 'info');
		return false;
	} else if ($.trim(increase) == '') {
		$.messager.alert('提示', '请输入加价幅度！', 'info');
		return false;
	} else if ($.trim(assessmentPrice) == '') {
		$.messager.alert('提示', '请输入评估价！', 'info');
		return false;
	} else if (!/^(\d{4})-(\d{2})-(\d{2})$/.test(startBiddingPeriod)) {
		$.messager.alert('提示', '请输入正确的开始竞拍时间！', 'info');
		return false;
	} else if (!/^(\d{4})-(\d{2})-(\d{2})$/.test(endBiddingPeriod)) {
		$.messager.alert('提示', '请输入正确的结束竞拍时间！', 'info');
		return false;
	} else if (endBiddingPeriod < startBiddingPeriod) {
		$.messager.alert('提示', '结束竞拍时间应该晚于开始竞拍时间！', 'info');
		return false;
	} else if (!/^(\d{4})-(\d{2})-(\d{2})$/.test(startUseTime)) {
		$.messager.alert('提示', '请输入正确的开始展示时间！', 'info');
		return false;
	} else if (!/^(\d{4})-(\d{2})-(\d{2})$/.test(endUseTime)) {
		$.messager.alert('提示', '请输入正确的结束展示时间！', 'info');
		return false;
	} else if (endUseTime < startUseTime) {
		$.messager.alert('提示', '结束展示时间应该晚于开始展示时间！', 'info');
		return false;
	} else if (biddingNotice == '') {
		$.messager.alert('提示', '请输入竞价须知！', 'info');
		return false;
	} else if (biddingBulletin == '') {
		$.messager.alert('提示', '请输入竞价公告！', 'info');
		return false;
	}
	// 提交数据
	$.ajax({
		type : 'post',
		cache : false,
		url : '../advposire/modify_advposire.do',
		data : {
			"advplaceId" : advplaceId,
			"advId" : advId,
			"startingPrice" : startingPrice,
			"increase" : increase,
			"assessmentPrice" : assessmentPrice,
			"startBiddingPeriod" : startBiddingPeriod,
			"endBiddingPeriod" : endBiddingPeriod,
			"startUseTime" : startUseTime,
			"endUseTime" : endUseTime,
			"biddingNotice" : biddingNotice,
			"biddingBulletin" : biddingBulletin
		},
		dataType : 'json',
		success : function(data) {
			if (data.code == 1) {
				parent.$('#winedit').window('close');// 关闭新增窗体
				parent.showmsg("修改成功");
				parent.reload1();
			} else {
				parent.showmsg(data.text);
			}
		}
	});

}
