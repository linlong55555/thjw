<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ include file="/WEB-INF/jsp/common.jsp"%>
<script type="text/javascript" src="${ctx}/js/index.js"></script>

<script type="text/javascript">
	$(function() {

		/*1 	var json = {
				"typeCode" : "01",
				"regionCode" : "420100"
			};
			$.ajax({
				type : 'post',
				url : 'service/advbidding/queryAdvplaceatt.do',
				data : {
					"version" : 1,
					"data" : JSON.stringify(json)
				},
				dataType : 'json',
				success : function(data) {
					$("#val").html(JSON.stringify(data));
				},
				error : function() {
					alert("程序出现错误");
				}
			}); */

		/* 	var json = {
				"advplaceId" : "1001"
			};
			$.ajax({
				type : 'post',
				url : 'service/advbidding/detailAdvplaceatt.do',
				data : {
					"version" : 1,
					"data" : JSON.stringify(json)
				},
				dataType : 'json',
				success : function(data) {
					$("#val").html(JSON.stringify(data));
				},
				error : function() {
					alert("程序出现错误");
				}
			});  */

		/* 3
		
			$.ajax({
				type : 'post',
				url : 'service/advbidding/find_biddingdeposit.do',
				data : {
					"memberId" : "1001"
				},
				dataType : 'json',
				success : function(data) {
					$("#val").html(JSON.stringify(data));
				},
				error : function() {
					alert("程序出现错误");
				}
			}); */

		/* 4
		
		$.ajax({
		type : 'post',
		url : 'service/advbidding/add_biddingdeposit.do',
		data : {
			"memberId" : "1004",
			"memberName" : "杜123",
			"memberNo" : "1001",
			"glideNumber" : "1001",
			"depositPrice" : "100",
		},
		dataType : 'json',
		success : function(data) {
			$("#val").html(JSON.stringify(data));
		},
		error : function() {
			alert("程序出现错误");
		}
		}); */

		/* 5
		
		$.ajax({
			type : 'post',
			url : 'service/advbidding/update_biddingdeposit.do',
			data : {
				"memberId" : "1004"
			},
			dataType : 'json',
			success : function(data) {
				$("#val").html(JSON.stringify(data));
			},
			error : function() {
				alert("程序出现错误");
			}
		}); */

		/*
		6
		 var json = {
			"number" : "3"
		};
		$.ajax({
			type : 'post',
			url : 'service/advbidding/queryhotAdvattribute.do',
			data : {
				"version" : 1,
				"data" : JSON.stringify(json)
			},
			dataType : 'json',
			success : function(data) {
				$("#val").html(JSON.stringify(data));
			},
			error : function() {
				alert("程序出现错误");
			}
		}); */

		/* 	var json = {
				"numCount" : "2"
			};

			$.ajax({
				type : 'post',
				url : 'service/advbidding/querynewAdvbidding.do',
				data : {
					"version" : 1,
					"data" : JSON.stringify(json)
				},
				dataType : 'json',
				success : function(data) {
					$("#val").html(JSON.stringify(data));
				},
				error : function() {
					alert("程序出现错误");
				}
			}); */

		/* 8
		
			$.ajax({
				type : 'post',
				url : 'service/advbidding/querysell_advattribute.do',
				data : {
					"numCount" : "2"
				},
				dataType : 'json',
				success : function(data) {
					$("#val").html(JSON.stringify(data));
				},
				error : function() {
					alert("程序出现错误");
				}
			});
		 */

		/* var json = {
			"memberId" : "1002",
			"biddingStatus" : "",
			"page" : "0",
			"rows" : "10"
		};

		$.ajax({
			type : 'post',
			url : 'service/advbidding/queryBiddingByMember.do',
			data : {
				"version" : "1",
				"data":JSON.stringify(json)
			},
			dataType : 'json',
			success : function(data) {
				$("#val").html(JSON.stringify(data));
			},
			error : function() {
				alert("程序出现错误");
			}
		});
		 *//* 
										 var json = {
											"biddingId" : "1002"
										};
										
										 $.ajax({
											type : 'post',
											url : 'service/advbidding/detailAdvbidding.do',
											data : {
												"version" : "1",
												"data":JSON.stringify(json)
											},
											dataType : 'json',
											success : function(data) {
												$("#val").html(JSON.stringify(data));
											},
											error : function() {
												alert("程序出现错误");
											}
										}); */

		var json = {
			"memberId" : 2011,
			"memberName" : "杜海1w",
			"memberNo" : "18963984338",
			"avgbiddingPrice" : "1500",
			"biddingPrice" : "100000",
			"adverContent" : "手游广告",
			"biddingTimeList" : [ {
				"startTime" : "14:00:00",
				"endTime" : "16:00:00",
				"totalNumber" : 100
			}, {
				"startTime" : "19:00:00",
				"endTime" : "21:00:00",
				"totalNumber" : 200
			} ],
			"advplaceReleaseList" : [ {
				"advplaceId" : 1001
			}, {
				"advplaceId" : 1002
			} ]
		};
		$.ajax({
			type : 'post',
			url : 'service/advbidding/addAdvbidding.do',
			data : {
				"version" : 1,
				"data" : JSON.stringify(json)
			},
			dataType : 'json',
			success : function(data) {
				$("#val").html(JSON.stringify(data));
			},
			error : function() {
				alert("程序出现错误");
			}
		});

	});
</script>

</head>

<body id="val">



</body>
</html>
