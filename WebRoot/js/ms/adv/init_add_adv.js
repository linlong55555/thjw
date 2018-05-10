var fileName = '';
var editor;
$(function() {
	selectCity($("#code2").val());
	textareaCss();
});

function textareaCss() {
	KindEditor.ready(function(K) {
		editor = K.create('textarea[name="advExplain"]', {
			resizeType : 1,
			allowPreviewEmoticons : false,
			uploadJson : '../adv/upload_adv_image.do',
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
	// 文件改变
	$('#fileName')
			.change(
					function() {
						// 判断文件格式
						var file = $(this).val();
						if (file.lastIndexOf('.') == -1) {
							alert("路径不正确!");
							return;
						}
						var AllImgExt = ".jpg|.jpeg|.gif|.bmp|.png|";
						var extName = file.substring(file.lastIndexOf("."))
								.toLowerCase();
						if (AllImgExt.indexOf(extName + "|") == -1) {
							ErrMsg = "该文件类型不允许上传。请上传 " + AllImgExt
									+ " 类型的文件，当前文件类型为" + extName;
							$.messager.alert('提示', ErrMsg);
							// $(this).val('');
							// alert(ErrMsg);
							return;
						}
						$.messager.progress();	// 显示进度条
						$('#advForm')
								.ajaxSubmit(
										{
											url : 'upload_adv_img.do',
											data : $('#advForm')
													.formSerialize(),
											type : 'post',
											dataType : 'json',
											success : function(data, status) {
												$.messager.progress('close');
												if (data.code == 0) {
													$.messager
															.alert(
																	'提示',
																	'您的session已失效，请重新登录',
																	'info',
																	function() {
																		document.location.href = 'index.jsp';
																	});
												} else {
													if (data.code == 1) {
														fileName = data.text;
														$('#imgName')
																.attr(
																		'src',
																		$(
																				'#basepath')
																				.val()
																				+ data.text);
													} else {
														if (data.code == 9) {
															$.messager.alert(
																	'提示',
																	data.text,
																	'info');
														}
													}
												}
											}
										});
					});

	// 保存影片项目
	$('#btnSave').click(function() {
		// 提交数据
		$.ajax({
			type : 'post',
			cache : false,
			url : 'add_adv.do',
			data : {
				'typeCode' : $('#typeCode').val(),// 类型编码
				'regionCode' : $('#regionCode').val(),// 地区编码
				'imgName' : fileName,// 广告位图片
				'advArea' : $('#advArea').val().trim(),// 详细地址
				'advPage' : $('#advPage').val().trim(),// 详细地址
				'advPosition' : $('#advPosition').val().trim(),// 详细地址
				'advPattern' : $('#advPattern').val().trim(),// 格式
				'advSize' : $('#advSize').val(),// 尺寸
				'webpageUrl' : $('#webpageUrl').val(),// url
				'advStatus' : $('#advStatus').val(),// 状态
				'advExplain' : editor.html()
			// 说明
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
	$('#advForm').form('clear');
	// parent.$('#win').window('close');
}

function selectCity(val) {
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
				var cityList = data.cityList;
				$("#regionCode").html("");
				for ( var i = 0; i < cityList.length; i++) {
					$("#regionCode").append(
							"<option value=" + cityList[i].code + "-"
									+ cityList[i].name + ">" + cityList[i].name
									+ "</option>");
				}
			} else {
				if (data.code == 9) {
					$.messager.alert('提示', data.text, 'info');
				}
			}
		}
	});
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