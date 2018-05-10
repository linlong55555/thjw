function subform() {
	$('#advbiddingForm').ajaxSubmit(
			{
				url : 'upload_advbidding_file.do',
				data : $('#advbiddingForm').formSerialize(),
				type : 'post',
				dataType : 'json',
				success : function(data, status) {
					if (data.code == 0) {
						$.messager.alert('提示', '您的session已失效，请重新登录', 'info',
								function() {
									document.location.href = 'index.jsp';
								});
					} else {
						if (data.code == 1) {
							parent.$('#winfile').window('close');// 关闭新增窗体
							parent.showmsg(data.text);
							parent.reload1();
						}
						$.messager.alert('提示', data.text, 'info');
					}
				}
			});

}