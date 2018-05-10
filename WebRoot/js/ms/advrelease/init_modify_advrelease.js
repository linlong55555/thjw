var count = 1;// 播发时间段个数
var countadv = 1;// 广告位个数
var editor;
$(function() {
	textareaCss();
});

function textareaCss() {
	KindEditor.ready(function(K) {
		editor = K.create('textarea[name="releaseContent"]', {
			resizeType : 1,
			allowPreviewEmoticons : false,
			uploadJson : '../advrelease/upload_advrelease_image.do',
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
		modify();
	});

	countadv = $("#advinfo td[id='seqenceadv']").last().html();// 获取到广告位个数
	if (countadv > 1) {// 如果个数大于1则换图片
		$("#advinfo img[id!='add']").attr("src",
				$("#ctx1").val() + "/images/del.png");
	}

	count = $("#advreleaseinfo td[id='seqence']").last().html();// 获取到播放时间段个数
	if (count > 1) {// 如果个数大于1则换图片
		$("#advreleaseinfo img[id!='add']").attr("src",
				$("#ctx1").val() + "/images/del.png");
	}

	if ($("#advinfo input[name='typeCode']").last().val() == "02") {// 如果为广告机广告位
		$("#advreHide").show();// 播放时间段显示
	}

}

function clearForm() {
	$('#advreleaseForm').form('clear');
}

function advDel(row) {// 广告位删除一条
	if (countadv > 1) {
		$.messager.confirm('确认对话框', '是否删除？', function(r) {
			if (r) {
				$(row).parent().parent().remove();
				countadv--;
				$("#advinfo td[id='seqenceadv']").each(function(i) {
					$(this).text(i + 1);
				});
			}
			if (countadv <= 1) {
				$("#advinfo img[id!='add']").attr("src",
						$("#ctx1").val() + "/images/del_hui.png");
			}
		});

	}
}

function selectAdv() {
	if ($("#winadvadd").html() == null || $("#winadvadd").html() == "") {
		$(document.body).append("<div id='winadvadd'></div>");
	}
	openWin("winadvadd", "添加广告位", "../adv/init_add_winadvadd.do?id=2", $(window)
			.width() - 5, $(window).height() - 5);
}

function selectAdvCallBack(advList) {
	$("#advinfo").html(
			"<tr>" + $("#advinfo").find("tr").first().html() + "</tr><tr>"
					+ $("#advinfo").find("tr").first().next().html() + "</tr>");
	countadv = 1;
	$.each(advList,
			function(i, advAtr) {
				if (i > 0) {
					$("#advinfo").append(
							"<tr>" + $("#advinfo").find("tr").last().html()
									+ "</tr>");
					$("#advinfo").find("tr").last().find("td").first().text(
							++countadv);
				} else {
					$("#advinfo").find("tr").last().find("td").first().text(
							countadv);
				}
				$("#advinfo").find("tr").last().find("input[name='advId']")
						.val(advAtr.advId);
				$("#advinfo").find("tr").last()
						.find("input[name='regionName']")
						.val(advAtr.regionName);
				if (advAtr.advPosition != null && advAtr.advPosition != "") {
					$("#advinfo").find("tr").last().find(
							"input[name='detailedAddress']").val(
							advAtr.advArea + advAtr.advPage);
				} else {
					$("#advinfo").find("tr").last().find(
							"input[name='detailedAddress']").val(
							advAtr.advArea + advAtr.advPage
									+ advAtr.advPosition);
				}
				$("#advinfo").find("tr").last().find("input[name='typeCode']")
						.val(advAtr.advType.typeCode);
				$("#advinfo").find("tr").last().find("input[name='typeName']")
						.val(advAtr.advType.typeName);
			});
	if (countadv > 1) {
		$("#advinfo img[id!='add']").attr("src",
				$("#ctx1").val() + "/images/del.png");
	}
	if (advList[0].advType.typeCode == "02") {// 为广告机广告
		$("#advreHide").show();
	} else {
		$("#advreHide").hide();
	}
}

/*
 * function advreleaseAdd() { count++; $("#advreleaseinfo").append( "<tr>" +
 * $("#advreleaseinfo").find("tr").last().html() + "</tr>");
 * $("#advreleaseinfo").find("tr").last().find("td").first().text(count); if
 * (count > 1) { $("#advreleaseinfo img[id!='add']").attr("src",
 * $("#ctx1").val() + "/images/del.png"); } }
 */

function advreleaseAdd() {
	count++;
	var tr = '<tr><td id="seqence" align="center">1</td><td align="center"><input type="text" name="startTime" style="height:20px;padding:0px;" class="easyui-timespinner" data-options="showSeconds:true" /></td>'
			+ '<td align="center"><input type="text" name="endTime" style="height:20px;padding:0px;" class="easyui-timespinner" data-options="showSeconds:true" /></td>'
			+ '<td align="center"><input type="text" name="totalNumber" style="width: 60%;height:20px;padding:0px;" class="easyui-numberbox" value="0" /></td>'
			+ '<td align="center"><a href="javascript:void(0);" onclick="advreleaseDel(this);"><img alt="删除" src="${ctx}/images/del_hui.png" /> </a></td></tr>';
	$("#advreleaseinfo").append(tr);
	$.parser.parse($("#advreleaseinfo"));
	$("#advreleaseinfo").find("tr").last().find("td").first().text(count);
	if (count > 1) {
		$("#advreleaseinfo img[id!='add']").attr("src",
				$("#ctx1").val() + "/images/del.png");
	}
}

function advreleaseDel(row) {
	if (count > 1) {
		$.messager.confirm('确认对话框', '是否删除？', function(r) {
			if (r) {
				$(row).parent().parent().remove();
				count--;
				$("#advreleaseinfo td[id='seqence']").each(function(i) {
					$(this).text(i + 1);
				});
			}
			if (count <= 1) {
				$("#advreleaseinfo img[id!='add']").attr("src",
						$("#ctx1").val() + "/images/del_hui.png");
			}
		});

	}
}

function modify() {
	var releaseId = $("#releaseId").val();// 广告发布编号
	var releaseName = $("#releaseName").val();// 广告发布名
	var startDate = $('#startDate').datebox('getValue'); // 获取日期输入框的值 开始时间
	var endDate = $('#endDate').datebox('getValue'); // 获取日期输入框的值 结束时间
	var releaseContent = editor.html();// 发布说明
	var biddingId = $("#biddingId").val();// 竞价编号

	if ($.trim(releaseName) == '') {
		$.messager.alert('提示', '请输入广告发布名！', 'info');
		return false;
	} else if (!/^(\d{4})-(\d{2})-(\d{2})$/.test(startDate)) {
		$.messager.alert('提示', '请输入正确的开始展示时间！', 'info');
		return false;
	} else if (!/^(\d{4})-(\d{2})-(\d{2})$/.test(endDate)) {
		$.messager.alert('提示', '请输入正确的结束展示时间！', 'info');
		return false;
	} else if (endDate < startDate) {
		$.messager.alert('提示', '结束展示时间应该晚于开始展示时间！', 'info');
		return false;
	}

	var advId = "";
	$("#advinfo input").each(function() {
		var name = $(this).attr("name");
		if (name == "advId") {
			if ($(this).val() == "") {
				$.messager.alert('提示', '请选择要发布的广告位！', 'info');
				throw $break;
			}
			advId += $(this).val() + "-";
		}
	});
	var startTime = "";
	var endTime = "";
	var totalNumber = "";
	if ($("#advinfo input[name='typeCode']")[0].value == "02") {// 为广告机广告位
		$("#advreleaseinfo input").each(function() {
			var name = $(this).attr("name");
			if (name == "startTime") {
				if ($(this).timespinner('getValue') == "") {
					$.messager.alert('提示', '请输入播放开始时间！', 'info');
					throw $break;
				}
				startTime += $(this).val() + "-";
			} else if (name == "endTime") {
				if ($(this).timespinner('getValue') == "") {
					$.messager.alert('提示', '请输入播放结束时间！', 'info');
					throw $break;
				}
				endTime += $(this).val() + "-";
			} else if (name == "totalNumber") {
				if ($(this).val() == "" || $(this).val() <= 0) {
					$.messager.alert('提示', '请输入正确的播放次数！', 'info');
					throw $break;
				}
				totalNumber += $(this).val() + "-";
			}
		});
	}
	if (releaseContent == '') {
		$.messager.alert('提示', '请输入发布说明！', 'info');
		return false;
	}
	// 提交数据
	$.ajax({
		type : 'post',
		cache : false,
		url : '../advrelease/modify_advrelease.do',
		data : {
			"releaseId" : releaseId,
			"releaseName" : releaseName,
			"startDate" : startDate,
			"endDate" : endDate,
			"releaseContent" : releaseContent,
			"advId" : advId,
			"startTime" : startTime,
			"endTime" : endTime,
			"totalNumber" : totalNumber,
			"biddingId" : biddingId
		},
		dataType : 'json',
		success : function(data) {
			if (data.code == 1) {
				parent.$('#winedit').window('close');// 关闭新增窗体
				parent.showmsg("修改成功!");
				parent.reload1();
			} else {
				parent.showmsg(data.text);
			}
		}
	});

}
