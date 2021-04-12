$(function(){
	$("#sendBtn").click(send_letter);
	$(".close").click(delete_msg);
});

function send_letter() {
	$("#sendModal").modal("hide");

	var toId = $("#recipient-id").val();
	var content = $("#message-text").val();
	var toRole = $("#recipient-role").val();
	$.post(
		"/warehouse/letter/send",
		{"toId":toId,"toRole":toRole,"content":content},
		function (data) {
			data = $.parseJSON(data);
			if (data.code == 0){
				$("#hintBody").text("发送成功");
			}else{
				$("#hintBody").text(data.msg);
			}
			$("#hintModal").modal("show");
			setTimeout(function(){
				$("#hintModal").modal("hide");
				location.reload();
			}, 1000);
		}

	);
}
