
$(function() {
	$('#btnSave').click(function() {
		$("#advForm").form("submit",{
			url:ctx+'/auctionAttribute/add.do',
			onSubmit:function(){
				return $("#advForm").form('validate');  
			},  
			success:function(data){
				data = $.parseJSON(data);
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
});

function clearForm() {
	$('#advForm').form('clear');
	// parent.$('#win').window('close');
}

function attrId_blur(obj){
	var id=obj.value;
	if(!id){
		return ;
	}
	$.post(
		ctx+'/auctionAttribute/checkId.do',
		{id:id},
		function(data){
			if(data && data.result){
				
			}else{
				parent.showmsg("编号已存在！");
				$(obj).val("").focus();
			}
		},'json');
}