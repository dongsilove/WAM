/**
 * @FileName 	login.js
 * @author 		ljpark
 * @Date 		2021.02.20
 * @Description 로그인
 * @History
 * DATE			AUTHOR		NOTE	
 * -------------------------------------------------
 * 2021.02.20	ljpark		신규
 */

$(function() {
	$("#userId").focus();
	$("#loginForm").validate({
	
		submitHandler : function () { //validation이 끝난 이후의 submit 직전 추가 작업할 부분

			let pars = {"userId": $("#userId").val(), "pwd":$("#pwd").val()};
			$.ajax({
				url : "/api/login/login",
				data : pars,
				type : "POST",
				datatype : "JSON",
				success : function(data) {
					console.log(data);
					if(data == '200') {
						location.href = "/asst/list";
					} else {
						alert("로그인아이디가 없거나 로그인 정보가 일치 하지 않습니다.");
					}
				},
				error : function(request, error) {
					if (request.status == 401) { alert("세션이 종료되었습니다.(세션유지시간 : 30분)"); window.location.href = "/login/actionLogout.do"; return; }  // 2019.11.13 추가 by ljpark
					console.log("message: " + request.responseText + ", error:" + error);
				}
			});
 			
		}
		, rules: { //규칙 - id 값으로 
			  userId       : {required:true} 								
			, pwd          : {required:true} 			
		}
		
	});
		
	/**로그인 버튼**/
	$("#btn_lg").click(function() {
		
		$("#loginForm").submit();
		
	});	
	
});
