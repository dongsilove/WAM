/**
 * @FileName 	ajaxUtil.js
 * @author 		ljpark
 * @Date 		2021.02.07
 * @Description ajax 호출
 */

var _ajaxUtils = {
	ajax : function(addOptions) {

		var _defaultOptions = {
			 "method" : "GET"
			, "url": ""
			, "form" : null
			, "successCallback": null // 성공시 처리함수
			, async : true
		};
		
		// 옵션확장
		var options = $.extend( {}, _defaultOptions, addOptions );
		var pars = {};
		console.log(options);
		
		if (options.form != null) {
			pars = options.form.serializeObject();
			processYmd(pars); // '-'제거, 공백제거
			if (options.method.toUpperCase() != "GET") {
				pars = JSON.stringify(pars);
			} else {
				pars["perPage"] =  _paging.paginationOptions.itemsPerPage;
			}
			console.log(pars);
		}
		
		$.ajax({
			url: options.url
			, type: options.method
			, data: pars
			, dataType: "JSON"
			, contentType: 'application/json;charset=UTF-8'
			, async : options.async
			, success: function(data) {
				options.successCallback(data);
			}
			, error: function(request,error) {
				//spinner("hide"); // 로딩이미지 감추기
				if (request.status == 401) {
					window.location.href = "/login/actionLogout.do"; // 2019.11.13 추가 by ljpark
				} else {
					alert("시스템 오류입니다. 잠시 후 다시 접속하시기 바랍니다. [ message: " + request.responseText + "\nerror:" + error+ "]");
				}
			}
		});
	}
	/*
	 *  promise반환. 성공시 조회결과의 []반환
	 */
	,promiseAjax : function(addOptions) {
		var dfd = $.Deferred();
		var _defaultOptions = {
			 "method" : "GET"
			, "url": ""
			, "form" : null
			, "successCallback": null // 성공시 처리함수
			, async : true
		};
		
		// 옵션확장
		var options = $.extend( {}, _defaultOptions, addOptions );
		var pars = {};
		console.log(options);
		
		if (options.form != null) {
			pars = options.form.serializeObject();
			if (options.method.toUpperCase() != "GET") {
				pars = JSON.stringify(pars);
			} else {
				pars["perPage"] =  _paging.paginationOptions.itemsPerPage;
			}
			console.log(pars);
		}
		
		$.ajax({
			url: options.url
			, type: options.method
			, data: pars
			, dataType: "JSON"
			, contentType: 'application/json;charset=UTF-8'
			, async : options.async
			, success: function(data) {
				let result = options.successCallback(data);
				dfd.resolve(result);
			}
			, error: function(request,error) {
				//spinner("hide"); // 로딩이미지 감추기
				if (request.status == 401) {
					window.location.href = "/login/actionLogout.do"; // 2019.11.13 추가 by ljpark
				} else {
					alert("시스템 오류입니다. 잠시 후 다시 접속하시기 바랍니다. [ message: " + request.responseText + "\nerror:" + error+ "]");
				}
				dfd.reject(new Error("message: " + request.responseText + ", error:" + error));
			}
		});
		return dfd.promise();
	}
}